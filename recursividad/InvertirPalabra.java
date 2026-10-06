public class InvertirPalabra {

    /**
     * Invierte una cadena de texto de forma recursiva.
     *
     * Caso base: si la cadena es vacía o tiene un solo carácter, ya está invertida.
     *
     * En cada llamada se separa el primer carácter y se invierte el resto:
     * invertir("hola") = invertir("ola") + "h"
     *
     * @param palabra cadena a invertir
     * @return palabra invertida
     */
    public static String invertirPalabra(String palabra) {
        if (palabra == null) {
            throw new IllegalArgumentException("La cadena no puede ser nula.");
        }

        if (palabra.length() <= 1) {
            return palabra;
        }

        char primer = palabra.charAt(0);
        String resto = palabra.substring(1);

        return invertirPalabra(resto) + primer;
    }

    public static void main(String[] args) {
        String[] pruebas = {"", "a", "hola", "reconocer", "java"};

        for (String palabra : pruebas) {
            System.out.println(palabra + " -> " + invertirPalabra(palabra));
        }

        // La recursión termina cuando la cadena queda vacía o con un solo carácter.
        // En ese punto la función devuelve la misma cadena y se reconstruye la inversa al volver.
    }
}
