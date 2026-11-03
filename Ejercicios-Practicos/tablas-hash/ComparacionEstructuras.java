/*
 * =================================================================================
 * PROMPT INICIAL UTILIZADO:
 * "Necesito resolver el Ejercicio 10 en Java para OpenCode:
 *  - Problema: Implementar un programa comparativo para analizar empíricamente el rendimiento
 *    y la eficiencia de búsqueda entre tres estructuras de datos: Lista Enlazada, Árbol Binario 
 *    de Búsqueda (ABB) y Tabla Hash.
 *  - Explicación teórica de la comparación de eficiencias:
 *      1. Lista Enlazada: Realiza un recorrido secuencial nodo por nodo desde el inicio. 
 *         En el peor de los casos o cuando el elemento está al final/no existe, realiza O(n) comparaciones.
 *      2. Árbol Binario de Búsqueda (ABB): Realiza búsquedas aprovechando la propiedad de ordenamiento. 
 *         En un árbol balanceado requiere O(log n) comparaciones al descartar la mitad de los nodos 
 *         en cada nivel.
 *      3. Tabla Hash: Accede directamente al índice mediante la función hash en O(1) promedio. 
 *         El número de accesos/comparaciones equivale únicamente a la cantidad de nodos recorridos 
 *         dentro de la lista de colisión (bucket) específica.
 *  - Estructura de Datos:
 *      1. Lista Enlazada Simple.
 *      2. Árbol Binario de Búsqueda (ABB) ordenado por código.
 *      3. Tabla Hash con Encadenamiento Separado (Chaining).
 *  - Clave de búsqueda: El código del producto (String, ej: 'P105').
 *  - Operaciones a implementar:
 *      1. Cargar productos simultáneamente en las tres estructuras.
 *      2. buscarEnLista(String codigo) -> Retorna el producto y las comparaciones realizadas.
 *      3. buscarEnArbol(String codigo) -> Retorna el producto y las comparaciones realizadas.
 *      4. buscarEnTablaHash(String codigo) -> Retorna el producto y las comparaciones/accesos realizados.
 *      5. ejecutarComparación(String codigo) -> Ejecuta la búsqueda en las 3 estructuras e imprime la comparativa.
 *  - Casos de prueba a validar:
 *      1. Carga masiva de productos en orden para probar distintos escenarios de búsqueda.
 *      2. Búsqueda de un producto ubicado al inicio de la lista.
 *      3. Búsqueda de un producto ubicado al final de la lista (demostrando la ventaja del ABB y Hash).
 *      4. Búsqueda de un producto inexistente en las tres estructuras.
 *      5. Despliegue de métricas comparativas por pantalla."
 *
 * AJUSTES POSTERIORES AL PROMPT:
 * No se realizaron ajustes posteriores. El diseño inicial cumple con medir de forma exacta
 * las comparaciones secuenciales, ramificadas y de buckets para cada estructura.
 * =================================================================================
 */

public class ComparacionEstructuras {

    // Clase Producto común a todas las estructuras
    static class Producto {
        String codigo;
        String nombre;
        double precio;

        public Producto(String codigo, String nombre, double precio) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
        }

