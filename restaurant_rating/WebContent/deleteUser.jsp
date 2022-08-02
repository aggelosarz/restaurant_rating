
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <%@ page import="java.sql.ResultSet" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
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




  <form   name="form1" action="AdminController" method="post" class="myform" >
<h1>Delete a User</h1>
<table border="4" class="table table-striped">
 <thead class="thead-dark" >
   <tr>
        <th>Email</th>
         <th>Username</th>
        <th>Type</th>
        <th>choose</th>
     
        
 
   </tr>
   </thead>
  <tbody>
         <c:forEach items="${requestScope.User}" var="s">
            
                <tr>
           <td><c:out value="${s.getEmail()}" /></td>
           <td><c:out value="${s.getUsername()}" /></td>
           <td name="type" ><c:out value="${s.getType()}" /></td>
           <td><input type="checkbox"  name="username" id="username" value="<c:out value="${s.getUsername()}" />"></td>
            </tr>
        <input type="hidden" name="type"  value="<c:out value="${s.getType()}" />">
  </c:forEach>
   </tbody>
   </table>
        
   <button type="submit" name="action" value="delete" id="delete"  class="btn btn-outline-primary">Delete</button> 
    <a href="AdminProfile.jsp" class="btn btn-outline-dark">Back</a>
   
  
</form>`
<%} %>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>