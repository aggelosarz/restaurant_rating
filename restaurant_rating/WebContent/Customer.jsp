<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RRatting</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="myStyle.css">
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires","0");
    if(session.getAttribute("user")==null) {
        response.sendRedirect("Index.jsp");
    }else {
%>
<body class="bg-light">
  <header style="background-color:blue;">
<nav class="navbar navbar-expand-lg  navbar-dark  bg-secondary border-bottom box-shadow mb-3 ">
<div class="container-fluid ">
<a class="navbar-brand col-10" href="#">
    <img src="rr.png" 
    width="40" height="40" class="d-inline-block align-top" alt="">
    RRating
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
<div class="navbar-collapse collapse " id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a href="CustController?action=delete" class="nav-link  col-1" >Διαγραφή λογαριασμού</a>
                        </li> 
                        <li class="nav-item">
                            <a href="Logout" class="nav-link  col-1" >Logout</a>
                        </li> 
                      
                    </ul>
                </div>
            
</div>
</nav>
</header>
  
    
    
    <div class="container-md shadow-sm p-3 mb-5 bg-white rounded">

      <form action="RestaurantController" method="get">
	  <input type="hidden" name="action" value="search">
        <div class="row">
          <div class="col">
            <p class="h3 text-center">Welcome <c:out value='${sessionScope.user.getUsername()}'/>!!</p>
          </div>
        </div>
		
	<p class="h4 text-center">Ψάξε κάτι συγκεκριμένο </p>
     <div class="row">
	    <div class="col-3">
        <input type="text" name="restName" class="form-control" placeholder="Εστιατόριο">
        </div>
	   
		<input type="hidden"  name="user" value="cust">
		<div class="col-3">
         <select id="inputState" name="city" class="form-control">
        <option value="Αθήνα" selected>Αθήνα</option>
        <option  value="Πάτρα">Πάτρα</option>
           <option  value="Ηράκλειο">Ηράκλειο</option>
              <option  value="Θεσσαλονίκη">Θεσσαλονίκη</option>
                 <option  value="Βόλος">Βόλος</option>
                  <option  value="Χαλκίδα">Χαλκίδα</option>
                   
      </select>
        </div>
		<div class="col-3">
         <select id="type" name="type" class="form-control" required>
      <c:forEach items="${sessionScope.category}" var="s">
        <option value="<c:out value="${s.getCategory()}" />" ><c:out value="${s.getCategory()}" /></option>
              </c:forEach>      
      </select>
		
		  </div>
 
	 <div class="col">
             <button type="submit" class="btn btn-danger wrn-btn">Αναζήτηση</button>
      </div>
          </div>
          <%
        String status=request.getParameter("status");
        %>
        <%if(status!=null){ %>
        	 <% if(status.equals("exist")){ %>
        	   <br>    <div class="alert alert-danger" role="alert">There is not such restaurant!</div>

  <% }else if (status.equals("error")){ %>
   <br>  <div class="alert alert-danger" role="alert">There was an error!Try again.</div>
  
  
  <%}} %>
	  </form>
	 
	  </div>
	  <div class="container-md shadow-sm p-3 mb-5 bg-white rounded">

      <form action="RestaurantController" method="get">
      <input type="hidden" name="action" value="getRests">
      <p class="h4 text-center">Ψάξε με βάση την πόλη και την κουζίνα </p>
      <div class="row">
     
      <input type="hidden"  name="user" value="cust">
		<div class="col-3">
         <select id="inputState" name="city" class="form-control">
        <option value="Αθήνα" selected>Αθήνα</option>
        <option  value="Πάτρα">Πάτρα</option>
           <option  value="Ηράκλειο">Ηράκλειο</option>
              <option  value="Θεσσαλονίκη">Θεσσαλονίκη</option>
                 <option  value="Βόλος">Βόλος</option>
                  <option  value="Χαλκίδα">Χαλκίδα</option>
                   
      </select>
        </div>
		<div class="col-3">
         
            <select id="type" name="type" class="form-control" >
      <c:forEach items="${sessionScope.category}" var="s">
        <option value="<c:out value="${s.getCategory()}" />" ><c:out value="${s.getCategory()}" /></option>
              </c:forEach>      
      </select>
		     
          
           </div>
           <div class="col">
             <button type="submit" class="btn btn-danger wrn-btn">Αναζήτηση</button>
      </div>
       </div>
	  </form>
	  
	  <%
	  if(status!=null){ %>
	  <% if(status.equals("true")){ %>
 	
 	 <table class="table">
  
    <thead>
    
      <tr>
        <th>'Ονομα Εστιατόριου</th>
        <th>Κουζίνα</th>
        <th>Πόλη</th>
        <th>Διεύθυνση</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach items="${requestScope.Rest}" var="s">
      <tr>
        <td><a href="RestaurantController?action=search&user=cust&restName=<c:out value='${s.getRestaurantName()}'/>&city=<c:out value='${s.getCity()}'/>&type=<c:out value='${s.getType()}'/>"><c:out value='${s.getRestaurantName()}'/></a></td>
        <td><c:out value='${s.getType()}'/></td>
        <td><c:out value='${s.getCity()}'/></td>
        <td><c:out value='${s.getAdress()}'/></td>
      </tr>
     
       </c:forEach>
    </tbody>
  </table>


<%}} %>
	  
	  
	  
	  </div>
	  
	  
	 <p class="h3 text-center">Top Εστιατόρια</p>
	<div class="container">
	<div class="row"> 
	<p class="h5 text-center col ">Aνα Κουζίνα</p>
	<p class="h5 text-center col ">Aνα Πόλη</p>
	</div>
    <div class="row justify-content-between">          
  <table class="table col-5">
 
    <thead>
      <tr>
        <th>Κουζίνα</th>
        <th>Εστατόριο</th>
        
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.bestRestInCategory}" var="s">
      <tr>
        <td><c:out value='${s.getType()}'/></td>
        <td><a href="RestaurantController?action=search&user=cust&restName=<c:out value='${s.getRestaurantName()}'/>&city=<c:out value='${s.getCity()}'/>&type=<c:out value='${s.getType()}'/>""><c:out value='${s.getRestaurantName()}'/></a></td>
        
      </tr>
      
      </c:forEach>
    </tbody>
  </table>
  
  
  <table class="table col-5">

    <thead>
      <tr>
        <th>Πόλη</th>
        <th>Εστιατόριο</th>
        
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.bestRestInCity}" var="s">
      <tr>
        <td><c:out value='${s.getCity()}'/></td>
        <td><a href="RestaurantController?action=search&user=cust&restName=<c:out value='${s.getRestaurantName()}'/>&city=<c:out value='${s.getCity()}'/>&type=<c:out value='${s.getType()}'/>"><c:out value='${s.getRestaurantName()}'/></a></td>
        
      </tr>
    
       </c:forEach>
    </tbody>
  </table>
  
  
</div>
  
  
  
   <p class="h5 text-center">Top 10 </p>   
      
           
  <table class="table">
  
    <thead>
    
      <tr>
        <th>'Ονομα Εστιατόριου</th>
        <th>Κουζίνα</th>
        <th>Περιοχή</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach items="${sessionScope.best10Rest}" var="s">
      <tr>
        <td><a href="RestaurantController?action=search&user=cust&restName=<c:out value='${s.getRestaurantName()}'/>&city=<c:out value='${s.getCity()}'/>&type=<c:out value='${s.getType()}'/>"><c:out value='${s.getRestaurantName()}'/></a></td>
        <td><c:out value='${s.getType()}'/></td>
        <td><c:out value='${s.getCity()}'/></td>
      </tr>
     
       </c:forEach>
    </tbody>
  </table>
</div>




	  
    
	 <%
        
    }
    %>	
      
          



<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>