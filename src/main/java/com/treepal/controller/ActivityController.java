package com.treepal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public ActivityController(MapService mapService, ResultGenerater resultGenerater) {
		this.mapService = mapService;
		this.resultGenerater = resultGenerater;
	}
	
	@RequestMapping(value="/findNearbyTree", method = RequestMethod.GET)
	@ResponseBody
	public RestResult findNearbyTree(double log, double lat, double dis) {
		try {
			double[] coordinates = new double[2];
			coordinates[0] = log;
			coordinates[1] = lat;
			return resultGenerater.getSuccessResult(mapService.findNearbyTree(coordinates, dis));
		}
		catch(Exception e) {
			return resultGenerater.getFailResult(e.getMessage());
		}
	}
}
