import java.util.Arrays;

public class ComparacionOrdenamientosSimples {
    public static void main(String[] args) {
        int[] arregloOriginal = {8, 3, 5, 1, 9, 2};
        int[] arregloBurbuja = Arrays.copyOf(arregloOriginal, arregloOriginal.length);
        int[] arregloSeleccion = Arrays.copyOf(arregloOriginal, arregloOriginal.length);
        int[] arregloInsercion = Arrays.copyOf(arregloOriginal, arregloOriginal.length);

        ResultadoOrdenamiento resultadoBurbuja = bubbleSort(arregloBurbuja);
        ResultadoOrdenamiento resultadoSeleccion = selectionSort(arregloSeleccion);
        ResultadoOrdenamiento resultadoInsercion = insertionSort(arregloInsercion);

        System.out.println("Arreglo original: " + Arrays.toString(arregloOriginal));
        System.out.println("Cada algoritmo recibe una copia identica del arreglo.");
        System.out.println("Las comparaciones cuentan solo comparaciones entre valores.");

        mostrarResultado("Bubble Sort", arregloBurbuja, resultadoBurbuja, "Intercambios");
        mostrarResultado("Selection Sort", arregloSeleccion, resultadoSeleccion, "Intercambios");
        mostrarResultado("Insertion Sort", arregloInsercion, resultadoInsercion, "Desplazamientos hacia la derecha");
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

    public static ResultadoOrdenamiento insertionSort(int[] array) {
        int comparaciones = 0;
        int desplazamientos = 0;

        for (int indice = 1; indice < array.length; indice++) {
            int numeroActual = array[indice];
            int posicion = indice - 1;

            while (posicion >= 0) {
                comparaciones++;
                if (array[posicion] <= numeroActual) {
                    break;
                }

                array[posicion + 1] = array[posicion];
                desplazamientos++;
                posicion--;
            }

            array[posicion + 1] = numeroActual;
        }

        return new ResultadoOrdenamiento(comparaciones, desplazamientos);
    }

    private static void mostrarResultado(
            String nombre,
            int[] array,
            ResultadoOrdenamiento resultado,
            String etiquetaMovimientos) {
        System.out.println("\n" + nombre + ": " + Arrays.toString(array));
        System.out.println("Comparaciones: " + resultado.comparaciones);
        System.out.println(etiquetaMovimientos + ": " + resultado.movimientos);
    }

    public static class ResultadoOrdenamiento {
        private final int comparaciones;
        private final int movimientos;

        public ResultadoOrdenamiento(int comparaciones, int movimientos) {
            this.comparaciones = comparaciones;
            this.movimientos = movimientos;
        }
    }
}