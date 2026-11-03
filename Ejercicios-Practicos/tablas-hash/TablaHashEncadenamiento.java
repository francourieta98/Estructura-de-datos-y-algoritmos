/*
 * =================================================================================
 * PROMPT INICIAL UTILIZADO:
 * "Necesito resolver el Ejercicio 8 en Java para OpenCode:
 *  - Problema: Implementar una Tabla Hash de productos utilizando Encadenamiento Separado (Chaining).
 *  - Explicación de por qué el encadenamiento simplifica la eliminación:
 *    A diferencia de los métodos de direccionamiento abierto (como la exploración lineal), en el 
 *    encadenamiento separado cada casillero de la tabla contiene una lista enlazada independiente. 
 *    Esto simplifica drásticamente la eliminación porque desvincular o remover un nodo de una lista 
 *    enlazada es una operación local: solo requiere reajustar los punteros del nodo anterior y posterior 
 *    (O(1) tras encontrar el elemento). No se requiere el uso de marcas de borrado lógico (Tombstones/DELETED) 
 *    ni existe el riesgo de romper la secuencia de búsqueda de otros elementos que hayan colisionado en la 
 *    misma posición o en posiciones contiguas.
 *  - Estructura de Datos: Tabla Hash basada en un Arreglo de Listas Enlazadas Simples.
 *  - Clave de búsqueda: El código del producto (String, ej: 'PROD-01').
 *  - Función Hash: Polynomial Rolling Hash con base 31 sobre la clave String ajustada con módulo 
 *    respecto a la capacidad de la tabla.
 *  - Operaciones a implementar:
 *      1. insertarProducto(String codigo, String nombre, double precio, int stock)
 *      2. buscarProducto(String codigo)
 *      3. eliminarProducto(String codigo)
 *      4. imprimirTablaConListas() -> Muestra cada posición con su lista enlazada de productos.
 *      5. contarProductosPorPosicion() -> Muestra la cantidad exacta de productos en cada bucket.
 *      6. obtenerPosicionMasColisionada() -> Informa qué posición tiene la lista más larga y su cantidad de elementos.
 *  - Casos de prueba a validar:
 *      1. Inserción de varios productos generando colisiones deliberadas.
 *      2. Búsqueda de un producto existente e inexistente.
 *      3. Eliminación de productos en distintas posiciones de la lista (inicio, medio y final).
 *      4. Conteo correcto de productos por casillero.
 *      5. Identificación de la posición con mayor número de colisiones.
 *      6. Impresión general del estado de la tabla."
 *
 * AJUSTES POSTERIORES AL PROMPT:
 * No se realizaron ajustes posteriores. El prompt abarca todas las métricas de monitoreo de 
 * colisiones solicitadas y la justificación teórica requerida.
 * =================================================================================
 */

public class TablaHashEncadenamiento {

    // Clase Nodo que representa a un Producto dentro de la lista enlazada
    static class NodoProducto {
        String codigo;
        String nombre;
        double precio;
        int stock;
        NodoProducto siguiente;

        public NodoProducto(String codigo, String nombre, double precio, int stock) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
            this.siguiente = null;
        }

