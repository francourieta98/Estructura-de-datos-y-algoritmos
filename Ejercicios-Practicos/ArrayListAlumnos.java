import java.util.ArrayList;

public class ArrayListAlumnos {
    public static void main(String[] args) {
        ArrayList<String> alumnos = new ArrayList<>();

        alumnos.add("Ana");
        alumnos.add("Bruno");
        alumnos.add("Carla");
        alumnos.add("Diego");

        System.out.println("Cantidad de alumnos: " + alumnos.size());
        System.out.println("Lista completa de alumnos:");

        for (int indice = 0; indice < alumnos.size(); indice++) {
            System.out.println((indice + 1) + ". " + alumnos.get(indice));
        }
    }
}