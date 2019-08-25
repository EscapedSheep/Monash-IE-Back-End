package com.treepal.service;

import java.util.List;

import org.springframework.data.geo.GeoResults;

import com.treepal.domain.TreeGeo;

public interface MapService {
	
	GeoResults<TreeGeo> findNearbyTree(double[] coordinates, int limit);

}
