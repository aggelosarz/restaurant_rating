package controllers;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import dao.CategoryDao;
import dao.RestaurantDao;
import dao.UserDao;
import models.Restaurant;
import models.User;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserDao userDao;
       private CategoryDao catergoryDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        userDao=new UserDao();
        catergoryDao=new CategoryDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action=request.getParameter("action");
		HttpSession session = request.getSession();
		
		 if(action.equals("getUsers") ) 
		{   
			String type=request.getParameter("type");
			System.out.println(type);
			 
			  try {
				  
				  RequestDispatcher view = request.getRequestDispatcher("deleteUser.jsp");
				  
				request.setAttribute("User", userDao.getAllUsersByType(type));
				 view.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("AdminProfile.jsp");
				e.printStackTrace();
			}
		       
		}
		else if(action.equals("delete") ) 
		{   
			
			String type=request.getParameter("type");     
			String[] name=request.getParameterValues("username");
			System.out.print(type);
			try {
				if(name.length>0) {
				for(int i=0;i<name.length;i++) {
			    userDao.deleteUser(name[i]);
			    if(type.contentEquals("Owner")) {
				RestaurantDao restDao=new RestaurantDao();
				 
				//File file = new File(rest.getPath()); 
		          /*
		        if(file.delete()) 
		        { 
		            System.out.println("File deleted successfully"); 
		        } 
		        else
		        { 
		            System.out.println("Failed to delete the file"); 
		        } 
		        */
				restDao.deleteRest(name[i]);
				}
			}
				System.out.print("succesfully!!");
				 RequestDispatcher view = request.getRequestDispatcher("deleteUser.jsp");
				  
					request.setAttribute("User", userDao.getAllUsersByType(type));
					 view.forward(request, response);
			}
				else   response.sendRedirect("deleteUser.jsp");
			
			}
			
			catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 response.sendRedirect("deleteUser.jsp?status=error");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
				 response.sendRedirect("deleteUser.jsp?status=error");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String action=request.getParameter("action");
		HttpSession session = request.getSession();
		if(action.equals("insertCategory")) {
			String category=request.getParameter("category");
			try {
				
				if(catergoryDao.getCategorybyName(category)==null) {
				
					catergoryDao.insertCategory(category);
					
					response.sendRedirect("insertCategory.jsp");
				    
				}
				
				else 
				{
					response.sendRedirect("insertCategory.jsp?status=false");
				}
			}
				
					catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("insertCategory.jsp?status=error");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("insertCatergory.jsp?status=error");
			}
		}
		else if(action.equals("signin")) {
			
			String email=request.getParameter("myEmail");
			String pass=request.getParameter("myPass");
			try {
				User user=new User();
				user=userDao.validateUser(email,pass, "Admin");
				if(user!=null) {
					session.setAttribute("user", user);
					response.sendRedirect("AdminProfile.jsp");
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
				user.setType("Admin");
				user.setPassword(pass);
				user.setUsername(username);
				if(userDao.getUserbyUsername(username)==null) {
					if(userDao.getUserbyEmail(email)==null) {
					userDao.addUser(user);
				response.sendRedirect("Index.jsp");
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
