import java.util.NoSuchElementException;

public class PilaEnteros {
    private final int[] elementos;
    private int top;

    public PilaEnteros(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }

        elementos = new int[capacidad];
        top = -1;
    }

    public void push(int valor) {
        if (isFull()) {
            throw new IllegalStateException("No se puede agregar: la pila esta llena.");
        }

        elementos[++top] = valor;
    }

    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("No se puede quitar: la pila esta vacia.");
        }

        int valor = elementos[top];
        elementos[top--] = 0;
        return valor;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay elementos para consultar.");
        }

        return elementos[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == elementos.length - 1;
    }

    public int size() {
        return top + 1;
    }

    public static void main(String[] args) {
        System.out.println("El indice top senala la posicion del elemento que esta arriba de la pila.");
        System.out.println("Empieza en -1 porque el arreglo esta vacio; al agregar, top avanza.");
        System.out.println("Al quitar, top retrocede. Una pila llena no acepta mas elementos y una vacia no permite consultar ni quitar.");
        System.out.println();

        PilaEnteros pila = new PilaEnteros(2);
        pila.push(10);
        pila.push(20);
        System.out.println("Elemento de arriba (peek): " + pila.peek());
        System.out.println("Cantidad de elementos (size): " + pila.size());
        System.out.println("Esta llena? " + pila.isFull());
        try {
            pila.push(30);
        } catch (IllegalStateException excepcion) {
            System.out.println(excepcion.getMessage());
        }
        System.out.println("Elemento quitado (pop): " + pila.pop());
        System.out.println("Elemento de arriba ahora: " + pila.peek());
        pila.pop();
        System.out.println("Esta vacia? " + pila.isEmpty());
        try {
            pila.pop();
        } catch (NoSuchElementException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }
}
