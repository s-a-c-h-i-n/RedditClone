package com.self.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.self.redditClone.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

}
