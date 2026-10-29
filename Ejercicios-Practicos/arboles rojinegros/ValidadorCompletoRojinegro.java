/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java dentro de la clase 'ArbolRojinegro' un método integrador 'boolean esRojinegroValido()' que consolide 
 * la validación de un Árbol Rojinegro (Red-Black Tree).
 * El método debe verificar de manera conjunta las 4 propiedades indispensables:
 * 1. Propiedad de la Raíz: Que la raíz sea de color NEGRO (o que el árbol esté vacío).
 * 2. Propiedad de Rojos Consecutivos: Que ningún nodo de color ROJO tenga un hijo de color ROJO.
 * 3. Propiedad de Altura Negra Uniforme: Que todos los caminos desde la raíz hasta las hojas centinela (NIL/null) 
 *    posean exactamente la misma cantidad de nodos NEGROS.
 * 4. Propiedad de Árbol Binario de Búsqueda (ABB): Que el árbol mantenga el ordenamiento estricto (izquierdo < nodo < derecho).
 * 
 * Explica en los comentarios:
 * - La arquitectura del validador y cómo se coordinan las submétricas.
 * - Cómo se valida recursivamente la propiedad ABB usando límites dinámicos (mínimo y máximo).
 * - Cómo se prueba por consola mediante casos donde el árbol sea totalmente válido y casos donde falle individualmente 
 *   por cada una de las 4 condiciones.
 * 
 * Incluye las secciones de auditoría 'Prompt inicial utilizado' y 'Ajustes realizados al prompt'."
 * 
 * AJUSTES REALIZADOS AL PROMPT:
 * - Se integraron los métodos auxiliares creados en los ejercicios anteriores en una única clase funcional autocontenida.
 * - Se incorporaron mensajes de diagnóstico específicos por consola cuando alguna de las validaciones falla, facilitando 
 *   la identificación de la propiedad violada.
 * 
 * ARQUITECTURA DEL VALIDADOR COMPLETO:
 * El método 'esRojinegroValido()' actúa como orquestador ejecutando secuencialmente:
 * 1. `raizEsNegra()`: Evaluación en $O(1)$ de la raíz.
 * 2. `esABB()`: Verificación en $O(N)$ usando cotas inferiores y superiores (`MIN_VALUE` / `MAX_VALUE`).
 * 3. `noHayRojosConsecutivos()`: Recorrido en $O(N)$ verificando que si $nodo.color == ROJO \implies hijos == NEGROS$.
 * 4. `mismaAlturaNegra()`: Cálculo Bottom-Up en $O(N)$ verificando la uniformidad de la altura negra ($bh$).
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

public class ValidadorCompletoRojinegro {
    private NodoRojinegro raiz;

    public ValidadorCompletoRojinegro() {
        this.raiz = null;
    }

    // ==========================================
    // 1. VALIDACIÓN: RAÍZ NEGRA
    // ==========================================
    public boolean raizEsNegra() {
        if (raiz == null) return true;
        if (raiz.color != Color.NEGRO) {
            System.out.println("  [FALLO PROPIEDAD 1] La raíz (" + raiz.valor + ") es de color ROJO.");
            return false;
        }
        return true;
    }

