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
import dao.RestaurantDao;
import dao.UserDao;
import models.User;

/**
 * Servlet implementation class GuestController
 */
@WebServlet("/GuestController")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestController() {
        super();
        
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
if(action.equals("signin")) {
			
		
			
				
				 
					RestaurantDao restDao=new RestaurantDao();
					CategoryDao catDao=new CategoryDao();
					try {
						session.setAttribute("bestRestInCategory", restDao.getBestRestInEveryCategory());
						session.setAttribute("bestRestInCity", restDao.getBestRestInEveryCity());
						session.setAttribute("best10Rest", restDao.getBest10Rest());
						session.setAttribute("category", catDao.getAllRestCategory());
						response.sendRedirect("Guest.jsp");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
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
	}

}
