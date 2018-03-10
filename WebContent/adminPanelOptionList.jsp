<%@ page language="java" contentType="text/html; harset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="carrent.rent.*" %>
<%@ page import="carrent.option.OptionRepository" %>
<%
    List<Option> all = OptionRepository.findAll();
    pageContext.setAttribute("optionList", all);
%>
<jsp:include page="header.jsp" />
    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <div class="col-lg-3">
          <h1 class="my-4">Shop Name</h1>
          <div class="list-group">
            <a href="#" class="list-group-item active">Category 1</a>
            <a href="#" class="list-group-item">Category 2</a>
            <a href="#" class="list-group-item">Category 3</a>
          </div>
        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">
            <div class="row">
                <form method="post" action="addOption">
                    <input type="text" name="optionName" class="form-control">
                    <button class="btn btn-lg btn-primary btn-block btn-signin"
                            type="submit">Save</button>

                </form>
            </div>

          <c:forEach items="${optionList}" var="option">
          <div class="row">
              <div class="col-md-10">
              <p>${option.name}</p>
              </div>
              <div class="col-md-2">
              <a href="/removeOption?optionId=${option.id}"
                 class="btn btn-primary">Usun</a>
              </div>

          </div>
          </c:forEach>
        </div>
        <!-- /.col-lg-9 -->

      </div>

    </div>
    <!-- /.container -->
<jsp:include page="footer.jsp" />