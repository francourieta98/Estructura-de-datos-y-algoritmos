/*
 * =================================================================================
 * PROMPT INICIAL UTILIZADO:
 * "Necesito resolver el Ejercicio 2 en Java para OpenCode:
 *  - Problema: Diseñar e implementar un sistema de administración de alumnos para una materia.
 *  - Justificación de la Tabla Hash: Una Tabla Hash es adecuada para este problema porque
 *    necesitamos acceder de forma directa e instantánea a la información de un alumno utilizando
 *    su legajo como identificador único. Esto permite realizar operaciones de búsqueda, inserción,
 *    eliminación y actualización de notas/condición en un tiempo medio constante O(1), evitando
 *    recorrer arreglos o listas completas de forma secuencial O(n).
 *  - Estructura de Datos: Tabla Hash con direccionamiento abierto y sondeo lineal (Linear Probing).
 *  - Clave de búsqueda: El legajo del alumno (int, ej: 10452).
 *  - Función Hash: Método de la división (legajo % capacidad) asegurando el manejo de enteros positivos.
 *  - Manejo de colisiones: Sondeo Lineal (Linear Probing). Cuando ocurre una colisión en el índice
 *    calculado, se busca secuencialmente el siguiente casillero disponible (índice + 1) % capacidad.
 *  - Operaciones a implementar:
 *      1. insertarAlumno(int legajo, String nombre, String apellido, double notaParcial, String condicionFinal)
 *      2. buscarPorLegajo(int legajo)
 *      3. modificarNota(int legajo, double nuevaNota)
 *      4. actualizarCondicionFinal(int legajo, String nuevaCondicion)
 *      5. eliminarAlumno(int legajo) -> Manejo mediante bandera de borrado lógico (Tombstone / DELETED)
 *      6. listarAlumnos()
 *  - Casos de prueba a validar:
 *      1. Carga inicial de alumnos sin y con colisión de legajos.
 *      2. Búsqueda de un alumno existente e inexistente.
 *      3. Modificación exitosa de nota y actualización de condición.
 *      4. Eliminación de un alumno con verificación de continuidad en las búsquedas (sondeo).
 *      5. Listado general de todos los alumnos almacenados."
 *
 * AJUSTES POSTERIORES AL PROMPT:
 * No se realizaron ajustes adicionales al prompt. Se mantuvo el diseño con borrado lógico
 * para garantizar la corrección del sondeo lineal tras la eliminación de elementos.
 * =================================================================================
 */

public class Ejercicio2 {

    // Clase para representar la información del alumno
    static class Alumno {
        int legajo;
        String nombre;
        String apellido;
        double notaParcial;
        String condicionFinal; // Ej: "Cursando", "Aprobado", "Regular", "Recuperatorio"
        boolean estaEliminado;  // Bandera para borrado lógico (Tombstone)

        public Alumno(int legajo, String nombre, String apellido, double notaParcial, String condicionFinal) {
            this.legajo = legajo;
            this.nombre = nombre;
            this.apellido = apellido;
            this.notaParcial = notaParcial;
            this.condicionFinal = condicionFinal;
            this.estaEliminado = false;
        }

        @Override
        public String toString() {
            return "[Legajo: " + legajo + " | " + apellido + ", " + nombre + 
                   " | Nota: " + notaParcial + " | Condición: " + condicionFinal + "]";
        }
    }

    // Clase Tabla Hash utilizando Sondeo Lineal (Open Addressing)
    static class TablaHashAlumnos {
        private Alumno[] tabla;
        private int capacidad;
        private int tamanioActual;

        public TablaHashAlumnos(int capacidad) {
            this.capacidad = capacidad;
            this.tabla = new Alumno[capacidad];
            this.tamanioActual = 0;
        }

        // Función Hash: Método de la división
        private int funcionHash(int legajo) {
            return Math.abs(legajo) % capacidad;
        }

        // 1. Insertar Alumno
        public boolean insertarAlumno(int legajo, String nombre, String apellido, double notaParcial, String condicionFinal) {
            if (tamanioActual >= capacidad) {
                System.out.println("-> Error: La tabla hash está llena. No se puede insertar el legajo " + legajo);
                return false;
            }

            int indiceBase = funcionHash(legajo);
            int i = 0;

            while (i < capacidad) {
                int indiceActual = (indiceBase + i) % capacidad;
                Alumno alumnoActual = tabla[indiceActual];

                // Inserción en casillero vacío o borrado previamente
                if (alumnoActual == null || alumnoActual.estaEliminado) {
                    tabla[indiceActual] = new Alumno(legajo, nombre, apellido, notaParcial, condicionFinal);
                    tamanioActual++;
                    System.out.println("-> Alumno " + legajo + " (" + apellido + ") insertado en el índice " + indiceActual);
                    return true;
                }

                // Si la clave ya existe y no está eliminada, se actualizan los datos
                if (alumnoActual.legajo == legajo && !alumnoActual.estaEliminado) {
                    alumnoActual.nombre = nombre;
                    alumnoActual.apellido = apellido;
                    alumnoActual.notaParcial = notaParcial;
                    alumnoActual.condicionFinal = condicionFinal;
                    System.out.println("-> Datos del alumno con legajo " + legajo + " actualizados correctamente.");
                    return true;
                }

                i++; // Sondeo lineal
            }

            return false;
        }

