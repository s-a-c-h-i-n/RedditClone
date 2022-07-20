package com.self.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.self.redditClone.model.Subreddit;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long>{

}
