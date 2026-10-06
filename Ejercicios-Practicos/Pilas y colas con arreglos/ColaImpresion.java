import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ColaImpresion {
    private static class Documento {
        private final String nombre;
        private final int paginas;

        private Documento(String nombre, int paginas) {
            this.nombre = nombre;
            this.paginas = paginas;
        }

        @Override
        public String toString() {
            return nombre + " (" + paginas + " paginas)";
        }
    }

    public static void main(String[] args) {
        Deque<Documento> cola = new ArrayDeque<Documento>();
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("La cola de impresion usa FIFO: el primer documento agregado es el primero que se imprime.");
        System.out.println("Asi se respeta el orden de llegada y ningun documento nuevo adelanta a los que esperan.");
        System.out.println();

        while (continuar) {
            mostrarMenu();
            String opcion = entrada.nextLine().trim();

            switch (opcion) {
                case "1":
                    agregarDocumento(entrada, cola);
                    break;
                case "2":
                    if (cola.isEmpty()) {
                        System.out.println("No hay documentos pendientes de impresion.");
                    } else {
                        Documento documento = cola.removeFirst();
                        System.out.println("Imprimiendo: " + documento);
                    }
                    break;
                case "3":
                    if (cola.isEmpty()) {
                        System.out.println("No hay documentos pendientes de impresion.");
                    } else {
                        System.out.println("Siguiente documento: " + cola.peekFirst());
                    }
                    break;
                case "4":
                    continuar = false;
                    System.out.println("Cola de impresion finalizada.");
                    break;
                default:
                    System.out.println("Opcion no valida. Elige del 1 al 4.");
            }
            System.out.println();
        }

        entrada.close();
    }

    private static void agregarDocumento(Scanner entrada, Deque<Documento> cola) {
        System.out.print("Nombre del documento: ");
        String nombre = entrada.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            return;
        }

        System.out.print("Cantidad de paginas: ");
        String paginasIngresadas = entrada.nextLine().trim();
        int paginas;
        try {
            paginas = Integer.parseInt(paginasIngresadas);
        } catch (NumberFormatException excepcion) {
            System.out.println("La cantidad de paginas debe ser un numero entero.");
            return;
        }

        if (paginas <= 0) {
            System.out.println("La cantidad de paginas debe ser mayor que cero.");
            return;
        }

        Documento documento = new Documento(nombre, paginas);
        cola.addLast(documento);
        System.out.println("Documento agregado a la cola: " + documento);
    }

    private static void mostrarMenu() {
        System.out.println("1. Agregar documento");
        System.out.println("2. Imprimir siguiente documento");
        System.out.println("3. Consultar siguiente documento");
        System.out.println("4. Salir");
        System.out.print("Elige una opcion: ");
    }
}