        @Override
        public String toString() {
            return "[" + codigo + " | " + nombre + " | $" + precio + " | Stock: " + stock + "]";
        }
    }

    // Clase que implementa la Tabla Hash con Encadenamiento Separado
    static class TablaHashChaining {
        private NodoProducto[] tabla;
        private int capacidad;

        public TablaHashChaining(int capacidad) {
            this.capacidad = capacidad;
            this.tabla = new NodoProducto[capacidad];
        }

        // Función Hash para claves tipo String
        private int funcionHash(String codigo) {
            String clave = codigo.trim();
            int hash = 0;
            for (int i = 0; i < clave.length(); i++) {
                hash = (31 * hash + clave.charAt(i)) % capacidad;
            }
            return Math.abs(hash);
        }

        // 1. Insertar Producto (o actualizar si la clave ya existe)
        public void insertarProducto(String codigo, String nombre, double precio, int stock) {
            int indice = funcionHash(codigo);
            NodoProducto actual = tabla[indice];

            // Si el producto ya existe en la lista enlazada, se actualizan sus valores
            while (actual != null) {
                if (actual.codigo.equals(codigo)) {
                    actual.nombre = nombre;
                    actual.precio = precio;
                    actual.stock = stock;
                    System.out.println("-> Producto " + codigo + " actualizado en la posición " + indice + ".");
                    return;
                }
                actual = actual.siguiente;
            }

            // Inserción limpia al inicio de la lista enlazada de esa posición
            NodoProducto nuevo = new NodoProducto(codigo, nombre, precio, stock);
            nuevo.siguiente = tabla[indice];
            tabla[indice] = nuevo;
            System.out.println("-> Producto '" + nombre + "' (" + codigo + ") insertado en la posición " + indice + ".");
        }

        // 2. Buscar Producto por Código
        public NodoProducto buscarProducto(String codigo) {
            int indice = funcionHash(codigo);
            NodoProducto actual = tabla[indice];

            while (actual != null) {
                if (actual.codigo.equals(codigo)) {
                    return actual;
                }
                actual = actual.siguiente;
            }
            return null; // No encontrado
        }

        // 3. Eliminar Producto por Código
        public boolean eliminarProducto(String codigo) {
            int indice = funcionHash(codigo);
            NodoProducto actual = tabla[indice];
            NodoProducto anterior = null;

            while (actual != null) {
                if (actual.codigo.equals(codigo)) {
                    if (anterior == null) {
                        // Eliminación del primer nodo de la lista
                        tabla[indice] = actual.siguiente;
                    } else {
                        // Eliminación de nodo intermedio o final reconectando el puntero 'siguiente'
                        anterior.siguiente = actual.siguiente;
                    }
                    System.out.println("-> Producto " + codigo + " eliminado exitosamente del índice " + indice + ".");
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }

            System.out.println("-> Error: No se pudo eliminar, el producto " + codigo + " no existe.");
            return false;
        }

        // 4. Imprimir cada posición con su lista enlazada
        public void imprimirTablaConListas() {
            System.out.println("\n=== ESTADO DE LA TABLA HASH (ENCADENAMIENTO SEPARADO) ===");
            for (int i = 0; i < capacidad; i++) {
                System.out.print("Posición [" + i + "]: ");
                NodoProducto actual = tabla[i];

                if (actual == null) {
                    System.out.println("[ VACÍA ]");
                } else {
                    while (actual != null) {
                        System.out.print(actual.toString());
                        if (actual.siguiente != null) {
                            System.out.print(" -> (Colisión) ");
                        }
                        actual = actual.siguiente;
                    }
                    System.out.println();
                }
            }
            System.out.println("=========================================================\n");
        }

        // 5. Contar cuántos productos hay en cada posición
        public void contarProductosPorPosicion() {
            System.out.println("=== CONTEO DE ELEMENTOS POR POSICIÓN ===");
            for (int i = 0; i < capacidad; i++) {
                int contador = 0;
                NodoProducto actual = tabla[i];
                while (actual != null) {
                    contador++;
                    actual = actual.siguiente;
                }
                System.out.println("Posición [" + i + "]: " + contador + " producto(s)");
            }
            System.out.println("=======================================\n");
        }

        // 6. Informar cuál fue la posición con más colisiones
        public void obtenerPosicionMasColisionada() {
            int maxElementos = 0;
            int posicionMax = -1;

            for (int i = 0; i < capacidad; i++) {
                int contador = 0;
                NodoProducto actual = tabla[i];
                while (actual != null) {
                    contador++;
                    actual = actual.siguiente;
                }

                if (contador > maxElementos) {
                    maxElementos = contador;
                    posicionMax = i;
                }
            }

            if (posicionMax != -1 && maxElementos > 0) {
                System.out.println("-> La posición con MÁS COLISIONES es la [" + posicionMax + "] con un total de " 
                                   + maxElementos + " elementos (" + (maxElementos - 1) + " colisión/es).");
            } else {
                System.out.println("-> La tabla está completamente vacía.");
            }
        }
    }

    // Casos de prueba
    public static void main(String[] args) {
        // Tamaño pequeño (4) para provocar acumulación de colisiones en los buckets
        TablaHashChaining inventario = new TablaHashChaining(4);

        System.out.println("--- 1. INSERCIÓN DE PRODUCTOS CON COLISIONES ---");
        inventario.insertarProducto("P01", "Teclado Mecánico", 15000.0, 10);
        inventario.insertarProducto("P02", "Mouse Gamer", 8500.0, 25);
        inventario.insertarProducto("P03", "Monitor 24", 75000.0, 5);
        inventario.insertarProducto("P04", "Auriculares", 12000.0, 15);
        inventario.insertarProducto("P05", "Pad Mouse", 3000.0, 40);
        inventario.insertarProducto("P06", "Webcam HD", 18000.0, 8);

        inventario.imprimirTablaConListas();

        System.out.println("--- 2. BÚSQUEDA DE PRODUCTO ---");
        NodoProducto buscado = inventario.buscarProducto("P03");
        System.out.println("Búsqueda P03: " + (buscado != null ? buscado : "No encontrado"));

        NodoProducto noExiste = inventario.buscarProducto("P99");
        System.out.println("Búsqueda P99: " + (noExiste != null ? noExiste : "No encontrado"));

        System.out.println("\n--- 3. MÉTRICAS DE COLISIÓN Y CONTEO ---");
        inventario.contarProductosPorPosicion();
        inventario.obtenerPosicionMasColisionada();

        System.out.println("\n--- 4. ELIMINACIÓN DE PRODUCTO ---");
        // Eliminación de un elemento de la lista encadenada sin afectar a los demás
        inventario.eliminarProducto("P01");
        inventario.eliminarProducto("P99"); // Inexistente

        inventario.imprimirTablaConListas();

        System.out.println("--- 5. MÉTRICAS ACTUALIZADAS TRAS ELIMINACIÓN ---");
        inventario.contarProductosPorPosicion();
        inventario.obtenerPosicionMasColisionada();
    }
}
