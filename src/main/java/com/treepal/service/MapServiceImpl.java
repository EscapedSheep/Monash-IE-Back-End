package com.treepal.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treepal.dao.TreeGeoRepository;
import com.treepal.domain.GeoTree;
import com.treepal.domain.Geometry;
import com.treepal.domain.Properties;

@Service("MapService")
public class MapServiceImpl implements MapService {

	@Autowired
	private TreeGeoRepository treeGeoRepository;

	@Override
	public List<GeoTree> findBySuburb(String suburb) {
		return treeGeoRepository.findBySuburb(suburb);
	}

	@Override
	public List<GeoTree> findByCoordinates(double lon, double lat, int limit) {
		return treeGeoRepository.findByCoordinates(lon, lat, limit);
	}
/*
	@Override
	public GeoTree save(double lon, double lat, String name, Object genus, Object family, Object diameter_breast_height,
			Object year_planted, Object date_planted, Object fulladdress, Object suburb) {
		GeoTree re = new GeoTree();
		Properties p = new Properties();
		Geometry geo = new Geometry();
		double[] co = {lon, lat};
		geo.setCoordinates(co);
		p.setCommon_name(name);
		p.setGenus(genus);
		p.setFamily(family);
		p.setDate_planted(date_planted);
		p.setDiameter_breast_height(diameter_breast_height);
		p.setYear_planted(year_planted);
		p.setDate_planted(date_planted);
		p.setFulladdress(fulladdress);
		p.setSuburb(suburb);
		re.setGeometry(geo);
		re.setProperties(p);
		return treeGeoRepository.save(re);
	}
*/


}
