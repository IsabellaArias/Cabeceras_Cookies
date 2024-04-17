package Servlet;

import Mapping.dtos.StudentDto;
import Model.Student;
import Services.StudentService;
import Services.impl.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/student.json"})
public class StudentJson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        StudentService service= new StudentServiceImpl();
        List<StudentDto> students = service.listar();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(students);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws
            ServletException, IOException {
        ServletInputStream jsonStream= req.getInputStream();
        ObjectMapper mapper=new ObjectMapper();
        Student st =mapper.readValue(jsonStream,Student.class);
        resp.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out=resp.getWriter()){
            out.println("<!DOCTYPEhtml>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <metacharset=\"UTF-8\">");
            out.println(" <title>Detalle de producto desde Json</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Detalle de producto desde Json</h1>");
            out.println("<ul>");
            out.println("<li>Id:"+st.getId()+"</li>");
            out.println("<li>Nombre:"+st.getNombre()+"</li>");
            out.println("<li>Correo:"+st.getEmail()+"</li>");
            out.println("<li>Semestre: " + st.getSemestre() + "</li>");
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
}
