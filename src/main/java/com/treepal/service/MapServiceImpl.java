package com.treepal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.stereotype.Service;

import com.treepal.dao.TreeGeoRepository;
import com.treepal.domain.TreeGeo;

@Service("MapService")
public class MapServiceImpl implements MapService {

	@Autowired
	private TreeGeoRepository treeGeoRepository;
	
	@Override
	public GeoResults<TreeGeo> findNearbyTree(double[] coordinates, int limit) {
		return treeGeoRepository.findNearbyTree(coordinates, limit);
	}

}
