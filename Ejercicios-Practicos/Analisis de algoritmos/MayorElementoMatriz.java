public class MayorElementoMatriz {

    /**
     * Encuentra el mayor elemento de una matriz recorriendo cada posición.
     * Mantiene una variable llamada mayor, que se actualiza cada vez que aparece
     * un valor superior al máximo encontrado hasta el momento.
     *
     * @param matriz matriz de enteros
     * @return el mayor valor contenido en la matriz
     * @throws IllegalArgumentException si la matriz es nula o vacía
     */
    public static int encontrarMayor(int[][] matriz) {
        if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
            throw new IllegalArgumentException("La matriz no puede ser nula ni vacía.");
        }

        int mayor = matriz[0][0];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] > mayor) {
                    mayor = matriz[i][j];
                }
            }
        }

        return mayor;
    }

    public static void main(String[] args) {
        int[][] matriz = {
            {4, 1, 9},
            {7, 12, 3},
            {6, 8, 10}
        };

        int mayor = encontrarMayor(matriz);
        System.out.println("El mayor elemento de la matriz es: " + mayor);

        // Es necesario recorrer todos los elementos porque el valor máximo puede estar en cualquier posición.
        // La complejidad temporal es O(m x n), donde m es la cantidad de filas y n la cantidad de columnas.
        // La complejidad espacial es O(1), porque solo se usa una variable para guardar el máximo.
    }
}
