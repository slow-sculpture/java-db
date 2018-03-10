<%@ page language="java" contentType="text/html; harset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="carrent.rent.CarRepository" %>
<%@ page import="carrent.rent.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="carrent.rent.RentRepository" %>
<%@ page import="carrent.rent.Rent" %>
<%@ page import="java.util.HashMap" %>
<%
    HashMap<String, String> error = (HashMap<String, String>) request.getAttribute("error");
    HashMap<String, String> variable =(HashMap<String, String>) request.getAttribute("variable");
    if(error != null)
        pageContext.setAttribute("error", error);
    if(variable!= null)
        pageContext.setAttribute("variable", variable);



%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop Item - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-item.css" rel="stylesheet">

</head>

<body>
    <!-- Page Content -->
    <div class="container">

                <div class="card card-container">
                    <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
                    <p id="profile-name" class="profile-name-card"></p>
                    <form class="form-signin" action="/register" method="post">
                        <span id="reauth-email" class="reauth-email"></span>
                        <input type="email" value="${variable.get("email")}" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus>
                        <p>${error.get("email")}</p>
                        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
                        <p>${error.get("password")}</p>
                        <input type="password" id="inputPassword2" name="passwordRepeat" class="form-control" placeholder="Password repeat" required>
                        <p>${error.get("passwordRepeat")}</p>
                        <input type="text" value="${variable.get("firstName")}" id="firstName" name="firstName" class="form-control" placeholder="Firstname" required>
                        <p>${error.get("firstName")}</p>
                        <input type="text" value="${variable.get("lastName")}" id="lastName" name="lastName" class="form-control" placeholder="Lastname" required>
                        <p>${error.get("lastName")}</p>
                        <input type="text" value="${variable.get("phoneNumber")}" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="Phone number" required>
                        <p>${error.get("phoneNumber")}</p>
                        <label>Day of birth</label>
                        <input type="date" id="dayOfBirth" name="dayOfBirth" class="form-control"  required>
                        <p>${error.get("dayOfBirth")}</p>
                        <label>License Car day</label>
                        <input type="date" id="licenseCarDay" name="licenseCarDay" class="form-control"  required>
                        <p>${error.get("licenseCarDay")}</p>


                        <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign up</button>

                    </form><!-- /form -->
                    <a href="#" class="forgot-password">
                        Forgot the password?
                    </a>
                </div><!-- /card-container -->

        </div>
        <!-- /.col-lg-9 -->



    </div>
    <!-- /.container -->
<jsp:include page="footer.jsp" />