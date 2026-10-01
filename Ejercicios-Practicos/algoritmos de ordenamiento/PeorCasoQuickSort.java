import java.util.Arrays;

public class PeorCasoQuickSort {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7};
        Estadisticas estadisticas = new Estadisticas();

        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        System.out.println("Al elegir el primer elemento como pivote en un arreglo ordenado, el pivote es siempre el menor.");
        System.out.println("Cada particion deja un lado vacio y otro casi completo, por lo que Quicksort se degrada a O(n^2).");

        quickSort(numeros, 0, numeros.length - 1, estadisticas);

        System.out.println("\nArreglo final: " + Arrays.toString(numeros));
        System.out.println("Llamadas recursivas (incluye casos base, no la llamada inicial): " + estadisticas.llamadasRecursivas);
        System.out.println("Comparaciones durante las particiones: " + estadisticas.comparaciones);
    }

    private static void quickSort(int[] array, int inicio, int fin, Estadisticas estadisticas) {
        if (inicio >= fin) {
            return;
        }

        int pivote = array[inicio];
        int posicionPivote = particionar(array, inicio, fin, estadisticas);

        int[] subarregloIzquierdo = Arrays.copyOfRange(array, inicio, posicionPivote);
        int[] subarregloDerecho = Arrays.copyOfRange(array, posicionPivote + 1, fin + 1);

        System.out.println("\nPivote elegido: " + pivote);
        System.out.println("Subarreglo izquierdo: " + Arrays.toString(subarregloIzquierdo));
        System.out.println("Subarreglo derecho: " + Arrays.toString(subarregloDerecho));

        estadisticas.llamadasRecursivas += 2;
        quickSort(array, inicio, posicionPivote - 1, estadisticas);
        quickSort(array, posicionPivote + 1, fin, estadisticas);
    }

    private static int particionar(int[] array, int inicio, int fin, Estadisticas estadisticas) {
        int pivote = array[inicio];
        int posicionMenores = inicio;

        for (int indice = inicio + 1; indice <= fin; indice++) {
            estadisticas.comparaciones++;
            if (array[indice] <= pivote) {
                posicionMenores++;
                intercambiar(array, posicionMenores, indice);
            }
        }

        intercambiar(array, inicio, posicionMenores);
        return posicionMenores;
    }

    private static void intercambiar(int[] array, int primero, int segundo) {
        int temporal = array[primero];
        array[primero] = array[segundo];
        array[segundo] = temporal;
    }

    private static class Estadisticas {
        private int llamadasRecursivas;
        private int comparaciones;
    }
}