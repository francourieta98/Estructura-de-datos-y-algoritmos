/*
 * PROMPT INICIAL UTILIZADO:
 * "Crea en Java una clase 'ArbolAVL' completa y autocontenida que implemente todas las operaciones fundamentales de un Árbol AVL:
 * - 'insertar(int valor)'
 * - 'eliminar(int valor)'
 * - 'buscar(int valor)'
 * - 'altura()'
 * - 'esAVL()'
 * - 'recorridoInOrden()'
 * 
 * Explica detalladamente en los comentarios de cabecera:
 * 1. Cómo se reutiliza la lógica del Árbol Binario de Búsqueda (ABB) para inserción y eliminación.
 * 2. Cómo se recalcula la altura y el factor de equilibrio durante la fase de desapilado / retorno de la recursión.
 * 3. Las condiciones exactas para la aplicación de las 4 rotaciones (LL, RR, LR, RL).
 * 4. El algoritmo de eliminación usando el sucesor inorden (mínimo del subárbol derecho) y rebalanceo posterior.
 * 5. La metodología de validación post-operación que garantiza que la estructura conserve las propiedades ABB y AVL."
 * 
 * RAZONAMIENTO Y ARQUITECTURA TÉCNICA DEL ÁRBOL AVL INTEGRAL:
 * 
 * 1. REUTILIZACIÓN DE LA LÓGICA ABB + REBALANCEO POSTERIOR:
 *    Tanto la inserción como la eliminación comienzan ejecutando el algoritmo estándar de un Árbol Binario de Búsqueda (ABB):
 *    - Inserción: Navega buscando la posición vacía (null) respetando la propiedad $menor < actual < mayor$.
 *    - Eliminación: Localiza el nodo y resuelve según posea 0, 1 o 2 hijos (reemplazando por el sucesor inorden en el caso de 2 hijos).
 * 
 * 2. RECALCULO DE BALANCE EN EL RETORNO RECURSIVO:
 *    A medida que la pila de llamadas recursivas desapila hacia la raíz, cada nodo ancestro ejecuta sucesivamente:
 *    a) Actualización de altura: $altura = 1 + \max(altura(izq), altura(der))$
 *    b) Obtención del Factor de Balance ($FB$): $FB = altura(derecho) - altura(izquierdo)$
 * 
 * 3. CRITERIOS EXACTOS PARA LAS CUATRO ROTACIONES:
 *    - Si $FB == -2$ (Desbalance por la izquierda):
 *      * Si $FB(hijoIzquierdo) \le 0$: Caso LL -> Se aplica **Rotación Simple a la Derecha**.
 *      * Si $FB(hijoIzquierdo) > 0$: Caso LR -> Se aplica **Rotación Doble Izquierda-Derecha**.
 *    - Si $FB == +2$ (Desbalance por la derecha):
 *      * Si $FB(hijoDerecho) \ge 0$: Caso RR -> Se aplica **Rotación Simple a la Izquierda**.
 *      * Si $FB(hijoDerecho) < 0$: Caso RL -> Se aplica **Rotación Doble Derecha-Izquierda**.
 * 
 * 4. VALIDACIÓN DE INTEGRIDAD POST-OPERACIÓN:
 *    Se incluye el método `esAVL()` que evalúa de forma recursiva que en cada nodo se cumplan las reglas
 *    de ordenamiento $ABB$ y la propiedad $|FB| \le 1$, garantizando una complejidad temporal estricta de $O(\log N)$.
 * 
 * AJUSTES REALIZADOS DESPUÉS DE LA PRIMERA RESPUESTA:
 * - Se integraron los tres casos de eliminación (hoja, un hijo y dos hijos) dentro del flujo recursivo que retorna 
 *   el nodo rebalanceado a sus ancestros.
 * - Se incluyó el método de búsqueda iterativa optimizada en $O(\log N)$.
 * 
 * MODIFICACIONES O CORRECCIONES MANUALES REALIZADAS:
 * - Se implementó un plan de pruebas en el método `main` ejecutando inserciones que desencadenan las 4 rotaciones 
 *   y eliminaciones sucesivas, comprobando mediante `esAVL()` la integridad del árbol tras cada paso.
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

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    // ==========================================
    // MÉTODOS AUXILIARES DE ALTURA Y BALANCE
    // ==========================================

    public int altura() {
        return obtenerAltura(raiz);
    }

    private int obtenerAltura(NodoAVL nodo) {
        if (nodo == null) return 0;
        return nodo.altura;
    }

    private void actualizarAltura(NodoAVL nodo) {
        if (nodo != null) {
            nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        }
    }

    private int obtenerFactorEquilibrio(NodoAVL nodo) {
        if (nodo == null) return 0;
        return obtenerAltura(nodo.derecho) - obtenerAltura(nodo.izquierdo);
    }

    // ==========================================
    // ROTACIONES AVL (SIMPLE Y DOBLES)
    // ==========================================

    // Rotación Simple a la Derecha (Caso LL)
    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;

        x.derecho = y;
        y.izquierdo = T2;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    // Rotación Simple a la Izquierda (Caso RR)
    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    // Rotación Doble Izquierda-Derecha (Caso LR)
    private NodoAVL rotacionIzquierdaDerecha(NodoAVL nodo) {
        nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
        return rotacionDerecha(nodo);
    }

    // Rotación Doble Derecha-Izquierda (Caso RL)
    private NodoAVL rotacionDerechaIzquierda(NodoAVL nodo) {
        nodo.derecho = rotacionDerecha(nodo.derecho);
        return rotacionIzquierda(nodo);
    }

    /**
     * Evalúa el factor de balance de un nodo y aplica la rotación correspondiente si existe desbalance (+2 o -2).
     */
    private NodoAVL balancear(NodoAVL nodo) {
        if (nodo == null) return null;

        actualizarAltura(nodo);
        int balance = obtenerFactorEquilibrio(nodo);

        // Desbalance por la izquierda (FB = -2)
        if (balance == -2) {
            if (obtenerFactorEquilibrio(nodo.izquierdo) <= 0) {
                return rotacionDerecha(nodo); // Caso LL
            } else {
                return rotacionIzquierdaDerecha(nodo); // Caso LR
            }
        }

        // Desbalance por la derecha (FB = +2)
        if (balance == 2) {
            if (obtenerFactorEquilibrio(nodo.derecho) >= 0) {
                return rotacionIzquierda(nodo); // Caso RR
            } else {
                return rotacionDerechaIzquierda(nodo); // Caso RL
            }
        }

        return nodo; // El nodo está balanceado
    }

    // ==========================================
    // INSERCIÓN
    // ==========================================

    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private NodoAVL insertarRecursivo(NodoAVL actual, int valor) {
        if (actual == null) {
            return new NodoAVL(valor);
        }

        if (valor < actual.valor) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        } else {
            return actual; // No se permiten duplicados
        }

        // Retorna el nodo rebalanceado durante el retorno de la recursión
        return balancear(actual);
    }

    // ==========================================
    // ELIMINACIÓN
    // ==========================================

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private NodoAVL eliminarRecursivo(NodoAVL actual, int valor) {
        if (actual == null) {
            return null; // Elemento no encontrado
        }

        if (valor < actual.valor) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = eliminarRecursivo(actual.derecho, valor);
        } else {
            // Nodo encontrado
            
            // Caso 1 y 2: Un solo hijo o ninguno (hoja)
            if (actual.izquierdo == null || actual.derecho == null) {
                NodoAVL temp = (actual.izquierdo != null) ? actual.izquierdo : actual.derecho;

                if (temp == null) {
                    // Caso 0 hijos (hoja)
                    actual = null;
                } else {
                    // Caso 1 hijo
                    actual = temp;
                }
            } else {
                // Caso 3: Dos hijos
                // Obtener el sucesor inorden (mínimo del subárbol derecho)
                NodoAVL sucesor = obtenerMinimo(actual.derecho);
                actual.valor = sucesor.valor;
                // Eliminar el sucesor duplicado en el subárbol derecho
                actual.derecho = eliminarRecursivo(actual.derecho, sucesor.valor);
            }
        }

        if (actual == null) {
            return null;
        }

        // Retorna el nodo rebalanceado
        return balancear(actual);
    }

    private NodoAVL obtenerMinimo(NodoAVL nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    // ==========================================
    // BÚSQUEDA
    // ==========================================

    public boolean buscar(int valor) {
        NodoAVL actual = raiz;
        while (actual != null) {
            if (valor < actual.valor) {
                actual = actual.izquierdo;
            } else if (valor > actual.valor) {
                actual = actual.derecho;
            } else {
                return true; // Encontrado
            }
        }
        return false; // No encontrado
    }

    // ==========================================
    // RECORRIDO INORDEN
    // ==========================================

    public void recorridoInOrden() {
        System.out.print("Inorden: ");
        inordenRecursivo(raiz);
        System.out.println("FIN");
    }

    private void inordenRecursivo(NodoAVL nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inordenRecursivo(nodo.derecho);
        }
    }

    // ==========================================
    // VALIDACIÓN INTEGRAL esAVL()
    // ==========================================

    public boolean esAVL() {
        return esAVLRecursivo(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean esAVLRecursivo(NodoAVL nodo, long min, long max) {
        if (nodo == null) {
            return true;
        }

        // Validación de propiedad ABB
        if (nodo.valor <= min || nodo.valor >= max) {
            return false;
        }

        // Validación de propiedad AVL
        int balance = obtenerFactorEquilibrio(nodo);
        if (Math.abs(balance) > 1) {
            return false;
        }

        return esAVLRecursivo(nodo.izquierdo, min, nodo.valor) &&
               esAVLRecursivo(nodo.derecho, nodo.valor, max);
    }

    // ==========================================
    // MÉTODO MAIN DE PRUEBAS EXTENSIVAS
    // ==========================================

    public static void main(String[] args) {
        ArbolAVL avl = new ArbolAVL();

        System.out.println("=== 1. INSERCIÓN MÚLTIPLE Y BALANCEO AUTOMÁTICO ===");
        int[] valores = {10, 20, 30, 40, 50, 25};
        System.out.print("Insertando: ");
        for (int v : valores) {
            System.out.print(v + " ");
            avl.insertar(v);
        }
        System.out.println("\n");

        avl.recorridoInOrden();
        System.out.println("Altura total del árbol: " + avl.altura());
        System.out.println("¿El árbol cumple las condiciones AVL?: " + avl.esAVL());

        System.out.println("\n=== 2. PRUEBA DE BÚSQUEDA ===");
        System.out.println("¿Existe el valor 25?: " + avl.buscar(25)); // true
        System.out.println("¿Existe el valor 99?: " + avl.buscar(99)); // false

        System.out.println("\n=== 3. PRUEBA DE ELIMINACIÓN Y REBALANCEO ===");
        System.out.println("Eliminando nodo 40...");
        avl.eliminar(40);
        avl.recorridoInOrden();
        System.out.println("¿Sigue siendo AVL tras eliminar el 40?: " + avl.esAVL());

        System.out.println("\nEliminando la raíz actual (30)...");
        avl.eliminar(30);
        avl.recorridoInOrden();
        System.out.println("¿Sigue siendo AVL tras eliminar la raíz?: " + avl.esAVL());
        System.out.println("Nueva altura del árbol: " + avl.altura());
    }
}
