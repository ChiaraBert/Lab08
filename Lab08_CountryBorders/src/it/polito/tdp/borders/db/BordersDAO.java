package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;



public class BordersDAO {

	public List<Country> loadAllCountries() {
		
		List<Country> paesi = new ArrayList<Country>();

		String sql = "SELECT ccode,StateAbb,StateNme " + "FROM country " + "ORDER BY StateAbb ";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Country b= new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				paesi.add(b);
			}

			conn.close();
			return paesi;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		
		List<Border> confini = new ArrayList<Border>();
		
		final String sql = "SELECT c1.CCode as CCode1, c1.StateAbb as StateAbb1, c1.StateNme as StateNme1, " +
				"c2.CCode as CCode2, c2.StateAbb as StateAbb2, c2.StateNme as StateNme2 " +
				"FROM contiguity, country c1, country c2 " +
				"WHERE contiguity.state1no = c1.CCode " +
				"AND contiguity.state2no = c2.CCode " +
				"AND contiguity.year<=? AND contiguity.conttype=1";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				confini.add(new Border(
						new Country(rs.getInt("CCode1"), rs.getString("StateAbb1"), rs.getString("StateNme1")),
						new Country(rs.getInt("CCode2"), rs.getString("StateAbb2"), rs.getString("StateNme2")))) ;
			}
				
			rs.close();
			conn.close();
			return confini;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- getCountryPairs");
			throw new RuntimeException("Database Error");
		}
		
				
	}
	
	
}
