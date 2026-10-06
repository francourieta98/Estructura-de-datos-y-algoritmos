import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ValidadorParentesis {
    public static boolean estanBalanceados(String expresion) {
        Deque<Character> abiertos = new ArrayDeque<Character>();

        for (int indice = 0; indice < expresion.length(); indice++) {
            char caracter = expresion.charAt(indice);

            if (caracter == '(') {
                abiertos.push(caracter);
            } else if (caracter == ')') {
                if (abiertos.isEmpty()) {
                    return false;
                }
                abiertos.pop();
            }
        }

        return abiertos.isEmpty();
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Escribe una expresion matematica para revisar sus parentesis:");
        String expresion = entrada.nextLine();

        System.out.println("Se apila cada parentesis abierto '(' que aparece.");
        System.out.println("Al encontrar ')' se realiza pop para emparejarlo con el ultimo '(' abierto.");
        System.out.println("Si no hay un '(' disponible al encontrar ')', o quedan '(' al final, la expresion es invalida.");
        System.out.println();

        if (estanBalanceados(expresion)) {
            System.out.println("Valido: los parentesis estan balanceados.");
        } else {
            System.out.println("Invalido: los parentesis no estan balanceados.");
        }

        entrada.close();
    }
}
