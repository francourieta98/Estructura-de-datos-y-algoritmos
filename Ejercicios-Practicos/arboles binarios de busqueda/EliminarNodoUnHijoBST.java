/*
 * PROMPT INICIAL UTILIZADO:
 * "Extiende la lógica de eliminación en un Árbol Binario de Búsqueda (BST) en Java para resolver el caso 
 * donde el nodo a eliminar tiene un solo hijo (ya sea únicamente el izquierdo o únicamente el derecho). 
 * Explica en los comentarios:
 * - Cómo se reconecta el árbol para no perder el subárbol restante del nodo eliminado.
 * - Por qué devolver la referencia del hijo directamente al nodo padre permite 'saltear' el nodo eliminado.
 * Prueba el programa eliminando un nodo que tenga solo hijo izquierdo y otro que tenga solo hijo derecho, 
 * mostrando el recorrido inorden antes y después de cada operación."
 * 
 * LÓGICA DE RECONEXIÓN PARA NODOS CON UN SOLO HIJO EN BST:
 * 1. IDENTIFICACIÓN DEL CASO:
 *    Un nodo se encuentra en esta situación cuando una de sus ramas es nula y la otra no:
 *    - Tiene solo hijo derecho: `nodo.izquierdo == null && nodo.derecho != null`
 *    - Tiene solo hijo izquierdo: `nodo.derecho == null && nodo.izquierdo != null`
 * 
 * 2. CÓMO SE RECONECTA EL ÁRBOL (By-pass o Salteo de nodo):
 *    Cuando el método recursivo encuentra el nodo a eliminar, en lugar de retornar `null` (como en el caso de las hojas), 
 *    retorna directamente la referencia a su único hijo existente:
 *    - Si falta el hijo izquierdo: retorna `actual.derecho`
 *    - Si falta el hijo derecho: retorna `actual.izquierdo`
 * 
 *    Al retornar esa referencia a la llamada recursiva previa del nodo padre (`padre.izquierdo = ...` o `padre.derecho = ...`), 
 *    el abuelo pasa a apuntar directamente a su nieto. De esta manera, el nodo deseado queda "desconectado" 
 *    sin perder ninguna de las ramas ni violar las propiedades de orden del BST.
 * 
 * AJUSTES POSTERIORES Y RAZONES:
 * - Se integró la lógica previa de hojas para que el método maneje transparente y unificadamente 
 *   casos de 0 e 1 hijos.
 * - Se construyó una estructura de prueba que garantiza la presencia de nodos con un solo hijo 
 *   (tanto a la izquierda como a la derecha).
 * 
 * MODIFICACIONES / CORRECCIONES MANUALES REALIZADAS:
 * - Se agregaron mensajes de trazabilidad por consola para identificar cuándo se realiza la reconexión.
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

public class EliminarNodoUnHijoBST {
    private Nodo raiz;

    public EliminarNodoUnHijoBST() {
        this.raiz = null;
    }

    // Método para insertar elementos en el BST
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

    // Método público para eliminar un nodo (casos de 0 y 1 hijo)
    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    // Lógica recursiva de eliminación
    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        // Caso base: árbol vacío o valor no encontrado
        if (actual == null) {
            System.out.println("El valor " + valor + " no se encuentra en el árbol.");
            return null;
        }

        // Navegación en el BST
        if (valor < actual.valor) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = eliminarRecursivo(actual.derecho, valor);
        } else {
            // Nodo encontrado
            
            // CASO 1: Es una hoja (0 hijos)
            if (actual.izquierdo == null && actual.derecho == null) {
                System.out.println("-> Eliminando HOJA (" + valor + ").");
                return null;
            }

            // CASO 2A: Solo tiene hijo DERECHO (falta el izquierdo)
            if (actual.izquierdo == null) {
                System.out.println("-> Eliminando nodo con SOLO HIJO DERECHO (" + valor + "). Reconectando con su hijo " + actual.derecho.valor);
                return actual.derecho; // El padre adopta al hijo derecho
            }

            // CASO 2B: Solo tiene hijo IZQUIERDO (falta el derecho)
            if (actual.derecho == null) {
                System.out.println("-> Eliminando nodo con SOLO HIJO IZQUIERDO (" + valor + "). Reconectando con su hijo " + actual.izquierdo.valor);
                return actual.izquierdo; // El padre adopta al hijo izquierdo
            }

            // CASO 3: Tiene dos hijos (Se abordará formalmente en el Ejercicio 10)
            System.out.println("ADVERTENCIA: El nodo " + valor + " tiene DOS hijos. Este ejercicio solo aborda 0 y 1 hijo.");
        }

        return actual;
    }

    // Recorrido Inorden
    public void mostrarInorden() {
        System.out.print("Inorden: ");
        inordenRecursivo(raiz);
        System.out.println("FIN");
    }

    private void inordenRecursivo(Nodo nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + " -> ");
            inordenRecursivo(nodo.derecho);
        }
    }

    public static void main(String[] args) {
        EliminarNodoUnHijoBST bst = new EliminarNodoUnHijoBST();

        /*
         * Construcción de una estructura específica:
         *         50
         *       /    \
         *     30      70
         *    /  \       \
         *  20   40      80
         *  /              \
         * 10              90
         * 
         * Nodos con un solo hijo:
         * - Nodo 20: Tiene solo hijo izquierdo (10)
         * - Nodo 70: Tiene solo hijo derecho (80 -> 90)
         * - Nodo 80: Tiene solo hijo derecho (90)
         */
        int[] valores = {50, 30, 70, 20, 40, 80, 10, 90};
        for (int v : valores) {
            bst.insertar(v);
        }

        System.out.println("=== ESTADO INICIAL DEL ÁRBOL ===");
        bst.mostrarInorden(); // Esperado: 10 -> 20 -> 30 -> 40 -> 50 -> 70 -> 80 -> 90 -> FIN

        System.out.println("\n=== PRUEBA 1: ELIMINAR NODO CON SOLO HIJO IZQUIERDO (20) ===");
        bst.eliminar(20);
        bst.mostrarInorden(); // Esperado: 10 -> 30 -> 40 -> 50 -> 70 -> 80 -> 90 -> FIN

        System.out.println("\n=== PRUEBA 2: ELIMINAR NODO CON SOLO HIJO DERECHO (70) ===");
        bst.eliminar(70);
        bst.mostrarInorden(); // Esperado: 10 -> 30 -> 40 -> 50 -> 80 -> 90 -> FIN
    }
}
