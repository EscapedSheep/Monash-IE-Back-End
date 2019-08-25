package com.treepal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


import com.treepal.domain.TreeGeo;

@Component
public class TreeGeoRepositoryImpl implements TreeGeoRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<TreeGeo> findNearbyTree(double[] coordinates, int limit) {
		Point point = new Point(coordinates[0], coordinates[1]);
		List<TreeGeo> trees = 
				mongoTemplate.find(new Query(Criteria.where("geometry.coordinates").near(point)).limit(limit), TreeGeo.class);
		return trees;
	}

}
