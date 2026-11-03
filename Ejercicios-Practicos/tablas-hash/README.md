/*
 * =================================================================================
 * PROMPT INICIAL UTILIZADO:
 * "Necesito resolver el siguiente problema en Java para OpenCode:
 *  - Problema: Implementar una Tabla Hash para almacenar y gestionar productos de un comercio.
 *  - Estructura de Datos: Tabla Hash basada en Arreglo con Manejo de Colisiones por Encadenamiento
 *    (Chaining / Listas Enlazadas en cada posición del arreglo).
 *  - Clave de búsqueda: El código del producto (String, ej: 'PROD-101' o 'A12').
 *  - Función Hash: Algoritmo polynomial rolling hash (o hashing incremental basado en 31)
 *    aplicado sobre el String del código y ajustado con la operación módulo (%) al tamaño de la tabla.
 *  - Manejo de colisiones: Encadenamiento externo (Chaining). Cada casilla del arreglo contiene una
 *    lista enlazada simple de nodos Producto.
 *  - Operaciones a implementar:
 *      1. insertar(String codigo, String nombre, double precio, int stock)
 *      2. buscar(String codigo)
 *      3. eliminar(String codigo)
 *      4. actualizarPrecio(String codigo, double nuevoPrecio)
 *      5. actualizarStock(String codigo, int nuevoStock)
 *      6. imprimirTablaCompleta() -> Debe mostrar visualmente los índices y los elementos encadenados (colisiones).
 *  - Casos de prueba a validar:
 *      1. Inserción correcta de varios productos.
 *      2. Ocurrencia intencional de colisiones (mismo índice) para verificar el encadenamiento.
 *      3. Búsqueda exitosa y fallida.
 *      4. Actualización de precio y stock existente e inexistente.
 *      5. Eliminación de un elemento al inicio, medio o fin de una lista colisionada.
 *      6. Impresión visual de la tabla mostrando los buckets y sus colisiones."
 *
 * AJUSTES POSTERIORES AL PROMPT:
 * No se realizaron ajustes posteriores. El prompt inicial contempló todos los casos
 * límites y requerimientos funcionales solicitados en la consigna.
 * =================================================================================
 */

public class Ejercicio1 {

