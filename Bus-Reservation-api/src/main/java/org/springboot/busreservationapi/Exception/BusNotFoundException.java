package org.springboot.busreservationapi.Exception;

public class BusNotFoundException extends RuntimeException{
    public BusNotFoundException(String message){
        super(message);
    }

}
