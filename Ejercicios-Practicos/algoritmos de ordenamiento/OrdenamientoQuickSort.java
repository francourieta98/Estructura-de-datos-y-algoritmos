import java.util.Arrays;

public class OrdenamientoQuickSort {
    public static void main(String[] args) {
        int[] numeros = {8, 3, 1, 7, 0, 10, 2};

        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        System.out.println("Quicksort aplica divide y venceras: elige un pivote y separa el arreglo en dos partes.");
        System.out.println("Luego ordena recursivamente cada subarreglo; los de cero o un elemento ya estan ordenados.");

        quickSort(numeros, 0, numeros.length - 1);

        System.out.println("\nArreglo final ordenado: " + Arrays.toString(numeros));
    }

    public static void quickSort(int[] array, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }

        int pivote = array[inicio];
        int posicionPivote = particionar(array, inicio, fin);

        int[] subarregloIzquierdo = Arrays.copyOfRange(array, inicio, posicionPivote);
        int[] subarregloDerecho = Arrays.copyOfRange(array, posicionPivote + 1, fin + 1);

        System.out.println("\nPivote elegido (primer elemento): " + pivote);
        System.out.println("Subarreglo izquierdo: " + Arrays.toString(subarregloIzquierdo));
        System.out.println("Subarreglo derecho: " + Arrays.toString(subarregloDerecho));

        quickSort(array, inicio, posicionPivote - 1);
        quickSort(array, posicionPivote + 1, fin);
    }

    private static int particionar(int[] array, int inicio, int fin) {
        int pivote = array[inicio];
        int posicionMenores = inicio;

        for (int indice = inicio + 1; indice <= fin; indice++) {
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
}