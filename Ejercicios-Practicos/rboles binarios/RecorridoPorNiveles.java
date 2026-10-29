/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un recorrido por niveles (Breadth-First Search - BFS) de un árbol binario 
 * utilizando una cola (Queue). Muestra por consola los nodos nivel por nivel. 
 * Explica en los comentarios:
 * - Por qué este recorrido no se resuelve de la misma forma que preorden, inorden o postorden.
 * - Por qué se necesita una estructura de datos tipo cola (Queue).
 * - Qué nodo se encola primero.
 * - Cuándo se agregan los hijos a la cola."
 * 
 * EXPLICACIÓN REQUERIDA SOBRE EL RECORRIDO POR NIVELES:
 * 1. POR QUÉ NO SE RESUELVE IGUAL QUE PREORDEN, INORDEN O POSTORDEN:
 *    Los recorridos en profundidad (DFS: preorden, inorden y postorden) bajan tanto como es posible 
 *    por una rama antes de retroceder (usan una estructura tipo Pila / Call Stack mediante recursión). 
 *    En cambio, el recorrido por niveles es un recorrido en anchura (BFS) que debe procesar horizontalmente 
 *    todos los nodos de una misma profundidad antes de descender al siguiente nivel.
 * 
 * 2. POR QUÉ SE NECESITA UNA COLA (QUEUE):
 *    Se requiere una cola porque sigue el principio FIFO (First In, First Out / Primero en entrar, primero en salir). 
 *    Esto garantiza que los nodos se procesen estrictamente en el orden en que fueron descubiertos (de izquierda 
 *    a derecha y de arriba a abajo), preservando el orden horizontal por nivel.
 * 
 * 3. QUÉ NODO SE ENCOLA PRIMERO:
 *    Se encola primero el nodo RAÍZ del árbol para dar inicio al proceso de iteración.
 * 
 * 4. CUÁNDO SE AGREGAN LOS HIJOS A LA COLA:
 *    Los hijos se agregan a la cola justo en el momento en que se desencola y procesa su nodo padre. 
 *    En cuanto se atiende al padre, sus hijos izquierdo y derecho (si existen) se encolan al final 
 *    para ser procesados en el siguiente turno.
 * 
 * AJUSTES REALIZADOS Y JUSTIFICACIÓN:
 * - Se utilizó 'LinkedList' como implementación concreta del interfaz 'Queue' de Java Collections Framework.
 * - Se estructuró la impresión ciclo por ciclo (midiendo el tamaño de la cola en cada nivel) para mostrar 
 *   por consola visualmente separados los nodos que pertenecen a cada nivel.
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

public class RecorridoPorNiveles {

    public static void main(String[] args) {
        // Construcción del árbol binario de ejemplo:
        //       10          -> Nivel 0
        //      /  \
        //     5    20       -> Nivel 1
        //    / \   / \
        //   3   7 15 30     -> Nivel 2
        Nodo raiz = new Nodo(10);
        raiz.izquierdo = new Nodo(5);
        raiz.derecho = new Nodo(20);

        raiz.izquierdo.izquierdo = new Nodo(3);
        raiz.izquierdo.derecho = new Nodo(7);

        raiz.derecho.izquierdo = new Nodo(15);
        raiz.derecho.derecho = new Nodo(30);

        System.out.println("=== RECORRIDO POR NIVELES (BFS) ===");
        recorridoPorNiveles(raiz);
    }

    /**
     * Método iterativo para realizar el recorrido por niveles del árbol binario.
     * @param raiz Nodo raíz del árbol.
     */
    public static void recorridoPorNiveles(Nodo raiz) {
        if (raiz == null) {
            return;
        }

        // Crear la cola para el recorrido en anchura
        Queue<Nodo> cola = new LinkedList<>();

        // Paso 1: Encolar la raíz primero
        cola.add(raiz);

        int nivel = 0;

        // Iterar mientras existan nodos pendientes en la cola
        while (!cola.isEmpty()) {
            int nodosEnEsteNivel = cola.size();
            System.out.print("Nivel " + nivel + ": ");

            for (int i = 0; i < nodosEnEsteNivel; i++) {
                // Desencolar el nodo que toca procesar (FIFO)
                Nodo nodoActual = cola.poll();
                System.out.print(nodoActual.valor + " ");

                // Paso 2: Encolar los hijos del nodo actual si existen
                if (nodoActual.izquierdo != null) {
                    cola.add(nodoActual.izquierdo);
                }
                if (nodoActual.derecho != null) {
                    cola.add(nodoActual.derecho);
                }
            }
            
            System.out.println();
            nivel++;
        }
    }
}
