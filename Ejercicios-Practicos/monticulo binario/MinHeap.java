/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase 'MinHeap' que represente un Montículo Binario Mínimo basado en un arreglo dinámico de enteros.
 * La clase debe contemplar:
 * 1. Definición y propiedades:
 *    - Explicar por qué se utiliza un arreglo contiguo en lugar de punteros.
 *    - Explicar por qué es recomendable utilizar la convención de iniciar el almacenamiento desde el índice 1 (dejando el índice 0 libre).
 *    - Mostrar cómo se calculan las fórmulas aritméticas de acceso:
 *      * Padre de i: i / 2
 *      * Hijo izquierdo de i: 2 * i
 *      * Hijo derecho de i: 2 * i + 1
 * 2. Operaciones principales:
 *    - 'insertar(int x)': Agrega un elemento preservando la propiedad de Min-Heap mediante 'percolateUp' (filtrado hacia arriba).
 *    - 'obtenerMinimo()': Devuelve el elemento con la prioridad mínima (ubicado en el índice 1) sin eliminarlo.
 *    - 'eliminarMinimo()': Remueve y retorna la raíz, reorganizando la estructura con 'percolateDown' (filtrado hacia abajo).
 *    - 'estaVacio()': Retorna true si el montículo carece de elementos.
 *    - 'obtenerTamano()': Retorna la cantidad actual de elementos válidos en el montículo.
 * 3. Casos Borde y Excepciones:
 *    - Consultar o eliminar en un montículo vacío (lanzar IllegalStateException).
 *    - Redimensionamiento dinámico del arreglo cuando este alcanza su capacidad máxima.
 * 4. Pruebas por Consola:
 *    - Probar la inserción de una secuencia desordenada [45, 20, 14, 12, 31, 7, 11].
 *    - Consultar el mínimo.
 *    - Extraer secuencialmente todos los elementos para verificar la propiedad de ordenamiento del montículo."
 * 
 * AJUSTES REALIZADOS LUEGO DE REVISAR EL CÓDIGO:
 * - Se inicializó el arreglo interno con una capacidad por defecto de 10 posiciones (índices 0 al 9, almacenando hasta 9 elementos útiles).
 * - Se agregó un método 'redimensionar()' para duplicar la capacidad del arreglo automáticamente cuando `tamano == capacidad - 1`.
 * - Se optimizaron las llamadas en `percolateDown` asegurando que el intercambio se realice con el hijo de MENOR valor entre el izquierdo y el derecho.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * 
 * 1. ¿POR QUÉ SE USA UN ARREGLO EN LUGAR DE CÓDIGO CON REFERENCIAS/PUNTEROS?
 *    Un Montículo Binario es un Árbol Binario Completo (todos los niveles están totalmente llenos, excepto posiblemente el último, 
 *    el cual se llena de izquierda a derecha). Esta propiedad geométrica estricta permite mapear perfectamente cada nodo en las posiciones 
 *    de un arreglo contiguo sin dejar "huecos" ni requerir la sobrecarga de memoria de punteros/referencias a objetos.
 * 
 * 2. ¿POR QUÉ SE RECOMIENDA INICIAR DESDE EL ÍNDICE 1?
 *    Iniciar en el índice 1 simplifica la aritmética de desplazamiento mediante operaciones de división y multiplicación binaria directa por 2 (desplazamientos de bits):
 *    - Padre de $i$: $\lfloor i / 2 \rfloor$
 *    - Hijo Izquierdo de $i$: $2 \times i$
 *    - Hijo Derecho de $i$: $2 \times i + 1$
 *    Si se iniciara en el índice 0, las fórmulas requerirían sumas/restas de desplazamiento adicionales: $padre = (i - 1) / 2$, $hijoIzq = 2i + 1$, $hijoDer = 2i + 2$.
 * 
 * 3. ¿CUÁNDO SE APLICA PERCOLATE UP (Filtrado hacia arriba)?
 *    Se ejecuta tras una **inserción**. El nuevo elemento se coloca temporalmente en la primera posición disponible al final del arreglo (preservando la forma de árbol completo).
 *    Luego, mientras sea MENOR que su padre, intercambia posiciones subiendo por la rama hasta restaurar la propiedad de Min-Heap ($padre \le hijo$).
 * 
 * 4. ¿CUÁNDO SE APLICA PERCOLATE DOWN (Filtrado hacia abajo)?
 *    Se ejecuta tras una **eliminación del mínimo**. La raíz (mínimo) es reemplazada por el último elemento del arreglo. 
 *    Luego, mientras este elemento sea MAYOR que alguno de sus hijos, intercambia posiciones descendiendo hacia la rama del hijo MENOR hasta restaurar el orden del Min-Heap.
 */

public class MinHeap {
    private int[] heap;
    private int tamano;
    private static final int CAPACIDAD_INICIAL = 10;

    /**
     * Constructor que inicializa el montículo con la capacidad por defecto.
     */
    public MinHeap() {
        this.heap = new int[CAPACIDAD_INICIAL];
        this.tamano = 0; // El índice 0 queda libre/reservado
    }

    /**
     * Retorna si el montículo está vacío.
     */
    public boolean estaVacio() {
        return this.tamano == 0;
    }

