package com.self.redditClone.service;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.self.redditClone.dto.SubredditDto;
import com.self.redditClone.model.Subreddit;
import com.self.redditClone.repository.SubredditRepository;

import lombok.AllArgsConstructor;
import static java.util.stream.Collectors.toList;


@Service
@AllArgsConstructor
public class SubredditService {
	
	private final SubredditRepository subredditRepository;
	
	@Transactional
	public SubredditDto save(SubredditDto subredditDto) {
		Subreddit save = subredditRepository.save(mapSubredditDto(subredditDto));
		subredditDto.setId(save.getId());
		return subredditDto;
	}

	private Subreddit mapSubredditDto(SubredditDto subredditDto) {
		return Subreddit.builder().name(subredditDto.getName())
					.description(subredditDto.getDescription())
					.build();
	}
	
	@Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }
	
	private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder().name(subreddit.getName())
                .id(subreddit.getId())
                .postCount(subreddit.getPosts().size())
                .build();
    }
}
