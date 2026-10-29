/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase 'HeapifyMinHeap' que tome un arreglo desordenado de enteros y lo convierta en un MinHeap en tiempo O(N) utilizando el algoritmo de Floyd (Heapify/Build-Heap) Bottom-Up.
 * La clase debe contemplar:
 * 1. Explicación teórica de los conceptos clave:
 *    - Por qué Heapify comienza el procesamiento descendente desde 'tamano / 2' hacia atrás (índice 1).
 *    - Por qué los nodos hoja (desde 'tamano / 2 + 1' hasta 'tamano') no requieren procesamiento.
 *    - Qué operación se aplica sobre cada nodo interno ('percolateDown' / filtrado hacia abajo).
 *    - Cuál es la diferencia fundamental en complejidad temporal entre la construcción por Heapify u O(N) y la inserción uno por uno u O(N log N).
 * 2. Operaciones principales:
 *    - Constructor 'HeapifyMinHeap(int[] arregloDesordenado)': Copia el arreglo a la convención con índice 0 libre y ejecuta 'construirHeap()'.
 *    - 'construirHeap()': Recorre desde 'tamano / 2' hasta 1 aplicando 'percolateDown'.
 *    - 'obtenerMinimo()', 'eliminarMinimo()', 'obtenerTamano()', 'estaVacio()'.
 * 3. Pruebas por Consola:
 *    - Probar con la transformación del arreglo desordenado [40, 20, 10, 30, 50, 60, 5, 15].
 *    - Mostrar el estado del arreglo antes y después de aplicar Heapify.
 *    - Extraer secuencialmente todos los elementos ordenados."
 * 
 * AJUSTES REALIZADOS LUEGO DE REVISAR EL CÓDIGO:
 * - Se diseñó el constructor para aceptar arreglos con indexación estándar desde 0 y realizar la conversión transparente a un arreglo con índice 0 libre.
 * - Se optimizó el método 'percolateDown' asegurando que los intercambios respeten los límites válidos del tamaño del heap.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * 
 * 1. ¿POR QUÉ HEAPIFY COMIENZA DESDE 'tamano / 2'?
 *    En un Árbol Binario Completo de $N$ elementos, los nodos hojas ocupan la mitad inferior del árbol.
 *    El último nodo que no es una hoja (el padre del último elemento del montículo situado en el índice $N$) se encuentra exactamente en el índice $\lfloor N / 2 \rfloor$.
 * 
 * 2. ¿POR QUÉ LAS HOJAS NO NECESITAN PROCESARSE?
 *    Un nodo hoja no posee ningún hijo por debajo. Por definición, un árbol de un solo nodo sin hijos ya satisface trivialmente la propiedad de Min-Heap.
 *    Procesar las hojas mediante filtrado descendente (`percolateDown`) sería una operación redundante que retornaría inmediatamente.
 * 
 * 3. ¿QUÉ OPERACIÓN SE APLICA SOBRE CADA NODO INTERNO?
 *    Se aplica la operación **`percolateDown` (filtrado hacia abajo)** comenzando desde el índice $\lfloor N / 2 \rfloor$ y descendiendo uno a uno hasta llegar a la raíz (índice 1).
 *    Este enfoque *Bottom-Up* garantiza que cuando se filtra un nodo $i$, sus dos subárboles izquierdo y derecho ya son montículos válidos.
 * 
 * 4. DIFERENCIA ENTRE HEAPIFY E INSERTAR UNO POR UNO:
 *    - **Inserción una por una (Top-Down):** Se insertan $N$ elementos aplicando `percolateUp`. La mayoría de los nodos están en el nivel inferior (hojas) y podrían recorrer la altura completa $O(\log N)$ hacia arriba. Complejidad total: $O(N \log N)$.
 *    - **Algoritmo Heapify de Floyd (Bottom-Up):** Los nodos en la parte inferior (la mayoría de los nodos) recorren distancias muy cortas (0 o 1 nivel), mientras que solo la raíz puede recorrer $\log N$ niveles. Matemáticamente, la suma de las alturas $\sum_{h=0}^{\log N} \frac{N}{2^{h+1}} O(h)$ converge a $O(N)$.
 */

