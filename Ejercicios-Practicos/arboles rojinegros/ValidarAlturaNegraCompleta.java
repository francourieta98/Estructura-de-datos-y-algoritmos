/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java dentro de la clase de Árbol Rojinegro el método 'boolean mismaAlturaNegra()' que verifique 
 * recursivamente que TODOS los caminos desde la raíz hasta cualquier hoja centinela (null/NIL) tengan exactamente 
 * el mismo número de nodos negros (Propiedad 5 de los Árboles Rojinegros).
 * Explica en los comentarios:
 * - Cómo se manejan las hojas 'null' como nodos negros conceptuales (aportando +1 a la altura negra).
 * - Cómo la función recursiva calcula y compara la altura negra devuelta por los subárboles izquierdo y derecho.
 * - Por qué retornar '-1' o usar una excepción/indicador de error permite propagar de inmediato la falla 
 *   cuando dos caminos difieren en altura negra.
 * Muestra por consola casos de prueba con un árbol válido (todos los caminos con igual altura negra) 
 * y un árbol inválido (caminos con distinta cantidad de nodos negros)."
 * 
 * AJUSTES REALIZADOS AL PROMPT:
 * - Se optó por una estrategia recursiva eficiente de una sola pasada (Bottom-Up): la función recursiva 'obtenerAlturaNegra(NodoRojinegro)' 
 *   retorna la altura negra del subárbol si es uniforme, o '-1' si detecta cualquier desequilibrio entre sus ramas.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * PROPIEDAD ROJINEGRA EVALUADA (PROPIEDAD 5):
 * "Para cada nodo, todos los caminos simples desde el nodo hasta cualquiera de sus hojas descendientes NIL 
 * contienen el mismo número de nodos negros."
 * 
 * ALGORITMO RECURSIVO DE ALTURA NEGRA UNIFORME:
 * 1. Caso Base (nodo == null):
 *    Los nodos 'null' representan las hojas centinela NIL. Por definición, todo nodo NIL es NEGRO, por lo que 
 *    un puntero 'null' retorna una altura negra de 1.
 * 
 * 2. Paso Recursivo:
 *    - Se calcula la altura negra de la rama izquierda: `int altIzq = obtenerAlturaNegra(nodo.izquierdo)`.
 *    - Se calcula la altura negra de la rama derecha: `int altDer = obtenerAlturaNegra(nodo.derecho)`.
 * 
 * 3. Detección de Inconsistencia:
 *    - Si `altIzq == -1` o `altDer == -1` (un subárbol interno ya falló) o `altIzq != altDer` (los caminos 
 *      izquierdo y derecho del nodo actual difieren), se retorna `-1` (falla global).
 * 
 * 4. Propagación de Altura:
 *    - Si las alturas coinciden (`altIzq == altDer`), el nodo actual retorna:
 *      `altIzq + (nodo.color == Color.NEGRO ? 1 : 0)`.
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

public class ValidarAlturaNegraCompleta {
    private NodoRojinegro raiz;

    public ValidarAlturaNegraCompleta() {
        this.raiz = null;
    }

    /**
     * Método público para verificar si todos los caminos tienen la misma altura negra.
     */
    public boolean mismaAlturaNegra() {
        // Si el método recursivo no retorna -1, significa que todos los caminos son consistentes
        return obtenerAlturaNegraValida(this.raiz) != -1;
    }

    /**
     * Método recursivo que calcula la altura negra de cada subárbol.
     * Retorna la altura negra del nodo si sus caminos son uniformes, o -1 si detecta desbalance.
     */
    private int obtenerAlturaNegraValida(NodoRojinegro nodo) {
        // Caso Base: Hoja centinela (null/NIL) es NEGRA por definición -> Aporta +1
        if (nodo == null) {
            return 1;
        }

        // Llamadas recursivas para obtener la altura negra de ambos subárboles
        int alturaNegraIzquierda = obtenerAlturaNegraValida(nodo.izquierdo);
        int alturaNegraDerecha = obtenerAlturaNegraValida(nodo.derecho);

        // Si alguna rama previa falló o si las dos ramas actuales difieren en su número de nodos negros
        if (alturaNegraIzquierda == -1 || alturaNegraDerecha == -1 || alturaNegraIzquierda != alturaNegraDerecha) {
            return -1; // Propaga la falla hacia arriba
        }

        // Si son iguales, suma +1 sólo si el nodo actual es NEGRO
        int aporteActual = (nodo.color == Color.NEGRO) ? 1 : 0;
        return alturaNegraIzquierda + aporteActual;
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBA: VALIDAR ALTURA NEGRA UNIFORME EN TODOS LOS CAMINOS ===");

        // --- CASO DE PRUEBA 1: Árbol Válido (Todos los caminos tienen exactamente 2 nodos negros) ---
        //                20 (NEGRO)
        //             /              \
        //        10 (ROJO)         30 (NEGRO)
        //        /      \          /        \
        //   5 (NEGRO) 15 (NEGRO) NIL(NEGRO) NIL(NEGRO)
        //
        // Caminos desde la raíz:
        // - 20 -> 10 -> 5  -> NIL (Negros: 20, 5, NIL = 3)
        // - 20 -> 10 -> 15 -> NIL (Negros: 20, 15, NIL = 3)
        // - 20 -> 30 -> NIL       (Negros: 20, 30, NIL = 3)

        ValidarAlturaNegraCompleta arbolValido = new ValidarAlturaNegraCompleta();
        arbolValido.raiz = new NodoRojinegro(20, Color.NEGRO);

        // Subárbol Izquierdo
        arbolValido.raiz.izquierdo = new NodoRojinegro(10, Color.ROJO);
        arbolValido.raiz.izquierdo.izquierdo = new NodoRojinegro(5, Color.NEGRO);
        arbolValido.raiz.izquierdo.derecho = new NodoRojinegro(15, Color.NEGRO);

        // Subárbol Derecho
        arbolValido.raiz.derecho = new NodoRojinegro(30, Color.NEGRO);

        System.out.println("1. Evaluando Árbol Válido:");
        System.out.println("   ¿Todos los caminos tienen la misma altura negra?: " + arbolValido.mismaAlturaNegra());

        System.out.println("\n------------------------------------------------\n");

        // --- CASO DE PRUEBA 2: Árbol Inválido (Un camino tiene un nodo negro extra) ---
        //                20 (NEGRO)
        //             /              \
        //        10 (ROJO)         30 (NEGRO)
        //        /      \                   \
        //   5 (NEGRO) 15 (NEGRO)          40 (NEGRO) -> ¡Desbalance!
        ValidarAlturaNegraCompleta arbolInvalido = new ValidarAlturaNegraCompleta();
        arbolInvalido.raiz = new NodoRojinegro(20, Color.NEGRO);

        arbolInvalido.raiz.izquierdo = new NodoRojinegro(10, Color.ROJO);
        arbolInvalido.raiz.izquierdo.izquierdo = new NodoRojinegro(5, Color.NEGRO);
        arbolInvalido.raiz.izquierdo.derecho = new NodoRojinegro(15, Color.NEGRO);

        arbolInvalido.raiz.derecho = new NodoRojinegro(30, Color.NEGRO);
        arbolInvalido.raiz.derecho.derecho = new NodoRojinegro(40, Color.NEGRO); // Agrega un nodo negro extra en esta rama

        System.out.println("2. Evaluando Árbol Inválido:");
        System.out.println("   ¿Todos los caminos tienen la misma altura negra?: " + arbolInvalido.mismaAlturaNegra());
    }
}
