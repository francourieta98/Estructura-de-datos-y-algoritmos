public class Ejercicio7ModificarElemento {
    private static class Nodo {
        private int dato;
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

    public void modificar(int posicion, int nuevoDato) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posicion fuera de los limites: " + posicion);
        }

        Nodo actual = head;
        for (int indice = 0; indice < posicion; indice++) {
            actual = actual.siguiente;
        }
        actual.dato = nuevoDato;
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

    public static void main(String[] args) {
        System.out.println("dato guarda el valor almacenado en el nodo.");
        System.out.println("siguiente guarda la referencia al proximo nodo de la cadena.");
        System.out.println("modificar cambia dato, pero conserva siguiente y la estructura de la lista.");
        System.out.println("La posicion se valida antes de recorrer desde head.");
        System.out.println();

        Ejercicio7ModificarElemento lista = new Ejercicio7ModificarElemento();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);

        System.out.print("Lista inicial: ");
        lista.imprimir();
        lista.modificar(1, 99);
        System.out.print("Despues de modificar la posicion 1: ");
        lista.imprimir();

        try {
            lista.modificar(-1, 50);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println(excepcion.getMessage());
        }
        try {
            lista.modificar(lista.size, 50);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }
}
