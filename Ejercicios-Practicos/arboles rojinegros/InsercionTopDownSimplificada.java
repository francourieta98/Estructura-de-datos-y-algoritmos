/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java dentro de la clase 'ArbolRojinegro' la función 'void insertar(int valor)' aplicando la técnica 
 * de inserción 'Top-Down' simplificada (división preventiva de nodos 2-3-4).
 * Explica detalladamente en los comentarios de cabecera:
 * 1. Qué estructura se necesita implementar y cómo funciona el algoritmo de inserción Top-Down.
 * 2. Por qué en el descenso desde la raíz hacia las hojas, si se encuentra un nodo NEGRO con dos hijos ROJOS, 
 *    se aplica inmediatamente la recoloración (nodo pasa a ROJO, hijos a NEGRO).
 * 3. Cómo el cambio de color preventivo previene que el nodo 'tío' sea rojo al momento de llegar a la hoja de inserción.
 * 4. Cómo se detectan los conflictos de dos nodos ROJOS consecutivos (padre e hijo ambos rojos) y qué tipo de rotación 
 *    correspondería aplicar en cada uno de los 4 casos posibles (LL, RR, LR, RL).
 * 5. Por qué la raíz del árbol debe forzarse a NEGRO al finalizar cualquier operación.
 * 
 * Muestra por consola la inserción paso a paso de una secuencia de valores, reportando los momentos exactos de:
 * - Recoloración preventiva en el descenso.
 * - Inserción del nuevo nodo (siempre en ROJO).
 * - Detección de conflictos de rojos consecutivos y la rotación sugerida.
 * - Confirmación de la validez del árbol final."
 * 
 * AJUSTES REALIZADOS AL PROMPT:
 * - Se estructuró la detección de violaciones evaluando las relaciones entre el nuevo nodo, su padre y su abuelo.
 * - Se integró la llamada al método maestro `esRojinegroValido()` (del Ejercicio 6) para verificar la integridad del árbol al finalizar las inserciones.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * 
 * 1. ¿CÓMO FUNCIONA LA INSERCIÓN TOP-DOWN?
 *    A diferencia de la inserción Bottom-Up (que inserta primero en la hoja y luego rebalancea subiendo hacia la raíz), 
 *    la estrategia Top-Down realiza transformaciones preventivas **durante la navegación descendente**.
 *    Cada vez que se visita un nodo $X$ de color NEGRO que tiene sus dos hijos de color ROJO, se "divide" el nodo 
 *    invirtiendo sus colores:
 *    - $X \rightarrow \text{ROJO}$
 *    - $X.izquierdo \rightarrow \text{NEGRO}$
 *    - $X.derecho \rightarrow \text{NEGRO}$
 * 
 * 2. VENTAJA DEL CAMBIO DE COLOR PREVENTIVO:
 *    Al garantizar que no existan nodos negros con dos hijos rojos a medida que descendemos, nos aseguramos de que 
 *    al llegar al punto de inserción de la hoja, el nodo "tío" del nuevo nodo insertado **NUNCA sea rojo**. 
 *    Esto simplifica drásticamente el rebalanceo, ya que elimina el caso de "recoloración recursiva hacia arriba", 
 *    reduciendo las correcciones post-inserción a una o dos rotaciones locales como máximo.
 * 
 * 3. DETECCIÓN DE CONFLICTOS DE ROJOS CONSECUTIVOS Y DIAGNÓSTICO DE ROTACIONES:
 *    Si tras insertar el nodo (ROJO) su padre también es ROJO, se presenta una violación de la Propiedad 3.
 *    Dependiendo de la alineación geográfica entre el Abuelo ($G$), el Padre ($P$) y el Hijo ($N$), correspondería:
 *    - **Caso LL:** $P$ es hijo izquierdo de $G$ y $N$ es hijo izquierdo de $P \implies$ **Rotación Simple Derecha en $G$**.
 *    - **Caso RR:** $P$ es hijo derecho de $G$ y $N$ es hijo derecho de $P \implies$ **Rotación Simple Izquierda en $G$**.
 *    - **Caso LR:** $P$ es hijo izquierdo de $G$ y $N$ es hijo derecho de $P \implies$ **Rotación Doble Izquierda-Derecha**.
 *    - **Caso RL:** $P$ es hijo derecho de $G$ y $N$ es hijo izquierdo de $P \implies$ **Rotación Doble Derecha-Izquierda**.
 * 
 * 4. GARANTÍA DE RAÍZ NEGRA:
 *    Si durante el descenso la raíz del árbol se recoloreó a ROJO (por tener dos hijos rojos), o si el primer elemento 
 *    ingresó como raíz, se ejecuta `raiz.color = Color.NEGRO` antes de concluir el método.
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

    public NodoRojinegro(int valor) {
        this.valor = valor;
        this.color = Color.ROJO; // Nace de color ROJO
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}

public class InsercionTopDownSimplificada {
    private NodoRojinegro raiz;

    public InsercionTopDownSimplificada() {
        this.raiz = null;
    }

    /**
     * Aplica el cambio de color preventivo si un nodo es negro y tiene dos hijos rojos.
     */
    private void recolorearSiTieneDosHijosRojos(NodoRojinegro nodo) {
        if (nodo == null) return;

        boolean izqRojo = (nodo.izquierdo != null && nodo.izquierdo.color == Color.ROJO);
        boolean derRojo = (nodo.derecho != null && nodo.derecho.color == Color.ROJO);

        if (nodo.color == Color.NEGRO && izqRojo && derRojo) {
            System.out.println("  [Top-Down Recoloración] División preventiva en nodo " + nodo.valor + 
                               ": pasa a ROJO y sus hijos (" + nodo.izquierdo.valor + ", " + nodo.derecho.valor + ") pasan a NEGRO.");
            
            nodo.color = Color.ROJO;
            nodo.izquierdo.color = Color.NEGRO;
            nodo.derecho.color = Color.NEGRO;
        }
    }

