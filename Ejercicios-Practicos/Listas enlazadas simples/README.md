# Listas enlazadas simples

## Ejercicio 1: crear una lista enlazada simple desde cero

`ListaEnlazadaSimple.java` implementa una lista de enteros sin usar `ArrayList`, `LinkedList` ni otras colecciones.

Cada nodo guarda un entero y una referencia al siguiente nodo. `head` apunta al primer nodo de la lista; cuando vale `null`, no hay elementos. La lista conserva `size`, que empieza en cero y aumenta una unidad cada vez que se inserta un nodo. Insertar al inicio hace que el nuevo nodo apunte al `head` anterior y luego actualiza `head`; insertar al final enlaza el nuevo nodo después del último.

La clase incluye:

- `insertarAlInicio(int dato)`
- `insertarAlFinal(int dato)`
- `imprimir()`
- `estaVacia()`
- `getSize()`

El método `main` explica los nodos, `head` y la actualización de `size`, y muestra las operaciones.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac ListaEnlazadaSimple.java
java ListaEnlazadaSimple
```
