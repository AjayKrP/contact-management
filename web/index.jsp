<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>Contacts Application</title>
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark"
       style="background-color: tomato">
    <div>
      <a href="#" class="navbar-brand"> Contacts App </a>
    </div>
  </nav>
</header>
<br>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body text-center">
      <h4 class="">Welcome to Contacts App</h4>
      <a  href="<%=request.getContextPath()%>/list"
         class="btn btn-success">Start Adding Contacts</a>
    </div>
  </div>
</div>
</body>
</html>
