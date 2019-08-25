package com.treepal.dao;

import java.util.List;

import com.treepal.domain.TreeGeo;

public interface TreeGeoRepository {
	
	List<TreeGeo> findNearbyTree(double[] coordinates, double maxDistance);

}