        @Override
        public String toString() {
            return "[" + codigo + " | " + nombre + " | $" + precio + "]";
        }
    }

    // Clase auxiliar para retornar el resultado de la búsqueda junto con el contador de comparaciones
    static class ResultadoBusqueda {
        Producto producto;
        int comparaciones;

        public ResultadoBusqueda(Producto producto, int comparaciones) {
            this.producto = producto;
            this.comparaciones = comparaciones;
        }
    }

    // -------------------------------------------------------------------------
    // 1. ESTRUCTURA: LISTA ENLAZADA SIMPLE
    // -------------------------------------------------------------------------
    static class NodoLista {
        Producto producto;
        NodoLista siguiente;

        public NodoLista(Producto producto) {
            this.producto = producto;
            this.siguiente = null;
        }
    }

    static class ListaEnlazada {
        private NodoLista cabeza;

        public void insertar(Producto producto) {
            NodoLista nuevo = new NodoLista(producto);
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        }

        public ResultadoBusqueda buscar(String codigo) {
            int comparaciones = 0;
            NodoLista actual = cabeza;

            while (actual != null) {
                comparaciones++;
                if (actual.producto.codigo.equals(codigo)) {
                    return new ResultadoBusqueda(actual.producto, comparaciones);
                }
                actual = actual.siguiente;
            }

            return new ResultadoBusqueda(null, comparaciones);
        }
    }

    // -------------------------------------------------------------------------
    // 2. ESTRUCTURA: ÁRBOL BINARIO DE BÚSQUEDA (ABB)
    // -------------------------------------------------------------------------
    static class NodoArbol {
        Producto producto;
        NodoArbol izquierdo;
        NodoArbol derecho;

        public NodoArbol(Producto producto) {
            this.producto = producto;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    static class ArbolBinarioBusqueda {
        private NodoArbol raiz;

        public void insertar(Producto producto) {
            raiz = insertarRec(raiz, producto);
        }

        private NodoArbol insertarRec(NodoArbol nodo, Producto producto) {
            if (nodo == null) {
                return new NodoArbol(producto);
            }

            int comparacion = producto.codigo.compareTo(nodo.producto.codigo);
            if (comparacion < 0) {
                nodo.izquierdo = insertarRec(nodo.izquierdo, producto);
            } else if (comparacion > 0) {
                nodo.derecho = insertarRec(nodo.derecho, producto);
            }
            return nodo;
        }

        public ResultadoBusqueda buscar(String codigo) {
            int[] comparaciones = new int[]{0}; // Contenedor mutable para contar en recursión
            Producto hallado = buscarRec(raiz, codigo, comparaciones);
            return new ResultadoBusqueda(hallado, comparaciones[0]);
        }

        private Producto buscarRec(NodoArbol nodo, String codigo, int[] contador) {
            if (nodo == null) {
                return null;
            }

            contador[0]++;
            int comparacion = codigo.compareTo(nodo.producto.codigo);

            if (comparacion == 0) {
                return nodo.producto;
            } else if (comparacion < 0) {
                return buscarRec(nodo.izquierdo, codigo, contador);
            } else {
                return buscarRec(nodo.derecho, codigo, contador);
            }
        }
    }

    // -------------------------------------------------------------------------
    // 3. ESTRUCTURA: TABLA HASH (ENCADENAMIENTO SEPARADO)
    // -------------------------------------------------------------------------
    static class TablaHash {
        private NodoLista[] tabla;
        private int capacidad;

        public TablaHash(int capacidad) {
            this.capacidad = capacidad;
            this.tabla = new NodoLista[capacidad];
        }

        private int funcionHash(String codigo) {
            int hash = 0;
            for (int i = 0; i < codigo.length(); i++) {
                hash = (31 * hash + codigo.charAt(i)) % capacidad;
            }
            return Math.abs(hash);
        }

        public void insertar(Producto producto) {
            int indice = funcionHash(producto.codigo);
            NodoLista nuevo = new NodoLista(producto);
            nuevo.siguiente = tabla[indice];
            tabla[indice] = nuevo;
        }

        public ResultadoBusqueda buscar(String codigo) {
            int indice = funcionHash(codigo);
            int accesos = 0;
            NodoLista actual = tabla[indice];

            while (actual != null) {
                accesos++;
                if (actual.producto.codigo.equals(codigo)) {
                    return new ResultadoBusqueda(actual.producto, accesos);
                }
                actual = actual.siguiente;
            }

            return new ResultadoBusqueda(null, accesos);
        }
    }

    // -------------------------------------------------------------------------
    // MÉTODO PRINCIPAL Y COMPARADOR EMPÍRICO
    // -------------------------------------------------------------------------
    public static void compararBusqueda(String codigo, ListaEnlazada lista, ArbolBinarioBusqueda arbol, TablaHash tablaHash) {
        System.out.println("=================================================================");
        System.out.println(" BÚSQUEDA DEL CÓDIGO: '" + codigo + "'");
        System.out.println("=================================================================");

        // Búsqueda en Lista
        ResultadoBusqueda resLista = lista.buscar(codigo);

        // Búsqueda en Árbol Binario
        ResultadoBusqueda resArbol = arbol.buscar(codigo);

        // Búsqueda en Tabla Hash
        ResultadoBusqueda resHash = tablaHash.buscar(codigo);

        // Mostrar Resultado del Producto
        if (resLista.producto != null) {
            System.out.println("--> Resultado de la búsqueda: " + resLista.producto);
        } else {
            System.out.println("--> Resultado de la búsqueda: [ PRODUCTO NO ENCONTRADO ]");
        }

        System.out.println("\n--- COMPARATIVA DE COMPLEJIDAD EMPÍRICA ---");
        System.out.println(" 1. Lista Enlazada  : " + resLista.comparaciones + " comparaciones (Recorrido O(n))");
        System.out.println(" 2. Árbol Binario   : " + resArbol.comparaciones + " comparaciones (Búsqueda O(log n))");
        System.out.println(" 3. Tabla Hash      : " + resHash.comparaciones + " accesos/recorridos (Acceso O(1) promedio)");
        System.out.println("=================================================================\n");
    }

    public static void main(String[] args) {
        // Inicializar las 3 estructuras
        ListaEnlazada lista = new ListaEnlazada();
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        TablaHash tablaHash = new TablaHash(11); // Tamaño primo para mejor distribución de hash

        // Arreglo de productos de prueba para cargar en las 3 estructuras
        Producto[] productos = {
            new Producto("P101", "Teclado Mecánico", 15000.0),
            new Producto("P102", "Mouse Óptico", 4500.0),
            new Producto("P103", "Monitor 24 Pulgadas", 85000.0),
            new Producto("P104", "Auriculares Bluetooth", 12000.0),
            new Producto("P105", "Disco SSD 1TB", 35000.0),
            new Producto("P106", "Memoria RAM 16GB", 22000.0),
            new Producto("P107", "Placa de Video RTX 3060", 210000.0),
            new Producto("P108", "Fuente de Alimentación 750W", 28000.0),
            new Producto("P109", "Gabinete Gamer", 19000.0),
            new Producto("P110", "Webcam Full HD", 14000.0)
        };

        // Carga simultánea en las 3 estructuras
        for (Producto p : productos) {
            lista.insertar(p);
            arbol.insertar(p);
            tablaHash.insertar(p);
        }

        System.out.println("Carga masiva de 10 productos completada en las 3 estructuras.\n");

        // CASO DE PRUEBA 1: Búsqueda de elemento al final de la lista enlazada
        compararBusqueda("P101", lista, arbol, tablaHash);

        // CASO DE PRUEBA 2: Búsqueda de un elemento intermedio
        compararBusqueda("P105", lista, arbol, tablaHash);

        // CASO DE PRUEBA 3: Búsqueda de un producto inexistente
        compararBusqueda("P999", lista, arbol, tablaHash);
    }
}
