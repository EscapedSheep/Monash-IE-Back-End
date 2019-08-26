package com.treepal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.treepal.domain.GeoTree;


@Component
public class TreeGeoRepositoryImpl implements TreeGeoRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<GeoTree> findBySuburb(String suburb) {
		Query query = Query.query(Criteria.where("properties.suburb").is(suburb));
		return mongoTemplate.find(query, GeoTree.class);
	}


}
