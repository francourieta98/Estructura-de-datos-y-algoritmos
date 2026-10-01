import java.util.Arrays;

public class OrdenamientoShellSort {
    public static void main(String[] args) {
        int[] numeros = {35, 12, 43, 8, 51, 23, 6, 18};

        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        System.out.println("ShellSort compara elementos separados por un gap, no solo elementos vecinos.");
        System.out.println("Los saltos grandes acercan valores a su zona; al reducir el gap, Insertion Sort termina el ordenamiento con menos movimientos.");

        shellSort(numeros);

        System.out.println("Arreglo ordenado: " + Arrays.toString(numeros));
    }

    public static void shellSort(int[] array) {
        int numeroPasada = 0;

        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            System.out.println("\nEtapa con gap = " + gap);

            for (int indice = gap; indice < array.length; indice++) {
                int numeroActual = array[indice];
                int posicion = indice;

                while (posicion >= gap && array[posicion - gap] > numeroActual) {
                    array[posicion] = array[posicion - gap];
                    posicion -= gap;
                }

                array[posicion] = numeroActual;
                numeroPasada++;
                System.out.println("Pasada " + numeroPasada + " (gap = " + gap + "): " + Arrays.toString(array));
            }
        }
    }
}