import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class HistorialNavegacion {
    public static void main(String[] args) {
        Deque<String> historial = new ArrayDeque<String>();
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("El historial usa LIFO: la ultima pagina visitada queda arriba de la pila.");
        System.out.println("Al volver, pop quita la pagina actual y permite regresar a la visita anterior.");
        System.out.println();

        while (continuar) {
            mostrarMenu();
            String opcion = entrada.nextLine().trim();

            switch (opcion) {
                case "1":
                    System.out.print("Ingresa la URL: ");
                    String url = entrada.nextLine().trim();
                    if (url.isEmpty()) {
                        System.out.println("La URL no puede estar vacia.");
                    } else {
                        historial.push(url);
                        System.out.println("Pagina visitada: " + url);
                    }
                    break;
                case "2":
                    if (historial.size() <= 1) {
                        System.out.println("No hay una pagina anterior en el historial.");
                    } else {
                        String paginaActual = historial.pop();
                        System.out.println("Volviendo desde: " + paginaActual);
                        System.out.println("Pagina anterior: " + historial.peek());
                    }
                    break;
                case "3":
                    if (historial.isEmpty()) {
                        System.out.println("Aun no se ha visitado ninguna pagina.");
                    } else {
                        System.out.println("Pagina actual: " + historial.peek());
                    }
                    break;
                case "4":
                    continuar = false;
                    System.out.println("Simulacion finalizada.");
                    break;
                default:
                    System.out.println("Opcion no valida. Elige del 1 al 4.");
            }
            System.out.println();
        }

        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("1. Visitar una pagina");
        System.out.println("2. Volver a la pagina anterior");
        System.out.println("3. Consultar la pagina actual");
        System.out.println("4. Salir");
        System.out.print("Elige una opcion: ");
    }
}
