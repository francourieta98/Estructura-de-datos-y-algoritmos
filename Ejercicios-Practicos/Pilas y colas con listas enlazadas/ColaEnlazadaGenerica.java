import java.util.NoSuchElementException;
import java.util.Objects;

public class ColaEnlazadaGenerica {
    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        private Nodo(T dato) {
            this.dato = dato;
        }
    }

    public static class Cola<T> {
        private Nodo<T> head;
        private Nodo<T> tail;

        public void encolar(T dato) {
            Nodo<T> nuevo = new Nodo<T>(dato);
            if (estaVacia()) {
                head = nuevo;
                tail = nuevo;
            } else {
                tail.siguiente = nuevo;
                tail = nuevo;
            }
        }

        public T desencolar() {
            if (estaVacia()) {
                throw new NoSuchElementException("No se puede desencolar: la cola esta vacia.");
            }

            T dato = head.dato;
            head = head.siguiente;
            if (head == null) {
                tail = null;
            }
            return dato;
        }

        public T consultarFrente() {
            if (estaVacia()) {
                throw new NoSuchElementException("No hay frente: la cola esta vacia.");
            }
            return head.dato;
        }

        public boolean estaVacia() {
            return head == null;
        }

        public boolean contiene(T dato) {
            Nodo<T> actual = head;
            while (actual != null) {
                if (Objects.equals(actual.dato, dato)) {
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

            Nodo<T> actual = head;
            System.out.print("Frente -> ");
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

    private static class Cliente {
        private final String nombre;
        private final int numero;

        private Cliente(String nombre, int numero) {
            this.nombre = nombre;
            this.numero = numero;
        }

        @Override
        public String toString() {
            return nombre + " (#" + numero + ")";
        }

        @Override
        public boolean equals(Object otro) {
            if (this == otro) {
                return true;
            }
            if (!(otro instanceof Cliente)) {
                return false;
            }
            Cliente cliente = (Cliente) otro;
            return numero == cliente.numero && Objects.equals(nombre, cliente.nombre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nombre, numero);
        }
    }

    public static void main(String[] args) {
        System.out.println("La cola conserva FIFO para cualquier tipo T: sale primero el que se encolo primero.");
        System.out.println("head sigue apuntando al frente y tail al final; el tipo del dato no cambia esos enlaces.");
        System.out.println("Encolar agrega al final y desencolar quita del frente, preservando el orden de llegada.");
        System.out.println();

        Cola<String> nombres = new Cola<String>();
        nombres.encolar("Ana");
        nombres.encolar("Luis");
        nombres.encolar("Marta");
        System.out.print("Cola de nombres: ");
        nombres.imprimir();
        System.out.println("Frente: " + nombres.consultarFrente());
        System.out.println("Atendido: " + nombres.desencolar());
        System.out.print("Nombres restantes: ");
        nombres.imprimir();

        Cola<Cliente> clientes = new Cola<Cliente>();
        Cliente cliente1 = new Cliente("Sofia", 101);
        Cliente cliente2 = new Cliente("Diego", 102);
        clientes.encolar(cliente1);
        clientes.encolar(cliente2);
        System.out.print("Cola de clientes: ");
        clientes.imprimir();
        System.out.println("Contiene cliente 102? " + clientes.contiene(new Cliente("Diego", 102)));
        System.out.println("Atendido: " + clientes.desencolar());
        System.out.println("Siguiente cliente: " + clientes.consultarFrente());
    }
}
