import java.util.NoSuchElementException;

public class Pila<T> {
    private final Object[] elementos;
    private int top;

    public Pila(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }

        elementos = new Object[capacidad];
        top = -1;
    }

    public void push(T valor) {
        if (isFull()) {
            throw new IllegalStateException("No se puede agregar: la pila esta llena.");
        }

        elementos[++top] = valor;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("No se puede quitar: la pila esta vacia.");
        }

        T valor = (T) elementos[top];
        elementos[top--] = null;
        return valor;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay elementos para consultar.");
        }

        return (T) elementos[top];
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
}
