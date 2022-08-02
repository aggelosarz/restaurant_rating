package controllers;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategoryDao;
import dao.RatingDao;
import dao.RestaurantDao;
import dao.UserDao;
import models.Rating;
import models.Restaurant;
import models.User;

/**
 * Servlet implementation class CustController
 */
@WebServlet("/CustController")
public class CustController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserDao userDao;
       private RatingDao ratingDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustController() {
        super();
        // TODO Auto-generated constructor stub
        userDao=new UserDao();
        ratingDao=new RatingDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action =request.getParameter("action");
		HttpSession session = request.getSession();
		 if(action.equals("rate")) {
			String rate=request.getParameter("star");
			Restaurant rest=(Restaurant) session.getAttribute("rest");
			User user=(User) session.getAttribute("user");
			System.out.println(user.getUsername());
			try {
				Rating rating=ratingDao.getRating(user.getUsername(),rest.getRestaurantName());
				if(rating==null) {
					rating=new Rating();
					rating.setUsername(user.getUsername());
					System.out.println(rating.getUsername());
					rating.setGrade(Integer.parseInt(rate));
					rating.setRestName(rest.getRestaurantName());
					rating.setIdRestaurant(rest.getId());
					ratingDao.addRating(rating);
				}
				else {
					rating.setGrade(Integer.parseInt(rate));
					ratingDao.update(rating);
				}
				//session.setAttribute("rate", rating.getGrade());
				RestaurantDao restDao=new RestaurantDao();
				rest=restDao.getRestaurantbyId(rest.getId());
				session.setAttribute("rest", rest);
				response.sendRedirect("restProfil.jsp?status=customer");
			} catch (NoSuchAlgorithmException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 else if(action.contentEquals("delete")) {
			 User user=(User) session.getAttribute("user");
			 try {
				userDao.deleteUser(user.getUsername());
				response.sendRedirect("Index.jsp");
			} catch (NoSuchAlgorithmException | SQLException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("Customer.jsp");
				e.printStackTrace();
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
		
		if(action.equals("signin")) {
			
			String email=request.getParameter("myEmail");
			String pass=request.getParameter("myPass");
			try {
				User user=new User();
				user=userDao.validateUser(email,pass, "Cust");
				if(user!=null) {
					RestaurantDao restDao=new RestaurantDao();
					CategoryDao catDao=new CategoryDao();
					session.setAttribute("bestRestInCategory", restDao.getBestRestInEveryCategory());
					session.setAttribute("bestRestInCity", restDao.getBestRestInEveryCity());
					session.setAttribute("best10Rest", restDao.getBest10Rest());
					session.setAttribute("category", catDao.getAllRestCategory());
					session.setAttribute("user", user);
					response.sendRedirect("Customer.jsp");
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
				user.setType("Cust");
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
