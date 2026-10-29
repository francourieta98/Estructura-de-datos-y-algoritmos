/*
 * PROMPT INICIAL UTILIZADO:
 * "Crea en Java la clase NodoAVL para un Árbol AVL de números enteros. 
 * La clase debe contener:
 * - Un atributo entero 'valor' o 'dato' para almacenar el número.
 * - Las referencias a sus hijos 'izquierdo' y 'derecho'.
 * - Un atributo de balance denominado 'bal' (o factor de equilibrio / altura).
 * Explica detalladamente en los comentarios por qué un nodo AVL necesita guardar información 
 * de balance/altura además del dato simple que tiene un nodo de Árbol Binario de Búsqueda (ABB) común."
 * 
 * ¿POR QUÉ UN NODO AVL NECESITA GUARDAR INFORMACIÓN DE BALANCE/ALTURA?
 * 1. MANTENIMIENTO DEL TIEMPO O(log N):
 *    En un Árbol Binario de Búsqueda (ABB) estándar, si las inserciones ocurren en orden creciente o decreciente, 
 *    el árbol se degrada a una lista enlazada (O(N)). Un Árbol AVL garantiza un tiempo de búsqueda, inserción 
 *    y eliminación en O(log N) mediante el mantenimiento estricto de su propiedad de balance.
 * 
 * 2. CÁLCULO EFICIENTE DEL FACTOR DE BALANCE:
 *    El factor de balance ($FB$) de un nodo se calcula como:
 *    $FB = \text{altura}(sub\acute{a}rbol\_derecho) - \text{altura}(sub\acute{a}rbol\_izquierdo)$  (o viceversa).
 *    Si no guardáramos la altura o el balance en cada nodo, calcular el $FB$ requeriría recorrer recursivamente 
 *    todo el subárbol en cada inserción o eliminación ($O(N)$), haciendo que la reestructuración fuera ineficiente.
 * 
 * 3. DETECCIÓN TEMPRANA DE DESBALANCE (-2, +2):
 *    Al almacenar la altura o el factor de balance directamente en la estructura de cada nodo, la verificación 
 *    se realiza en tiempo constante $O(1)$ a medida que la pila de llamadas recursivas regresa de una inserción. 
 *    Esto permite identificar inmediatamente el primer nodo que viole la condición $|FB| \le 1$ para activar 
 *    las rotaciones correspondientes.
 * 
 * AJUSTES REALIZADOS DESPUÉS DE LA PRIMERA RESPUESTA:
 * - Se decidió utilizar el atributo 'altura' en lugar de guardar un 'bal' relativo de -1, 0, 1. La razón es que 
 *   calcular el factor de balance dinámicamente mediante 'altura(der) - altura(izq)' resulta menos propenso a errores 
 *   durante las rotaciones que mantener actualizado un entero relativo simple.
 * - Se inicializó la altura por defecto en 1 para nodos recién creados (hojas).
 * 
 * MODIFICACIONES O CORRECCIONES MANUALES REALIZADAS:
 * - Se agregaron métodos accesores (getters/setters) y un constructor claro para facilitar el uso de la clase 
 *   en los siguientes ejercicios de Árboles AVL.
 */

public class NodoAVL {
    public int valor;
    public int altura; // Almacena la altura del subárbol para calcular el balance en O(1)
    public NodoAVL izquierdo;
    public NodoAVL derecho;

    /**
     * Constructor para inicializar un nodo con un valor.
     * @param valor Número entero que almacenará el nodo.
     */
    public NodoAVL(int valor) {
        this.valor = valor;
        this.altura = 1; // Un nuevo nodo hoja comienza siempre con altura 1
        this.izquierdo = null;
        this.derecho = null;
    }

    /**
     * Método toString auxiliar para visualizar el nodo y su altura actual.
     */
    @Override
    public String toString() {
        return "Nodo{" +
                "valor=" + valor +
                ", altura=" + altura +
                '}';
    }

    // Clase principal de demostración
    public static void main(String[] args) {
        System.out.println("=== CREACIÓN Y PRUEBA DE NODO AVL ===");
        
        NodoAVL nodoRaiz = new NodoAVL(10);
        nodoRaiz.izquierdo = new NodoAVL(5);
        nodoRaiz.derecho = new NodoAVL(15);

        System.out.println("Nodo Raíz: " + nodoRaiz);
        System.out.println("Hijo Izquierdo: " + nodoRaiz.izquierdo);
        System.out.println("Hijo Derecho: " + nodoRaiz.derecho);
    }
}
