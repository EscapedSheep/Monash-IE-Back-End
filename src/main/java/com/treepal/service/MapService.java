package com.treepal.service;

import java.util.List;

import com.treepal.domain.GeoTree;

/**
*  Map service, define all map function
*
* @author  Maida Ge
* @version 2.0
* @date   2019-09-09
*/
public interface MapService {
	
	List<GeoTree> findBySuburb(String suburb);

	List<GeoTree> findByCoordinates(double lon, double lat, int limit);

	GeoTree save(String species, double lon, double lat, String scientific, String common, String source, String height);
	
	List<GeoTree> findMyRecord(String source);
}
