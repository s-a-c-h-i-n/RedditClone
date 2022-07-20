package com.self.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.self.redditClone.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
