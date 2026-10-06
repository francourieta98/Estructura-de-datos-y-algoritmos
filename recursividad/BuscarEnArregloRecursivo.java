public class BuscarEnArregloRecursivo {

    /**
     * Busca un valor dentro de un arreglo de enteros de forma recursiva.
     * La búsqueda comienza en la posición 0.
     *
     * @param arreglo Arreglo donde se busca el valor.
     * @param indice Posición actual en la que se está buscando.
     * @param valor Valor a buscar.
     * @return El índice donde se encuentra el valor, o -1 si no existe.
     */
    public static int buscar(int[] arreglo, int indice, int valor) {
        if (arreglo == null || arreglo.length == 0) {
            return -1;
        }

        if (indice >= arreglo.length) {
            return -1;
        }

        if (arreglo[indice] == valor) {
            return indice;
        }

        return buscar(arreglo, indice + 1, valor);
    }

    public static void main(String[] args) {
        int[] arreglo = {3, 7, 1, 9};

        System.out.println(buscar(arreglo, 0, 1));
        System.out.println(buscar(arreglo, 0, 9));
        System.out.println(buscar(arreglo, 0, 5));

        int[] arregloVacio = {};
        System.out.println(buscar(arregloVacio, 0, 4));
    }
}
