public class Ejercicio4InsertarEnPosicion {
    private static class Nodo {
        private final int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
        }
    }

    private Nodo head;
    private int size;

    public void insertarEnPosicion(int dato, int posicion) {
        if (posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException("Posicion fuera de los limites: " + posicion);
        }

        Nodo nuevo = new Nodo(dato);
        if (posicion == 0) {
            nuevo.siguiente = head;
            head = nuevo;
        } else {
            Nodo actual = head;
            for (int indice = 0; indice < posicion - 1; indice++) {
                actual = actual.siguiente;
            }

            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        size++;
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
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Se permite insertar en posiciones de 0 a size inclusive.");
        System.out.println("Posicion 0 inserta al inicio; posicion size inserta al final.");
        System.out.println("Para insertar despues de actual, primero se conserva la continuacion:");
        System.out.println("nuevo.setSiguiente(actual.getSiguiente());");
        System.out.println("actual.setSiguiente(nuevo);");
        System.out.println("Si se invierte el orden sin guardar el sucesor, se puede perder el resto");
        System.out.println("de la lista o crear un ciclo.");
        System.out.println();

        Ejercicio4InsertarEnPosicion lista = new Ejercicio4InsertarEnPosicion();
        lista.insertarEnPosicion(20, 0);
        lista.insertarEnPosicion(10, 0);
        lista.insertarEnPosicion(30, 2);
        lista.insertarEnPosicion(40, 3);
        System.out.print("Lista con inserciones al inicio, en medio y al final: ");
        lista.imprimir();
        System.out.println("Tamano: " + lista.size);

        try {
            lista.insertarEnPosicion(99, -1);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println(excepcion.getMessage());
        }
        try {
            lista.insertarEnPosicion(99, lista.size + 1);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }
}
