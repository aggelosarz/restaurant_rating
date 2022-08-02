package dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Rating;


public class RatingDao {
	private Connection connection;

    public RatingDao() {
        connection = DbUtil.getConnection();
      
    }
    
    
   public void addRating(Rating rating) throws SQLException {
	 
          
   		PreparedStatement	  preparedStatement = connection.
   				  prepareStatement("INSERT INTO rating (username,restName,grade,idrestaurant) VALUES (?,?,?,?);");
       	  preparedStatement.setString(1, rating.getUsername());
       	  preparedStatement.setString(2, rating.getRestName());
       	  preparedStatement.setInt(3,rating.getGrade());
       	 preparedStatement.setInt(4,rating.getIdRestaurant());
        	 
       	    preparedStatement.executeUpdate();
       	    preparedStatement.close();
       	 

	   
   }
   
   public Rating getRating(String username,String restName) throws SQLException, NoSuchAlgorithmException  {
		// TODO Auto-generated method stub
	   Rating rating=new Rating();
		String query="SELECT * FROM rating where username=\""+username+"\" AND restName=\""+restName+"\" ;";
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			System.out.println("sdaasdasdas");
			rating.setUsername(username);
			rating.setGrade(rs.getInt("grade"));
			rating.setRestName(restName);
			return rating;
		}
	
		return null;
	}
   public void update(Rating rating) throws SQLException {
	   String query = "update rating set grade= ? where username=? AND restName=?";
	      PreparedStatement preparedStmt = connection.prepareStatement(query);
	      preparedStmt.setInt  (1, rating.getGrade());
	      preparedStmt.setString(2, rating.getUsername());
	      preparedStmt.setString(3, rating.getRestName());
	      // execute the java preparedstatement
	      preparedStmt.executeUpdate();
   }
    
}
