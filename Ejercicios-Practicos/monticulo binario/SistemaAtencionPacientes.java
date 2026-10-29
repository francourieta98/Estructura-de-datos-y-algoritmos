/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase 'SistemaAtencionPacientes' que simule la cola de atención de una guardia médica utilizando un MinHeap dinámico.
 * La clase debe contemplar:
 * 1. Definición de la entidad Paciente:
 *    - Atributos: 'nombre' (String), 'nivelUrgencia' (int, donde 1 es la máxima urgencia/prioridad) y 'ordenLlegada' (long, secuencial único).
 *    - Implementación de la interfaz 'Comparable<Paciente>' sobreescribiendo 'compareTo':
 *      * Comparación primaria: Menor 'nivelUrgencia' tiene mayor prioridad (revisa el MinHeap).
 *      * Comparación secundaria (Desempate FIFO): Si la urgencia es idéntica, el menor 'ordenLlegada' tiene prioridad.
 * 2. Estructura MinHeap Genérica / Orientada a Pacientes:
 *    - Arreglo de 'Paciente' dinámico iniciado en índice 1.
 *    - Métodos principales de la Cola de Prioridad:
 *      * 'registrarPaciente(String nombre, int urgencia)': Crea e inserta un paciente asignando un 'ordenLlegada' autoincremental.
 *      * 'proximoPaciente()': Consulta (peek) el paciente en la raíz sin atenderlo.
 *      * 'atenderProximoPaciente()': Remueve (poll) la raíz y retorna el paciente atendido.
 *      * 'estaVacio()' y 'obtenerCantidadPacientes()'.
 * 3. Explicación teórica de las preguntas clave:
 *    - Cómo representar un Paciente y por qué es indispensable implementar 'Comparable'.
 *    - Cómo se gestionan las comparaciones multinivel para resolver empates (estabilidad de la prioridad).
 *    - Qué operaciones mínimas debe exponer la Cola de Prioridad médica.
 * 4. Pruebas por Consola:
 *    - Registrar pacientes con urgencias mixtas y empates (ej. Urgencia 1 para emergencias, Urgencia 3 para consultas de guardia).
 *    - Simular la atención secuencial demostrando el cumplimiento estricto del orden de prioridad y el desempate por orden de llegada."
 * 
 * AJUSTES REALIZADOS LUEGO DE REVISAR EL CÓDIGO:
 * - Se integró un contador estático/instancia `contadorLlegada` para garantizar que cada paciente reciba un `ordenLlegada` estrictamente monótono e incremental al ingresar.
 * - Se adaptaron las comparaciones de `percolateUp` y `percolateDown` usando el método `compareTo` de Java (`pacienteA.compareTo(pacienteB) < 0`).
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * 
 * 1. CÓMO REPRESENTAR UN PACIENTE Y POR QUÉ SE NECESITA 'Comparable':
 *    Un tipo primitivo (`int`) posee un orden natural único. Un objeto complejo (`Paciente`) posee múltiples atributos.
 *    Para que el MinHeap sepa qué paciente "es menor" (tiene mayor prioridad de atención) sin acoplar la lógica de comparación 
 *    dentro del algoritmo de ordenamiento, la clase `Paciente` debe implementar `Comparable<Paciente>` y definir la relación formal en `compareTo()`.
 * 
 * 2. CÓMO RESOLVER EMPATES (Criterio de Estabilidad):
 *    Los heaps estándar no son inherentemente estables (pueden alterar el orden de elementos con la misma clave).
 *    Para solucionar esto, se añade el atributo `ordenLlegada`. Si `urgenciaA == urgenciaB`, se desempata evaluando `ordenLlegadaA < ordenLlegadaB`.
 * 
 * 3. OPERACIONES QUE EXPONE LA COLA DE PRIORIDAD:
 *    - `registrarPaciente` (Insert / Enqueue): $O(\log N)$
 *    - `atenderProximoPaciente` (Poll / Dequeue): $O(\log N)$
 *    - `proximoPaciente` (Peek): $O(1)$
 */

class Paciente implements Comparable<Paciente> {
    private String nombre;
    private int nivelUrgencia; // 1 = Máxima Urgencia (ej. Cuestión vital), 5 = Menor Urgencia
    private long ordenLlegada; // Contador incremental para preservar FIFO en empates

    public Paciente(String nombre, int nivelUrgencia, long ordenLlegada) {
        this.nombre = nombre;
        this.nivelUrgencia = nivelUrgencia;
        this.ordenLlegada = ordenLlegada;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivelUrgencia() {
        return nivelUrgencia;
    }

    public long getOrdenLlegada() {
        return ordenLlegada;
    }

    /**
     * Define el orden de prioridad en el MinHeap.
     * Retorna un número negativo si 'this' debe atenderse ANTES que 'otro'.
     */
    @Override
    public int compareTo(Paciente otro) {
        // 1. Criterio Principal: Menor nivel de urgencia pasa primero
        if (this.nivelUrgencia != otro.nivelUrgencia) {
            return Integer.compare(this.nivelUrgencia, otro.nivelUrgencia);
        }
        // 2. Criterio Secundario (Desempate): Menor orden de llegada (llegó antes) pasa primero
        return Long.compare(this.ordenLlegada, otro.ordenLlegada);
    }

    @Override
    public String toString() {
        return "Paciente: " + String.format("%-10s", nombre) + 
               " | Urgencia: " + nivelUrgencia + 
               " | Orden de Llegada: #" + ordenLlegada;
    }
}

public class SistemaAtencionPacientes {
    private Paciente[] heap;
    private int tamano;
    private long contadorLlegada; // Generador del orden de llegada
    private static final int CAPACIDAD_INICIAL = 10;

