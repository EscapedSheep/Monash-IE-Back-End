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
	public List<TreeGeo> findBoxTree(double[] coordinates, double[] coordinates2,int limit) {
		Point point = new Point(coordinates[0], coordinates[1]);
		Point point2 = new Point(coordinates2[0], coordinates[1]);
		Query query = new Query(Criteria.where("geometry.coordinates").within(new Box(point, point2)));
		return mongoTemplate.find(query,TreeGeo.class);
	}

	@Override
	public GeoResults<TreeGeo> findNearbyTree(double[] coordinates, int limit) {
		/*Point point = new Point(coordinates[0],coordinates[1]);
		Query query = new Query(Criteria.where("geometry.coordinates").near(point).maxDistance(20 / 111)).limit(limit);
        return mongoTemplate.find(query, TreeGeo.class);
        
        DBObject near = new BasicDBObject( "loc",JSON.parse("{$near : [ " + lon + "," + lat + " ] }"));  
        DBObject query = new BasicDBObject();  
        //query.put("cityId", 110000);  
        //near.put("cityId", 110000);  
        DBCursor cur = mongoTemplate.g.find(near, query);  
        int n = 0;  
        while(cur.hasNext()){  
            DBObject c = cur.next();  
            BasicDBList loc = (BasicDBList)c.get("loc");  
         }  
         */
		NearQuery query = NearQuery.near(new Point(coordinates[0], coordinates[1]),Metrics.KILOMETERS).num(limit);
		return mongoTemplate.geoNear(query, TreeGeo.class);
		
		
		
		
	}
		

}
