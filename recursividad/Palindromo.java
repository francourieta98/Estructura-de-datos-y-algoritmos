public class Palindromo {

    /**
     * Determina si una palabra es palíndromo de manera recursiva.
     *
     * Un palíndromo se lee igual al derecho y al revés, por eso se comparan
     * el primer y último carácter. Si son distintos, ya no puede ser palíndromo.
     *
     * Si coinciden, se elimina ese par de extremos y se sigue comprobando recursivamente.
     *
     * @param palabra cadena a evaluar
     * @return true si es palíndromo, false si no lo es
     */
    public static boolean esPalindromo(String palabra) {
        if (palabra == null) {
            throw new IllegalArgumentException("La cadena no puede ser nula.");
        }

        if (palabra.length() <= 1) {
            return true;
        }

        if (palabra.charAt(0) != palabra.charAt(palabra.length() - 1)) {
            return false;
        }

        return esPalindromo(palabra.substring(1, palabra.length() - 1));
    }

    public static void main(String[] args) {
        String[] pruebas = {"", "a", "oso", "radar", "hola", "reconocer"};

        for (String palabra : pruebas) {
            System.out.println(palabra + " -> " + esPalindromo(palabra));
        }

        // La recursión termina cuando la cadena queda vacía o con un solo carácter.
        // En esos casos, la palabra es palíndromo por definición.
    }
}
