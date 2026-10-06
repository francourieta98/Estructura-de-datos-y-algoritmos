public class ContarDigitos {

    /**
     * Cuenta la cantidad de dígitos de un número entero positivo de manera
     * recursiva.
     *
     * Se reduce el problema usando división entera por 10:
     * 1234 -> 123 -> 12 -> 1
     *
     * Caso base: si el número es menor que 10, entonces tiene un solo dígito.
     *
     * @param n número entero positivo
     * @return cantidad de dígitos del número
     * @throws IllegalArgumentException si n es negativo o cero
     */
    public static int contarDigitos(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El número debe ser positivo.");
        }

        if (n < 10) {
            return 1;
        }

        return 1 + contarDigitos(n / 10);
    }

    public static void main(String[] args) {
        int[] pruebas = { 7, 25, 100, 12345, 987654321 };

        for (int valor : pruebas) {
            System.out.println("Número: " + valor + " -> Dígitos: " + contarDigitos(valor));
        }

        // La recursión termina cuando el número queda en un solo dígito, es decir n <
        // 10.
        // En ese punto la función devuelve 1 y se detiene correctamente.
    }
}
