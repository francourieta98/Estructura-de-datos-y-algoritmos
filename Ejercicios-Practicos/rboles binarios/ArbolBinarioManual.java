/*
 * PROMPT INICIAL UTILIZADO:
 * "Genera un programa en Java que implemente un árbol binario de enteros construido manualmente, 
 * sin aplicar ningún tipo de regla de ordenamiento (no es un Árbol Binario de Búsqueda / BST).
 * El árbol debe tener la siguiente estructura exacta:
 *       10
 *      /  \
 *     5    20
 *    / \   / \
 *   3   7 15 30
 * Debe mostrar por consola la raíz, los hijos de cada nodo y cuáles son los nodos hoja."
 * 
 * AJUSTES REALIZADOS Y JUSTIFICACIÓN:
 * 1. Modularización de la detección de hojas: Se implementó un método recursivo (mostrarHojas) 
 *    para recorrer el árbol dinámicamente e identificar los nodos sin hijos (hojas), evitando hardcodear la salida.
 * 2. Método auxiliar de impresión de relaciones: Se añadió 'imprimirRelacion' para encapsular la verificación de 
 *    existencia de hijos y evitar duplicación de código redundante al mostrar la raíz e hijos de cada nodo.
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

public class ArbolBinarioManual {

    public static void main(String[] args) {
        // 1. Construcción manual de los nodos (Sin reglas de ordenamiento)
        Nodo raiz = new Nodo(10);
        
        Nodo nodo5 = new Nodo(5);
        Nodo nodo20 = new Nodo(20);
        
        Nodo nodo3 = new Nodo(3);
        Nodo nodo7 = new Nodo(7);
        Nodo nodo15 = new Nodo(15);
        Nodo nodo30 = new Nodo(30);

        // 2. Enlace manual de la jerarquía del árbol
        raiz.izquierdo = nodo5;
        raiz.derecho = nodo20;

        nodo5.izquierdo = nodo3;
        nodo5.derecho = nodo7;

        nodo20.izquierdo = nodo15;
        nodo20.derecho = nodo30;

        // 3. Muestra de la raíz
        System.out.println("=== ESTRUCTURA DEL ÁRBOL BINARIO ===");
        System.out.println("Raíz del árbol: " + raiz.valor);
        System.out.println("------------------------------------");

        // 4. Muestra de los hijos de cada nodo
        System.out.println("Hijos de cada nodo:");
        imprimirRelacion(raiz);
        imprimirRelacion(nodo5);
        imprimirRelacion(nodo20);
        imprimirRelacion(nodo3);
        imprimirRelacion(nodo7);
        imprimirRelacion(nodo15);
        imprimirRelacion(nodo30);

        // 5. Identificación y muestra de las hojas
        System.out.println("------------------------------------");
        System.out.print("Nodos hoja (sin hijos): ");
        mostrarHojas(raiz);
        System.out.println();
    }

    // Método auxiliar para imprimir los hijos directos de un nodo
    private static void imprimirRelacion(Nodo nodo) {
        if (nodo == null) return;
        
        String hijoIzq = (nodo.izquierdo != null) ? String.valueOf(nodo.izquierdo.valor) : "ninguno";
        String hijoDer = (nodo.derecho != null) ? String.valueOf(nodo.derecho.valor) : "ninguno";
        
        System.out.println("Nodo " + nodo.valor + " -> Hijo Izquierdo: " + hijoIzq + " | Hijo Derecho: " + hijoDer);
    }

    // Recorrido recursivo para identificar las hojas
    private static void mostrarHojas(Nodo nodo) {
        if (nodo == null) return;

        if (nodo.izquierdo == null && nodo.derecho == null) {
            System.out.print(nodo.valor + " ");
        } else {
            mostrarHojas(nodo.izquierdo);
            mostrarHojas(nodo.derecho);
        }
    }
}
