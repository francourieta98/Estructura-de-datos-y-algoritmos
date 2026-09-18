public class ContadorWhileEjercicio {
    public static void main(String[] args) {
        int contador = 1;

        while (contador <= 10) {
            System.out.println("Contador: " + contador);
            contador++;
        }

        System.out.println("El contador termino en: " + contador);
    }
}