package Mapping.mapper;

import Mapping.dtos.StudentDto;
import Model.Student;

public class StudentMapper {
    // Crea un nuevo objeto Student utilizando los valores del objeto StudentDto
    public static Student mapFrom(StudentDto studentDto ){
        return new Student(studentDto.id(), studentDto.name(), studentDto.email(), studentDto.semestre());}
    // Crea un nuevo objeto StudentDto utilizando los valores del objeto Student
    public static StudentDto mapFrom(Student student){return  new StudentDto(
            student.getId(),
            student.getNombre(),
            student.getEmail(),
            student.getSemestre()
    );
    }
}