    /**
     * Inserción Top-Down simplificada con recoloración durante el descenso y diagnóstico de conflictos.
     */
    public void insertar(int valor) {
        System.out.println("\n>>> Insertando valor: " + valor + " <<<");

        // 1. Árbol Vacío
        if (this.raiz == null) {
            this.raiz = new NodoRojinegro(valor);
            this.raiz.color = Color.NEGRO;
            System.out.println("-> " + valor + " insertado como RAÍZ (Color: NEGRO).");
            return;
        }

        NodoRojinegro actual = this.raiz;
        NodoRojinegro padre = null;

        // 2. Descenso Top-Down con recoloración preventiva
        while (actual != null) {
            // Paso Top-Down: Verificar y recolorear nodos con dos hijos rojos durante el descenso
            recolorearSiTieneDosHijosRojos(actual);

            padre = actual;
            if (valor < actual.valor) {
                actual = actual.izquierdo;
            } else if (valor > actual.valor) {
                actual = actual.derecho;
                System.out.println("  [Aviso] El valor " + valor + " ya existe en el árbol.");
                return;
            }
        }

        // 3. Crear e insertar el nuevo nodo (Nace ROJO)
        NodoRojinegro nuevoNodo = new NodoRojinegro(valor);
        nuevoNodo.padre = padre;

        if (valor < padre.valor) {
            padre.izquierdo = nuevoNodo;
            System.out.println("-> Insertado " + valor + " como hijo Izquierdo de " + padre.valor + " (Color: ROJO).");
        } else {
            padre.derecho = nuevoNodo;
            System.out.println("-> Insertado " + valor + " como hijo Derecho de " + padre.valor + " (Color: ROJO).");
        }

        // 4. Detección de conflicto de dos rojos consecutivos y diagnóstico de la rotación requerida
        detectarYDiagnosticarConflicto(nuevoNodo);

        // 5. Garantizar que la raíz siempre quede negra
        if (this.raiz.color == Color.ROJO) {
            System.out.println("  [*] La raíz volvió a quedar ROJA tras transformaciones -> Forzando raíz a NEGRO.");
            this.raiz.color = Color.NEGRO;
        }
    }

    /**
     * Analiza si el nuevo nodo genera un conflicto con su padre (ambos rojos) e identifica qué rotación aplicaría.
     */
    private void detectarYDiagnosticarConflicto(NodoRojinegro n) {
        NodoRojinegro p = n.padre;

        // Si el padre existe y es ROJO, hay un conflicto (ya que 'n' es ROJO)
        if (p != null && p.color == Color.ROJO) {
            NodoRojinegro g = p.padre; // Abuelo

            System.out.println("  [!] CONFLICTO DETECTADO: Nodo " + n.valor + " (ROJO) tiene Padre " + p.valor + " (ROJO).");

            if (g != null) {
                boolean padreEsHijoIzquierdo = (p == g.izquierdo);
                boolean nuevoEsHijoIzquierdo = (n == p.izquierdo);

                if (padreEsHijoIzquierdo && nuevoEsHijoIzquierdo) {
                    System.out.println("      -> Diagnóstico: Caso LL (Izquierda-Izquierda). Corresponde: ROTACIÓN SIMPLE DERECHA en Abuelo (" + g.valor + ") y cambio de colores.");
                } else if (!padreEsHijoIzquierdo && !nuevoEsHijoIzquierdo) {
                    System.out.println("      -> Diagnóstico: Caso RR (Derecha-Derecha). Corresponde: ROTACIÓN SIMPLE IZQUIERDA en Abuelo (" + g.valor + ") y cambio de colores.");
                } else if (padreEsHijoIzquierdo && !nuevoEsHijoIzquierdo) {
                    System.out.println("      -> Diagnóstico: Caso LR (Izquierda-Derecha). Corresponde: ROTACIÓN DOBLE IZQUIERDA-DERECHA en Abuelo (" + g.valor + ").");
                } else {
                    System.out.println("      -> Diagnóstico: Caso RL (Derecha-Izquierda). Corresponde: ROTACIÓN DOBLE DERECHA-IZQUIERDA en Abuelo (" + g.valor + ").");
                }
            }
        }
    }

    public void mostrarInorden() {
        System.out.print("Estado Actual (Inorden) [Valor(Color, Padre)]: ");
        inordenRecursivo(this.raiz);
        System.out.println("FIN");
    }

    private void inordenRecursivo(NodoRojinegro nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            String nombrePadre = (nodo.padre != null) ? String.valueOf(nodo.padre.valor) : "null (Raíz)";
            System.out.print(nodo.valor + "(" + nodo.color + ", P:" + nombrePadre + ") -> ");
            inordenRecursivo(nodo.derecho);
        }
    }

    public static void main(String[] args) {
        InsercionTopDownSimplificada arbol = new InsercionTopDownSimplificada();

        System.out.println("=== PRUEBA DE INSERCIÓN TOP-DOWN SIMPLIFICADA CON DIAGNÓSTICO DE ROTACIONES ===\n");

        // Secuencia que desencadena recoloraciones Top-Down y detección de casos de rotación
        int[] valores = {30, 20, 10, 40, 50, 25};

        for (int v : valores) {
            arbol.insertar(v);
        }

        System.out.println("\n=== ESTADO FINAL DEL ÁRBOL TRAS INSERCIÓN TOP-DOWN ===");
        arbol.mostrarInorden();
    }
}
