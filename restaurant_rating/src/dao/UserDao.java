package dao;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.acl.Owner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import models.User;


public  class UserDao {
	
private Connection connection;
	
    public UserDao() {
        connection = DbUtil.getConnection();
    }

	
	private String getHashMD5(String unhashed,String salt) throws NoSuchAlgorithmException {
        // Hash the password.
	  final String toHash = salt + unhashed + salt;
	 
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            return "00000000000000000000000000000000";
        }
        messageDigest.update(toHash.getBytes(), 0, toHash.length());
        String hashed = new BigInteger(1, messageDigest.digest()).toString(16);
        if (hashed.length() < 32) {
            hashed = "0" + hashed;
        }
        return hashed.toUpperCase();
    }
	
	public User getUserbyUsername(String Username) throws SQLException, NoSuchAlgorithmException  {
		// TODO Auto-generated method stub
		User user=new User();
		String query="SELECT * FROM user where username=\""+Username+"\" ;";
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			user.setUsername(rs.getString("username"));
		user.setEmail(rs.getString("Email"));
			user.setPassword(rs.getString("hash"));
			user.setType(rs.getString("type"));
			
			return user;
		}
	
		return null;
	}
    
	public User getUserbyEmail(String Email) throws SQLException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		User user=new User();
		String query="SELECT * FROM user where Email=\""+Email+"\" ;";
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			user.setPassword(rs.getString("hash"));
		user.setId(rs.getInt("iduser"));
			user.setEmail(rs.getString("Email"));
			user.setType(rs.getString("type"));
			
			return user;
		}
		
		return null;
		
	}
	
	public User getUserbyId(int id) throws SQLException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		User user=new User();
		String query="SELECT * FROM user where iduser=\""+id+"\" ;";
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
			user.setPassword(rs.getString("hash"));
		user.setId(rs.getInt("iduser"));
			user.setEmail(rs.getString("Email"));
			user.setType(rs.getString("type"));
			
			return user;
		}
		return null;
		
	}
	public void addUser(User user) throws NoSuchAlgorithmException, SQLException    {
		// TODO Auto-generated method stub
		
	
			
		SecureRandom random = new SecureRandom();
		byte bytes[]= new byte[20];
		 random.nextBytes(bytes);
			 String salt=random.toString();
			 String hash;
			
				hash = getHashMD5(user.getPassword(),salt);
			
	PreparedStatement preparedStatement = connection.
			prepareStatement("INSERT INTO user (Email, hash, salt,type,username) VALUES (?,?,?,?,?)");
	// Parameters start with 1

	preparedStatement.setString(1, user.getEmail());
	preparedStatement.setString(2, hash);
	preparedStatement.setString(3, salt);
	preparedStatement.setString(4,user.getType());
	preparedStatement.setString(5,user.getUsername());
	preparedStatement.executeUpdate();

	
		
		

	}
		
	
	public User validateUser(String email,String pass,String type) throws SQLException, NoSuchAlgorithmException  {
User user=new User();
		String query="SELECT * FROM user where Email=\""+email+"\" AND type=\""+type+"\";";
		
		

		
		Statement st;
		
			st = connection.createStatement();
		
		ResultSet rs= st.executeQuery(query);
		while(rs.next()) {
		String salt=rs.getString("salt");
		String hash=getHashMD5(pass,salt);
		if(hash.equals(rs.getString("hash"))) {
			user.setUsername(rs.getString("username"));
			user.setType(rs.getString("type"));
			//user.setPassword(rs.getString("hash"));
			int id=rs.getInt("iduser");
			user.setId(id);
			
			return user;
		
		}
		
		}
	
		return null;
			
		}
	public void deleteUser(String username) throws NoSuchAlgorithmException, SQLException
	{  	    
		   
		
		
	  
	 
		
		    PreparedStatement	  preparedStatement1 = connection.
		 			  prepareStatement("DELETE FROM user WHERE username=?");
		 	  preparedStatement1.setString(1, username);
		 	 preparedStatement1.executeUpdate();
			    preparedStatement1.close();
			    
			
		
			
	}
	
	
	 public List<User> getAllUsersByType(String type) throws SQLException {
	        List<User> Users = new ArrayList<User>();
	     
	            Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery("select * from user where type=\""+type+"\";");
	            while (rs.next()) {
	                User user = new User();
	                user.setEmail(rs.getString("Email"));
	            	user.setUsername(rs.getString("username"));
	    			user.setType(rs.getString("type"));
	    			//user.setPassword(rs.getString("hash"));
	    			user.setId(rs.getInt("iduser"));
	    			System.out.println(user.getEmail());
	    			Users.add(user);
	            }
	      

	        return Users;
	    }
	
}





