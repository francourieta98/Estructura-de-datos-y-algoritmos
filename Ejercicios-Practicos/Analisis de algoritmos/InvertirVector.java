import java.util.Arrays;

public class InvertirVector {

    /**
     * Solución 1: usa un vector auxiliar para invertir el arreglo original.
     * Complejidad temporal O(n), complejidad espacial O(n).
     */
    public static int[] invertirConAuxiliar(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser nulo.");
        }

        int[] invertido = new int[vector.length];

        for (int i = 0; i < vector.length; i++) {
            invertido[i] = vector[vector.length - 1 - i];
        }

        return invertido;
    }

    /**
     * Solución 2: invierte el mismo arreglo sin usar un vector adicional grande.
     * Usa dos índices desde los extremos y hace intercambio in-place.
     * Complejidad temporal O(n), complejidad espacial O(1).
     */
    public static void invertirInPlace(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser nulo.");
        }

        int izquierda = 0;
        int derecha = vector.length - 1;

        while (izquierda < derecha) {
            int temporal = vector[izquierda];
            vector[izquierda] = vector[derecha];
            vector[derecha] = temporal;

            izquierda++;
            derecha--;
        }
    }

    public static void main(String[] args) {
        int[] vector = {1, 2, 3, 4, 5};

        int[] invertidoAux = invertirConAuxiliar(vector);
        System.out.println("Con vector auxiliar: " + Arrays.toString(invertidoAux));

        int[] vectorInPlace = {1, 2, 3, 4, 5};
        invertirInPlace(vectorInPlace);
        System.out.println("In-place: " + Arrays.toString(vectorInPlace));

        // Comparación:
        // - Con vector auxiliar: más simple de entender, pero usa O(n) de memoria adicional.
        // - In-place: más eficiente en memoria, porque usa O(1) de espacio extra.
        // Ambas tienen O(n) de tiempo, pero la variante in-place es preferible cuando se quiere ahorrar memoria.
    }
}
