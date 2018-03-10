<%@ page language="java" contentType="text/html; harset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="carrent.rent.CarRepository" %>
<%@ page import="carrent.rent.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="carrent.rent.RentRepository" %>
<%@ page import="carrent.rent.Rent" %>
<%@ page import="carrent.account.User" %>
<%@ page import="java.util.Optional" %>
<%@ page import="carrent.account.UserRepository" %>
<%
    Cookie[] cookies = request.getCookies();
    for(Cookie c : cookies){
        if(c.getName().equals("remember")){
            Optional<User> userByEmail = UserRepository.findUserByEmail(c.getValue());
            if(userByEmail.isPresent()) {
                request.getSession().setAttribute("userId", userByEmail.get().getId());
                response.sendRedirect("index.jsp");
                return;
            }
        }
    }

    String error = request.getParameter("error");
    if(error != null && error.equals("true"))
    pageContext.setAttribute("error", error);
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
                    <form class="form-signin" action="login" method="post">
                        <span id="reauth-email" class="reauth-email"></span>
                        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus>
                        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
                        <c:if test="${not empty error}">
                        <div>
                            <p class="alert-danger">Login lub hasło jest nieprawidłowe</p>
                        </div>
                        </c:if>
                        <div id="remember" class="checkbox">
                            <label>
                                <input type="checkbox" value="remember-me" name="remember"> Remember me
                            </label>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>

                        <a class="btn btn-lg btn-primary btn-block btn-signin" href="register.jsp">Sign up</a>

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