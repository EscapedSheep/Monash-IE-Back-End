package com.treepal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treepal.domain.RestResult;
import com.treepal.service.MapService;
import com.treepal.utils.ResultGenerater;

@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController{

	private final MapService mapService;
	

	private final ResultGenerater resultGenerater;
	
	@Autowired
	public ActivityController(ResultGenerater resultGenerater, MapService mapService) {
		this.resultGenerater = resultGenerater;
		this.mapService = mapService;
	}
	
	@RequestMapping(value="/findBySuburb", method = RequestMethod.POST)
	@ResponseBody
	public RestResult findBySuburb(@RequestParam String suburb) {
		try {
			return resultGenerater.getSuccessResult(mapService.findBySuburb(suburb));
		}
		catch(Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
		}
	}
}
