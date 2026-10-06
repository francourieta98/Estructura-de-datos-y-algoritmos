public class CompararVectores {

    /**
     * Determina si dos vectores son iguales.
     * La comparación se realiza elemento por elemento en paralelo.
     * Si encuentra una diferencia, termina inmediatamente.
     *
     * @param a primer vector
     * @param b segundo vector
     * @return true si son iguales, false si hay alguna diferencia
     * @throws IllegalArgumentException si alguno de los vectores es nulo
     */
    public static boolean sonIguales(int[] a, int[] b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Los vectores no pueden ser nulos.");
        }

        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] vector1 = {1, 2, 3, 4};
        int[] vector2 = {1, 2, 3, 5};

        boolean iguales = sonIguales(vector1, vector2);
        System.out.println("Los vectores son iguales: " + iguales);

        // La estrategia es recorrer ambos vectores en paralelo.
        // Si se detecta una diferencia, se corta la ejecución de inmediato.
        // Mejor caso: O(1), si difieren en la primera posición.
        // Peor caso: O(n), si todos los elementos coinciden hasta el final.
        // Caso promedio: O(n), porque la comparación a lo largo del arreglo sigue siendo lineal.
    }
}
