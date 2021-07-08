<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Auth</title>
</head>
    <body>
        <h1>
            <a>Money: <%= request.getSession().getAttribute("money") %></a>
        </h1>
    </body>
</html>