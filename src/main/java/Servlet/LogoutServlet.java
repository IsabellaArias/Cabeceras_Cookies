package Servlet;

import Services.LoginService;
import Services.impl.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl(); // Crea una instancia de LoginServiceImpl
        Optional<String> username = auth.getUsername(req); // Obtiene el nombre de usuario de la solicitud HTTP req
        if (username.isPresent()) { // Verifica si el nombre de usuario existe
            Cookie usernameCookie = new Cookie("Username", ""); // Crea una nueva cookie llamada "Username"
            usernameCookie.setMaxAge(0); // Establece el tiempo de vida de la cookie a 0, lo que significa que se eliminará al cerrar el navegador
            resp.addCookie(usernameCookie); // Agrega la cookie al objeto de respuesta resp
        }
        resp.sendRedirect(req.getContextPath() + "/Login.html"); // Redirige al usuario a la página de inicio de sesión "/Login.html"
    }
}