/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java la función de inserción recursiva para una clase 'ArbolAVL' actuando en esta fase 
 * únicamente como un Árbol Binario de Búsqueda (ABB) estándar, sin aplicar aún rotaciones de auto-balanceo. 
 * Explica en los comentarios:
 * - El criterio de decisión para dirigir la inserción hacia el subárbol izquierdo o derecho.
 * - Cómo se manejan los valores duplicados o repetidos para mantener la coherencia en la estructura.
 * - Por qué es imprescindible recalcular la altura del nodo durante el retorno de la pila recursiva.
 * Muestra por consola la inserción de varios valores y verifica el estado de las alturas de los nodos."
 * 
 * CRITERIO DE DECISIÓN Y REGLAS DE NAVEGACIÓN EN EL ABB:
 * 1. DECISIÓN DE RAMAS:
 *    - Si el valor a insertar es estrictamente MENOR que el valor del nodo actual ($valor < actual.valor$), 
 *      la inserción continúa recursivamente en el subárbol izquierdo (`actual.izquierdo`).
 *    - Si el valor a insertar es estrictamente MAYOR que el valor del nodo actual ($valor > actual.valor$), 
 *      la inserción continúa recursivamente en el subárbol derecho (`actual.derecho`).
 * 
 * 2. MANEJO DE VALORES DUPLICADOS:
 *    - En esta implementación estándar de Árbol AVL / ABB, los valores repetidos o duplicados son ignorados. 
 *      Si $valor == actual.valor$, el método retorna el nodo actual sin modificar la estructura, evitando 
 *      duplicados que alterarían las propiedades de búsqueda eficiente.
 * 
 * 3. RECALCULO DE LA ALTURA EN EL RETORNO RECURSIVO:
 *    A medida que la recursión desciende, se localiza el punto de inserción (donde $actual == null$) y se crea 
 *    el nuevo `NodoAVL` con altura 1. Al retornar de la recursión hacia la raíz, cada nodo ancestro reasigna 
 *    sus punteros (`actual.izquierdo = ...` / `actual.derecho = ...`) y recalcula inmediatamente su atributo 
 *    `altura` mediante `1 + Math.max(altura(izq), altura(der))`.
 * 
 * AJUSTES REALIZADOS DESPUÉS DE LA PRIMERA RESPUESTA:
 * - Se encapsuló la llamada pública `insertar(int valor)` delegando a la función privada recursiva `insertarABB(NodoAVL actual, int valor)`.
 * 
 * MODIFICACIONES O CORRECCIONES MANUALES REALIZADAS:
 * - Se incorporó un recorrido inorden y una impresión por niveles que muestra no solo los valores almacenados 
 *   sino también las alturas calculadas en cada nodo antes de pasar al auto-balanceo de los siguientes ejercicios.
 */

class NodoAVL {
    public int valor;
    public int altura;
    public NodoAVL izquierdo;
    public NodoAVL derecho;

    public NodoAVL(int valor) {
        this.valor = valor;
        this.altura = 1; // Todo nodo nuevo (hoja) inicia con altura 1
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class InsertarABBEnAVL {
    private NodoAVL raiz;

    public InsertarABBEnAVL() {
        this.raiz = null;
    }

    /**
     * Obtiene la altura de forma segura previniendo NullPointerException.
     */
    private int obtenerAltura(NodoAVL nodo) {
        if (nodo == null) {
            return 0; // Caso base: altura de un subárbol vacío es 0
        }
        return nodo.altura;
    }

    /**
     * Recalcula la altura de un nodo a partir de las alturas de sus hijos.
     */
    private void actualizarAltura(NodoAVL nodo) {
        if (nodo != null) {
            nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        }
    }

    /**
     * Método público para insertar un nuevo valor en el árbol.
     */
    public void insertar(int valor) {
        raiz = insertarABB(raiz, valor);
    }

    /**
     * Inserción recursiva al estilo ABB (sin rebalanceo todavía).
     */
    private NodoAVL insertarABB(NodoAVL actual, int valor) {
        // Caso base 1: Se encontró la posición de inserción
        if (actual == null) {
            System.out.println("-> Insertando nuevo nodo con valor: " + valor);
            return new NodoAVL(valor);
        }

        // Navegación en el árbol según la propiedad de ABB
        if (valor < actual.valor) {
            actual.izquierdo = insertarABB(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarABB(actual.derecho, valor);
        } else {
            // Caso base 2: El valor ya existe (duplicado), se ignora
            System.out.println("-> Valor " + valor + " duplicado. No se realizan cambios.");
            return actual;
        }

        // Actualización de la altura del nodo actual tras la inserción en uno de sus subárboles
        actualizarAltura(actual);

        return actual;
    }

    /**
     * Muestra el recorrido Inorden junto con la altura almacenada en cada nodo.
     */
    public void mostrarInordenConAlturas() {
        System.out.print("Inorden [Valor (Altura)]: ");
        inordenRecursivo(raiz);
        System.out.println("FIN");
    }

    private void inordenRecursivo(NodoAVL nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + " (H=" + nodo.altura + ") -> ");
            inordenRecursivo(nodo.derecho);
        }
    }

    public static void main(String[] args) {
        InsertarABBEnAVL arbol = new InsertarABBEnAVL();

        System.out.println("=== PRUEBA DE INSERCIÓN BÁSICA ABB (PRE-AVL) ===");
        
        // Inserción de una secuencia de valores
        int[] valores = {50, 30, 70, 20, 40, 50}; // Incluye el 50 como duplicado
        for (int v : valores) {
            arbol.insertar(v);
        }

        System.out.println("\n=== VERIFICACIÓN DE LA ESTRUCTURA Y ALTURAS ===");
        arbol.mostrarInordenConAlturas();
    }
}
