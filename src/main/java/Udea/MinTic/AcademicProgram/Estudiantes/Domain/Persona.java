package Udea.MinTic.AcademicProgram.Estudiantes.Domain;

import lombok.Data;
import lombok.Getter;//dependencia lombok
import lombok.Setter;//dependencia lombok

@Data public class Persona { //@Data hace las veces de Getter ySetter para todos los atributos
    // en caso de no querer encapsular alguno si debo usar uno a uno
    private String nombre;
    private String apellido;
    private int edad;
    private int id;//para poder identificar una persona
    private String doc;
}
