package com.treepal.dao;

import java.util.List;

import com.treepal.domain.GeoTree;

public interface TreeGeoRepository {
	
	List<GeoTree> findBySuburb(String suburb);
}
