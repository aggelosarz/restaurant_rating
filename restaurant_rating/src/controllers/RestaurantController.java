package controllers;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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

import dao.RestaurantDao;
import models.Restaurant;
import models.User;

/**
 * Servlet implementation class RestaurantController
 */
@WebServlet("/RestaurantController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
public class RestaurantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private RestaurantDao restDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantController() {
        super();
        restDao=new RestaurantDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action =request.getParameter("action");
		HttpSession session = request.getSession();
		if(action.equals("search")) {
			
			String name=request.getParameter("restName");
			String city=request.getParameter("city");
			String type=request.getParameter("type");
			String user =request.getParameter("user");
			try {
				Restaurant rest=restDao.getRestaurantbyNameCityType(name, type, city);
				if(rest!=null) {
					session.setAttribute("rest", rest);
					
					if(user.equals("owner")) {
						response.sendRedirect("restProfil.jsp?status=owner");
					}
					
					else if( user.equals("cust")){
					response.sendRedirect("restProfil.jsp?status=customer");
					}
					else 
						response.sendRedirect("restProfil.jsp?status=guest");
				}
				else {
					if(user.equals("cust"))
					    response.sendRedirect("Customer.jsp?status=exist");
					else if(user.equals("guest"))
						response.sendRedirect("Guest.jsp?status=exist");
				}
			} catch (NoSuchAlgorithmException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("Customer.jsp?status=error");
			}
			
			
		}
		else if(action.equals("getRests"))
		{
			String city=request.getParameter("city");
		String type=request.getParameter("type");
		String user =request.getParameter("user");
		
		try {
			List<Restaurant> rest = restDao.getRestaurantbyCityType( city,type);
			if(rest!=null) {
				if(user.equals("cust")) {
				RequestDispatcher view = request.getRequestDispatcher("Customer.jsp?status=true");
				request.setAttribute("Rest", rest);
				view.forward(request, response);
				}
				else
				{
					RequestDispatcher view = request.getRequestDispatcher("Guest.jsp?status=true");
					request.setAttribute("Rest", rest);
					view.forward(request, response);
				}
					
			}
			else
				if(user.equals("cust"))
					response.sendRedirect("Customer.jsp?status=exist");
				else
					response.sendRedirect("Guest.jsp?status=exist");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("Customer.jsp?status=error");
		}
		
		}
		else if(action.equals("deleteRest"))
		{
			int id= Integer.parseInt(request.getParameter("id"));
			try {
				restDao.deleteRest(id);
				User user=(User) session.getAttribute("user");
				List<Restaurant>rest= restDao.getRestaurantbyOwnername(user.getUsername());
				session.setAttribute("Rests", rest);
				response.sendRedirect("ownerProfil.jsp");
			} catch (NoSuchAlgorithmException | SQLException e) {
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
		response.setContentType("text/html;charset=UTF-8");
		String action =request.getParameter("action");
		HttpSession session = request.getSession();
		if(action.equals("insertRestaurant")){
			User user=  (User) session.getAttribute("user");
			Restaurant rest=new Restaurant();
			
			String name=request.getParameter("restName");
			String type=request.getParameter("type");
			String city=request.getParameter("city");
			String adress=request.getParameter("adress");
			String description=request.getParameter("infos");
			String change=request.getParameter("change");
			Part part;
			String filename;
			String savepath;
			if(change.contentEquals("true")) {
				 part=request.getPart("file");
				 filename=getFileName(part);
				 //savepath="C:\\Users\\DimitrisPC\\Desktop\\Ergasia TexLog\\restaurant_rating\\WebContent\\images"+File.separator+filename;
				 savepath="C:\\Users\\user\\eclipse-workspace\\restaurant_rating\\WebContent\\images"+File.separator+filename;
				 
				 try{
				part.write(savepath+File.separator);
				 }
				 catch(IOException ex) {
					 
				 }
			}
			else {
				filename="rr.png";
				 //savepath="C:\\Users\\DimitrisPC\\Desktop\\Ergasia TexLog\\restaurant_rating\\WebContent\\images"+File.separator+filename;
				savepath="C:\\Users\\user\\eclipse-workspace\\restaurant_rating\\WebContent\\images"+File.separator+filename;
				File fileSaveDir=new File(savepath);
				 try{
					 fileSaveDir.createNewFile();
						 }
						 catch(IOException ex) {
							 
						 }
				
			}
				rest.setFilename(filename);
				rest.setPath(savepath);
				rest.setRestaurantName(name);
				rest.setType(type);
				rest.setCity(city);
				rest.setAdress(adress);
				rest.setDescription(description);
				rest.setOwnerName(user.getUsername());
				//session.setAttribute("rest", rest);
				try {
					if(restDao.getRestaurantbyCityandAdress(rest.getCity(), rest.getAdress())==null ) {
					
						
						restDao.addRestaurant(rest);
						String owner =request.getParameter("user");
						try {
						if(owner.equals("owner")) {
							List<Restaurant>restaurants= restDao.getRestaurantbyOwnername(user.getUsername());
							session.setAttribute("Rests", restaurants);
							response.sendRedirect("ownerProfil.jsp");
						}
						else
						response.sendRedirect("Index.jsp");
						}
						catch(java.lang.NullPointerException exception) {
							response.sendRedirect("Index.jsp");
						}
					}
					else {
						response.sendRedirect("restaurantSignup.jsp?status=name");
					}
				} catch (NoSuchAlgorithmException | SQLException e) {
					// TODO Auto-generated catch block
					response.sendRedirect("restaurantSignup.jsp?status=error");
					e.printStackTrace();
				}
			}
		else if(action.equals("editRestaurant")) {
			User user=  (User) session.getAttribute("user");
			Restaurant rest=new Restaurant();
			
			String name=request.getParameter("restName");
			String type=request.getParameter("type");
			String city=request.getParameter("city");
			String adress=request.getParameter("adress");
			String description=request.getParameter("infos");
			String change=request.getParameter("change");
			String filename;
			String savepath;
			if(change.equals("true")) {
			Part part=request.getPart("file");
			 filename=getFileName(part);
			 //savepath="C:\\Users\\DimitrisPC\\Desktop\\Ergasia TexLog\\restaurant_rating\\WebContent\\images"+File.separator+filename;
			savepath="C:\\Users\\user\\eclipse-workspace\\restaurant_rating\\WebContent\\images"+File.separator+filename;
			
			 try{
			part.write(savepath+File.separator);
			 }
			 catch(IOException ex) {
				 
			 }
			
			}
			else {
				filename=(((Restaurant)session.getAttribute("rest")).getFilename());
				//savepath="C:\\Users\\DimitrisPC\\Desktop\\Ergasia TexLog\\restaurant_rating\\WebContent\\images"+File.separator+filename;
				savepath="C:\\Users\\user\\eclipse-workspace\\restaurant_rating\\WebContent\\images"+File.separator+filename;
				
			}
			
		
				
			
			
				
			rest.setPath(savepath);
			rest.setFilename(filename);
				rest.setRestaurantName(name);
				rest.setType(type);
				rest.setCity(((Restaurant) session.getAttribute("rest")).getCity());
				
				rest.setAdress(((Restaurant) session.getAttribute("rest")).getAdress());
				
				rest.setDescription(description);
				rest.setOwnerName(user.getUsername());
				rest.setId(((Restaurant) session.getAttribute("rest")).getId());
				System.out.println(rest.getId());
				try {
					
					if( restDao.getRestaurantbyCityandAdress(city, adress)==null || (rest.getCity().equals(city) && rest.getAdress().contentEquals(adress)))
					{
						rest.setCity(city);
						rest.setAdress(adress);
						File fileSaveDir=new File(savepath);
						restDao.update(rest);
						session.setAttribute("rest", rest);
						response.sendRedirect("restProfil.jsp?status=owner");
					}
					
					else {
						response.sendRedirect("editRest.jsp?status=name");
					}
				} catch (SQLException | NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("editRest.jsp?status=error");
				}
		}
		
		
	}
	private String getFileName(Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename"))
	            return content.substring(content.indexOf("=") + 2, content.length() - 1);
	        }
	    return "";
	}
}
