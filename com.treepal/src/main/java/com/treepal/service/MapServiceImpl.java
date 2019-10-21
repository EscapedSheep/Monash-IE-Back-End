/*
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

	@Override
	public GeoTree save(String species, double lon, double lat, String scientific, String common, String source,
			String height) {
		Properties p = new Properties();
		GeoTree result = new GeoTree();
		Geometry m = new Geometry();
		p.setSpecies(species);
		p.setScientific(scientific);
		p.setCommon(common);
		p.setSource(source);
		p.setHeight(height);
		double[] coor = {lon,lat};
		m.setCoordinates(coor);
		result.setGeometry(m);
		result.setProperties(p);
		return treeGeoRepository.save(result);
	}

	@Override
	public List<GeoTree> findMyRecord(String source) {
		return treeGeoRepository.findBySource(source);
	}



}
*/