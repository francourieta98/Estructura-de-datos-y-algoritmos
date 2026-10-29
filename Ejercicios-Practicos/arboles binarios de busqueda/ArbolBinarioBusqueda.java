/*
 * PROMPT INICIAL UTILIZADO:
 * "Crea una implementación en Java de un Árbol Binario de Búsqueda (BST) para insertar números enteros. 
 * El programa debe constar de una clase Nodo y una clase ArbolBinarioBusqueda. Debe incluir métodos 
 * para insertar valores respetando las propiedades del BST y un método para realizar un recorrido inorden. 
 * Muestra por consola el recorrido inorden para comprobar que los elementos aparecen ordenados de menor a mayor."
 * 
 * AJUSTES POSTERIORES Y RAZONES:
 * 1. Encapsulamiento del método recursivo de inserción: Se creó un método público 'insertar(int valor)' 
 *    y un método privado recursivo 'insertarRecursivo(Nodo actual, int valor)'. Esto evita exponer 
 *    la estructura interna de nodos al usuario en la llamada del método desde la clase principal.
 * 2. Manejo de valores duplicados: Se añadió una verificación explícita para ignorar o no duplicar 
 *    nodos si el valor ya existe en el árbol, manteniendo la integridad estándar de un BST.
 * 
 * MODIFICACIONES / CORRECCIONES MANUALES REALIZADAS:
 * - Se corrigió manualmente el retorno del método de inserción recursiva (`actual.izquierdo = ...` / `actual.derecho = ...`) 
 *   para garantizar que los punteros y enlaces entre nodos padres e hijos se reasignen correctamente durante 
 *   el rebalanceo natural de la pila de llamadas.
 * - Se formateó la salida en consola para incluir un mensaje explícito que verifique el ordenamiento.
 */

// Clase que representa un nodo dentro del BST
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

// Clase que administra la estructura del Árbol Binario de Búsqueda
public class ArbolBinarioBusqueda {
    private Nodo raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    // Método público para insertar un valor
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    // Método privado recursivo para insertar respetando la propiedad BST
    private Nodo insertarRecursivo(Nodo actual, int valor) {
        // Si el subárbol está vacío, se crea el nuevo nodo
        if (actual == null) {
            return new Nodo(valor);
        }

        // Si el valor es menor, va al subárbol izquierdo
        if (valor < actual.valor) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } 
        // Si el valor es mayor, va al subárbol derecho
        else if (valor > actual.valor) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        // Si el valor es igual, no se inserta duplicado
        
        return actual;
    }

    // Método público para iniciar el recorrido inorden
    public void recorridoInorden() {
        System.out.print("Recorrido Inorden: ");
        inordenRecursivo(raiz);
        System.out.println("FIN");
    }

    // Método auxiliar recursivo para el recorrido inorden (Izquierdo -> Raíz -> Derecho)
    private void inordenRecursivo(Nodo nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + " -> ");
            inordenRecursivo(nodo.derecho);
        }
    }

    // Método principal de prueba
    public static void main(String[] args) {
        ArbolBinarioBusqueda bst = new ArbolBinarioBusqueda();

        // Insertar valores desordenados: 50, 30, 70, 20, 40, 60, 80
        System.out.println("=== INSERTANDO ELEMENTOS EN EL BST ===");
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        
        for (int v : valores) {
            System.out.println("Insertando: " + v);
            bst.insertar(v);
        }

        System.out.println("\n=== VERIFICACIÓN DEL ORDEN (INORDEN) ===");
        bst.recorridoInorden();
        System.out.println("Resultado esperado: 20 -> 30 -> 40 -> 50 -> 60 -> 70 -> 80 -> FIN");
    }
}
