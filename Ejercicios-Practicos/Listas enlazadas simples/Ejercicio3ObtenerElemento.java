public class Ejercicio3ObtenerElemento {
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

    public int obtener(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posicion fuera de los limites: " + posicion);
        }

        Nodo actual = head;
        for (int indice = 0; indice < posicion; indice++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public static void main(String[] args) {
        System.out.println("Aunque se indique una posicion, la lista no permite acceso directo como un arreglo.");
        System.out.println("Para obtener un dato se empieza en head y se avanza nodo por nodo hasta esa posicion.");
        System.out.println("Las posiciones validas van de 0 a size - 1; las demas se rechazan.");
        System.out.println();

        Ejercicio3ObtenerElemento lista = new Ejercicio3ObtenerElemento();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);

        System.out.println("Dato en posicion 1: " + lista.obtener(1));
        try {
            lista.obtener(-1);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println(excepcion.getMessage());
        }
        try {
            lista.obtener(lista.size);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }
}
