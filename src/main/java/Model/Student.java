package Model;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class Student {
    private Integer id;
    private String nombre;
    private String email;
    private String semestre;
}