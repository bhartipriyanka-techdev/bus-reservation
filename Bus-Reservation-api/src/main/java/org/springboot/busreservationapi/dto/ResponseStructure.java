package org.springboot.busreservationapi.dto;

import lombok.Data;

@Data
public class ResponseStructure<T> {
    private String message;
    private T data;
    private int statusCode;

}
