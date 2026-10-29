/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase 'MaxHeap' que represente un Montículo Binario Máximo basado en un arreglo dinámico de enteros.
 * Explica en los comentarios de cabecera:
 * 1. Qué cambia respecto del MinHeap:
 *    - La propiedad del montículo se invierte: para todo nodo 'i' en el índice > 1, su nodo padre debe ser mayor o igual (padre >= hijo).
 * 2. Qué comparaciones se modifican:
 *    - En 'percolateUp': el elemento sube mientras sea MAYOR que su padre (valor > heap[i / 2]).
 *    - En 'percolateDown': el elemento desciende comparándose con el hijo de MAYOR valor.
 * 3. Comportamiento de 'insert' ('insertar'):
 *    - Inserta el elemento al final del arreglo y ejecuta 'percolateUp' haciendo flotar los valores más grandes hacia la cima.
 * 4. Comportamiento de 'poll' ('eliminarMaximo'):
 *    - Reemplaza la raíz por el último elemento y ejecuta 'percolateDown', hundiendo los valores más pequeños para que el nuevo máximo ascienda a la raíz.
 * 5. Valor que devuelve 'peek' ('obtenerMaximo'):
 *    - Retorna siempre el elemento mayor situado en la raíz (índice 1).
 * 
 * Muestra pruebas por consola insertando la secuencia [10, 50, 15, 40, 60, 20, 5] y extrayendo progresivamente los elementos para verificar el orden descendente."
 * 
 * AJUSTES REALIZADOS LUEGO DE REVISAR EL CÓDIGO:
 * - Se adaptó la nomenclatura de los métodos para soportar tanto la terminología estándar (`insert`, `poll`, `peek`) como sus equivalentes descriptivos (`insertar`, `eliminarMaximo`, `obtenerMaximo`).
 * - Se aseguró que `percolateDown` seleccione de forma estricta al hijo MAYOR entre el izquierdo y el derecho para los intercambios descendentes.
 */

public class MaxHeap {
    private int[] heap;
    private int tamano;
    private static final int CAPACIDAD_INICIAL = 10;

    public MaxHeap() {
        this.heap = new int[CAPACIDAD_INICIAL];
        this.tamano = 0; // Índice 0 libre
    }

    public boolean estaVacio() {
        return this.tamano == 0;
    }

    public int obtenerTamano() {
        return this.tamano;
    }

    /**
     * Equivalente a peek(): Retorna el valor máximo sin eliminarlo.
     */
    public int peek() {
        if (estaVacio()) {
            throw new IllegalStateException("Error: No se puede consultar el máximo en un montículo vacío.");
        }
        return this.heap[1]; // El máximo siempre está en la raíz
    }

    public int obtenerMaximo() {
        return peek();
    }

    /**
     * Equivalente a insert(): Agrega un nuevo valor al MaxHeap.
     */
    public void insert(int valor) {
        if (this.tamano == this.heap.length - 1) {
            redimensionar();
        }

        this.tamano++;
        this.heap[this.tamano] = valor;

        // Filtrado hacia arriba para flotar el valor si es mayor que sus ancestros
        percolateUp(this.tamano);
    }

    public void insertar(int valor) {
        insert(valor);
    }

    /**
     * Equivalente a poll(): Remueve y devuelve el elemento máximo.
     */
    public int poll() {
        if (estaVacio()) {
            throw new IllegalStateException("Error: No se puede eliminar elementos de un montículo vacío.");
        }

        int maximo = this.heap[1];

        // Reemplazar raíz con el último elemento
        this.heap[1] = this.heap[this.tamano];
        this.tamano--;

        // Filtrado hacia abajo si aún quedan elementos
        if (this.tamano > 0) {
            percolateDown(1);
        }

        return maximo;
    }

    public int eliminarMaximo() {
        return poll();
    }

    /**
     * Filtrado hacia arriba (Percolate Up): Sube el elemento si es MAYOR que su padre.
     */
    private void percolateUp(int i) {
        int valor = this.heap[i];

        // Se invierte la comparación: sube mientras valor > padre
        while (i > 1 && valor > this.heap[i / 2]) {
            this.heap[i] = this.heap[i / 2];
            i = i / 2;
        }

        this.heap[i] = valor;
    }

    /**
     * Filtrado hacia abajo (Percolate Down): Desciende intercambiando con el hijo MAYOR.
     */
    private void percolateDown(int i) {
        int valor = this.heap[i];
        int hijoMayor;

        while (2 * i <= this.tamano) {
            hijoMayor = 2 * i; // Hijo izquierdo por defecto

            // Se invierte la comparación: seleccionar el hijo con el valor MAYOR
            if (hijoMayor + 1 <= this.tamano && this.heap[hijoMayor + 1] > this.heap[hijoMayor]) {
                hijoMayor++; // Seleccionar hijo derecho
            }

            // Si el valor es mayor o igual que su hijo mayor, la propiedad está restaurada
            if (valor >= this.heap[hijoMayor]) {
                break;
            }

            this.heap[i] = this.heap[hijoMayor];
            i = hijoMayor;
        }

        this.heap[i] = valor;
    }

    private void redimensionar() {
        int[] nuevoHeap = new int[this.heap.length * 2];
        System.arraycopy(this.heap, 0, nuevoHeap, 0, this.heap.length);
        this.heap = nuevoHeap;
    }

    public void mostrarArreglo() {
        System.out.print("Arreglo del MaxHeap [1 a " + tamano + "]: ");
        for (int i = 1; i <= tamano; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE MONTÍCULO BINARIO MÁXIMO (MAX-HEAP) ===\n");

        MaxHeap maxHeap = new MaxHeap();

        int[] datos = {10, 50, 15, 40, 60, 20, 5};
        System.out.print("Insertando elementos: ");
        for (int x : datos) {
            System.out.print(x + " ");
            maxHeap.insert(x);
        }
        System.out.println("\n");

        maxHeap.mostrarArreglo();
        System.out.println("Cantidad de elementos: " + maxHeap.obtenerTamano());
        System.out.println("Elemento Máximo (peek): " + maxHeap.peek());

        System.out.println("\n----------------------------------------------------\n");

        System.out.println("Extrayendo elementos en orden de prioridad descendente (poll):");
        while (!maxHeap.estaVacio()) {
            System.out.println("-> Extraído: " + maxHeap.poll());
        }

        System.out.println("\n¿El heap está vacío?: " + maxHeap.estaVacio());
    }
}
