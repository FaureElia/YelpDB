package it.polito.tdp.yelp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.yelp.db.YelpDAO;

public class Model {
	
	Map<String,Business> businessIdMap;
	YelpDAO dao;
	
	public Model() {
		dao=new YelpDAO();
		this.businessIdMap=new HashMap<>();
		//this.businesses=dao.readBusinesses(businessIdMap);
	}
	
	public List<Business> readBusinesses(){
		List<Business> businesses;
		businesses=dao.readBusinesses(businessIdMap);
		return businesses;
		
		
	}
	
	

}
