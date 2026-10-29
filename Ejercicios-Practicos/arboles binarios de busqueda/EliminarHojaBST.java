/*
 * PROMPT INICIAL UTILIZADO:
 * "Implementa en Java la eliminación de un nodo hoja (sin hijos) dentro de un Árbol Binario de Búsqueda (BST).
 * Explica en los comentarios:
 * - Cómo se identifica que el nodo que se desea eliminar es efectivamente una hoja.
 * - Qué debe devolver el método recursivo en ese caso para desvincular la hoja del árbol.
 * Prueba el programa eliminando un nodo hoja y mostrando el recorrido inorden antes y después."
 * 
 * LÓGICA DE ELIMINACIÓN DE UNA HOJA EN BST:
 * 1. IDENTIFICACIÓN DE UNA HOJA:
 *    Se identifica que un nodo es una hoja cuando sus dos referencias a hijos son nulas:
 *    `(actual.izquierdo == null && actual.derecho == null)`.
 * 
 * 2. RETORNO DEL MÉTODO RECURSIVO PARA DESVINCULAR:
 *    En la recursión, los enlaces de un nodo padre se actualizan asignando el resultado de la llamada recursiva:
 *    - `actual.izquierdo = eliminarRecursivo(actual.izquierdo, clave);`
 *    - `actual.derecho = eliminarRecursivo(actual.derecho, clave);`
 * 
 *    Cuando el método llega al nodo hoja que se quiere eliminar, al retornar `null` en lugar del nodo actual,
 *    la referencia en el padre (`actual.izquierdo` o `actual.derecho`) se sobrescribe con `null`.
 *    De esta forma, la hoja queda desvinculada del árbol y es recogida por el recolector de basura (Garbage Collector).
 * 
 * AJUSTES POSTERIORES Y RAZONES:
 * - Se encapsuló el método de eliminación en una función pública `eliminarHoja(int valor)` y una privada recursiva 
 *   para mantener una interfaz limpia hacia el usuario.
 * - Se añadió una validación previa en el código de prueba para verificar que el nodo seleccionado sea efectivamente 
 *   una hoja antes de llamar a la eliminación.
 * 
 * MODIFICACIONES / CORRECCIONES MANUALES REALIZADAS:
 * - Se aseguró que el método no intente eliminar nodos que tengan hijos (si el nodo encontrado tiene al menos 
 *   un hijo, el programa emite una advertencia indicando que este ejercicio aplica únicamente a hojas).
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

public class EliminarHojaBST {
    private Nodo raiz;

    public EliminarHojaBST() {
        this.raiz = null;
    }

    // Método para insertar elementos en el BST
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

    // Método público para eliminar una hoja
    public void eliminarHoja(int valor) {
        raiz = eliminarHojaRecursivo(raiz, valor);
    }

    // Lógica recursiva para buscar y desvincular una hoja
    private Nodo eliminarHojaRecursivo(Nodo actual, int valor) {
        // Caso base: valor no encontrado o árbol vacío
        if (actual == null) {
            System.out.println("El valor " + valor + " no existe en el árbol.");
            return null;
        }

        // Navegación en el BST
        if (valor < actual.valor) {
            actual.izquierdo = eliminarHojaRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = eliminarHojaRecursivo(actual.derecho, valor);
        } else {
            // Se encontró el nodo deseado: Verificar si es una hoja
            if (actual.izquierdo == null && actual.derecho == null) {
                System.out.println("-> Nodo hoja " + valor + " encontrado y eliminado.");
                // Retornar null para que el padre rompa el enlace hacia este nodo
                return null; 
            } else {
                System.out.println("ADVERTENCIA: El nodo " + valor + " no es una hoja (tiene hijos). No se eliminará.");
            }
        }

        return actual;
    }

    // Recorrido Inorden
    public void mostrarInorden() {
        System.out.print("Inorden: ");
        inordenRecursivo(raiz);
        System.out.println("FIN");
    }

    private void inordenRecursivo(Nodo nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + " -> ");
            inordenRecursivo(nodo.derecho);
        }
    }

    public static void main(String[] args) {
        EliminarHojaBST bst = new EliminarHojaBST();

        // 1. Carga de elementos en el BST
        // Nodos hoja resultantes: 20, 40, 60, 80
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores) {
            bst.insertar(v);
        }

        System.out.println("=== ESTADO INICIAL DEL ÁRBOL ===");
        bst.mostrarInorden(); // Esperado: 20 -> 30 -> 40 -> 50 -> 60 -> 70 -> 80 -> FIN

        System.out.println("\n=== PRUEBA 1: ELIMINAR NODO HOJA (20) ===");
        bst.eliminarHoja(20);
        bst.mostrarInorden(); // Esperado: 30 -> 40 -> 50 -> 60 -> 70 -> 80 -> FIN

        System.out.println("\n=== PRUEBA 2: ELIMINAR OTRA HOJA (80) ===");
        bst.eliminarHoja(80);
        bst.mostrarInorden(); // Esperado: 30 -> 40 -> 50 -> 60 -> 70 -> FIN

        System.out.println("\n=== PRUEBA 3: INTENTAR ELIMINAR UN NODO PADRE CON HIJOS (30) ===");
        bst.eliminarHoja(30);
        bst.mostrarInorden(); // No debe modificarse
    }
}
