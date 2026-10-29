/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java los métodos 'void rotarIzquierda(NodoRojinegro x)' y 'void rotarDerecha(NodoRojinegro y)' 
 * dentro de la clase 'ArbolRojinegro'.
 * Explica en los comentarios:
 * - Qué estructura se implementa y cómo difieren las rotaciones de un Árbol Rojinegro respecto a un Árbol AVL 
 *   (reutilización del intercambio estructural, pero incorporando la actualización de los punteros hacia el 'padre').
 * - Cómo se actualizan los 6 enlaces clave durante la rotación:
 *   1. Subárbol cruzado (T2) adopta a su nuevo padre.
 *   2. El nodo que asciende (hijo) apunta al antiguo padre del nodo que desciende.
 *   3. El antiguo padre (o la raíz del árbol) actualiza su puntero hacia el nuevo nodo superior.
 *   4. La relación directa entre el nodo pivot y el nodo desplazado.
 * - Cómo se trata el caso especial donde el nodo rotado es la raíz del árbol.
 * Muestra por consola pruebas de ambas rotaciones verificando las relaciones de valor, color y puntero al padre."
 * 
 * AJUSTES REALIZADOS AL PROMPT:
 * - Se aseguró que las funciones manejen correctamente el puntero 'padre' del nodo cruzado (T2) evaluando que no sea `null`.
 * - Se integró la actualización directa de la variable de instancia `raiz` cuando el nodo rotado carece de padre (`nodo.padre == null`).
 * 
 * JUSTIFICACIÓN TÉCNICA:
 * ROTACIONES EN ÁRBOLES ROJINEGROS VS. ÁRBOLES AVL:
 * 1. ESTRUCTURA GEOMÉTRICA IDÉNTICA:
 *    La reorganización física de las ramas es idéntica a la realizada en los Árboles AVL. Una rotación conserva 
 *    estrictamente la propiedad de ordenamiento del Árbol Binario de Búsqueda (ABB).
 * 
 * 2. DIFERENCIA CLAVE (EL PUNTERO AL PADRE):
 *    En un Árbol AVL convencional, la navegación es unireccional (de arriba hacia abajo) y los enlaces se actualizan 
 *    mediante los retornos recursivos (`nodo.izquierdo = rotar(...)`). 
 *    En un Árbol Rojinegro, debido a que cada nodo posee una referencia explícita a su `padre`, la rotación debe 
 *    actualizar bidireccionalmente todos los punteros involucrados:
 *    - `hijo.padre = padre.padre`
 *    - `padre.padre = hijo`
 *    - `subarbolCruzado.padre = padre`
 * 
 * 3. OBJETIVO DE LA ROTACIÓN EN ROJINEGROS:
 *    Mientras en AVL la rotación busca restaurar la **altura estricta** ($|FB| \le 1$), en un Árbol Rojinegro la rotación 
 *    se utiliza para reestructurar ramas cuando ocurren violaciones de **rojos consecutivos** tras una inserción o eliminación.
 */

enum Color {
    ROJO,
    NEGRO
}

class NodoRojinegro {
    int valor;
    Color color;
    NodoRojinegro izquierdo;
    NodoRojinegro derecho;
    NodoRojinegro padre;

    public NodoRojinegro(int valor, Color color) {
        this.valor = valor;
        this.color = color;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}

public class RotacionesSimplesRojinegro {
    private NodoRojinegro raiz;

    public RotacionesSimplesRojinegro() {
        this.raiz = null;
    }

    /**
     * Rotación Simple a la Izquierda sobre el nodo 'x'.
     * El hijo derecho 'y' pasa a tomar el lugar de 'x'.
     */
    public void rotarIzquierda(NodoRojinegro x) {
        if (x == null || x.derecho == null) return;

        System.out.println("  [!] Ejecutando Rotación a la Izquierda sobre el nodo: " + x.valor);

        NodoRojinegro y = x.derecho; // 'y' será la nueva raíz del subárbol
        NodoRojinegro T2 = y.izquierdo; // Subárbol cruzado

        // 1. Reasignación del subárbol cruzado T2
        x.derecho = T2;
        if (T2 != null) {
            T2.padre = x;
        }

        // 2. Conectar el padre de 'x' con 'y'
        y.padre = x.padre;
        if (x.padre == null) {
            this.raiz = y; // 'x' era la raíz del árbol completo
        } else if (x == x.padre.izquierdo) {
            x.padre.izquierdo = y;
        } else {
            x.padre.derecho = y;
        }

        // 3. Colocar a 'x' como hijo izquierdo de 'y'
        y.izquierdo = x;
        x.padre = y;
    }

