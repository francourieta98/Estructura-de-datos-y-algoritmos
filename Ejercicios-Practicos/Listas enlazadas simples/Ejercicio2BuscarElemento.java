public class Ejercicio2BuscarElemento {
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
            return;
        }

        Nodo actual = head;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
    }

    public boolean buscar(int dato) {
        Nodo actual = head;
        while (actual != null) {
            if (actual.dato == dato) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("En una lista enlazada no se puede saltar directamente a un indice como en un arreglo.");
        System.out.println("Cada nodo solo conoce al siguiente, por eso buscar recorre secuencialmente desde head.");
        System.out.println("La busqueda termina al encontrar el dato o al llegar a null.");
        System.out.println();

        Ejercicio2BuscarElemento lista = new Ejercicio2BuscarElemento();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);

        System.out.println("Buscar 20: " + lista.buscar(20));
        System.out.println("Buscar 99: " + lista.buscar(99));
    }
}
