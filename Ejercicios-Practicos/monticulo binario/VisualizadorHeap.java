/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase 'VisualizadorHeap' que extienda o incluya las operaciones de un MinHeap básico basado en arreglo.
 * La clase debe incluir un método de inspección estructural que, tras cada inserción, imprima:
 * 1. El arreglo interno con los elementos válidos almacenados desde el índice 1 hasta 'tamano'.
 * 2. El valor actual de la raíz (mínimo).
 * 3. El detalle nodo por nodo mostrando:
 *    - Índice y Valor del nodo actual.
 *    - Su Padre (si existe, calculado como i / 2).
 *    - Su Hijo Izquierdo (si existe, $2 * i \le tamano$).
 *    - Su Hijo Derecho (si existe, $2 * i + 1 \le tamano$).
 * 
 * Explica en los comentarios:
 * - Cómo se representa el heap internamente mediante el arreglo contiguo iniciando en el índice 1.
 * - Cómo se recorre el arreglo de forma lineal desde $i = 1$ hasta $i = tamano$ para extraer la jerarquía del árbol.
 * - Cómo se calculan las relaciones aritméticas entre índices.
 * - Cómo se evita imprimir/consultar hijos o padres inexistentes mediante validaciones de límites de índices.
 * 
 * Muestra por consola una simulación paso a paso insertando los elementos [15, 10, 30, 5, 20] mostrando la estructura tras cada paso."
 * 
 * AJUSTES REALIZADOS LUEGO DE REVISAR EL CÓDIGO:
 * - Se añadió una rutina de formateo en consola para presentar el detalle de relaciones padre-hijos con alineación visual clara.
 * - Se aseguraron las comprobaciones estrictas de límites (`2 * i <= tamano` y `2 * i + 1 <= tamano`) para prevenir accesos fuera de rango (OutOfBounds) al consultar hijos.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * 
 * 1. RECORRIDO Y NAVEGACIÓN EN EL ARREGLO:
 *    Debido a que el Montículo Binario es un Árbol Binario Completo, no existen huecos entre los índices 1 y 'tamano'.
 *    Recorrer el arreglo linealmente con un bucle `for (int i = 1; i <= tamano; i++)` equivale exactamente a realizar un 
 *    recorrido del árbol **por niveles** (Level-Order Traversal) de arriba a abajo y de izquierda a derecha.
 * 
 * 2. CÁLCULO DE RELACIONES Y PREVENCIÓN DE HIJOS/PADRES INEXISTENTES:
 *    - Padre: Todo nodo con $i > 1$ posee un padre en la posición $\lfloor i / 2 \rfloor$. Para el nodo raíz ($i = 1$), se indica "Ninguno (Es Raíz)".
 *    - Hijo Izquierdo: Existe únicamente si $2 \times i \le tamano$.
 *    - Hijo Derecho: Existe únicamente si $2 \times i + 1 \le tamano$.
 *    Si la posición calculada supera a `tamano`, la rama no existe y se imprime "Ninguno".
 */

public class VisualizadorHeap {
    private int[] heap;
    private int tamano;
    private static final int CAPACIDAD_INICIAL = 15;

    public VisualizadorHeap() {
        this.heap = new int[CAPACIDAD_INICIAL];
        this.tamano = 0; // Índice 0 no utilizado
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

    public void insertar(int valor) {
        if (this.tamano == this.heap.length - 1) {
            redimensionar();
        }

        this.tamano++;
        this.heap[this.tamano] = valor;
        percolateUp(this.tamano);

        // Visualizar la estructura inmediatamente después de cada inserción
        imprimirEstadoDetallado(valor);
    }

    private void percolateUp(int i) {
        int valor = this.heap[i];
        while (i > 1 && valor < this.heap[i / 2]) {
            this.heap[i] = this.heap[i / 2];
            i = i / 2;
        }
        this.heap[i] = valor;
    }

    private void redimensionar() {
        int[] nuevoHeap = new int[this.heap.length * 2];
        System.arraycopy(this.heap, 0, nuevoHeap, 0, this.heap.length);
        this.heap = nuevoHeap;
    }

    /**
     * Imprime el estado completo del Heap: Arreglo, Raíz y Relaciones Nodo por Nodo.
     */
    public void imprimirEstadoDetallado(int ultimoInsertado) {
        System.out.println("==================================================================");
        System.out.println("TRAS INSERTAR EL VALOR: " + ultimoInsertado);
        System.out.println("==================================================================");

        // 1. Imprimir arreglo interno
        System.out.print("Arreglo interno [1 a " + tamano + "]: ");
        System.out.print("[");
        for (int i = 1; i <= tamano; i++) {
            System.out.print(heap[i] + (i < tamano ? ", " : ""));
        }
        System.out.println("]");

        // 2. Valor de la Raíz
        System.out.println("Valor de la Raíz (Mínimo): " + obtenerMinimo());
        System.out.println("\nDetalle de relaciones jerárquicas:");

        // 3. Inspección nodo por nodo
        for (int i = 1; i <= tamano; i++) {
            int valorNodo = heap[i];

            // Cálculo y validación del Padre
            String textoPadre = (i > 1) ? heap[i / 2] + " (índice " + (i / 2) + ")" : "Ninguno (Es Raíz)";

            // Cálculo y validación del Hijo Izquierdo
            int idxIzq = 2 * i;
            String textoIzq = (idxIzq <= tamano) ? heap[idxIzq] + " (índice " + idxIzq + ")" : "Ninguno";

            // Cálculo y validación del Hijo Derecho
            int idxDer = 2 * i + 1;
            String textoDer = (idxDer <= tamano) ? heap[idxDer] + " (índice " + idxDer + ")" : "Ninguno";

            System.out.printf(" - Nodo [%d] = %-3d | Padre: %-18s | Hijo Izq: %-15s | Hijo Der: %-15s\n",
                    i, valorNodo, textoPadre, textoIzq, textoDer);
        }
        System.out.println("==================================================================\n");
    }

    public static void main(String[] args) {
        VisualizadorHeap visualizador = new VisualizadorHeap();

        System.out.println("=== PRUEBA DEL VISUALIZADOR DE MONTÍCULO BINARIO (MIN-HEAP) ===\n");

        int[] datos = {15, 10, 30, 5, 20};

        // Insertar elementos uno a uno
        for (int x : datos) {
            visualizador.insertar(x);
        }
    }
}
