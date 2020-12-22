package com.example.householdappliance.response;


import com.example.householdappliance.constant.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private String code;
    private String message;
    private Object result;
    
    public ApiResponse() {
    	this.code =  Constant.OK;
    }
    
    public static ApiResponse create() {
    	ApiResponse response = new ApiResponse();
    	response.code = Constant.OK;
    	response.message = Constant.SUCCESS;
    	return response;
    	
    }
}
