package com.treepal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
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
		Query query = Query.query(Criteria.where("properties.suburb").regex(suburb,"i"));
		return mongoTemplate.find(query, GeoTree.class);
	}

	@Override
	public List<GeoTree> findByCoordinates(double lon, double lat, int limit) {
		Query query = new Query(Criteria.where("geometry.coordinates").nearSphere(new Point(lon,lat)));
		query.limit(limit);
		return mongoTemplate.find(query, GeoTree.class);
	}

	@Override
	public GeoTree save(GeoTree geoTree) {
		return mongoTemplate.save(geoTree);
	}

	@Override
	public List<GeoTree> findBySource(String source) {
		Query query = new Query(Criteria.where("properties.source").is(source));
		return mongoTemplate.find(query, GeoTree.class);
	}
	
}
