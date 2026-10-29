/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase 'CompararHeapifyVsInserciones' que permita construir un MinHeap mediante dos métodos distintos:
 * 1. Inserción elemento por elemento (Top-Down con 'percolateUp', O(N log N)).
 * 2. Algoritmo de Heapify de Floyd (Bottom-Up con 'percolateDown', O(N)).
 * 
 * La clase debe contemplar:
 * 1. Explicación teórica de las preguntas del ejercicio:
 *    - Por qué los arreglos resultantes de ambas construcciones pueden ser físicamente diferentes.
 *    - Por qué ambos arreglos son MinHeaps totalmente válidos aunque sus elementos tengan una disposición interna distinta.
 *    - Cómo se valida formalmente la propiedad de orden de un MinHeap.
 *    - Cómo recorrer el arreglo de forma sistemática comprobando que para todo nodo 'i' (desde 1 hasta 'tamano / 2') se cumpla que 'heap[i] <= heap[2*i]' y 'heap[i] <= heap[2*i + 1]'.
 * 
 * 2. Funcionalidades del programa:
 *    - Método 'boolean esHeapValido(int[] arr, int tamano)' para auditar y validar la propiedad del MinHeap en cualquier arreglo.
 *    - Ejecución simultánea de ambos métodos sobre la misma secuencia desordenada de entrada.
 *    - Impresión de los arreglos generados y la verificación del cumplimiento de la propiedad de MinHeap en ambos casos.
 * 
 * Muestra por consola una prueba con la secuencia [50, 30, 20, 15, 10, 8, 16] comparando visualmente los arreglos resultantes."
 * 
 * AJUSTES REALIZADOS LUEGO DE REVISAR EL CÓDIGO:
 * - Se implementó un validador genérico 'esHeapValido' que evalúa nodo por nodo hasta 'tamano / 2', controlando cuidadosamente que el hijo derecho no exceda el límite válido del arreglo.
 * - Se clonó el arreglo original de prueba antes de pasarlo a cada método para asegurar que ambas estrategias operen exactamente sobre la misma entrada.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * 
 * 1. ¿POR QUÉ LOS ARREGLOS FINALES PUEDEN NO SER IDÉNTICOS?
 *    Un Montículo Binario es una estructura de orden parcial (no de orden total como un Árbol Binario de Búsqueda).
 *    - **Inserción Uno por Uno (Top-Down):** Inserta elementos en las hojas y ejecuta `percolateUp` (hacia la raíz). Las decisiones de intercambio dependen del orden en el que ingresan los datos.
 *    - **Heapify (Bottom-Up):** Empieza en la mitad del arreglo ($\lfloor N/2 \rfloor$) y aplica `percolateDown` (hacia las hojas). Las reorganizaciones suceden de abajo hacia arriba en bloques locales.
 *    Debido a que existen múltiples disposiciones válidas que satisfacen que $padre \le hijos$, ambos métodos suelen generar arreglos con un ordenamiento interno distinto.
 * 
 * 2. ¿POR QUÉ AMBOS PUEDEN SER HEAPS CORRECTOS?
 *    Un arreglo representa un MinHeap válido si y solo si satisface la **Propiedad de MinHeap**: la raíz de cada subárbol es menor o igual que sus hijos directos ($heap[i] \le heap[2i]$ y $heap[i] \le heap[2i+1]$).
 *    No se exige un orden estricto entre hermanos o entre elementos de ramas distintas.
 * 
 * 3. CÓMO VALIDAR LA PROPIEDAD DE ORDEN Y RECORRER EL ARREGLO:
 *    Para verificar si un arreglo de tamaño $N$ es un MinHeap válido, solo es necesario revisar los nodos que tienen al menos un hijo, es decir, desde el índice $i = 1$ hasta $i = \lfloor N / 2 \rfloor$:
 *    - Verificar que si $2i \le N \implies heap[i] \le heap[2i]$ (hijo izquierdo).
 *    - Verificar que si $2i + 1 \le N \implies heap[i] \le heap[2i + 1]$ (hijo derecho).
 *    Si para todo nodo $i$ en ese rango se cumplen ambas condiciones, el montículo es 100% válido.
 */

public class CompararHeapifyVsInserciones {

    /**
     * Valida si un arreglo dado satisface la propiedad de MinHeap.
     * Recorre desde i = 1 hasta tamano / 2 verificando que cada padre sea <= que sus hijos.
     */
    public static boolean esHeapValido(int[] heap, int tamano) {
        if (heap == null || tamano <= 1) {
            return true;
        }

        // Solo inspeccionamos los nodos que tienen hijos (hasta tamano / 2)
        for (int i = 1; i <= tamano / 2; i++) {
            int padre = heap[i];
            int hijoIzq = 2 * i;
            int hijoDer = 2 * i + 1;

            // Validar Hijo Izquierdo
            if (hijoIzq <= tamano && padre > heap[hijoIzq]) {
                System.out.println("  [X] Violación en índice " + i + ": Padre (" + padre + ") > Hijo Izquierdo (" + heap[hijoIzq] + ")");
                return false;
            }

            // Validar Hijo Derecho
            if (hijoDer <= tamano && padre > heap[hijoDer]) {
                System.out.println("  [X] Violación en índice " + i + ": Padre (" + padre + ") > Hijo Derecho (" + heap[hijoDer] + ")");
                return false;
            }
        }

        return true;
    }

    /**
     * Método 1: Construcción por inserciones uno por uno (Top-Down con percolateUp)
     */
    public static int[] construirPorInserciones(int[] datos) {
        int n = datos.length;
        int[] heap = new int[n + 1];
        int tamano = 0;

        for (int valor : datos) {
            tamano++;
            heap[tamano] = valor;

            // Percolate Up
            int i = tamano;
            while (i > 1 && heap[i] < heap[i / 2]) {
                int temp = heap[i];
                heap[i] = heap[i / 2];
                heap[i / 2] = temp;
                i = i / 2;
            }
        }

        return heap;
    }

    /**
     * Método 2: Construcción por Heapify de Floyd (Bottom-Up con percolateDown)
     */
    public static int[] construirPorHeapify(int[] datos) {
        int n = datos.length;
        int[] heap = new int[n + 1];
        System.arraycopy(datos, 0, heap, 1, n);

        // Heapify Bottom-Up
        for (int i = n / 2; i >= 1; i--) {
            percolateDown(heap, i, n);
        }

        return heap;
    }

    private static void percolateDown(int[] heap, int i, int tamano) {
        int valor = heap[i];
        int hijoMenor;

        while (2 * i <= tamano) {
            hijoMenor = 2 * i;

            if (hijoMenor + 1 <= tamano && heap[hijoMenor + 1] < heap[hijoMenor]) {
                hijoMenor++;
            }

            if (valor
