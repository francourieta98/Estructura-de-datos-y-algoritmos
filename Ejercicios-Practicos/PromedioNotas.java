public class PromedioNotas {
    public static void main(String[] args) {
        // Un array almacena varias notas del mismo tipo en una sola estructura.
        double[] notas = {8.5, 7.0, 9.5, 6.0, 10.0};

        // El acumulador comienza en cero y guardara la suma de las notas.
        double acumulador = 0;

        // length indica la cantidad de elementos que tiene el array.
        // El indice comienza en 0 porque los arrays se indexan desde cero.
        for (int indice = 0; indice < notas.length; indice++) {
            // El recorrido con for visita cada nota usando su indice.
            acumulador += notas[indice];
        }

        // El promedio se calcula dividiendo la suma por la cantidad de notas.
        double promedio = acumulador / notas.length;

        System.out.println("Cantidad de notas: " + notas.length);
        System.out.println("Suma de las notas: " + acumulador);
        System.out.println("Promedio: " + promedio);
    }
}
