public class Alumno {
    // Atributos privados: solo la clase puede acceder directamente a ellos.
    private String nombre;
    private int edad;

    // Constructor: inicializa los atributos al crear un objeto.
    public Alumno(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Metodo: define una accion que puede realizar un objeto Alumno.
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre + " | Edad: " + edad + " anos");
    }

    public static void main(String[] args) {
        // Instanciacion: creacion de tres objetos a partir de la clase Alumno.
        Alumno primerAlumno = new Alumno("Ana", 20);
        Alumno segundoAlumno = new Alumno("Bruno", 22);
        Alumno tercerAlumno = new Alumno("Carla", 19);

        System.out.println("Datos de los alumnos:");
        primerAlumno.mostrarDatos();
        segundoAlumno.mostrarDatos();
        tercerAlumno.mostrarDatos();
    }
}
