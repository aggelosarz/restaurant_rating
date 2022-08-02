package controllers;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mysql.jdbc.Constants;

import dao.CategoryDao;
import dao.RestaurantDao;
import dao.UserDao;
import models.Restaurant;
import models.User;



/**
 * Servlet implementation class OwnerController
 */
@WebServlet("/OwnerController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
public class OwnerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private  UserDao userDao;
       private RestaurantDao restDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerController() {
        super();
        userDao=new UserDao();
        restDao=new RestaurantDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action=request.getParameter("action");
		HttpSession session = request.getSession();
		if(action.equals("newRest")) {
			CategoryDao catDao=new CategoryDao();
			
			  
				try {
					session.setAttribute("category", catDao.getAllRestCategory());
					response.sendRedirect("restaurantSignup.jsp?status=new");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					response.sendRedirect("restProfil.jsp");
					e.printStackTrace();
				}
				
				 
		}
		else if(action.equals("editRest")) {
			CategoryDao catDao=new CategoryDao();
			
			  
				try {
					session.setAttribute("category", catDao.getAllRestCategory());
					response.sendRedirect("editRest.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 
		}
		else if(action.contentEquals("delete")) {
			 User user=(User) session.getAttribute("user");
			 String username=user.getUsername();
			 try {
				userDao.deleteUser(username);
				restDao.deleteRest(username);
				response.sendRedirect("Index.jsp");
			} catch (NoSuchAlgorithmException | SQLException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("ownerProfil.jsp");
				e.printStackTrace();
			}
		 }
		
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		//doGet(request, response);
	
		String action=request.getParameter("action");
		HttpSession session = request.getSession();
		if(action.equals("signin")) {
			
		
			String email=request.getParameter("myEmail");
			String pass=request.getParameter("myPass");

			try {
				User user=new User();
				user=userDao.validateUser(email,pass, "Owner");
				if(user!=null) {
					session.setAttribute("user", user);
					
					List<Restaurant>rest= restDao.getRestaurantbyOwnername(user.getUsername());
					if(rest==null) {
						CategoryDao catDao=new CategoryDao();
						response.sendRedirect("restaurantSignup.jsp");
						 
							session.setAttribute("category", catDao.getAllRestCategory());
							
							 
					}
				
					session.setAttribute("Rests", rest);
					response.sendRedirect("ownerProfil.jsp");
					
				}
				else {
					response.sendRedirect("Index.jsp?status=false");
				}
			} catch (NoSuchAlgorithmException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("Index.jsp?status=error");
			}
		}
		else if(action.equals("signup")) {
			
			String email=request.getParameter("myEmail");
			String pass=request.getParameter("myPass");
		
			String username=request.getParameter("myUsername");
			try {
				User user=new User();
				user.setEmail(email);
				user.setType("Owner");
				user.setPassword(pass);
				user.setUsername(username);
				if(userDao.getUserbyUsername(username)==null) {
					if(userDao.getUserbyEmail(email)==null) {
					userDao.addUser(user);
					session.setAttribute("user", user);
					CategoryDao catDao=new CategoryDao();
				
					  
					  session.setAttribute("category", catDao.getAllRestCategory());
						
						response.sendRedirect("restaurantSignup.jsp");
				
					}
					else
						response.sendRedirect("signup.jsp?status=email");
				}
				
				else {
					
					response.sendRedirect("signup.jsp?status=username");
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("signup.jsp?status=error");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("signup.jsp?status=error");
			}
			
		}
		
	}

	
	
	
	
}





