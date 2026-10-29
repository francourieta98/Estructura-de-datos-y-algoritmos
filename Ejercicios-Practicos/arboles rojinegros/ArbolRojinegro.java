/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java la clase 'ArbolRojinegro' que contenga el atributo 'raiz' (de tipo NodoRojinegro) 
 * y el método 'boolean raizEsNegra()'.
 * 
 * Explica en los comentarios:
 * - Qué estructura se necesita implementar y qué propiedad del Árbol Rojinegro se está validando.
 * - Por qué un árbol vacío (donde raiz == null o apunta al centinela NIL) debe retornar 'true'.
 * - Cómo se simula la inserción de un elemento respetando que la raíz nade roja y luego sea coloreada a negra.
 * - Cómo se validará por consola con pruebas de árbol vacío, raíz roja (inválida) y raíz negra (válida).
 * 
 * Incluye la auditoría con 'Prompt inicial utilizado' y 'Ajustes realizados al prompt'."
 * 
 * AJUSTES REALIZADOS AL PROMPT:
 * - Se especificó el tratamiento del caso borde donde la raíz es 'null' para retornar 'true' de acuerdo a la consigna.
 * - Se agregó un método auxiliar 'insertarRaizPrueba(int valor, Color color)' para simular estados de prueba sin implementar aún todo el rebalanceo de inserción.
 * 
 * JUSTIFICACIÓN TÉCNICA:
 * PROPIEDAD ROJINEGRA EVALUADA:
 * "La raíz de todo Árbol Rojinegro debe ser siempre de color NEGRO."
 * 
 * 1. ¿POR QUÉ UN ÁRBOL VACÍO RETORNA TRUE?:
 *    En la teoría de Árboles Rojinegros, los nodos nulos o de límite se consideran hojas centinela (NIL) y son, por definición, de color NEGRO. 
 *    Por consiguiente, un árbol vacío no infringe ninguna regla del árbol rojinegro, manteniendo la propiedad de manera vacua (vacuously true).
 * 
 * 2. CORRECCIÓN DE LA RAÍZ AL INSERTAR:
 *    Cuando se inserta el primer elemento en un Árbol Rojinegro, inicialmente ingresa como un nodo ROJO. El algoritmo de balanceo inmediatamente 
 *    detecta que es la raíz y cambia su color a NEGRO para asegurar el cumplimiento de esta regla fundamental.
 */

// Enumeración de Colores
enum Color {
    ROJO,
    NEGRO
}

// Clase NodoRojinegro
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

// Clase principal ArbolRojinegro
public class ArbolRojinegro {
    private NodoRojinegro raiz;

    public ArbolRojinegro() {
        this.raiz = null; // El árbol inicia vacío
    }

    /**
     * Valida si la raíz del árbol cumple con la propiedad de ser NEGRA.
     * @return true si la raíz es NEGRA o si el árbol está vacío; false si la raíz es ROJA.
     */
    public boolean raizEsNegra() {
        // Caso 1: Árbol vacío -> Por definición las hojas centinela NIL son negras.
        if (raiz == null) {
            return true;
        }

        // Caso 2: Verificar el color del nodo raíz
        return raiz.color == Color.NEGRO;
    }

    /**
     * Método auxiliar de prueba para asignar manualmente una raíz con un color específico.
     */
    public void establecerRaizParaPrueba(int valor, Color color) {
        this.raiz = new NodoRojinegro(valor, color);
    }

    public static void main(String[] args) {
        ArbolRojinegro arbol = new ArbolRojinegro();

        System.out.println("=== VALIDACIÓN DE PROPIEDAD: RAÍZ NEGRA ===");

        // ESCENARIO 1: Árbol Vacío
        System.out.println("1. Árbol Vacío:");
        System.out.println("   ¿La raíz es negra/válida?: " + arbol.raizEsNegra()); // Esperado: true

        System.out.println("\n-------------------------------------------\n");

        // ESCENARIO 2: Raíz asignada con color ROJO (Estado inválido violando reglas)
        arbol.establecerRaizParaPrueba(10, Color.ROJO);
        System.out.println("2. Árbol con raíz ROJA (Inválido):");
        System.out.println("   ¿La raíz es negra/válida?: " + arbol.raizEsNegra()); // Esperado: false

        System.out.println("\n-------------------------------------------\n");

        // ESCENARIO 3: Raíz corregida a color NEGRO (Estado válido)
        arbol.establecerRaizParaPrueba(10, Color.NEGRO);
        System.out.println("3. Árbol con raíz NEGRA (Válido):");
        System.out.println("   ¿La raíz es negra/válida?: " + arbol.raizEsNegra()); // Esperado: true
    }
}
