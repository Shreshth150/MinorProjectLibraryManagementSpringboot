package com.example.library.management.minor_project_library_management.exception;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(int id){
        super("User id not found : " + id);
    }

}
