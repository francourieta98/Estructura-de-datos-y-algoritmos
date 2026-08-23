import java.util.Scanner;

public class OperacionesMetodos {
    // sumar recibe dos parametros y devuelve el resultado con return.
    public static int sumar(int primerNumero, int segundoNumero) {
        return primerNumero + segundoNumero;
    }

    // restar tiene una responsabilidad: calcular la diferencia entre dos numeros.
    public static int restar(int primerNumero, int segundoNumero) {
        return primerNumero - segundoNumero;
    }

    // multiplicar reutiliza la misma estructura para otra operacion.
    public static int multiplicar(int primerNumero, int segundoNumero) {
        return primerNumero * segundoNumero;
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingresa el primer numero: ");
        int primerNumero = teclado.nextInt();

        System.out.print("Ingresa el segundo numero: ");
        int segundoNumero = teclado.nextInt();

        // El main coordina la entrada y usa los metodos sin repetir sus calculos.
        System.out.println("Suma: " + sumar(primerNumero, segundoNumero));
        System.out.println("Resta: " + restar(primerNumero, segundoNumero));
        System.out.println("Multiplicacion: " + multiplicar(primerNumero, segundoNumero));

        teclado.close();
    }
}
