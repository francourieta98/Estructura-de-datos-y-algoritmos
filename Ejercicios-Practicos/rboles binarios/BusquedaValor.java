/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un método que busque un número entero dentro de un árbol binario general.
 * El método debe devolver 'true' si el valor existe en el árbol y 'false' en caso contrario.
 * Deja en claro en la explicación que, como el árbol NO está ordenado (no es un BST), no se puede 
 * descartar automáticamente ninguna rama. Por lo tanto, la búsqueda debe revisar el nodo actual, 
 * luego el subárbol izquierdo y luego el subárbol derecho."
 * 
 * EXPLICACIÓN DE LA BÚSQUEDA EN UN ÁRBOL BINARIO GENERAL:
 * 1. IMPOSIBILIDAD DE DESCARTAR RAMAS:
 *    A diferencia de un Árbol Binario de Búsqueda (BST), donde el orden de los valores nos permite saber 
 *    si un elemento está a la izquierda o a la derecha, en un árbol binario general los valores están 
 *    distribuidos sin ninguna regla de orden. Por ello, no existe criterio para ignorar ninguna rama.
 * 
 * 2. ESTRATEGIA DE BÚSQUEDA (Evaluación en Cortocircuito):
 *    - Se verifica primero si el nodo actual (Raíz) contiene el valor buscado.
 *    - Si no coincide, se busca recursivamente en el subárbol izquierdo.
 *    - Solo si tampoco se encuentra en el subárbol izquierdo, se procede a buscar en el subárbol derecho.
 * 
 * 3. COMPLEJIDAD:
 *    En el peor de los casos (elemento inexistente o ubicado en la última hoja explorada), la complejidad 
 *    es O(N), ya que debe inspeccionar todos los nodos del árbol.
 * 
 * AJUSTES REALIZADOS Y JUSTIFICACIÓN:
 * - Se utilizó el operador lógico OR ('||') con evaluación perezosa/cortocircuito en la llamada recursiva. 
 *   Esto optimiza la ejecución deteniendo la búsqueda inmediatamente tan pronto como alguna rama devuelva 'true', 
 *   evitando recorridos innecesarios en el resto del árbol.
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

public class BusquedaValor {

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

        // Pruebas de búsqueda
        int[] valoresABuscar = {15, 7, 99, 10};

        System.out.println("=== BÚSQUEDA EN ÁRBOL BINARIO GENERAL ===");
        for (int valor : valoresABuscar) {
            boolean encontrado = buscar(raiz, valor);
            System.out.println("¿El valor " + valor + " existe en el árbol?: " + encontrado);
        }
    }

    /**
     * Método recursivo para buscar un número en un árbol binario general.
     * @param nodo Nodo actual visitado.
     * @param objetivo Valor buscado.
     * @return true si el valor está en el árbol, false en caso contrario.
     */
    public static boolean buscar(Nodo nodo, int objetivo) {
        // 1. Caso base: si llegamos a un nodo nulo, el valor no está en esta rama
        if (nodo == null) {
            return false;
        }

        // 2. Revisar el nodo actual (Raíz)
        if (nodo.valor == objetivo) {
            return true;
        }

        // 3 y 4. Buscar en el subárbol izquierdo y, si no está, en el subárbol derecho.
        // Gracias al operador '||', si 'buscar(nodo.izquierdo, objetivo)' devuelve true,
        // la llamada a 'buscar(nodo.derecho, objetivo)' ni siquiera se ejecuta (cortocircuito).
        return buscar(nodo.izquierdo, objetivo) || buscar(nodo.derecho, objetivo);
    }
}
