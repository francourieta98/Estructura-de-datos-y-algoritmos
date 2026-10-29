/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase 'ColaImpresionHeap' que simule una cola de impresión de documentos utilizando un MaxHeap.
 * La clase debe contemplar:
 * 1. Definición de la entidad TrabajoImpresion:
 *    - Atributos: 'nombreArchivo' (String), 'paginas' (int), 'prioridad' (int, donde mayor valor numérico = mayor prioridad de impresión) y 'ordenLlegada' (long, secuencial único).
 *    - Implementación de 'Comparable<TrabajoImpresion>':
 *      * Comparación primaria: Mayor 'prioridad' pasa primero a impresión.
 *      * Comparación secundaria (Desempate FIFO): Si la prioridad es idéntica, el menor 'ordenLlegada' (el que llegó antes) pasa primero.
 * 
 * 2. Explicación teórica de las preguntas planteadas:
 *    - Por qué conviene un MaxHeap sobre un MinHeap cuando asignamos mayor valor numérico a la máxima prioridad.
 *    - Cómo comparar prioridades y cómo resolver empates de forma estable garantizando el principio FIFO.
 *    - Qué métodos esenciales debe exponer la cola de impresión: 'agregarTrabajo', 'proximoTrabajo', 'imprimirProximoTrabajo', 'estaVacia' y 'obtenerCantidadTrabajos'.
 *    - Qué pruebas demuestran su correcto funcionamiento.
 * 
 * 3. Pruebas por Consola:
 *    - Encolar trabajos con prioridades heterogéneas y empates (ej. prioridad alta para reportes ejecutivos, prioridad normal para borradores).
 *    - Simular la impresión secuencial mostrando el nombre del archivo, páginas, prioridad y orden de llegada."
 * 
 * AJUSTES REALIZADOS LUEGO DE REVISAR EL CÓDIGO:
 * - Se integró la lógica de desempate invirtiendo la comparación de `ordenLlegada` dentro de `compareTo` (ya que en un MaxHeap el valor "mayor" según `compareTo` sube a la raíz; por ende, menor `ordenLlegada` debe retornar un valor positivo cuando `this.ordenLlegada < otro.ordenLlegada`).
 * - Se añadieron logs informativos al encolar e imprimir para simular el procesamiento real en un spooler de impresión.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * 
 * 1. ¿POR QUÉ CONVIENE MaxHeap O MinHeap?:
 *    Depende de la convención de negocio adoptada:
 *    - Si definimos que mayor valor numérico = mayor urgencia (ej. Prioridad 10 > Prioridad 1), **conviene un MaxHeap**, ya que coloca naturalmente los valores numéricos más altos en la raíz.
 *    - Si adoptáramos la convención tipo "código de urgencia" (donde 1 = Urgente y 5 = Bajas), convendría un MinHeap.
 *    Para este ejercicio implementamos un **MaxHeap**, de modo que las prioridades más altas emerjan hacia la cima.
 * 
 * 2. CÓMO COMPARAR PRIORIDADES Y RESOLVER EMPATES (Estabilidad FIFO):
 *    - **Comparación Primaria:** Si `this.prioridad != otra.prioridad`, comparamos directamente `Integer.compare(this.prioridad, otra.prioridad)`.
 *    - **Desempate (Misma Prioridad):** Se evalúa `ordenLlegada`. Al estar dentro de un MaxHeap, el elemento con **menor** `ordenLlegada` (el que ingresó primero al spooler) debe retornar un valor **mayor** para ganar la posición superior. Se realiza como `Long.compare(otra.ordenLlegada, this.ordenLlegada)`.
 * 
 * 3. MÉTODOS DE LA COLA DE IMPRESIÓN:
 *    - `agregarTrabajo(nombre, paginas, prioridad)`: Insert en $O(\log N)$.
 *    - `proximoTrabajo()`: Peek en $O(1)$.
 *    - `imprimirProximoTrabajo()`: Poll/Dequeue en $O(\log N)$.
 *    - `estaVacia()` y `obtenerCantidadTrabajos()`.
 * 
 * 4. PRUEBAS DEMOSTRATIVAS:
 *    - Encolar trabajos con distintas prioridades para verificar el despojo de los de prioridad baja.
 *    - Encolar múltiples trabajos con **misma prioridad** pero distinto momento de llegada para verificar el comportamiento estricto FIFO.
 */

class TrabajoImpresion implements Comparable<TrabajoImpresion> {
    private String nombreArchivo;
    private int paginas;
    private int prioridad; // Mayor valor numérico = Mayor prioridad
    private long ordenLlegada; // Contador incremental secuencial

    public TrabajoImpresion(String nombreArchivo, int paginas, int prioridad, long ordenLlegada) {
        this.nombreArchivo = nombreArchivo;
        this.paginas = paginas;
        this.prioridad = prioridad;
        this.ordenLlegada = ordenLlegada;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public int getPaginas() {
        return paginas;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public long getOrdenLlegada() {
        return ordenLlegada;
    }

    @Override
    public int compareTo(TrabajoImpresion otro) {
        // 1. Criterio Principal: Mayor prioridad numérica sube a la raíz del MaxHeap
        if (this.prioridad != otro.prioridad) {
            return Integer.compare(this.prioridad, otro.prioridad);
        }
        // 2. Criterio Secundario (Desempate FIFO): Menor orden de llegada gana prioridad en el MaxHeap
        return Long.compare(otro.ordenLlegada, this.ordenLlegada);
    }

    @Override
    public String toString() {
        return String.format("Archivo: %-25s | Páginas: %-3d | Prioridad: %-2d | Llegada: #%d",
                "'" + nombreArchivo + "'", paginas, prioridad, ordenLlegada);
    }
}

public class ColaImpresionHeap {
    private TrabajoImpresion[] heap;
    private int tamano;
    private long contadorLlegada;
    private static final int CAPACIDAD_INICIAL = 10;

    public ColaImpresionHeap() {
        this.heap = new TrabajoImpresion[CAPACIDAD_INICIAL];
        this.tamano = 0;
        this.contadorLlegada = 0;
