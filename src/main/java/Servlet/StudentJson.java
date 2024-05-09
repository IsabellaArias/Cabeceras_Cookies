package Servlet;

import Mapping.dtos.StudentDto;
import Model.Student;
import Services.LoginService;
import Services.StudentService;
import Services.impl.LoginServiceImpl;
import Services.impl.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/student.json"})
public class StudentJson extends HttpServlet {
    /**
     * Maneja solicitudes HTTP GET para "/student.json".
     * Devuelve una representación en JSON de una lista de estudiantes.
     *
     * @param req  la solicitud HTTP
     * @param resp la respuesta HTTP
     * @throws ServletException si hay un error procesando la solicitud
     * @throws IOException      si hay un error escribiendo en la respuesta
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Verificar la autenticación basada en la cookie
        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);

        if (!cookieOptional.isPresent()) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Debe iniciar sesión para acceder a esta página");
            return;
        }
        // Crea una instancia del servicio StudentService
        StudentService service = new StudentServiceImpl();

        // Obtiene una lista de estudiantes desde el servicio StudentService
        List<StudentDto> students = service.listar();

        // Crea un objeto Mapper de ObjectMapper para convertir la lista de estudiantes a JSON
        ObjectMapper mapper = new ObjectMapper();

        // Convierte la lista de estudiantes a JSON
        String json = mapper.writeValueAsString(students);

        // Establece el tipo de contenido de la respuesta a "application/json"
        resp.setContentType("application/json");

        // Escribe la representación en JSON de los estudiantes en la respuesta
        resp.getWriter().write(json);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Verifica la autenticación basada en la cookie
        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);

        if (!cookieOptional.isPresent()) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Debe iniciar sesión para acceder a esta página");
            return;
        }

        // Obtiene la representación en JSON del estudiante del cuerpo de la solicitud
        ServletInputStream jsonStream = req.getInputStream();

        // Crea un objeto Mapper de ObjectMapper para convertir la representación en JSON del estudiante a un objeto Student
        ObjectMapper mapper = new ObjectMapper();

        // Convierte la representación en JSON del estudiante a un objeto Student
        Student st = mapper.readValue(jsonStream, Student.class);

        // Establece el tipo de contenido de la respuesta a "text/html;charset=UTF-8"
        resp.setContentType("text/html;charset=UTF-8");

        // Obtiene un PrintWriter para escribir la página HTML en la respuesta
        try (PrintWriter out = resp.getWriter()) {
            // Escribe la página HTML con detalles del estudiante recibido en la respuesta
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Detalle de producto desde Json</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Detalle de producto desde Json</h1>");
            if (cookieOptional.isPresent()) {
                out.println("<ul>");
                out.println("<li>Id:" + st.getId() + "</li>");
                out.println("<li>Nombre:" + st.getNombre() + "</li>");
                out.println("<li>Correo:" + st.getEmail() + "</li>");
                out.println("<li>Semestre: " + st.getSemestre() + "</li>");
                out.println("</ul>");
            }
            out.println(" </body>");
            out.println("</html>");
        }
    }
}
