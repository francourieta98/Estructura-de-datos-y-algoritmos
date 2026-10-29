/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un método recursivo para recorrer un árbol binario en postorden y mostrar 
 * los valores visitados por consola. Explica en los comentarios:
 * - Por qué el nodo (padre) se procesa después de recorrer todos sus hijos.
 * - En qué situaciones prácticas o reales es útil procesar primero los hijos antes que el padre."
 * 
 * ¿POR QUÉ EL NODO SE PROCESA DESPUÉS DE RECORRER SUS HIJOS?
 * En el recorrido POSTORDEN (Izquierdo -> Derecho -> Raíz / I-D-R), el procesamiento del nodo padre 
 * depende del estado o resultado de sus subárboles. Se deben explorar y resolver completamente 
 * las ramas izquierda y derecha antes de poder realizar cualquier acción, cálculo o destrucción 
 * sobre el nodo actual.
 * 
 * SITUACIONES EN LAS QUE ES ÚTIL PROCESAR PRIMERO LOS HIJOS Y LUEGO EL PADRE:
 * 1. LIBERACIÓN / DESTRUCCIÓN DE MEMORIA O ARCHIVOS:
 *    Para eliminar un árbol completo en lenguajes como C/C++ o eliminar una estructura de carpetas 
 *    en un sistema de archivos, debes borrar primero los archivos/hijos interiores; de lo contrario, 
 *    perderías la referencia (puntero) para acceder a ellos si borras la carpeta padre primero.
 * 2. CÁLCULO DE PROPIEDADES DEPENDIENTES (Ej: Altura, Peso, Tamaño):
 *    Para calcular la altura o el tamaño total en disco de una carpeta/nodo, el padre necesita conocer 
 *    primero el tamaño final de todos sus subdirectorios e hijos para sumarlos.
 * 3. EVALUACIÓN DE EXPRESIONES MATEMÁTICAS (Árboles de expresión):
 *    Para evaluar un operador (como '+', '*', etc.) ubicado en un nodo raíz, primero se deben calcular 
 *    los resultados numéricos de sus operandos (hijos izquierdo y derecho).
 * 
 * AJUSTES REALIZADOS Y JUSTIFICACIÓN:
 * - Se reutilizó la estructura de árbol de los ejercicios previos para mantener la coherencia pedagógica 
 *   y permitir la comparación clara entre Preorden, Inorden y Postorden sobre el mismo conjunto de datos.
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

public class RecorridoPostorden {

    public static void main(String[] args) {
        // Construcción del árbol binario de ejemplo:
        //       10
        //      /  \
        //     5    20
        //    / \   / \
        //   3   7 15 30
        Nodo raiz = new Nodo(10);
        raiz.izquierdo = new Nodo(5);
        raiz.derecho = new Nodo(20);

        raiz.izquierdo.izquierdo = new Nodo(3);
        raiz.izquierdo.derecho = new Nodo(7);

        raiz.derecho.izquierdo = new Nodo(15);
        raiz.derecho.derecho = new Nodo(30);

        System.out.println("=== RECORRIDO EN POSTORDEN (Izquierdo -> Derecho -> Raíz) ===");
        System.out.print("Secuencia de visita: ");
        postorden(raiz);
        System.out.println("FIN");
    }

    /**
     * Método recursivo para el recorrido en postorden.
     * @param nodo Nodo actual visitado en la llamada recursiva.
     */
    public static void postorden(Nodo nodo) {
        // Caso base: subárbol vacío
        if (nodo == null) {
            return;
        }

        // 1. Recorrer primero el subárbol izquierdo
        postorden(nodo.izquierdo);

        // 2. Recorrer después el subárbol derecho
        postorden(nodo.derecho);

        // 3. Procesar finalmente el nodo actual (Raíz / Padre)
        System.out.print(nodo.valor + " -> ");
    }
}
