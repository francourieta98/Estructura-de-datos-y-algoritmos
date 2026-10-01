import java.util.Arrays;

public class OrdenamientoNombresSelection {
    public static void main(String[] args) {
        String[] nombres = {"Lucia", "Ana", "Pedro", "Juan"};

        System.out.println("Arreglo original: " + Arrays.toString(nombres));
        System.out.println("Para ordenar textos usamos compareTo en lugar de comparar con > o < como con numeros.");
        System.out.println("compareTo indica si un nombre aparece antes o despues alfabeticamente que otro.");

        selectionSort(nombres);

        System.out.println("Arreglo ordenado: " + Arrays.toString(nombres));
    }

    public static void selectionSort(String[] nombres) {
        for (int inicio = 0; inicio < nombres.length - 1; inicio++) {
            int indiceMenor = inicio;

            for (int indice = inicio + 1; indice < nombres.length; indice++) {
                if (nombres[indice].compareTo(nombres[indiceMenor]) < 0) {
                    indiceMenor = indice;
                }
            }

            String temporal = nombres[inicio];
            nombres[inicio] = nombres[indiceMenor];
            nombres[indiceMenor] = temporal;

            System.out.println("Despues de la pasada " + (inicio + 1) + ": " + Arrays.toString(nombres));
        }
    }
}