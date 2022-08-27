package com.self.redditClone.service;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.self.redditClone.dto.SubredditDto;
import com.self.redditClone.exception.SpringRedditException;
import com.self.redditClone.mapper.SubredditMapper;
import com.self.redditClone.model.Subreddit;
import com.self.redditClone.repository.SubredditRepository;

import lombok.AllArgsConstructor;
import static java.util.stream.Collectors.toList;


@Service
@AllArgsConstructor
public class SubredditService {
	
	private final SubredditRepository subredditRepository;
	private final SubredditMapper subredditMapper;
	
	@Transactional
	public SubredditDto save(SubredditDto subredditDto) {
		Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
		subredditDto.setId(save.getId());
		return subredditDto;
	}
	
	@Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }
	
	public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
	
}
