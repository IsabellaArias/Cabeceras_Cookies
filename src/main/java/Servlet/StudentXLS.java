package Servlet;

import Mapping.dtos.StudentDto;
import Services.StudentService;
import Services.impl.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/students.xls","/students.html","/students"})
public class StudentXLS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Crea una instancia del servicio StudentService
        StudentService service = new StudentServiceImpl();

        // Obtiene una lista de estudiantes desde el servicio StudentService
        List<StudentDto> students = service.listar();

        // Establece el tipo de contenido de la respuesta a "text/html;charset=UTF-8"
        resp.setContentType("text/html;charset=UTF-8");

        // Obtiene el nombre del servlet path
        String servletPath = req.getServletPath();

        // Verifica si el nombre del servlet path termina con ".xls"
        boolean esXls = servletPath.endsWith(".xls");

        // Si el nombre del servlet path termina con ".xls", establece el tipo de contenido de la respuesta a "application/vnd.ms-excel" y establece la cabecera "Content-Disposition" para adjuntar el archivo con el nombre "students.xls"
        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=students.xls");
        }

        // Obtiene un PrintWriter para escribir la respuesta
        try (PrintWriter out = resp.getWriter()) {

            // Si el nombre del servlet path no termina con ".xls", escribe el HTML de encabezado, título y un enlace para descargar los estudiantes como un archivo Excel
            if (!esXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Listado de Estudiantes</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>¡Listado De Estudiantes!</h1>");
                out.println("<p><a href=\"" + req.getContextPath() + "/students.xls\"\">exportara xls</a></p>");
            }

            // Escribe el HTML de la tabla de encabezado con los nombres de las columnas
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>correo</th>");
            out.println("<th>Semestre</th>");
            out.println("</tr>");

            // Itera a través de la lista de estudiantes y escribe cada estudiante como una fila en la tabla
            students.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.id() + "</td>");
                out.println("<td>" + p.name() + "</td>");
                out.println("<td>" + p.email() + "</td>");
                out.println("<td>" + p.semestre() + "</td>");
                out.println("</tr>");
            });

            // Si el nombre del servlet path no termina con ".xls", escribe el HTML de cierre del cuerpo y del documento
            if (!esXls) {
                out.println(" </body>");
                out.println("</html>");
            }
        }
    }
}
