import java.util.Scanner;

public class CalculadoraBasica {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("=== Calculadora basica ===");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");
        System.out.println("5. Calcular modulo");

        System.out.print("Elige una operacion: ");
        int operacion = teclado.nextInt();

        System.out.print("Ingresa el primer numero: ");
        double primerNumero = teclado.nextDouble();

        System.out.print("Ingresa el segundo numero: ");
        double segundoNumero = teclado.nextDouble();

        switch (operacion) {
            case 1:
                System.out.println("Resultado de la suma: " + (primerNumero + segundoNumero));
                break;
            case 2:
                System.out.println("Resultado de la resta: " + (primerNumero - segundoNumero));
                break;
            case 3:
                System.out.println("Resultado de la multiplicacion: " + (primerNumero * segundoNumero));
                break;
            case 4:
                if (segundoNumero == 0) {
                    System.out.println("Error: no se puede dividir por cero.");
                } else {
                    System.out.println("Resultado de la division: " + (primerNumero / segundoNumero));
                }
                break;
            case 5:
                if (segundoNumero == 0) {
                    System.out.println("Error: no se puede calcular el modulo con cero.");
                } else {
                    System.out.println("Resultado del modulo: " + (primerNumero % segundoNumero));
                }
                break;
            default:
                System.out.println("Error: la operacion elegida no es valida.");
        }

        teclado.close();
    }
}