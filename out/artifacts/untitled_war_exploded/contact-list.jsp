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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="#" class="navbar-brand"> Contacts App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Contacts</a></li>
        </ul>
        <form class="form-inline my-2 my-lg-0 navbar-right" action="<%=request.getContextPath()%>/search" method="GET">
            <input class="form-control mr-sm-2" name="name" type="search" placeholder="Search by name" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" style="color: antiquewhite;">Search</button>
        </form>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Contacts</h3>
        <hr>
        <div class="container">
            <div class="custom-control-inline">
                <div class="add-new">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success text-left">Add
                        New Contact</a>
                </div>

               <div class="custom-control-inline" style="margin-left: 50px">
                    <div class="filter-by-attribute">
                        <select class="form-control" id="filter_by">
                            <option>select filter by</option>
                            <option>id</option>
                            <option>name</option>
                            <option>email</option>
                            <option>address</option>
                            <option>mobile</option>
                        </select>
                    </div>
                    <div class="sort_order">
                        <select class="form-control" id="sort_order">\
                            <option>select sort order</option>
                            <option>asc</option>
                            <option>desc</option>
                        </select>
                    </div>
                    <button type="button" id="filter" class="btn btn-success">Filter</button>
                </div>
            </div>
        </div>


        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Address</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="contact" items="${listContact}">

                <tr>
                    <td><c:out value="${contact.id}" /></td>
                    <td><c:out value="${contact.name}" /></td>
                    <td><c:out value="${contact.email}" /></td>
                    <td><c:out value="${contact.mobile}" /></td>
                    <td><c:out value="${contact.address}" /></td>
                    <td><a href="edit?id=<c:out value='${contact.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${contact.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
<script>
    $(document).ready(function () {
        /**
         * If i use AJAX here then no need to use localStorage
         * Using local storage ony for the purpose of remembering uses previous choice
         */
        if (localStorage.getItem('filterBy')) {
            $('#filter_by').val(localStorage.getItem('filterBy'));
        }
        if (localStorage.getItem('orderBy')) {
            $('#sort_order').val(localStorage.getItem('orderBy'));
        }
        $('#filter').click(function () {
            let filterBy = $('#filter_by').val();
            let orderBy = $('#sort_order').val();
            localStorage.setItem('filterBy', filterBy);
            localStorage.setItem('orderBy', orderBy);
            let query_string = "<%=request.getContextPath()%>/filter?filter_by=" + filterBy  + "&sort_order="+ orderBy;
            window.location.href = query_string;
        });
    })
</script>
</body>
</html>
