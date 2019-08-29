package com.treepal.controller;

import java.util.List;

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

	@RequestMapping(value="/addNewRecord", method = RequestMethod.POST)
	@ResponseBody
	public RestResult addTree(@RequestBody String species, double lon, double lat, String scientific, String common, String height) {
		try {
			String source = String.valueOf(super.getTree().getId());
			return resultGenerater.getSuccessResult(mapService.save(species, lon, lat, scientific, common, source, height));
		}
		catch(Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
					
		}
		
	}
	
	@RequestMapping(value="/findMyRecord", method = RequestMethod.GET)
	@ResponseBody
	public RestResult findMyRecord() {
		try {
			String source = String.valueOf(super.getTree().getId());
			List<GeoTree> trees = mapService.findMyRecord(source);
			if (trees.size() == 0) 
				return resultGenerater.getFailResult("Oh, seems u did not add any record");
			return resultGenerater.getSuccessResult(trees);
		}
		catch(Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
		}
	}
}
