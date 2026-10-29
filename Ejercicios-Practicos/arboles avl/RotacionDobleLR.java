/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java la resolución del desbalanceo del Caso LR (Izquierda-Derecha) en un Árbol AVL. Explica en los comentarios:
 * - Por qué al insertar la secuencia [30, 10, 20] una sola rotación a la derecha sobre el nodo raíz no resuelve el problema.
 * - Por qué se requiere una rotación doble: primero una rotación simple a la izquierda sobre el hijo izquierdo (10) y luego una rotación simple a la derecha sobre la raíz desbalanceada (30).
 * - Cómo se transforman las alturas y las relaciones hijo-padre en cada una de las dos fases.
 * Muestra por consola la inserción de [30, 10, 20] paso a paso y comprueba el estado final rebalanceado."
 * 
 * EXPLICACIÓN TÉCNICA DEL CASO LR Y LA NECESIDAD DE ROTACIÓN DOBLE:
 * 1. POR QUÉ OCURRE EL CASO LR:
 *    - Se inserta el 30: Raíz.
 *    - Se inserta el 10: Va a la izquierda de 30 (30.izquierdo = 10).
 *    - Se inserta el 20: Va a la derecha de 10 (10.derecho = 20).
 * 
 *    Estructura previa al rebalanceo:
 *            30  (FB = -2) -> [DESBALANCE EN LA RAÍZ]
 *           /
 *          10    (FB = +1) -> [INCLINACIÓN HACIA LA DERECHA EN EL HIJO]
 *           \
 *            20  (FB = 0)
 * 
 * 2. POR QUÉ UNA SOLA ROTACIÓN NO ALCANZA:
 *    Si intentáramos aplicar una rotación simple a la derecha directamente sobre la raíz (30):
 *    - El nodo 10 subiría como nueva raíz, 30 pasaría a ser su hijo derecho y 20 se reasignaría como hijo izquierdo de 30.
 *    - El árbol resultante sería:
 *            10  (FB = +2)
 *              \
 *               30
 *              /
 *             20
 *    ¡El desbalance persiste (FB = +2 en el 10)! Esto ocurre porque la rama desbalanceada "hace un zig-zag" (forma de codo), 
 *    por lo que rotar en una sola dirección simplemente traslada la forma irregular sin alinear los nodos.
 * 
 * 3. SOLUCIÓN: ROTACIÓN DOBLE LR (Izquierda -> Derecha):
 *    - Paso 1: Rotación simple a la IZQUIERDA sobre el hijo izquierdo (10).
 *      Alinea la rama en forma recta (convierte el Caso LR en un Caso LL estándar).
 *            30  (FB = -2)
 *           /
 *          20    (FB = -1)
 *         /
 *        10
 * 
 *    - Paso 2: Rotación simple a la DERECHA sobre el nodo desbalanceado (30).
 *      Mueve el nodo mediano (20) a la cúspide como nueva raíz.
 *            20  (FB = 0)
 *           /  \
 *          10   30
 * 
 * AJUSTES REALIZADOS DESPUÉS DE LA PRIMERA RESPUESTA:
 * - Se reutilizaron los métodos 'rotacionIzquierda' y 'rotacionDerecha' implementados en los ejercicios 6 y 7, combinándolos en el bloque de decisión del caso LR.
 * 
 * MODIFICACIONES O CORRECCIONES MANUALES REALIZADAS:
 * - Se añadieron mensajes descriptivos por consola durante la ejecución para visualizar el paso intermedio (conversión de LR a LL) antes del balanceo definitivo.
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

public class RotacionDobleLR {
    private NodoAVL raiz;

    public RotacionDobleLR() {
        this.raiz = null;
    }

    private int obtenerAltura(NodoAVL nodo) {
        if (nodo == null) return 0;
        return nodo.altura;
    }

    private void actualizarAltura(NodoAVL nodo) {
        if (nodo != null) {
            nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        }
    }

    private int obtenerFactorEquilibrio(NodoAVL nodo) {
        if (nodo == null) return 0;
        return obtenerAltura(nodo.derecho) - obtenerAltura(nodo.izquierdo);
    }

    // Rotación Simple a la Izquierda
    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    // Rotación Simple a la Derecha
    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;

        x.derecho = y;
        y.izquierdo = T2;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    /**
     * Resuelve el Caso LR aplicando una Rotación Doble:
     * 1. Rotación Izquierda en el hijo izquierdo.
     * 2. Rotación Derecha en el nodo desbalanceado.
     */
    private NodoAVL rotacionIzquierdaDerecha(NodoAVL nodo) {
        System.out.println("  [Paso 1] Aplicando Rotación Izquierda sobre el hijo izquierdo: " + nodo.izquierdo.valor);
        nodo.izquierdo = rotacionIzquierda(nodo.izquierdo); // Convierte LR en LL

        System.out.println("  [Paso 2] Aplicando Rotación Derecha sobre la raíz desbalanceada: " + nodo.valor);
        return rotacionDerecha(nodo); // Resuelve el caso LL
    }

    public void insertar(int valor) {
        raiz = insertarYBalancear(raiz, valor);
    }

    private NodoAVL insertarYBalancear(NodoAVL nodo, int valor) {
        if (nodo == null) {
            return new NodoAVL(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = insertarYBalancear(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertarYBalancear(nodo.derecho, valor);
        } else {
            return nodo; // No duplicados
        }

        actualizarAltura(nodo);
        int balance = obtenerFactorEquilibrio(nodo);

        // DETECCIÓN DEL CASO LR (Izquierda-Derecha):
        // Desbalance por la izquierda (FB == -2) y el nuevo elemento entró por la derecha del hijo izquierdo (FB de hijo > 0)
        if (balance == -2 && obtenerFactorEquilibrio(nodo.izquierdo) > 0) {
            System.out.println("-> Desbalance detectado en nodo " + nodo.valor + " (FB = " + balance + "). Caso LR.");
            return rotacionIzquierdaDerecha(nodo);
        }

        return nodo;
    }

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
        RotacionDobleLR arbol = new RotacionDobleLR();

        System.out.println("=== PRUEBA DE CASO LR (ROTACIÓN DOBLE IZQUIERDA-DERECHA) ===");
        System.out.println("Insertando valores en secuencia: 30, 10, 20\n");

        System.out.println("1. Insertando 30...");
        arbol.insertar(30);

        System.out.println("2. Insertando 10...");
        arbol.insertar(10);

        System.out.println("3. Insertando 20...");
        arbol.insertar(20); // Se activa el desbalance LR y la rotación doble

        System.out.println("\n=== ESTADO FINAL DEL ÁRBOL REBALANCEADO ===");
        arbol.mostrarInordenConAlturas();
    }
}
