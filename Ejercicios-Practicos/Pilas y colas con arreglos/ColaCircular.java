import java.util.Arrays;
import java.util.NoSuchElementException;

public class ColaCircular {
    private final int[] elementos;
    private int front;
    private int rear;
    private int size;

    public ColaCircular(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }

        elementos = new int[capacidad];
        front = -1;
        rear = -1;
        size = 0;
    }

    public void enqueue(int valor) {
        if (isFull()) {
            throw new IllegalStateException("No se puede agregar: la cola esta llena.");
        }

        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % elementos.length;
        elementos[rear] = valor;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No se puede quitar: la cola esta vacia.");
        }

        int valor = elementos[front];
        elementos[front] = 0;
        size--;

        if (isEmpty()) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % elementos.length;
        }

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
        return size == elementos.length;
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
        System.out.println("El operador % hace que el indice vuelva a 0 al pasar el final del arreglo.");
        System.out.println("rear = (rear + 1) % capacidad al encolar; front = (front + 1) % capacidad al desencolar.");
        System.out.println("La cola esta vacia cuando size == 0 y llena cuando size == capacidad.");
        System.out.println();

        ColaCircular cola = new ColaCircular(4);
        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        cola.mostrarEstado("Despues de encolar 10, 20 y 30:");

        System.out.println("dequeue() devuelve: " + cola.dequeue());
        System.out.println("dequeue() devuelve: " + cola.dequeue());
        cola.mostrarEstado("Despues de liberar dos posiciones al inicio:");

        cola.enqueue(40);
        cola.enqueue(50);
        cola.mostrarEstado("Despues de encolar 40 y 50: rear vuelve al inicio y reutiliza espacios.");

        System.out.println("Primer elemento (front): " + cola.front());
        cola.enqueue(60);
        cola.mostrarEstado("Despues de encolar 60: todas las posiciones estan ocupadas.");
        System.out.println("Esta llena? " + cola.isFull());
        try {
            cola.enqueue(70);
        } catch (IllegalStateException excepcion) {
            System.out.println(excepcion.getMessage());
        }

        while (!cola.isEmpty()) {
            System.out.println("dequeue() devuelve: " + cola.dequeue());
        }
        System.out.println("Esta vacia? " + cola.isEmpty());
        try {
            cola.front();
        } catch (NoSuchElementException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }
}
