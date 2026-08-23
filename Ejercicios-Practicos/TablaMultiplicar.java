import java.util.Scanner;

public class TablaMultiplicar {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingresa un numero: ");
        int numero = teclado.nextInt();

        System.out.println("Tabla de multiplicar del " + numero + ":");

        // for repite una instruccion de forma controlada.
        // multiplicador es la variable de control del ciclo.
        for (int multiplicador = 1; multiplicador <= 10; multiplicador++) {
            // La condicion mantiene la repeticion mientras multiplicador sea <= 10.
            int resultado = numero * multiplicador;
            System.out.println(numero + " x " + multiplicador + " = " + resultado);
        }
        // El incremento ++ aumenta la variable de control en cada repeticion.

        teclado.close();
    }
}
