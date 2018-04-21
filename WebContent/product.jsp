<%@ page import="java.util.Optional" %>
<%@ page import="hibernate.shop.product.Product" %>
<%@ page import="hibernate.shop.product.ProductRepository" %>
<%@ page import="hibernate.shop.utils.ProjectHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="hibernate.shop.product.ProductRating" %>
<%@ page import="java.math.BigDecimal" %>
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
    <link href="css/star-rating.css" media="all" rel="stylesheet" type="text/css" />

    <!-- optionally if you need to use a theme, then include the theme CSS file as mentioned below -->
    <link href="themes/krajee-svg/theme.css" media="all" rel="stylesheet" type="text/css" />

    <!-- important mandatory libraries -->
    <script src="js/jquery.js"></script>
    <script src="js/star-rating.js" type="text/javascript"></script>

    <!-- optionally if you need to use a theme, then include the theme JS file as mentioned below -->
    <script src="themes/krajee-svg/theme.js"></script>





  </head>
<%
  Optional<Product> product= ProductRepository.findOneById(
          ProjectHelper.parseStringToLong(request.getParameter("productId")));
  if(product.isPresent()){
    List<ProductRating> allProductRating = ProductRepository.findAllByProductId(product.get().getId());
    Double avgProductRating = ProductRepository.findAllByProductIdAvg(product.get().getId());
      pageContext.setAttribute("product", product.get());
      pageContext.setAttribute("allProductRating", allProductRating);
      pageContext.setAttribute("avgProductRating", avgProductRating);
  }
%>
  <body>

    <!-- Navigation -->
    <%@ include file="head.jsp"%>

    <!-- Page Content -->
    <div class="container">

      <!-- Portfolio Item Heading -->
      <h1 class="my-4">${product.name}</h1>

      <!-- Portfolio Item Row -->
      <div class="row">

        <div class="col-md-8">
          <img class="img-fluid" src="/productImage/pexels-photo-100582.jpeg" alt="">
        </div>

        <div class="col-md-4">
          <h3 class="my-3">${product.price.grossPrice}</h3>
          <h4 class="my-3">${product.price.nettoPrice}</h4>
          <p>${product.description}</p>
          <form action="/addProduct" method="get">
            <input name="productId" type="hidden" value="${product.id}">
            <input name="productAmount" type="text" value="1">
            <button type="submit" class="btn-primary btn">Add to cart</button>
          </form>
        </div>

      </div>
      <!-- /.row -->

      <!-- Related Projects Row -->
      <h3 class="my-4">Related Projects</h3>

      <div class="row">

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="http://placehold.it/500x300" alt="">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="http://placehold.it/500x300" alt="">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="http://placehold.it/500x300" alt="">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img class="img-fluid" src="http://placehold.it/500x300" alt="">
          </a>
        </div>

      </div>
      <!-- /.row -->

	  <!-- productRating -->
      <div class="col-md-12">

        <div>
          <form method="post" action="/addNewProductRating" >
          <label>Opinion:</label>
          <textarea class="form-control" name="description" > </textarea>
          <input id="input-id" name="rating" type="text" class="rating" data-size="lg" >
          <input type="hidden" value="1" name="productId" />
          <button type="submit" class="btn-primary btn"> Save</button>

          </form>
        </div>

      </div>
    <c:forEach items="${allProductRating}" var="productRating">
      <div class="col-md-4">
        <div>Opinia wystawiona: ${productRating.createDate}</div>
        <div>Ocena: ${productRating.rating}</div>
        <div>Komentarz: ${productRating.description}</div>
      </div>
    </c:forEach>
      <div>Srednia ocen - ${avgProductRating}</div>
    </div>
    <!-- /.container -->

    <!-- Footer -->
    <%@ include file="foot.jsp"%>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
