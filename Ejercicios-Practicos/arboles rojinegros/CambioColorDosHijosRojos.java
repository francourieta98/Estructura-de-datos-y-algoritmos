/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java dentro de la clase 'ArbolRojinegro' el método 'void cambiarColorSiTieneDosHijosRojos(NodoRojinegro nodo)' 
 * que realice la operación de recoloración (push-down de color negro / split de nodo 2-3-4).
 * Explica en los comentarios:
 * - Qué estructura se necesita implementar y qué caso de balanceo del Árbol Rojinegro representa (Caso donde el 'tío' es ROJO).
 * - Las reglas de recoloración: transformar al nodo en ROJO y a sus dos hijos en NEGROS.
 * - Por qué se debe tratar a la raíz como un caso especial (si el nodo recoloreado a rojo resulta ser la raíz, 
 *   debe forzarse nuevamente a NEGRO para preservar la Propiedad 2).
 * - Cómo esta operación preserva perfectamente la altura negra de los subárboles.
 * Muestra por consola pruebas evaluando la recoloración en nodos internos y en la raíz del árbol."
 * 
 * AJUSTES REALIZADOS AL PROMPT:
 * - Se añadieron verificaciones estrictas de seguridad (evitando `NullPointerException` si los hijos son nulos) 
 *   antes de consultar su color.
 * - Se implementó la garantía explícita de que la raíz permanezca siempre NEGRA tras la recoloración.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * ¿CUÁNDO SE APLICA Y CÓMO FUNCIONA EL CAMBIO DE COLOR?
 * 
 * 1. REPRESENTACIÓN DEL CASO "TÍO ROJO":
 *    Durante la inserción en un Árbol Rojinegro, cuando un nodo nuevo (ROJO) tiene un padre ROJO y el hermano de su padre (su "tío") 
 *    también es ROJO, la solución no requiere rotaciones. Se resuelve "empujando" el color negro del abuelo hacia sus dos hijos.
 * 
 * 2. REGLA DE RECOLORACIÓN:
 *    - El nodo abuelo (o padre de los dos hijos rojos) pasa a ser ROJO.
 *    - Sus dos hijos (el padre y el tío del nuevo nodo) pasan a ser NEGROS.
 * 
 * 3. PRESERVACIÓN DE LA ALTURA NEGRA:
 *    Antes del cambio: El camino hacia cada subárbol pasaba por 1 nodo negro (el abuelo) y 0 en los hijos.
 *    Después del cambio: El camino hacia cada subárbol pasa por 0 nodos negros en el abuelo y 1 en el hijo correspondiente.
 *    ¡La suma total de nodos negros hacia las hojas no cambia en ningún camino!
 * 
 * 4. EL CASO ESPECIAL DE LA RAÍZ:
 *    Si el nodo que recoloreamos a ROJO es la **raíz del árbol**, estaríamos violando la Propiedad 2 ("La raíz debe ser negra"). 
 *    Por esta razón, tras realizar la recoloración, se verifica si `nodo == raiz`; de ser así, la raíz se fuerza a ser **NEGRA**.
 *    Este evento es el único mecanismo por el cual la altura negra ($bh$) de todo el árbol se incrementa en +1.
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

public class CambioColorDosHijosRojos {
    private NodoRojinegro raiz;

    public CambioColorDosHijosRojos() {
        this.raiz = null;
    }

