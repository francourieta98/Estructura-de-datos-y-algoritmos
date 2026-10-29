/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java dos métodos dentro de una clase de Árbol Binario de Búsqueda (BST): 
 * 'cantidadNodos()' y 'cantidadHojas()'. Explica detalladamente en los comentarios la diferencia 
 * conceptual y de código entre contar todos los nodos y contar únicamente los nodos hoja. 
 * Muestra pruebas ejecutando ambos métodos sobre distintos árboles (un árbol completo y un árbol lineal/degenerado)."
 * 
 * DIFERENCIA ENTRE CONTAR TODOS LOS NODOS Y CONTAR SOLAMENTE HOJAS:
 * 
 * 1. CONTAR TODOS LOS NODOS:
 *    - Definición: Suma cada uno de los elementos presentes en la estructura, independientemente de si tienen hijos o no.
 *    - Caso Base: Si el nodo es 'null', retorna 0.
 *    - Paso Recursivo: Si el nodo existe, aporta 1 al conteo y suma los nodos de sus dos subárboles:
 *      Total = 1 + contarNodos(izq) + contarNodos(der)
 * 
 * 2. CONTAR SOLAMENTE HOJAS:
 *    - Definición: Suma únicamente aquellos nodos terminales que no tienen descendientes (izquierdo == null && derecho == null).
 *    - Caso Base: Si el nodo es 'null', retorna 0.
 *    - Condición de Hoja: Si el nodo no tiene hijo izquierdo ni derecho, aporta 1.
 *    - Paso Recursivo para Nodos Internos: Si el nodo tiene al menos un hijo, no es una hoja (aporta 0), 
 *      por lo que solo delega la búsqueda a sus ramas:
 *      Total Hojas = contarHojas(izq) + contarHojas(der)
 * 
 * AJUSTES POSTERIORES Y RAZONES:
 * - Se encapsularon los métodos recursivos privados detrás de métodos públicos sin parámetros 
 *   ('cantidadNodos()' y 'cantidadHojas()') para simplificar su llamada desde el código cliente.
 * 
 * MODIFICACIONES / CORRECCIONES MANUALES REALIZADAS:
 * - Se diseñó una batería de pruebas con dos estructuras de árbol completamente distintas (un BST balanceado 
 *   y un BST degenerado) para demostrar cómo varía la proporción entre nodos totales y hojas según la forma del árbol.
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

public class ContarNodosYHojasBST {
    private Nodo raiz;

    public ContarNodosYHojasBST() {
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

    // 1. Método público para contar la cantidad total de nodos
    public int cantidadNodos() {
        return contarNodosRecursivo(raiz);
    }

    // Lógica recursiva para contar TODOS los nodos
    private int contarNodosRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        // Cuenta 1 (el nodo actual) más la suma de sus subárboles
        return 1 + contarNodosRecursivo(nodo.izquierdo) + contarNodosRecursivo(nodo.derecho);
    }

    // 2. Método público para contar únicamente las hojas
    public int cantidadHojas() {
        return contarHojasRecursivo(raiz);
    }

    // Lógica recursiva para contar SOLO las hojas (nodos sin hijos)
    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        // Si no tiene hijo izquierdo ni derecho, es una hoja
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }
        // Si no es hoja, se suman las hojas encontradas en las ramas
        return contarHojasRecursivo(nodo.izquierdo) + contarHojasRecursivo(nodo.derecho);
    }

    public static void main(String[] args) {
        // --- ÁRBOL 1: BST Balanceado (Estructura en forma de pirámide) ---
        ContarNodosYHojasBST arbol1 = new ContarNodosYHojasBST();
        int[] valores1 = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores1) {
            arbol1.insertar(v);
        }

        System.out.println("=== PRUEBA EN ÁRBOL 1 (BST Balanceado) ===");
        System.out.println("Elementos insertados: [50, 30, 70, 20, 40, 60, 80]");
        System.out.println("Cantidad total de nodos: " + arbol1.cantidadNodos()); // Esperado: 7
        System.out.println("Cantidad de nodos hoja:   " + arbol1.cantidadHojas()); // Esperado: 4 (20, 40, 60, 80)

        System.out.println("\n-------------------------------------------\n");

        // --- ÁRBOL 2: BST Degenerado (Estructura tipo Lista Enlazada) ---
        ContarNodosYHojasBST arbol2 = new ContarNodosYHojasBST();
        int[] valores2 = {10, 20, 30, 40, 50};
        for (int v : valores2) {
            arbol2.insertar(v);
        }

        System.out.println("=== PRUEBA EN ÁRBOL 2 (BST Degenerado / Lineal) ===");
        System.out.println("Elementos insertados: [10, 20, 30, 40, 50]");
        System.out.println("Cantidad total de nodos: " + arbol2.cantidadNodos()); // Esperado: 5
        System.out.println("Cantidad de nodos hoja:   " + arbol2.cantidadHojas()); // Esperado: 1 (solo el 50)
    }
}
