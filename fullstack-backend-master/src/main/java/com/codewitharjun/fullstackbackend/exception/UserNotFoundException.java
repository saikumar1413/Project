package com.codewitharjun.fullstackbackend.exception;
/* Created by sai */

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could not found the user with id "+ id);
    }
}
