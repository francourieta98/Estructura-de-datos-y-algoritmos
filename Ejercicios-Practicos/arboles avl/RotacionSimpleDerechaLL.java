/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java el método de 'rotacionDerecha(NodoAVL y)' para solucionar el desbalanceo del Caso LL 
 * (Izquierda-Izquierda) en un Árbol AVL. Explica en los comentarios:
 * - Por qué insertar los valores 30, 20 y 10 en ese orden genera un desbalanceo de tipo LL.
 * - Cómo funciona geométricamente la rotación simple a la derecha (el hijo izquierdo pasa a ser la nueva raíz 
 *   y el nodo desbalanceado pasa a ser su hijo derecho).
 * - Qué sucede con el subárbol derecho del hijo izquierdo durante la reasignación de punteros.
 * - Cómo se actualizan las alturas de los nodos involucrados después de la rotación.
 * Prueba el método insertando la secuencia [30, 20, 10] y verifica que el árbol quede balanceado con raíz 20."
 * 
 * EXPLICACIÓN DEL CASO LL Y LA ROTACIÓN SIMPLE DERECHA:
 * 1. POR QUÉ LA SECUENCIA [30, 20, 10] GENERA UN CASO LL:
 *    - Se inserta el 30: Es la raíz.
 *    - Se inserta el 20: Va a la izquierda de 30 (30.izquierdo = 20).
 *    - Se inserta el 10: Va a la izquierda de 20 (20.izquierdo = 10).
 *    
 *    Estructura generada:
 *            30  (FB = -2) -> [DESBALANCE!]
 *           /
 *          20    (FB = -1)
 *         /
 *        10      (FB = 0)
 * 
 *    El nodo 30 tiene una altura izquierda de 2 y derecha de 0, dando un Factor de Balance de $0 - 2 = -2$.
 *    Dado que el nuevo nodo (10) se insertó en el subárbol **Izquierdo** del hijo **Izquierdo** de 30, 
 *    se clasifica estrictamente como un **Caso Izquierda-Izquierda (LL)**.
 * 
 * 2. MECANISMO DE LA ROTACIÓN DERECHA (Paso a Paso):
 *    Sean:
 *    - `y`: Nodo desbalanceado (30)
 *    - `x`: Hijo izquierdo de `y` (20), que se convertirá en la nueva raíz del subárbol.
 *    - `T2`: Subárbol derecho de `x` (si existiera).
 * 
 *    Pasos de reasignación de punteros:
 *    a) `x = y.izquierdo`          (x apunta a 20)
 *    b) `T2 = x.derecho`           (se guarda la rama derecha de 20)
 *    c) `x.derecho = y`            (20 adopta a 30 como su hijo derecho)
 *    d) `y.izquierdo = T2`         (30 adopta a T2 como su hijo izquierdo)
 * 
 *    Estructura resultante tras rotar a la derecha:
 *            20  (FB = 0)  -> [NUEVA RAÍZ]
 *           /  \
 *          10   30
 * 
 * 3. ACTUALIZACIÓN DE ALTURAS:
 *    Las alturas de `y` (30) y de `x` (20) cambian tras la rotación. Primero debe actualizarse la altura de `y`
 *    (ya que ahora está más abajo) y luego la de `x` (que ahora es el nodo superior).
 * 
 * AJUSTES REALIZADOS DESPUÉS DE LA PRIMERA RESPUESTA:
 * - Se integró la rotación dentro del flujo de inserción automática al detectar $FB = -2$ junto a $FB(hijoIzquierdo) \le 0$.
 * 
 * MODIFICACIONES O CORRECCIONES MANUALES REALIZADAS:
 * - Se agregaron impresiones por consola que muestran el árbol antes y después de aplicar la rotación simple a la derecha.
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

public class RotacionSimpleDerechaLL {
    private NodoAVL raiz;

    public RotacionSimpleDerechaLL() {
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

    /**
     * Ejecuta una Rotación Simple a la Derecha sobre el nodo desbalanceado 'y'.
     * @param y Nodo desbalanceado (FB = -2).
     * @return Nueva raíz del subárbol (x).
     */
    private NodoAVL rotacionDerecha(NodoAVL y) {
        System.out.println("  [!] Ejecutando Rotación Simple a la Derecha sobre el nodo: " + y.valor);
        
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;

        // Reasignación de punteros para la rotación
        x.derecho = y;
        y.izquierdo = T2;

        // Actualizar alturas (primero el nodo que quedó más abajo 'y', luego la nueva raíz 'x')
        actualizarAltura(y);
        actualizarAltura(x);

        return x; // Retorna la nueva raíz del subárbol
    }

    public void insertar(int valor) {
        raiz = insertarYBalancear(raiz, valor);
    }

    private NodoAVL insertarYBalancear(NodoAVL nodo, int valor) {
        // 1. Inserción estándar de ABB
        if (nodo == null) {
            return new NodoAVL(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierdo = insertarYBalancear(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertarYBalancear(nodo.derecho, valor);
        } else {
            return nodo; // Duplicados no permitidos
        }

        // 2. Actualizar altura del nodo ancestro
        actualizarAltura(nodo);

        // 3. Obtener el factor de equilibrio
        int balance = obtenerFactorEquilibrio(nodo);

        // 4. Verificación y resolución del CASO LL (Izquierda-Izquierda)
        // Ocurre si balance == -2 y la inserción fue en la rama izquierda del hijo izquierdo (valor < nodo.izquierdo.valor)
        if (balance == -2 && obtenerFactorEquilibrio(nodo.izquierdo) <= 0) {
            System.out.println("-> Desbalance detectado en nodo " + nodo.valor + " (FB = " + balance + "). Caso LL.");
            return rotacionDerecha(nodo);
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
        RotacionSimpleDerechaLL arbol = new RotacionSimpleDerechaLL();

        System.out.println("=== PRUEBA DE CASO LL (ROTACIÓN SIMPLE DERECHA) ===");
        System.out.println("Insertando valores en secuencia: 30, 20, 10\n");

        System.out.println("1. Insertando 30...");
        arbol.insertar(30);

        System.out.println("2. Insertando 20...");
        arbol.insertar(20);

        System.out.println("3. Insertando 10...");
        arbol.insertar(10); // Aquí se activa el desbalance LL y la rotación

        System.out.println("\n=== ESTADO FINAL DEL ÁRBOL REBALANCEDO ===");
        arbol.mostrarInordenConAlturas();
    }
}
