/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un método recursivo para contar la cantidad total de nodos de un árbol binario. 
 * Muestra el total resultante por consola. Explica detalladamente la lógica recursiva en los comentarios:
 * - Si el nodo es null, devuelve 0 (caso base).
 * - Si el nodo existe, cuenta 1 (el nodo actual) más los nodos del subárbol izquierdo más los nodos del subárbol derecho."
 * 
 * LÓGICA RECURSIVA PARA CONTAR NODOS:
 * 1. CASO BASE: 
 *    Si el nodo es 'null' (nodo == null), significa que la rama está vacía, por lo que aporta 0 nodos al conteo total.
 * 2. PASO RECURSIVO:
 *    Si el nodo existe, la cantidad total de nodos en ese punto es:
 *    Total = 1 (nodo actual) + contarNodos(nodo.izquierdo) + contarNodos(nodo.derecho)
 *    
 *    Esta fórmula se aplica acumulativamente desde las hojas hacia arriba a través de la pila de llamadas.
 * 
 * AJUSTES REALIZADOS Y JUSTIFICACIÓN:
 * - Se mantuvo la misma estructura de árbol con 7 nodos (Raíz 10, hijos 5 y 20, hojas 3, 7, 15, 30) 
 *   para verificar de manera inmediata e intuitiva que la salida por consola dé exactamente 7.
 * - Se agregó una prueba adicional pasando un subárbol específico para evidenciar la reusabilidad del método.
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

public class ContarNodos {

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

        // Contar el total de nodos del árbol completo
        int totalNodos = contarNodos(raiz);

        System.out.println("=== CONTEO DE NODOS EN ÁRBOL BINARIO ===");
        System.out.println("Cantidad total de nodos en el árbol: " + totalNodos);
        
        // Prueba adicional: contar solo el subárbol izquierdo (raíz en nodo 5)
        int nodosSubarbolIzq = contarNodos(raiz.izquierdo);
        System.out.println("Cantidad de nodos en el subárbol izquierdo (nodo 5): " + nodosSubarbolIzq);
    }

    /**
     * Método recursivo para contar los nodos de un árbol binario.
     * @param nodo Nodo actual visitado.
     * @return Número total de nodos en la estructura apuntada por 'nodo'.
     */
    public static int contarNodos(Nodo nodo) {
        // Caso base: si el nodo es nulo, no suma al conteo
        if (nodo == null) {
            return 0;
        }

        // 1 (nodo actual) + nodos subárbol izquierdo + nodos subárbol derecho
        return 1 + contarNodos(nodo.izquierdo) + contarNodos(nodo.derecho);
    }
}
