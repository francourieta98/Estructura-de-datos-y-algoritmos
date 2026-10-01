import java.util.Arrays;

public class RankingPuntajes {
    public static void main(String[] args) {
        Jugador[] jugadores = {
            new Jugador("Ana", 1200),
            new Jugador("Pedro", 900),
            new Jugador("Lucia", 1500)
        };

        System.out.println("Jugadores recibidos: " + Arrays.toString(jugadores));
        System.out.println("Algoritmo elegido: Insertion Sort.");
        System.out.println("Cada jugador se inserta en su posicion del ranking. Es claro y eficiente para listas pequenas o casi ordenadas.");
        System.out.println("Si hay empate, se conserva el orden original. Para listas muy grandes conviene un algoritmo O(n log n).");

        insertionSort(jugadores);

        System.out.println("\nRanking final:");
        mostrarRanking(jugadores);
    }

    public static void insertionSort(Jugador[] jugadores) {
        for (int indice = 1; indice < jugadores.length; indice++) {
            Jugador jugadorActual = jugadores[indice];
            int posicion = indice - 1;

            while (posicion >= 0 && jugadores[posicion].puntaje < jugadorActual.puntaje) {
                jugadores[posicion + 1] = jugadores[posicion];
                posicion--;
            }

            jugadores[posicion + 1] = jugadorActual;
            System.out.println("Despues de insertar " + jugadorActual.nombre + ": " + Arrays.toString(jugadores));
        }
    }

    private static void mostrarRanking(Jugador[] jugadores) {
        for (int indice = 0; indice < jugadores.length; indice++) {
            System.out.println((indice + 1) + ". " + jugadores[indice].nombre + " - " + jugadores[indice].puntaje + " puntos");
        }
    }

    public static class Jugador {
        private final String nombre;
        private final int puntaje;

        public Jugador(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }

        @Override
        public String toString() {
            return nombre + " (" + puntaje + ")";
        }
    }
}