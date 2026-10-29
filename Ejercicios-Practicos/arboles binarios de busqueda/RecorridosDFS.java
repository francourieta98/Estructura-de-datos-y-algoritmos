/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java una clase BST que permita insertar una serie de números enteros y muestre 
 * por consola los tres recorridos en profundidad (DFS): Inorden, Preorden y Postorden. 
 * Explica en los comentarios:
 * - Qué orden de visita sigue cada recorrido (ej. Izquierdo-Raíz-Derecho).
 * - Para qué sirve o en qué casos prácticos se utiliza cada uno de ellos.
 * Carga el árbol con varios elementos y compara las salidas por consola."
 * 
 * EXPLICACIÓN TÉCNICA Y USOS PRÁCTICOS DE LOS RECORRIDOS DFS EN UN BST:
 * 
 * 1. RECORRIDO INORDEN (Izquierdo -> Raíz -> Derecho):
 *    - Orden: Visita primero el subárbol izquierdo, procesa el nodo actual (raíz) y luego el subárbol derecho.
 *    - Utilidad/Uso práctico: En un Árbol Binario de Búsqueda (BST), el recorrido inorden permite obtener 
 *      todos los elementos ordenados de menor a mayor. Es ideal para reportes ordenados o exportación de datos.
 * 
 * 2. RECORRIDO PREORDEN (Raíz -> Izquierdo -> Derecho):
 *    - Orden: Procesa primero el nodo actual (raíz) y luego visita los subárboles izquierdo y derecho.
 *    - Utilidad/Uso práctico: Es muy útil para clonar/duplicar la estructura exacta de un árbol o para 
 *      serializarlo en un archivo de texto/BD para que al reconstruirlo se cree con la misma forma original.
 * 
 * 3. RECORRIDO POSTORDEN (Izquierdo -> Derecho -> Raíz):
 *    - Orden: Visita los subárboles izquierdo y derecho antes de procesar el nodo actual (raíz).
 *    - Utilidad/Uso práctico: Ideal para operaciones "bottom-up" (de abajo hacia arriba), como la liberación 
 *      de memoria, eliminación completa de árboles, cálculo de tamaño/altura de carpetas o evaluación de expresiones.
 * 
 * AJUSTES POSTERIORES Y RAZONES:
 * - Se reutilizaron los métodos de inserción estándar del BST para mantener la coherencia.
 * - Se agregaron separadores en consola para comparar de forma inmediata cómo un mismo conjunto de datos 
 *   produce secuencias de valores totalmente distintas según el tipo de recorrido.
 * 
 * MODIFICACIONES / CORRECCIONES MANUALES REALIZADAS:
 * - Se formateó la salida del método 'main' agregando la lista original de inserción para facilitar la trazabilidad.
 */

class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class RecorridosDFS {
    private Nodo raiz;

    public RecorridosDFS() {
        this.raiz = null;
    }

    // Inserción en el BST
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }
        if (valor < actual.valor) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        return actual;
    }

    // 1. Recorrido Inorden (Izquierdo -> Raíz -> Derecho)
    public void mostrarInorden() {
        System.out.print("Inorden  (I-R-D): ");
        inorden(raiz);
        System.out.println("FIN");
    }

    private void inorden(Nodo nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.print(nodo.valor + " -> ");
            inorden(nodo.derecho);
        }
    }

    // 2. Recorrido Preorden (Raíz -> Izquierdo -> Derecho)
    public void mostrarPreorden() {
        System.out.print("Preorden (R-I-D): ");
        preorden(raiz);
        System.out.println("FIN");
    }

    private void preorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " -> ");
            preorden(nodo.izquierdo);
            preorden(nodo.derecho);
        }
    }

    // 3. Recorrido Postorden (Izquierdo -> Derecho -> Raíz)
    public void mostrarPostorden() {
        System.out.print("Postorden (I-D-R): ");
        postorden(raiz);
        System.out.println("FIN");
    }

    private void postorden(Nodo nodo) {
        if (nodo != null) {
            postorden(nodo.izquierdo);
            postorden(nodo.derecho);
            System.out.print(nodo.valor + " -> ");
        }
    }

    public static void main(String[] args) {
        RecorridosDFS bst = new RecorridosDFS();

        // Carga de varios números en el BST
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        
        System.out.println("=== INSERTANDO ELEMENTOS EN EL BST ===");
        System.out.print("Secuencia de inserción: ");
        for (int v : valores) {
            System.out.print(v + " ");
            bst.insertar(v);
        }
        System.out.println("\n");

        System.out.println("=== COMPARACIÓN DE SALIDAS DE LOS RECORRIDOS DFS ===");
        bst.mostrarInorden();  // Muestra los valores ordenados de menor a mayor
        bst.mostrarPreorden(); // Muestra primero la raíz de cada subárbol
        bst.mostrarPostorden(); // Muestra primero los nodos hoja de cada rama
    }
}
