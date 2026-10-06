public class MinimoVector {

    /**
     * Busca el valor mínimo de un arreglo de enteros.
     * La estrategia utilizada es un recorrido lineal del arreglo.
     * 
     * @param numeros arreglo de enteros
     * @return el valor mínimo del arreglo
     * @throws IllegalArgumentException si el arreglo es nulo o está vacío
     */
    public static int encontrarMinimo(int[] numeros) {
        if (numeros == null || numeros.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo ni vacío.");
        }

        // Se inicializa con el primer elemento del arreglo.
        int minimo = numeros[0];

        // Se recorre el arreglo desde el segundo elemento.
        // En cada paso, si se encuentra un valor menor, se actualiza el mínimo.
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] < minimo) {
                minimo = numeros[i];
            }
        }

        return minimo;
    }

    public static void main(String[] args) {
        int[] numeros = { 12, -4, 8, 3, -9, 14, 0 };

        int minimo = encontrarMinimo(numeros);
        System.out.println("El valor mínimo del vector es: " + minimo);

        // No es necesario ordenar el vector para encontrar el mínimo.
        // Ordenar el arreglo requiere más trabajo y más pasos, mientras que
        // esta solución solo recorre cada elemento una vez.
        // La complejidad temporal es O(n) y la espacial es O(1).
    }
}
