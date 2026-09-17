import java.util.Scanner;

public class TablaMultiplicarFor {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingresa un numero: ");
        int numero = teclado.nextInt();

        System.out.println("Tabla de multiplicar del " + numero + ":");

        for (int multiplicador = 1; multiplicador <= 10; multiplicador++) {
            int resultado = numero * multiplicador;
            System.out.println(numero + " x " + multiplicador + " = " + resultado);
        }

        teclado.close();
    }
}