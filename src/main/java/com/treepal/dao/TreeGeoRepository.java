package com.treepal.dao;

import java.util.List;

import org.springframework.data.geo.GeoResults;

import com.treepal.domain.TreeGeo;

public interface TreeGeoRepository {
	
	List<TreeGeo> findBoxTree(double[] coordinates, double[] coordinates2, int limit);

	GeoResults<TreeGeo> findNearbyTree(double[] coordinates, int limit);
}
