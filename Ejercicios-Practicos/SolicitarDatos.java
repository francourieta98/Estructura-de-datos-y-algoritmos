import java.util.Scanner;

public class SolicitarDatos {
    public static void main(String[] args) {
        // Creacion de un objeto Scanner para leer la entrada por teclado.
        Scanner teclado = new Scanner(System.in);

        // nextLine() lee una linea completa de texto.
        System.out.print("Ingresa tu nombre: ");
        String nombre = teclado.nextLine();

        // nextInt() lee un numero entero ingresado por teclado.
        System.out.print("Ingresa tu edad: ");
        int edad = teclado.nextInt();

        // Salida por consola: muestra un mensaje en la pantalla.
        System.out.println("Hola, " + nombre);
        System.out.println("Tu edad es: " + edad + " anos.");

        teclado.close();
    }
}
