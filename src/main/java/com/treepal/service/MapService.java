package com.treepal.service;

import java.util.List;

import com.treepal.domain.GeoTree;


public interface MapService {
	
	List<GeoTree> findBySuburb(String suburb);

	List<GeoTree> findByCoordinates(double lon, double lat, int limit);

	//GeoTree save(double lon, double lat, String name, Object genus, Object family, Object diameter_breast_height, Object year_planted, Object date_planted, Object fulladdress, Object suburb);
}
