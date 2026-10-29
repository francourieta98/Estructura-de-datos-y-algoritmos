/*
 * PROMPT INICIAL UTILIZADO:
 * "Agrega a la clase ArbolBinarioBusqueda un método 'buscar(int valor)' que devuelva 'true' si el valor 
 * existe en el BST y 'false' si no existe. Explica en los comentarios por qué no es necesario recorrer 
 * todo el árbol durante la búsqueda. Muestra ejemplos en consola con búsquedas exitosas y fallidas."
 * 
 * ¿POR QUÉ NO ES NECESARIO RECORRER TODO EL ÁRBOL EN UN BST?
 * A diferencia de un árbol binario general (donde habría que revisar ambas ramas), en un Árbol Binario 
 * de Búsqueda (BST) se cumple la regla de ordenamiento: 
 * - Todos los nodos del subárbol izquierdo son MENORES que el nodo actual.
 * - Todos los nodos del subárbol derecho son MAYORES que el nodo actual.
 * 
 * Gracias a esta propiedad, en cada paso de comparación se puede descartar inmediatamente la mitad de 
 * los subárboles pendientes (si el valor buscado es menor que el nodo actual, se ignora todo el subárbol 
 * derecho; si es mayor, se ignora todo el subárbol izquierdo). Esto reduce la complejidad media de la 
 * búsqueda de O(N) a O(log N).
 * 
 * AJUSTES POSTERIORES Y RAZONES:
 * 1. Implementación Iterativa vs. Recursiva: Se eligió implementar un método de búsqueda de forma 
 *    iterativa (mediante un bucle 'while') para evitar la sobrecarga de la pila de llamadas (call stack) 
 *    y hacer la ejecución más eficiente en memoria.
 * 
 * MODIFICACIONES / CORRECCIONES MANUALES REALIZADAS:
 * - Se integró el método 'buscar' manteniendo compatibilidad con la estructura previa del Ejercicio 1.
 * - Se estructuró el código dentro de la misma clase 'ArbolBinarioBusqueda' para facilitar su adición directa.
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

public class BusquedaBST {
    private Nodo raiz;

    public BusquedaBST() {
        this.raiz = null;
    }

    // Método para insertar valores en el BST
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }
        if (valor < actual.valor) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        return actual;
    }

    /**
     * Método para buscar un valor dentro del BST.
     * @param valor Elemento a buscar.
     * @return true si se encuentra en el árbol, false si no.
     */
    public boolean buscar(int valor) {
        Nodo actual = raiz;

        // Recorrido eficiente descartando ramas según el valor
        while (actual != null) {
            if (valor == actual.valor) {
                return true; // Elemento encontrado
            } else if (valor < actual.valor) {
                actual = actual.izquierdo; // Descartar subárbol derecho
            } else {
                actual = actual.derecho; // Descartar subárbol izquierdo
            }
        }

        return false; // El elemento no existe en el BST
    }

    public static void main(String[] args) {
        BusquedaBST bst = new BusquedaBST();

        // Construcción del árbol con valores de prueba
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores) {
            bst.insertar(v);
        }

        System.out.println("=== PRUEBAS DE BÚSQUEDA EN BST ===");

        // Búsquedas exitosas
        System.out.println("¿Existe el 40?: " + bst.buscar(40)); // Esperado: true
        System.out.println("¿Existe el 80?: " + bst.buscar(80)); // Esperado: true
        System.out.println("¿Existe la raíz (50)?: " + bst.buscar(50)); // Esperado: true

        System.out.println("----------------------------------");

        // Búsquedas fallidas
        System.out.println("¿Existe el 25?: " + bst.buscar(25)); // Esperado: false
        System.out.println("¿Existe el 90?: " + bst.buscar(90)); // Esperado: false
        System.out.println("¿Existe el 5?: "  + bst.buscar(5));  // Esperado: false
    }
}
