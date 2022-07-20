package com.self.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.self.redditClone.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
