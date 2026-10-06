import java.util.Objects;

public class Ejercicio10ListaGenerica {
    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        private Nodo(T dato) {
            this.dato = dato;
        }
    }

    public static class ListaEnlazada<T> {
        private Nodo<T> head;
        private int size;

        public void insertarAlInicio(T dato) {
            Nodo<T> nuevo = new Nodo<T>(dato);
            nuevo.siguiente = head;
            head = nuevo;
            size++;
        }

        public void insertarAlFinal(T dato) {
            Nodo<T> nuevo = new Nodo<T>(dato);
            if (head == null) {
                head = nuevo;
            } else {
                Nodo<T> actual = head;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
            size++;
        }

        public boolean buscar(T dato) {
            Nodo<T> actual = head;
            while (actual != null) {
                if (Objects.equals(actual.dato, dato)) {
                    return true;
                }
                actual = actual.siguiente;
            }
            return false;
        }

        public boolean eliminar(T dato) {
            if (head == null) {
                return false;
            }

            if (Objects.equals(head.dato, dato)) {
                head = head.siguiente;
                size--;
                return true;
            }

            Nodo<T> anterior = head;
            Nodo<T> actual = head.siguiente;
            while (actual != null) {
                if (Objects.equals(actual.dato, dato)) {
                    anterior.siguiente = actual.siguiente;
                    size--;
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }
            return false;
        }

        public boolean estaVacia() {
            return head == null;
        }

        public int getSize() {
            return size;
        }

        public void imprimir() {
            Nodo<T> actual = head;
            while (actual != null) {
                System.out.print(String.valueOf(actual.dato));
                if (actual.siguiente != null) {
                    System.out.print(" -> ");
                }
                actual = actual.siguiente;
            }
            System.out.println(" -> null");
        }
    }

    private static class Alumno {
        private final String nombre;

        private Alumno(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }

        @Override
        public boolean equals(Object otro) {
            if (this == otro) {
                return true;
            }
            if (!(otro instanceof Alumno)) {
                return false;
            }
            Alumno alumno = (Alumno) otro;
            return Objects.equals(nombre, alumno.nombre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nombre);
        }
    }

    public static void main(String[] args) {
        System.out.println("Cambia el tipo almacenado: dato pasa de int a T, y cada referencia es Nodo<T>.");
        System.out.println("La comparacion de valores usa equals (a traves de Objects.equals) en vez de == para objetos.");
        System.out.println("Se mantienen head, siguiente, size y los pasos de recorrido y actualizacion de enlaces.");
        System.out.println("Insertar y eliminar no dependen del tipo: solo conectan nodos; el algoritmo no opera sobre el dato.");
        System.out.println();

        ListaEnlazada<Integer> enteros = new ListaEnlazada<Integer>();
        enteros.insertarAlFinal(10);
        enteros.insertarAlFinal(20);
        enteros.insertarAlInicio(5);
        System.out.print("Lista<Integer>: ");
        enteros.imprimir();
        System.out.println("Eliminar 10: " + enteros.eliminar(10));
        System.out.print("Despues de eliminar: ");
        enteros.imprimir();

        ListaEnlazada<String> textos = new ListaEnlazada<String>();
        textos.insertarAlFinal("rojo");
        textos.insertarAlFinal("verde");
        textos.insertarAlFinal("azul");
        System.out.print("Lista<String>: ");
        textos.imprimir();
        System.out.println("Buscar verde: " + textos.buscar("verde"));
        System.out.println("Eliminar verde: " + textos.eliminar("verde"));
        System.out.print("Despues de eliminar: ");
        textos.imprimir();

        ListaEnlazada<Alumno> alumnos = new ListaEnlazada<Alumno>();
        alumnos.insertarAlFinal(new Alumno("Ana"));
        alumnos.insertarAlFinal(new Alumno("Luis"));
        System.out.print("Lista<Alumno>: ");
        alumnos.imprimir();
        System.out.println("Eliminar Ana: " + alumnos.eliminar(new Alumno("Ana")));
        System.out.print("Despues de eliminar: ");
        alumnos.imprimir();
        System.out.println("Tamano de la lista de alumnos: " + alumnos.getSize());
    }
}
