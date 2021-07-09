<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class = 'h-100'>

<meta charset="utf-8">

<title>Home</title>

<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/cover/">



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
<style><%@include file="/css/cover.css"%></style>
</head>

<body class="d-flex h-100 text-center text-white bg-dark">

<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
    <header style="margin-bottom: 10%">
        <div>
            <h3 class="float-md-start mb-0">Java EE</h3>
            <nav class="nav nav-masthead justify-content-center float-md-end">

                <form action="/session" method="post">
                <button class="nav-link" type="submit" style="display: inline"  name="request" value="exit">Exit</button>
                <button class="nav-link" type="submit"  style="color:red;display: inline;"  name="request" value="delete">Delete account</button>
                </form>
            </nav>
        </div>
    </header>

    <main class="px-3">
        <h1>Your money: <%= request.getSession().getAttribute("money") %></h1>
        <p class="lead">You can send it to someone.</p>
        <p class="lead">
        <br><br>
        <form action="/session" method="post">
        <div>
            <div style = "display: inline-block; text-align: right; margin-right: 15px;">
                <h5>Username:</h5>
                <h5>Money:</h5>
            </div>
            <div style = "display: inline-block; text-align: left;">
                <input type="text" name="toSend"><br>
                <input type="text" name="amountSend"><br>
            </div>
        </div>


        <button class="btn btn-primary btn-lg" type="submit" style="display: inline; margin-top: 10px;"  name="request" value="send">Send money</button>
        </form>
        </p>
    </main>

    <footer class="mt-auto text-white-50">
    </footer>
</div>

</body>

</html>