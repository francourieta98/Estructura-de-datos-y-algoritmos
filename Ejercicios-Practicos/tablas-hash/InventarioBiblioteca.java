/*
 * =================================================================================
 * PROMPT INICIAL UTILIZADO:
 * "Necesito resolver el Ejercicio 6 en Java para OpenCode:
 *  - Problema: Implementar un sistema de Inventario de Biblioteca por ISBN mediante una Tabla Hash.
 *  - Tratamiento de Colisiones (Misma Posición):
 *    Cuando dos códigos ISBN generan la misma posición en la tabla (colisión hash), el sistema
 *    las gestiona mediante el método de Encadenamiento (Chaining). Cada posición (bucket) de la tabla
 *    contiene una lista enlazada simple de nodos de tipo Libro. Al ocurrir una colisión, el nuevo libro
 *    se inserta al inicio de la lista enlazada de dicho bucket, evitando la pérdida de datos y manteniendo
 *    un acceso eficiente en O(1) promedio.
 *  - Estructura de Datos: Tabla Hash basada en Arreglo con Manejo de Colisiones por Encadenamiento.
 *  - Clave de búsqueda: El ISBN del libro (String, ej: '978-3-16-148410-0' o '9783161484100').
 *  - Función Hash: Polynomial Rolling Hash con base 31 aplicado sobre la cadena del ISBN limpia 
 *    (removiendo guiones o espacios) y ajustado mediante módulo (%) a la capacidad de la tabla.
 *  - Operaciones a implementar:
 *      1. insertarLibro(String isbn, String titulo, String autor, int cantidadDisponible)
 *      2. buscarPorIsbn(String isbn)
 *      3. prestarLibro(String isbn) -> Reduce la cantidad disponible en 1 si hay stock.
 *      4. devolverLibro(String isbn) -> Incrementa la cantidad disponible en 1.
 *      5. eliminarLibro(String isbn)
 *      6. imprimirEstadoTabla() -> Muestra visualmente las posiciones y las colisiones encadenadas.
 *  - Casos de prueba a validar:
 *      1. Inserción de libros con y sin colisiones intencionales de ISBN.
 *      2. Búsqueda exitosa y fallida por ISBN.
 *      3. Préstamo de libro con stock disponible y sin stock (límite).
 *      4. Devolución de libro con incremento de stock.
 *      5. Eliminación de libro en la tabla (inicio/medio de lista encadenada).
 *      6. Impresión del estado completo de la tabla hash."
 *
 * AJUSTES POSTERIORES AL PROMPT:
 * No se realizaron ajustes posteriores. El diseño inicial contempla el control de límites
 * de stock en préstamos/devoluciones y el encadenamiento para colisiones por ISBN.
 * =================================================================================
 */

public class InventarioBiblioteca {

    // Clase Nodo que representa a un Libro
    static class Libro {
        String isbn;
        String titulo;
        String autor;
        int cantidadDisponible;
        Libro siguiente;

        public Libro(String isbn, String titulo, String autor, int cantidadDisponible) {
            this.isbn = isbn;
            this.titulo = titulo;
            this.autor = autor;
            this.cantidadDisponible = cantidadDisponible;
            this.siguiente = null;
        }

        @Override
        public String toString() {
            return "[" + isbn + " | '" + titulo + "' | Autor: " + autor + " | Disp: " + cantidadDisponible + "]";
        }
    }

    // Clase Tabla Hash para el Inventario de la Biblioteca
    static class TablaHashBiblioteca {
        private Libro[] tabla;
        private int capacidad;

        public TablaHashBiblioteca(int capacidad) {
            this.capacidad = capacidad;
            this.tabla = new Libro[capacidad];
        }

        // Función Hash para convertir el ISBN en un índice válido
        private int funcionHash(String isbn) {
            // Limpieza de guiones y espacios del ISBN
            String isbnLimpio = isbn.replaceAll("[^0-9X-x]", "").toUpperCase();
            
            if (isbnLimpio.isEmpty()) {
                isbnLimpio = isbn;
            }

            int hash = 0;
            for (int i = 0; i < isbnLimpio.length(); i++) {
                hash = (31 * hash + isbnLimpio.charAt(i)) % capacidad;
            }

            return Math.abs(hash);
        }

        // 1. Insertar libro (si el ISBN ya existe, actualiza los datos)
        public void insertarLibro(String isbn, String titulo, String autor, int cantidadDisponible) {
            int indice = funcionHash(isbn);
            Libro actual = tabla[indice];

            // Si el libro ya existe, se actualizan sus datos y stock
            while (actual != null) {
                if (actual.isbn.equalsIgnoreCase(isbn.trim())) {
                    actual.titulo = titulo;
                    actual.autor = autor;
                    actual.cantidadDisponible = cantidadDisponible;
                    System.out.println("-> Libro con ISBN " + isbn + " actualizado en la posición " + indice + ".");
                    return;
                }
                actual = actual.siguiente;
            }

            // Inserción al inicio de la lista en esa posición (Manejo de Colisiones por Chaining)
            Libro nuevo = new Libro(isbn.trim(), titulo, autor, cantidadDisponible);
            nuevo.siguiente = tabla[indice];
            tabla[indice] = nuevo;
            System.out.println("-> Libro '" + titulo + "' insertado con éxito en el índice " + indice + ".");
        }

