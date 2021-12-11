<%--
  Created by IntelliJ IDEA.
  User: Yuriy
  Date: 27.11.2021
  Time: 17:50
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
        <div class="col-7">
            <h4 align="left">Travel Agency</h4>
        </div>
        <div class="col-2">
            <p align="right"><button type = "button" class="btn btn-outline-primary"><a href = "/jsp/account.jsp">My account</a></button></p>
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





</body>
</html>
