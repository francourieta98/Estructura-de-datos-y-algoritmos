public class SumaRecursiva {

    /**
     * Calcula la suma de los números desde n hasta 1 de forma recursiva.
     *
     * La suma puede pensarse como:
     * suma(n) = n + suma(n - 1)
     *
     * El caso base es n == 0, porque la suma de cero elementos es 0.
     *
     * @param n número desde el cual se suman los valores hasta 1
     * @return la suma de n + (n-1) + ... + 1
     * @throws IllegalArgumentException si n es negativo
     */
    public static int suma(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El valor de n no puede ser negativo.");
        }

        if (n == 0) {
            return 0;
        }

        return n + suma(n - 1);
    }

    public static void main(String[] args) {
        int[] valoresPrueba = { 0, 1, 5, 10, 15 };

        for (int valor : valoresPrueba) {
            System.out.println("suma(" + valor + ") = " + suma(valor));
        }

        // La recursión termina cuando n llega a 0, porque en ese punto se devuelve 0.
        // Así se evita una llamada infinita y se garantiza la reducción del problema.
    }
}
