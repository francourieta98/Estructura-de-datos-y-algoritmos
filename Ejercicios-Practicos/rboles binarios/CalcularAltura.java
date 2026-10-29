/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java un método recursivo para calcular la altura de un árbol binario. 
 * Define y explica en los comentarios el criterio utilizado para medir la altura:
 * - Árbol vacío (nodo == null): altura 0.
 * - Árbol con solo la raíz: altura 1.
 * Explica detalladamente cómo la altura de cualquier nodo se obtiene calculando la altura 
 * del subárbol izquierdo, la altura del subárbol derecho y sumando 1 al mayor de ambos valores."
 * 
 * DEFINICIÓN DEL CRITERIO DE ALTURA (Basado en nodos):
 * - Árbol vacío (null): Altura = 0.
 * - Nodo único (Raíz sin hijos): Altura = 1.
 * (Nota: Este criterio cuenta la cantidad de niveles / nodos en el camino más largo desde la raíz hasta una hoja).
 * 
 * LÓGICA RECURSIVA DE CÁLCULO:
 * 1. CASO BASE: 
 *    Si el nodo actual es 'null', la altura es 0.
 * 2. PASO RECURSIVO:
 *    - Se calcula la altura del subárbol izquierdo: 'altIzq = calcularAltura(nodo.izquierdo)'.
 *    - Se calcula la altura del subárbol derecho: 'altDer = calcularAltura(nodo.derecho)'.
 *    - La altura del nodo actual es: 1 + Math.max(altIzq, altDer).
 *    
 *    Sumar 1 representa la contribución del nivel del nodo actual a la rama más profunda que desciende de él.
 * 
 * AJUSTES REALIZADOS Y JUSTIFICACIÓN:
 * - Se utilizó 'Math.max(altIzq, altDer)' para simplificar de manera limpia y legible la comparación 
 *   entre las alturas de ambas ramas.
 * - Se agregaron casos de prueba dinámicos (evaluar la altura total vs. la altura de un subárbol o hoja) 
 *   para confirmar que el algoritmo funciona correctamente en cualquier subnivel.
 */

class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class CalcularAltura {

    public static void main(String[] args) {
        // Construcción del árbol binario de ejemplo:
        //       10          -> Nivel 1
        //      /  \
        //     5    20       -> Nivel 2
        //    / \   / \
        //   3   7 15 30     -> Nivel 3 (Altura Total = 3)
        Nodo raiz = new Nodo(10);
        raiz.izquierdo = new Nodo(5);
        raiz.derecho = new Nodo(20);

        raiz.izquierdo.izquierdo = new Nodo(3);
        raiz.izquierdo.derecho = new Nodo(7);

        raiz.derecho.izquierdo = new Nodo(15);
        raiz.derecho.derecho = new Nodo(30);

        // Pruebas de cálculo de altura
        System.out.println("=== CÁLCULO DE LA ALTURA DEL ÁRBOL ===");
        System.out.println("Criterio: Árbol vacío = 0, Solo Raíz = 1");
        System.out.println("---------------------------------------");
        
        System.out.println("Altura del árbol completo: " + calcularAltura(raiz));
        System.out.println("Altura del subárbol derecho (nodo 20): " + calcularAltura(raiz.derecho));
        System.out.println("Altura de un nodo hoja (nodo 3): " + calcularAltura(raiz.izquierdo.izquierdo));
        System.out.println("Altura de un árbol vacío (null): " + calcularAltura(null));
    }

    /**
     * Método recursivo para calcular la altura de un árbol binario.
     * @param nodo Nodo actual visitado.
     * @return Altura del árbol o subárbol cuya raíz es 'nodo'.
     */
    public static int calcularAltura(Nodo nodo) {
        // 1. Caso base: un árbol o subárbol vacío tiene altura 0
        if (nodo == null) {
            return 0;
        }

        // 2. Calcular la altura de los subárboles izquierdo y derecho
        int alturaIzquierda = calcularAltura(nodo.izquierdo);
        int alturaDerecha = calcularAltura(nodo.derecho);

        // 3. La altura es 1 (nodo actual) más el máximo entre la altura izquierda y derecha
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }
}
