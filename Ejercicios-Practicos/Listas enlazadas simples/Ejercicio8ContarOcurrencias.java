public class Ejercicio8ContarOcurrencias {
    private static class Nodo {
        private final int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
        }
    }

    private Nodo head;

    private void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (head == null) {
            head = nuevo;
        } else {
            Nodo actual = head;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public int contarOcurrencias(int dato) {
        int cantidad = 0;
        Nodo actual = head;
        while (actual != null) {
            if (actual.dato == dato) {
                cantidad++;
            }
            actual = actual.siguiente;
        }
        return cantidad;
    }

    private void imprimir() {
        Nodo actual = head;
        while (actual != null) {
            System.out.print(actual.dato);
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
            actual = actual.siguiente;
        }
        System.out.println(" -> null");
    }

    public static void main(String[] args) {
        System.out.println("Para contar todas las apariciones, el recorrido debe llegar hasta null.");
        System.out.println("No se debe detener en la primera coincidencia porque puede haber mas nodos");
        System.out.println("con el mismo valor despues de ella.");
        System.out.println();

        Ejercicio8ContarOcurrencias lista = new Ejercicio8ContarOcurrencias();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(10);

        System.out.print("Lista: ");
        lista.imprimir();
        System.out.println("Ocurrencias de 10: " + lista.contarOcurrencias(10));
        System.out.println("Ocurrencias de 99: " + lista.contarOcurrencias(99));
    }
}
