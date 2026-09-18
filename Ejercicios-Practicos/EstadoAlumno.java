import java.util.Scanner;

public class EstadoAlumno {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingresa la nota del alumno (0 a 10): ");
        double nota = teclado.nextDouble();

        if (nota < 0 || nota > 10) {
            System.out.println("Error: la nota debe estar entre 0 y 10.");
        } else if (nota >= 8) {
            System.out.println("El alumno promociono.");
        } else if (nota >= 6) {
            System.out.println("El alumno aprobo.");
        } else {
            System.out.println("El alumno desaprobo.");
        }

        teclado.close();
    }
}