/*
 * PROMPT INICIAL UTILIZADO:
 * "Agrega a la clase ArbolBinarioBusqueda un método 'altura()' que devuelva la altura de un BST.
 * Explica en los comentarios:
 * - El razonamiento del caso base (cuándo el nodo es null).
 * - Por qué la altura se calcula tomando el máximo entre la altura del subárbol izquierdo y del derecho más 1.
 * Muestra pruebas comparativas entre un árbol balanceado y un árbol degenerado (lineal/desbalanceado)."
 * 
 * RAZONAMIENTO DE LA ALTURA EN UN BST:
 * 1. CASO BASE:
 *    Si el nodo es 'null' (nodo == null), el subárbol está vacío, por lo que su altura es 0. 
 *    Este es el punto de parada para la recursión al llegar más allá de los nodos hoja.
 * 
 * 2. CÁLCULO DEL MÁXIMO ENTRE SUBÁRBOLES:
 *    La altura de un nodo representa la longitud del camino más largo desde ese nodo hasta la hoja más lejana. 
 *    Dado que un nodo puede tener dos ramas (izquierda y derecha), la distancia máxima posible hacia abajo 
 *    estará determinada exclusivamente por la rama que tenga mayor profundidad. Por ello se toma el valor 
 *    máximo mediante 'Math.max(altIzq, altDer)' y se le suma 1 para incluir el propio nivel del nodo actual.
 * 
 * 3. ÁRBOL BALANCEADO VS. ÁRBOL DEGENERADO:
 *    - Árbol Balanceado: Al insertar elementos distribuidos (ej: 50, 30, 70...), la altura se mantiene cercana a O(log N).
 *    - Árbol Degenerado: Al insertar elementos ya ordenados (ej: 10, 20, 30, 40...), el árbol se comporta como 
 *      una lista enlazada simple y la altura se degrada a O(N).
 * 
 * AJUSTES POSTERIORES Y RAZONES:
 * - Se creó un método auxiliar público 'obtenerAltura()' para no exponer la raíz ni requerir que el usuario pase 
 *   parámetros desde el método 'main'.
 * 
 * MODIFICACIONES / CORRECCIONES MANUALES REALIZADAS:
 * - Se implementó una prueba doble en la función principal para demostrar explícitamente cómo la secuencia 
 *   de inserción impacta de forma drástica en la altura final del BST.
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

public class AlturaBST {
    private Nodo raiz;

    public AlturaBST() {
        this.raiz = null;
    }

    // Inserción en el BST
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

    // Método público para obtener la altura
    public int altura() {
        return calcularAltura(raiz);
    }

    // Método privado recursivo para calcular la altura
    private int calcularAltura(Nodo nodo) {
        // Caso base: un árbol/subárbol vacío tiene altura 0
        if (nodo == null) {
            return 0;
        }

        // Calcular la altura de cada subárbol
        int altIzquierda = calcularAltura(nodo.izquierdo);
        int altDerecha = calcularAltura(nodo.derecho);

        // Sumar 1 al máximo entre el subárbol izquierdo y el derecho
        return 1 + Math.max(altIzquierda, altDerecha);
    }

    public static void main(String[] args) {
        // 1. Prueba con un Árbol Balanceado
        AlturaBST bstBalanceado = new AlturaBST();
        int[] valoresBalanceados = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valoresBalanceados) {
            bstBalanceado.insertar(v);
        }

        // 2. Prueba con un Árbol Degenerado (insertando elementos en orden ascendente)
        AlturaBST bstDegenerado = new AlturaBST();
        int[] valoresOrdenados = {10, 20, 30, 40, 50, 60, 70};
        for (int v : valoresOrdenados) {
            bstDegenerado.insertar(v);
        }

        System.out.println("=== PRUEBA DE ALTURA EN BST ===");
        System.out.println("1. Árbol Balanceado (7 nodos):");
        System.out.println("   Valores: [50, 30, 70, 20, 40, 60, 80]");
        System.out.println("   Altura obtenida: " + bstBalanceado.altura()); // Esperado: 3

        System.out.println("\n2. Árbol Degenerado / Lista Lineal (7 nodos):");
        System.out.println("   Valores: [10, 20, 30, 40, 50, 60, 70]");
        System.out.println("   Altura obtenida: " + bstDegenerado.altura()); // Esperado: 7
    }
}
