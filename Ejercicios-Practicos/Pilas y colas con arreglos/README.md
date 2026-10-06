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

## Ejercicio 3: validar paréntesis balanceados

`ValidadorParentesis.java` recibe una expresión matemática y determina si sus paréntesis están balanceados. Recorre la expresión y guarda cada paréntesis abierto `(` en una pila. Cada vez que encuentra un paréntesis cerrado `)`, realiza `pop` para emparejarlo con el último abierto. Si no hay ninguno que emparejar, o quedan abiertos al terminar, el resultado es inválido.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac ValidadorParentesis.java
java ValidadorParentesis
```

## Ejercicio 4: historial de navegación

`HistorialNavegacion.java` permite visitar una página ingresando su URL, volver a la página anterior y consultar la página actual. Cada visita se apila; al volver, se hace `pop` de la página actual y la página previa queda en la cima. Se aplica LIFO porque la página más recientemente visitada es la primera que se quita al retroceder. El programa avisa si todavía no existe una página anterior.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac HistorialNavegacion.java
java HistorialNavegacion
```

## Ejercicio 5: pila genérica

`Pila.java` implementa `Pila<T>` usando un arreglo de capacidad fija. Incluye `push`, `pop`, `peek`, `isEmpty`, `isFull` y `size`; al intentar agregar a una pila llena o quitar/consultar una vacía, lanza una excepción.

El parámetro genérico `T` permite usar una sola implementación para distintos tipos, como `Pila<Integer>`, `Pila<String>` o una pila de objetos propios. El compilador comprueba que cada pila se use con su tipo declarado. En cambio, `PilaEnteros` solo almacena valores `int` y no puede reutilizarse para texto u objetos.

`EjemploPilaGenerica.java` demuestra el uso con enteros, cadenas y objetos `Producto`, y explica en pantalla para qué sirven los genéricos.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac Pila.java EjemploPilaGenerica.java
java EjemploPilaGenerica
```

## Ejercicio 6: cola simple de enteros

`ColaEnteros.java` implementa una cola circular de capacidad fija con `enqueue`, `dequeue`, `front`, `isEmpty`, `isFull` y `size`. `front` señala el primer elemento, que será el próximo en salir; `rear` señala el último elemento agregado. Al encolar avanza `rear`, y al desencolar avanza `front`. Ambos índices regresan al inicio del arreglo al alcanzar el final. Cuando se elimina el último elemento, ambos vuelven a `-1`.

Si se intenta agregar a una cola llena, se lanza `IllegalStateException`; si se intenta retirar o consultar una cola vacía, se lanza `NoSuchElementException`. El método `main` explica los índices y muestra también cómo la cola circular reutiliza el espacio liberado.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac ColaEnteros.java
java ColaEnteros
```

## Ejercicio 7: sistema de turnos para atención

`SistemaTurnos.java` asigna un número consecutivo y agrega cada persona a una cola. Permite atender a la siguiente persona y consultar quién está primero. Usa FIFO: la primera persona que llega es la primera en ser atendida. Una pila LIFO atendería primero a la última persona que llegó, invirtiendo injustamente el orden de espera. Si no hay personas esperando, el programa lo informa.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac SistemaTurnos.java
java SistemaTurnos
```

## Ejercicio 8: cola de impresión

`ColaImpresion.java` permite agregar documentos con nombre y cantidad de páginas, imprimir el siguiente y consultar cuál sigue. Los documentos se agregan al final de la cola y se imprimen desde el frente. FIFO hace que el primero agregado sea el primero impreso, conservando el orden de llegada. La cantidad de páginas debe ser un entero positivo.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac ColaImpresion.java
java ColaImpresion
```

## Ejercicio 9: desperdicio de espacio en una cola lineal

`ColaLinealDesperdicio.java` usa una cola simple, no circular. El programa muestra el arreglo y los índices luego de encolar cinco valores y desencolar dos. Los espacios liberados al inicio quedan vacíos, pero no se pueden reutilizar porque `rear` solo avanza hacia el final del arreglo. Por eso, un nuevo `enqueue` falla aunque haya lugares libres antes de `front`. A diferencia de esta demostración, `ColaEnteros.java` del ejercicio 6 sí es circular y reutiliza esos espacios.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac ColaLinealDesperdicio.java
java ColaLinealDesperdicio
```

## Ejercicio 10: cola circular

`ColaCircular.java` implementa una cola circular de enteros usando un arreglo. Los índices avanzan con módulo capacidad: `(rear + 1) % capacidad` al encolar y `(front + 1) % capacidad` al desencolar. El resto `%` hace que, al alcanzar el final del arreglo, el índice vuelva a cero y pueda reutilizar espacios libres al inicio. La cola está vacía cuando `size == 0` y llena cuando `size == capacidad`. El ejemplo muestra el arreglo e índices para ilustrar el recorrido circular.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac ColaCircular.java
java ColaCircular
```
