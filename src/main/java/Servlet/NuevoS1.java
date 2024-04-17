package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Define un servlet llamado NuevoS1
@WebServlet({"/nuevoS1"})
public class NuevoS1 extends HttpServlet {

    // Método que se llama cuando se realiza una solicitud HTTP GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Establece el tipo de contenido de la respuesta como texto HTML con codificación UTF-8
        resp.setContentType("text/html;charset=UTF-8");

        // Configura el encabezado HTTP para que el navegador actualice la página cada segundo
        resp.setHeader("refresh", "1");

        // Obtiene la hora actual del sistema
        LocalTime hora = LocalTime.now();

        // Define un formateador de fecha y hora con el patrón "hh:mm:ss" (horas, minutos y segundos)
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");

        // Obtiene un objeto PrintWriter desde HttpServletResponse para escribir la respuesta
        try (PrintWriter out = resp.getWriter()) {
            // Escribe la estructura básica de un documento HTML
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>La hora actualizada</title>");
            out.println("</head>");
            out.println("<body>");

            // Escribe un encabezado de nivel 1 en HTML
            out.println("<h1>La hora actualizada!</h1>");

            // Escribe la hora actual formateada en un encabezado de nivel 3 en HTML
            out.println("<h3>" + hora.format(df) + "</h3>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}