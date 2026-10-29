/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un método recursivo 'obtenerAltura' o 'altura' para calcular la altura de un Árbol AVL. 
 * Explica en los comentarios:
 * - Cuál es el caso base cuando el nodo es 'null'.
 * - Cómo se relacionan la altura del nodo actual con las alturas de sus subárboles izquierdo y derecho.
 * - Por qué se utiliza un método auxiliar seguro para evitar excepciones de puntero nulo (NullPointerException) 
 *   al consultar la altura de los hijos de un nodo.
 * Muestra en consola la altura del árbol en diferentes escenarios (árbol vacío, un solo nodo y subárboles de distintas profundidades)."
 * 
 * RAZONAMIENTO SOBRE EL CÁLCULO DE LA ALTURA EN UN ÁRBOL AVL:
 * 1. CASO BASE (Nodo NULL):
 *    Si el nodo es 'null' (nodo == null), no contiene ningún valor ni nivel. Por convención para el cálculo 
 *    de balance de un Árbol AVL donde las hojas comienzan con altura 1, un nodo nulo tiene una altura de 0.
 * 
 * 2. COMBINACIÓN DE ALTURAS (Paso Recursivo):
 *    La altura de un nodo existente en el árbol equivale a 1 (el nivel del nodo actual) más el máximo 
 *    entre la altura de su subárbol izquierdo y la altura de su subárbol derecho:
 *    $Altura(Nodo) = 1 + \max(Altura(Nodo.izquierdo), Altura(Nodo.derecho))$
 * 
 * 3. MANEJO SEGURO DE ATRIBUTOS (Método auxiliar 'obtenerAltura'):
 *    Al consultar 'nodo.izquierdo.altura', si 'nodo.izquierdo' es 'null', Java arrojaría un 'NullPointerException'. 
 *    Para prevenirlo, se abstrae la lectura en un método auxiliar que evalúa si el nodo es nulo antes de leer 
 *    su atributo '.altura'.
 * 
 * AJUSTES REALIZADOS DESPUÉS DE LA PRIMERA RESPUESTA:
 * - Se vinculó el cálculo con la clase 'NodoAVL' definida en el Ejercicio 1, manteniendo la propiedad de que 
 *   las hojas tienen altura 1.
 * - Se implementó tanto el método de lectura $O(1)$ desde el atributo interno del nodo como el método de 
 *   recalculo recursivo explícito para verificar que ambos coincidan.
 * 
 * MODIFICACIONES O CORRECCIONES MANUALES REALIZADAS:
 * - Se agregó el método 'actualizarAltura(NodoAVL nodo)' para que recalcule la altura basándose en sus hijos 
 *   y mantenga actualizada la estructura antes de que se realicen las rotaciones de balanceo en los siguientes ejercicios.
 */

class NodoAVL {
    public int valor;
    public int altura;
    public NodoAVL izquierdo;
    public NodoAVL derecho;

    public NodoAVL(int valor) {
        this.valor = valor;
        this.altura = 1;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class AlturaArbolAVL {

    /**
     * Método auxiliar seguro para obtener la altura de un nodo.
     * Retorna 0 si el nodo es null, previniendo NullPointerException.
     */
    public static int obtenerAltura(NodoAVL nodo) {
        if (nodo == null) {
            return 0; // Caso base: la altura de un nodo nulo es 0
        }
        return nodo.altura;
    }

    /**
     * Recalcula y actualiza la altura de un nodo dado en base a las alturas
     * de sus subárboles izquierdo y derecho.
     */
    public static void actualizarAltura(NodoAVL nodo) {
        if (nodo != null) {
            nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        }
    }

    /**
     * Método recursivo para calcular la altura total desde cualquier nodo.
     */
    public static int calcularAlturaRecursiva(NodoAVL nodo) {
        // Caso base
        if (nodo == null) {
            return 0;
        }
        // Combinación de alturas de los subárboles
        int altIzq = calcularAlturaRecursiva(nodo.izquierdo);
        int altDer = calcularAlturaRecursiva(nodo.derecho);

        return 1 + Math.max(altIzq, altDer);
    }

    public static void main(String[] args) {
        System.out.println("=== CÁLCULO DE ALTURA EN ÁRBOL AVL ===");

        // Escenario 1: Árbol Vacío
        NodoAVL raizVacia = null;
        System.out.println("1. Altura de árbol vacío (null): " + obtenerAltura(raizVacia));

        // Escenario 2: Nodo único (Raíz)
        NodoAVL raizUnica = new NodoAVL(10);
        System.out.println("2. Altura de un solo nodo: " + obtenerAltura(raizUnica));

        // Escenario 3: Árbol con varias alturas
        //       10          -> Altura 3
        //      /  \
        //     5    15       -> Altura 2
        //    /
        //   2               -> Altura 1
        NodoAVL raiz = new NodoAVL(10);
        raiz.izquierdo = new NodoAVL(5);
        raiz.derecho = new NodoAVL(15);
        raiz.izquierdo.izquierdo = new NodoAVL(2);

        // Actualizar alturas desde las hojas hacia arriba
        actualizarAltura(raiz.izquierdo.izquierdo);
        actualizarAltura(raiz.izquierdo);
        actualizarAltura(raiz.derecho);
        actualizarAltura(raiz);

        System.out.println("3. Altura de la raíz (método O(1) vía atributo): " + obtenerAltura(raiz));
        System.out.println("4. Altura de la raíz (método recursivo): " + calcularAlturaRecursiva(raiz));
        System.out.println("5. Altura del subárbol derecho: " + obtenerAltura(raiz.derecho));
        System.out.println("6. Altura del subárbol izquierdo: " + obtenerAltura(raiz.izquierdo));
    }
}
