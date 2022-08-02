package dao;



import java.awt.Component;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import models.RestCategory;
import models.User;





public class CategoryDao {
	private Connection connection;
	 public CategoryDao() {
	        connection = DbUtil.getConnection();
	       
	    }


public boolean insertCategory(String name)throws NoSuchAlgorithmException, SQLException {
	
  	 PreparedStatement	  preparedStatement = connection.
  			  prepareStatement("INSERT INTO category (name) VALUES (?)");
  	  preparedStatement.setString(1, name);
  	
  	  
  	    preparedStatement.executeUpdate();
  	    preparedStatement.close();
  	    
  	    return true;
  	    
  	 
   }   

public void deleteCategory(String name) throws NoSuchAlgorithmException, SQLException
{  	    
	   
	
	
  
 
	
	    PreparedStatement	  preparedStatement1 = connection.
	 			  prepareStatement("DELETE FROM category WHERE name=?");
	 	  preparedStatement1.setString(1, name);
	 	 preparedStatement1.executeUpdate();
		    preparedStatement1.close();
		    
		
	
		
}



public RestCategory getCategorybyName(String name)throws NoSuchAlgorithmException, SQLException {
	RestCategory category=new RestCategory();
	
	   	 String query="SELECT * from category WHERE name = '" + name + "'";;
	    	Statement st;

	    		st = connection.createStatement();
	    	
	    	ResultSet rs= st.executeQuery(query);
	    	
	    	while(rs.next()) {
	    		category.setCategory(rs.getString("name"));
	    		category.setId(rs.getInt("idcategory"));
	    		st.close();
	    		return category;
	    	}
	    	 
	    	
	    	return null;
	    	
	    
	   	
	   	
	   }

public List<RestCategory> getAllRestCategory() throws SQLException {
    List<RestCategory> category = new ArrayList<RestCategory>();
 
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from category ;");
        while (rs.next()) {
        	RestCategory cat=new RestCategory();
        	cat.setCategory(rs.getString("name"));
        	cat.setId(rs.getInt("idcategory"));
			
        	category.add(cat);
        }
  

    return category;
}


	
	
	
}






