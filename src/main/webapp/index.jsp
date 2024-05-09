<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - S1</title>
</head>
<body>
<h1><%= "Seguimiento 1" %></h1>
<ul>
  <li> <a href="http://localhost:8080/C2Seguimiento1_war/test">Ruta Test</a> </li>
  <li> <a href="http://localhost:8080/C2Seguimiento1_war/students.xls">Ruta Student XLS</a> </li>
  <li> <a href="http://localhost:8080/C2Seguimiento1_war/students.html">Ruta Student Html</a> </li>
  <li> <a href="http://localhost:8080/C2Seguimiento1_war/students">Ruta Students</a> </li>
  <li> <a href="http://localhost:8080/C2Seguimiento1_war/student.json">Ruta Students desde Json</a> </li>
  <li> <a href="http://localhost:8080/C2Seguimiento1_war/nuevoS1">Ruta Ver Hora actual</a> </li>
  <li> <a href="http://localhost:8080/C2Seguimiento1_war/Login.html">Ruta Login html</a> </li>
  <li> <a href="http://localhost:8080/C2Seguimiento1_war/Logout">Ruta Logout</a> </li>
</ul>

<h2>Iniciar Sesión</h2>
<form action="Login" method="post">
  <label for="username">Usuario:</label><br>
  <input type="text" id="username" name="username"><br>
  <label for="password">Contraseña:</label><br>
  <input type="password" id="password" name="password"><br><br>
  <input type="submit" value="Iniciar Sesión">
</form>

</body>
</html>