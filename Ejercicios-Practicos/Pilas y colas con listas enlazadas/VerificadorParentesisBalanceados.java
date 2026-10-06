import java.util.Scanner;

public class VerificadorParentesisBalanceados {
    private static class Nodo {
        private final char parentesis;
        private Nodo siguiente;

        private Nodo(char parentesis, Nodo siguiente) {
            this.parentesis = parentesis;
            this.siguiente = siguiente;
        }
    }

    private static class PilaParentesis {
        private Nodo head;

        private void apilar(char parentesis) {
            head = new Nodo(parentesis, head);
        }

        private boolean estaVacia() {
            return head == null;
        }

        private void desapilar() {
            head = head.siguiente;
        }
    }

    public static boolean estanBalanceados(String expresion) {
        PilaParentesis aperturas = new PilaParentesis();

        for (int indice = 0; indice < expresion.length(); indice++) {
            char caracter = expresion.charAt(indice);
            if (caracter == '(') {
                aperturas.apilar(caracter);
            } else if (caracter == ')') {
                if (aperturas.estaVacia()) {
                    return false;
                }
                aperturas.desapilar();
            }
        }

        return aperturas.estaVacia();
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Escribe una expresion para verificar sus parentesis:");
        String expresion = entrada.nextLine();

        System.out.println("Cada '(' se guarda en una pila enlazada.");
        System.out.println("Al encontrar ')', se desapila una apertura para emparejarla.");
        System.out.println("Si aparece un cierre cuando la pila esta vacia, la expresion es invalida.");
        System.out.println("Al terminar, la pila tambien debe estar vacia: no pueden quedar aperturas sin cerrar.");
        System.out.println();

        if (estanBalanceados(expresion)) {
            System.out.println("Valida: los parentesis estan balanceados.");
        } else {
            System.out.println("Invalida: los parentesis no estan balanceados.");
        }

        entrada.close();
    }
}
