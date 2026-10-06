public class PotenciaRecursiva {

    /**
     * Calcula la potencia de un número sin usar Math.pow.
     * La idea es que una potencia es una multiplicación repetida del mismo valor.
     *
     * Ejemplo: 3^4 = 3 * 3 * 3 * 3
     *
     * Se reduce el exponente en cada llamada:
     * potencia(base, exp) = base * potencia(base, exp - 1)
     *
     * El caso base es cuando exp == 0, porque cualquier número distinto de 0
     * elevado a 0 es 1.
     *
     * @param base número base
     * @param exp  exponente no negativo
     * @return base elevado a exp
     * @throws IllegalArgumentException si el exponente es negativo
     */
    public static int potencia(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("El exponente no puede ser negativo.");
        }

        if (exp == 0) {
            return 1;
        }

        return base * potencia(base, exp - 1);
    }

    public static void main(String[] args) {
        int[][] pruebas = {
                { 2, 0 },
                { 5, 1 },
                { 3, 2 },
                { 4, 3 },
                { 2, 5 }
        };

        for (int[] prueba : pruebas) {
            int base = prueba[0];
            int exp = prueba[1];
            System.out.println(base + "^" + exp + " = " + potencia(base, exp));
        }

        // La recursión termina cuando el exponente llega a 0 y devuelve 1.
        // Así se evita una llamada infinita y se garantiza que el problema se reduzca
        // cada vez.
    }
}
