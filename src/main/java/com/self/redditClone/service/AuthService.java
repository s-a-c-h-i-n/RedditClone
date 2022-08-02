package com.self.redditClone.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.AssertingParty.Verification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.self.redditClone.dto.AuthenticationResponse;
import com.self.redditClone.dto.LoginRequest;
import com.self.redditClone.dto.RegisterRequest;
import com.self.redditClone.exception.SpringRedditException;
import com.self.redditClone.model.NotificationEmail;
import com.self.redditClone.model.User;
import com.self.redditClone.model.VerificationToken;
import com.self.redditClone.repository.UserRepository;
import com.self.redditClone.repository.VerificationTokenRepository;
import com.self.redditClone.security.JwtProvider;

import lombok.AllArgsConstructor;
import com.self.redditClone.utils.Constants;

@Service
@AllArgsConstructor
public class AuthService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	private final MailContentBuilder mailContentBuilder;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;

	@Transactional
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		userRepository.save(user);
		
		String token = generateVerificationToken(user);
		String message = mailContentBuilder.build("Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
                + Constants.ACTIVATION_EMAIL + "/" + token);
		mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));
		
	}
	
	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		
		verificationTokenRepository.save(verificationToken);
		return token;
		
	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		
		verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));
		fetchUserAndEnable(verificationToken.get());
	}
	
	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		String userName = verificationToken.getUser().getUsername();
		User user = userRepository.findByUsername(userName).orElseThrow(() -> new SpringRedditException("User not found with name - "+userName));
		user.setEnabled(true);
		
		userRepository.save(user);
	}
	
	//User Authentication
	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        
        return new AuthenticationResponse(token, loginRequest.getUsername());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