    /**
     * Rotación Simple a la Derecha sobre el nodo 'y'.
     * El hijo izquierdo 'x' pasa a tomar el lugar de 'y'.
     */
    public void rotarDerecha(NodoRojinegro y) {
        if (y == null || y.izquierdo == null) return;

        System.out.println("  [!] Ejecutando Rotación a la Derecha sobre el nodo: " + y.valor);

        NodoRojinegro x = y.izquierdo; // 'x' será la nueva raíz del subárbol
        NodoRojinegro T2 = x.derecho; // Subárbol cruzado

        // 1. Reasignación del subárbol cruzado T2
        y.izquierdo = T2;
        if (T2 != null) {
            T2.padre = y;
        }

        // 2. Conectar el padre de 'y' con 'x'
        x.padre = y.padre;
        if (y.padre == null) {
            this.raiz = x; // 'y' era la raíz del árbol completo
        } else if (y == y.padre.izquierdo) {
            y.padre.izquierdo = x;
        } else {
            y.padre.derecho = x;
        }

        // 3. Colocar a 'y' como hijo derecho de 'x'
        x.derecho = y;
        y.padre = x;
    }

    public void mostrarInorden() {
        System.out.print("Estado [Valor(Color, Padre)]: ");
        inordenRecursivo(this.raiz);
        System.out.println("FIN");
    }

    private void inordenRecursivo(NodoRojinegro nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            String nombrePadre = (nodo.padre != null) ? String.valueOf(nodo.padre.valor) : "null (Raíz)";
            System.out.print(nodo.valor + "(" + nodo.color + ", Padre:" + nombrePadre + ") -> ");
            inordenRecursivo(nodo.derecho);
        }
    }

    public static void main(String[] args) {
        RotacionesSimplesRojinegro arbol = new RotacionesSimplesRojinegro();

        System.out.println("=== PRUEBA DE ROTACIONES SIMPLES CON MANEJO DE PADRES ===\n");

        // --- PRUEBA 1: Rotación Izquierda ---
        //       10 (NEGRO) [Raíz]
        //         \
        //          20 (ROJO)
        //            \
        //             30 (ROJO)
        arbol.raiz = new NodoRojinegro(10, Color.NEGRO);
        arbol.raiz.derecho = new NodoRojinegro(20, Color.ROJO);
        arbol.raiz.derecho.padre = arbol.raiz;

        arbol.raiz.derecho.derecho = new NodoRojinegro(30, Color.ROJO);
        arbol.raiz.derecho.derecho.padre = arbol.raiz.derecho;

        System.out.println("1. Estado Inicial (Previo a Rotación Izquierda en raíz 10):");
        arbol.mostrarInorden();

        arbol.rotarIzquierda(arbol.raiz);

        System.out.println("Estado Tras Rotación Izquierda (Nueva Raíz: 20):");
        arbol.mostrarInorden();

        System.out.println("\n----------------------------------------------------\n");

        // --- PRUEBA 2: Rotación Derecha ---
        //       30 (NEGRO) [Raíz]
        //      /
        //     20 (ROJO)
        //    /
        //   10 (ROJO)
        RotacionesSimplesRojinegro arbol2 = new RotacionesSimplesRojinegro();
        arbol2.raiz = new NodoRojinegro(30, Color.NEGRO);
        
        arbol2.raiz.izquierdo = new NodoRojinegro(20, Color.ROJO);
        arbol2.raiz.izquierdo.padre = arbol2.raiz;

        arbol2.raiz.izquierdo.izquierdo = new NodoRojinegro(10, Color.ROJO);
        arbol2.raiz.izquierdo.izquierdo.padre = arbol2.raiz.izquierdo;

        System.out.println("2. Estado Inicial (Previo a Rotación Derecha en raíz 30):");
        arbol2.mostrarInorden();

        arbol2.rotarDerecha(arbol2.raiz);

        System.out.println("Estado Tras Rotación Derecha (Nueva Raíz: 20):");
        arbol2.mostrarInorden();
    }
}