    /**
     * Retorna la cantidad actual de elementos en el montículo.
     */
    public int obtenerTamano() {
        return this.tamano;
    }

    /**
     * Consulta el elemento mínimo sin extraerlo.
     * @throws IllegalStateException si el montículo está vacío.
     */
    public int obtenerMinimo() {
        if (estaVacio()) {
            throw new IllegalStateException("Error: No se puede consultar el mínimo en un montículo vacío.");
        }
        return this.heap[1]; // El mínimo siempre está en la raíz (índice 1)
    }

    /**
     * Inserta un nuevo valor en el montículo.
     */
    public void insertar(int valor) {
        // Redimensionar si el arreglo se llena
        if (this.tamano == this.heap.length - 1) {
            redimensionar();
        }

        // 1. Insertar el nuevo valor en la última posición disponible
        this.tamano++;
        this.heap[this.tamano] = valor;

        // 2. Restaurar la propiedad de Min-Heap mediante filtrado hacia arriba
        percolateUp(this.tamano);
    }

    /**
     * Extrae y retorna el elemento mínimo del montículo.
     * @throws IllegalStateException si el montículo está vacío.
     */
    public int eliminarMinimo() {
        if (estaVacio()) {
            throw new IllegalStateException("Error: No se puede eliminar elementos de un montículo vacío.");
        }

        int minimo = this.heap[1];

        // 1. Reemplazar la raíz con el último elemento del montículo
        this.heap[1] = this.heap[this.tamano];
        this.tamano--;

        // 2. Restaurar la propiedad de Min-Heap mediante filtrado hacia abajo si aún quedan elementos
        if (this.tamano > 0) {
            percolateDown(1);
        }

        return minimo;
    }

    /**
     * Filtrado hacia arriba (Percolate Up): Sube el elemento en la posición 'i'
     * mientras sea menor que su nodo padre.
     */
    private void percolateUp(int i) {
        int valor = this.heap[i];

        // Mientras no lleguemos a la raíz (i > 1) y el valor sea menor que su padre
        while (i > 1 && valor < this.heap[i / 2]) {
            this.heap[i] = this.heap[i / 2]; // Bajar al padre
            i = i / 2;                       // Ascender al índice del padre
        }

        this.heap[i] = valor; // Colocar el valor en su posición correcta
    }

    /**
     * Filtrado hacia abajo (Percolate Down): Reubica el elemento en la posición 'i'
     * intercambiándolo con su hijo de menor valor.
     */
    private void percolateDown(int i) {
        int valor = this.heap[i];
        int hijoMenor;

        // Mientras el nodo tenga al menos un hijo izquierdo (2 * i <= tamano)
        while (2 * i <= this.tamano) {
            hijoMenor = 2 * i; // Hijo izquierdo por defecto

            // Si existe un hijo derecho y es menor que el hijo izquierdo
            if (hijoMenor + 1 <= this.tamano && this.heap[hijoMenor + 1] < this.heap[hijoMenor]) {
                hijoMenor++; // Seleccionar el hijo derecho
            }

            // Si el valor actual es menor o igual que su hijo menor, la propiedad está restaurada
            if (valor <= this.heap[hijoMenor]) {
                break;
            }

            // Subir al hijo menor
            this.heap[i] = this.heap[hijoMenor];
            i = hijoMenor; // Descender al índice del hijo menor
        }

        this.heap[i] = valor; // Colocar el valor en su posición correcta
    }

    /**
     * Duplica la capacidad del arreglo cuando se llena.
     */
    private void redimensionar() {
        int[] nuevoHeap = new int[this.heap.length * 2];
        System.arraycopy(this.heap, 0, nuevoHeap, 0, this.heap.length);
        this.heap = nuevoHeap;
    }

    /**
     * Imprime el contenido raw del arreglo interno para verificación de pruebas.
     */
    public void mostrarArreglo() {
        System.out.print("Arreglo del Heap [Índices 1 a " + tamano + "]: ");
        for (int i = 1; i <= tamano; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE MONTÍCULO BINARIO MÍNIMO (MIN-HEAP) ===\n");

        MinHeap minHeap = new MinHeap();

        // 1. Inserción de elementos
        int[] datos = {45, 20, 14, 12, 31, 7, 11};
        System.out.print("Insertando elementos: ");
        for (int x : datos) {
            System.out.print(x + " ");
            minHeap.insertar(x);
        }
        System.out.println("\n");

        // 2. Estado interno del montículo
        minHeap.mostrarArreglo();
        System.out.println("Cantidad de elementos: " + minHeap.obtenerTamano());
        System.out.println("Elemento Mínimo (Raíz): " + minHeap.obtenerMinimo());

        System.out.println("\n----------------------------------------------------\n");

        // 3. Extracción ordenada secuencial de todos los elementos
        System.out.println("Extrayendo elementos en orden de prioridad (Eliminar Mínimo):");
        while (!minHeap.estaVacio()) {
            System.out.println("-> Extraído: " + minHeap.eliminarMinimo());
        }

        // 4. Verificación de caso borde (vacío)
        System.out.println("\n¿El heap está vacío?: " + minHeap.estaVacio());
        try {
            minHeap.obtenerMinimo();
        } catch (IllegalStateException e) {
            System.out.println("Excepción capturada con éxito: " + e.getMessage());
        }
    }
}
