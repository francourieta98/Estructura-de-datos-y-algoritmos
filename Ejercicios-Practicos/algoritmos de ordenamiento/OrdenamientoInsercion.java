import java.util.Arrays;

public class OrdenamientoInsercion {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 5, 4, 6, 7};

        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        System.out.println("Insertion Sort mantiene ordenada la parte izquierda e inserta cada numero en su lugar.");
        System.out.println("Como el arreglo ya esta casi ordenado, pocos elementos deben desplazarse para abrir espacio.");

        int desplazamientos = insertionSort(numeros);

        System.out.println("Arreglo ordenado: " + Arrays.toString(numeros));
        System.out.println("Desplazamientos de elementos hacia la derecha: " + desplazamientos);
    }

    public static int insertionSort(int[] array) {
        int desplazamientos = 0;

        for (int indice = 1; indice < array.length; indice++) {
            int numeroActual = array[indice];
            int posicion = indice - 1;

            while (posicion >= 0 && array[posicion] > numeroActual) {
                array[posicion + 1] = array[posicion];
                desplazamientos++;
                posicion--;
            }

            array[posicion + 1] = numeroActual;
        }

        return desplazamientos;
    }
}