import java.util.NoSuchElementException;

public class ColaEnteros {
    private final int[] elementos;
    private int front;
    private int rear;
    private int size;

    public ColaEnteros(int capacidad) {
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

    public static void main(String[] args) {
        System.out.println("front senala el primer elemento, que sera el siguiente en salir.");
        System.out.println("rear senala el ultimo elemento agregado.");
        System.out.println("Al encolar, rear avanza; al desencolar, front avanza.");
        System.out.println("Los indices vuelven al inicio del arreglo al llegar al final, como un ciclo.");
        System.out.println("Cuando se elimina el ultimo elemento, front y rear vuelven a -1.");
        System.out.println();

        ColaEnteros cola = new ColaEnteros(3);
        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        System.out.println("Primer elemento (front): " + cola.front());
        System.out.println("Cantidad de elementos (size): " + cola.size());
        System.out.println("Esta llena? " + cola.isFull());
        try {
            cola.enqueue(40);
        } catch (IllegalStateException excepcion) {
            System.out.println(excepcion.getMessage());
        }

        System.out.println("Elemento eliminado (dequeue): " + cola.dequeue());
        cola.enqueue(40);
        System.out.println("Primer elemento tras liberar espacio: " + cola.front());
        System.out.println("Cantidad de elementos (size): " + cola.size());
        while (!cola.isEmpty()) {
            System.out.println("Elemento eliminado (dequeue): " + cola.dequeue());
        }
        System.out.println("Esta vacia? " + cola.isEmpty());
        try {
            cola.dequeue();
        } catch (NoSuchElementException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }
}
