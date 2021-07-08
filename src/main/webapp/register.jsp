<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <title>Register</title>




    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


    <!-- Custom styles for this template -->

    <style><%@include file="/css/signin.css"%></style>
</head>



<body class="text-center">

<main class="form-signin">
    <form action="/session" method="post">

        <h1 class="h3 mb-3 fw-normal">Register</h1>
        <a style="text-align: right;" href="/login">
            <div>
                Sign In
            </div>
        </a>

        <div class="form-floating">
            <input type="text" name="reglogin" class="form-control" id="floatingInput" placeholder="login">
            <label for="floatingInput">Login</label>
        </div>
        <div class="form-floating">
            <input type="password" name="regpass" class="form-control" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword">Password</label>
        </div>

        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit" name="request" value="register">Register</button>
    </form>
</main>


</html>