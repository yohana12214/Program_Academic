package Udea.MinTic.AcademicProgram.Estudiantes.Controller;

import Udea.MinTic.AcademicProgram.Estudiantes.Domain.Persona;
import Udea.MinTic.AcademicProgram.Estudiantes.Services.ServicePersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//@RestController se utiliza con frontend desacoplado, proyectos grandes
@RestController//lanza lo que tengo a browser
@RequestMapping(value = "/Persona") //Path primario o ruta primaria en todos los End Point de esta clase
public class ControllerPersona {

    //Para instanciar un atributo dentro de mi misma clase se hace con un constructor:


    @Autowired //enlaza el servicio con el controlador, en vez de constructor e instancia
    ServicePersona serviceProgramaAcademico;//instancia

    @GetMapping(path = "/udea/program/yopis") //para que sea accesible al web browser
    public String callServicePrograma() {
        Persona alumno = new Persona();// 2. genero una instancia de persona con objeto alumno, lo necesito para incribirAlumno
        alumno.setNombre("Yohana"); //2.a
        alumno.setApellido("Hurtado");//2.b
        alumno.setEdad(25);

        return serviceProgramaAcademico.incribirAlumno(alumno); //4. recibe  metodo incribiralumno y 5. finalmente esto es lo que recibe el we browser a traves de @getmapping
    }


    //ejemplo dowhile1
    @GetMapping(path = "/udea/doWhile1", produces = "application/json")
    public void doWhile1() {
        serviceProgramaAcademico.doWhile1(5);
        //explicación,
        // cuando ejecuto esta ruta "/udea/doWhile1" desde el explorador,
        // viene aquí, me envía el 5 a la función doWhile1 del service,
        // recibe, hace ciclo e imprime en consola y no en el browser

    }


    //ejemplo dowhile2
    @GetMapping(path = "/udea/doWhile", produces = "application/json")//habla con el navegador a traves del metodo get
    public ArrayList doWhile() {
        ArrayList<String> salida = new ArrayList<>();//creacion instancia del ArrayList
        salida = serviceProgramaAcademico.doWhile(7); //cargué ejecución al objeto salida

        //explicación, 2do ejemplo
        // cuando ejecuto esta ruta "/udea/doWhile" desde el explorador,
        // viene aquí, me envía el 7 a la función doWhile del service,
        //si quiero que imprima en browser debo hacer return en el controller
        // creo un Arraylist cambio el tipo,cada vez que imprime el objeto llena  el Arraylist
        //
        return salida; //devolví o retorne el objeto
    }
}

/**************************************************************************************************/
/*----------------Hasta aquí tengo 4 End Points en mi API----------------------------------------*/
// Función End Point: servir info hacia el request
// API: Aplication Program Interfaz, toda la función ArrayList es una API
// Mi API es una aplicación que presenta varias acciones
/* ::::::Mi API funciona como un servidor de recursos y funciones, metodos::::::
:::::::::Mi cliente es el Frontend, va a solicitar info de esa API a traves:::::
:::::::::deL protocolo HTTP para obtener esos recursos::::::::::::::::::::::::::*/
/***************************************************************************************************/

