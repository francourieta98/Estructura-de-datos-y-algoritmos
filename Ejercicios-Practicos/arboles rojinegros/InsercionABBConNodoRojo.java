/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java dentro de la clase 'ArbolRojinegro' el método 'void insertarComoABB(int valor)' que inserte un nuevo 
 * elemento respetando las reglas de un Árbol Binario de Búsqueda (ABB) estándar, pero adaptado a la estructura de Árbol Rojinegro.
 * Explica en los comentarios:
 * - Qué estructura se necesita implementar y qué propiedades se ven involucradas durante la inserción.
 * - Por qué todo nuevo nodo NACE SIEMPRE DE COLOR ROJO (por qué es preferible arriesgarse a violar la propiedad de 'no rojos consecutivos' 
 *   en lugar de romper inmediatamente la propiedad de 'misma altura negra en todos los caminos').
 * - Por qué si el nodo insertado resulta ser la raíz del árbol, debe cambiarse su color inmediatamente a NEGRO.
 * - Cómo se manejan las referencias al 'padre' durante el descenso y enlace del nuevo nodo.
 * Muestra por consola la inserción progresiva de valores verificando los colores iniciales asignados y el estado de la raíz."
 * 
 * AJUSTES REALIZADOS AL PROMPT:
 * - Se implementó un algoritmo de inserción iterativo con puntero 'padre' auxiliar para garantizar la correcta asignación 
 *   de las referencias bidireccionales (`nuevoNodo.padre = padreActual` y `padreActual.izquierdo/derecho = nuevoNodo`).
 * - Se omitieron las rotaciones y recoloraciones complejas de balanceo para enfocar la lección exclusivamente en la asignación de color inicial.
 * 
 * JUSTIFICACIÓN TÉCNICA:
 * ¿POR QUÉ EL NUEVO NODO NACE SIEMPRE ROJO?
 * 1. PRESERVACIÓN DE LA ALTURA NEGRA (PROPIEDAD 5):
 *    La propiedad de la altura negra uniforme (que todos los caminos desde la raíz a las hojas centinela NIL tengan el mismo número de nodos negros) 
 *    es la regla más global, compleja y costosa de reparar si se llega a romper. 
 *    Al insertar un nuevo nodo de color **ROJO**, la cantidad de nodos negros en ese camino **NO cambia**, garantizando de entrada que la altura 
 *    negra de todos los caminos se mantenga 100% intacta.
 * 
 * 2. VIOLACIÓN LOCAL VS. VIOLACIÓN GLOBAL:
 *    Si el nuevo nodo se inserta bajo un padre NEGRO, ¡no se rompe ninguna regla del árbol!
 *    Si se inserta bajo un padre ROJO, únicamente se genera una violación **local** (propiedad de no rojos consecutivos), la cual se puede reparar 
 *    fácilmente mediante recoloraciones locales y/o rotaciones sin afectar la estructura global de altura negra.
 * 
 * 3. EXCEPCIÓN DE LA RAÍZ:
 *    Si el árbol estaba vacío, el nuevo nodo se convierte en la raíz. Como la Propiedad 2 exige que la raíz sea siempre NEGRA, 
 *    se cambia inmediatamente su color de ROJO a NEGRO. Esto incrementa la altura negra de todo el árbol de forma homogénea en +1.
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
        this.color = Color.ROJO; // Todo nodo nace ROJO por defecto
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}

public class InsercionABBConNodoRojo {
    private NodoRojinegro raiz;

    public InsercionABBConNodoRojo() {
        this.raiz = null;
    }

    /**
     * Inserción básica de ABB asignando color ROJO y corrigiendo la raíz si es necesario.
     */
    public void insertarComoABB(int valor) {
        NodoRojinegro nuevoNodo = new NodoRojinegro(valor); // Nace de color ROJO

        NodoRojinegro padreActual = null;
        NodoRojinegro actual = this.raiz;

        // 1. Navegación en el árbol para encontrar la posición de inserción ABB
        while (actual != null) {
            padreActual = actual;
            if (valor < actual.valor) {
                actual = actual.izquierdo;
            } else if (valor > actual.valor) {
                actual = actual.derecho;
            } else {
                System.out.println("  [Aviso] El valor " + valor + " ya existe. No se permiten duplicados.");
                return;
            }
        }

        // 2. Enlace con el nodo padre
        nuevoNodo.padre = padreActual;

        // 3. Posicionamiento como hijo izquierdo o derecho
        if (padreActual == null) {
            // CASO ESPECIAL: El árbol estaba vacío, el nuevo nodo es la RAÍZ
            this.raiz = nuevoNodo;
            this.raiz.color = Color.NEGRO; // Regla: La raíz debe ser NEGRA
            System.out.println("-> Insertado " + valor + " como RAÍZ (Color forzado a NEGRO).");
        } else if (valor < padreActual.valor) {
            padreActual.izquierdo = nuevoNodo;
            System.out.println("-> Insertado " + valor + " como hijo Izquierdo de " + padreActual.valor + " (Color: ROJO).");
        } else {
            padreActual.derecho = nuevoNodo;
            System.out.println("-> Insertado " + valor + " como hijo Derecho de " + padreActual.valor + " (Color: ROJO).");
        }
    }

    /**
     * Muestra el estado actual del árbol mediante un recorrido inorden.
     */
    public void mostrarInorden() {
        System.out.print("Estado del Árbol (Inorden) [Valor(Color, Padre)]: ");
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
        InsercionABBConNodoRojo arbol = new InsercionABBConNodoRojo();

        System.out.println("=== PRUEBA DE INSERCIÓN BÁSICA ABB CON NODO ROJO ===\n");

        // Secuencia de inserciones
        int[] valores = {20, 10, 30, 5, 15};

        for (int v : valores) {
            arbol.insertarComoABB(v);
        }

        System.out.println("\n=== VERIFICACIÓN DE ESTRUCTURA Y COLORES ===");
        arbol.mostrarInorden();
    }
}
