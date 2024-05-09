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
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;


@WebServlet({"/Login","/Login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME="admin";
    final static String PASSWORD="12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth=new LoginServiceImpl();
        Optional<String>cookieOptional=auth.getUsername(req);
        if (cookieOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out=resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <metacharset=\"UTF-8\">");
                out.println(" <title>Hola " + cookieOptional.get() + "</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Login correcto!</h1>");
                out.println(" <h3>Hola "+ cookieOptional.get() +" ya has iniciado sesión anteriormente</h3>");
                out.println("<p><a href='" + req.getContextPath() + "/student.json'>Ver lista estudiantes</a></p>");
                out.println(" </body>");
                out.println("</html>");
            }
        }else {
        getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,
        IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if(USERNAME.equals(username) && PASSWORD.equals(password)){
            Cookie usernameCookie=new Cookie("Username", username);
            resp.addCookie(usernameCookie);
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out=resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <metacharset=\"UTF-8\">");
                out.println(" <title>Login correcto!</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Login correcto!</h1>");
                out.println(" <h3>Hola "+username+" has iniciado sesión con éxito!</h3>");
                out.println("<p><a href='" + req.getContextPath() + "/student.json'>Ver lista estudiantes</a></p>");
                out.println(" </body>");
                out.println("</html>");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
}