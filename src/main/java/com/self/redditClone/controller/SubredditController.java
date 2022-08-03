package com.self.redditClone.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.self.redditClone.dto.SubredditDto;
import com.self.redditClone.service.SubredditService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/subreddit")
public class SubredditController {
	
	private final SubredditService subredditService;

	@PostMapping
	public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditdto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(subredditService.save(subredditdto));
	}
	
	@GetMapping
	public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(subredditService.getAll());
	}
}
