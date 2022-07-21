package com.self.redditClone.exception;

public class SpringRedditException extends RuntimeException {
	public SpringRedditException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
	
	public SpringRedditException(String message) {
        super(message);
    }
}
