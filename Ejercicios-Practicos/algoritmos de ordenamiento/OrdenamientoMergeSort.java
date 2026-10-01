import java.util.Arrays;

public class OrdenamientoMergeSort {
    public static void main(String[] args) {
        int[] numeros = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        System.out.println("Division: MergeSort parte el arreglo y repite el proceso con cada mitad.");
        System.out.println("Fusion: combina las mitades ya ordenadas en un solo subarreglo ordenado.");

        mergeSort(numeros);

        System.out.println("\nArreglo final ordenado: " + Arrays.toString(numeros));
    }

    public static void mergeSort(int[] array) {
        if (array.length == 0) {
            System.out.println("Condicion de corte: el arreglo esta vacio.");
            return;
        }

        mergeSort(array, 0, array.length - 1, 0);
    }

    private static void mergeSort(int[] array, int inicio, int fin, int nivel) {
        String indentacion = crearIndentacion(nivel);

        if (inicio == fin) {
            System.out.println(indentacion + "Condicion de corte: queda un elemento " + array[inicio] + ".");
            return;
        }

        int medio = (inicio + fin) / 2;
        int[] parteIzquierda = Arrays.copyOfRange(array, inicio, medio + 1);
        int[] parteDerecha = Arrays.copyOfRange(array, medio + 1, fin + 1);

        System.out.println(indentacion + "Dividir " + Arrays.toString(Arrays.copyOfRange(array, inicio, fin + 1)));
        System.out.println(indentacion + "  Parte izquierda: " + Arrays.toString(parteIzquierda));
        System.out.println(indentacion + "  Parte derecha:   " + Arrays.toString(parteDerecha));

        mergeSort(array, inicio, medio, nivel + 1);
        mergeSort(array, medio + 1, fin, nivel + 1);

        parteIzquierda = Arrays.copyOfRange(array, inicio, medio + 1);
        parteDerecha = Arrays.copyOfRange(array, medio + 1, fin + 1);
        System.out.println(indentacion + "Fusionar " + Arrays.toString(parteIzquierda) + " y " + Arrays.toString(parteDerecha));

        fusionar(array, inicio, medio, fin);

        int[] resultadoFusion = Arrays.copyOfRange(array, inicio, fin + 1);
        System.out.println(indentacion + "Resultado de la fusion: " + Arrays.toString(resultadoFusion));
    }

    private static void fusionar(int[] array, int inicio, int medio, int fin) {
        int[] parteIzquierda = Arrays.copyOfRange(array, inicio, medio + 1);
        int[] parteDerecha = Arrays.copyOfRange(array, medio + 1, fin + 1);
        int indiceIzquierdo = 0;
        int indiceDerecho = 0;
        int indiceResultado = inicio;

        while (indiceIzquierdo < parteIzquierda.length && indiceDerecho < parteDerecha.length) {
            if (parteIzquierda[indiceIzquierdo] <= parteDerecha[indiceDerecho]) {
                array[indiceResultado++] = parteIzquierda[indiceIzquierdo++];
            } else {
                array[indiceResultado++] = parteDerecha[indiceDerecho++];
            }
        }

        while (indiceIzquierdo < parteIzquierda.length) {
            array[indiceResultado++] = parteIzquierda[indiceIzquierdo++];
        }

        while (indiceDerecho < parteDerecha.length) {
            array[indiceResultado++] = parteDerecha[indiceDerecho++];
        }
    }

    private static String crearIndentacion(int nivel) {
        StringBuilder indentacion = new StringBuilder();
        for (int indice = 0; indice < nivel; indice++) {
            indentacion.append("  ");
        }
        return indentacion.toString();
    }
}