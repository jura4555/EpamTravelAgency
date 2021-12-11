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
        <div class="col-10">
            <h4 align="left">Travel Agency</h4>
        </div>
        <div class="col-1">
            <p align="right"><button type = "button" class="btn btn-outline-primary"><a href = "/jsp/home.jsp">Home</a></button></p>
        </div>
        <div class="col-1">
            <p align="left"><button type = "button" class="btn btn-outline-primary"><a href = "/jsp/login.jsp">Login</a></button></p>
        </div>
    </div>
</div>

<form action="/jsp/registration", method="post">
    <div class="container">
        <div class="row">
            <div class="col-3">
                <!--<div class="mb-5 ms-5 mt-3">-->
                <div class="mb-3 mt-4">
                    <h3>Sign in</h3>
                </div>
                <c:if test="${errorLogin == '1'}">
                    <div class="bg-danger rounded">
                        <p align="center"> You entered incorrectly login!
                            Login must be between 3 and 15 English characters or numbers</p>
                    </div>
                </c:if>
                <c:if test="${errorLoginUnique == '1'}">
                    <div class="bg-danger rounded">
                        <p align="center"> This login is exist!</p>
                    </div>
                </c:if>
                <c:if test="${errorPasswordLenght == '1'}">
                    <div class="bg-danger rounded">
                        <p align="center"> You entered incorrectly password!
                            Password must be between 5 and 15 characters</p>
                    </div>
                </c:if>
                <c:if test="${errorPassword == '1'}">
                    <div class="bg-danger rounded">
                        <p align="center">Your password and repeat password are different</p>
                    </div>
                </c:if>
                <c:if test="${errorName == '1'}">
                    <div class="bg-danger rounded">
                        <p align="center"> You entered incorrectly name!
                            Name must be between 1 and 15 characters</p>
                    </div>
                </c:if>
                <c:if test="${errorSurname == '1'}">
                    <div class="bg-danger rounded">
                        <p align="center"> You entered incorrectly surname!
                            Surname must be between 1 and 15 characters</p>
                    </div>
                </c:if>
                <c:if test="${errorEmail == '1'}">
                    <div class="bg-danger rounded">
                        <p align="center"> You entered incorrectly e-mail!</p>
                    </div>
                </c:if>
                <!--<div class="mb-1 ms-5 mt-3">-->
                <div class="mb-3">
                    <label for="login" class="form-label">Enter login:</label>
                    <input type="text" class="form-control" id="login" placeholder="Enter login" name="login" value=${login}>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Enter passsword:</label>
                    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
                </div>
                <div class="mb-3">
                    <label for="Repeat password" class="form-label">Repeat password:</label>
                    <input type="password" class="form-control" id="Repeat password" placeholder="Repeat password" name="Repeat password">
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Enter name:</label>
                    <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value=${name}>
                </div>
                <div class="mb-3">
                    <label for="surname" class="form-label">Enter surname:</label>
                    <input type="text" class="form-control" id="surname" placeholder="Enter surname" name="surname" value=${surname}>
                </div>
                <div class="mb-3">
                    <label for="E-mail" class="form-label">Enter E-mail:</label>
                    <input type="email" class="form-control" id="E-mail" placeholder="...@..." name="E-mail" value=${email}>
                </div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary">Sign up</button>
                </div>
            </div>
        </div>
    </div>
</form>

</body>
</html>
