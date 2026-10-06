public class ListaEnlazadaSimple {
    private static class Nodo {
        private final int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
        }
    }

    private Nodo head;
    private int size;

    public void insertarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = head;
        head = nuevo;
        size++;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (estaVacia()) {
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

    public void imprimir() {
        if (estaVacia()) {
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

    public boolean estaVacia() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        System.out.println("Cada nodo guarda un numero entero y una referencia al siguiente nodo.");
        System.out.println("head apunta al primer nodo; si head es null, la lista esta vacia.");
        System.out.println("Cada insercion enlaza un nodo y aumenta size en uno.");
        System.out.println("Insertar al inicio actualiza head; insertar al final enlaza el nuevo nodo al ultimo.");
        System.out.println();

        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        System.out.println("Esta vacia? " + lista.estaVacia());
        System.out.println("Tamano inicial: " + lista.getSize());
        lista.imprimir();

        lista.insertarAlInicio(20);
        lista.insertarAlInicio(10);
        lista.insertarAlFinal(30);

        System.out.print("Lista despues de insertar: ");
        lista.imprimir();
        System.out.println("Esta vacia? " + lista.estaVacia());
        System.out.println("Tamano actual: " + lista.getSize());
    }
}
