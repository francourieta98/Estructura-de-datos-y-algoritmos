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

El ejercicio independiente está en `Ejercicio2BuscarElemento.java`, que implementa `boolean buscar(int dato)`. El método empieza en `head`, compara cada nodo con el dato buscado y avanza por la referencia `siguiente` hasta encontrarlo o llegar a `null`.

Una lista enlazada simple no ofrece acceso directo por índice: cada nodo solo conoce al siguiente, no la posición ni la dirección de los demás nodos. Por eso, la búsqueda debe ser secuencial desde `head`; puede terminar al hallar el dato o después de recorrer la lista completa. El `main` muestra una búsqueda exitosa y otra fallida.

```text
javac Ejercicio2BuscarElemento.java
java Ejercicio2BuscarElemento
```

## Ejercicio 3: obtener un elemento por posición

El ejercicio independiente está en `Ejercicio3ObtenerElemento.java`. Su método `int obtener(int posicion)` devuelve el dato de la posición solicitada, considerando que la primera posición es `0`. Si `posicion < 0` o `posicion >= size`, lanza `IndexOutOfBoundsException`.

Aunque el método recibe una posición, la lista no funciona como un arreglo: no puede saltar directamente a un índice. Comienza en `head` y avanza nodo por nodo hasta alcanzar la posición indicada. El `main` muestra una posición válida y ambas condiciones inválidas.

```text
javac Ejercicio3ObtenerElemento.java
java Ejercicio3ObtenerElemento
```

## Ejercicio 4: insertar un nodo en una posición específica

El ejercicio independiente está en `Ejercicio4InsertarEnPosicion.java`. Su método `void insertarEnPosicion(int dato, int posicion)` inserta antes del nodo que actualmente ocupa `posicion`. Se permite cualquier posición entre `0` y `size`, inclusive: `0` inserta al inicio, `size` inserta al final y los valores intermedios insertan en medio. Una posición menor que `0` o mayor que `size` lanza `IndexOutOfBoundsException`.

Para insertar después de un nodo `actual`, primero se conserva la continuación de la lista enlazando el nuevo nodo con el sucesor:

```text
nuevo.setSiguiente(actual.getSiguiente());
actual.setSiguiente(nuevo);
```

Si se cambia el orden y se apunta `actual` a `nuevo` antes de guardar su sucesor, la referencia al resto de la lista se puede perder; luego `nuevo.getSiguiente()` incluso podría apuntar a sí mismo y formar un ciclo. El `main` demuestra inserciones al inicio, en medio y al final, además de posiciones inválidas.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac Ejercicio4InsertarEnPosicion.java
java Ejercicio4InsertarEnPosicion
```

## Ejercicio 5: eliminar un nodo por valor

El ejercicio independiente está en `Ejercicio5EliminarPorValor.java`. Su método `boolean eliminar(int dato)` elimina solo la primera aparición y devuelve `true` si encontró el dato o `false` si la lista está vacía o el valor no existe. Actualiza `head` al eliminar el primer nodo; para los demás, enlaza el nodo anterior con el siguiente y decrementa el tamaño.

En Java no se borra manualmente la memoria del nodo. Al desconectarlo de la cadena, queda inaccesible desde la lista y el Garbage Collector puede recuperarlo cuando corresponda. El ejemplo demuestra lista vacía, eliminación del primer nodo, uno del medio, el último y un valor inexistente.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac Ejercicio5EliminarPorValor.java
java Ejercicio5EliminarPorValor
```

## Ejercicio 6: eliminar un nodo por posición

El ejercicio independiente está en `Ejercicio6EliminarPorPosicion.java`. Su método `void eliminarEnPosicion(int posicion)` elimina el nodo en una posición entre `0` y `size - 1`; una posición menor que `0` o mayor/igual que `size` lanza `IndexOutOfBoundsException`. Cada eliminación válida decrementa `size`. Para la posición `0`, actualiza `head`. Para otra posición, recorre hasta el nodo anterior y lo enlaza con el nodo siguiente al eliminado (`anterior.siguiente = eliminado.siguiente`).

El `main` muestra eliminaciones al inicio, en medio, al final y del único nodo, además de probar la lista vacía y posiciones inválidas.

### Compilar y ejecutar

Desde esta carpeta:

```text
javac Ejercicio6EliminarPorPosicion.java
java Ejercicio6EliminarPorPosicion
```
