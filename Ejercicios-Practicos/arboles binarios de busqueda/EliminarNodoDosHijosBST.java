/*
 * PROMPT INICIAL UTILIZADO:
 * "Completa el algoritmo de eliminación en un Árbol Binario de Búsqueda (BST) en Java contemplando el caso 
 * más complejo: eliminar un nodo que posee dos hijos (izquierdo y derecho). 
 * Explica en los comentarios:
 * - Qué es el sucesor inorden.
 * - Por qué se puede utilizar el valor mínimo del subárbol derecho como reemplazo.
 * - Cómo se completa la eliminación borrando el nodo duplicado en el subárbol derecho.
 * Muestra pruebas en consola eliminando la raíz y nodos internos con dos hijos, verificando que el recorrido 
 * inorden mantenga la secuencia perfectamente ordenada."
 * 
 * LÓGICA DE ELIMINACIÓN DE UN NODO CON DOS HIJOS EN BST:
 * 1. QUÉ ES EL SUCESOR INORDEN:
 *    El sucesor inorden de un nodo $N$ es el elemento que contiene el valor inmediatamente MAYOR a $N$ 
 *    en todo el árbol (es decir, el siguiente elemento en la secuencia ordenada si hiciéramos un recorrido inorden).
 * 
 * 2. POR QUÉ SE USA EL MÍNIMO DEL SUBÁRBOL DERECHO:
 *    Para eliminar un nodo con dos hijos sin deshacer la estructura completa del árbol ni desconectar sus subárboles:
 *    a) Se busca el valor mínimo dentro de su subárbol derecho (que es exactamente el sucesor inorden).
 *    b) Este valor es ideal porque cumple dos condiciones estrictas:
 *       - Es mayor que todos los nodos del subárbol izquierdo de $N$.
 *       - Es menor que todos los demás nodos restantes del subárbol derecho de $N$.
 *    c) Se reemplaza el valor del nodo $N$ por el valor de su sucesor inorden.
 * 
 * 3. ELIMINACIÓN DEL DUPLICADO:
 *    Una vez copiado el valor del sucesor inorden al nodo actual, el valor queda duplicado temporalmente. 
 *    Por ello, se realiza una llamada recursiva sobre el subárbol derecho para eliminar el nodo original 
 *    del sucesor inorden (`actual.derecho = eliminarRecursivo(actual.derecho, sucesor.valor)`). 
 *    Dado que el sucesor es el mínimo de la derecha, se garantiza que tiene como máximo 1 hijo (el derecho), 
 *    por lo que su borrado cae en un caso simple (0 o 1 hijo).
 * 
 * AJUSTES POSTERIORES Y RAZONES:
 * - Se creó un método auxiliar privado 'encontrarMinimo(Nodo nodo)' para obtener rápidamente el nodo sucesor.
 * - Se integraron los tres casos de eliminación (0 hijos, 1 hijo y 2 hijos) en un único método recursivo 
 *   robusto y unificado.
 * 
 * MODIFICACIONES / CORRECCIONES MANUALES REALIZADAS:
 * - Se agregaron impresiones por consola en cada etapa para visualizar cómo se encuentra el sucesor inorden 
 *   y cómo el recorrido inorden final ratifica el mantenimiento del orden del BST.
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

public class EliminarNodoDosHijosBST {
    private Nodo raiz;

    public EliminarNodoDosHijosBST() {
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

    // Método público para eliminar cualquier nodo por su valor
    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    // Lógica completa y unificada de eliminación en un BST
    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        // Caso base: árbol vacío o el valor no existe
        if (actual == null) {
            System.out.println("El valor " + valor + " no existe en el árbol.");
            return null;
        }

        // Navegación para localizar el nodo a eliminar
        if (valor < actual.valor) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = eliminarRecursivo(actual.derecho, valor);
        } else {
            // ¡Nodo encontrado!

            // CASO 1: Nodo con 0 hijos (Hoja)
            if (actual.izquierdo == null && actual.derecho == null) {
                System.out.println("-> [Caso 1] Eliminando HOJA (" + valor + ")");
                return null;
            }

            // CASO 2A: Nodo con 1 hijo (Solo hijo derecho)
            if (actual.izquierdo == null) {
                System.out.println("-> [Caso 2] Eliminando nodo con solo HIJO DERECHO (" + valor + ")");
                return actual.derecho;
            }

            // CASO 2B: Nodo con 1 hijo (Solo hijo izquierdo)
            if (actual.derecho == null) {
                System.out.println("-> [Caso 2] Eliminando nodo con solo HIJO IZQUIERDO (" + valor + ")");
                return actual.izquierdo;
            }

            // CASO 3: Nodo con DOS HIJOS
            // 1. Obtener el sucesor inorden (mínimo del subárbol derecho)
            Nodo sucesorInorden = encontrarMinimo(actual.derecho);
            System.out.println("-> [Caso 3] Eliminando nodo con DOS HIJOS (" + valor + "). Sucesor inorden encontrado: " + sucesorInorden.valor);

            // 2. Reemplazar el valor del nodo actual por el del sucesor inorden
            actual.valor = sucesorInorden.valor;

            // 3. Eliminar el nodo sucesor duplicado de su ubicación original en el subárbol derecho
            actual.derecho = eliminarRecursivo(actual.derecho, sucesorInorden.valor);
        }

        return actual;
    }

    /**
     * Método auxiliar para obtener el nodo con el valor mínimo a partir de un nodo dado.
     */
    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
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
        EliminarNodoDosHijosBST bst = new EliminarNodoDosHijosBST();

        // Carga de elementos en el BST
        // Estructura creada:
        //        50 (Raíz)
        //      /    \
        //    30      70
        //   /  \    /  \
        // 20   40  60  80
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores) {
            bst.insertar(v);
        }

        System.out.println("=== ESTADO INICIAL DEL ÁRBOL ===");
        bst.mostrarInorden(); // Esperado: 20 -> 30 -> 40 -> 50 -> 60 -> 70 -> 80 -> FIN

        System.out.println("\n=== PRUEBA 1: ELIMINAR NODO INTERNO CON DOS HIJOS (30) ===");
        // Nodos hijos de 30: 20 y 40. Sucesor inorden (mínimo de subárbol derecho de 30): 40
        bst.eliminar(30);
        bst.mostrarInorden(); // Esperado: 20 -> 40 -> 50 -> 60 -> 70 -> 80 -> FIN

        System.out.println("\n=== PRUEBA 2: ELIMINAR LA RAÍZ CON DOS HIJOS (50) ===");
        // Nodos hijos de 50: 30(ahora modificado) y 70. Sucesor inorden: 60
        bst.eliminar(50);
        bst.mostrarInorden(); // Esperado: 20 -> 40 -> 60 -> 70 -> 80 -> FIN
    }
}
