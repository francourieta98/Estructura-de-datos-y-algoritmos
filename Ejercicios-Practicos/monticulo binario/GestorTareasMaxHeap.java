/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase 'GestorTareasMaxHeap' que represente un gestor de tareas pendientes basado en una Cola de Prioridad con MaxHeap Genérico (MaxHeap<T extends Comparable<T>>).
 * 
 * La clase debe contemplar:
 * 1. Clase 'Tarea':
 *    - Atributos: 'descripcion' (String), 'prioridad' (int, mayor valor numérico = mayor prioridad de ejecución), 'duracionMinutos' (int).
 *    - Implementación de 'Comparable<Tarea>':
 *      * 'compareTo': Retorna un valor positivo si esta tarea tiene MAYOR prioridad numérica que la otra.
 *      * En caso de empate en prioridad numérica, utiliza la menor duración estimada como criterio de desempate.
 * 
 * 2. Clase Genérica 'MaxHeapGenerico<T extends Comparable<T>>':
 *    - Soporta cualquier tipo de objeto que implemente 'Comparable<T>'.
 *    - Arreglo interno 'Object[] heap' ajustado a la convención con índice 1 libre.
 *    - Métodos principales:
 *      * 'insert(T elemento)'
 *      * 'peek()': Devuelve el elemento con mayor prioridad ubicado en la raíz (índice 1) sin eliminarlo.
 *      * 'poll()': Extrae y devuelve el elemento máximo (raíz), reorganizando la estructura con 'percolateDown'.
 *      * 'estaVacio()' y 'obtenerTamano()'.
 * 
 * 3. Explicación teórica de las preguntas clave:
 *    - Cómo adaptar el heap para trabajar con objetos genéricos ('Comparable<T>' y casteo de tipos).
 *    - Cómo comparar tareas a través de 'compareTo'.
 *    - Qué significa 'mayor prioridad' en un MaxHeap (los valores con mayor peso/prioridad emergen hacia la raíz).
 *    - Qué deben devolver 'peek' (consulta en O(1)) y 'poll' (extracción en O(log N)).
 * 
 * 4. Pruebas por Consola:
 *    - Registrar una lista de tareas heterogéneas (ej. "Corrección de Bug Crítico", "Refactorización", "Documentación").
 *    - Consultar 'peek()' antes de procesar.
 *    - Ejecutar (poll) todas las tareas de manera secuencial comprobando el orden estricto de ejecución."
 * 
 * AJUSTES REALIZADOS LUEGO DE REVISAR EL CÓDIGO:
 * - Se implementó la supresión de advertencias `@SuppressWarnings("unchecked")` para realizar los casteos seguros del arreglo `Object[]` hacia el tipo genérico `T`.
 * - Se estructuró el método `compareTo` de `Tarea` para que mayor valor numérico signifique mayor prioridad dentro del MaxHeap.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * 
 * 1. CÓMO ADAPTAR EL HEAP PARA TRABAJAR CON OBJETOS GENÉRICOS:
 *    Java no permite instanciar directamente arreglos de tipos genéricos (`new T[capacidad]`). 
 *    Por ello, se utiliza un arreglo `Object[] heap` y se realiza un casteo `(T) heap[i]` al consultar o reubicar elementos. 
 *    Al exigir la restricción `<T extends Comparable<T>>`, nos aseguramos de que cualquier objeto ingresado pueda ser comparado usando `.compareTo()`.
 * 
 * 2. CÓMO COMPARAR TAREAS Y QUÉ SIGNIFICA "MAYOR PRIORIDAD":
 *    A diferencia del MinHeap del ejercicio anterior (donde el número 1 representaba la prioridad urgente), en un **MaxHeap** la raíz contiene el valor de **máximo peso**.
 *    Por tanto, una tarea con prioridad 100 es de mayor urgencia que una con prioridad 10. 
 *    En `compareTo()`, si `this.prioridad > otra.prioridad`, retorna un entero positivo, haciendo que el elemento ascienda hacia la raíz mediante `percolateUp`.
 * 
 * 3. QUÉ DEBEN DEVOLVER 'peek' Y 'poll':
 *    - `peek()`: Retorna `(T) heap[1]`, la tarea más urgente en la cima de la cola sin modificar la estructura ($O(1)$).
 *    - `poll()`: Remueve y retorna `(T) heap[1]`, reorganizando el árbol en $O(\log N)$ para posicionar la siguiente tarea más urgente en la raíz.
 */

import java.util.Objects;

class Tarea implements Comparable<Tarea> {
    private String descripcion;
    private int prioridad; // Mayor valor = Mayor prioridad
    private int duracionMinutos;

    public Tarea(String descripcion, int prioridad, int duracionMinutos) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.duracionMinutos = duracionMinutos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    @Override
    public int compareTo(Tarea otra) {
        // 1. Criterio Principal: Mayor valor numérico de prioridad pasa primero
        if (this.prioridad != otra.prioridad) {
            return Integer.compare(this.prioridad, otra.prioridad);
        }
        // 2. Criterio Secundario (Desempate): Menor duración estimada pasa primero
        return Integer.compare(otra.duracionMinutos, this.duracionMinutos);
    }

