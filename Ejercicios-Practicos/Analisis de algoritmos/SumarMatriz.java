public class SumarMatriz {

    /**
     * Suma todos los elementos de una matriz recorrida fila por fila y columna por
     * columna.
     * Además, contabiliza cuántas operaciones de suma se realizaron.
     *
     * @param matriz matriz de enteros
     * @return un arreglo con dos valores: [sumaTotal, operacionesRealizadas]
     * @throws IllegalArgumentException si la matriz es nula
     */
    public static int[] sumarMatriz(int[][] matriz) {
        if (matriz == null) {
            throw new IllegalArgumentException("La matriz no puede ser nula.");
        }

        int sumaTotal = 0;
        int operacionesRealizadas = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                sumaTotal += matriz[i][j];
                operacionesRealizadas++;
            }
        }

        return new int[] { sumaTotal, operacionesRealizadas };
    }

    public static void main(String[] args) {
        int[][] matriz = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        int[] resultado = sumarMatriz(matriz);
        System.out.println("La suma total de la matriz es: " + resultado[0]);
        System.out.println("Operaciones realizadas: " + resultado[1]);

        // Se recorre la matriz fila por fila y columna por columna para visitar cada
        // valor exactamente una vez.
        // La complejidad temporal es O(m x n), donde m es la cantidad de filas y n la
        // cantidad de columnas.
        // La complejidad espacial es O(1), porque solo se usan variables auxiliares
        // para acumular y contar.
    }
}