    // ==========================================
    // 2. VALIDACIÓN: PROPIEDAD ABB
    // ==========================================
    public boolean esABB() {
        return esABBRecursivo(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean esABBRecursivo(NodoRojinegro nodo, long min, long max) {
        if (nodo == null) return true;

        if (nodo.valor <= min || nodo.valor >= max) {
            System.out.println("  [FALLO PROPIEDAD ABB] El nodo " + nodo.valor + " viola el ordenamiento ABB.");
            return false;
        }

        return esABBRecursivo(nodo.izquierdo, min, nodo.valor) &&
               esABBRecursivo(nodo.derecho, nodo.valor, max);
    }

    // ==========================================
    // 3. VALIDACIÓN: SIN ROJOS CONSECUTIVOS
    // ==========================================
    public boolean noHayRojosConsecutivos() {
        return noHayRojosConsecutivosRecursivo(raiz);
    }

    private boolean noHayRojosConsecutivosRecursivo(NodoRojinegro nodo) {
        if (nodo == null) return true;

        if (nodo.color == Color.ROJO) {
            if (nodo.izquierdo != null && nodo.izquierdo.color == Color.ROJO) {
                System.out.println("  [FALLO PROPIEDAD ROJOS CONSECUTIVOS] Nodo ROJO " + nodo.valor + " tiene hijo izquierdo ROJO (" + nodo.izquierdo.valor + ").");
                return false;
            }
            if (nodo.derecho != null && nodo.derecho.color == Color.ROJO) {
                System.out.println("  [FALLO PROPIEDAD ROJOS CONSECUTIVOS] Nodo ROJO " + nodo.valor + " tiene hijo derecho ROJO (" + nodo.derecho.valor + ").");
                return false;
            }
        }

        return noHayRojosConsecutivosRecursivo(nodo.izquierdo) &&
               noHayRojosConsecutivosRecursivo(nodo.derecho);
    }

    // ==========================================
    // 4. VALIDACIÓN: ALTURA NEGRA UNIFORME
    // ==========================================
    public boolean mismaAlturaNegra() {
        return obtenerAlturaNegra(raiz) != -1;
    }

    private int obtenerAlturaNegra(NodoRojinegro nodo) {
        if (nodo == null) {
            return 1; // Hojas centinela NIL son de color NEGRO por definición
        }

        int altIzq = obtenerAlturaNegra(nodo.izquierdo);
        int altDer = obtenerAlturaNegra(nodo.derecho);

        if (altIzq == -1 || altDer == -1 || altIzq != altDer) {
            return -1; // Propaga el error de desbalance en la altura negra
        }

        int aporte = (nodo.color == Color.NEGRO) ? 1 : 0;
        return altIzq + aporte;
    }

    // ==========================================
    // MÉTODO MAESTRO INTEGRADOR
    // ==========================================
    /**
     * Valida de forma integral si la estructura cumple las 4 propiedades fundamentales del Árbol Rojinegro.
     */
    public boolean esRojinegroValido() {
        System.out.println("--- Iniciando Auditoría de Árbol Rojinegro ---");
        
        boolean v1 = raizEsNegra();
        boolean v2 = esABB();
        boolean v3 = noHayRojosConsecutivos();
        boolean v4 = mismaAlturaNegra();

        if (!v4) {
            System.out.println("  [FALLO PROPIEDAD ALTURA NEGRA] Los caminos hacia las hojas tienen distinta cantidad de nodos negros.");
        }

        boolean esValido = v1 && v2 && v3 && v4;
        System.out.println(">>> RESULTADO FINAL: " + (esValido ? "ÁRBOLES ROJINEGRO VÁLIDO [OK]" : "ÁRBOLES ROJINEGRO INVÁLIDO [FAIL]") + " <<<\n");
        return esValido;
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DEL VALIDADOR COMPLETO DE ÁRBOL ROJINEGRO ===\n");

        // --- PRUEBA 1: ÁRBOLES ROJINEGRO PERFECTAMENTE VÁLIDO ---
        //                20 (NEGRO)
        //             /              \
        //        10 (ROJO)         30 (ROJO)
        //        /      \          /        \
        //   5 (NEGRO) 15 (NEGRO) 25(NEGRO) 35(NEGRO)
        ValidadorCompletoRojinegro arbolValido = new ValidadorCompletoRojinegro();
        arbolValido.raiz = new NodoRojinegro(20, Color.NEGRO);

        arbolValido.raiz.izquierdo = new NodoRojinegro(10, Color.ROJO);
        arbolValido.raiz.izquierdo.izquierdo = new NodoRojinegro(5, Color.NEGRO);
        arbolValido.raiz.izquierdo.derecho = new NodoRojinegro(15, Color.NEGRO);

        arbolValido.raiz.derecho = new NodoRojinegro(30, Color.ROJO);
        arbolValido.raiz.derecho.izquierdo = new NodoRojinegro(25, Color.NEGRO);
        arbolValido.raiz.derecho.derecho = new NodoRojinegro(35, Color.NEGRO);

        System.out.println("1. CASO 1: Árbol con todas las propiedades cumplidas:");
        arbolValido.esRojinegroValido();

        // --- PRUEBA 2: FALLO POR RAÍZ ROJA ---
        ValidadorCompletoRojinegro arbolRaizRoja = new ValidadorCompletoRojinegro();
        arbolRaizRoja.raiz = new NodoRojinegro(20, Color.ROJO); // ¡Fallo!
        System.out.println("2. CASO 2: Violación de Raíz Negra:");
        arbolRaizRoja.esRojinegroValido();

        // --- PRUEBA 3: FALLO POR ORDEN ABB ---
        ValidadorCompletoRojinegro arbolFalloABB = new ValidadorCompletoRojinegro();
        arbolFalloABB.raiz = new NodoRojinegro(20, Color.NEGRO);
        arbolFalloABB.raiz.izquierdo = new NodoRojinegro(50, Color.ROJO); // ¡Fallo! 50 no puede estar a la izquierda de 20
        System.out.println("3. CASO 3: Violación de propiedad ABB:");
        arbolFalloABB.esRojinegroValido();

        // --- PRUEBA 4: FALLO POR ROJOS CONSECUTIVOS ---
        ValidadorCompletoRojinegro arbolRojosJuntos = new ValidadorCompletoRojinegro();
        arbolRojosJuntos.raiz = new NodoRojinegro(20, Color.NEGRO);
        arbolRojosJuntos.raiz.izquierdo = new NodoRojinegro(10, Color.ROJO);
        arbolRojosJuntos.raiz.izquierdo.izquierdo = new NodoRojinegro(5, Color.ROJO); // ¡Fallo! Padre e hijo rojos
        System.out.println("4. CASO 4: Violación de Rojos Consecutivos:");
        arbolRojosJuntos.esRojinegroValido();

        // --- PRUEBA 5: FALLO POR ALTURA NEGRA DESIGUAL ---
        ValidadorCompletoRojinegro arbolAlturaDesigual = new ValidadorCompletoRojinegro();
        arbolAlturaDesigual.raiz = new NodoRojinegro(20, Color.NEGRO);
        arbolAlturaDesigual.raiz.izquierdo = new NodoRojinegro(10, Color.NEGRO); // Aporta nodo negro extra a la izquierda
        arbolAlturaDesigual.raiz.derecho = new NodoRojinegro(30, Color.ROJO);
        System.out.println("5. CASO 5: Violación de Altura Negra Uniforme:");
        arbolAlturaDesigual.esRojinegroValido();
    }
}
