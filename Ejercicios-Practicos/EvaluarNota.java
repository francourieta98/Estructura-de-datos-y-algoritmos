import java.util.Scanner;

public class EvaluarNota {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingresa la nota del alumno (0 a 10): ");
        double nota = teclado.nextDouble();

        // Validacion de rango usando operadores relacionales y la condicion logica &&.
        if (nota < 0 || nota > 10) {
            System.out.println("Error: la nota debe estar entre 0 y 10.");
        } else if (nota >= 8 && nota <= 10) {
            // >= y <= comparan la nota con los limites del rango de promocion.
            System.out.println("El alumno promociono.");
        } else if (nota >= 6 && nota < 8) {
            // La condicion indica que la nota esta dentro del rango de aprobacion.
            System.out.println("El alumno aprobo.");
        } else {
            // Si no se cumple ninguna condicion anterior, la nota es menor que 6.
            System.out.println("El alumno desaprobo.");
        }

        teclado.close();
    }
}
