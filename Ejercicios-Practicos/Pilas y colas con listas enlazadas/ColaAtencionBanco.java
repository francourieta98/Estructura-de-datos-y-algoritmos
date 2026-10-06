import java.util.Scanner;

public class ColaAtencionBanco {
    private static class Cliente {
        private final String nombre;
        private final int numeroTurno;
        private final String motivoConsulta;

        private Cliente(String nombre, int numeroTurno, String motivoConsulta) {
            this.nombre = nombre;
            this.numeroTurno = numeroTurno;
            this.motivoConsulta = motivoConsulta;
        }

        @Override
        public String toString() {
            return "Turno " + numeroTurno + " - " + nombre + " (Motivo: " + motivoConsulta + ")";
        }
    }

    private static class Nodo {
        private final Cliente cliente;
        private Nodo siguiente;

        private Nodo(Cliente cliente) {
            this.cliente = cliente;
        }
    }

    private static class ColaClientes {
        private Nodo head;
        private Nodo tail;

        private void encolar(Cliente cliente) {
            Nodo nuevo = new Nodo(cliente);
            if (head == null) {
                head = nuevo;
                tail = nuevo;
            } else {
                tail.siguiente = nuevo;
                tail = nuevo;
            }
        }

        private Cliente desencolar() {
            if (head == null) {
                return null;
            }

            Cliente cliente = head.cliente;
            head = head.siguiente;
            if (head == null) {
                tail = null;
            }
            return cliente;
        }

        private Cliente consultarSiguiente() {
            return head == null ? null : head.cliente;
        }

        private boolean estaVacia() {
            return head == null;
        }

        private void imprimir() {
            if (estaVacia()) {
                System.out.println("No hay clientes en la fila.");
                return;
            }

            Nodo actual = head;
            System.out.println("Fila (primero en atenderse al inicio):");
            while (actual != null) {
                System.out.println("- " + actual.cliente);
                actual = actual.siguiente;
            }
        }
    }

    public static void main(String[] args) {
        ColaClientes fila = new ColaClientes();
        Scanner entrada = new Scanner(System.in);
        int siguienteTurno = 1;
        boolean continuar = true;

        System.out.println("La cola es adecuada porque el banco atiende en orden de llegada (FIFO).");
        System.out.println("Cada cliente nuevo se agrega al final y se atiende primero al que esta al frente.");
        System.out.println("head marca a quien sigue y tail permite agregar al final sin cambiar ese orden.");
        System.out.println();

        while (continuar) {
            mostrarMenu();
            String opcion = entrada.nextLine().trim();

            switch (opcion) {
                case "1":
                    Cliente agregado = agregarCliente(entrada, fila, siguienteTurno);
                    if (agregado != null) {
                        siguienteTurno++;
                        System.out.println("Cliente agregado: " + agregado);
                    }
                    break;
                case "2":
                    Cliente atendido = fila.desencolar();
                    if (atendido == null) {
                        System.out.println("No hay clientes para atender.");
                    } else {
                        System.out.println("Atendiendo a: " + atendido);
                    }
                    break;
                case "3":
                    Cliente siguiente = fila.consultarSiguiente();
                    if (siguiente == null) {
                        System.out.println("No hay clientes esperando.");
                    } else {
                        System.out.println("Siguiente cliente: " + siguiente);
                    }
                    break;
                case "4":
                    fila.imprimir();
                    break;
                case "5":
                    continuar = false;
                    System.out.println("Sistema de atencion finalizado.");
                    break;
                default:
                    System.out.println("Opcion no valida. Elige del 1 al 5.");
            }
            System.out.println();
        }

        entrada.close();
    }

    private static Cliente agregarCliente(Scanner entrada, ColaClientes fila, int numeroTurno) {
        System.out.print("Nombre del cliente: ");
        String nombre = entrada.nextLine().trim();
        System.out.print("Motivo de consulta: ");
        String motivo = entrada.nextLine().trim();

        if (nombre.isEmpty() || motivo.isEmpty()) {
            System.out.println("El nombre y el motivo de consulta son obligatorios.");
            return null;
        }

        Cliente cliente = new Cliente(nombre, numeroTurno, motivo);
        fila.encolar(cliente);
        return cliente;
    }

    private static void mostrarMenu() {
        System.out.println("1. Agregar cliente a la fila");
        System.out.println("2. Atender al proximo cliente");
        System.out.println("3. Consultar quien sigue");
        System.out.println("4. Imprimir fila actual");
        System.out.println("5. Salir");
        System.out.print("Elige una opcion: ");
    }
}
