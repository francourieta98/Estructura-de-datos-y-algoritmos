import java.util.NoSuchElementException;

public class ColaEnlazadaEnteros {
    private static class Nodo {
        private final int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
        }
    }

    private Nodo head;
    private Nodo tail;

    public void encolar(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (estaVacia()) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.siguiente = nuevo;
            tail = nuevo;
        }
    }

    public int desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("No se puede desencolar: la cola esta vacia.");
        }

        int dato = head.dato;
        head = head.siguiente;
        if (head == null) {
            tail = null;
        }
        return dato;
    }

    public int consultarFrente() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay frente: la cola esta vacia.");
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
            System.out.println("La cola esta vacia.");
            return;
        }

        Nodo actual = head;
        System.out.print("Frente -> ");
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
        System.out.println("head apunta al frente, que es el siguiente elemento en salir.");
        System.out.println("tail apunta al final, donde se agrega cada nuevo elemento.");
        System.out.println("Con ambos extremos, encolar y desencolar se realizan en O(1).");
        System.out.println("Si solo se guardara head, para encolar habria que recorrer toda la lista");
        System.out.println("hasta el ultimo nodo, haciendo cada encolado O(n).");
        System.out.println("Al desencolar el ultimo elemento, head y tail deben quedar en null.");
        System.out.println();

        ColaEnlazadaEnteros cola = new ColaEnlazadaEnteros();
        System.out.println("Esta vacia? " + cola.estaVacia());
        cola.imprimir();

        cola.encolar(10);
        cola.encolar(20);
        cola.encolar(30);
        cola.imprimir();
        System.out.println("Frente: " + cola.consultarFrente());
        System.out.println("Contiene 20? " + cola.contiene(20));
        System.out.println("Contiene 99? " + cola.contiene(99));

        while (!cola.estaVacia()) {
            System.out.println("Desencolado: " + cola.desencolar());
        }
        System.out.println("Esta vacia? " + cola.estaVacia());

        cola.encolar(40);
        System.out.println("Frente despues de encolar en cola vacia: " + cola.consultarFrente());
        cola.imprimir();
    }
}
