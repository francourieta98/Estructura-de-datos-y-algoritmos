/*
 * =================================================================================
 * PROMPT INICIAL UTILIZADO:
 * "Necesito resolver el Ejercicio 4 en Java para OpenCode:
 *  - Problema: Implementar un Diccionario de Palabras mediante una Tabla Hash.
 *  - Razonamiento del Hash para String:
 *    1. Para convertir una palabra (String) en un índice, se utiliza el algoritmo de 
 *       Polynomial Rolling Hash (multiplicación acumulativa por una constante prima, típicamente 31).
 *    2. Se itera caracter por caracter convirtiendo cada uno a su valor ASCII.
 *    3. Se combina el valor hash previo multiplicando por 31 y sumando el valor del caracter.
 *    4. Para evitar desbordamientos numéricos y mantener el índice dentro del rango válido
 *       de la tabla, se aplica la operación módulo (%) respecto a la capacidad de la tabla 
 *       y se utiliza Math.abs() para asegurar un resultado siempre positivo.
 *  - Estructura de Datos: Tabla Hash con Manejo de Colisiones por Encadenamiento (Chaining).
 *  - Clave de búsqueda: La palabra (String, no sensible a mayúsculas/minúsculas).
 *  - Operaciones a implementar:
 *      1. agregarPalabra(String palabra, String definicion)
 *      2. buscarDefinicion(String palabra)
 *      3. modificarDefinicion(String palabra, String nuevaDefinicion)
 *      4. eliminarPalabra(String palabra)
 *      5. imprimirTablaPosiciones() -> Muestra cada posición/índice y las palabras almacenadas.
 *  - Casos de prueba a validar:
 *      1. Agregar varias palabras y sus definiciones.
 *      2. Forzar colisiones en el mismo índice de la tabla para verificar encadenamiento.
 *      3. Búsqueda exitosa y de palabra inexistente.
 *      4. Modificación de definición existente e inexistente.
 *      5. Eliminación de una palabra en la tabla.
 *      6. Impresión completa mostrando las posiciones en la tabla."
 *
 * AJUSTES POSTERIORES AL PROMPT:
 * No se realizaron ajustes posteriores. Se incluyó la normalización de palabras a minúsculas
 * para garantizar que la búsqueda sea insensible a mayúsculas/minúsculas.
 * =================================================================================
 */

public class DiccionarioPalabras {

    // Clase Nodo que representa una Entrada del Diccionario
    static class EntradaDiccionario {
        String palabra;
        String definicion;
        EntradaDiccionario siguiente;

        public EntradaDiccionario(String palabra, String definicion) {
            this.palabra = palabra;
            this.definicion = definicion;
            this.siguiente = null;
        }

        @Override
        public String toString() {
            return "[" + palabra + ": \"" + definicion + "\"]";
        }
    }

    // Clase Tabla Hash para el Diccionario
    static class TablaHashDiccionario {
        private EntradaDiccionario[] tabla;
        private int capacidad;

        public TablaHashDiccionario(int capacidad) {
            this.capacidad = capacidad;
            this.tabla = new EntradaDiccionario[capacidad];
        }

        // Generación de Hash para una clave de tipo String
        private int funcionHash(String palabra) {
            String clave = palabra.toLowerCase().trim(); // Normalización
            int hash = 0;
            
            // Algoritmo polinomial con constante prima 31
            for (int i = 0; i < clave.length(); i++) {
                hash = (31 * hash + clave.charAt(i)) % capacidad;
            }

            return Math.abs(hash);
        }

        // 1. Agregar una palabra con su definición
        public void agregarPalabra(String palabra, String definicion) {
            String clave = palabra.toLowerCase().trim();
            int indice = funcionHash(clave);
            EntradaDiccionario actual = tabla[indice];

            // Si la palabra ya existe, actualiza su definición
            while (actual != null) {
                if (actual.palabra.equalsIgnoreCase(clave)) {
                    actual.definicion = definicion;
                    System.out.println("-> Definición de '" + palabra + "' actualizada en el índice " + indice + ".");
                    return;
                }
                actual = actual.siguiente;
            }

            // Inserción al inicio de la lista en esa posición (Chaining)
            EntradaDiccionario nuevaEntrada = new EntradaDiccionario(clave, definicion);
            nuevaEntrada.siguiente = tabla[indice];
            tabla[indice] = nuevaEntrada;
            System.out.println("-> Palabra '" + palabra + "' agregada con éxito en la posición " + indice + ".");
        }

