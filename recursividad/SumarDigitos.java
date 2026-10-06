public class SumarDigitos {

    /**
     * Suma los dígitos de un número entero positivo de forma recursiva.
     *
     * El último dígito se obtiene con n % 10, y el número se reduce con n / 10.
     *
     * Caso base: si n < 10, el número tiene un solo dígito y su suma es el mismo
     * valor.
     *
     * @param n número entero positivo
     * @return suma de los dígitos de n
     * @throws IllegalArgumentException si n es menor o igual a 0
     */
    public static int sumarDigitos(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El número debe ser positivo.");
        }

        if (n < 10) {
            return n;
        }

        return (n % 10) + sumarDigitos(n / 10);
    }

    public static void main(String[] args) {
        int[] pruebas = { 7, 25, 123, 987, 4567 };

        for (int valor : pruebas) {
            System.out.println("Número: " + valor + " -> Suma de dígitos: " + sumarDigitos(valor));
        }

        // La recursión termina cuando el número queda en un solo dígito.
        // Una vez que n < 10, se devuelve el propio dígito y la llamada finaliza
        // correctamente.
    }
}
