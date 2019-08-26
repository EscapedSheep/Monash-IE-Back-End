package com.treepal.domain;

import java.io.Serializable;

public class GeoTree implements Serializable{
	private static final long serialVersionUID = -3258839839160856613L;
	
	private String _id;
	
	private String type;
	
	private Geometry geometry;
	
	private Properties properties;
	
	public GeoTree() {
		
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	

}
