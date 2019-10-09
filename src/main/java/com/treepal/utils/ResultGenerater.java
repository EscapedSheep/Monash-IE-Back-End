package com.treepal.utils;

import org.springframework.stereotype.Component;

import com.treepal.domain.Response;
import com.treepal.domain.RestResult;

/**
*  Generate json result for web controller return to frontend
*
* @author  Maida Ge(Mark)
* @version 3.0
* @date   2019-10-07
*/
@Component
public class ResultGenerater {
	
	public RestResult getSuccessResult() {
        return new RestResult()
                .setCode(Response.SUCCESS.getCode())
                .setMsg(Response.SUCCESS.getMsg());
    }

    public  RestResult getSuccessResult(Object data) {
        return new RestResult()
        		.setCode(Response.SUCCESS.getCode())
                .setMsg(Response.SUCCESS.getMsg())
                .setData(data);
    }

    public  RestResult getSuccessResult(String message,Object data) {
        return new RestResult()
                .setCode(Response.SUCCESS.getCode())
                .setMsg(message)
                .setData(data);
    }

    public  RestResult getFailResult(String message) {
        return new RestResult()
                .setCode(Response.FAIL.getCode())
                .setMsg(message);
    }

    public RestResult getFailResult(String message, Object data) {
        return new RestResult()
                .setCode(Response.FAIL.getCode())
                .setMsg(message)
                .setData(data);
    }

    public RestResult getFreeResult(int code, String message, Object data) {
        return new RestResult()
                .setCode(code)
                .setMsg(message)
                .setData(data);
    }
    
    public RestResult getNoImageResult() {
    	return new RestResult()
    			.setCode(Response.WRONGIMAGE.getCode())
    			.setMsg(Response.WRONGIMAGE.getMsg());
    }

}
