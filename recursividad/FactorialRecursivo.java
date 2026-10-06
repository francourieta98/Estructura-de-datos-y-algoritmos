public class FactorialRecursivo {

    /**
     * Calcula el factorial de un número entero no negativo de forma recursiva.
     *
     * El factorial puede resolverse recursivamente porque:
     * n! = n * (n - 1)!
     *
     * El caso base es cuando n == 0 o n == 1, porque ambos tienen factorial 1.
     *
     * @param n número del que se calcula el factorial
     * @return factorial de n
     * @throws IllegalArgumentException si n es negativo
     */
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El factorial no está definido para números negativos.");
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int[] valoresPrueba = {0, 1, 5, 7, 10};

        for (int valor : valoresPrueba) {
            System.out.println("factorial(" + valor + ") = " + factorial(valor));
        }

        // La recursión termina cuando n llega a 0 o 1, porque en ese caso se devuelve 1.
        // Esto evita una llamada infinita y garantiza que el problema se reduzca de forma correcta.
    }
}
