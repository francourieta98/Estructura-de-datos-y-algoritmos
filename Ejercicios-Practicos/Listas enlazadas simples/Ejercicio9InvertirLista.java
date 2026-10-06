public class Ejercicio9InvertirLista {
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

    public void invertir() {
        Nodo anterior = null;
        Nodo actual = head;

        while (actual != null) {
            Nodo siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }

        head = anterior;
    }

    private void imprimir() {
        Nodo actual = head;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        System.out.println("anterior guarda el nodo previo en el orden invertido.");
        System.out.println("actual es el nodo cuyo enlace se va a cambiar.");
        System.out.println("siguiente guarda temporalmente el resto de la lista antes de cambiar el enlace.");
        System.out.println("El orden importa: primero se guarda siguiente, luego se invierte el enlace,");
        System.out.println("y al final se avanzan anterior y actual. Si no se guarda siguiente primero,");
        System.out.println("se pierde el acceso a los nodos que aun no se han recorrido.");
        System.out.println();

        Ejercicio9InvertirLista lista = new Ejercicio9InvertirLista();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        System.out.print("Antes de invertir: ");
        lista.imprimir();
        lista.invertir();
        System.out.print("Despues de invertir: ");
        lista.imprimir();
    }
}
