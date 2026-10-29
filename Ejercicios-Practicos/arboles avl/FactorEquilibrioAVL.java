/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un método 'obtenerFactorEquilibrio' o 'obtenerBalance' que calcule el factor de balance 
 * de un nodo en un Árbol AVL mediante la fórmula: 'alturaDerecha - alturaIzquierda' (o 'alturaIzquierda - alturaDerecha').
 * Explica en los comentarios:
 * - Por qué los únicos valores válidos en un Árbol AVL para mantener el equilibrio son -1, 0 y 1.
 * - Qué significa exactamente un valor de +2 o -2 y por qué indica que la estructura requiere una rotación.
 * Muestra pruebas en consola calculando el factor de equilibrio para nodos con distintos niveles de balance."
 * 
 * TEORÍA DEL FACTOR DE EQUILIBRIO EN ÁRBOLES AVL:
 * 1. FORMULA UTILIZADA:
 *    $FactorDeBalance (FB) = Altura(Subárbol Derecho) - Altura(Subárbol Izquierdo)$
 * 
 * 2. VALORES VÁLIDOS Y SIGNIFIADO (-1, 0, 1):
 *    Un Árbol AVL es un Árbol Binario de Búsqueda Auto-balanceado. Su propiedad fundamental establece 
 *    que la diferencia de altura entre las ramas de cualquier nodo no debe ser superior a 1.
 *    - $FB = 0$: El nodo está perfectamente equilibrado (ambos subárboles tienen la misma altura).
 *    - $FB = -1$: El subárbol izquierdo es 1 nivel más alto que el derecho (ligera inclinación a la izquierda).
 *    - $FB = +1$: El subárbol derecho es 1 nivel más alto que el izquierdo (ligera inclinación a la derecha).
 * 
 * 3. VALORES DE DESBALANCE (+2 o -2):
 *    Cuando tras una inserción o eliminación el $FB$ alcanza **+2** o **-2**, la propiedad AVL se rompe:
 *    - $FB = -2$: Desbalance por la izquierda (la rama izquierda sobrepasa en 2 niveles a la derecha).
 *    - $FB = +2$: Desbalance por la derecha (la rama derecha sobrepasa en 2 niveles a la izquierda).
 *    Un valor de $+2$ o $-2$ indica el punto exacto donde el árbol debe ejecutar una **rotación** 
 *    (Simple o Doble) para restaurar el balance a la franja válida $[-1, 0, 1]$ manteniendo el tiempo $O(\log N)$.
 * 
 * AJUSTES REALIZADOS DESPUÉS DE LA PRIMERA RESPUESTA:
 * - Se aseguró el uso del método auxiliar 'obtenerAltura' desarrollado en el Ejercicio 2 para evitar 
 *   errores al calcular el factor de balance cuando uno o ambos hijos son 'null'.
 * 
 * MODIFICACIONES O CORRECCIONES MANUALES REALIZADAS:
 * - Se añadieron pruebas explícitas que generan intencionalmente un factor de balance de +2 y -2 para 
 *   demostrar la detección previa a una rotación.
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

public class FactorEquilibrioAVL {

    /**
     * Método auxiliar para obtener la altura de un nodo de forma segura.
     */
    public static int obtenerAltura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    /**
     * Actualiza el atributo 'altura' de un nodo según la de sus hijos.
     */
    public static void actualizarAltura(NodoAVL nodo) {
        if (nodo != null) {
            nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        }
    }

    /**
     * Calcula el Factor de Equilibrio (FB) de un nodo.
     * Fórmulas: AlturaDerecha - AlturaIzquierda
     * @param nodo Nodo a evaluar.
     * @return Factor de balance entero.
     */
    public static int obtenerFactorEquilibrio(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return obtenerAltura(nodo.derecho) - obtenerAltura(nodo.izquierdo);
    }

    public static void main(String[] args) {
        System.out.println("=== CÁLCULO DEL FACTOR DE EQUILIBRIO EN ÁRBOLES AVL ===");

        // 1. Caso Equilibrado PERFECTO (FB = 0)
        //       10
        //      /  \
        //     5    15
        NodoAVL raizEquilibrada = new NodoAVL(10);
        raizEquilibrada.izquierdo = new NodoAVL(5);
        raizEquilibrada.derecho = new NodoAVL(15);
        actualizarAltura(raizEquilibrada.izquierdo);
        actualizarAltura(raizEquilibrada.derecho);
        actualizarAltura(raizEquilibrada);

        System.out.println("1. Árbol Equilibrado Perfecto (Raíz 10): FB = " + obtenerFactorEquilibrio(raizEquilibrada));

        // 2. Caso Ligera Inclinación a la Izquierda VÁLIDA (FB = -1)
        //       10
        //      /  
        //     5   
        NodoAVL raizInclinadaIzq = new NodoAVL(10);
        raizInclinadaIzq.izquierdo = new NodoAVL(5);
        actualizarAltura(raizInclinadaIzq.izquierdo);
        actualizarAltura(raizInclinadaIzq);

        System.out.println("2. Inclinación leve a la izquierda (Raíz 10): FB = " + obtenerFactorEquilibrio(raizInclinadaIzq));

        // 3. Caso DESBALANCE por la Izquierda (FB = -2)
        //       10
        //      /  
        //     5   
        //    /
        //   2
        NodoAVL raizDesbalanceIzq = new NodoAVL(10);
        raizDesbalanceIzq.izquierdo = new NodoAVL(5);
        raizDesbalanceIzq.izquierdo.izquierdo = new NodoAVL(2);
        actualizarAltura(raizDesbalanceIzq.izquierdo.izquierdo);
        actualizarAltura(raizDesbalanceIzq.izquierdo);
        actualizarAltura(raizDesbalanceIzq);

        System.out.println("3. DESBALANCE por la Izquierda (Raíz 10): FB = " + obtenerFactorEquilibrio(raizDesbalanceIzq) + " -> [¡Requiere Rotación!]");

        // 4. Caso DESBALANCE por la Derecha (FB = +2)
        //       10
        //         \
        //          15
        //            \
        //             20
        NodoAVL raizDesbalanceDer = new NodoAVL(10);
        raizDesbalanceDer.derecho = new NodoAVL(15);
        raizDesbalanceDer.derecho.derecho = new NodoAVL(20);
        actualizarAltura(raizDesbalanceDer.derecho.derecho);
        actualizarAltura(raizDesbalanceDer.derecho);
        actualizarAltura(raizDesbalanceDer);

        System.out.println("4. DESBALANCE por la Derecha (Raíz 10): FB = " + obtenerFactorEquilibrio(raizDesbalanceDer) + " -> [¡Requiere Rotación!]");
    }
}
