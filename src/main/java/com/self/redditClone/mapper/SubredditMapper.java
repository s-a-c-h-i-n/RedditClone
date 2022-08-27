package com.self.redditClone.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.self.redditClone.dto.SubredditDto;
import com.self.redditClone.model.Post;
import com.self.redditClone.model.Subreddit;

@Mapper(componentModel = "spring")
public interface SubredditMapper {
	
	//maps subreddit to subredditdto
	@Mapping(target = "postCount", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

	default Integer mapPosts(List<Post> postCount) {
        return postCount.size();
    }
	
	@InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subreddit);
}
