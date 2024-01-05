package com.abhijeet.noteapp.Exception;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String msg){
        super(msg);
    }
}
