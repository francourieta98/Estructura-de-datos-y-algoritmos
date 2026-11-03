/*
 * =================================================================================
 * PROMPT INICIAL UTILIZADO:
 * "Necesito resolver el Ejercicio 3 en Java para OpenCode:
 *  - Problema: Implementar una Agenda de Contactos mediante una Tabla Hash.
 *  - Conversión de Clave a Índice: El número telefónico se recibe como un String 
 *    (ej: '+5493511234567' o '3511234567') para conservar prefijos, guiones o espacios. 
 *    Para convertirlo en un índice válido:
 *      1. Se limpian los caracteres no numéricos dejando solo los dígitos.
 *      2. Se aplica la función Hash de la multiplicación / Horner utilizando la base 31 
 *         sobre la cadena limpia de dígitos.
 *      3. Se aplica la operación módulo (%) respecto a la capacidad de la tabla 
 *         y Math.abs() para evitar índices negativos.
 *  - Estructura de Datos: Tabla Hash con Manejo de Colisiones por Encadenamiento (Chaining).
 *  - Clave de búsqueda: El número telefónico (String).
 *  - Operaciones a implementar:
 *      1. agregarContacto(String telefono, String nombre, String email, String direccion)
 *      2. buscarPorTelefono(String telefono)
 *      3. eliminarContacto(String telefono)
 *      4. modificarEmail(String telefono, String nuevoEmail)
 *      5. modificarDireccion(String telefono, String nuevaDireccion)
 *      6. mostrarContactosAgrupados() -> Muestra la tabla por casilleros/posiciones y sus colisiones.
 *  - Casos de prueba a validar:
 *      1. Inserción de contactos con teléfonos en distintos formatos.
 *      2. Ocurrencia y visualización de colisiones en las mismas posiciones.
 *      3. Búsqueda exitosa y fallida.
 *      4. Modificación de email y dirección.
 *      5. Eliminación de contactos en inicio y medio de listas encadenadas.
 *      6. Impresión de la agenda agrupada por posición de la tabla."
 *
 * AJUSTES POSTERIORES AL PROMPT:
 * No se realizaron ajustes posteriores. El diseño inicial contempla el procesamiento 
 * de teléfonos alfanuméricos/simbológicos y el encadenamiento para colisiones.
 * =================================================================================
 */

public class AgendaContactos {

    // Clase Nodo que representa a cada Contacto
    static class Contacto {
        String telefono;
        String nombre;
        String email;
        String direccion;
        Contacto siguiente;

        public Contacto(String telefono, String nombre, String email, String direccion) {
            this.telefono = telefono;
            this.nombre = nombre;
            this.email = email;
            this.direccion = direccion;
            this.siguiente = null;
        }

        @Override
        public String toString() {
            return "[" + nombre + " | Tel: " + telefono + " | Email: " + email + " | Dir: " + direccion + "]";
        }
    }

    // Clase Tabla Hash para la Agenda
    static class TablaHashAgenda {
        private Contacto[] tabla;
        private int capacidad;

        public TablaHashAgenda(int capacidad) {
            this.capacidad = capacidad;
            this.tabla = new Contacto[capacidad];
        }

        // Conversión del número telefónico a un índice válido
        private int funcionHash(String telefono) {
            // 1. Limpieza: se remueven caracteres no numéricos (+, -, espacios)
            String soloDigitos = telefono.replaceAll("[^0-9]", "");
            
            // Si el teléfono no tenía dígitos, se toma el valor del String original
            if (soloDigitos.isEmpty()) {
                soloDigitos = telefono;
            }

            // 2. Cálculo Hash polinomial con constante 31
            int hash = 0;
            for (int i = 0; i < soloDigitos.length(); i++) {
                hash = (31 * hash + soloDigitos.charAt(i)) % capacidad;
            }

            // 3. Ajuste dentro del rango [0, capacidad - 1]
            return Math.abs(hash);
        }

        // 1. Agregar contacto
        public void agregarContacto(String telefono, String nombre, String email, String direccion) {
            int indice = funcionHash(telefono);
            Contacto actual = tabla[indice];

            // Si el contacto ya existe, se actualizan los datos
            while (actual != null) {
                if (actual.telefono.equals(telefono)) {
                    actual.nombre = nombre;
                    actual.email = email;
                    actual.direccion = direccion;
                    System.out.println("-> Contacto con teléfono " + telefono + " actualizado.");
                    return;
                }
                actual = actual.siguiente;
            }

            // Insertar al inicio de la lista en esa posición (Chaining)
            Contacto nuevo = new Contacto(telefono, nombre, email, direccion);
            nuevo.siguiente = tabla[indice];
            tabla[indice] = nuevo;
            System.out.println("-> Contacto '" + nombre + "' guardado en la posición " + indice + ".");
        }

