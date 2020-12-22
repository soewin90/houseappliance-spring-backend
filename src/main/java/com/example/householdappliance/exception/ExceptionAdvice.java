package com.example.householdappliance.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.householdappliance.constant.Constant;
import com.example.householdappliance.response.ApiResponse;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException ex, WebRequest request) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<Boolean>(Constant.NOT_OK, Constant.FAIL, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
	
	 @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
         ApiResponse<Boolean> apiResponse = new ApiResponse<Boolean>(Constant.NOT_OK, Constant.FAIL, false);
         return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
     }
	 
	 @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception, WebRequest request) {
		 
		 Map<String, String> errors = new HashMap<String, String>();
		 exception.getBindingResult().getAllErrors().forEach((error) -> {
			 String fieldName = ((FieldError) error).getField();
			 String errorMessage = error.getDefaultMessage();
			 errors.put(fieldName, errorMessage);
		 });
         ApiResponse<Map<String, String>> apiResponse = new ApiResponse<Map<String, String>>(Constant.NOT_OK, Constant.FAIL, errors);
         return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(Exception.class)
     public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
    	 ApiResponse<Boolean> apiResponse = new ApiResponse<Boolean>(Constant.NOT_OK, Constant.FAIL, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
