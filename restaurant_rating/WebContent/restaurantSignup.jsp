 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>

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




<header style="background-color:blue;">
<nav class="navbar navbar-expand-lg  navbar-dark  bg-secondary border-bottom box-shadow mb-3 ">
<div class="container-fluid ">
<a class="navbar-brand col-11" href="">
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
                            <a href="" class="nav-link  col-1" >Guest</a>
                        </li> 
                        
                      
                    </ul>
                </div>
            
</div>
</nav>
</header>
<section class="container-fluid">
<section class="row justify-content-center">
<form action="RestaurantController" method="post" class="myform" enctype="multipart/form-data">


<h1>Sign up</h1>
  <div class="form-group " >
  <%
        String status=request.getParameter("status");
        %>
        <%if(status!=null){ %>
        	 <% if(status.equals("new")){ %>
        	 <input type="hidden" name="user" value="owner">
        	 <%}} %>
    <input type="hidden" name="action" value="insertRestaurant">
    <input type="hidden" name="change" value="false" id="change">
<img src="rr.png" name="myProfil" id="profil" width="200" height="200" alt="notFound" class="img-fluid" >
     
  </div>
 
 <div class="form-group" >
    <label for="restName">Όνομα Εστιατορίου</label>
    <input type="text" class="form-control" name="restName" id="restName" required>
  </div>
  <div class="form-group" >
    <label for="type">Κουζίνα</label>
      <select id="type" name="type" class="form-control" required>
      <c:forEach items="${sessionScope.category}" var="s">
        <option value="<c:out value="${s.getCategory()}" />" ><c:out value="${s.getCategory()}" /></option>
              </c:forEach>      
      </select>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
       <label for="inputAddress">Address</label>
    <input type="text" class="form-control" id="inputAddress" name="adress" placeholder="1234 Main St">
    </div>
    <div class="form-group col-md-4">
      <label for="inputState">State</label>
      <select id="inputState" name="city" class="form-control">
        <option value="Αθήνα" selected>Αθήνα</option>
        <option  value="Πάτρα">Πάτρα</option>
           <option  value="Ηράκλειο">Ηράκλειο</option>
              <option  value="Θεσσαλονίκη">Θεσσαλονίκη</option>
                 <option  value="Βόλος">Βόλος</option>
                  <option  value="Χαλκίδα">Χαλκίδα</option>
                   
      </select>
    </div>
   
  </div>
  <div class="form-group">
    <label for="exampleFormControlTextarea1">Πληροφορίες</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" name="infos" rows="3"></textarea>
  </div>
  
  <div class="form-group" >
    <label for="file">Photo profil</label>
    <input type="file" class="form-control" name="file" id="file" />
  </div>
<br>
<div class="col text-center">
<div class="row">

<a class="col btn btn-primary text-white" href="signup.jsp">Back</a>
  <button type="submit" id="finish" class="col btn btn-primary">Finish</button>
 
  </div>
</div>
 
        <%if(status!=null){ %>
        	 <% if(status.equals("error")){ %>
        	  <br>  <div class="alert alert-danger" role="alert">Error!Try again</div>
        	    <%}else if(status.equals("name")){%>
        	     <br>  <div class="alert alert-danger" role="alert">This name already exists</div>
        	     	 <input type="hidden" name="user" value="owner">
        	     <%}} %>
</form>
</section>
</section>
<%} %>

<script>
window.addEventListener('load', function() {
	  document.querySelector('input[type="file"]').addEventListener('change', function() {
	      if (this.files && this.files[0]) {
	          var img =  document.getElementById("profil");  // $('img')[0]
	          img.src = URL.createObjectURL(this.files[0]); // set src to blob url
	          var input=document.getElementById("change");
		         input.value="true";
	          
	      
	   
	         

	          
	       
	      }
	  });
	});
	</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>