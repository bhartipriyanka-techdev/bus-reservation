package org.springboot.busreservationapi.Exception;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(String message){
        super(message);
    }
}
