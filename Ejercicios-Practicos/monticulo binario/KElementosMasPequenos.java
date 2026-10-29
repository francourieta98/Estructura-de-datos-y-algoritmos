/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase 'KElementosMasPequenos' que determine los k elementos menores de un arreglo desordenado mediante un MinHeap.
 * La clase debe contemplar:
 * 1. Explicación teórica de las preguntas planteadas:
 *    - Cómo construir el MinHeap a partir del arreglo de entrada utilizando Heapify en O(N).
 *    - Cuántas veces se debe ejecutar la operación 'poll()' (o 'eliminarMinimo()') para extraer el resultado (exactamente 'k' veces, logrando una complejidad de O(N + k log N)).
 *    - Manejo de casos borde:
 *      * Si k es mayor que la cantidad de elementos N: se devuelven todos los N elementos del arreglo ordenados de menor a mayor.
 *      * Si k es cero o negativo: se devuelve un conjunto/arreglo vacío o una lista sin elementos.
 * 2. Operaciones principales:
 *    - Método 'int[] obtenerKMenores(int[] datos, int k)': Ejecuta la validación, la construcción por Heapify y la extracción secuencial.
 * 3. Pruebas por Consola:
 *    - Probar el ejemplo sugerido: datos = {40, 10, 30, 5, 80, 25} con k = 3 -> Salida: [5, 10, 25].
 *    - Probar casos borde con k = 0, k mayor que N (ej. k = 10) y k negativo."
 * 
 * AJUSTES REALIZADOS LUEGO DE REVISAR EL CÓDIGO:
 * - Se ajustó la dimensión del arreglo resultante para que en el caso de k > N devuelva exactamente N elementos sin lanzar un OutOfBoundsException.
 * - Se aisló la estructura MinHeap interna para hacer el método autocontenido y reutilizable.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * 
 * 1. CÓMO CONSTRUIR EL HEAP Y COMPLEJIDAD OPTIMIZADA:
 *    En lugar de ordenar todo el arreglo en $O(N \log N)$ mediante un algoritmo tradicional (como QuickSort o MergeSort), convertimos 
 *    el arreglo en un MinHeap usando el algoritmo de **Heapify de Floyd (Bottom-Up)** en tiempo $O(N)$.
 * 
 * 2. CUÁNTAS VECES SE EJECUTA 'poll()':
 *    Se ejecuta exactamente $k$ veces la extracción del mínimo (`poll()`). Cada `poll()` toma $O(\log N)$ para reestructurar la raíz.
 *    Complejidad temporal total: $O(N + k \log N)$. 
 *    Para valores de $k \ll N$ (ej. buscar los 3 menores entre 1,000,000 de datos), este enfoque es inmensamente superior a ordenar el arreglo completo.
 * 
 * 3. CASOS BORDE:
 *    - **Si $k > N$:** El algoritmo ajusta $k = N$, devolviendo los $N$ elementos ordenados de menor a mayor.
 *    - **Si $k \le 0$:** Se retorna inmediatamente un arreglo vacío de longitud 0 (`new int[0]`).
 */

import java.util.Arrays;

public class KElementosMasPequenos {

    /**
     * Estructura MinHeap interna auxiliar optimizada con Heapify.
     */
    private static class MinHeapAuxiliar {
        private int[] heap;
        private int tamano;

        public MinHeapAuxiliar(int[] datos) {
            this.tamano = datos.length;
            this.heap = new int[this.tamano + 1];
            System.arraycopy(datos, 0, this.heap, 1, this.tamano);
            construirHeap();
        }

        private void construirHeap() {
            for (int i = this.tamano / 2; i >= 1; i--) {
                percolateDown(i);
            }
        }

        public int poll() {
            if (this.tamano == 0) {
                throw new IllegalStateException("El heap está vacío.");
            }
            int minimo = this.heap[1];
            this.heap[1] = this.heap[this.tamano];
            this.tamano--;
            if (this.tamano > 0) {
                percolateDown(1);
            }
            return minimo;
        }

        private void percolateDown(int i) {
            int valor = this.heap[i];
            int hijoMenor;

            while (2 * i <= this.tamano) {
                hijoMenor = 2 * i;
                if (hijoMenor + 1 <= this.tamano && this.heap[hijoMenor + 1] < this.heap[hijoMenor]) {
                    hijoMenor++;
                }
                if (valor <= this.heap[hijoMenor]) {
                    break;
                }
                this.heap[i] = this.heap[hijoMenor];
                i = hijoMenor;
            }
            this.heap[i] = valor;
        }
    }

    /**
     * Devuelve un arreglo con los 'k' elementos más pequeños del arreglo original.
     */
    public static int[] obtenerKMenores(int[] datos, int k) {
        // Manejo de Caso Borde 1: Arreglo nulo o vacío, o k <= 0
        if (datos == null || datos.length == 0 || k <= 0) {
            return new int[0];
        }

        // Manejo de Caso Borde 2: k es mayor que la cantidad de elementos disponibles (N)
        int limiteExtraer = Math.min(k, datos.length);
        int[] resultado = new int[limiteExtraer];

        // 1. Construir el MinHeap en O(N) mediante Heapify
        MinHeapAuxiliar heap = new MinHeapAuxiliar(datos);

        // 2. Extraer 'k' veces la raíz con poll() en O(k log N)
        for (int i = 0; i < limiteExtraer; i++) {
            resultado[i] = heap.poll();
        }

        return resultado;
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBA: K ELEMENTOS MÁS PEQUEÑOS CON MIN-HEAP ===\n");

        // --- PRUEBA 1: Caso Ejemplo de la Consigna ---
        int[] datos1 = {40, 10, 30, 5, 80, 25};
        int k1 = 3;
        System.out.println("1. Arreglo original: " + Arrays.toString(datos1) + " | k = " + k1);
        int[] res1 = obtenerKMenores(datos1, k1);
        System.out.println("   Los " + k1 + " elementos más pequeños son: " + Arrays.toString(res1));
        System.out.println("   Salida esperada: [5, 10, 25]\n");

        System.out.println("----------------------------------------------------\n");

        // --- PRUEBA 2: Caso Borde k = 0 ---
        int k2 = 0;
        System.out.println("2. Caso Borde (k = 0):");
        int[] res2 = obtenerKMenores(datos1, k2);
        System.out.println("   Resultado: " + Arrays.toString(res2) + " (Longitud: " + res2.length + ")\n");

        // --- PRUEBA 3: Caso Borde k mayor que N (k = 10, N = 6) ---
        int k3 = 10;
        System.out.println("3. Caso Borde (k > N, k = 10, N = 6):");
        int[] res3 = obtenerKMenores(datos1, k3);
        System.out.println("   Resultado (devuelve todos los " + res3.length + " elementos ordenados): " + Arrays.toString(res3) + "\n");

        // --- PRUEBA 4: Caso Borde k negativo (k = -5) ---
        int k4 = -5;
        System.out.println("4. Caso Borde (k negativo, k = -5):");
        int[] res4 = obtenerKMenores(datos1, k4);
        System.out.println("   Resultado: " + Arrays.toString(res4) + " (Longitud: " + res4.length + ")");
    }
}
