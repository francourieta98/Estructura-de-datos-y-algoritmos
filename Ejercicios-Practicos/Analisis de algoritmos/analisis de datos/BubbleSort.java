import java.util.Arrays;

public class BubbleSort {

    /**
     * Ordena un vector utilizando el algoritmo Bubble Sort.
     * Además, cuenta cuántas comparaciones e intercambios se realizan.
     *
     * @param vector arreglo a ordenar
     * @return un arreglo con [comparaciones, intercambios]
     * @throws IllegalArgumentException si el vector es nulo
     */
    public static int[] bubbleSort(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser nulo.");
        }

        int comparaciones = 0;
        int intercambios = 0;
        int n = vector.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparaciones++;

                if (vector[j] > vector[j + 1]) {
                    int temporal = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = temporal;
                    intercambios++;
                }
            }
        }

        return new int[] { comparaciones, intercambios };
    }

    public static void main(String[] args) {
        int[] vector = {5, 1, 4, 2, 8};

        int[] estadisticas = bubbleSort(vector);

        System.out.println("Vector ordenado: " + Arrays.toString(vector));
        System.out.println("Comparaciones: " + estadisticas[0]);
        System.out.println("Intercambios: " + estadisticas[1]);

        // Bubble Sort es útil principalmente para fines didácticos o arreglos pequeños,
        // porque compara y reacomoda elementos repetidamente.
        // En el peor caso y en el caso promedio, su complejidad es O(n^2).
        // Con optimización, el mejor caso puede ser O(n), si el arreglo ya está ordenado.
    }
}
