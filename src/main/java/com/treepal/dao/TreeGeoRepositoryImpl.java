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
	public List<TreeGeo> findNearbyTree(double[] coordinates, double maxDistance) {
		Point point = new Point(coordinates[0], coordinates[1]);
		List<TreeGeo> trees = 
				mongoTemplate.find(new Query(Criteria.where("geometry.coordinates").nearSphere(point).maxDistance(maxDistance)), TreeGeo.class);
		return trees;
	}

}
