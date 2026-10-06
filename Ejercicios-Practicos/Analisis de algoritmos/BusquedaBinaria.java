public class BusquedaBinaria {

    /**
     * Busca un elemento en un arreglo ordenado mediante búsqueda binaria.
     * Además, muestra paso a paso cómo cambian los índices izquierda, derecha y
     * medio.
     *
     * @param vector  arreglo ordenado
     * @param buscado elemento a buscar
     * @return la posición del elemento si existe; -1 si no existe
     * @throws IllegalArgumentException si el arreglo es nulo o está vacío
     */
    public static int busquedaBinaria(int[] vector, int buscado) {
        if (vector == null || vector.length == 0) {
            throw new IllegalArgumentException("El vector no puede ser nulo ni vacío.");
        }

        int izquierda = 0;
        int derecha = vector.length - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            System.out.println("izquierda=" + izquierda + ", derecha=" + derecha + ", medio=" + medio + ", valorMedio="
                    + vector[medio]);

            if (vector[medio] == buscado) {
                System.out.println("Se encontró el elemento en la posición " + medio + ".");
                return medio;
            }

            if (vector[medio] < buscado) {
                izquierda = medio + 1;
                System.out
                        .println("El valor buscado es mayor que el elemento central. Se descarta la mitad izquierda.");
            } else {
                derecha = medio - 1;
                System.out.println("El valor buscado es menor que el elemento central. Se descarta la mitad derecha.");
            }
        }

        System.out.println("El elemento no existe en el vector.");
        return -1;
    }

    public static void main(String[] args) {
        int[] vector = { 2, 5, 9, 12, 17, 24, 31, 38, 45 };
        int buscado = 24;

        System.out.println("Buscando el valor " + buscado + " mediante búsqueda binaria.");
        int posicion = busquedaBinaria(vector, buscado);

        if (posicion != -1) {
            System.out.println("Posición encontrada: " + posicion);
        }

        // La búsqueda binaria es más eficiente que la lineal cuando el arreglo está
        // ordenado,
        // porque a cada paso descarta la mitad del espacio posible.
        // Complejidad temporal: O(log n).
        // Complejidad espacial: O(1).
        // Requiere que el arreglo esté ordenado; si no lo estuviera, no podríamos
        // descartar mitades válidas.
    }
}
