public class ContarOcurrencias {

    /**
     * Cuenta cuántas veces aparece un valor específico dentro del vector.
     * La estrategia consiste en recorrer el arreglo completo y acumular un contador
     * cada vez que se encuentra una coincidencia.
     *
     * @param vector arreglo de enteros
     * @param buscado valor a buscar
     * @return cantidad de veces que aparece buscado en vector
     * @throws IllegalArgumentException si el arreglo es nulo
     */
    public static int contarOcurrencias(int[] vector, int buscado) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser nulo.");
        }

        int contador = 0;

        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == buscado) {
                contador++;
            }
        }

        return contador;
    }

    public static void main(String[] args) {
        int[] vector = {3, 8, 3, 1, 3, 7, 3, 9};
        int buscado = 3;

        int cantidad = contarOcurrencias(vector, buscado);
        System.out.println("El valor " + buscado + " aparece " + cantidad + " veces en el vector.");

        // Es necesario recorrer todo el vector porque el valor buscado puede aparecer en
        // cualquier posición, y solo revisando cada elemento se puede determinar su cantidad total.
        // Complejidad temporal: O(n), ya que se recorre cada posición una sola vez.
        // Complejidad espacial: O(1), porque solo se usa un contador como variable auxiliar.
    }
}