        // 2. Buscar una definición por palabra
        public String buscarDefinicion(String palabra) {
            int indice = funcionHash(palabra);
            EntradaDiccionario actual = tabla[indice];

            while (actual != null) {
                if (actual.palabra.equalsIgnoreCase(palabra.trim())) {
                    return actual.definicion;
                }
                actual = actual.siguiente;
            }

            return null; // Palabra no encontrada
        }

        // 3. Modificar una definición
        public boolean modificarDefinicion(String palabra, String nuevaDefinicion) {
            int indice = funcionHash(palabra);
            EntradaDiccionario actual = tabla[indice];

            while (actual != null) {
                if (actual.palabra.equalsIgnoreCase(palabra.trim())) {
                    actual.definicion = nuevaDefinicion;
                    System.out.println("-> Definición de '" + palabra + "' modificada correctamente.");
                    return true;
                }
                actual = actual.siguiente;
            }

            System.out.println("-> Error: La palabra '" + palabra + "' no existe en el diccionario.");
            return false;
        }

        // 4. Eliminar una palabra
        public boolean eliminarPalabra(String palabra) {
            int indice = funcionHash(palabra);
            EntradaDiccionario actual = tabla[indice];
            EntradaDiccionario anterior = null;

            while (actual != null) {
                if (actual.palabra.equalsIgnoreCase(palabra.trim())) {
                    if (anterior == null) {
                        tabla[indice] = actual.siguiente; // Primer elemento de la lista
                    } else {
                        anterior.siguiente = actual.siguiente; // Saltear nodo
                    }
                    System.out.println("-> Palabra '" + palabra + "' eliminada del diccionario.");
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }

            System.out.println("-> Error: No se pudo eliminar '" + palabra + "', no fue encontrada.");
            return false;
        }

        // 5. Imprimir la tabla mostrando en qué posición quedó cada palabra
        public void imprimirTablaPosiciones() {
            System.out.println("\n=== ESTADO DEL DICCIONARIO POR POSICIONES EN LA TABLA HASH ===");
            for (int i = 0; i < capacidad; i++) {
                System.out.print("Posición [" + i + "]: ");
                EntradaDiccionario actual = tabla[i];

                if (actual == null) {
                    System.out.println("[ VACÍA ]");
                } else {
                    while (actual != null) {
                        System.out.print(actual.toString());
                        if (actual.siguiente != null) {
                            System.out.print(" ---> (Colisión) ");
                        }
                        actual = actual.siguiente;
                    }
                    System.out.println();
                }
            }
            System.out.println("===============================================================\n");
        }
    }

    // Casos de prueba
    public static void main(String[] args) {
        // Capacidad pequeña (5) para observar el mapeo de índices y las colisiones
        TablaHashDiccionario diccionario = new TablaHashDiccionario(5);

        System.out.println("--- 1. AGREGAR PALABRAS ---");
        diccionario.agregarPalabra("Algoritmo", "Conjunto ordenado de operaciones para resolver un problema.");
        diccionario.agregarPalabra("Variable", "Espacio de memoria reservado para almacenar un valor.");
        diccionario.agregarPalabra("Funcion", "Bloque de código diseñado para realizar una tarea específica.");
        diccionario.agregarPalabra("Clase", "Plantilla para la creación de objetos según un modelo predefinido.");
        diccionario.agregarPalabra("Objeto", "Instancia de una clase que posee estado, comportamiento e identidad.");

        diccionario.imprimirTablaPosiciones();

        System.out.println("--- 2. BÚSQUEDA DE DEFINICIONES ---");
        String defAlgoritmo = diccionario.buscarDefinicion("Algoritmo");
        System.out.println("Definición 'Algoritmo': " + (defAlgoritmo != null ? defAlgoritmo : "No encontrada"));

        String defInexistente = diccionario.buscarDefinicion("Recursion");
        System.out.println("Definición 'Recursion': " + (defInexistente != null ? defInexistente : "No encontrada"));

        System.out.println("\n--- 3. MODIFICACIÓN DE DEFINICIÓN ---");
        diccionario.modificarDefinicion("Variable", "Contenedor de datos cuyo valor puede cambiar durante la ejecución.");
        diccionario.modificarDefinicion("Puntero", "Inexistente en este ejemplo");

        diccionario.imprimirTablaPosiciones();

        System.out.println("--- 4. ELIMINACIÓN DE PALABRA ---");
        diccionario.eliminarPalabra("Clase");
        diccionario.eliminarPalabra("Polimorfismo"); // Inexistente

        diccionario.imprimirTablaPosiciones();
    }
}
