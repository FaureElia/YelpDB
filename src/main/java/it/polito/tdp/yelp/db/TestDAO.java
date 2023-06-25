package it.polito.tdp.yelp.db;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.yelp.model.Business;
import it.polito.tdp.yelp.model.Model;

public class TestDAO {

	public static void main(String[] args) {
		
		YelpDAO dao = new YelpDAO();
		Model model=new Model();
		
		List<Business> businesses=model.readBusinesses();
		System.out.println(businesses.size());
		System.out.println("nessun problema fino a qua");
		long startTime=System.currentTimeMillis();
		List<Double> stelle=new ArrayList<>();
		for (Business b:businesses) {
			Double num=dao.averageStars(b);
			// sto facendo tante piccole query! Il tempo impiegato è alto (a causa del tempo necessario per aprire e chiudere la connessione ogni volta!!
			//di conseguenza mi conviene utilizzare un pattern diverso nel DB Connect, che crea una connessione nuova solo se deve essere creata!
			
			stelle.add(num);
		}
		long endTime=System.currentTimeMillis();
		System.out.println(stelle.size());
		System.out.println(stelle.get(0));
		System.out.println("Time= "+(endTime-startTime)+" ms");
		
		

	}

}