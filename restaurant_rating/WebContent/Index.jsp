 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

</head>
<body class="mybody">

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
                            <a href="GuestController?action=signin" class="nav-link  col-1" >Eπισκέπτης</a>
                        </li> 
                        <li class="nav-item">
                            <a href="signup.jsp" class="nav-link  col-1" >Εγγραφή</a>
                        </li>
                      
                    </ul>
                </div>
            
</div>
</nav>
</header>


<section class="container-fluid">
<section class="row justify-content-center">
<form   name="form1" action="CustController" method="post" class="myform" >

<h1>Login</h1>
  <div class="form-group " >
  <input type="hidden" name="action" value="signin">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1"  name="myEmail" aria-describedby="emailHelp" required>
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group" >
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" name="myPass" id="exampleInputPassword1" required>
  </div>
 <div class="form-check">
  <input class="form-check-input" type="radio" name="type" id="exampleRadios1" value="Cust" checked>
  <label class="form-check-label" for="exampleRadios1">
    Πελάτης
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="type" id="exampleRadios2" value="Owner">
  <label class="form-check-label" for="exampleRadios2">
    Καταστηματάρχης
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="type" id="exampleRadios3" value="Admin">
  <label class="form-check-label" for="exampleRadios3">
    Διαχειριστής
  </label>
</div>
<br>
<div class="col text-center">
  <button type="submit" class="btn btn-primary ">Login</button>
</div>
 <%
       
 
        String status=request.getParameter("status");
        %>
        <%if(status!=null){ %>
        	 <% if(status.equals("false")){ %>
        	    <br><div class="alert alert-danger" role="alert">The Email is not valid!</div>

  <% }else if (status.equals("error")){ %>
    <br> <div class="alert alert-danger" role="alert">There was an error!Try again.</div>
  <%}} %>
</form>
</section>
</section>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>
jQuery(document).ready(function($) {
    var form = $('form[name="form1"]'),
        radio = $('input[name="type"]'),
        choice = '';

    radio.change(function(e) {
        choice = this.value;

        if (choice === 'Cust') {
            form.attr('action', 'CustController');
        } else if(choice === 'Owner') {
            form.attr('action', 'OwnerController');
        }
        else{
        	form.attr('action', 'AdminController');
        }
    });
});


</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>