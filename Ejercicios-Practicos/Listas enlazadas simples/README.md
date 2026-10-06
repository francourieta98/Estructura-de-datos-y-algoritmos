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

## Ejercicio 2: buscar elementos

La misma clase implementa `boolean buscar(int dato)`. El método empieza en `head`, compara cada nodo con el dato buscado y avanza por la referencia `siguiente` hasta encontrarlo o llegar a `null`.

Una lista enlazada simple no ofrece acceso directo por índice: cada nodo solo conoce al siguiente, no la posición ni la dirección de los demás nodos. Por eso, la búsqueda debe ser secuencial desde `head`; puede terminar al hallar el dato o después de recorrer la lista completa. El `main` muestra una búsqueda exitosa y otra fallida.

## Ejercicio 3: obtener un elemento por posición

`int obtener(int posicion)` devuelve el dato de la posición solicitada, considerando que la primera posición es `0`. Si `posicion < 0` o `posicion >= size`, lanza `IndexOutOfBoundsException`.

Aunque el método recibe una posición, la lista no funciona como un arreglo: no puede saltar directamente a un índice. Comienza en `head` y avanza nodo por nodo hasta alcanzar la posición indicada. El `main` muestra una posición válida y ambas condiciones inválidas.
