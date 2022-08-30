package com.data.exceptions;

public class DatabaseException extends Exception {
	
	private String message;

    public DatabaseException(String message){
        super(message);
    }

}
