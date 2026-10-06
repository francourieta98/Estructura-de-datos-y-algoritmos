public class Ejercicio6EliminarPorPosicion {
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

    public void eliminarEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posicion fuera de los limites: " + posicion);
        }

        if (posicion == 0) {
            head = head.siguiente;
        } else {
            Nodo anterior = head;
            for (int indice = 0; indice < posicion - 1; indice++) {
                anterior = anterior.siguiente;
            }
            Nodo eliminado = anterior.siguiente;
            anterior.siguiente = eliminado.siguiente;
        }

        size--;
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

    private void mostrarEstado() {
        System.out.print("Lista: ");
        imprimir();
        System.out.println("Tamano: " + size);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Para eliminar una posicion intermedia, se recorre hasta el nodo anterior.");
        System.out.println("Luego anterior.siguiente apunta a eliminado.siguiente, saltando el nodo eliminado.");
        System.out.println("Al quitar la posicion 0 se actualiza head; cada eliminacion valida decrementa size.");
        System.out.println();

        Ejercicio6EliminarPorPosicion lista = new Ejercicio6EliminarPorPosicion();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        System.out.print("Lista inicial: ");
        lista.imprimir();
        System.out.println("Tamano: " + lista.size);
        System.out.println();

        lista.eliminarEnPosicion(0);
        System.out.println("Despues de eliminar la posicion 0 (inicio):");
        lista.mostrarEstado();

        lista.eliminarEnPosicion(1);
        System.out.println("Despues de eliminar la posicion 1 (medio):");
        lista.mostrarEstado();

        lista.eliminarEnPosicion(lista.size - 1);
        System.out.println("Despues de eliminar la ultima posicion:");
        lista.mostrarEstado();

        lista.eliminarEnPosicion(0);
        System.out.println("Despues de eliminar el unico nodo:");
        lista.mostrarEstado();

        try {
            lista.eliminarEnPosicion(0);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println("Lista vacia: " + excepcion.getMessage());
        }

        lista.insertarAlFinal(50);
        try {
            lista.eliminarEnPosicion(-1);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println(excepcion.getMessage());
        }
        try {
            lista.eliminarEnPosicion(lista.size);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }
}