    /**
     * Aplica la recoloración si el nodo posee dos hijos estrictamente de color ROJO.
     */
    public void cambiarColorSiTieneDosHijosRojos(NodoRojinegro nodo) {
        if (nodo == null) return;

        // Verificación de existencia y color de ambos hijos
        boolean tieneHijoIzqRojo = (nodo.izquierdo != null && nodo.izquierdo.color == Color.ROJO);
        boolean tieneHijoDerRojo = (nodo.derecho != null && nodo.derecho.color == Color.ROJO);

        if (tieneHijoIzqRojo && tieneHijoDerRojo) {
            System.out.println("  [!] Aplicando cambio de color en el nodo: " + nodo.valor);
            
            // 1. El nodo pasa a ser ROJO
            nodo.color = Color.ROJO;

            // 2. Ambos hijos pasan a ser NEGROS
            nodo.izquierdo.color = Color.NEGRO;
            nodo.derecho.color = Color.NEGRO;

            // 3. CASO ESPECIAL: Si el nodo es la raíz, debe permanecer NEGRA
            if (nodo == this.raiz) {
                System.out.println("  [*] El nodo recoloreado es la RAÍZ -> Se reestablece su color a NEGRO.");
                this.raiz.color = Color.NEGRO;
            }
        } else {
            System.out.println("  [i] El nodo " + nodo.valor + " no cumple la condición de tener dos hijos rojos.");
        }
    }

    public void mostrarInorden() {
        System.out.print("Estado del Árbol [Valor(Color)]: ");
        inordenRecursivo(this.raiz);
        System.out.println("FIN");
    }

    private void inordenRecursivo(NodoRojinegro nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + "(" + nodo.color + ") -> ");
            inordenRecursivo(nodo.derecho);
        }
    }

    public static void main(String[] args) {
        CambioColorDosHijosRojos arbol = new CambioColorDosHijosRojos();

        System.out.println("=== PRUEBA DE CAMBIO DE COLOR CON DOS HIJOS ROJOS ===\n");

        // --- PRUEBA 1: Recoloración en la Raíz ---
        //          20 (NEGRO) [Raíz]
        //         /   \
        //     10(ROJO) 30(ROJO)
        arbol.raiz = new NodoRojinegro(20, Color.NEGRO);
        arbol.raiz.izquierdo = new NodoRojinegro(10, Color.ROJO);
        arbol.raiz.derecho = new NodoRojinegro(30, Color.ROJO);

        System.out.println("1. Estado Inicial (Raíz 20 Negra con dos hijos Rojos):");
        arbol.mostrarInorden();

        System.out.println("\nEjecutando cambiarColorSiTieneDosHijosRojos en la raíz (20)...");
        arbol.cambiarColorSiTieneDosHijosRojos(arbol.raiz);

        System.out.println("Estado Tras Cambio:");
        arbol.mostrarInorden(); // La raíz vuelve a quedar Negra y los hijos pasan a ser Negros

        System.out.println("\n----------------------------------------------------\n");

        // --- PRUEBA 2: Recoloración en un Nodo Interno ---
        //               50 (NEGRO) [Raíz]
        //              /
        //          20 (NEGRO)
        //         /   \
        //     10(ROJO) 30(ROJO)
        CambioColorDosHijosRojos arbolInterno = new CambioColorDosHijosRojos();
        arbolInterno.raiz = new NodoRojinegro(50, Color.NEGRO);
        
        arbolInterno.raiz.izquierdo = new NodoRojinegro(20, Color.NEGRO);
        arbolInterno.raiz.izquierdo.padre = arbolInterno.raiz;

        arbolInterno.raiz.izquierdo.izquierdo = new NodoRojinegro(10, Color.ROJO);
        arbolInterno.raiz.izquierdo.derecho = new NodoRojinegro(30, Color.ROJO);

        System.out.println("2. Estado Inicial (Nodo Interno 20 Negro con dos hijos Rojos):");
        arbolInterno.mostrarInorden();

        System.out.println("\nEjecutando cambiarColorSiTieneDosHijosRojos en el nodo interno (20)...");
        arbolInterno.cambiarColorSiTieneDosHijosRojos(arbolInterno.raiz.izquierdo);

        System.out.println("Estado Tras Cambio:");
        arbolInterno.mostrarInorden(); // El nodo 20 pasa a ser ROJO y sus hijos 10 y 30 a NEGROS
    }
}
