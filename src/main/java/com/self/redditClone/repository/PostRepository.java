package com.self.redditClone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.self.redditClone.model.Post;
import com.self.redditClone.model.Subreddit;
import com.self.redditClone.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
