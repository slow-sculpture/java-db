<%@ page import="java.util.Optional" %>
<%@ page import="hibernate.shop.cart.Cart" %>
<%@ page import="hibernate.shop.cart.CartRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">

</head>

<body>

<!-- Navigation -->
<%@ include file="head.jsp" %>
<%
    if (userFromCookie != null) {
        Optional<Cart> byUserId = CartRepository.findByUserId(userFromCookie.getId());
        if (byUserId.isPresent()) {
            pageContext.setAttribute("cart", byUserId.get());
        }
    }
%>
<!-- Page Content -->
<div class="container">

    <div class="row">

        <%@ include file="leftMenu.jsp" %>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">
            <h2>Koszyk</h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Nazwa produktu</th>
                    <th scope="col">Ilość</th>
                    <th scope="col">Kwota netto</th>
                    <th scope="col">Kwota brutto</th>
                    <th scope="col">Razem</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cart.cartDetailSet}" var="cd" varStatus="it">
                    <tr>
                        <th scope="row">${cd.product.name}</th>
                        <td>
                            <input name="amount_1" value="${cd.amount}"/>
                            <span class="glyphicon glyphicon-plus" aria-hidden="true">
                                    +
                                    -
                                </span>

                        </td>
                        <td>${cd.price.nettoPrice}</td>
                        <td>${cd.price.grossPrice}</td>
                        <fmt:formatNumber var="totalNettoPrice" type="number"
                                          minFractionDigits="2" maxFractionDigits="2"
                                         value="${cd.price.nettoPrice.multiply(cd.amount)}"/>
                        <fmt:formatNumber var="totalNettoPrice" type="number"
                                          minFractionDigits="2" maxFractionDigits="2"
                                         value="${cd.price.grossPrice.multiply(cd.amount)}"/>
                        <td>${cd.price.nettoPrice.multiply(cd.amount)}</td>
                        <td>${cd.price.grossPrice.multiply(cd.amount)}</td>
                    </tr>
                </c:forEach>

                </tbody>
                <tfoot>
                <tr>
                    <td></td>
                    <td><b>Suma:</b></td>
                    <td><fmt:formatNumber type="number"
                                          minFractionDigits="2" maxFractionDigits="2"
                                          value="${cart.totalNettoPrice}"/></td>
                    <td><fmt:formatNumber type="number"
                                          minFractionDigits="2" maxFractionDigits="2"
                                          value="${cart.totalGrossPrice}"/></td>
                </tr>
                </tfoot>
            </table>
            <!-- /.card -->

            <div class="row">
                <div class="col-md-6">
                    <h5>Metoda dostawy:</h5>
                    <select class="form-control">
                        <option>Kurier</option>
                        <option>Odbiór osobisty</option>

                    </select>
                </div>
                <div class="col-md-6">
                    <h5>Adres dostawy</h5>
                    <div class="col-md-12">
                        <label>Miejscowość</label>
                        <input type="text" class="form-control" name="city"/>
                    </div>
                    <div class="col-md-12">
                        <label>Kod pocztowy</label>
                        <input type="text" class="form-control" name="zip"/>
                    </div>
                    <div class="col-md-12">
                        <label>Ulica</label>
                        <input type="text" class="form-control" name="street"/>
                    </div>

                </div>
            </div>

            <div>
                <a href="/createOrder" class="btn btn-success">Kup i zapłać</a>
            </div>

        </div>
        <!-- /.col-lg-9 -->

    </div>

</div>
<!-- /.container -->

<!-- Footer -->
<%@ include file="foot.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
