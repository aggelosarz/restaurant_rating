 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>



<style type="text/css">
#upload{
    display:none
}
.gallery{
	margin:10px 5px;
	
}
.gallery img{
	width:300px;
	transition:1s;
	filter:grayscale(100%);
	
}
.gallery img:hover{
	filter:grayscale(0);
	transform:scale(1.1);
}


</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="myStyle.css">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires","0");
    if(session.getAttribute("rest")==null) {
        response.sendRedirect("Index.jsp");
    }else {
%>






<header style="background-color:blue;">
<nav class="navbar navbar-expand-lg  navbar-light   border-bottom box-shadow mb-3 " style="background-color: #e3f2fd;">
<div class="container-fluid ">
 <%
        String status=request.getParameter("status");
        %>
<%if(status!=null){ %>
 <%if(status.equals("customer")){ %>
<a class="navbar-brand col-11" href="">
    <img src="rr.png" 
    width="40" height="40" class="d-inline-block align-top" alt="">
    RRating
  </a>
   <%}else if(status.equals("owner")){ %>
   <a class="navbar-brand col-11" href="ownerProfil.jsp">
    <img src="rr.png" 
    width="40" height="40" class="d-inline-block align-top" alt="">
    RRating
  </a>
  <%}else if(status.equals("guest")){ %>
   
   <a class="navbar-brand col-11" href="Guest.jsp">
    <img src="rr.png" 
    width="40" height="40" class="d-inline-block align-top" alt="">
    RRating
    <% }}%>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
<div class="navbar-collapse collapse " id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a href="Logout" class="nav-link  col-1" >Logout</a>
                        </li> 
                      
                    </ul>
                </div>
            
</div>
</nav>
</header>


<div class="container">
<div class="row">
<div class="col align-self-start" id="div1">
<ul class="list-group list-group-flush  " style="position :fixed">
<li class="list-group-item "><img src="images/<c:out value='${sessionScope.rest.getFilename()}'/>" name="myProfil" id="profil" width="200" height="200" alt="notFound" class="rounded " ></li>
<li class="list-group-item">Πόλη: <c:out value='${sessionScope.rest.getCity()}'/></li>
<li class="list-group-item">Διεύθυνση: <c:out value='${sessionScope.rest.getAdress()}'/></li>
<li class="list-group-item">Κουζίνα: <c:out value='${sessionScope.rest.getType()}'/></li>
<li class="list-group-item">Βαθμολογία: <c:out value='${sessionScope.rest.getGrade()}'/></li>

        <%if(status!=null){ %>
        	 <%if(status.equals("customer")){ %>
        	 <form action="CustController" method="get">
        	  <li class="list-group-item"><div class="rating" >
        	  
        	   <input type="hidden" name="action" value="rate">
        	  <input type="radio" name="star" value="5" id="star5"><label for="star5"></label>
        	  <input type="radio" name="star" value="4" id="star4" ><label for="star4" ></label>
        	  <input type="radio" name="star" value="3" id="star3"><label for="star3"></label>
        	  <input type="radio" name="star" value="2" id="star2"><label for="star2"></label>
        	  <input type="radio" name="star" value="1" id="star1"><label for="star1"></label>
        	 
        	  </div></li>
 </form>
  <%}else if(status.equals("owner")){ %>
  <li class="list-group-item"><a href="OwnerController?action=editRest" class="btn btn-light">Ενημέρωση Εστιαστορίου</a></li>
  
 <%}else if(status.equals("guest")){ %>
 <li class="list-group-item text-info">Εγγραφείτε για να μπορείτε να βαθμολογήσετε</li>

 
  <% }}%>
</ul>
</div>
<div class="col align-self-center">
<h3  class="text-uppercase text-info" ><c:out value="${sessionScope.rest.getRestaurantName()}" /></h3>
</div>
</div>
<div class="row">
<div class="col-3"></div>
<div id="infos"class="col-9"   style="background-color:	#808080; border-radius:30px;">

<p class="font-weight-normal">
<h3 >Infos</h3>
<p  ><c:out value="${sessionScope.rest.getDescription()}" />
</p>

</div>
</div>
</div>


<%
        
    }
    %>	
      

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>

<script>
$('input[type=radio]').on('change', function() {
    $(this).closest("form").submit();
});

</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>





