import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SistemaTurnos {
    private static class Persona {
        private final int numeroTurno;
        private final String nombre;

        private Persona(int numeroTurno, String nombre) {
            this.numeroTurno = numeroTurno;
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return "Turno " + numeroTurno + ": " + nombre;
        }
    }

    public static void main(String[] args) {
        Deque<Persona> cola = new ArrayDeque<Persona>();
        Scanner entrada = new Scanner(System.in);
        int siguienteTurno = 1;
        boolean continuar = true;

        System.out.println("Este sistema usa FIFO: la primera persona que llega es la primera en ser atendida.");
        System.out.println("Con LIFO, la ultima persona en llegar saldria primero y se alteraria el orden de espera.");
        System.out.println();

        while (continuar) {
            mostrarMenu();
            String opcion = entrada.nextLine().trim();

            switch (opcion) {
                case "1":
                    System.out.print("Nombre de la persona: ");
                    String nombre = entrada.nextLine().trim();
                    if (nombre.isEmpty()) {
                        System.out.println("El nombre no puede estar vacio.");
                    } else {
                        Persona persona = new Persona(siguienteTurno++, nombre);
                        cola.addLast(persona);
                        System.out.println("Turno asignado: " + persona);
                    }
                    break;
                case "2":
                    if (cola.isEmpty()) {
                        System.out.println("No hay personas esperando.");
                    } else {
                        System.out.println("Atendiendo a " + cola.removeFirst());
                    }
                    break;
                case "3":
                    if (cola.isEmpty()) {
                        System.out.println("No hay personas esperando.");
                    } else {
                        System.out.println("Siguiente persona: " + cola.peekFirst());
                    }
                    break;
                case "4":
                    continuar = false;
                    System.out.println("Sistema de turnos finalizado.");
                    break;
                default:
                    System.out.println("Opcion no valida. Elige del 1 al 4.");
            }
            System.out.println();
        }

        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("1. Agregar persona y asignar turno");
        System.out.println("2. Atender siguiente persona");
        System.out.println("3. Consultar quien esta primero");
        System.out.println("4. Salir");
        System.out.print("Elige una opcion: ");
    }
}
