# Pilas y colas con arreglos

## Ejercicio 1: pila de enteros

`PilaEnteros.java` implementa una pila de capacidad fija con un arreglo de enteros.

### Índice `top`

`top` guarda la posición del elemento que está en la cima. Al iniciar vale `-1`, que representa que no hay elementos. Al agregar un elemento, primero se incrementa `top` y se guarda el valor en esa posición. Al quitarlo, se devuelve el valor y `top` retrocede una posición.

### Operaciones

- `push(valor)`: agrega un entero. Si la pila está llena, lanza `IllegalStateException`.
- `pop()`: quita y devuelve el entero de la cima. Si está vacía, lanza `NoSuchElementException`.
- `peek()`: devuelve el entero de la cima sin quitarlo. Si está vacía, lanza `NoSuchElementException`.
- `isEmpty()`: indica si la pila está vacía.
- `isFull()`: indica si se alcanzó la capacidad del arreglo.
- `size()`: devuelve la cantidad actual de elementos.

La capacidad debe ser mayor que cero; de lo contrario, el constructor lanza `IllegalArgumentException`.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac PilaEnteros.java
java PilaEnteros
```

El método `main` explica el uso de `top` y muestra las operaciones principales.