public class HeapifyMinHeap {
    private int[] heap;
    private int tamano;

    /**
     * Constructor que recibe un arreglo desordenado y lo convierte en MinHeap usando Heapify.
     */
    public HeapifyMinHeap(int[] arregloDesordenado) {
        if (arregloDesordenado == null || arregloDesordenado.length == 0) {
            this.tamano = 0;
            this.heap = new int[10];
            return;
        }

        this.tamano = arregloDesordenado.length;
        // Se crea el arreglo reservando el índice 0 libre (tamaño N + 1)
        this.heap = new int[this.tamano + 1];

        // Copiar los datos desplazando una posición
        System.arraycopy(arregloDesordenado, 0, this.heap, 1, this.tamano);

        // Algoritmo de Floyd para construir el Heap en O(N)
        construirHeap();
    }

    /**
     * Algoritmo Heapify de Floyd (Build-Heap) Bottom-Up.
     * Complejidad temporal: O(N).
     */
    private void construirHeap() {
        // Comienza en el último nodo padre (tamano / 2) y retrocede hasta la raíz (1)
        for (int i = this.tamano / 2; i >= 1; i--) {
            percolateDown(i);
        }
    }

    /**
     * Reubica el elemento en la posición 'i' intercambiándolo con su hijo de menor valor.
     */
    private void percolateDown(int i) {
        int valor = this.heap[i];
        int hijoMenor;

        while (2 * i <= this.tamano) {
            hijoMenor = 2 * i; // Hijo izquierdo

            // Si existe hijo derecho y es menor que el izquierdo
            if (hijoMenor + 1 <= this.tamano && this.heap[hijoMenor + 1] < this.heap[hijoMenor]) {
                hijoMenor++;
            }

            // Si la propiedad de Min-Heap se cumple, detener
            if (valor <= this.heap[hijoMenor]) {
                break;
            }

            this.heap[i] = this.heap[hijoMenor];
            i = hijoMenor;
        }

        this.heap[i] = valor;
    }

    public boolean estaVacio() {
        return this.tamano == 0;
    }

    public int obtenerTamano() {
        return this.tamano;
    }

    public int obtenerMinimo() {
        if (estaVacio()) {
            throw new IllegalStateException("El montículo está vacío.");
        }
        return this.heap[1];
    }

    public int eliminarMinimo() {
        if (estaVacio()) {
            throw new IllegalStateException("El montículo está vacío.");
        }

        int minimo = this.heap[1];
        this.heap[1] = this.heap[this.tamano];
        this.tamano--;

        if (this.tamano > 0) {
            percolateDown(1);
        }

        return minimo;
    }

    public void mostrarArreglo() {
        System.out.print("[");
        for (int i = 1; i <= tamano; i++) {
            System.out.print(heap[i] + (i < tamano ? ", " : ""));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE HEAPIFY DESDE ARREGLO DESORDENADO [O(N)] ===\n");

        int[] datosOriginales = {40, 20, 10, 30, 50, 60, 5, 15};

        System.out.print("1. Arreglo original desordenado: ");
        System.out.print("[");
        for (int i = 0; i < datosOriginales.length; i++) {
            System.out.print(datosOriginales[i] + (i < datosOriginales.length - 1 ? ", " : ""));
        }
        System.out.println("]");

        // Construir el MinHeap a partir del arreglo en O(N)
        HeapifyMinHeap heap = new HeapifyMinHeap(datosOriginales);

        System.out.print("2. Arreglo tras aplicar Heapify: ");
        heap.mostrarArreglo();

        System.out.println("   Mínimo (Raíz): " + heap.obtenerMinimo());
        System.out.println("   Último nodo padre procesado en Heapify (N / 2): índice " + (datosOriginales.length / 2));

        System.out.println("\n----------------------------------------------------\n");

        System.out.println("3. Extracción ordenada de elementos (eliminarMinimo):");
        while (!heap.estaVacio()) {
            System.out.println("-> Extraído: " + heap.eliminarMinimo());
        }
    }
}
