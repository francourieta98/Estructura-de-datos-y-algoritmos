/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java dos métodos dentro de la clase de Árbol Binario de Búsqueda (BST): 'minimo()' y 'maximo()'.
 * Explica en los comentarios:
 * - Por qué en un BST el valor mínimo se encuentra avanzando siempre hacia la izquierda y el máximo hacia la derecha.
 * - Qué debe ocurrir o qué excepción/retorno debe manejarse si el árbol está vacío.
 * Muestra pruebas por consola obteniendo el menor y el mayor elemento de un BST."
 * 
 * POR QUÉ EL MÍNIMO ESTÁ A LA IZQUIERDA Y EL MÁXIMO A LA DERECHA EN UN BST:
 * Por definición de la propiedad fundamental de un Árbol Binario de Búsqueda (BST):
 * - Para todo nodo $N$, cualquier elemento almacenado en su subárbol izquierdo es menor que el valor de $N$.
 * - Para todo nodo $N$, cualquier elemento almacenado en su subárbol derecho es mayor que el valor de $N$.
 * 
 * Por lo tanto:
 * 1. Para hallar el valor MÍNIMO, basta con descender iterativa o recursivamente por el hijo izquierdo 
 *    hasta llegar al último nodo que no posea hijo izquierdo (`nodo.izquierdo == null`).
 * 2. Para hallar el valor MÁXIMO, se desciende por el hijo derecho hasta llegar al último nodo 
 *    que no posea hijo derecho (`nodo.derecho == null`).
 * 
 * MANEJO DE ÁRBOLES VACÍOS:
 * Si la raíz es `null`, no existe un valor mínimo ni máximo. En esta implementación se lanza una excepción 
 * `IllegalStateException("El árbol está vacío")` para notificar un estado inválido y evitar retornos confusos 
 * o errores de puntero nulo (`NullPointerException`).
 * 
 * AJUSTES POSTERIORES Y RAZONES:
 * - Se eligió una implementación iterativa (`while`) tanto para `minimo()` como para `maximo()` en lugar de 
 *   métodos recursivos. Esto ahorra espacio en la memoria stack y proporciona un tiempo de ejecución eficiente $O(h)$.
 * 
 * MODIFICACIONES / CORRECCIONES MANUALES REALIZADAS:
 * - Se envolvió la llamada a los métodos de un árbol vacío dentro de un bloque `try-catch` en el `main` para 
 *   demostrar cómo se controla la excepción sin que colapse el programa.
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

public class MinimoMaximoBST {
    private Nodo raiz;

    public MinimoMaximoBST() {
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

    /**
     * Obtiene el menor valor almacenado en el BST.
     * @return Valor entero mínimo.
     * @throws IllegalStateException si el árbol está vacío.
     */
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("El árbol está vacío, no se puede obtener el mínimo.");
        }

        Nodo actual = raiz;
        // Avanzar siempre hacia el nodo más a la izquierda
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }

        return actual.valor;
    }

    /**
     * Obtiene el mayor valor almacenado en el BST.
     * @return Valor entero máximo.
     * @throws IllegalStateException si el árbol está vacío.
     */
    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("El árbol está vacío, no se puede obtener el máximo.");
        }

        Nodo actual = raiz;
        // Avanzar siempre hacia el nodo más a la derecha
        while (actual.derecho != null) {
            actual = actual.derecho;
        }

        return actual.valor;
    }

    public static void main(String[] args) {
        MinimoMaximoBST bst = new MinimoMaximoBST();

        // 1. Carga de elementos en el BST
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 15, 95};
        System.out.println("=== INSERTANDO ELEMENTOS EN EL BST ===");
        System.out.print("Valores: ");
        for (int v : valores) {
            System.out.print(v + " ");
            bst.insertar(v);
        }
        System.out.println("\n");

        // 2. Obtención de Mínimo y Máximo
        System.out.println("=== OBTENCIÓN DE MÍNIMO Y MÁXIMO ===");
        System.out.println("Valor Mínimo (más a la izquierda): " + bst.minimo()); // Esperado: 15
        System.out.println("Valor Máximo (más a la derecha):   " + bst.maximo()); // Esperado: 95

        System.out.println("\n-------------------------------------\n");

        // 3. Prueba de manejo de excepción en árbol vacío
        MinimoMaximoBST arbolVacio = new MinimoMaximoBST();
        System.out.println("=== PRUEBA EN ÁRBOL VACÍO ===");
        try {
            arbolVacio.minimo();
        } catch (IllegalStateException e) {
            System.out.println("Excepción capturada correctamente: " + e.getMessage());
        }
    }
}