    // Clase que representa el nodo de la lista enlazada (Producto)
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
            return "[" + codigo + " | " + nombre + " | $" + precio + " | Stk: " + stock + "]";
        }
    }

    // Clase que representa la Tabla Hash
    static class TablaHashProductos {
        private NodoProducto[] tabla;
        private int capacidad;

        public TablaHashProductos(int capacidad) {
            this.capacidad = capacidad;
            this.tabla = NodoProducto[capacidad];
        }

        // Función Hash: Basada en el valor ASCII de la clave String con módulo capacidad
        private int funcionHash(String codigo) {
            int hash = 0;
            for (int i = 0; i < codigo.length(); i++) {
                hash = (31 * hash + codigo.charAt(i)) % capacidad;
            }
            return Math.abs(hash);
        }

        // 1. Insertar un producto (si la clave ya existe, actualiza los datos)
        public void insertar(String codigo, String nombre, double precio, int stock) {
            int indice = funcionHash(codigo);
            NodoProducto actual = tabla[indice];

            // Verificar si el producto ya existe en la lista enlazada
            while (actual != null) {
                if (actual.codigo.equals(codigo)) {
                    actual.nombre = nombre;
                    actual.precio = precio;
                    actual.stock = stock;
                    System.out.println("-> Producto " + codigo + " actualizado correctamente en el índice " + indice + ".");
                    return;
                }
                actual = actual.siguiente;
            }

            // Si no existe, se inserta al inicio de la lista en el bucket
            NodoProducto nuevo = new NodoProducto(codigo, nombre, precio, stock);
            nuevo.siguiente = tabla[indice];
            tabla[indice] = nuevo;
            System.out.println("-> Producto " + codigo + " (" + nombre + ") insertado con éxito en el índice " + indice + ".");
        }

        // 2. Buscar un producto por código
        public NodoProducto buscar(String codigo) {
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

        // 3. Eliminar un producto por código
        public boolean eliminar(String codigo) {
            int indice = funcionHash(codigo);
            NodoProducto actual = tabla[indice];
            NodoProducto anterior = null;

            while (actual != null) {
                if (actual.codigo.equals(codigo)) {
                    if (anterior == null) {
                        tabla[indice] = actual.siguiente; // Era el primer elemento
                    } else {
                        anterior.siguiente = actual.siguiente; // Saltear nodo
                    }
                    System.out.println("-> Producto " + codigo + " eliminado exitosamente.");
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }

            System.out.println("-> No se pudo eliminar. Producto " + codigo + " no encontrado.");
            return false;
        }

        // 4. Actualizar el precio de un producto
        public boolean actualizarPrecio(String codigo, double nuevoPrecio) {
            NodoProducto producto = buscar(codigo);
            if (producto != null) {
                producto.precio = nuevoPrecio;
                System.out.println("-> Precio de " + codigo + " actualizado a $" + nuevoPrecio + ".");
                return true;
            }
            System.out.println("-> Error al actualizar precio: Producto " + codigo + " no encontrado.");
            return false;
        }

        // 5. Actualizar el stock de un producto
        public boolean actualizarStock(String codigo, int nuevoStock) {
            NodoProducto producto = buscar(codigo);
            if (producto != null) {
                producto.stock = nuevoStock;
                System.out.println("-> Stock de " + codigo + " actualizado a " + nuevoStock + " unidades.");
                return true;
            }
            System.out.println("-> Error al actualizar stock: Producto " + codigo + " no encontrado.");
            return false;
        }

        // 6. Imprimir la tabla completa mostrando las colisiones
        public void imprimirTablaCompleta() {
            System.out.println("\n=== ESTADO ACTUAL DE LA TABLA HASH (BUCKETS Y COLISIONES) ===");
            for (int i = 0; i < capacidad; i++) {
                System.out.print("Bucket " + i + ": ");
                NodoProducto actual = tabla[i];
                if (actual == null) {
                    System.out.println("[ VACÍO ]");
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
            System.out.println("=============================================================\n");
        }
    }

    // Método principal para ejecutar los casos de prueba requeridos
    public static void main(String[] args) {
        // Se define un tamaño chico (5) para forzar y observar el encadenamiento por colisión
        TablaHashProductos inventario = new TablaHashProductos(5);

        System.out.println("--- 1. PRUEBA DE INSERCIÓN Y COLISIONES ---");
        inventario.insertar("P101", "Arroz 1kg", 1200.50, 50);
        inventario.insertar("P102", "Fideos 500g", 850.00, 30);
        inventario.insertar("P103", "Aceite 1L", 2300.00, 20);
        inventario.insertar("P104", "Leche 1L", 950.00, 40);
        inventario.insertar("P105", "Galletitas", 600.00, 100);
        inventario.insertar("P106", "Azúcar 1kg", 1100.00, 15); // Fuerza colisión en alguno de los buckets

        inventario.imprimirTablaCompleta();

        System.out.println("--- 2. PRUEBA DE BÚSQUEDA ---");
        NodoProducto buscado = inventario.buscar("P103");
        if (buscado != null) {
            System.out.println("Producto encontrado: " + buscado);
        } else {
            System.out.println("Producto no encontrado.");
        }

        NodoProducto noExiste = inventario.buscar("P999");
        System.out.println("Búsqueda P999 (esperado null): " + noExiste);

        System.out.println("\n--- 3. PRUEBA DE ACTUALIZACIONES ---");
        inventario.actualizarPrecio("P103", 2500.00);
        inventario.actualizarStock("P101", 65);
        inventario.actualizarPrecio("P999", 100.00); // Intento sobre inexistente

        inventario.imprimirTablaCompleta();

        System.out.println("--- 4. PRUEBA DE ELIMINACIÓN ---");
        inventario.eliminar("P102"); // Eliminar producto
        inventario.eliminar("P999"); // Eliminar inexistente

        inventario.imprimirTablaCompleta();
    }
}
