# Pilas y colas con listas enlazadas

## Ejercicio 1: pila enlazada de enteros

`PilaEnlazadaEnteros.java` implementa una pila de enteros usando nodos enlazados, sin una colección de Java. Permite apilar, desapilar, consultar el tope, verificar si está vacía, buscar un valor e imprimir el contenido.

`head` representa el tope porque siempre apunta al nodo agregado más recientemente. Así, `apilar` crea un nodo delante del `head` actual y actualiza `head`; `desapilar` obtiene ese dato y mueve `head` al siguiente nodo. Ambas operaciones solo actualizan una cantidad fija de referencias, independientemente de cuántos elementos haya, por lo que se ejecutan en O(1). En cambio, buscar o imprimir requiere recorrer nodos y toma O(n).

Si se intenta desapilar o consultar el tope de una pila vacía, se lanza `NoSuchElementException`.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac PilaEnlazadaEnteros.java
java PilaEnlazadaEnteros
```

## Ejercicio 2: cola enlazada de enteros

`ColaEnlazadaEnteros.java` implementa una cola con operaciones para encolar, desencolar, consultar el frente, verificar si está vacía, buscar un valor e imprimir el contenido.

Mantiene dos referencias: `head` apunta al frente, que es el próximo nodo en salir, y `tail` apunta al último nodo, donde se agrega el siguiente elemento. Esto permite encolar y desencolar en O(1). Si solo se mantuviera `head`, cada encolado tendría que recorrer todos los nodos para encontrar el final, y costaría O(n). Cuando se desencola el último nodo, ambas referencias se restablecen a `null`.

Desencolar o consultar el frente cuando la cola está vacía lanza `NoSuchElementException`. El `main` demuestra también que la cola puede volver a usarse después de quedar vacía.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac ColaEnlazadaEnteros.java
java ColaEnlazadaEnteros
```

## Ejercicio 3: pila genérica

`PilaEnlazadaGenerica.java` implementa `Pila<T>` con nodos `Nodo<T>`. El ejemplo utiliza la misma implementación con `Pila<Integer>`, `Pila<String>` y `Pila<Alumno>`.

En Java, el parámetro genérico `T` representa el tipo que se elige al crear la pila, y permite que el compilador compruebe que se usen valores de ese tipo. La lógica de la pila no depende del tipo almacenado: apilar crea un nodo y lo enlaza como nuevo `head`; desapilar mueve `head` al siguiente nodo. Los algoritmos manipulan referencias y mantienen LIFO, sin necesitar saber si el dato es un entero, texto u objeto.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac PilaEnlazadaGenerica.java
java PilaEnlazadaGenerica
```
