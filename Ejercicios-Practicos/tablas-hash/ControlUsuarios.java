/*
 * =================================================================================
 * PROMPT INICIAL UTILIZADO:
 * "Necesito resolver el Ejercicio 5 en Java para OpenCode:
 *  - Problema: Implementar un sistema de Control de Usuarios por Email utilizando una Tabla Hash.
 *  - Explicación de por qué no alcanza con una Lista:
 *    Guardar usuarios en una lista simple implica que para validar si un email existe, iniciar sesión
 *    o buscar datos, se debe realizar un recorrido secuencial elemento por elemento. Esto representa
 *    una complejidad temporal de O(n). A medida que el número de usuarios crece a miles o millones,
 *    las operaciones de autenticación y validación en tiempo real se vuelven lentas e ineficientes.
 *    La Tabla Hash permite realizar estas comprobaciones en tiempo medio constante O(1), optimizando
 *    drásticamente la velocidad de respuesta del sistema.
 *  - Estructura de Datos: Tabla Hash con Manejo de Colisiones por Encadenamiento (Chaining / Listas Enlazadas).
 *  - Clave de búsqueda: El email del usuario (String, tratado de forma insensible a mayúsculas/minúsculas).
 *  - Función Hash: Polynomial Rolling Hash con base 31 sobre los caracteres ASCII del email.
 *  - Operaciones a implementar:
 *      1. registrarUsuario(String email, String nombre, String contrasena, String estadoCuenta)
 *      2. buscarPorEmail(String email)
 *      3. cambiarContrasena(String email, String nuevaContrasena)
 *      4. cambiarEstadoCuenta(String email, String nuevoEstado)
 *      5. eliminarUsuario(String email)
 *      6. existeEmail(String email) -> Devuelve un booleano indicando existencia.
 *      7. imprimirTablaUsuarios() -> Muestra la estructura de la tabla y sus colisiones.
 *  - Casos de prueba a validar:
 *      1. Registro de usuarios con validación de emails duplicados.
 *      2. Comprobación de existencia de emails cargados e inexistentes.
 *      3. Búsqueda exitosa y fallida.
 *      4. Cambio de contraseña y activación/desactivación de cuenta.
 *      5. Eliminación de usuario y posterior verificación de no existencia.
 *      6. Impresión general de la tabla."
 *
 * AJUSTES POSTERIORES AL PROMPT:
 * No se realizaron ajustes posteriores. El diseño abarca todas las operaciones requeridas,
 * la normalización de la clave email a minúsculas y el manejo de colisiones por encadenamiento.
 * =================================================================================
 */

public class ControlUsuarios {

    // Clase Nodo que representa a un Usuario
    static class Usuario {
        String email;
        String nombre;
        String contrasena;
        String estadoCuenta; // Ej: "ACTIVA", "INACTIVA", "SUSPENDIDA"
        Usuario siguiente;

        public Usuario(String email, String nombre, String contrasena, String estadoCuenta) {
            this.email = email;
            this.nombre = nombre;
            this.contrasena = contrasena;
            this.estadoCuenta = estadoCuenta;
            this.siguiente = null;
        }

        @Override
        public String toString() {
            return "[" + email + " | User: " + nombre + " | Estado: " + estadoCuenta + "]";
        }
    }

    // Clase Tabla Hash para Control de Usuarios
    static class TablaHashUsuarios {
        private Usuario[] tabla;
        private int capacidad;

        public TablaHashUsuarios(int capacidad) {
            this.capacidad = capacidad;
            this.tabla = new Usuario[capacidad];
        }

        // Función Hash polinomial sobre la cadena del email
        private int funcionHash(String email) {
            String clave = email.toLowerCase().trim();
            int hash = 0;
            for (int i = 0; i < clave.length(); i++) {
                hash = (31 * hash + clave.charAt(i)) % capacidad;
            }
            return Math.abs(hash);
        }

        // Validar si un email ya existe en la tabla (O(1) promedio)
        public boolean existeEmail(String email) {
            return buscarPorEmail(email) != null;
        }

        // 1. Registrar usuario
        public boolean registrarUsuario(String email, String nombre, String contrasena, String estadoCuenta) {
            String clave = email.toLowerCase().trim();

            if (existeEmail(clave)) {
                System.out.println("-> Error: El email '" + email + "' ya se encuentra registrado.");
                return false;
            }

            int indice = funcionHash(clave);
            Usuario nuevo = new Usuario(clave, nombre, contrasena, estadoCuenta);
            
            // Inserción al inicio de la lista (Chaining)
            nuevo.siguiente = tabla[indice];
            tabla[indice] = nuevo;
            System.out.println("-> Usuario '" + nombre + "' registrado con éxito en el índice " + indice + ".");
            return true;
        }

