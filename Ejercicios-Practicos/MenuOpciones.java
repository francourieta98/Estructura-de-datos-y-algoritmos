import java.util.Scanner;

public class MenuOpciones {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Menu de opciones: el usuario elige una alternativa ingresando un numero.
        System.out.println("=== Menu de opciones ===");
        System.out.println("1. Saludar");
        System.out.println("2. Mostrar un mensaje");
        System.out.println("3. Mostrar el lenguaje del curso");
        System.out.println("4. Salir");
        System.out.print("Elige una opcion: ");
        int opcion = teclado.nextInt();

        // switch es una estructura de control que compara el valor de opcion.
        switch (opcion) {
            case 1:
                // case define una accion para una opcion especifica.
                System.out.println("Hola, bienvenido al programa.");
                break; // Finaliza este case y evita ejecutar los siguientes.
            case 2:
                System.out.println("Estas aprendiendo estructuras de control.");
                break;
            case 3:
                System.out.println("El lenguaje del curso es Java.");
                break;
            case 4:
                System.out.println("Programa finalizado.");
                break;
            default:
                // default se ejecuta cuando no coincide ningun case.
                System.out.println("Opcion invalida. Debes elegir entre 1 y 4.");
        }

        teclado.close();
    }
}
