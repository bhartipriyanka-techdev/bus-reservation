package org.springboot.busreservationapi.Exception;


import org.springboot.busreservationapi.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationAppException  {
    //---------------------Exception for Admin-------------------------------------------
    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleMerchantAppException(AdminNotFoundException exception){
       ResponseStructure<String>  responseStructure = new ResponseStructure<>();
       responseStructure.setData("Admin Not Found....!");
       responseStructure.setMessage(exception.getMessage());
       responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseStructure);
    }
    //------------------------------Exception for User-----------------------------------
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleMerchantAppException(UserNotFoundException exception){
        ResponseStructure<String>  responseStructure = new ResponseStructure<>();
        responseStructure.setData("User Not Found....!");
        responseStructure.setMessage(exception.getMessage());
        responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseStructure);
    }
    //------------------------------Exception for Bus-----------------------------------
    @ExceptionHandler(BusNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleMerchantAppException(BusNotFoundException exception){
        ResponseStructure<String>  responseStructure = new ResponseStructure<>();
        responseStructure.setData("Bus Not Found....!");
        responseStructure.setMessage(exception.getMessage());
        responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseStructure);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException validException){

        Map<String, String> errors = new HashMap<>();
        List<ObjectError> objectErrorList = validException.getBindingResult().getAllErrors();
        for(ObjectError objectError : objectErrorList){
            String fieldName = ((FieldError)objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        return errors;
    }
}
