 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<form   name="form1" action="AdminController" method="post" class="myform" >
<h1>Category</h1>

<label>Category</label>
<input type="text" class="form-control" id="category"  name="category" required placeholder="Category">
<small id="categoryhelp" class="form-text text-muted">
 The category must include only letters!
</small>
  <%
       
 
        String status=request.getParameter("status");
        %>
        <%if(status!=null){ %>
        	 <% if(status.equals("false")){ %>
        	    <div class="alert alert-danger" role="alert">Category Already exist</div>

  <% }else if (status.equals("error")){ %>
  <div class="alert alert-danger" role="alert">There was an error!Try again.</div>
  
   <% }} %>
<button type="submit" name="action" value="insertCategory" id="insertCategory" class="btn btn-outline-primary">Add</button>
 <a href="AdminProfile.jsp" class="btn btn-outline-dark">Back</a>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>