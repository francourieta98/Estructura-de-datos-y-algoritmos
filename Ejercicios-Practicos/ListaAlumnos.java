import java.util.ArrayList;

public class ListaAlumnos {
    public static void main(String[] args) {
        // ArrayList es una lista dinamica que puede cambiar su cantidad de elementos.
        ArrayList<String> alumnos = new ArrayList<>();

        // add() agrega nombres al final de la lista.
        alumnos.add("Ana");
        alumnos.add("Bruno");
        alumnos.add("Carla");
        alumnos.add("Diego");

        // size() devuelve la cantidad actual de elementos de la lista.
        System.out.println("Cantidad de alumnos: " + alumnos.size());
        System.out.println("Lista completa de alumnos:");

        // Recorrido de la lista: el indice comienza en 0.
        for (int indice = 0; indice < alumnos.size(); indice++) {
            // get() obtiene el elemento ubicado en el indice indicado.
            System.out.println((indice + 1) + ". " + alumnos.get(indice));
        }

        // Diferencia: un array tiene tamano fijo; ArrayList puede crecer o reducirse.
        // En un array se usa length, mientras que en ArrayList se usa size().
    }
}
