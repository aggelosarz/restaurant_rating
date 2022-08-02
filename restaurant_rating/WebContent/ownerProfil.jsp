 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="myStyle.css">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

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
<nav class="navbar navbar-expand-lg  navbar-light   border-bottom box-shadow mb-3 " style="background-color: #e3f2fd;">
<div class="container-fluid ">
<a class="navbar-brand col-10" href="">
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
                            <a href="OwnerController?action=delete" class="nav-link  col-1" >Διαγραφή λογαριασμού</a>
                        </li> 
                        <li class="nav-item">
                            <a href="Logout" class="nav-link  col-1" >Logout</a>
                        </li> 
                      
                    </ul>
                </div>
            
</div>
</nav>
</header>

<div class="container">
<h1 class="text-center">My Restaurants</h1>
<table border="4" class="table table-striped">
<tbody>
<c:forEach items="${sessionScope.Rests}" var="s">
 <tr>
<td><a href="RestaurantController?action=search&user=owner&restName=<c:out value='${s.getRestaurantName()}'/>&city=<c:out value='${s.getCity()}'/>&type=<c:out value='${s.getType()}'/>"">
<img src="images/<c:out value='${s.getFilename()}'/>" name="myProfil" id="profil" width="70" height="70" alt="notFound" class="row rounded mx-auto d-block" ></td>
</a>

<td><label  class="col"><c:out value='${s.getRestaurantName()}'/></label></td>
<td><label class="col"><c:out value='${s.getCity()}'/></label></td>
<td><label class="col"><c:out value='${s.getAdress()}'/> </label></td>
<td><label  class="col"><c:out value='${s.getType()}'/></label></td>
<td><a class="btn btn-secondary" href="RestaurantController?action=deleteRest&id=<c:out value='${s.getId()}'/>">delete</a></td>
</tr>



</c:forEach>
</tbody>
</table>
<a href="OwnerController?action=newRest" class="btn btn-light">Εισαγωγή νέου Εστιαστορίου</a>
</div>

 
	 <%
        
    }
    %>	
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>