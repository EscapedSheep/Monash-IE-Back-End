package com.treepal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treepal.domain.GeoTree;
import com.treepal.domain.RestResult;
import com.treepal.service.MapService;
import com.treepal.utils.ResultGenerater;

@Controller
@RequestMapping(value= {"/activity","/addTree"})
public class ActivityController extends BaseController{

	private final MapService mapService;
	

	private final ResultGenerater resultGenerater;
	
	@Autowired
	public ActivityController(ResultGenerater resultGenerater, MapService mapService) {
		this.resultGenerater = resultGenerater;
		this.mapService = mapService;
	}
	
	@RequestMapping(value="/findBySuburb", method = RequestMethod.GET)
	@ResponseBody
	public RestResult findBySuburb(String suburb) {
		try {
			return resultGenerater.getSuccessResult(mapService.findBySuburb(suburb));
		}
		catch(Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
		}
	}
	
	@RequestMapping(value="findByLocation", method = RequestMethod.GET)
	@ResponseBody
	public RestResult findByLocation(double lon, double lat, int lim) {
		try {
			return resultGenerater.getSuccessResult(mapService.findByCoordinates(lon, lat, lim));
		}
		catch(Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
		}
	}
	/*
	@RequestMapping(value="/addTreeRecord", method=RequestMethod.POST)
	public RestResult addTreeRecord(double lon, double lat, String name, Object genus, Object family, Object height, Object year_planted, Object date_planted, Object fulladdress, Object suburb) {
		try {
			GeoTree result = mapService.save(lon, lat, name, genus, family, height, year_planted, date_planted, fulladdress, suburb);
			return resultGenerater.getSuccessResult(result);
		}
		catch(Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
		}
	}
	*/
}
