public class BusquedaVectorDesordenado {

    /**
     * Busca un elemento dentro de un arreglo desordenado mediante búsqueda
     * secuencial.
     * El método devuelve la posición en la que se encontró el elemento y también
     * informa cuántas posiciones fueron recorridas.
     *
     * @param vector  arreglo desordenado
     * @param buscado valor a buscar
     * @return un arreglo con dos valores: [posiciónEncontrada,
     *         posicionesRecorridas]
     *         si no se encuentra, la posición será -1
     * @throws IllegalArgumentException si el arreglo es nulo
     */
    public static int[] buscarElemento(int[] vector, int buscado) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser nulo.");
        }

        int posicionesRecorridas = 0;

        for (int i = 0; i < vector.length; i++) {
            posicionesRecorridas++;

            if (vector[i] == buscado) {
                return new int[] { i, posicionesRecorridas };
            }
        }

        return new int[] { -1, posicionesRecorridas };
    }

    public static void main(String[] args) {
        int[] vector = { 9, 4, 7, 1, 8, 3, 6, 5 };
        int buscado = 8;

        int[] resultado = buscarElemento(vector, buscado);

        if (resultado[0] == -1) {
            System.out.println("El elemento " + buscado + " no existe en el vector.");
        } else {
            System.out.println("El elemento " + buscado + " fue encontrado en la posición " + resultado[0] + ".");
        }

        System.out.println("Posiciones recorridas: " + resultado[1]);

        // En un vector desordenado, la búsqueda secuencial es la estrategia adecuada
        // porque no depende del orden de los elementos. Una búsqueda binaria no sirve
        // aquí porque requiere que el arreglo esté ordenado.
        // Mejor caso: O(1), si el elemento está en la primera posición.
        // Peor caso: O(n), si el elemento está al final o no existe.
        // Caso promedio: O(n), ya que en promedio se recorre una parte importante del
        // vector.
    }
}
