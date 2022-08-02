<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
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
   
   <div class="fixed-top">
  <div class="collapse" id="navbarToggleExternalContent">
    <div class="bg-dark p-4">
    <a href="Logout" class="btn btn-info btn-lg">
          <span class="glyphicon glyphicon-log-out"></span> Logout
        </a>
    </div>
  </div>
  <nav class="navbar navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </nav>
</div>

<br/><br/><br/><br/>
 <div class="row">
   
 <div class="card border-primary mb-3" style="max-width: 18rem;">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Delete users</h5>
        <p class="card-text">Delete a customer or an owner.</p>
        <a href="AdminController?action=getUsers&type=Cust" class="btn btn-primary">Delete Customer</a>
         <a href="AdminController?action=getUsers&type=Owner" class="btn btn-primary">Delete Owner</a>
      </div>
    </div>
  </div>
 
  
 
  
 <div class="card border-primary mb-3" style="max-width: 18rem;">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">category</h5>
        <p class="card-text">Insert a new category for restaurnats</p>
        <a href="insertCategory.jsp" class="btn btn-primary">Insert</a>
      </div>
    </div>
  </div>
  </div>
  

 



   
   
   
   
   </form>

<%} %>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>