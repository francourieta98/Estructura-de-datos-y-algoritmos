import java.util.Scanner;

public class OperacionesMetodosEjercicio {
    public static int sumar(int primerNumero, int segundoNumero) {
        return primerNumero + segundoNumero;
    }

    public static int restar(int primerNumero, int segundoNumero) {
        return primerNumero - segundoNumero;
    }

    public static int multiplicar(int primerNumero, int segundoNumero) {
        return primerNumero * segundoNumero;
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingresa el primer numero: ");
        int primerNumero = teclado.nextInt();

        System.out.print("Ingresa el segundo numero: ");
        int segundoNumero = teclado.nextInt();

        System.out.println("Suma: " + sumar(primerNumero, segundoNumero));
        System.out.println("Resta: " + restar(primerNumero, segundoNumero));
        System.out.println("Multiplicacion: " + multiplicar(primerNumero, segundoNumero));

        teclado.close();
    }
}