package it.polito.tdp.yelp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.yelp.model.Business;

public class YelpDAO {
	
	//static Map<String,Business> businessIdMap=new HashMap<String,Business>();// se fosse statica verrebbe condivisa tra tutte le istanze della classe dao.
	/**
	 * NOTA: QUESTO MECCANISMO E' UTILE PER QUERY CHE POTREBBERO CREARE RISULTATI SOVRAPPOSTI(CIOE' EVITA DI RICREARE DUE OGGETTI UGUALI)
	 * 
	 */
	public List<Business> readBusinesses(Map<String,Business> businessIdMap){
		try {
		Connection conn=DBConnect.getConnection();
		String sql="SELECT * "
				+ "FROM business";
		List<Business> result=new ArrayList<>();
		PreparedStatement st=conn.prepareStatement(sql);
		ResultSet rs=st.executeQuery();
		
		while (rs.next()) {
			if (businessIdMap.containsKey(rs.getString("business_id"))){
				result.add(businessIdMap.get("business_id"));
			}else {
				
				//se non è ancora stato creato il mio oggetto lo creo e lo metto nell mappa che
				//identifica tutti gli oggetti!
				//inoltre se creassi diversi oggetti uguali essi sarebbero tutti identici utilizzando il metodo
				//equals()
				/**QUESTO CAUSEREBBE UN ECCESSIVO UTILIZZO DELLA MEMORIA, OLTRE AD UNA IMPOSSIBILITA' DI MODIFICARE 
				 * ULTERIORI CAMPI (PERCHE' NON SAPREI ESATTAMENTE IN QUALE OGGETTO LO STAREI FACENDO)
				 * 
				 **/
				
				
				Boolean active=rs.getString("active").equals("true");
				Business b=new Business(rs.getString("business_id"),
						rs.getString("full_address"),
						active,
						rs.getString("categories"),
						rs.getString("city"),
						rs.getInt("review_count"),
						rs.getString("business_name"),
						rs.getString("neighborhoods"),
						rs.getDouble("latitude"),
						rs.getDouble("longitude"),
						rs.getString("state"),
						rs.getDouble("stars"));
				result.add(b);
				businessIdMap.put(b.getBusinessId(), b);
			}
		}
		
		conn.close();
		return result;
		
		}catch(SQLException e) {
			System.out.println("");
			
		}
		return null;
		
	}
	
	public Double averageStars(Business b) {
		try {
		Connection conn=DBConnect.getConnection();
		String sql="SELECT AVG(Stars) AS stelle "
				+ "FROM reviews "
				+ "WHERE business_id=? ";
		List<Business> result=new ArrayList<>();
		PreparedStatement st=conn.prepareStatement(sql);
		st.setString(1,b.getBusinessId());
		ResultSet rs=st.executeQuery();
		rs.first();//mi posiziono sulla prima riga
		
		Double stelle=rs.getDouble("stelle");
		
		conn.close();
		
		/**
		 * ATTENZIONE!1
		 * SE MI DIMENTICASSI DI CHIUDERE LA CONNESSIONE, UNA VOLTA ESEGUITA LA QUERY NON LA STAREI RESTITUENDO !
		 * DI CONSEGUENZA IL connection pool non trova mai una connessione libera da dare al db connect!
		 * il programma aspetta fino all'infinito per una nuova connessione!!!
		 */
		return stelle;// non posso fare il return prima di chiudere la connessione!!! l
		
		}catch(SQLException e) {
			System.out.println("");
			
		}
		return null;
		
	}

}
