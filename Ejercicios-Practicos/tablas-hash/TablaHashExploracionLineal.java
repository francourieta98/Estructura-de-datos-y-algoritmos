/*
 * =================================================================================
 * PROMPT INICIAL UTILIZADO:
 * "Necesito resolver el Ejercicio 7 en Java para OpenCode:
 *  - Problema: Implementar una Tabla Hash de enteros utilizando Direccionamiento Abierto 
 *    con Exploración Lineal (Linear Probing).
 *  - Explicación del problema al eliminar elementos en exploración lineal y su solución:
 *    El problema principal al borrar un elemento es que si simplemente se limpia o vacía el casillero 
 *    (colocándolo en NULL o marcado como vacío original), se 'rompe' la cadena de exploración lineal.
 *    Búsquedas posteriores de otros elementos que colisionaron y se insertaron después en casilleros 
 *    siguientes se detendrían prematuramente al encontrar esa celda vacía, concluyendo de forma 
 *    errónea que el elemento no existe.
 *    Solución propuesta: Se utiliza la técnica de 'Borrado Lógico' o 'Tombstone' (Marcador de borrado). 
 *    Cuando se elimina un elemento, el casillero se marca con un estado especial (BORRADO / DELETED). 
 *    En búsquedas posteriores, la exploración continúa a través de los casilleros marcados como DELETED.
 *    En inserciones, los casilleros DELETED pueden ser reutilizados para almacenar nuevos valores.
 *  - Estructura de Datos: Tabla Hash sobre un Arreglo de Objetos/Estados.
 *  - Clave de búsqueda: El valor entero (Integer).
 *  - Función Hash: Método de la división (Math.abs(numero) % capacidad).
 *  - Operaciones a implementar:
 *      1. insertar(int numero)
 *      2. buscar(int numero) -> Informa explícitamente cuántos intentos (colisiones/pasos) requirió.
 *      3. eliminar(int numero)
 *      4. imprimirArregloInterno() -> Muestra el contenido del arreglo y los estados de cada celda.
 *  - Casos de prueba a validar:
 *      1. Inserción de varios enteros con y sin colisiones.
 *      2. Búsqueda exitosa e informe de cantidad de intentos.
 *      3. Búsqueda de elemento inexistente e informe de intentos.
 *      4. Eliminación de un elemento intermedio dentro de una secuencia de colisión.
 *      5. Verificación de que las búsquedas siguientes no se interrumpan tras la eliminación.
 *      6. Reutilización de celdas marcadas como borradas durante nuevas inserciones."
 *
 * AJUSTES POSTERIORES AL PROMPT:
 * No se realizaron ajustes posteriores. La solución integra el conteo detallado de intentos
 * y la lógica de Tombstone/DELETED solicitada.
 * =================================================================================
 */

public class TablaHashExploracionLineal {

    // Enum para representar el estado de cada casillero del arreglo
    enum EstadoCasilla { VACIO, OCUPADO, BORRADO }

    // Clase que representa un casillero individual en el arreglo hash
    static class ElementoHash {
        int valor;
        EstadoCasilla estado;

        public ElementoHash() {
            this.valor = 0;
            this.estado = EstadoCasilla.VACIO;
        }
    }

    // Clase principal de la Tabla Hash con Exploración Lineal
    static class TablaHashLineal {
        private ElementoHash[] tabla;
        private int capacidad;
        private int tamanioActual;

        public TablaHashLineal(int capacidad) {
            this.capacidad = capacidad;
            this.tabla = new ElementoHash[capacidad];
            for (int i = 0; i < capacidad; i++) {
                this.tabla[i] = new ElementoHash();
            }
            this.tamanioActual = 0;
        }

        // Función Hash: Método de la división
        private int funcionHash(int numero) {
            return Math.abs(numero) % capacidad;
        }

        // 1. Insertar un número utilizando exploración lineal
        public boolean insertar(int numero) {
            if (tamanioActual >= capacidad) {
                System.out.println("-> Error: La tabla hash está totalmente llena. No se puede insertar " + numero + ".");
                return false;
            }

            int indiceBase = funcionHash(numero);
            int primerBorradoEncontrado = -1;

            for (int intento = 0; intento < capacidad; intento++) {
                int indiceActual = (indiceBase + intento) % capacidad;
                ElementoHash casilla = tabla[indiceActual];

                // Si ya existe el número y está ocupado, no se duplica
                if (casilla.estado == EstadoCasilla.OCUPADO && casilla.valor == numero) {
                    System.out.println("-> El número " + numero + " ya existe en la tabla (índice " + indiceActual + ").");
                    return false;
                }

                // Guardar la primera casilla borrada para reutilizarla
                if (casilla.estado == EstadoCasilla.BORRADO && primerBorradoEncontrado == -1) {
                    primerBorradoEncontrado = indiceActual;
                }

                // Si encuentra una casilla vacía, inserta (en el borrado si existía o en la vacía)
                if (casilla.estado == EstadoCasilla.VACIO) {
                    int indiceInsercion = (primerBorradoEncontrado != -1) ? primerBorradoEncontrado : indiceActual;
                    tabla[indiceInsercion].valor = numero;
                    tabla[indiceInsercion].estado = EstadoCasilla.OCUPADO;
                    tamanioActual++;
                    System.out.println("-> Número " + numero + " insertado en el índice " + indiceInsercion + ".");
                    return true;
                }
            }

            // Si no halló casillas vacías pero sí una borrada
            if (primerBorradoEncontrado != -1) {
                tabla[primerBorradoEncontrado].valor = numero;
                tabla[primerBorradoEncontrado].estado = EstadoCasilla.OCUPADO;
                tamanioActual++;
                System.out.println("-> Número " + numero + " insertado en celda previamente borrada (índice " + primerBorradoEncontrado + ").");
                return true;
            }

            return false;
        }

