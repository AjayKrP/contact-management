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
                   class="nav-link">Contact</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${contact != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${contact == null}">
                <form id="contact-form" action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${contact != null}">
                                Edit Contact
                            </c:if>
                            <c:if test="${contact == null}">
                                Add New Contact
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${contact != null}">
                        <input type="hidden" name="id" value="<c:out value='${contact.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Contact Name</label> <input type="text"
                                                        value="<c:out value='${contact.name}' />" class="form-control"
                                                        name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Email</label> <input type="text"
                                                         value="<c:out value='${contact.email}'  />" class="form-control"
                                                         name="email">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Address</label> <input type="text"
                                                           value="<c:out value='${contact.address}' />" class="form-control"
                                                           name="address">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Mobile</label> <input type="text"
                                                      value="<c:out value='${contact.mobile}'   />" class="form-control"
                                                      name="mobile">
                    </fieldset>

                    <button type="button" id="save-form-button" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>

<script>
    //

    function validateMobile(mobileField){
        console.log(mobileField);
        let regMobile = /^([0|\+[0-9]{1,5})?([7-9][0-9]{9})$/;
        if (regMobile.test(mobileField) === false) {
            alert('Invalid Mobile Number');
            return false;
        }
        return true;
    }

    function validateEmail(emailField){
        let reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
        if (reg.test(emailField) === false) {
            alert('Invalid Email Address');
            return false;
        }
        return true;
    }

    $(document).ready(function () {
        let mobileField = $('[name="mobile"]');
        $('[name="email"]').on('blur', function () {
            console.log($(this).val());
            validateEmail($(this).val());
        });
        $('[name="mobile"]').on('blur', function () {
            console.log($(this).val())
            validateMobile($(this).val());
        });


        $('#save-form-button').click(function (e) {
            e.preventDefault();
            let  emailFieldValue = $('[name="email"]').val();
            let mobileFieldValue = $('[name="mobile"]').val();
            console.log(emailFieldValue, mobileFieldValue);

            if (validateMobile(mobileFieldValue) && validateEmail(emailFieldValue)) {
                $('#contact-form').submit();
            } else {
                return false;
            }
        })
    })
</script>
</body>
</html>

