/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java la resolución del desbalanceo del Caso RL (Derecha-Izquierda) en un Árbol AVL. Explica en los comentarios:
 * - Por qué al insertar la secuencia [10, 30, 20] el desbalance de la raíz está orientada hacia la derecha (FB = +2), pero el subárbol hijo se carga hacia la izquierda (FB = -1).
 * - Por qué una sola rotación a la izquierda no resuelve el problema y por qué se requiere una rotación doble: primero una rotación simple a la derecha sobre el hijo derecho (30) y luego una rotación simple a la izquierda sobre el nodo desbalanceado (10).
 * - Cómo se transforman las alturas y la estructura en cada fase.
 * Muestra por consola la inserción de [10, 30, 20] paso a paso y comprueba el estado final rebalanceado."
 * 
 * EXPLICACIÓN TÉCNICA DEL CASO RL Y LA ROTACIÓN DOBLE DERECHA-IZQUIERDA:
 * 1. POR QUÉ OCURRE EL CASO RL:
 *    - Se inserta el 10: Es la raíz.
 *    - Se inserta el 30: Va a la derecha de 10 (10.derecho = 30).
 *    - Se inserta el 20: Va a la izquierda de 30 (30.izquierdo = 20).
 * 
 *    Estructura previa al rebalanceo:
 *            10  (FB = +2) -> [DESBALANCE POR LA DERECHA EN LA RAÍZ]
 *              \
 *               30  (FB = -1) -> [INCLINACIÓN HACIA LA IZQUIERDA EN EL HIJO]
 *              /
 *             20  (FB = 0)
 * 
 *    El nodo 10 tiene un Factor de Balance de $+2$ (subárbol derecho 2 niveles más alto que el izquierdo). 
 *    Sin embargo, su hijo derecho (30) tiene un Factor de Balance de $-1$ porque su propio nuevo hijo (20) se insertó a la **izquierda**.
 *    Esta combinación de direcciones (derecha en la raíz, izquierda en el hijo) forma un "zig-zag" o codo.
 * 
 * 2. POR QUÉ SE REQUIERE UNA ROTACIÓN DOBLE (Derecha -> Izquierda):
 *    Una sola rotación a la izquierda sobre la raíz no balancea la estructura porque los nodos no están alineados en línea recta.
 *    - Paso 1: Rotación simple a la DERECHA sobre el hijo derecho (30).
 *      Alinea la rama a la derecha (convierte el Caso RL en un Caso RR estándar).
 *            10  (FB = +2)
 *              \
 *               20  (FB = +1)
 *                 \
 *                  30
 * 
 *    - Paso 2: Rotación simple a la IZQUIERDA sobre la raíz desbalanceada (10).
 *      Sube el nodo central (20) a la cúspide como nueva raíz del subárbol.
 *            20  (FB = 0)
 *           /  \
 *          10   30
 * 
 * AJUSTES REALIZADOS DESPUÉS DE LA PRIMERA RESPUESTA:
 * - Se modularizó la lógica reutilizando las funciones de rotación simple para formar el método `rotacionDerechaIzquierda(NodoAVL nodo)`.
 * 
 * MODIFICACIONES O CORRECCIONES MANUALES REALIZADAS:
 * - Se incluyeron mensajes explicativos en la consola durante el proceso de inserción para mostrar ambas fases del rebalanceo doble.
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

public class RotacionDobleRL {
    private NodoAVL raiz;

    public RotacionDobleRL() {
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

    /**
     * Resuelve el Caso RL aplicando una Rotación Doble:
     * 1. Rotación Derecha sobre el hijo derecho.
     * 2. Rotación Izquierda sobre el nodo desbalanceado.
     */
    private NodoAVL rotacionDerechaIzquierda(NodoAVL nodo) {
        System.out.println("  [Paso 1] Aplicando Rotación Derecha sobre el hijo derecho: " + nodo.derecho.valor);
        nodo.derecho = rotacionDerecha(nodo.derecho); // Convierte RL en RR

        System.out.println("  [Paso 2] Aplicando Rotación Izquierda sobre la raíz desbalanceada: " + nodo.valor);
        return rotacionIzquierda(nodo); // Resuelve el caso RR
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

        // DETECCIÓN DEL CASO RL (Derecha-Izquierda):
        // Desbalance por la derecha (FB == +2) y el nuevo elemento entró por la izquierda del hijo derecho (FB de hijo < 0)
        if (balance == 2 && obtenerFactorEquilibrio(nodo.derecho) < 0) {
            System.out.println("-> Desbalance detectado en nodo " + nodo.valor + " (FB = +" + balance + "). Caso RL.");
            return rotacionDerechaIzquierda(nodo);
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
        RotacionDobleRL arbol = new RotacionDobleRL();

        System.out.println("=== PRUEBA DE CASO RL (ROTACIÓN DOBLE DERECHA-IZQUIERDA) ===");
        System.out.println("Insertando valores en secuencia: 10, 30, 20\n");

        System.out.println("1. Insertando 10...");
        arbol.insertar(10);

        System.out.println("2. Insertando 30...");
        arbol.insertar(30);

        System.out.println("3. Insertando 20...");
        arbol.insertar(20); // Se activa el desbalance RL y la rotación doble

        System.out.println("\n=== ESTADO FINAL DEL ÁRBOL REBALANCEADO ===");
        arbol.mostrarInordenConAlturas();
    }
}
