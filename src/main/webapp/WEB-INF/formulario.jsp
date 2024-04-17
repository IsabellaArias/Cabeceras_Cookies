<%--
  Created by IntelliJ IDEA.
  User: Gamer
  Date: 16/04/2024
  Time: 6:03 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicio de sesión</title>
</head>
<body>
<form action="login" method="post">
    <div>
        <label for="username">Nombre de usuario</label>
        <div>
            <input type="text" name="username" id="username">
        </div>
    </div>
    <div>
        <label for="password">Contraseña</label>
        <div>
            <input type="password" name="password" id="password">
        </div>
    </div>
    <div>
        <input type="submit" value="Iniciar sesión">
    </div>
</form>
</body>
</html>
