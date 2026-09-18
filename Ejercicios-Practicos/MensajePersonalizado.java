import java.util.Scanner;

public class MensajePersonalizado {
    public static void main(String[] args) {
        // Scanner permite leer datos que el usuario escribe en la consola.
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingresa tu nombre: ");
        String nombre = teclado.nextLine().trim();

        System.out.print("Ingresa tu edad: ");
        int edad = teclado.nextInt();

        System.out.println("Hola, " + nombre + "!");
        System.out.println("Tienes " + edad + " anos.");
        System.out.println("Bienvenido al programa, " + nombre + ".");

        teclado.close();
    }
}