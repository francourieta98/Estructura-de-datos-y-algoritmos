/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un método recursivo para contar cuántas hojas tiene un árbol binario.
 * Una hoja es un nodo que no tiene hijo izquierdo ni hijo derecho. 
 * Explica en los comentarios:
 * - Qué ocurre si el nodo es null.
 * - Cómo se detecta una hoja.
 * - Qué se hace si el nodo no es una hoja (tiene al menos un hijo).
 * - Por qué se deben sumar los resultados del subárbol izquierdo y del subárbol derecho."
 * 
 * EXPLICACIÓN REQUERIDA SOBRE EL CONTEO DE HOJAS:
 * 1. QUÉ OCURRE SI EL NODO ES NULL:
 *    Si el nodo es 'null' (nodo == null), significa que la rama está vacía, por lo que devuelve 0 
 *    ya que un nodo inexistente no puede ser una hoja.
 * 
 * 2. CÓMO SE DETECTA UNA HOJA:
 *    Un nodo se identifica como hoja cuando cumple la condición:
 *    (nodo.izquierdo == null && nodo.derecho == null).
 *    Si se cumple esta condición, el método devuelve 1, pues hemos encontrado una hoja.
 * 
 * 3. QUÉ SE HACE SI EL NODO TIENE HIJOS:
 *    Si el nodo no es nulo pero tampoco es una hoja (es un nodo interno/padre), ese nodo aporta 0 
 *    al conteo de hojas directamente, pero debe delegar el trabajo a sus descendientes invocando 
 *    recursivamente al método en su hijo izquierdo y en su hijo derecho.
 * 
 * 4. POR QUÉ SE SUMAN LOS RESULTADOS DE AMBOS SUBÁRBOLES:
 *    Las hojas pueden estar distribuidas tanto en la rama izquierda como en la rama derecha. 
 *    Para obtener el total global de hojas de la estructura, es indispensable sumar las hojas 
 *    encontradas en el subárbol izquierdo con las halladas en el subárbol derecho:
 *    Total de Hojas = contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho).
 * 
 * AJUSTES REALIZADOS Y JUSTIFICACIÓN:
 * - Se utilizó el mismo árbol de los ejercicios previos (hojas: 3, 7, 15, 30) para verificar 
 *   fácilmente que el resultado dé exactamente 4 hojas.
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

public class ContarHojas {

    public static void main(String[] args) {
        // Construcción del árbol binario de ejemplo:
        //       10
        //      /  \
        //     5    20
        //    / \   / \
        //   3   7 15 30    -> Hojas: 3, 7, 15, 30 (Total: 4)
        Nodo raiz = new Nodo(10);
        raiz.izquierdo = new Nodo(5);
        raiz.derecho = new Nodo(20);

        raiz.izquierdo.izquierdo = new Nodo(3);
        raiz.izquierdo.derecho = new Nodo(7);

        raiz.derecho.izquierdo = new Nodo(15);
        raiz.derecho.derecho = new Nodo(30);

        // Calcular el número total de hojas
        int totalHojas = contarHojas(raiz);

        System.out.println("=== CONTEO DE HOJAS EN ÁRBOL BINARIO ===");
        System.out.println("Cantidad total de hojas en el árbol: " + totalHojas);
    }

    /**
     * Método recursivo para contar las hojas de un árbol binario.
     * @param nodo Nodo actual visitado.
     * @return Cantidad de hojas en el subárbol que tiene como raíz a 'nodo'.
     */
    public static int contarHojas(Nodo nodo) {
        // 1. Si el nodo es null, no hay hojas
        if (nodo == null) {
            return 0;
        }

        // 2. Detección de una hoja (sin hijo izquierdo ni derecho)
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }

        // 3 y 4. Si es un nodo interno, sumamos las hojas del subárbol izquierdo y del derecho
        return contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho);
    }
}
