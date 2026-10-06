public class EjemploPilaGenerica {
    public static void main(String[] args) {
        System.out.println("Los genericos permiten reutilizar la misma pila con distintos tipos.");
        System.out.println("Pila<Integer> almacena enteros y Pila<String> almacena texto;");
        System.out.println("el compilador verifica el tipo y evita duplicar una clase que solo acepte int.");
        System.out.println();

        Pila<Integer> enteros = new Pila<Integer>(3);
        enteros.push(10);
        enteros.push(20);
        System.out.println("Integer en la cima: " + enteros.peek());
        System.out.println("Integer retirado: " + enteros.pop());

        Pila<String> textos = new Pila<String>(3);
        textos.push("Hola");
        textos.push("Mundo");
        System.out.println("String en la cima: " + textos.peek());
        System.out.println("String retirado: " + textos.pop());

        Pila<Producto> productos = new Pila<Producto>(2);
        productos.push(new Producto("Cuaderno"));
        productos.push(new Producto("Lapiz"));
        System.out.println("Objeto en la cima: " + productos.peek());
        System.out.println("Objeto retirado: " + productos.pop());
    }

    private static class Producto {
        private final String nombre;

        private Producto(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }
}
