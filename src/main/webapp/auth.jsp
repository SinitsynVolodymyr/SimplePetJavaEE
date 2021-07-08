<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Auth</title>
</head>
    <body>
        <h1>
            <a>Money: <%= request.getSession().getAttribute("money") %></a>
            <br>
            <br>
            <br>
            <form action="/session" method="post">
                <button type="submit" name="request" value="exit" class="btn-link">Exit</button>
            </form>
            <form action="/session" method="post">
                <button type="submit" name="request" value="delete" class="btn-link">Delete account</button>
            </form>

        </h1>
    </body>
</html>