    @Override
    public String toString() {
        return String.format("Tarea: %-30s | Prioridad: %-3d | Duración: %d min", 
                             "'" + descripcion + "'", prioridad, duracionMinutos);
    }
}

/**
 * Montículo Binario Máximo Genérico para cualquier clase que implemente Comparable.
 */.
class MaxHeapGenerico<T extends Comparable<T>> {
    private Object[] heap;
    private int tamano;
    private static final int CAPACIDAD_INICIAL = 10;

    public MaxHeapGenerico() {
        this.heap = new Object[CAPACIDAD_INICIAL];
        this.tamano = 0; // Índice 0 libre
    }

    public boolean estaVacio() {
        return this.tamano == 0;
    }

    public int obtenerTamano() {
        return this.tamano;
    }

    @SuppressWarnings("unchecked")
    private T obtenerElemento(int idx) {
        return (T) this.heap[idx];
    }

    /**
     * Devuelve el elemento con mayor prioridad (situado en la raíz) sin eliminarlo.
     */
    public T peek() {
        if (estaVacio()) {
            throw new IllegalStateException("El MaxHeap está vacío.");
        }
        return obtenerElemento(1);
    }

    /**
     * Inserta un elemento en el MaxHeap y reorganiza mediante percolateUp.
     */
    public void insert(T elemento) {
        Objects.requireNonNull(elemento, "No se pueden insertar elementos nulos.");

        if (this.tamano == this.heap.length - 1) {
            redimensionar();
        }

        this.tamano++;
        this.heap[this.tamano] = elemento;

        percolateUp(this.tamano);
    }

    /**
     * Extrae y devuelve el elemento de mayor prioridad (raíz), reorganizando con percolateDown.
     */
    public T poll() {
        if (estaVacio()) {
            throw new IllegalStateException("El MaxHeap está vacío.");
        }

        T maximo = obtenerElemento(1);

        this.heap[1] = this.heap[this.tamano];
        this.heap[this.tamano] = null;
        this.tamano--;

        if (this.tamano > 0) {
            percolateDown(1);
        }

        return maximo;
    }

    private void percolateUp(int i) {
        T valor = obtenerElemento(i);

        // En MaxHeap: sube mientras el elemento actual sea MAYOR que su padre (compareTo > 0)
        while (i > 1 && valor.compareTo(obtenerElemento(i / 2)) > 0) {
            this.heap[i] = this.heap[i / 2];
            i = i / 2;
        }

        this.heap[i] = valor;
    }

    private void percolateDown(int i) {
        T valor = obtenerElemento(i);
        int hijoMayor;

        while (2 * i <= this.tamano) {
            hijoMayor = 2 * i;

            // Seleccionar el hijo con mayor prioridad (el "mayor" según compareTo)
            if (hijoMayor + 1 <= this.tamano && 
                obtenerElemento(hijoMayor + 1).compareTo(obtenerElemento(hijoMayor)) > 0) {
                hijoMayor++;
            }

            // Si el valor actual es mayor o igual que su hijo mayor, detener
            if (valor.compareTo(obtenerElemento(hijoMayor)) >= 0) {
                break;
            }

            this.heap[i] = obtenerElemento(hijoMayor);
            i = hijoMayor;
        }

        this.heap[i] = valor;
    }

    private void redimensionar() {
        Object[] nuevoHeap = new Object[this.heap.length * 2];
        System.arraycopy(this.heap, 0, nuevoHeap, 0, this.heap.length);
        this.heap = nuevoHeap;
    }
}

public class GestorTareasMaxHeap {

    public static void main(String[] args) {
        System.out.println("=== GESTOR DE TAREAS PENDIENTES (MAX-HEAP GENÉRICO) ===\n");

        MaxHeapGenerico<Tarea> colaTareas = new MaxHeapGenerico<>();

        System.out.println("--- 1. Carga de Tareas Pendientes ---");
        // Tarea(descripción, prioridad, duraciónMinutos)
        Tarea t1 = new Tarea("Actualizar Documentación", 20, 45);
        Tarea t2 = new Tarea("Corregir Fallo Crítico en BD", 95, 30);
        Tarea t3 = new Tarea("Refactorizar Módulo Auth", 50, 120);
        Tarea t4 = new Tarea("Atender Ticket de Soporte", 50, 15); // Misma prioridad que t3, pero menor duración
        Tarea t5 = new Tarea("Desplegar Hotfix a Producción", 100, 10);

        Tarea[] lista = {t1, t2, t3, t4, t5};
        for (Tarea t : lista) {
            colaTareas.insert(t);
            System.out.println("[+] Agregada: " + t);
        }

        System.out.println("\nTotal de tareas pendientes: " + colaTareas.obtenerTamano());
        System.out.println("Próxima tarea según prioridad (peek): " + colaTareas.peek().getDescripcion());

        System.out.println("\n----------------------------------------------------\n");

        System.out.println("--- 2. Ejecución Secuencial de Tareas (poll) ---");
        int paso = 1;
        while (!colaTareas.estaVacio()) {
            Tarea ejecutada = colaTareas.poll();
            System.out.println("Ejecutando #" + paso + " -> " + ejecutada);
            paso++;
        }

        System.out.println("\n¿Quedan tareas pendientes?: " + colaTareas.estaVacio());
    }
}
