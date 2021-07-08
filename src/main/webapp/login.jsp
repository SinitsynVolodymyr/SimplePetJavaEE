<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Auth</title>
</head>
    <body>
        <h2>
            <form action="/auth" method="post">
                <p><b>Как по вашему мнению расшифровывается аббревиатура &quot;ОС&quot;?</b></p>
                <p>
                    Login: <input type="text" name="login"><Br>
                    Password: <input type="password" name="pass"><Br>
                <p><input type="submit"></p>
            </form>
        </h2>
    </body>
</html>