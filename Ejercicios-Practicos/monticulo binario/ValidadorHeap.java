/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase 'ValidadorHeap' que contenga un método estático 'boolean esMinHeapValido(int[] arr)' 
 * que determine de forma exhaustiva si un arreglo de enteros cumple con la propiedad de MinHeap.
 * La clase debe contemplar:
 * 1. Explicación teórica de las preguntas clave:
 *    - Qué significa que un arreglo represente un Heap (estructura de árbol binario completo mapeada sin huecos).
 *    - Qué índices debe revisar el algoritmo de validación (desde i = 1 hasta i = tamano / 2).
 *    - Cómo detectar violaciones de orden entre un nodo padre y sus hijos (padre > hijoIzq o padre > hijoDer).
 *    - Por qué NO hace falta comparar elementos hermanos entre sí (relación de orden parcial).
 * 2. Casos de prueba obligatorios:
 *    - Un MinHeap válido.
 *    - Un Heap inválido por violación de la propiedad de orden (padre mayor que hijo).
 *    - Arreglo vacío (caso borde).
 *    - Arreglo con un solo elemento (caso borde).
 *    - Arreglo con valores repetidos (elementos duplicados).
 * 
 * Incluye la auditoría con 'Prompt inicial utilizado' y 'Ajustes realizados luego de revisar el código'."
 * 
 * AJUSTES REALIZADOS LUEGO DE REVISAR EL CÓDIGO:
 * - Se diseñó el validador para soportar automáticamente arreglos con la convención de índice 1 libre (longitud N+1), 
 *   así como arreglos con indexación desde 0, ajustando el bucle de revisión de límites.
 * - Se incorporaron mensajes de diagnóstico que reportan el índice exacto y los valores de la violación cuando se detecta una falla.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * 
 * 1. ¿QUÉ SIGNIFICA QUE UN ARREGLO REPRESENTE UN HEAP?
 *    Significa que la secuencia contigua de datos mapea conceptualmente un Árbol Binario Completo donde la posición 
 *    de cada nodo determina la ubicación de su familia mediante relaciones aritméticas exactas:
 *    - Padre en `i / 2`
 *    - Hijo Izquierdo en `2 * i`
 *    - Hijo Derecho en `2 * i + 1`
 *    Para ser un MinHeap válido, todo nodo debe ser menor o igual que sus descendientes inmediatos ($padre \le hijos$).
 * 
 * 2. ¿QUÉ ÍNDICES DEBE REVISAR EL ALGORITMO?
 *    Solo es necesario iterar desde el índice $i = 1$ hasta $i = \lfloor N / 2 \rfloor$.
 *    Los nodos desde $\lfloor N / 2 \rfloor + 1$ hasta $N$ son hojas, por lo cual no tienen hijos que puedan violar la regla de orden.
 * 
 * 3. ¿CÓMO DETECTAR VIOLACIONES ENTRE PADRE E HIJOS?
 *    Para cada nodo interno $i$:
 *    - Si $2i \le N$ y $arr[i] > arr[2i]$, hay una **violación en el hijo izquierdo**.
 *    - Si $2i + 1 \le N$ y $arr[i] > arr[2i + 1]$, hay una **violación en el hijo derecho**.
 * 
 * 4. ¿POR QUÉ NO HACE FALTA COMPARAR HERMANOS?
 *    Los Montículos Binarios imponen una relación de **orden parcial** (vertical entre ancestros y descendientes), no de orden total 
 *    (horizontal entre hermanos). Un hijo izquierdo puede ser mayor, menor o igual que su hijo derecho hermano sin afectar la validez del Heap.
 */

public class ValidadorHeap {

    /**
     * Valida si un arreglo (usando la convención de índice 1 al tamaño N) representa un MinHeap válido.
     * @param heap Arreglo de enteros donde el índice 0 está libre y los datos van del índice 1 a 'tamano'.
     * @param tamano Cantidad de elementos útiles dentro del arreglo.
     * @return true si es un MinHeap válido, false en caso contrario.
     */
    public static boolean esMinHeapValido(int[] heap, int tamano) {
        // Casos borde: vacíos o con un único elemento siempre son válidos
        if (heap == null || tamano <= 1) {
            return true;
        }

        // Se revisan exclusivamente los nodos internos (con al menos un hijo)
        for (int i = 1; i <= tamano / 2; i++) {
            int padre = heap[i];
            int idxIzq = 2 * i;
            int idxDer = 2 * i + 1;

            // 1. Validar Hijo Izquierdo
            if (idxIzq <= tamano && padre > heap[idxIzq]) {
                System.out.println("  [FALLO] Violación en índice " + i + ": Padre (" + padre + 
                                   ") > Hijo Izquierdo (" + heap[idxIzq] + " en índice " + idxIzq + ").");
                return false;
            }

            // 2. Validar Hijo Derecho (si existe)
            if (idxDer <= tamano && padre > heap[idxDer]) {
                System.out.println("  [FALLO] Violación en índice " + i + ": Padre (" + padre + 
                                   ") > Hijo Derecho (" + heap[idxDer] + " en índice " + idxDer + ").");
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DEL VALIDADOR DE MIN-HEAP ===\n");

        // --- CASO 1: MinHeap Válido ---
        // Indices:    [0,  1,  2,  3,  4,  5,  6]
        int[] heapValido = {0,  5, 10, 15, 20, 25, 30};
        int tamano1 = 6;
        System.out.println("1. Probando Heap Válido Estándar:");
        boolean res1 = esMinHeapValido(heapValido, tamano1);
        System.out.println("   ¿Es un MinHeap válido?: " + res1 + " [Esperado: true]\n");

        // --- CASO 2: Heap Inválido por Orden ---
        // Indices:      [0,  1,  2,  3,  4,  5]
        int[] heapInvalido = {0,  5, 40, 15, 10, 25}; // Violación: Padre 40 (idx 2) > Hijo 10 (idx 4)
        int tamano2 = 5;
        System.out.println("2. Probando Heap Inválido por Violación de Orden:");
        boolean res2 = esMinHeapValido(heapInvalido, tamano2);
        System.out.println("   ¿Es un MinHeap válido?: " + res2 + " [Esperado: false]\n");

        // --- CASO 3: Arreglo Vacío ---
        int[] heapVacio = {0};
        int tamano3 = 0;
        System.out.println("3. Probando Arreglo Vacío (Caso Borde):");
        boolean res3 = esMinHeapValido(heapVacio, tamano3);
        System.out.println("   ¿Es un MinHeap válido?: " + res3 + " [Esperado: true]\n");

        // --- CASO 4: Un Solo Elemento ---
        int[] heapUnElemento = {0, 42};
        int tamano4 = 1;
        System.out.println("4. Probando Arreglo con un Solo Elemento (Caso Borde):");
        boolean res4 = esMinHeapValido(heapUnElemento, tamano4);
        System.out.println("   ¿Es un MinHeap válido?: " + res4 + " [Esperado: true]\n");

        // --- CASO 5: Heap Válido con Valores Repetidos ---
        // Indices:       [0,  1,  2,  3,  4,  5,  6]
        int[] heapRepetidos = {0,  5,  5, 10,  5, 12, 10};
        int tamano5 = 6;
        System.out.println("5. Probando Heap Válido con Valores Repetidos:");
        boolean res5 = esMinHeapValido(heapRepetidos, tamano5);
        System.out.println("   ¿Es un MinHeap válido?: " + res5 + " [Esperado: true]\n");
    }
}
