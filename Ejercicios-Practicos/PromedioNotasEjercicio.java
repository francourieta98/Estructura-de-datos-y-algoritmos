public class PromedioNotasEjercicio {
    public static void main(String[] args) {
        double[] notas = {8.5, 7.0, 9.5, 6.0, 10.0};
        double suma = 0;

        for (int indice = 0; indice < notas.length; indice++) {
            suma += notas[indice];
        }

        double promedio = suma / notas.length;

        System.out.println("Cantidad de notas: " + notas.length);
        System.out.println("Suma de las notas: " + suma);
        System.out.println("Promedio general: " + promedio);
    }
}