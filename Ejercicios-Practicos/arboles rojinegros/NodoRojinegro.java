/*
 * PROMPT INICIAL UTILIZADO:
 * "Crea en Java la clase 'NodoRojinegro' para representar un nodo individual de un Árbol Rojinegro (Red-Black Tree).
 * La clase debe contener:
 * - Un entero 'valor' o 'dato'.
 * - Un atributo para el 'color' (puedes usar un tipo enum Color { ROJO, NEGRO } o una constante booleana/entera).
 * - Referencias a sus tres enlaces fundamentales: 'izquierdo', 'derecho' y 'padre'.
 * 
 * Explica detalladamente en los comentarios de cabecera:
 * 1. Qué estructura se va a implementar y qué atributos requiere.
 * 2. Qué propiedades del Árbol Rojinegro debe respetar (en particular las reglas de coloración).
 * 3. Qué casos debe contemplar la inicialización del nodo (nodo nuevo vs. nodo centinela/NIL).
 * 4. Por qué un Árbol Rojinegro requiere guardar la referencia al 'padre' y el 'color', a diferencia de un ABB común.
 * 5. Cómo se validará que la clase funciona correctamente (instanciación de nodos y verificación de relaciones padre-hijo).
 * 
 * Incluye también un método 'main' de prueba que construya una pequeña jerarquía manual y muestre el estado de los nodos por consola."
 * 
 * AJUSTES REALIZADOS AL PROMPT:
 * - Se especificó el uso de un 'enum Color { ROJO, NEGRO }' en lugar de un booleano simple para mejorar la legibilidad y tipado estricto del código.
 * - Se garantizó que el constructor por defecto asigne el color ROJO a los nuevos nodos insertedados, siguiendo las convenciones del algoritmo de Árboles Rojinegros.
 * 
 * JUSTIFICACIÓN TÉCNICA Y TEÓRICA:
 * ¿POR QUÉ EL NODO NECESITA GUARDAR EL COLOR Y EL PADRE?
 * 
 * 1. EL ATRIBUTO 'COLOR':
 *    Un Árbol Rojinegro es un Árbol Binario de Búsqueda auto-balanceado que utiliza el color de cada nodo para garantizar 
 *    que ninguna rama sea más del doble de larga que cualquier otra. Las 5 propiedades fundamentales que dependen del color son:
 *    a) Todo nodo es ROJO o NEGRO.
 *    b) La raíz es siempre NEGRA.
 *    c) Las hojas (nodos centinela NIL) son NEGRAS.
 *    d) Si un nodo es ROJO, sus dos hijos deben ser NEGROS (no puede haber dos nodos rojos consecutivos en una rama).
 *    e) Para cada nodo, todos los caminos desde él hasta sus hojas descendientes contienen el mismo número de nodos NEGROS (altura negra).
 *    Guardar el color permite verificar estas propiedades en $O(1)$ durante cada inserción o eliminación.
 * 
 * 2. LA REFERENCIA AL 'PADRE':
 *    En un ABB común, la navegación se realiza siempre en sentido descendente (de la raíz hacia las hojas). 
 *    En un Árbol Rojinegro, tras una inserción o eliminación, es necesario "subir" hacia los ancestros para detectar 
 *    violaciones del balanceo (como un padre rojo con un hijo rojo) y consultar el estado del nodo "tío" o "abuelo". 
 *    Tener un enlace directo al `padre` evita la necesidad de mantener listas o pilas auxiliares durante el recorrido 
 *    recursivo, permitiendo aplicar rotaciones y recoloraciones de forma eficiente y limpia en tiempo $O(1)$ local.
 */

// Enumeración para representar de forma estricta el color del nodo
enum Color {
    ROJO,
    NEGRO
}

public class NodoRojinegro {
    public int valor;
    public Color color;
    public NodoRojinegro izquierdo;
    public NodoRojinegro derecho;
    public NodoRojinegro padre;

    /**
     * Constructor para un nodo nuevo con valor.
     * Por convención en los Árboles Rojinegros, todo nuevo nodo ingresa siendo de color ROJO.
     * @param valor Número entero a almacenar.
     */
    public NodoRojinegro(int valor) {
        this.valor = valor;
        this.color = Color.ROJO; // Los nuevos nodos siempre se insertan de color ROJO
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }

    /**
     * Constructor parametrizado completo (útil para la inicialización del nodo centinela NIL).
     */
    public NodoRojinegro(int valor, Color color) {
        this.valor = valor;
        this.color = color;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }

    @Override
    public String toString() {
        String colorTexto = (this.color == Color.ROJO) ? "ROJO" : "NEGRO";
        String padreValor = (this.padre != null) ? String.valueOf(this.padre.valor) : "null (Raíz)";
        return "Nodo[Valor=" + valor + ", Color=" + colorTexto + ", Padre=" + padreValor + "]";
    }

    // Método main de prueba de la clase NodoRojinegro
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE LA CLASE NODO ROJINEGRO ===");

        // Crear el nodo raíz (por propiedad, la raíz debe ser NEGRA)
        NodoRojinegro raiz = new NodoRojinegro(20, Color.NEGRO);

        // Crear hijo izquierdo (por defecto nace ROJO)
        NodoRojinegro hijoIzq = new NodoRojinegro(10);
        hijoIzq.padre = raiz;
        raiz.izquierdo = hijoIzq;

        // Crear hijo derecho (por defecto nace ROJO)
        NodoRojinegro hijoDer = new NodoRojinegro(30);
        hijoDer.padre = raiz;
        raiz.derecho = hijoDer;

        // Visualización de la jerarquía
        System.out.println("1. " + raiz);
        System.out.println("2. Hijo Izquierdo: " + raiz.izquierdo);
        System.out.println("3. Hijo Derecho:   " + raiz.derecho);

        // Verificación bidireccional
        System.out.println("\n=== VERIFICACIÓN DE RELACIONES PADRE-HIJO ===");
        System.out.println("El padre del hijo izquierdo (" + hijoIzq.valor + ") es la raíz: " 
                            + (hijoIzq.padre.valor == raiz.valor));
        System.out.println("El padre del hijo derecho (" + hijoDer.valor + ") es la raíz: " 
                            + (hijoDer.padre.valor == raiz.valor));
    }
}
