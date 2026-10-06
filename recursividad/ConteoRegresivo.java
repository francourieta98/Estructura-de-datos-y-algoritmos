public class ConteoRegresivo {

    /**
     * Imprime un conteo desde n hasta 0 de forma recursiva.
     *
     * Esta función no devuelve un valor, sino que realiza una tarea de salida.
     * Su objetivo es imprimir números por pantalla.
     *
     * El caso base es cuando n == 0, porque allí se imprime 0 y termina la recursión.
     * En cada llamada se reduce el valor con n - 1 para acercarse al caso base.
     *
     * @param n valor inicial del conteo
     */
    public static void conteoRegresivo(int n) {
        if (n < 0) {
            return;
        }

        if (n == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(n);
        conteoRegresivo(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("Conteo desde 5:");
        conteoRegresivo(5);

        System.out.println("\nConteo desde 3:");
        conteoRegresivo(3);

        System.out.println("\nConteo desde 0:");
        conteoRegresivo(0);

        // Si se usara n + 1 en lugar de n - 1, la función nunca se acercaría al caso base.
        // Eso haría que la recursión no terminara y eventualmente provocaría un desbordamiento de pila.
    }
}
