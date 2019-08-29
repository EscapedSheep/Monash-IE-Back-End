package com.treepal.service;

import java.util.List;

import com.treepal.domain.GeoTree;


public interface MapService {
	
	List<GeoTree> findBySuburb(String suburb);

	List<GeoTree> findByCoordinates(double lon, double lat, int limit);

	GeoTree save(String species, double lon, double lat, String scientific, String common, String source, String height);
	
	List<GeoTree> findMyRecord(String source);
}
