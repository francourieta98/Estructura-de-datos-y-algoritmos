public class TiposPrimitivos {
    public static void main(String[] args) {
        // int: almacena números enteros de uso común.
        int edad = 20;

        // double: almacena números decimales con mayor precisión.
        double precio = 19.99;

        // float: almacena números decimales con menor precisión que double.
        // La letra f indica que el literal es de tipo float.
        float temperatura = 25.5f;

        // boolean: solo puede tener los valores true o false.
        boolean esEstudiante = true;

        // char: almacena un único carácter entre comillas simples.
        char inicial = 'A';

        // long: almacena enteros grandes.
        // La letra L indica que el literal es de tipo long.
        long poblacionMundial = 8_000_000_000L;

        // byte: almacena enteros pequeños, desde -128 hasta 127.
        byte nivel = 100;

        // short: almacena enteros más grandes que byte y más pequeños que int.
        short anio = 2026;

        System.out.println("int: " + edad);
        System.out.println("double: " + precio);
        System.out.println("float: " + temperatura);
        System.out.println("boolean: " + esEstudiante);
        System.out.println("char: " + inicial);
        System.out.println("long: " + poblacionMundial);
        System.out.println("byte: " + nivel);
        System.out.println("short: " + anio);
    }
}
