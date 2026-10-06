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

## Ejercicio 2: simulador de torre de platos

`SimuladorTorrePlatos.java` permite agregar un plato con un nombre o número, retirar el plato superior, consultar el plato superior y salir.

La torre se representa con una pila (`Deque<String>`). El último plato que se agrega queda arriba y se retira primero (LIFO). Una cola usa el orden FIFO y retiraría primero el plato colocado al fondo, por lo que no representa el comportamiento de una torre de platos. Si la torre está vacía, el programa informa que no hay plato para retirar o consultar.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac SimuladorTorrePlatos.java
java SimuladorTorrePlatos
```
