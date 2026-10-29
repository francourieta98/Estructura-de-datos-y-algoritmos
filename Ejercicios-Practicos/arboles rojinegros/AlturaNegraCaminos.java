/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java los métodos 'int alturaNegraIzquierda()' e 'int alturaNegraDerecha()' dentro de una clase de Árbol Rojinegro.
 * Explica en los comentarios:
 * - Qué representa la 'altura negra' ($bh$ o black-height) en un Árbol Rojinegro.
 * - Por qué el conteo de la altura negra incluye a las hojas centinela (null/NIL) pero habitualmente excluye al nodo raíz de partida.
 * - Cómo se implementan los recorridos iterativos o recursivos descendiendo estrictamente por las ramas izquierdas y derechas.
 * - Cómo se probarán ambos métodos con árboles donde ambos caminos tengan la misma o diferente altura negra.
 * 
 * Incluye las secciones de auditoría 'Prompt inicial utilizado' y 'Ajustes realizados al prompt'."
 * 
 * AJUSTES REALIZADOS AL PROMPT:
 * - Se clarificó la convención formal de cálculo: La altura negra de un nodo $x$, denotada $bh(x)$, es el número de nodos NEGROS 
 *   en cualquier camino desde $x$ (excluyendo a $x$) hasta una hoja centinela NIL (incluyendo a NIL).
 * - Se diseñó el algoritmo iterativo por su simplicidad y eficiencia $O(\log N)$ para recorrer exclusivamente el camino izquierdo o derecho.
 * 
 * CONCEPTO DE ALTURA NEGRA (BLACK-HEIGHT):
 * 1. DEFINICIÓN:
 *    La altura negra de un nodo es la cantidad de nodos de color NEGRO que se cruzan al descender hacia una hoja. 
 *    En un Árbol Rojinegro válido, la Propiedad 5 establece que TODOS los caminos desde la raíz hasta cualquier hoja NIL 
 *    deben tener exactamente el mismo número de nodos negros.
 * 
 * 2. CONTEO DE RAÍZ Y NIL:
 *    - La raíz no se cuenta en su propia altura negra ($bh(\text{raíz})$), pero se cuentan todos los nodos negros descendientes.
 *    - Los nodos nulos (`null` o centinelas NIL) son negros por definición, por lo que aportan +1 al contador al llegar al final del camino.
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

public class AlturaNegraCaminos {
    private NodoRojinegro raiz;

    public AlturaNegraCaminos() {
        this.raiz = null;
    }

    /**
     * Calcula la altura negra desde la raíz siguiendo siempre el camino de la izquierda.
     * Excluye la raíz del conteo e incluye las hojas centinela null (NIL).
     */
    public int alturaNegraIzquierda() {
        if (this.raiz == null) {
            return 0; // Árbol vacío
        }

        int contadorNegros = 0;
        // Comenzamos el recorrido a partir del primer hijo izquierdo (excluyendo la raíz)
        NodoRojinegro actual = this.raiz.izquierdo;

        while (actual != null) {
            if (actual.color == Color.NEGRO) {
                contadorNegros++;
            }
            actual = actual.izquierdo;
        }

        // Se suma +1 correspondiente a la hoja centinela NIL (null) al final del camino
        return contadorNegros + 1;
    }

    /**
     * Calcula la altura negra desde la raíz siguiendo siempre el camino de la derecha.
     * Excluye la raíz del conteo e incluye las hojas centinela null (NIL).
     */
    public int alturaNegraDerecha() {
        if (this.raiz == null) {
            return 0; // Árbol vacío
        }

        int contadorNegros = 0;
        // Comenzamos el recorrido a partir del primer hijo derecho (excluyendo la raíz)
        NodoRojinegro actual = this.raiz.derecho;

        while (actual != null) {
            if (actual.color == Color.NEGRO) {
                contadorNegros++;
            }
            actual = actual.derecho;
        }

        // Se suma +1 correspondiente a la hoja centinela NIL (null) al final del camino
        return contadorNegros + 1;
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE CÁLCULO DE ALTURA NEGRA POR CAMINOS ===");

        // --- CONSTRUCCIÓN DEL ÁRBOL DE PRUEBA ---
        //                20 (NEGRO) [Raíz - No se cuenta]
        //             /              \
        //        10 (ROJO)         30 (NEGRO)  [+1 Der]
        //        /      \              \
        //   5 (NEGRO)  15 (ROJO)     40 (ROJO)
        //     [+1 Izq]                 \
        //                            50 (NEGRO) [+1 Der]
        //
        // Altura negra esperada camino izquierdo: 1 (nodo 5) + 1 (NIL) = 2
        // Altura negra esperada camino derecho:   1 (nodo 30) + 1 (nodo 50) + 1 (NIL) = 3

        AlturaNegraCaminos arbol = new AlturaNegraCaminos();

        // Raíz (Negra)
        arbol.raiz = new NodoRojinegro(20, Color.NEGRO);

        // Rama Izquierda
        arbol.raiz.izquierdo = new NodoRojinegro(10, Color.ROJO);
        arbol.raiz.izquierdo.izquierdo = new NodoRojinegro(5, Color.NEGRO);
        arbol.raiz.izquierdo.derecho = new NodoRojinegro(15, Color.ROJO);

        // Rama Derecha
        arbol.raiz.derecho = new NodoRojinegro(30, Color.NEGRO);
        arbol.raiz.derecho.derecho = new NodoRojinegro(40, Color.ROJO);
        arbol.raiz.derecho.derecho.derecho = new NodoRojinegro(50, Color.NEGRO);

        System.out.println("1. Altura Negra del Camino Izquierdo: " + arbol.alturaNegraIzquierda());
        System.out.println("2. Altura Negra del Camino Derecho:   " + arbol.alturaNegraDerecha());

        boolean mismaAltura = (arbol.alturaNegraIzquierda() == arbol.alturaNegraDerecha());
        System.out.println("\n¿Tienen la misma altura negra en los extremos?: " + mismaAltura);
    }
}
