package Udea.MinTic.AcademicProgram.Estudiantes.Services;



import Udea.MinTic.AcademicProgram.Estudiantes.Domain.Persona;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service //le entrego datos para que service los procese
public class ServicePersona {

    @Getter
    @Setter
    private String nombrePrograma;


    //metodo
    public String incribirAlumno(Persona alumno) {// 1.le entrego al servicio un objeto de tipo "Persona"...
        String inscripcion = "El alumno" + alumno.getNombre() + " " + alumno.getApellido() + " " + alumno.getEdad() + "años, quedó inscrito al programa";//1.a
        return inscripcion;// // 3.aquí esta usando la instancia de tipo Persona con datos  y 3.a retorna a controller con el metodo incribiralumno valores con datos
    }

    //ejemplo de trazabilidad o primera vez (controlar tareas de primera ejecucion)
    public void doWhile1(int x) {
        do {
            System.out.println("holi" + x);
            x++;
            //ejecuta siempre y cuando se cumpla, siempre hace un ciclo
        } while (x < 10); //lo llevo al controller

    }


    //ejemplo de trazabilidad2 o primera vez, debo crear una lista para llenarla y subirla al browser
    public ArrayList doWhile(int a) {
        ArrayList<String> objTraza = new ArrayList();
        do {
            System.out.println("hola mundo" + a); //para consola
            objTraza.add("hola mundo" + String.valueOf(a)); //para browser
            a++;
            //ejecuta siempre y cuando se cumpla, siempre hace un ciclo
        } while (a < 10); //lo llevo al controller
        return objTraza;
    }
}