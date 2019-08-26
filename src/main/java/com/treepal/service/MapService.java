package com.treepal.service;

import java.util.List;

import com.treepal.domain.GeoTree;


public interface MapService {
	
	List<GeoTree> findBySuburb(String suburb);

}
