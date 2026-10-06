import java.util.Arrays;
import java.util.NoSuchElementException;

public class ColaLinealDesperdicio {
    private final int[] elementos;
    private int front;
    private int rear;
    private int size;

    public ColaLinealDesperdicio(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }

        elementos = new int[capacidad];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(int valor) {
        if (rear == elementos.length - 1) {
            throw new IllegalStateException(
                    "No se puede agregar: rear llego al final, aunque haya espacios libres al inicio.");
        }

        elementos[++rear] = valor;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No se puede quitar: la cola esta vacia.");
        }

        int valor = elementos[front];
        elementos[front++] = 0;
        size--;
        return valor;
    }

    public int front() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay elementos para consultar.");
        }

        return elementos[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return rear == elementos.length - 1;
    }

    public int size() {
        return size;
    }

    private void mostrarEstado(String operacion) {
        System.out.println(operacion);
        System.out.println("Arreglo: " + Arrays.toString(elementos));
        System.out.println("front = " + front + ", rear = " + rear + ", size = " + size);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("En una cola lineal, rear solo avanza hacia el final del arreglo.");
        System.out.println("Al hacer dequeue, front avanza y deja espacios libres al inicio.");
        System.out.println("Esos espacios no pueden reutilizarse: no se hace circular el arreglo.");
        System.out.println();

        ColaLinealDesperdicio cola = new ColaLinealDesperdicio(5);
        cola.mostrarEstado("Estado inicial:");

        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        cola.enqueue(40);
        cola.enqueue(50);
        cola.mostrarEstado("Despues de enqueue(10), enqueue(20), enqueue(30), enqueue(40), enqueue(50):");

        System.out.println("dequeue() devuelve: " + cola.dequeue());
        System.out.println("dequeue() devuelve: " + cola.dequeue());
        cola.mostrarEstado("Despues de dos dequeue: quedan espacios libres al inicio.");

        try {
            cola.enqueue(60);
        } catch (IllegalStateException excepcion) {
            System.out.println(excepcion.getMessage());
        }
        cola.mostrarEstado("El arreglo no puede reutilizar los espacios iniciales:");
    }
}
