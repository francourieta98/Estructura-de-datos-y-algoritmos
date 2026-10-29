/*
 * PROMPT INICIAL UTILIZADO:
 * "Crea en Java un método 'esAVL()' que verifique si una estructura de árbol binario cumple estrictamente 
 * las propiedades de un Árbol AVL. Explica en los comentarios:
 * - Por qué NO basta con revisar únicamente la raíz del árbol para confirmar que es un Árbol AVL.
 * - Cómo el algoritmo debe validar recursivamente dos condiciones esenciales en CADA NODO del árbol:
 *   1. Que cumpla la propiedad de ordenamiento del Árbol Binario de Búsqueda (ABB).
 *   2. Que su factor de equilibrio se mantenga dentro del rango [-1, 0, 1].
 * Muestra pruebas por consola evaluando árboles totalmente balanceados, árboles desbalanceados en la raíz, 
 * y árboles donde el desbalance ocurre únicamente en un subárbol profundo."
 * 
 * POR QUÉ NO BASTA CON REVISAR ÚNICAMENTE LA RAÍZ:
 * En un árbol binario, la raíz puede tener una diferencia de altura entre sus subárboles izquierdo y derecho 
 * menor o igual a 1 (por ejemplo, FB = 0), aparentando estar perfectamente equilibrada. Sin embargo, más abajo 
 * en la estructura (en algún subárbol interno o profundo), la diferencia de altura entre las ramas de un hijo 
 * o nieto puede ser de +2 o -2, violando la propiedad AVL.
 * 
 * Para que un árbol sea considerado un Árbol AVL válido, TODOS y cada uno de sus nodos deben ser balanceados 
 * ($|FB| \le 1$) y responder a las reglas de un Árbol Binario de Búsqueda (ABB).
 * 
 * CONDICIONES VALIDADAS EN LA VERIFICACIÓN RECURSIVA:
 * 1. Para el nodo actual:
 *    - Se calcula el factor de equilibrio: $|alturaDerecha - alturaIzquierda| \le 1$.
 *    - Se verifica la propiedad ABB: los valores de la izquierda son estrictamente menores y los de la derecha mayores.
 * 2. Para los subárboles:
 *    - Se ejecuta la llamada recursiva para garantizar que `esAVL(nodo.izquierdo)` y `esAVL(nodo.derecho)` 
 *      también retornen `true`.
 * 
 * AJUSTES REALIZADOS DESPUÉS DE LA PRIMERA RESPUESTA:
 * - Se incorporaron límites dinámicos (mínimo y máximo) en la función recursiva para validar simultáneamente 
 *   que se cumpla la propiedad de Árbol Binario de Búsqueda (ABB) además del factor de equilibrio.
 * 
 * MODIFICACIONES O CORRECCIONES MANUALES REALIZADAS:
 * - Se diseñó un caso de prueba donde la raíz tiene un factor de equilibrio de 0 pero un subárbol interno 
 *   está desbalanceado, demostrando la necesidad de la verificación recursiva profunda.
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

public class ValidarArbolAVL {

    /**
     * Obtiene la altura de forma segura previendo nodos nulos.
     */
    public static int obtenerAltura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    /**
     * Recalcula la altura del nodo según sus subárboles.
     */
    public static void actualizarAltura(NodoAVL nodo) {
        if (nodo != null) {
            nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        }
    }

    /**
     * Calcula el factor de equilibrio (FB) = AlturaDerecha - AlturaIzquierda.
     */
    public static int obtenerFactorEquilibrio(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return obtenerAltura(nodo.derecho) - obtenerAltura(nodo.izquierdo);
    }

    /**
     * Método público para verificar si el árbol completo es AVL.
     */
    public static boolean esAVL(NodoAVL raiz) {
        return esAVLRecursivo(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Método privado recursivo que valida tanto la propiedad ABB como el equilibrio AVL en cada nodo.
     */
    private static boolean esAVLRecursivo(NodoAVL nodo, long min, long max) {
        // Un árbol o subárbol vacío es AVL por definición
        if (nodo == null) {
            return true;
        }

        // 1. Validar la propiedad de Árbol Binario de Búsqueda (ABB)
        if (nodo.valor <= min || nodo.valor >= max) {
            System.out.println("  [Fallo de ABB] El nodo " + nodo.valor + " viola los límites del ABB.");
            return false;
        }

        // 2. Validar el Factor de Equilibrio (-1, 0, +1)
        int balance = obtenerFactorEquilibrio(nodo);
        if (Math.abs(balance) > 1) {
            System.out.println("  [Fallo de Balance] El nodo " + nodo.valor + " tiene un FB de " + balance + " (fuera de [-1, 0, 1]).");
            return false;
        }

        // 3. Verificación recursiva en los subárboles izquierdo y derecho
        return esAVLRecursivo(nodo.izquierdo, min, nodo.valor) &&
               esAVLRecursivo(nodo.derecho, nodo.valor, max);
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DE DETECCIÓN DE ÁRBOLES AVL ===");

        // --- PRUEBA 1: Árbol AVL Válido y Balanceado ---
        //       20
        //      /  \
        //     10   30
        NodoAVL raizValida = new NodoAVL(20);
        raizValida.izquierdo = new NodoAVL(10);
        raizValida.derecho = new NodoAVL(30);
        actualizarAltura(raizValida.izquierdo);
        actualizarAltura(raizValida.derecho);
        actualizarAltura(raizValida);

        System.out.println("1. Árbol 1 (Balanceado y Ordenado):");
        System.out.println("   ¿Es AVL?: " + esAVL(raizValida)); // Esperado: true

        System.out.println("\n-------------------------------------------\n");

        // --- PRUEBA 2: Árbol con desbalance en la Raíz (FB = -2) ---
        //       30
        //      /
        //     20
        //    /
        //   10
        NodoAVL raizDesbalanceada = new NodoAVL(30);
        raizDesbalanceada.izquierdo = new NodoAVL(20);
        raizDesbalanceada.izquierdo.izquierdo = new NodoAVL(10);
        actualizarAltura(raizDesbalanceada.izquierdo.izquierdo);
        actualizarAltura(raizDesbalanceada.izquierdo);
        actualizarAltura(raizDesbalanceada);

        System.out.println("2. Árbol 2 (Desbalance visible en la Raíz):");
        System.out.println("   ¿Es AVL?: " + esAVL(raizDesbalanceada)); // Esperado: false

        System.out.println("\n-------------------------------------------\n");

        // --- PRUEBA 3: Raíz balanceada (FB = 0) pero Subárbol Profundo Desbalanceado (FB = -2) ---
        //           50
        //         /    \
        //       20      70   (Raíz 50 tiene subárbol izq de altura 3 y der de altura 3 -> FB = 0)
        //      /  \    /  \
        //     10  30  60  80
        //    /
        //   5
        //  /
        // 2                  (El subárbol del nodo 10 se degenera -> FB = -2)
        NodoAVL raizEnganosa = new NodoAVL(50);
        
        // Rama izquierda
        raizEnganosa.izquierdo = new NodoAVL(20);
        raizEnganosa.izquierdo.izquierdo = new NodoAVL(10);
        raizEnganosa.izquierdo.derecho = new NodoAVL(30);
        raizEnganosa.izquierdo.izquierdo.izquierdo = new NodoAVL(5);
        raizEnganosa.izquierdo.izquierdo.izquierdo.izquierdo = new NodoAVL(2);

        // Rama derecha
        raizEnganosa.derecho = new NodoAVL(70);
        raizEnganosa.derecho.izquierdo = new NodoAVL(60);
        raizEnganosa.derecho.derecho = new NodoAVL(80);

        // Actualización de alturas de abajo hacia arriba
        actualizarAltura(raizEnganosa.izquierdo.izquierdo.izquierdo.izquierdo);
        actualizarAltura(raizEnganosa.izquierdo.izquierdo.izquierdo);
        actualizarAltura(raizEnganosa.izquierdo.izquierdo);
        actualizarAltura(raizEnganosa.izquierdo.derecho);
        actualizarAltura(raizEnganosa.izquierdo);

        actualizarAltura(raizEnganosa.derecho.izquierdo);
        actualizarAltura(raizEnganosa.derecho.derecho);
        actualizarAltura(raizEnganosa.derecho);

        actualizarAltura(raizEnganosa);

        System.out.println("3. Árbol 3 (Raíz con FB = 0 pero subárbol interno con FB = -2):");
        System.out.println("   Factor de equilibrio de la Raíz (50): " + obtenerFactorEquilibrio(raizEnganosa));
        System.out.println("   ¿Es AVL?: " + esAVL(raizEnganosa)); // Esperado: false
    }
}
