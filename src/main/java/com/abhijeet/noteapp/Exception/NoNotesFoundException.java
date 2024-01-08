package com.abhijeet.noteapp.Exception;

public class NoNotesFoundException extends RuntimeException{
    public NoNotesFoundException(String message){
        super(message);
    }
}
