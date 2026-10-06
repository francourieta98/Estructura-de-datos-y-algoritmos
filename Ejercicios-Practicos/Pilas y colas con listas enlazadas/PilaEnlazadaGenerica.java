import java.util.NoSuchElementException;

public class PilaEnlazadaGenerica {
    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        private Nodo(T dato) {
            this.dato = dato;
        }
    }

    public static class Pila<T> {
        private Nodo<T> head;

        public void apilar(T dato) {
            Nodo<T> nuevo = new Nodo<T>(dato);
            nuevo.siguiente = head;
            head = nuevo;
        }

        public T desapilar() {
            if (estaVacia()) {
                throw new NoSuchElementException("No se puede desapilar: la pila esta vacia.");
            }

            T dato = head.dato;
            head = head.siguiente;
            return dato;
        }

        public T consultarTope() {
            if (estaVacia()) {
                throw new NoSuchElementException("No hay elementos en el tope: la pila esta vacia.");
            }

            return head.dato;
        }

        public boolean estaVacia() {
            return head == null;
        }

        public void imprimir() {
            if (estaVacia()) {
                System.out.println("La pila esta vacia.");
                return;
            }

            Nodo<T> actual = head;
            System.out.print("Tope -> ");
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
    }

    public static void main(String[] args) {
        System.out.println("Un generico como Pila<T> permite elegir el tipo al crear la pila.");
        System.out.println("T representa Integer, String o una clase propia como Alumno.");
        System.out.println("La logica LIFO no cambia: apilar enlaza un nodo en head y desapilar avanza head.");
        System.out.println("La estructura manipula referencias y enlaces, no depende de operaciones del tipo almacenado.");
        System.out.println();

        Pila<Integer> enteros = new Pila<Integer>();
        enteros.apilar(10);
        enteros.apilar(20);
        System.out.print("Pila<Integer>: ");
        enteros.imprimir();
        System.out.println("Desapilado: " + enteros.desapilar());

        Pila<String> textos = new Pila<String>();
        textos.apilar("primero");
        textos.apilar("segundo");
        System.out.print("Pila<String>: ");
        textos.imprimir();
        System.out.println("Tope: " + textos.consultarTope());

        Pila<Alumno> alumnos = new Pila<Alumno>();
        alumnos.apilar(new Alumno("Ana"));
        alumnos.apilar(new Alumno("Luis"));
        System.out.print("Pila<Alumno>: ");
        alumnos.imprimir();
        System.out.println("Desapilado: " + alumnos.desapilar());
    }
}
