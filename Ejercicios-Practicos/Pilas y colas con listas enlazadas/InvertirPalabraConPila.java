import java.util.Scanner;

public class InvertirPalabraConPila {
    private static class Nodo {
        private final char caracter;
        private Nodo siguiente;

        private Nodo(char caracter, Nodo siguiente) {
            this.caracter = caracter;
            this.siguiente = siguiente;
        }
    }

    private static class PilaCaracteres {
        private Nodo head;

        private void apilar(char caracter) {
            head = new Nodo(caracter, head);
        }

        private char desapilar() {
            char caracter = head.caracter;
            head = head.siguiente;
            return caracter;
        }

        private boolean estaVacia() {
            return head == null;
        }
    }

    public static String invertir(String palabra) {
        PilaCaracteres pila = new PilaCaracteres();
        for (int indice = 0; indice < palabra.length(); indice++) {
            pila.apilar(palabra.charAt(indice));
        }

        StringBuilder invertida = new StringBuilder();
        while (!pila.estaVacia()) {
            invertida.append(pila.desapilar());
        }
        return invertida.toString();
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Ingresa una palabra para invertirla:");
        String palabra = entrada.nextLine();
        System.out.println("Se apilan los caracteres de izquierda a derecha.");
        System.out.println("La pila usa LIFO: el ultimo caracter que entra es el primero que sale,");
        System.out.println("por eso al desapilar se obtiene el orden inverso.");
        System.out.println();
        System.out.println("Palabra invertida: " + invertir(palabra));

        entrada.close();
    }
}
