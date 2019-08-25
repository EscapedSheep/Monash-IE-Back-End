package com.treepal.service;

import java.util.List;

import com.treepal.domain.TreeGeo;

public interface MapService {
	
	List<TreeGeo> findNearbyTree(double[] coordinates, int limit);

}
