package com.treepal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treepal.dao.TreeGeoRepository;
import com.treepal.domain.TreeGeo;

@Service("MapService")
public class MapServiceImpl implements MapService {

	@Autowired
	private TreeGeoRepository treeGeoRepository;
	
	@Override
	public List<TreeGeo> findNearbyTree(double[] coordinates, int limit) {
		return treeGeoRepository.findNearbyTree(coordinates, limit);
	}

}
