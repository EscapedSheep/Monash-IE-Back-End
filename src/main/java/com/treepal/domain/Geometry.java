package com.treepal.domain;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

public class Geometry {
	
	private String type;
	
	@GeoSpatialIndexed
	private double[] coordinates;
	
	public Geometry() {
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
	
	
	
	
	

}
