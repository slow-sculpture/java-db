<%@ page import="java.util.Optional" %>
<%@ page import="hibernate.shop.product.Product" %>
<%@ page import="hibernate.shop.product.ProductRepository" %>
<%@ page import="hibernate.shop.utils.ProjectHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="hibernate.shop.product.ProductRating" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="hibernate.shop.product.ProductType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Portfolio Item - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/portfolio-item.css" rel="stylesheet">

    <!-- rating -->
    <!-- default styles -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css" rel="stylesheet">
    <link href="css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>

    <!-- optionally if you need to use a theme, then include the theme CSS file as mentioned below -->
    <link href="themes/krajee-svg/theme.css" media="all" rel="stylesheet" type="text/css"/>

    <!-- important mandatory libraries -->
    <script src="js/jquery.js"></script>
    <script src="js/star-rating.js" type="text/javascript"></script>

    <!-- optionally if you need to use a theme, then include the theme JS file as mentioned below -->
    <script src="themes/krajee-svg/theme.js"></script>


</head>
<%
    Optional<Product> product = ProductRepository.findOneById(
            ProjectHelper.parseStringToLong(request.getParameter("productId")));

    ProductType[] values = ProductType.values();
    pageContext.setAttribute("productTypeList", values);
    if (product.isPresent()) {
        pageContext.setAttribute("product", product.get());


    }

    if (product.isPresent()) {
        List<ProductRating> allProductRating = ProductRepository.findAllByProductId(product.get().getId());
        Double avgProductRating = ProductRepository.findAllByProductIdAvg(product.get().getId());
        pageContext.setAttribute("product", product.get());
        pageContext.setAttribute("allProductRating", allProductRating);
        pageContext.setAttribute("avgProductRating", avgProductRating);
    }
%>
<body>

<!-- Navigation -->
<%@ include file="head.jsp" %>

<!-- Page Content -->
<div class="container">
    <form method="post" action="editProduct">
        <div class="form-group">
            <label>Nazwa:</label>
            <input type="text" name="name">
        </div>
        <div class="form-group">

            <label>Typ produktu:</label>
            <select name="productType">
                <c:forEach items="${productTypeList}" var="pd">
                    <option>${pd.name()}</option>
                </c:forEach>
            </select></div>
        <div class="form-group">
            <label>Cena netto:</label>
            <input type="number" name="nettoPrice">
        </div>
        <div class="form-group">
            <label>Cena brutto:</label>
            <input type="number" name="grossPrice">
        </div>
        <div class="form-group">
            <label>Opis:</label>
            <textarea name="description"></textarea>
        </div>
        <div class="form-group">
            <button type="submit">Zapisz</button>
        </div>
    </form>

</div>
<!-- /.container -->

<!-- Footer -->
<%@ include file="foot.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
