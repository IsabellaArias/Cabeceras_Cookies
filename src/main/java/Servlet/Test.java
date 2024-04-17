package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

// Esta anotación indica que esta clase maneja solicitudes para la ruta "/test"
@WebServlet(value="/test")
// Definimos la clase Test que extiende de HttpServlet para manejar solicitudes HTTP
public class Test extends HttpServlet {
    // Método que se ejecuta cuando se recibe una solicitud GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        resp.setContentType("text/html"); // Establecemos el tipo de contenido de la respuesta como HTML
        String metodoHttp = req.getMethod();// Obtenemos el método HTTP utilizado en la solicitud (GET, POST, etc.)
        String requestUri = req.getRequestURI();// Obtenemos la URI (Uniform Resource Identifier) de la solicitud
        String requestUrl = req.getRequestURL().toString();// Obtenemos la URL de la solicitud como una cadena
        String contexPath = req.getContextPath();// Obtenemos el contexto de la aplicación (el prefijo de la URL de la aplicación)

        String servletPath = req.getServletPath(); // Obtenemos el camino del servlet en la URL
        String ipCliente = req.getRemoteAddr();// Obtenemos la dirección IP del cliente que realizó la solicitud
        String ip = req.getLocalAddr();// Obtenemos la dirección IP del servidor local
        int port = req.getLocalPort();// Obtenemos el puerto del servidor local
        String scheme = req.getScheme();// Obtenemos el esquema utilizado en la solicitud (HTTP o HTTPS)
        String host = req.getHeader("host");// Obtenemos el encabezado "host" de la solicitud, que contiene la dirección del servidor y el puerto
        // Construimos la URL completa utilizando el esquema, el host, el contexto de la aplicación y el camino del servlet
        String url = scheme + "://" + host + contexPath + servletPath;
        // Construimos una segunda URL, incluyendo la dirección IP y el puerto del servidor local
        String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath;

        // Enviamos la respuesta al cliente
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>¡CABECERAS HTTP REQUEST!</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>¡CABECERAS HTTP REQUEST!</h1>");
            out.println("<ul>");

            // Mostramos la información recopilada sobre la solicitud HTTP
            out.println("<li>metodo http: " + metodoHttp + "</li>");
            out.println("<li>request uri: " + requestUri + "</li>");
            out.println("<li>request url: " + requestUrl + "</li>");
            out.println("<li>context path: " + contexPath + "</li>");
            out.println("<li>servlet path: " + servletPath + "</li>");
            out.println("<li>ip local: " + ip + "</li>");
            out.println("<li>ip cliente: " + ipCliente + "</li>");
            out.println("<li>puerto local: " + port + "</li>");
            out.println("<li>scheme: " + scheme + "</li>");
            out.println("<li>host: " + host + "</li>");
            out.println("<li>url: " + url + "</li>");
            out.println("<li>url2: " + url2 + "</li>");

            // Mostramos todos los encabezados HTTP de la solicitud
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                out.println("<li>"+ cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
}