        // 2. Buscar contacto por teléfono
        public Contacto buscarPorTelefono(String telefono) {
            int indice = funcionHash(telefono);
            Contacto actual = tabla[indice];

            while (actual != null) {
                if (actual.telefono.equals(telefono)) {
                    return actual;
                }
                actual = actual.siguiente;
            }
            return null;
        }

        // 3. Eliminar contacto
        public boolean eliminarContacto(String telefono) {
            int indice = funcionHash(telefono);
            Contacto actual = tabla[indice];
            Contacto anterior = null;

            while (actual != null) {
                if (actual.telefono.equals(telefono)) {
                    if (anterior == null) {
                        tabla[indice] = actual.siguiente;
                    } else {
                        anterior.siguiente = actual.siguiente;
                    }
                    System.out.println("-> Contacto " + telefono + " eliminado exitosamente.");
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }

            System.out.println("-> No se encontró el contacto " + telefono + " para eliminar.");
            return false;
        }

        // 4. Modificar email
        public boolean modificarEmail(String telefono, String nuevoEmail) {
            Contacto contacto = buscarPorTelefono(telefono);
            if (contacto != null) {
                contacto.email = nuevoEmail;
                System.out.println("-> Email de " + contacto.nombre + " actualizado a: " + nuevoEmail);
                return true;
            }
            System.out.println("-> Error: Contacto " + telefono + " no encontrado para actualizar email.");
            return false;
        }

        // 5. Modificar dirección
        public boolean modificarDireccion(String telefono, String nuevaDireccion) {
            Contacto contacto = buscarPorTelefono(telefono);
            if (contacto != null) {
                contacto.direccion = nuevaDireccion;
                System.out.println("-> Dirección de " + contacto.nombre + " actualizada a: " + nuevaDireccion);
                return true;
            }
            System.out.println("-> Error: Contacto " + telefono + " no encontrado para actualizar dirección.");
            return false;
        }

        // 6. Mostrar contactos agrupados por posición
        public void mostrarContactosAgrupados() {
            System.out.println("\n=== AGENDA DE CONTACTOS (AGRUPADOS POR POSICIÓN DE TABLA) ===");
            for (int i = 0; i < capacidad; i++) {
                System.out.print("Posición [" + i + "]: ");
                Contacto actual = tabla[i];

                if (actual == null) {
                    System.out.println("[ VACÍA ]");
                } else {
                    while (actual != null) {
                        System.out.print(actual.toString());
                        if (actual.siguiente != null) {
                            System.out.print(" --> (Colisión) ");
                        }
                        actual = actual.siguiente;
                    }
                    System.out.println();
                }
            }
            System.out.println("================================================================\n");
        }
    }

    // Casos de prueba
    public static void main(String[] args) {
        // Capacidad pequeña (5) para observar cómo se agrupan los contactos en las posiciones
        TablaHashAgenda agenda = new TablaHashAgenda(5);

        System.out.println("--- 1. AGREGAR CONTACTOS ---");
        agenda.agregarContacto("+549351111111", "Carlos Gómez", "carlos@mail.com", "Av. Colón 123");
        agenda.agregarContacto("3512222222", "Laura Paez", "laura@mail.com", "Calle Jujuy 456");
        agenda.agregarContacto("3513333333", "Marcos Rivas", "marcos@mail.com", "Av. Patria 789");
        agenda.agregarContacto("+549351444444", "Sofia Lopez", "sofia@mail.com", "Belgrano 321");
        agenda.agregarContacto("3515555555", "Diego Diaz", "diego@mail.com", "San Martín 654");

        agenda.mostrarContactosAgrupados();

        System.out.println("--- 2. BÚSQUEDA DE CONTACTO ---");
        Contacto buscado = agenda.buscarPorTelefono("3512222222");
        System.out.println("Resultado búsqueda 3512222222: " + (buscado != null ? buscado : "No encontrado"));

        System.out.println("\n--- 3. MODIFICACIONES DE EMAIL Y DIRECCIÓN ---");
        agenda.modificarEmail("+549351111111", "carlos.gomez.nuevo@mail.com");
        agenda.modificarDireccion("3512222222", "Nueva Dir. Olmos 888");

        agenda.mostrarContactosAgrupados();

        System.out.println("--- 4. ELIMINACIÓN DE CONTACTO ---");
        agenda.eliminarContacto("3513333333");
        agenda.eliminarContacto("0000000000"); // Inexistente

        agenda.mostrarContactosAgrupados();
    }
}
