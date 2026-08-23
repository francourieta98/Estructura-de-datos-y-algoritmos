public class ContadorWhile {
    public static void main(String[] args) {
        // El contador comienza en 1.
        int contador = 1;

        // while repite el bloque mientras la condicion sea verdadera.
        // Esta es la condicion de corte: el ciclo termina cuando contador supera 10.
        while (contador <= 10) {
            System.out.println("Contador: " + contador);

            // Incremento: aumenta el contador en uno en cada repeticion.
            contador++;
        }

        // Si se olvidara el incremento, contador siempre valdria 1,
        // la condicion seguiria siendo verdadera y se produciria un ciclo infinito.
        System.out.println("El contador termino en: " + contador);
    }
}
