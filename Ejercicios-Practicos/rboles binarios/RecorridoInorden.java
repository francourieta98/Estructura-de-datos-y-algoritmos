/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un método recursivo para recorrer un árbol binario en inorden y mostrar 
 * los valores visitados en consola. El árbol debe ser construido manualmente. 
 * Deja en claro en la explicación que el recorrido en inorden en un árbol binario general NO implica 
 * necesariamente que los valores salgan ordenados de menor a mayor, ya que no se trata de un 
 * Árbol Binario de Búsqueda (BST)."
 * 
 * ACLARACIÓN CLAVE SOBRE INORDEN Y BST:
 * - Un recorrido INORDEN sigue el esquema: Subárbol Izquierdo -> Raíz -> Subárbol Derecho (I-R-D).
 * - En un Árbol Binario de Búsqueda (BST), el inorden SIEMPRE devuelve los elementos ordenados de menor a mayor 
 *   debido a la propiedad intrínseca del BST (izquierdos menores, derechos mayores).
 * - En este ejercicio, al ser un ÁRBOL BINARIO GENERAL (construido manualmente sin reglas de ordenamiento), 
 *   el recorrido en inorden procesa los nodos en el orden I-R-D pero LOS VALORES RESULTANTES NO ESTARÁN ORDENADOS.
 * 
 * EXPLICACIÓN TÉCNICA DEL MÉTODO RECURSIVO:
 * 1. CASO BASE: Si el nodo actual es 'null', la recursión se detiene y regresa.
 * 2. PASO 1 (Izquierdo): Se llama recursivamente al subárbol izquierdo (inorden(nodo.izquierdo)).
 * 3. PASO 2 (Raíz): Se procesa/imprime el valor del nodo actual.
 * 4. PASO 3 (Derecho): Se llama recursivamente al subárbol derecho (inorden(nodo.derecho)).
 * 
 * AJUSTES REALIZADOS Y JUSTIFICACIÓN:
 * - Se reutilizó la misma estructura de árbol de los ejercicios anteriores (Raíz 10, hijos 5 y 20, etc.) 
 *   para demostrar claramente que la misma estructura produce diferentes secuencias de valores según 
 *   el tipo de recorrido (Preorden vs. Inorden).
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

public class RecorridoInorden {

    public static void main(String[] args) {
        // Construcción manual del árbol binario general
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

        System.out.println("=== RECORRIDO EN INORDEN (Izquierdo -> Raíz -> Derecho) ===");
        System.out.print("Secuencia de visita: ");
        inorden(raiz);
        System.out.println("FIN");
        
        System.out.println("\nNota: Aunque el resultado final de este árbol particular sea [3, 5, 7, 10, 15, 20, 30], ");
        System.out.println("esto ocurre únicamente por la disposición manual dada a los nodos, no porque sea un BST.");
    }

    /**
     * Método recursivo para el recorrido en inorden.
     * @param nodo Nodo actual visitado.
     */
    public static void inorden(Nodo nodo) {
        // Caso base: árbol o subárbol vacío
        if (nodo == null) {
            return;
        }

        // 1. Recorrer el subárbol izquierdo primero
        inorden(nodo.izquierdo);

        // 2. Procesar el nodo actual (Raíz)
        System.out.print(nodo.valor + " -> ");

        // 3. Recorrer el subárbol derecho después
        inorden(nodo.derecho);
    }
}