        // 2. Buscar número e informar la cantidad de intentos requeridos
        public int buscar(int numero) {
            int indiceBase = funcionHash(numero);
            int intentos = 0;

            for (int i = 0; i < capacidad; i++) {
                intentos++;
                int indiceActual = (indiceBase + i) % capacidad;
                ElementoHash casilla = tabla[indiceActual];

                // Si encuentra un casillero VACIO nunca antes ocupado, el número no existe
                if (casilla.estado == EstadoCasilla.VACIO) {
                    System.out.println("-> Búsqueda de " + numero + ": NO ENCONTRADO. Intentos realizados: " + intentos + ".");
                    return -1;
                }

                // Si está OCUPADO y coincide el valor
                if (casilla.estado == EstadoCasilla.OCUPADO && casilla.valor == numero) {
                    System.out.println("-> Búsqueda de " + numero + ": ENCONTRADO en el índice " + indiceActual + ". Intentos realizados: " + intentos + ".");
                    return indiceActual;
                }

                // Si está BORRADO o no coincide el valor, la exploración lineal continúa
            }

            System.out.println("-> Búsqueda de " + numero + ": NO ENCONTRADO (se recorrió toda la tabla). Intentos realizados: " + intentos + ".");
            return -1;
        }

        // 3. Eliminar número utilizando borrado lógico (Tombstone)
        public boolean eliminar(int numero) {
            int indiceBase = funcionHash(numero);

            for (int i = 0; i < capacidad; i++) {
                int indiceActual = (indiceBase + i) % capacidad;
                ElementoHash casilla = tabla[indiceActual];

                if (casilla.estado == EstadoCasilla.VACIO) {
                    break; // No existe
                }

                if (casilla.estado == EstadoCasilla.OCUPADO && casilla.valor == numero) {
                    casilla.estado = EstadoCasilla.BORRADO; // Borrado lógico
                    tamanioActual--;
                    System.out.println("-> Número " + numero + " eliminado del índice " + indiceActual + " (marcado como BORRADO).");
                    return true;
                }
            }

            System.out.println("-> Error: No se pudo eliminar el número " + numero + " porque no existe.");
            return false;
        }

        // 4. Imprimir el arreglo interno con el estado de cada celda
        public void imprimirArregloInterno() {
            System.out.println("\n=== ESTADO INTERNO DEL ARREGLO HASH (EXPLORACIÓN LINEAL) ===");
            for (int i = 0; i < capacidad; i++) {
                ElementoHash casilla = tabla[i];
                String contenido;
                
                if (casilla.estado == EstadoCasilla.OCUPADO) {
                    contenido = String.valueOf(casilla.valor);
                } else if (casilla.estado == EstadoCasilla.BORRADO) {
                    contenido = "DELETED/BORRADO";
                } else {
                    contenido = "VACÍO";
                }

                System.out.println("Posición [" + i + "]: " + contenido + " (" + casilla.estado + ")");
            }
            System.out.println("============================================================\n");
        }
    }

    // Casos de prueba
    public static void main(String[] args) {
        // Tamaño 7 para forzar colisiones por exploración lineal
        TablaHashLineal tablaHash = new TablaHashLineal(7);

        System.out.println("--- 1. INSERCIÓN CON COLISIONES ---");
        // Valores elegidos intencionalmente para colisionar (mod 7):
        // 10 % 7 = 3
        // 17 % 7 = 3 (colisión -> irá a la pos 4)
        // 24 % 7 = 3 (colisión -> irá a la pos 5)
        tablaHash.insertar(10);
        tablaHash.insertar(17);
        tablaHash.insertar(24);
        tablaHash.insertar(5);

        tablaHash.imprimirArregloInterno();

        System.out.println("--- 2. BÚSQUEDA E INFORME DE INTENTOS ---");
        tablaHash.buscar(10); // Debe tomar 1 intento
        tablaHash.buscar(24); // Debe tomar 3 intentos debido a la cadena de colisión
        tablaHash.buscar(99); // Inexistente

        System.out.println("\n--- 3. ELIMINACIÓN Y VERIFICACIÓN DE CONTINUIDAD ---");
        // Eliminamos el valor intermedio '17' que está en el medio de la cadena (10 -> 17 -> 24)
        tablaHash.eliminar(17);

        tablaHash.imprimirArregloInterno();

        // Verificamos que '24' siga siendo alcanzable aunque hayamos eliminado a '17'
        System.out.println("--- Búsqueda de 24 tras eliminar el 17 intermedio ---");
        tablaHash.buscar(24);

        System.out.println("\n--- 4. REUTILIZACIÓN DE CELDAS BORRADAS ---");
        // Insertamos un nuevo número que pueda aprovechar el casillero BORRADO
        tablaHash.insertar(31); // 31 % 7 = 3

        tablaHash.imprimirArregloInterno();
    }
}
