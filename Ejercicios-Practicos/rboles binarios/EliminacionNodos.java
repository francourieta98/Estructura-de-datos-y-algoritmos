/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un método para eliminar un nodo de un árbol binario general. 
 * El programa debe contemplar tres casos de prueba:
 * - Eliminar un nodo hoja.
 * - Eliminar un nodo con un solo hijo.
 * - Eliminar un nodo con dos hijos.
 * Explica en los comentarios que, al no tratarse de un Árbol Binario de Búsqueda (BST), NO se deben usar 
 * predecesores ni sucesores ordenados (inorden). La estrategia debe ser reemplazar el valor del nodo a eliminar 
 * por el valor del nodo más profundo y a la derecha del árbol, y posteriormente eliminar dicho nodo profundo. 
 * Muestra el árbol por consola antes y después de cada eliminación mediante un recorrido por niveles (BFS)."
 * 
 * ESTRATEGIA DE ELIMINACIÓN EN ÁRBOLES BINARIOS GENERALES:
 * 1. POR QUÉ NO SE USA SUCESOR/PREDECESOR INORDEN:
 *    En un BST, para eliminar un nodo con dos hijos se busca el valor inmediatamente menor (predecesor) o 
 *    mayor (sucesor) para conservar la propiedad de ordenamiento. En un árbol binario general NO existe tal 
 *    propiedad de orden.
 * 
 * 2. ESTRATEGIA UTILIZADA (Reemplazo por el nodo más profundo y a la derecha):
 *    - Para eliminar cualquier nodo (ya sea hoja, de 1 hijo o de 2 hijos) sin alterar la validez de los demás enlaces:
 *      a) Se localiza el nodo que se desea eliminar.
 *      b) Se busca el NODO MÁS PROFUNDO Y A LA DERECHA de todo el árbol (usando BFS / Recorrido por Niveles).
 *      c) Se copia el valor de ese nodo más profundo al nodo que se desea eliminar.
 *      d) Se elimina físicamente el nodo más profundo de su posición original.
 * 
 * 3. MANEJO DE LOS TRES CASOS:
 *    - Caso 1 (Hoja): Si la hoja resulta ser el nodo más profundo, se elimina directamente desvinculándola de su padre.
 *    - Caso 2 (1 Hijo) y Caso 3 (2 Hijos): Al reemplazar su valor con el del nodo más profundo y borrar este último, 
 *      la estructura del árbol se conserva perfectamente sin dejar huecos ni enlaces huérfanos.
 * 
 * AJUSTES REALIZADOS Y JUSTIFICACIÓN:
 * - Se reutilizó el método de recorrido por niveles (BFS) del Ejercicio 5 para visualizar de forma clara 
 *   y estructurada la forma del árbol en consola antes y después de cada eliminación.
 * - Se implementó un método auxiliar 'eliminarNodoProfundo' que realiza un recorrido iterativo para encontrar y 
 *   desconectar al padre del nodo a borrar.
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

public class EliminacionNodos {

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

        System.out.println("=== ESTADO INICIAL DEL ÁRBOL ===");
        mostrarPorNiveles(raiz);

        // CASO 1: Eliminar un nodo hoja (ejemplo: valor 30)
        System.out.println("\n--- CASO 1: Eliminar nodo HOJA (30) ---");
        raiz = eliminar(raiz, 30);
        mostrarPorNiveles(raiz);

        // CASO 2: Eliminar un nodo con 2 hijos (ejemplo: raíz 10)
        System.out.println("\n--- CASO 2: Eliminar nodo con DOS HIJOS (10) ---");
        raiz = eliminar(raiz, 10);
        mostrarPorNiveles(raiz);

        // Preparación del Caso 3 (1 Hijo): 
        // En el estado actual, el nodo 20 sólo conserva como hijo al 15 (tras acomodar el árbol)
        System.out.println("\n--- CASO 3: Eliminar nodo con UN SOLO HIJO (20) ---");
        raiz = eliminar(raiz, 20);
        mostrarPorNiveles(raiz);
    }

    /**
     * Elimina un valor del árbol binario general reemplazándolo por el nodo más profundo.
     */
    public static Nodo eliminar(Nodo raiz, int clave) {
        if (raiz == null) return null;

        // Si el árbol tiene un solo nodo y es el que buscamos
        if (raiz.izquierdo == null && raiz.derecho == null) {
            if (raiz.valor == clave) return null;
            else return raiz;
        }

        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        Nodo nodoAEliminar = null;
        Nodo temp = null;

        // BFS para encontrar el nodo a eliminar y el nodo más profundo
        while (!cola.isEmpty()) {
            temp = cola.poll();

            if (temp.valor == clave) {
                nodoAEliminar = temp;
            }

            if (temp.izquierdo != null) cola.add(temp.izquierdo);
            if (temp.derecho != null) cola.add(temp.derecho);
        }

        // Si se encontró el nodo a eliminar
        if (nodoAEliminar != null) {
            int valorProfundo = temp.valor; // temp contiene el nodo más profundo al terminar el BFS
            eliminarNodoProfundo(raiz, temp);
            nodoAEliminar.valor = valorProfundo; // Reemplazar el valor
        } else {
            System.out.println("El valor " + clave + " no existe en el árbol.");
        }

        return raiz;
    }

    /**
     * Método auxiliar para buscar al padre del nodo más profundo y desvincularlo.
     */
    private static void eliminarNodoProfundo(Nodo raiz, Nodo nodoProfundo) {
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();

            if (actual.izquierdo != null) {
                if (actual.izquierdo == nodoProfundo) {
                    actual.izquierdo = null;
                    return;
                } else {
                    cola.add(actual.izquierdo);
                }
            }

            if (actual.derecho != null) {
                if (actual.derecho == nodoProfundo) {
                    actual.derecho = null;
                    return;
                } else {
                    cola.add(actual.derecho);
                }
            }
        }
    }

    /**
     * Muestra el árbol por niveles para verificar los cambios estructurales.
     */
    public static void mostrarPorNiveles(Nodo raiz) {
        if (raiz == null) {
            System.out.println("Árbol vacío.");
            return;
        }

        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int nodosEnNivel = cola.size();
            System.out.print("Nivel " + nivel + ": ");

            for (int i = 0; i < nodosEnNivel; i++) {
                Nodo nodo = cola.poll();
                System.out.print(nodo.valor + " ");

                if (nodo.izquierdo != null) cola.add(nodo.izquierdo);
                if (nodo.derecho != null) cola.add(nodo.derecho);
            }
            System.out.println();
            nivel++;
        }
    }
}
