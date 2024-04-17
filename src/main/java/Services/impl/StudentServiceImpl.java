package Services.impl;

import Mapping.dtos.StudentDto;
import Mapping.mapper.StudentMapper;
import Model.Student;
import Services.StudentService;

import java.util.Arrays;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private List<Student> list= Arrays.asList(
            new Student(1, "Juan Perez","jperez@example.com","2"),
            new Student(2, "Maria Gonzalez","mgonzalez@example.com","3"),
            new Student(3, "Luis Rodriguez","lrodriguez@example.com","4"),
            new Student(4, "Ana Sanchez","asanchez@example.com","5"),
            new Student(5, "Carlos Martinez","cmartinez@example.com","6")
    );

    @Override
    public  List<StudentDto> listar() {
        return list.stream().map(StudentMapper::mapFrom).toList();
    }
}