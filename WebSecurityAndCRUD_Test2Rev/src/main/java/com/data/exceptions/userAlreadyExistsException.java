package com.data.exceptions;

public class userAlreadyExistsException extends Exception {
	
	private String message;

    public userAlreadyExistsException(String message){
        super(message);
    }

}