    public SistemaAtencionPacientes() {
        this.heap = new Paciente[CAPACIDAD_INICIAL];
        this.tamano = 0;
        this.contadorLlegada = 0;
    }

    public boolean estaVacio() {
        return this.tamano == 0;
    }

    public int obtenerCantidadPacientes() {
        return this.tamano;
    }

    /**
     * Registra un nuevo paciente asignándole un número de llegada secuencial.
     */
    public void registrarPaciente(String nombre, int nivelUrgencia) {
        if (this.tamano == this.heap.length - 1) {
            redimensionar();
        }

        this.contadorLlegada++;
        Paciente nuevo = new Paciente(nombre, nivelUrgencia, this.contadorLlegada);

        this.tamano++;
        this.heap[this.tamano] = nuevo;

        percolateUp(this.tamano);

        System.out.println("[+] Registrado: " + nuevo);
    }

    /**
     * Consulta el próximo paciente a ser atendido sin removerlo de la cola.
     */
    public Paciente proximoPaciente() {
        if (estaVacio()) {
            throw new IllegalStateException("No hay pacientes esperando en la guardia.");
        }
        return this.heap[1];
    }

    /**
     * Atiende y remueve al paciente con mayor prioridad de la cola.
     */
    public Paciente atenderProximoPaciente() {
        if (estaVacio()) {
            throw new IllegalStateException("No hay pacientes para atender.");
        }

        Paciente atendido = this.heap[1];

        // Reemplazar la raíz por el último paciente en la cola
        this.heap[1] = this.heap[this.tamano];
        this.heap[this.tamano] = null;
        this.tamano--;

        if (this.tamano > 0) {
            percolateDown(1);
        }

        return atendido;
    }

    private void percolateUp(int i) {
        Paciente valor = this.heap[i];

        // Mientras no lleguemos a la raíz y el paciente actual tenga MAYOR prioridad que su padre
        while (i > 1 && valor.compareTo(this.heap[i / 2]) < 0) {
            this.heap[i] = this.heap[i / 2];
            i = i / 2;
        }

        this.heap[i] = valor;
    }

    private void percolateDown(int i) {
        Paciente valor = this.heap[i];
        int hijoPrioritario;

        while (2 * i <= this.tamano) {
            hijoPrioritario = 2 * i;

            // Seleccionar el hijo con mayor prioridad de atención (el "menor" según compareTo)
            if (hijoPrioritario + 1 <= this.tamano && 
                this.heap[hijoPrioritario + 1].compareTo(this.heap[hijoPrioritario]) < 0) {
                hijoPrioritario++;
            }

            // Si el nodo actual tiene igual o mayor prioridad que su hijo prioritario, detener
            if (valor.compareTo(this.heap[hijoPrioritario]) <= 0) {
                break;
            }

            this.heap[i] = this.heap[hijoPrioritario];
            i = hijoPrioritario;
        }

        this.heap[i] = valor;
    }

    private void redimensionar() {
        Paciente[] nuevoHeap = new Paciente[this.heap.length * 2];
        System.arraycopy(this.heap, 0, nuevoHeap, 0, this.heap.length);
        this.heap = nuevoHeap;
    }

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE ATENCIÓN DE GUARDIA MÉDICA (MIN-HEAP PRIORITY QUEUE) ===\n");

        SistemaAtencionPacientes guardia = new SistemaAtencionPacientes();

        System.out.println("--- 1. Recepción y Registro de Pacientes ---");
        // Parametros: Nombre, Nivel Urgencia (1 = Máxima Urgencia / Código Rojo)
        guardia.registrarPaciente("Carlos", 3);   // Urgencia media
        guardia.registrarPaciente("Ana", 1);      // Emergencia vital
        guardia.registrarPaciente("Beatriz", 3);  // Misma urgencia que Carlos, pero llegó DESPUÉS
        guardia.registrarPaciente("David", 2);    // Urgencia alta
        guardia.registrarPaciente("Elena", 1);    // Emergencia vital, pero llegó DESPUÉS que Ana

        System.out.println("\nTotal de pacientes en espera: " + guardia.obtenerCantidadPacientes());
        System.out.println("Próximo paciente a ser atendido: " + guardia.proximoPaciente().getNombre());

        System.out.println("\n----------------------------------------------------\n");

        System.out.println("--- 2. Llamado a Consultorio (Orden de Atención) ---");
        int orden = 1;
        while (!guardia.estaVacio()) {
            Paciente atendido = guardia.atenderProximoPaciente();
            System.out.println("Atención #" + orden + " -> " + atendido);
            orden++;
        }

        System.out.println("\n¿Quedan pacientes en la guardia?: " + guardia.estaVacio());
    }
}
