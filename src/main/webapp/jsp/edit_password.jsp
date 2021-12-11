<%--
  Created by IntelliJ IDEA.
  User: Yuriy
  Date: 08.12.2021
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS (jsDelivr CDN) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Bootstrap Bundle JS (jsDelivr CDN) -->
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid p-4 my-0 bg-light text-info">
    <div class="row">
        <div class="col-8">
            <h4 align="left">Travel Agency</h4>
        </div>
        <div class="col-1">
            <p align="right"><button type = "button" class="btn btn-outline-primary"><a href = "/jsp/tour.jsp">Home</a></button></p>
        </div>
        <div class="col-1">
            <p align="center"><button type = "button" class="btn btn-outline-primary"><a href = "/jsp/home.jsp">My tour</a></button></p>
        </div>
        <div class="col-1">
            <p align="left"><button type = "button" class="btn btn-outline-primary"><a href = "/"> Log out </a></button></p>
        </div>
        <div class="col-1">
            <p align="left"><button type = "button" class="btn btn-outline-primary"><a href = "/jsp/home.jsp">Language</a></button></p>
        </div>
    </div>
</div>


<form action="/jsp/edit_password", method="post">
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="mb-3 mt-4">
                    <h3>Change password</h3>
                </div>
                <c:if test="${errorMyPassword == '1'}">
                    <div class="bg-danger rounded">
                        <p align="center">Your password is wrong</p>
                    </div>
                </c:if>
                <c:if test="${errorNewPassword == '1'}">
                    <div class="bg-danger rounded">
                        <p align="center">You entered incorrectly password!
                            Password must be between 5 and 15 characters</p>
                    </div>
                </c:if>
                <c:if test="${errorRepeatNewPassword == '1'}">
                    <div class="bg-danger rounded">
                        <p align="center">Your password and repeat password are different</p>
                    </div>
                </c:if>
                <div class="mb-3">
                    <label for="your password" class="form-label">Enter your password:</label>
                    <input type="password" class="form-control" id="your password" placeholder="Enter your password" name="my_password">
                </div>
                <div class="mb-3">
                    <label for="new password" class="form-label">Enter new password:</label>
                    <input type="password" class="form-control" id="new password" placeholder="Enter new password" name="new_password">
                </div>
                <div class="mb-3">
                    <label for="repeat password" class="form-label">Repeat new password:</label>
                    <input type="password" class="form-control" id="repeat password" placeholder="Repeat new password" name="repeat_password">
                </div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary">Edit</button>
                </div>
            </div>
        </div>
    </div>

</form>