        // 2. Buscar por Legajo
        public Alumno buscarPorLegajo(int legajo) {
            int indiceBase = funcionHash(legajo);
            int i = 0;

            while (i < capacidad) {
                int indiceActual = (indiceBase + i) % capacidad;
                Alumno alumnoActual = tabla[indiceActual];

                // Si encontramos un casillero nunca ocupado, el elemento no está
                if (alumnoActual == null) {
                    return null;
                }

                // Si el legajo coincide y NO fue eliminado
                if (alumnoActual.legajo == legajo && !alumnoActual.estaEliminado) {
                    return alumnoActual;
                }

                i++;
            }

            return null;
        }

        // 3. Modificar la Nota Parcial
        public boolean modificarNota(int legajo, double nuevaNota) {
            Alumno alumno = buscarPorLegajo(legajo);
            if (alumno != null) {
                alumno.notaParcial = nuevaNota;
                System.out.println("-> Nota del alumno " + legajo + " modificada a: " + nuevaNota);
                return true;
            }
            System.out.println("-> Error: No se encontró al alumno con legajo " + legajo + " para modificar nota.");
            return false;
        }

        // 4. Actualizar la Condición Final
        public boolean actualizarCondicionFinal(int legajo, String nuevaCondicion) {
            Alumno alumno = buscarPorLegajo(legajo);
            if (alumno != null) {
                alumno.condicionFinal = nuevaCondicion;
                System.out.println("-> Condición final del alumno " + legajo + " actualizada a: " + nuevaCondicion);
                return true;
            }
            System.out.println("-> Error: No se encontró al alumno con legajo " + legajo + " para actualizar condición.");
            return false;
        }

        // 5. Eliminar un Alumno
        public boolean eliminarAlumno(int legajo) {
            int indiceBase = funcionHash(legajo);
            int i = 0;

            while (i < capacidad) {
                int indiceActual = (indiceBase + i) % capacidad;
                Alumno alumnoActual = tabla[indiceActual];

                if (alumnoActual == null) {
                    break;
                }

                if (alumnoActual.legajo == legajo && !alumnoActual.estaEliminado) {
                    alumnoActual.estaEliminado = true; // Marca de borrado lógico
                    tamanioActual--;
                    System.out.println("-> Alumno con legajo " + legajo + " eliminado correctamente.");
                    return true;
                }

                i++;
            }

            System.out.println("-> Error: Alumno con legajo " + legajo + " no encontrado para eliminar.");
            return false;
        }

        // 6. Listar todos los Alumnos Almacenados
        public void listarAlumnos() {
            System.out.println("\n=== LISTADO COMPLETO DE ALUMNOS REGISTRADOS ===");
            boolean hayAlumnos = false;

            for (int i = 0; i < capacidad; i++) {
                Alumno actual = tabla[i];
                if (actual != null && !actual.estaEliminado) {
                    System.out.println("Índice [" + i + "]: " + actual);
                    hayAlumnos = true;
                }
            }

            if (!hayAlumnos) {
                System.out.println("No hay alumnos registrados en el sistema.");
            }
            System.out.println("===============================================\n");
        }
    }

    // Ejecución de pruebas
    public static void main(String[] args) {
        // Capacidad inicial definida para simular el comportamiento de sondeo lineal
        TablaHashAlumnos sistemaMateria = new TablaHashAlumnos(7);

        System.out.println("--- 1. CARGA INICIAL DE ALUMNOS ---");
        sistemaMateria.insertarAlumno(101, "Juan", "Pérez", 7.5, "Cursando");
        sistemaMateria.insertarAlumno(108, "María", "Gómez", 9.0, "Aprobado"); // Colisión intencional (108 % 7 = 3 == 101 % 7)
        sistemaMateria.insertarAlumno(102, "Lucas", "Rodríguez", 4.0, "Recuperatorio");
        sistemaMateria.insertarAlumno(103, "Ana", "Martínez", 8.5, "Cursando");

        sistemaMateria.listarAlumnos();

        System.out.println("--- 2. BÚSQUEDA DE ALUMNOS ---");
        Alumno buscado = sistemaMateria.buscarPorLegajo(108);
        System.out.println("Búsqueda legajo 108: " + (buscado != null ? buscado : "No encontrado"));

        Alumno noExiste = sistemaMateria.buscarPorLegajo(999);
        System.out.println("Búsqueda legajo 999: " + (noExiste != null ? noExiste : "No encontrado"));

        System.out.println("\n--- 3. MODIFICACIÓN DE NOTA Y CONDICIÓN ---");
        sistemaMateria.modificarNota(102, 7.0);
        sistemaMateria.actualizarCondicionFinal(102, "Aprobado");

        System.out.println("\n--- 4. ELIMINACIÓN DE ALUMNO ---");
        sistemaMateria.eliminarAlumno(101); // Eliminamos el elemento que generaba colisión inicial

        // Verificamos que se siga encontrando el legajo 108 tras eliminar el 101
        Alumno postEliminacion = sistemaMateria.buscarPorLegajo(108);
        System.out.println("Búsqueda del legajo 108 tras eliminar el 101: " + postEliminacion);

        sistemaMateria.listarAlumnos();
    }
}
