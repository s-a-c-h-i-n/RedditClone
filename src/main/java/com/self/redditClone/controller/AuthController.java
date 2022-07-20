package com.self.redditClone.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.self.redditClone.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@PostMapping
	public void signup(@RequestBody RegisterRequest registerRequest) {
		
	}
}
