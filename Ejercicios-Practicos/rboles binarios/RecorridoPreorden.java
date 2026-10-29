/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un método recursivo para recorrer un árbol binario en preorden y mostrar 
 * los valores visitados en el orden correcto. Explica en los comentarios:
 * - cuál es el caso base,
 * - qué se procesa primero,
 * - en qué orden se visitan los subárboles,
 * - por qué la solución es recursiva."
 * 
 * EXPLICACIÓN REQUERIDA SOBRE EL RECORRIDO PREORDEN:
 * 1. CASO BASE:
 *    Se alcanza cuando el nodo actual es 'null' (nodo == null). En este punto no hay ningún valor 
 *    que procesar ni subárboles que recorrer, por lo que la función retorna simplemente finalizando esa rama.
 * 
 * 2. QUÉ SE PROCESA PRIMERO:
 *    En el recorrido preorden, lo PRIMERO que se procesa (imprime o visita) es el nodo RAÍZ actual 
 *    antes de realizar cualquier llamada recursiva hacia las ramas.
 * 
 * 3. ORDEN EN QUE SE VISITAN LOS SUBÁRBOLES:
 *    El orden estricto de visita es: Raíz -> Subárbol Izquierdo -> Subárbol Derecho (R-I-D).
 *    Una vez visitada la raíz, se recorre completamente el subárbol izquierdo de forma recursiva 
 *    y, al terminar toda esa rama, se recorre el subárbol derecho.
 * 
 * 4. POR QUÉ LA SOLUCIÓN ES RECURSIVA:
 *    Un árbol binario es una estructura de datos auto-similar de naturaleza recursiva: cada subárbol 
 *    (izquierdo o derecho) es en sí mismo un árbol binario completo. La recursividad permite aplicar 
 *    exactamente la misma regla de visita (Raíz -> Izquierdo -> Derecho) a cada sub-nivel apoyándose 
 *    en la pila de llamadas del sistema (Call Stack) sin necesidad de gestionar pilas manuales.
 * 
 * AJUSTES REALIZADOS Y JUSTIFICACIÓN:
 * - Se reutilizó la estructura del árbol del Ejercicio 1 (Raíz 10, con hijos 5 y 20, etc.) para mantener 
 *   consistencia temática.
 * - Se añadió una salida limpia en consola formateada con flechas (->) para visualizar claramente 
 *   la secuencia exacta del recorrido.
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

public class RecorridoPreorden {

    public static void main(String[] args) {
        // Construcción del árbol binario de ejemplo
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

        System.out.println("=== RECORRIDO EN PREORDEN (Raíz -> Izquierdo -> Derecho) ===");
        System.out.print("Secuencia de visita: ");
        preorden(raiz);
        System.out.println("FIN");
    }

    /**
     * Método recursivo para el recorrido en preorden.
     * @param nodo Nodo actual visitado en la llamada recursiva.
     */
    public static void preorden(Nodo nodo) {
        // 1. Caso base: si el nodo es nulo, detenemos la recursión en esta rama
        if (nodo == null) {
            return;
        }

        // 2. Procesar primero el nodo actual (Raíz)
        System.out.print(nodo.valor + " -> ");

        // 3. Visitar recursivamente el subárbol izquierdo
        preorden(nodo.izquierdo);

        // 4. Visitar recursivamente el subárbol derecho
        preorden(nodo.derecho);
    }
}
