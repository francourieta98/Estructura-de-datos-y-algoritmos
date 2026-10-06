import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SimuladorTorrePlatos {
    public static void main(String[] args) {
        Deque<String> torre = new ArrayDeque<String>();
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("Una torre de platos funciona como una pila: el ultimo plato que se coloca");
        System.out.println("queda arriba y es el primero que se puede retirar (LIFO).");
        System.out.println("Una cola retiraria primero el plato que se coloco al fondo (FIFO),");
        System.out.println("lo cual no representa como se apilan los platos.");

        while (continuar) {
            mostrarMenu();
            String opcion = entrada.nextLine().trim();

            switch (opcion) {
                case "1":
                    System.out.print("Nombre o numero del plato: ");
                    String plato = entrada.nextLine().trim();
                    if (plato.isEmpty()) {
                        System.out.println("El plato no puede estar vacio.");
                    } else {
                        torre.push(plato);
                        System.out.println("Plato agregado arriba: " + plato);
                    }
                    break;
                case "2":
                    if (torre.isEmpty()) {
                        System.out.println("La torre esta vacia; no hay platos para retirar.");
                    } else {
                        System.out.println("Plato retirado: " + torre.pop());
                    }
                    break;
                case "3":
                    if (torre.isEmpty()) {
                        System.out.println("La torre esta vacia; no hay un plato arriba.");
                    } else {
                        System.out.println("Plato de arriba: " + torre.peek());
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
        System.out.println("1. Agregar un plato");
        System.out.println("2. Retirar el plato de arriba");
        System.out.println("3. Consultar el plato de arriba");
        System.out.println("4. Salir");
        System.out.print("Elige una opcion: ");
    }
}
