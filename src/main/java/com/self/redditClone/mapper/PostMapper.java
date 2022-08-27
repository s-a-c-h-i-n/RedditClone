package com.self.redditClone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.self.redditClone.dto.PostRequest;
import com.self.redditClone.dto.PostResponse;
import com.self.redditClone.model.Post;
import com.self.redditClone.model.Subreddit;
import com.self.redditClone.model.User;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);
}
