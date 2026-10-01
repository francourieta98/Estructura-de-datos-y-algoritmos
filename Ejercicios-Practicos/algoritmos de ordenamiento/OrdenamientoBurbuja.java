import java.util.Arrays;

public class OrdenamientoBurbuja {
    public static void main(String[] args) {
        int[] numeros = {8, 3, 5, 1, 9, 2};

        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        System.out.println("En cada pasada se comparan numeros vecinos. Si el de la izquierda es mayor, se intercambian; por eso el mayor de la parte desordenada va avanzando hacia el final.");

        for (int pasada = 0; pasada < numeros.length - 1; pasada++) {
            for (int indice = 0; indice < numeros.length - 1 - pasada; indice++) {
                if (numeros[indice] > numeros[indice + 1]) {
                    int temporal = numeros[indice];
                    numeros[indice] = numeros[indice + 1];
                    numeros[indice + 1] = temporal;
                }
            }

            System.out.println("Despues de la pasada " + (pasada + 1) + ": " + Arrays.toString(numeros));
        }

        System.out.println("Arreglo ordenado: " + Arrays.toString(numeros));
    }
}