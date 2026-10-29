/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java dentro de la clase 'ArbolRojinegro' el método 'boolean noHayRojosConsecutivos()' que recorra el árbol recursivamente.
 * Explica en los comentarios:
 * - Qué estructura se necesita implementar y qué propiedad del Árbol Rojinegro se está validando.
 * - Cómo se detecta la violación de la Propiedad (Ningún nodo rojo puede tener un hijo rojo).
 * - Cómo se realiza la navegación recursiva evaluando simultáneamente las ramas izquierda y derecha.
 * - Cómo se probará por consola mediante casos de prueba con árboles válidos e inválidos.
 * 
 * Incluye las secciones de auditoría 'Prompt inicial utilizado' y 'Ajustes realizados al prompt'."
 * 
 * AJUSTES REALIZADOS AL PROMPT:
 * - Se diseñó el algoritmo para que un nodo ROJO evalúe activamente a sus dos hijos (izquierdo y derecho) antes de descender recursivamente.
 * - Se agregaron métodos de construcción manual de estructuras para simular árboles válidos e inválidos.
 * 
 * JUSTIFICACIÓN TÉCNICA:
 * PROPIEDAD ROJINEGRA EVALUADA:
 * "Si un nodo es ROJO, ambos hijos deben ser estrictamente NEGROS (o centinelas NIL)."
 * En otras palabras: No pueden existir dos nodos de color ROJO consecutivos en ninguna relación padre-hijo.
 * 
 * ALGORITMO DE DETECCIÓN RECURSIVA:
 * 1. Caso Base: Si el nodo evaluado es `null` (hoja centinela NIL), por definición es NEGRO y no genera violación. Retorna `true`.
 * 2. Evaluación de Violación: Si el nodo actual es de color `ROJO`:
 *    - Se verifica su hijo izquierdo: si no es `null` y es de color `ROJO`, existe una violación de rojos consecutivos.
 *    - Se verifica su hijo derecho: si no es `null` y es de color `ROJO`, existe una violación de rojos consecutivos.
 * 3. Paso Recursivo: Se evalúan ambas ramas: `noHayRojosConsecutivos(nodo.izquierdo) && noHayRojosConsecutivos(nodo.derecho)`.
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

public class ValidarRojosConsecutivos {
    private NodoRojinegro raiz;

    public ValidarRojosConsecutivos() {
        this.raiz = null;
    }

    /**
     * Método público para verificar si no existen dos rojos consecutivos en todo el árbol.
     */
    public boolean noHayRojosConsecutivos() {
        return validarNoRojosConsecutivos(this.raiz);
    }

    /**
     * Método privado recursivo que recorre cada nodo y valida la propiedad.
     */
    private boolean validarNoRojosConsecutivos(NodoRojinegro nodo) {
        // Caso Base: Un nodo nulo (centinela NIL) no produce violaciones
        if (nodo == null) {
            return true;
        }

        // Si el nodo actual es ROJO, ninguno de sus hijos inmediatos puede ser ROJO
        if (nodo.color == Color.ROJO) {
            if (nodo.izquierdo != null && nodo.izquierdo.color == Color.ROJO) {
                System.out.println("  [Violación Detectada] El nodo ROJO (" + nodo.valor + 
                                   ") tiene un hijo izquierdo ROJO (" + nodo.izquierdo.valor + ").");
                return false;
            }
            if (nodo.derecho != null && nodo.derecho.color == Color.ROJO) {
                System.out.println("  [Violación Detectada] El nodo ROJO (" + nodo.valor + 
                                   ") tiene un hijo derecho ROJO (" + nodo.derecho.valor + ").");
                return false;
            }
        }

        // Verificación recursiva en el subárbol izquierdo y derecho
        return validarNoRojosConsecutivos(nodo.izquierdo) && validarNoRojosConsecutivos(nodo.derecho);
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBA: VERIFICAR QUE NO EXISTAN DOS ROJOS CONSECUTIVOS ===");

        // --- CASO DE PRUEBA 1: Árbol Válido ---
        //        20 (NEGRO)
        //       /   \
        //   10 (ROJO) 30 (ROJO)
        //   /
        // 5 (NEGRO)
        ValidarRojosConsecutivos arbolValido = new ValidarRojosConsecutivos();
        arbolValido.raiz = new NodoRojinegro(20, Color.NEGRO);
        
        arbolValido.raiz.izquierdo = new NodoRojinegro(10, Color.ROJO);
        arbolValido.raiz.izquierdo.padre = arbolValido.raiz;

        arbolValido.raiz.derecho = new NodoRojinegro(30, Color.ROJO);
        arbolValido.raiz.derecho.padre = arbolValido.raiz;

        arbolValido.raiz.izquierdo.izquierdo = new NodoRojinegro(5, Color.NEGRO);
        arbolValido.raiz.izquierdo.izquierdo.padre = arbolValido.raiz.izquierdo;

        System.out.println("1. Evaluando Árbol Válido:");
        System.out.println("   ¿Cumple propiedad 'Sin Rojos Consecutivos'?: " + arbolValido.noHayRojosConsecutivos());

        System.out.println("\n------------------------------------------------\n");

        // --- CASO DE PRUEBA 2: Árbol Inválido (Padre Rojo e Hijo Rojo) ---
        //        20 (NEGRO)
        //       /
        //   10 (ROJO)
        //   /
        // 5 (ROJO) -> ¡VIOLACIÓN!
        ValidarRojosConsecutivos arbolInvalido = new ValidarRojosConsecutivos();
        arbolInvalido.raiz = new NodoRojinegro(20, Color.NEGRO);

        arbolInvalido.raiz.izquierdo = new NodoRojinegro(10, Color.ROJO);
        arbolInvalido.raiz.izquierdo.padre = arbolInvalido.raiz;

        arbolInvalido.raiz.izquierdo.izquierdo = new NodoRojinegro(5, Color.ROJO); // Violación
        arbolInvalido.raiz.izquierdo.izquierdo.padre = arbolInvalido.raiz.izquierdo;

        System.out.println("2. Evaluando Árbol Inválido:");
        System.out.println("   ¿Cumple propiedad 'Sin Rojos Consecutivos'?: " + arbolInvalido.noHayRojosConsecutivos());
    }
}
