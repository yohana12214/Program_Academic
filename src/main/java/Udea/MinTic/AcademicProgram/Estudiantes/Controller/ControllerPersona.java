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

        return serviceProgramaAcademico.incribirAlumno(alumno); //4. recibe metodo incribiralumno y 5.
        // Finalmente esto es lo que recibe el we browser a traves de @getmapping
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

        //explicación, 2° ejemplo
        // cuando ejecuto esta ruta "/udea/doWhile" desde el explorador,
        // viene aquí, me envía el 7 a la función doWhile del service,
        //si quiero que imprima en browser debo hacer return en el controller
        // creo un Arraylist cambio el tipo, cada vez que imprime el objeto llena el Arraylist
        //
        return salida; //devolví o retorne el objeto
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

//******************METODO EMULADO (sin persistencia) PARA LISTAR PERSONAS  "GET sin parámetros"*******************************
@GetMapping(path = "Udea/MinTic/ListarPersonas", produces = MediaType.APPLICATION_JSON_VALUE)
public ArrayList<Persona> listarPersonas() {//arreglo tipo persona
    //ArrayList<Persona> listaP=new ArrayList<>();//instancia de manera local, dentro del metodo, pero ya se hizo de manera gral a la clase
    System.out.println("ingreso al método listar personas");
    return serviceProgramaAcademico.listar(); //se delaró el array en service junto con el metodo para que retorne aquí un proceso
}

    //*************METODO EMULADO (sin persistencia) PARA INSERTAR PERSONAS "POST" sin parámetros*******************************
    //ResponseEntity facilita el manejo con objetos, para adjudicar los estatus
    //este metodo recibe lo que viene del Request, ver RequestBody

    @PostMapping(path = "/Udea/MinTic/CrearPersona", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
// procesa y entrega info de tipo APLIC_JSON
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) { //va a recibir como parametro un objeo persona que viene del Request Body

        //recibe de service y lo guarda en salida
        boolean salida = serviceProgramaAcademico.addPersona(persona);
        if (salida == true) { //para aplicar códigos de status, retorna un 200
            System.out.println("ingresa al controlador crear personas 200");
            return new ResponseEntity<Persona>(persona, HttpStatus.OK);//Parametros: entidad Persona y estado
            //disparo peticion en el postman, almacena datos en lista y me da un 200 ok

        } else {
            System.out.println("ingresa al controlador crear personas 500");
            return new ResponseEntity("Error de Ejecución", HttpStatus.INTERNAL_SERVER_ERROR);//manipula el error retorna un 500
        }

        /* Explicacion
         * 1. linea87 ejecuta a traves del Service, lo que captura de "persona" línea 84, se lo envio a "persona" línea 87
         * 2. el service lo procesa, lo manipula me entrega una respuesta "salida" línea 88
         * 3. con esa rta hago una evaluacion, si la rta es true me devuelve un 200 ok y la entidad "persona", línea 89
         * 4. si la salida es falsa, no devuelva nada y genere un error 500 y ponga un mensajito, línea 93
         * 5. ya tenemos CREAR PERSONA hicimos un cableado a traves del controller, ir a un service, este procesa,
         * 6. me entrega rta y controller toma una decision
         * */

    }

    //******************METODO EMULADO (sin persistencia) PARA BUSCAR PERSONAS  "GET con parámetros"*******************************

    //comiunicación web solo lecturacon 2 parámetros, MediaType es homólogo a JEISON, como es get no necesita consume
    //a traves del parámetro hago el flujo lógico buesco y encuentro
    @GetMapping(path = "Udea/MinTic/BuscarPersona/{id}/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> buscarPersonas(@PathVariable int id, @PathVariable String nombre) {

        Persona p = serviceProgramaAcademico.buscarPersona(id);
        if (p != null) {
            System.out.println("ingresa al controlador buscar personas 200");
            return new ResponseEntity<Persona>(p, HttpStatus.OK);

        } else {
            System.out.println("ingresa al controlador buscar personas 500");
            return new ResponseEntity("Error de Ejecución", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //******************METODO EMULADO (sin persistencia) PARA CREAR PERSONAS  "POST con parámetros"*******************************

    @PostMapping(path="/Udea/MinTic/CrearPersona/{doc}",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)// procesa y entrega info de tipo APLIC_JSON
    public ResponseEntity <Persona> crearPersonaCondicional (@RequestBody Persona persona,@PathVariable String doc) { //Crea una persona peeeero dependiendo del documento ingresado se envía a un lado u otro

        //flujo de control switch case
        switch(doc){
            case "CC":
                serviceProgramaAcademico.addPersonaCC(persona,doc);
                break;
            case"TI":
                serviceProgramaAcademico.addPersonaTI(persona,doc);
                break;
            default:
                return new ResponseEntity("Error de Ejecución",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println("ingresa al controlador crear personas con documento");
        return new ResponseEntity<Persona>(persona ,HttpStatus.OK);
    }

    /*------   NEW-----metodo emulado (sin persistencia) para actualizar personas "Put con parámetros"----------------------------
     *Formas de enviar Datos :
     * @RequestBody: requiere un body tipo JSON, uso: POST
     * @PathVariable o @path Param: se le debe adicionar el parámetro al path, uso:GET y POST...
     * @RequestParam:se le adiciona al path y &
     -*/
    @PutMapping(path = "/Udea/MinTic/ActualizarPersona", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> actualizarPersona(@RequestParam int id, @RequestParam String nombreModificado) {
        Persona p = serviceProgramaAcademico.buscarPersona(id);
        p.setNombre(nombreModificado);
        System.out.println("metodo put");
        return new ResponseEntity<Persona>(p, HttpStatus.OK);
    }

    /*------   NEW-----metodo emulado (sin persistencia) para actualizar persona parcial  "PATCH con parámetros"----------------------------*/
    @PatchMapping(path = "/Udea/MinTic/ActualizarPP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizarPersonaParcial() {
        String retorno = "Actualizacion parcial de dominio  Persona";
        //aquí debe haber una implementación, por ahora así
        System.out.println("ok, metodo patch");
        return new ResponseEntity<String>(retorno, HttpStatus.OK);

        /*Explicacion
         * ResponseE tipo String
         *
         * */
    }

    @DeleteMapping(path = "/Udea/MinTic/BorrarPersona/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> borrarPersona(@PathVariable int id) {
        Persona p = serviceProgramaAcademico.buscarPersona(id);
        Boolean salida = serviceProgramaAcademico.borrarPersona(p);
        System.out.println("ok, metodo Delete");
        return new ResponseEntity<Boolean>(salida,HttpStatus.OK);

        /*Explicacion
         * para borrar persona necesito buscarla por id con @pathVariable id
         * llamo de nuevo a la función que construí para buscar persona
         * ahora función de borrado se la asigno a salida que es de tipo Boolean, y retorno
         * */

    }


}