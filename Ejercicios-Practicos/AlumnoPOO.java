class AlumnoEjemplo {
    private String nombre;
    private int edad;

    public AlumnoEjemplo(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre + " | Edad: " + edad + " anos");
    }
}

public class AlumnoPOO {
    public static void main(String[] args) {
        AlumnoEjemplo primerAlumno = new AlumnoEjemplo("Ana", 20);
        AlumnoEjemplo segundoAlumno = new AlumnoEjemplo("Bruno", 22);
        AlumnoEjemplo tercerAlumno = new AlumnoEjemplo("Carla", 19);

        System.out.println("Datos de los alumnos:");
        primerAlumno.mostrarDatos();
        segundoAlumno.mostrarDatos();
        tercerAlumno.mostrarDatos();
    }
}