import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        // Scanner permite leer los datos ingresados por teclado.
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

        double resultado;

        // Los operadores aritmeticos realizan calculos con los dos numeros.
        switch (operacion) {
            case 1:
                // Suma: combina dos valores usando el operador +.
                resultado = primerNumero + segundoNumero;
                System.out.println("Resultado de la suma: " + resultado);
                break;
            case 2:
                // Resta: calcula la diferencia usando el operador -.
                resultado = primerNumero - segundoNumero;
                System.out.println("Resultado de la resta: " + resultado);
                break;
            case 3:
                // Multiplicacion: calcula el producto usando el operador *.
                resultado = primerNumero * segundoNumero;
                System.out.println("Resultado de la multiplicacion: " + resultado);
                break;
            case 4:
                // Division: calcula el cociente usando el operador /.
                if (segundoNumero == 0) {
                    System.out.println("Error: no se puede dividir por cero.");
                } else {
                    resultado = primerNumero / segundoNumero;
                    System.out.println("Resultado de la division: " + resultado);
                }
                break;
            case 5:
                // Modulo: obtiene el resto usando el operador %.
                if (segundoNumero == 0) {
                    System.out.println("Error: no se puede calcular el modulo con cero.");
                } else {
                    resultado = primerNumero % segundoNumero;
                    System.out.println("Resultado del modulo: " + resultado);
                }
                break;
            default:
                System.out.println("Error: la operacion elegida no es valida.");
        }

        teclado.close();
    }
}
