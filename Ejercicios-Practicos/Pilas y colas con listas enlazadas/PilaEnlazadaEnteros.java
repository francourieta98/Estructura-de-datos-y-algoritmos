import java.util.NoSuchElementException;

public class PilaEnlazadaEnteros {
    private static class Nodo {
        private final int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
        }
    }

    private Nodo head;

    public void apilar(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = head;
        head = nuevo;
    }

    public int desapilar() {
        if (estaVacia()) {
            throw new NoSuchElementException("No se puede desapilar: la pila esta vacia.");
        }

        int dato = head.dato;
        head = head.siguiente;
        return dato;
    }

    public int consultarTope() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay elementos en el tope: la pila esta vacia.");
        }

        return head.dato;
    }

    public boolean estaVacia() {
        return head == null;
    }

    public boolean contiene(int dato) {
        Nodo actual = head;
        while (actual != null) {
            if (actual.dato == dato) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void imprimir() {
        if (estaVacia()) {
            System.out.println("La pila esta vacia.");
            return;
        }

        Nodo actual = head;
        System.out.print("Tope -> ");
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
        System.out.println("head representa el tope: el ultimo nodo agregado es el primero que se retira (LIFO).");
        System.out.println("Apilar inserta directamente delante de head y desapilar mueve head al siguiente nodo.");
        System.out.println("Ambas operaciones solo cambian unas pocas referencias, por eso son O(1).");
        System.out.println("Buscar e imprimir recorren los nodos y pueden tardar O(n).");
        System.out.println();

        PilaEnlazadaEnteros pila = new PilaEnlazadaEnteros();
        System.out.println("Esta vacia? " + pila.estaVacia());
        pila.imprimir();

        pila.apilar(10);
        pila.apilar(20);
        pila.apilar(30);
        pila.imprimir();
        System.out.println("Tope actual: " + pila.consultarTope());
        System.out.println("Contiene 20? " + pila.contiene(20));
        System.out.println("Contiene 99? " + pila.contiene(99));
        System.out.println("Desapilado: " + pila.desapilar());
        pila.imprimir();
        System.out.println("Desapilado: " + pila.desapilar());
        System.out.println("Desapilado: " + pila.desapilar());
        System.out.println("Esta vacia? " + pila.estaVacia());

        try {
            pila.desapilar();
        } catch (NoSuchElementException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }
}
