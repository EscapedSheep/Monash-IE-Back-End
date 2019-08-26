package com.treepal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.treepal.domain.TreeGeo;

@Component
public class TreeGeoRepositoryImpl implements TreeGeoRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<TreeGeo> findBySuburb(String suburb) {
		return mongoTemplate.find(new Query(Criteria.where("properties.suburb").is(suburb)), TreeGeo.class);
	}


}
