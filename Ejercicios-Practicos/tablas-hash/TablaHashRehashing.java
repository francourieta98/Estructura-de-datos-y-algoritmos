/*
 * =================================================================================
 * PROMPT INICIAL UTILIZADO:
 * "Necesito resolver el Ejercicio 9 en Java para OpenCode:
 *  - Problema: Implementar una Tabla Hash que gestione el Factor de Carga y realice Rehashing automático.
 *  - Explicación de por qué NO se pueden copiar los elementos directamente a la misma posición al redimensionar:
 *    La posición o índice de un elemento en la tabla se calcula mediante la fórmula: 
 *    índice = hash(clave) % capacidad_actual. 
 *    Al realizar un rehashing, la capacidad de la tabla cambia (típicamente se duplica, por ejemplo de 5 a 10). 
 *    Debido a que el divisor en la operación módulo (%) es distinto, la misma función hash devolverá un 
 *    índice completamente diferente en la nueva tabla. Si los elementos se copiaran directamente a sus índices 
 *    anteriores, quedarían desubicados en casilleros erróneos, haciendo imposible encontrarlos durante búsquedas futuras. 
 *    Por esta razón, cada elemento debe ser reinsertado pasando de nuevo por la función hash ajustada al nuevo tamaño.
 *  - Estructura de Datos: Tabla Hash basada en Arreglo con Manejo de Colisiones por Encadenamiento (Chaining).
 *  - Clave de búsqueda: La clave del elemento (String, ej: 'CLAVE-01').
 *  - Factor de Carga: Calculado como (tamanioActual / capacidad). Umbral límite: 0.75.
 *  - Operaciones a implementar:
 *      1. insertar(String clave, String valor) -> Verifica factor de carga y ejecuta rehashing si > 0.75.
 *      2. buscar(String clave)
 *      3. eliminar(String clave)
 *      4. calcularFactorCarga() -> Devuelve el ratio actual elementos / capacidad.
 *      5. redimensionarYRehash() -> Duplica la capacidad y reinserta todos los elementos.
 *      6. imprimirEstadoTabla() -> Muestra el arreglo, capacidad, tamaño actual y factor de carga.
 *  - Casos de prueba a validar:
 *      1. Inserción continua de elementos observando el incremento del factor de carga.
 *      2. Disparo automático del Rehashing al superar el umbral de 0.75.
 *      3. Verificación del cambio de posiciones/índices de los elementos en la nueva tabla redimensionada.
 *      4. Búsqueda y eliminación correcta de elementos antes y después del Rehashing."
 *
 * AJUSTES POSTERIORES AL PROMPT:
 * No se realizaron ajustes posteriores. El diseño inicial contempla el cálculo preciso del
 * factor de carga, la redistribución de elementos y el crecimiento dinámico del arreglo.
 * =================================================================================
 */

public class TablaHashRehashing {

    // Clase Nodo para la lista enlazada (Encadenamiento)
    static class Elemento {
        String clave;
        String valor;
        Elemento siguiente;

        public Elemento(String clave, String valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }

        @Override
        public String toString() {
            return "[" + clave + ": " + valor + "]";
        }
    }

    // Clase Tabla Hash con Rehashing Dinámico
    static class TablaHashDinamica {
        private Elemento[] tabla;
        private int capacidad;
        private int tamanioActual;
        private final double UMBRAL_FACTOR_CARGA = 0.75;

        public TablaHashDinamica(int capacidadInicial) {
            this.capacidad = capacidadInicial;
            this.tabla = new Elemento[capacidadInicial];
            this.tamanioActual = 0;
        }

        // Función Hash adaptada a la capacidad actual
        private int funcionHash(String clave) {
            String c = clave.trim();
            int hash = 0;
            for (int i = 0; i < c.length(); i++) {
                hash = (31 * hash + c.charAt(i)) % capacidad;
            }
            return Math.abs(hash);
        }

        // Calcular el Factor de Carga actual (λ = n / N)
        public double calcularFactorCarga() {
            return (double) tamanioActual / capacidad;
        }

        // 1. Insertar elemento (con control de factor de carga)
        public void insertar(String clave, String valor) {
            int indice = funcionHash(clave);
            Elemento actual = tabla[indice];

            // Si la clave ya existe, se actualiza el valor
            while (actual != null) {
                if (actual.clave.equals(clave)) {
                    actual.valor = valor;
                    System.out.println("-> Clave '" + clave + "' actualizada.");
                    return;
                }
                actual = actual.siguiente;
            }

            // Insertar al inicio de la lista
            Elemento nuevo = new Elemento(clave, valor);
            nuevo.siguiente = tabla[indice];
            tabla[indice] = nuevo;
            tamanioActual++;

            System.out.println("-> Insertado [" + clave + "] en pos " + indice + 
                               ". Factor de Carga actual: " + String.format("%.2f", calcularFactorCarga()));

            // Comprobar si se supera el umbral de 0.75 para hacer Rehashing
            if (calcularFactorCarga() > UMBRAL_FACTOR_CARGA) {
                System.out.println("\n*** ALERTA: Factor de Carga (" + String.format("%.2f", calcularFactorCarga()) + 
                                   ") supera el umbral (" + UMBRAL_FACTOR_CARGA + "). Iniciando Rehashing... ***");
                redimensionarYRehash();
            }
        }

