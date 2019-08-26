package com.treepal.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treepal.dao.TreeGeoRepository;
import com.treepal.domain.GeoTree;

@Service("MapService")
public class MapServiceImpl implements MapService {

	@Autowired
	private TreeGeoRepository treeGeoRepository;

	@Override
	public List<GeoTree> findBySuburb(String suburb) {
		return treeGeoRepository.findBySuburb(suburb);
	}
	

}
