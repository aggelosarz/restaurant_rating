package dao;


import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Restaurant;
import models.User;


public class RestaurantDao  {
	private Connection connection;

    public RestaurantDao() {
        connection = DbUtil.getConnection();
      
    }
	
    public List<Restaurant> getBest10Rest() throws SQLException{
    	List<Restaurant> Rests = new ArrayList<Restaurant>();
		String query="SELECT  * from restaurant \r\n" + 
				" order by avg_grade desc , votes desc\r\n" + 
				" limit 10";
		
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			Restaurant rest=new Restaurant();
			rest.setOwnerName(rs.getString("ownerName"));
			rest.setId(rs.getInt("idrestaurant"));
			rest.setPath(rs.getString("path"));
			rest.setFilename(rs.getString("filename"));
			rest.setCity(rs.getString("city"));
			rest.setAdress(rs.getString("adress"));
			rest.setType(rs.getString("type"));
			rest.setDescription(rs.getString("description"));
			 rest.setRestaurantName(rs.getString("name"));
			 rest.setGrade(rs.getFloat("avg_grade"));
			 Rests.add(rest);
		}
	
		return Rests;
    }
    
    
    public List<Restaurant> getBestRestInEveryCity() throws SQLException{
    	List<Restaurant> Rests = new ArrayList<Restaurant>();
		String query="SELECT p1.* \r\n" + 
				"FROM restaurant p1\r\n" + 
				"INNER JOIN\r\n" + 
				"(\r\n" + 
				"    SELECT max(votes) maxVotes\r\n" + 
				"    FROM restaurant\r\n" + 
				"    where avg_grade=(select max(avg_grade) from restaurant)\r\n" + 
				"        group by city\r\n" + 
				"  \r\n" + 
				") p2\r\n" + 
				"  ON p1.votes = p2.maxVotes";
		
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			Restaurant rest=new Restaurant();
			rest.setOwnerName(rs.getString("ownerName"));
			rest.setId(rs.getInt("idrestaurant"));
			rest.setPath(rs.getString("path"));
			rest.setFilename(rs.getString("filename"));
			rest.setCity(rs.getString("city"));
			rest.setAdress(rs.getString("adress"));
			rest.setType(rs.getString("type"));
			rest.setDescription(rs.getString("description"));
			 rest.setRestaurantName(rs.getString("name"));
			 rest.setGrade(rs.getFloat("avg_grade"));
			 Rests.add(rest);
		}
	
		return Rests;
    }
	
	public List<Restaurant> getBestRestInEveryCategory() throws SQLException {
		List<Restaurant> Rests = new ArrayList<Restaurant>();
		String query="SELECT p1.* \r\n" + 
				"FROM restaurant p1\r\n" + 
				"INNER JOIN\r\n" + 
				"(\r\n" + 
				"    SELECT max(votes) maxVotes\r\n" + 
				"    FROM restaurant\r\n" + 
				"    where avg_grade=(select max(avg_grade) from restaurant)\r\n" + 
				"        group by type\r\n" + 
				"  \r\n" + 
				") p2\r\n" + 
				"  ON p1.votes = p2.maxVotes";
		
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			Restaurant rest=new Restaurant();
			rest.setOwnerName(rs.getString("ownerName"));
			rest.setId(rs.getInt("idrestaurant"));
			rest.setPath(rs.getString("path"));
			rest.setFilename(rs.getString("filename"));
			rest.setCity(rs.getString("city"));
			rest.setAdress(rs.getString("adress"));
			rest.setType(rs.getString("type"));
			rest.setDescription(rs.getString("description"));
			 rest.setRestaurantName(rs.getString("name"));
			 rest.setGrade(rs.getFloat("avg_grade"));
			 Rests.add(rest);
		}
	
		return Rests;
		
	}
	
	public void addRestaurant(Restaurant restaurant) throws NoSuchAlgorithmException, SQLException {
		
	
			 
			
	
				
				 
	          
	    		PreparedStatement	  preparedStatement = connection.
	    				  prepareStatement("INSERT INTO restaurant (name,filename,path,city,adress,type,description,ownerName) VALUES (?,?,?,?,?,?,?,?);");
	        	  preparedStatement.setString(1, restaurant.getRestaurantName());
	        	  preparedStatement.setString(2, restaurant.getFilename());
	        	  preparedStatement.setString(3,restaurant.getPath());
	        	  preparedStatement.setString(4,restaurant.getCity());
	        	  preparedStatement.setString(5,restaurant.getAdress());
	        	  preparedStatement.setString(6,restaurant.getType());
	        	  preparedStatement.setString(7,restaurant.getDescription());
	         	  preparedStatement.setString(8,restaurant.getOwnerName());
	        	    preparedStatement.executeUpdate();
	        	    preparedStatement.close();
	        	 
	        	
	        	  
				
	       
		
		
		
	}



	public Restaurant getRestaurantbyCityandAdress(String city,String adress) throws SQLException, NoSuchAlgorithmException  {
		// TODO Auto-generated method stub
		Restaurant rest=new Restaurant();
		String query="SELECT * FROM restaurant where city=\""+city+"\" AND  adress=\""+adress+"\";";
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			rest.setOwnerName(rs.getString("ownerName"));
			rest.setId(rs.getInt("idrestaurant"));
			rest.setPath(rs.getString("path"));
			rest.setFilename(rs.getString("filename"));
			rest.setCity(rs.getString("city"));
			rest.setAdress(rs.getString("adress"));
			rest.setType(rs.getString("type"));
			rest.setDescription(rs.getString("description"));
			 rest.setRestaurantName(rs.getString("name"));
			 rest.setGrade(rs.getFloat("avg_grade"));
			return rest;
		}
	
		return null;
	}
	
	
	
	public List<Restaurant> getRestaurantbyCityType(String city,String type) throws SQLException{
		
		List<Restaurant> Rests = new ArrayList<Restaurant>();
		String query="SELECT * FROM restaurant where  type=\""+type+"\" AND city=\""+city+"\";";
		
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			Restaurant rest=new Restaurant();
			rest.setOwnerName(rs.getString("ownerName"));
			rest.setId(rs.getInt("idrestaurant"));
			rest.setPath(rs.getString("path"));
			rest.setFilename(rs.getString("filename"));
			rest.setCity(rs.getString("city"));
			rest.setAdress(rs.getString("adress"));
			rest.setType(rs.getString("type"));
			rest.setDescription(rs.getString("description"));
			 rest.setRestaurantName(rs.getString("name"));
			 rest.setGrade(rs.getFloat("avg_grade"));
			 Rests.add(rest);
		}
	
		return Rests;
		
		
	}
	
	public Restaurant getRestaurantbyNameCityType(String name,String type,String city) throws SQLException, NoSuchAlgorithmException  {
		// TODO Auto-generated method stub
		Restaurant rest=new Restaurant();
		String query="SELECT * FROM restaurant where name=\""+name+"\" AND type=\""+type+"\" AND city=\""+city+"\";";
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			rest.setOwnerName(rs.getString("ownerName"));
			rest.setId(rs.getInt("idrestaurant"));
			rest.setPath(rs.getString("path"));
			rest.setFilename(rs.getString("filename"));
			rest.setCity(rs.getString("city"));
			rest.setAdress(rs.getString("adress"));
			rest.setType(rs.getString("type"));
			rest.setDescription(rs.getString("description"));
			 rest.setRestaurantName(rs.getString("name"));
			 rest.setGrade(rs.getFloat("avg_grade"));
			return rest;
		}
	
		return null;
	}
	
	public Restaurant getRestaurantbyId(int id) throws SQLException, NoSuchAlgorithmException  {
		// TODO Auto-generated method stub
		Restaurant rest=new Restaurant();
		String query="SELECT * FROM restaurant where idrestaurant=\""+id+"\";";
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			rest.setOwnerName(rs.getString("ownerName"));
			rest.setId(rs.getInt("idrestaurant"));
			rest.setPath(rs.getString("path"));
			rest.setFilename(rs.getString("filename"));
			rest.setCity(rs.getString("city"));
			rest.setAdress(rs.getString("adress"));
			rest.setType(rs.getString("type"));
			rest.setDescription(rs.getString("description"));
			 rest.setRestaurantName(rs.getString("name"));
			 rest.setGrade(rs.getFloat("avg_grade"));
			return rest;
		}
	
		return null;
	}

	public List<Restaurant> getRestaurantbyOwnername(String name) throws SQLException, NoSuchAlgorithmException  {
		// TODO Auto-generated method stub
		 List<Restaurant> Rests = new ArrayList<Restaurant>();
		
		String query="SELECT * FROM restaurant where ownerName=\""+name+"\" ;";
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			Restaurant rest=new Restaurant();
			rest.setOwnerName(rs.getString("ownerName"));
			rest.setId(rs.getInt("idrestaurant"));
			rest.setPath(rs.getString("path"));
			rest.setFilename(rs.getString("filename"));
			rest.setCity(rs.getString("city"));
			rest.setAdress(rs.getString("adress"));
			rest.setType(rs.getString("type"));
			rest.setDescription(rs.getString("description"));
			 rest.setRestaurantName(rs.getString("name"));
			 rest.setGrade(rs.getFloat("avg_grade"));
			 Rests.add(rest);
		}
	
		return Rests;
	}

	public void update(Restaurant rest) throws SQLException {
		// TODO Auto-generated method stub
		  String query = "UPDATE restaurant SET   path = ?, filename= ?,city= ?,adress= ?,type= ?,description= ?,name=? where idrestaurant= ?;";
	      
		  PreparedStatement preparedStmt = connection.prepareStatement(query);
	      preparedStmt.setString   (1, rest.getPath());
	      preparedStmt.setString(2, rest.getFilename());
	      preparedStmt.setString(3,rest.getCity());
	      preparedStmt.setString(4,rest.getAdress());
	      preparedStmt.setString(5,rest.getType());
	      preparedStmt.setString(6,rest.getDescription());
	      preparedStmt.setString(7,rest.getRestaurantName());
	      preparedStmt.setInt(8,rest.getId());
	     
	      // execute the java preparedstatement
	      preparedStmt.executeUpdate();
	      
	
		
	}
	
	public void deleteRest(String username) throws NoSuchAlgorithmException, SQLException
	{  	    
		   
		
		
		 PreparedStatement	  preparedStatement = connection.
				  prepareStatement("DELETE FROM restaurant WHERE ownerName=?");
		  preparedStatement.setString(1, username);
		 preparedStatement.executeUpdate();
		    preparedStatement.close();
		
	}
	public void deleteRest(int id) throws NoSuchAlgorithmException, SQLException
	{  	    
		   
		
		
		 PreparedStatement	  preparedStatement = connection.
				  prepareStatement("DELETE FROM restaurant WHERE idrestaurant=?");
		  preparedStatement.setInt(1, id);
		 preparedStatement.executeUpdate();
		    preparedStatement.close();
		
	}

}













