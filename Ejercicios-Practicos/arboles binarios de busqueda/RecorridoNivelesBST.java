/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un recorrido por niveles (Breadth-First Search - BFS) en un Árbol Binario de Búsqueda 
 * (BST) utilizando la interfaz Queue. Explica en los comentarios:
 * - Por qué este recorrido necesita una cola (Queue) y no se resuelve igual que los recorridos recursivos DFS.
 * - Cómo garantiza la cola el procesamiento nivel por nivel de izquierda a derecha.
 * Muestra por consola los valores del árbol organizados estrictamente nivel por nivel."
 * 
 * EXPLICACIÓN TÉCNICA SOBRE EL RECORRIDO BFS Y LA COLA (QUEUE):
 * 1. POR QUÉ NO SE RESUELVE IGUAL QUE LOS RECORRIDOS RECURSIVOS DFS:
 *    Los recorridos DFS (Inorden, Preorden, Postorden) exploran el árbol en profundidad usando la pila de 
 *    llamadas recursivas (Call Stack / LIFO - Last In, First Out). La recursión baja hasta el nodo más profundo 
 *    de una rama antes de retroceder. En cambio, el recorrido por niveles (BFS) explora el árbol en anchura: 
 *    debe procesar horizontalmente todos los nodos de un mismo nivel antes de pasar al nivel siguiente.
 * 
 * 2. POR QUÉ SE NECESITA UNA COLA (QUEUE):
 *    Se requiere una cola porque opera bajo el principio FIFO (First In, First Out / Primero en entrar, primero en salir). 
 *    Al desencolar un nodo padre para procesarlo, sus hijos se encolan al final. La naturaleza FIFO asegura 
 *    que todos los nodos del nivel actual se atiendan en el orden exacto en que fueron descubiertos antes de 
 *    empezar con los nodos del nivel inferior.
 * 
 * AJUSTES POSTERIORES Y RAZONES:
 * - Se utilizó `LinkedList` como implementación de `Queue` de la biblioteca estándar de Java (`java.util.*`).
 * - Se agrupó el ciclo con un bucle `for` interno basado en `cola.size()` para medir la cantidad exacta de nodos 
 *   en el nivel actual e imprimirlos agrupados visualmente por renglón.
 * 
 * MODIFICACIONES / CORRECCIONES MANUALES REALIZADAS:
 * - Se agregaron validaciones para comprobar que la raíz no sea nula al inicio del recorrido.
 */

import java.util.LinkedList;
import java.util.Queue;

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

public class RecorridoNivelesBST {
    private Nodo raiz;

    public RecorridoNivelesBST() {
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

    /**
     * Recorrido por niveles (BFS) que imprime los elementos agrupados por nivel.
     */
    public void recorridoPorNiveles() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        // Estructura de datos FIFO para controlar el orden por niveles
        Queue<Nodo> cola = new LinkedList<>();
        
        // Se encola primero la raíz
        cola.add(raiz);

        int nivel = 0;

        while (!cola.isEmpty()) {
            int nodosEnEsteNivel = cola.size();
            System.out.print("Nivel " + nivel + ": ");

            // Procesar únicamente los nodos pertenecientes al nivel actual
            for (int i = 0; i < nodosEnEsteNivel; i++) {
                Nodo nodoActual = cola.poll(); // Desencolar (FIFO)
                System.out.print(nodoActual.valor + " ");

                // Encolar hijo izquierdo si existe
                if (nodoActual.izquierdo != null) {
                    cola.add(nodoActual.izquierdo);
                }
                // Encolar hijo derecho si existe
                if (nodoActual.derecho != null) {
                    cola.add(nodoActual.derecho);
                }
            }

            System.out.println(); // Salto de línea para diferenciar niveles
            nivel++;
        }
    }

    public static void main(String[] args) {
        RecorridoNivelesBST bst = new RecorridoNivelesBST();

        // Carga de números de prueba en el BST
        // Visualmente el árbol quedará así:
        //        50          -> Nivel 0
        //      /    \
        //    30      70      -> Nivel 1
        //   /  \    /  \
        // 20   40  60  80    -> Nivel 2
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores) {
            bst.insertar(v);
        }

        System.out.println("=== RECORRIDO POR NIVELES (BFS) EN BST ===");
        bst.recorridoPorNiveles();
    }
}
