package it.polito.tdp.yelp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DBConnect {
	
	public static Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mariadb://localhost/yelp?user=root&password=eliaMaria23" ;
		Connection conn = DriverManager.getConnection(jdbcURL) ;
		return conn;
	}
	
	/**
	static HikariDataSource dataSource; //variabile di classe di tipo statica!
	
	
	
	public static Connection getConnection() throws SQLException { //creo un metodo che mi gestisce la connessione secondo le convenzioni viste ultimamente(per database grandi!
		   if (dataSource==null) {
			   //crea la datasource
			   dataSource=new HikariDataSource();
			   dataSource.setJdbcUrl("jdbc:mariadb://localhost/yelp");
			   dataSource.setUsername("root");
			   dataSource.setPassword("eliaMaria23");
			   
		   }
		   // se la variabile statica datasource esiste già, non viene creata!
		   
		   return dataSource.getConnection(); // questo metodo è un metodo di HikariDataSource!!!
		
		
		
		
	}
	**/
	
	
}
