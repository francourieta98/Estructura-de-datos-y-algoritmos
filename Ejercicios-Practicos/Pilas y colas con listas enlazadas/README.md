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
