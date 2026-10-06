public class Ejercicio5EliminarPorValor {
    private static class Nodo {
        private final int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
        }
    }

    private Nodo head;
    private int size;

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
        size++;
    }

    public boolean eliminar(int dato) {
        if (head == null) {
            return false;
        }

        if (head.dato == dato) {
            head = head.siguiente;
            size--;
            return true;
        }

        Nodo anterior = head;
        Nodo actual = head.siguiente;
        while (actual != null) {
            if (actual.dato == dato) {
                anterior.siguiente = actual.siguiente;
                size--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }

        return false;
    }

    private void imprimir() {
        if (head == null) {
            System.out.println("La lista esta vacia.");
            return;
        }

        Nodo actual = head;
        while (actual != null) {
            System.out.print(actual.dato);
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
            actual = actual.siguiente;
        }
        System.out.println();
    }

    private void mostrarEliminacion(int dato) {
        System.out.println("Eliminar " + dato + ": " + eliminar(dato));
        System.out.print("Lista: ");
        imprimir();
        System.out.println("Tamano: " + size);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("En Java no se libera un nodo manualmente.");
        System.out.println("Al actualizar las referencias para desconectarlo, queda inaccesible");
        System.out.println("y el Garbage Collector puede recuperarlo cuando corresponda.");
        System.out.println("eliminar(dato) quita solo la primera aparicion.");
        System.out.println();

        Ejercicio5EliminarPorValor listaVacia = new Ejercicio5EliminarPorValor();
        System.out.println("Lista vacia - eliminar 10: " + listaVacia.eliminar(10));
        System.out.println();

        Ejercicio5EliminarPorValor lista = new Ejercicio5EliminarPorValor();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        System.out.print("Lista inicial: ");
        lista.imprimir();
        System.out.println();

        lista.mostrarEliminacion(10);
        lista.mostrarEliminacion(30);
        lista.mostrarEliminacion(40);
        lista.mostrarEliminacion(99);
        lista.mostrarEliminacion(20);
        lista.mostrarEliminacion(10);
    }
}