        // 2. Buscar usuario por email
        public Usuario buscarPorEmail(String email) {
            String clave = email.toLowerCase().trim();
            int indice = funcionHash(clave);
            Usuario actual = tabla[indice];

            while (actual != null) {
                if (actual.email.equalsIgnoreCase(clave)) {
                    return actual;
                }
                actual = actual.siguiente;
            }
            return null; // No encontrado
        }

        // 3. Cambiar contraseña
        public boolean cambiarContrasena(String email, String nuevaContrasena) {
            Usuario usuario = buscarPorEmail(email);
            if (usuario != null) {
                usuario.contrasena = nuevaContrasena;
                System.out.println("-> Contraseña actualizada para el email " + email + ".");
                return true;
            }
            System.out.println("-> Error: No se encontró al usuario con email " + email + ".");
            return false;
        }

        // 4. Activar o desactivar cuenta
        public boolean cambiarEstadoCuenta(String email, String nuevoEstado) {
            Usuario usuario = buscarPorEmail(email);
            if (usuario != null) {
                usuario.estadoCuenta = nuevoEstado;
                System.out.println("-> Estado de cuenta de " + email + " cambiado a: " + nuevoEstado);
                return true;
            }
            System.out.println("-> Error: No se encontró al usuario con email " + email + ".");
            return false;
        }

        // 5. Eliminar usuario
        public boolean eliminarUsuario(String email) {
            String clave = email.toLowerCase().trim();
            int indice = funcionHash(clave);
            Usuario actual = tabla[indice];
            Usuario anterior = null;

            while (actual != null) {
                if (actual.email.equalsIgnoreCase(clave)) {
                    if (anterior == null) {
                        tabla[indice] = actual.siguiente;
                    } else {
                        anterior.siguiente = actual.siguiente;
                    }
                    System.out.println("-> Usuario con email " + email + " eliminado del sistema.");
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }

            System.out.println("-> Error: No se pudo eliminar, el email " + email + " no existe.");
            return false;
        }

        // Imprimir el estado de la tabla hash
        public void imprimirTablaUsuarios() {
            System.out.println("\n=== REGISTRO DE USUARIOS EN LA TABLA HASH ===");
            for (int i = 0; i < capacidad; i++) {
                System.out.print("Posición [" + i + "]: ");
                Usuario actual = tabla[i];

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
            System.out.println("=============================================\n");
        }
    }

    // Casos de prueba
    public static void main(String[] args) {
        // Capacidad pequeña (5) para forzar y examinar colisiones
        TablaHashUsuarios sistema = new TablaHashUsuarios(5);

        System.out.println("--- 1. REGISTRO DE USUARIOS ---");
        sistema.registrarUsuario("juan@gmail.com", "Juan Perez", "12345", "ACTIVA");
        sistema.registrarUsuario("maria@hotmail.com", "Maria Lopez", "abcde", "ACTIVA");
        sistema.registrarUsuario("pedro@yahoo.com", "Pedro Gomez", "qwerty", "INACTIVA");
        sistema.registrarUsuario("ana@outlook.com", "Ana Martinez", "pass123", "ACTIVA");

        // Intento de registrar un email duplicado
        System.out.println("\n--- Intento de duplicado ---");
        sistema.registrarUsuario("JUAN@gmail.com", "Juan Clon", "99999", "ACTIVA");

        sistema.imprimirTablaUsuarios();

        System.out.println("--- 2. VALIDACIÓN Y BÚSQUEDA ---");
        System.out.println("¿Existe 'maria@hotmail.com'?: " + sistema.existeEmail("maria@hotmail.com"));
        System.out.println("¿Existe 'carlos@mail.com'?: " + sistema.existeEmail("carlos@mail.com"));

        Usuario buscado = sistema.buscarPorEmail("pedro@yahoo.com");
        System.out.println("Búsqueda pedro@yahoo.com: " + (buscado != null ? buscado : "No encontrado"));

        System.out.println("\n--- 3. CAMBIO DE CONTRASEÑA Y ESTADO ---");
        sistema.cambiarContrasena("juan@gmail.com", "nuevaClave2026");
        sistema.cambiarEstadoCuenta("pedro@yahoo.com", "ACTIVA");

        sistema.imprimirTablaUsuarios();

        System.out.println("--- 4. ELIMINACIÓN DE USUARIO ---");
        sistema.eliminarUsuario("maria@hotmail.com");
        System.out.println("¿Existe 'maria@hotmail.com' tras eliminar?: " + sistema.existeEmail("maria@hotmail.com"));

        sistema.imprimirTablaUsuarios();
    }
}
