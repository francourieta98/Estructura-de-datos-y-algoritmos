public class MultiplicacionRecursiva {

    /**
     * Multiplica dos números enteros sin usar el operador '*'.
     * La idea es representar la multiplicación como sumas repetidas.
     *
     * Ejemplo: 4 * 3 = 4 + 4 + 4
     *
     * En cada llamada recursiva se reduce el segundo factor:
     * multiplicar(a, b) = a + multiplicar(a, b - 1)
     *
     * El caso base es cuando alguno de los factores es 0, porque cualquier valor
     * multiplicado por 0 debe dar 0.
     *
     * @param a primer factor
     * @param b segundo factor
     * @return producto de a y b
     */
    public static int multiplicar(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        if (b < 0 || a < 0) {
            throw new IllegalArgumentException("Los factores deben ser no negativos.");
        }

        return a + multiplicar(a, b - 1);
    }

    public static void main(String[] args) {
        int[][] pruebas = {
            {0, 7},
            {7, 0},
            {4, 3},
            {5, 2},
            {6, 6}
        };

        for (int[] prueba : pruebas) {
            int a = prueba[0];
            int b = prueba[1];
            System.out.println(a + " * " + b + " = " + multiplicar(a, b));
        }

        // La recursión termina cuando uno de los factores es 0, porque la función devuelve 0.
        // Así se evita una llamada infinita y se garantiza la reducción del problema.
    }
}
