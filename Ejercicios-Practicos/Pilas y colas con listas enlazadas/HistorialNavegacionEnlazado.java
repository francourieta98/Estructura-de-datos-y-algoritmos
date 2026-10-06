import java.util.NoSuchElementException;
import java.util.Scanner;

public class HistorialNavegacionEnlazado {
    private static class Nodo {
        private final String url;
        private Nodo siguiente;

        private Nodo(String url, Nodo siguiente) {
            this.url = url;
            this.siguiente = siguiente;
        }
    }

    private static class PilaHistorial {
        private Nodo head;

        private void apilar(String url) {
            head = new Nodo(url, head);
        }

        private String desapilar() {
            if (estaVacia()) {
                throw new NoSuchElementException("No hay paginas anteriores en el historial.");
            }

            String url = head.url;
            head = head.siguiente;
            return url;
        }

        private String consultarTope() {
            if (estaVacia()) {
                throw new NoSuchElementException("No hay una pagina actual.");
            }
            return head.url;
        }

        private boolean estaVacia() {
            return head == null;
        }

        private void imprimir() {
            if (estaVacia()) {
                System.out.println("El historial esta vacio.");
                return;
            }

            Nodo actual = head;
            System.out.println("Historial (pagina actual primero):");
            while (actual != null) {
                System.out.println("- " + actual.url);
                actual = actual.siguiente;
            }
        }
    }

    public static void main(String[] args) {
        PilaHistorial historial = new PilaHistorial();
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("El historial usa una pila porque volver atras deshace la visita mas reciente.");
        System.out.println("La ultima pagina visitada queda en el tope y es la primera que se quita (LIFO).");
        System.out.println();

        while (continuar) {
            mostrarMenu();
            String opcion = entrada.nextLine().trim();

            switch (opcion) {
                case "1":
                    System.out.print("URL de la pagina: ");
                    String url = entrada.nextLine().trim();
                    if (url.isEmpty()) {
                        System.out.println("La URL no puede estar vacia.");
                    } else {
                        historial.apilar(url);
                        System.out.println("Pagina visitada: " + url);
                    }
                    break;
                case "2":
                    try {
                        String paginaAnterior = historial.desapilar();
                        if (historial.estaVacia()) {
                            System.out.println("Se quito: " + paginaAnterior);
                            System.out.println("No quedan paginas en el historial.");
                        } else {
                            System.out.println("Volviendo desde: " + paginaAnterior);
                            System.out.println("Pagina actual: " + historial.consultarTope());
                        }
                    } catch (NoSuchElementException excepcion) {
                        System.out.println(excepcion.getMessage());
                    }
                    break;
                case "3":
                    try {
                        System.out.println("Pagina actual: " + historial.consultarTope());
                    } catch (NoSuchElementException excepcion) {
                        System.out.println(excepcion.getMessage());
                    }
                    break;
                case "4":
                    historial.imprimir();
                    break;
                case "5":
                    continuar = false;
                    System.out.println("Simulacion finalizada.");
                    break;
                default:
                    System.out.println("Opcion no valida. Elige del 1 al 5.");
            }
            System.out.println();
        }

        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("1. Visitar una pagina");
        System.out.println("2. Volver a la pagina anterior");
        System.out.println("3. Consultar pagina actual");
        System.out.println("4. Imprimir historial");
        System.out.println("5. Salir");
        System.out.print("Elige una opcion: ");
    }
}