        // 2. Redimensionar la tabla y Reinsertar los elementos (Rehashing)
        private void redimensionarYRehash() {
            Elemento[] tablaAnterior = tabla;
            int capacidadAnterior = capacidad;

            // Duplicar la capacidad de la tabla
            capacidad = capacidad * 2;
            tabla = new Elemento[capacidad];
            tamanioActual = 0; // Se reinicia el conteo y se incrementará con las reinserciones

            System.out.println("-> Nueva capacidad de tabla: " + capacidad + " (Anterior: " + capacidadAnterior + ")");

            // Recorrer la tabla antigua y reinsertar cada elemento en la nueva tabla
            for (int i = 0; i < capacidadAnterior; i++) {
                Elemento actual = tablaAnterior[i];
                while (actual != null) {
                    // Se reinserta usando la nueva capacidad y relocalizando los índices
                    reinsertarEnRehash(actual.clave, actual.valor);
                    actual = actual.siguiente;
                }
            }
            System.out.println("*** Rehashing completado exitosamente. Nuevo Factor de Carga: " + 
                               String.format("%.2f", calcularFactorCarga()) + " ***\n");
        }

        // Auxiliar para la reinserción durante el Rehashing
        private void reinsertarEnRehash(String clave, String valor) {
            int nuevoIndice = funcionHash(clave);
            Elemento nuevo = new Elemento(clave, valor);
            nuevo.siguiente = tabla[nuevoIndice];
            tabla[nuevoIndice] = nuevo;
            tamanioActual++;
        }

        // 3. Buscar elemento por clave
        public Elemento buscar(String clave) {
            int indice = funcionHash(clave);
            Elemento actual = tabla[indice];

            while (actual != null) {
                if (actual.clave.equals(clave)) {
                    return actual;
                }
                actual = actual.siguiente;
            }
            return null;
        }

        // 4. Eliminar elemento por clave
        public boolean eliminar(String clave) {
            int indice = funcionHash(clave);
            Elemento actual = tabla[indice];
            Elemento anterior = null;

            while (actual != null) {
                if (actual.clave.equals(clave)) {
                    if (anterior == null) {
                        tabla[indice] = actual.siguiente;
                    } else {
                        anterior.siguiente = actual.siguiente;
                    }
                    tamanioActual--;
                    System.out.println("-> Elemento '" + clave + "' eliminado.");
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }

            System.out.println("-> Error: No se encontró '" + clave + "' para eliminar.");
            return false;
        }

        // 5. Imprimir el estado actual de la tabla
        public void imprimirEstadoTabla() {
            System.out.println("\n=== ESTADO DE LA TABLA HASH (Capacidad: " + capacidad + 
                               " | Elementos: " + tamanioActual + 
                               " | Factor Carga: " + String.format("%.2f", calcularFactorCarga()) + ") ===");
            for (int i = 0; i < capacidad; i++) {
                System.out.print("Posición [" + i + "]: ");
                Elemento actual = tabla[i];

                if (actual == null) {
                    System.out.println("[ VACÍA ]");
                } else {
                    while (actual != null) {
                        System.out.print(actual.toString());
                        if (actual.siguiente != null) {
                            System.out.print(" -> ");
                        }
                        actual = actual.siguiente;
                    }
                    System.out.println();
                }
            }
            System.out.println("===================================================================================\n");
        }
    }

    // Casos de prueba
    public static void main(String[] args) {
        // Iniciamos con una capacidad pequeña de 4 elementos para forzar rápidamente el Rehashing
        // Umbral 0.75 -> Si supera 3 elementos (3 / 4 = 0.75), al insertar el 4to (4 / 4 = 1.0) rehashea.
        TablaHashDinamica tabla = new TablaHashDinamica(4);

        System.out.println("--- 1. INSERCIÓN INICIAL HASTA ACTIVAR REHASHING ---");
        tabla.insertar("K01", "Valor A");
        tabla.insertar("K02", "Valor B");
        tabla.insertar("K03", "Valor C");

        tabla.imprimirEstadoTabla();

        System.out.println("--- 2. INSERCIÓN QUE DISPARA EL REHASHING ---");
        // Al insertar el 4to elemento, el factor de carga previo superará 0.75 y disparará la duplicación
        tabla.insertar("K04", "Valor D");

        tabla.imprimirEstadoTabla();

        System.out.println("--- 3. MÁS INSERCIONES EN LA NUEVA TABLA (CAPACIDAD 8) ---");
        tabla.insertar("K05", "Valor E");
        tabla.insertar("K06", "Valor F");

        tabla.imprimirEstadoTabla();

        System.out.println("--- 4. BÚSQUEDA Y ELIMINACIÓN ---");
        Elemento buscado = tabla.buscar("K03");
        System.out.println("Búsqueda K03: " + (buscado != null ? buscado : "No encontrado"));

        tabla.eliminar("K02");

        tabla.imprimirEstadoTabla();
    }
}