        // 2. Buscar libro por ISBN
        public Libro buscarPorIsbn(String isbn) {
            int indice = funcionHash(isbn);
            Libro actual = tabla[indice];

            while (actual != null) {
                if (actual.isbn.equalsIgnoreCase(isbn.trim())) {
                    return actual;
                }
                actual = actual.siguiente;
            }
            return null; // No encontrado
        }

        // 3. Prestar libro (reduce stock en 1)
        public boolean prestarLibro(String isbn) {
            Libro libro = buscarPorIsbn(isbn);
            if (libro == null) {
                System.out.println("-> Error en préstamo: No se encontró el libro con ISBN " + isbn + ".");
                return false;
            }

            if (libro.cantidadDisponible > 0) {
                libro.cantidadDisponible--;
                System.out.println("-> Préstamo realizado: '" + libro.titulo + "'. Unidades restantes: " + libro.cantidadDisponible);
                return true;
            } else {
                System.out.println("-> Sin ejemplares disponibles para prestar: '" + libro.titulo + "'.");
                return false;
            }
        }

        // 4. Devolver libro (incrementa stock en 1)
        public boolean devolverLibro(String isbn) {
            Libro libro = buscarPorIsbn(isbn);
            if (libro != null) {
                libro.cantidadDisponible++;
                System.out.println("-> Devolución registrada: '" + libro.titulo + "'. Unidades disponibles: " + libro.cantidadDisponible);
                return true;
            }
            System.out.println("-> Error en devolución: No se encontró el libro con ISBN " + isbn + ".");
            return false;
        }

        // 5. Eliminar libro por ISBN
        public boolean eliminarLibro(String isbn) {
            int indice = funcionHash(isbn);
            Libro actual = tabla[indice];
            Libro anterior = null;

            while (actual != null) {
                if (actual.isbn.equalsIgnoreCase(isbn.trim())) {
                    if (anterior == null) {
                        tabla[indice] = actual.siguiente; // Primer nodo de la lista
                    } else {
                        anterior.siguiente = actual.siguiente; // Saltear nodo
                    }
                    System.out.println("-> Libro con ISBN " + isbn + " ('" + actual.titulo + "') eliminado del inventario.");
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }

            System.out.println("-> Error: No se pudo eliminar, el ISBN " + isbn + " no existe.");
            return false;
        }

        // 6. Imprimir el estado de la tabla mostrando colisiones
        public void imprimirEstadoTabla() {
            System.out.println("\n=== ESTADO COMPLETO DEL INVENTARIO DE BIBLIOTECA ===");
            for (int i = 0; i < capacidad; i++) {
                System.out.print("Posición [" + i + "]: ");
                Libro actual = tabla[i];

                if (actual == null) {
                    System.out.println("[ VACÍA ]");
                } else {
                    while (actual != null) {
                        System.out.print(actual.toString());
                        if (actual.siguiente != null) {
                            System.out.print(" ---> (Colisión Hash) ");
                        }
                        actual = actual.siguiente;
                    }
                    System.out.println();
                }
            }
            System.out.println("===================================================\n");
        }
    }

    // Casos de prueba
    public static void main(String[] args) {
        // Capacidad reducida (5) para forzar y visualizar la ocurrencia de colisiones de ISBN
        TablaHashBiblioteca biblioteca = new TablaHashBiblioteca(5);

        System.out.println("--- 1. INSERCIÓN DE LIBROS ---");
        biblioteca.insertarLibro("978-0-13-468599-1", "Effective Java", "Joshua Bloch", 3);
        biblioteca.insertarLibro("978-0-13-235088-4", "Clean Code", "Robert C. Martin", 1);
        biblioteca.insertarLibro("978-0-201-63361-0", "Design Patterns", "Erich Gamma", 2);
        biblioteca.insertarLibro("978-0-321-35668-0", "Java Concurrency", "Brian Goetz", 4);
        biblioteca.insertarLibro("978-0-596-00920-5", "Head First Java", "Kathy Sierra", 2);

        biblioteca.imprimirEstadoTabla();

        System.out.println("--- 2. BÚSQUEDA DE LIBRO ---");
        Libro buscado = biblioteca.buscarPorIsbn("978-0-13-235088-4");
        System.out.println("Búsqueda Clean Code: " + (buscado != null ? buscado : "No encontrado"));

        Libro noExiste = biblioteca.buscarPorIsbn("978-0-00-000000-0");
        System.out.println("Búsqueda ISBN Inexistente: " + (noExiste != null ? noExiste : "No encontrado"));

        System.out.println("\n--- 3. OPERACIONES DE PRÉSTAMO Y DEVOLUCIÓN ---");
        // Préstamo exitoso de Clean Code
        biblioteca.prestarLibro("978-0-13-235088-4");
        
        // Intento de préstamo sin ejemplares disponibles
        biblioteca.prestarLibro("978-0-13-235088-4");

        // Devolución de ejemplar
        biblioteca.devolverLibro("978-0-13-235088-4");

        biblioteca.imprimirEstadoTabla();

        System.out.println("--- 4. ELIMINACIÓN DE LIBRO ---");
        biblioteca.eliminarLibro("978-0-201-63361-0"); // Eliminar Design Patterns
        biblioteca.eliminarLibro("978-0-00-000000-0"); // Eliminar inexistente

        biblioteca.imprimirEstadoTabla();
    }
}
