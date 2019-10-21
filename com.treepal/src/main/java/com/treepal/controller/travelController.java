/*
package com.treepal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treepal.domain.RestResult;
import com.treepal.utils.ResultGenerater;

@Controller
@RequestMapping("/travel")
public class travelController extends BaseController {

	@Autowired
	private ResultGenerater resultGenerater;
	
	@RequestMapping("/test")
	@ResponseBody
	public RestResult test() {
		try {
			return resultGenerater.getFreeResult(200, "Good", null);
		}
		catch(Exception e) {
			return resultGenerater.getFailResult("bad");
		}
	}
}
*/