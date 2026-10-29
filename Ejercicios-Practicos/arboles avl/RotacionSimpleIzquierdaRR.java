/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java el método 'rotacionIzquierda(NodoAVL x)' para solucionar el desbalanceo del Caso RR 
 * (Derecha-Derecha) en un Árbol AVL. Explica en los comentarios:
 * - Por qué insertar los valores 10, 20 y 30 en ese orden genera un desbalanceo de tipo RR.
 * - Cómo funciona geométricamente la rotación simple a la izquierda (el hijo derecho pasa a ser la nueva raíz 
 *   y el nodo desbalanceado pasa a ser su hijo izquierdo).
 * - Qué sucede con el subárbol izquierdo del hijo derecho durante la reasignación de punteros.
 * - Cómo se actualizan las alturas de los nodos involucrados después de la rotación.
 * Prueba el método insertando la secuencia [10, 20, 30] y verifica que el árbol quede balanceado con raíz 20."
 * 
 * EXPLICACIÓN DEL CASO RR Y LA ROTACIÓN SIMPLE IZQUIERDA:
 * 1. POR QUÉ LA SECUENCIA [10, 20, 30] GENERA UN CASO RR:
 *    - Se inserta el 10: Es la raíz.
 *    - Se inserta el 20: Va a la derecha de 10 (10.derecho = 20).
 *    - Se inserta el 30: Va a la derecha de 20 (20.derecho = 30).
 *    
 *    Estructura generada (previa al rebalanceo):
 *            10  (FB = +2) -> [DESBALANCE!]
 *              \
 *               20    (FB = +1)
 *                 \
 *                  30 (FB = 0)
 * 
 *    El nodo 10 tiene una altura izquierda de 0 y derecha de 2, dando un Factor de Balance de $2 - 0 = +2$.
 *    Dado que el nuevo nodo (30) se insertó en el subárbol **Derecho** del hijo **Derecho** de 10, 
 *    se clasifica estrictamente como un **Caso Derecha-Derecha (RR)**.
 * 
 * 2. MECANISMO DE LA ROTACIÓN IZQUIERDA (Paso a Paso):
 *    Sean:
 *    - `x`: Nodo desbalanceado (10).
 *    - `y`: Hijo derecho de `x` (20), que se convertirá en la nueva raíz del subárbol.
 *    - `T2`: Subárbol izquierdo de `y` (si existiera).
 * 
 *    Pasos de reasignación de punteros:
 *    a) `y = x.derecho`           (y apunta a 20)
 *    b) `T2 = y.izquierdo`        (se guarda el subárbol izquierdo de 20)
 *    c) `y.izquierdo = x`         (20 adopta a 10 como su hijo izquierdo)
 *    d) `x.derecho = T2`          (10 adopta a T2 como su hijo derecho)
 * 
 *    Estructura resultante tras rotar a la izquierda:
 *            20  (FB = 0)  -> [NUEVA RAÍZ]
 *           /  \
 *          10   30
 * 
 * 3. ACTUALIZACIÓN DE ALTURAS:
 *    Las alturas de `x` (10) y de `y` (20) cambian tras la rotación. Primero debe actualizarse la altura de `x`
 *    (ya que ahora descendió a ser hijo) y posteriormente la de `y` (que ascendió a raíz).
 * 
 * AJUSTES REALIZADOS DESPUÉS DE LA PRIMERA RESPUESTA:
 * - Se integró la rotación dentro del flujo de inserción automática al detectar $FB = +2$ junto a $FB(hijoDerecho) \ge 0$.
 * 
 * MODIFICACIONES O CORRECCIONES MANUALES REALIZADAS:
 * - Se añadieron impresiones por consola para rastrear cuándo se activa el desbalance y verificar que el recorrido inorden muestre alturas consistentes.
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

public class RotacionSimpleIzquierdaRR {
    private NodoAVL raiz;

    public RotacionSimpleIzquierdaRR() {
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
     * Ejecuta una Rotación Simple a la Izquierda sobre el nodo desbalanceado 'x'.
     * @param x Nodo desbalanceado (FB = +2).
     * @return Nueva raíz del subárbol (y).
     */
    private NodoAVL rotacionIzquierda(NodoAVL x) {
        System.out.println("  [!] Ejecutando Rotación Simple a la Izquierda sobre el nodo: " + x.valor);
        
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;

        // Reasignación de punteros para la rotación
        y.izquierdo = x;
        x.derecho = T2;

        // Actualizar alturas (primero el nodo que quedó más abajo 'x', luego la nueva raíz 'y')
        actualizarAltura(x);
        actualizarAltura(y);

        return y; // Retorna la nueva raíz del subárbol
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

        // 4. Verificación y resolución del CASO RR (Derecha-Derecha)
        // Ocurre si balance == +2 y la inserción fue en la rama derecha del hijo derecho
        if (balance == 2 && obtenerFactorEquilibrio(nodo.derecho) >= 0) {
            System.out.println("-> Desbalance detectado en nodo " + nodo.valor + " (FB = +" + balance + "). Caso RR.");
            return rotacionIzquierda(nodo);
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
        RotacionSimpleIzquierdaRR arbol = new RotacionSimpleIzquierdaRR();

        System.out.println("=== PRUEBA DE CASO RR (ROTACIÓN SIMPLE IZQUIERDA) ===");
        System.out.println("Insertando valores en secuencia: 10, 20, 30\n");

        System.out.println("1. Insertando 10...");
        arbol.insertar(10);

        System.out.println("2. Insertando 20...");
        arbol.insertar(20);

        System.out.println("3. Insertando 30...");
        arbol.insertar(30); // Se detecta desbalance RR y se ejecuta la rotación izquierda

        System.out.println("\n=== ESTADO FINAL DEL ÁRBOL REBALANCEADO ===");
        arbol.mostrarInordenConAlturas();
    }
}
