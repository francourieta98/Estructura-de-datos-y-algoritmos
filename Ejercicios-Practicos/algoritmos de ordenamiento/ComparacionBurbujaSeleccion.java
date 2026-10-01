import java.util.Arrays;

public class ComparacionBurbujaSeleccion {
    public static void main(String[] args) {
        int[] arregloOriginal = {8, 3, 5, 1, 9, 2};
        int[] arregloBurbuja = Arrays.copyOf(arregloOriginal, arregloOriginal.length);
        int[] arregloSeleccion = Arrays.copyOf(arregloOriginal, arregloOriginal.length);

        ResultadoOrdenamiento resultadoBurbuja = bubbleSort(arregloBurbuja);
        ResultadoOrdenamiento resultadoSeleccion = selectionSort(arregloSeleccion);

        System.out.println("Arreglo original: " + Arrays.toString(arregloOriginal));
        System.out.println("\nBurbuja compara elementos vecinos e intercambia los que estan en orden incorrecto.");
        mostrarResultado("Burbuja", arregloBurbuja, resultadoBurbuja);

        System.out.println("\nSeleccion busca el menor elemento restante y lo coloca al inicio de la parte desordenada.");
        mostrarResultado("Seleccion", arregloSeleccion, resultadoSeleccion);
    }

    public static ResultadoOrdenamiento bubbleSort(int[] array) {
        int comparaciones = 0;
        int intercambios = 0;

        for (int pasada = 0; pasada < array.length - 1; pasada++) {
            for (int indice = 0; indice < array.length - 1 - pasada; indice++) {
                comparaciones++;
                if (array[indice] > array[indice + 1]) {
                    int temporal = array[indice];
                    array[indice] = array[indice + 1];
                    array[indice + 1] = temporal;
                    intercambios++;
                }
            }
        }

        return new ResultadoOrdenamiento(comparaciones, intercambios);
    }

    public static ResultadoOrdenamiento selectionSort(int[] array) {
        int comparaciones = 0;
        int intercambios = 0;

        for (int inicio = 0; inicio < array.length - 1; inicio++) {
            int indiceMenor = inicio;

            for (int indice = inicio + 1; indice < array.length; indice++) {
                comparaciones++;
                if (array[indice] < array[indiceMenor]) {
                    indiceMenor = indice;
                }
            }

            if (indiceMenor != inicio) {
                int temporal = array[inicio];
                array[inicio] = array[indiceMenor];
                array[indiceMenor] = temporal;
                intercambios++;
            }
        }

        return new ResultadoOrdenamiento(comparaciones, intercambios);
    }

    private static void mostrarResultado(String nombre, int[] array, ResultadoOrdenamiento resultado) {
        System.out.println("Arreglo ordenado con " + nombre + ": " + Arrays.toString(array));
        System.out.println("Comparaciones: " + resultado.comparaciones);
        System.out.println("Intercambios: " + resultado.intercambios);
    }

    public static class ResultadoOrdenamiento {
        private final int comparaciones;
        private final int intercambios;

        public ResultadoOrdenamiento(int comparaciones, int intercambios) {
            this.comparaciones = comparaciones;
            this.intercambios = intercambios;
        }
    }
}