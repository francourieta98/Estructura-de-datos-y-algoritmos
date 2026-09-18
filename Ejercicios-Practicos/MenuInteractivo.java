import java.util.Scanner;

public class MenuInteractivo {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("=== Menu interactivo ===");
        System.out.println("1. Saludar");
        System.out.println("2. Mostrar un mensaje motivador");
        System.out.println("3. Mostrar el lenguaje del curso");
        System.out.println("4. Salir");
        System.out.print("Elige una opcion: ");
        int opcion = teclado.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Hola, bienvenido al programa.");
                break;
            case 2:
                System.out.println("Cada ejercicio te ayuda a aprender Java.");
                break;
            case 3:
                System.out.println("El lenguaje del curso es Java.");
                break;
            case 4:
                System.out.println("Programa finalizado.");
                break;
            default:
                System.out.println("Opcion invalida. Debes elegir entre 1 y 4.");
        }

        teclado.close();
    }
}