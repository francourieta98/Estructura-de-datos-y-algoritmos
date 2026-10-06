import java.util.HashSet;
import java.util.Set;

public class DetectarDuplicados {

    /**
     * Solución 1: dos ciclos anidados.
     * Compara cada elemento con todos los que le siguen.
     * Si encuentra un valor repetido, devuelve true.
     */
    public static boolean tieneDuplicadoDobleFor(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser nulo.");
        }

        for (int i = 0; i < vector.length; i++) {
            for (int j = i + 1; j < vector.length; j++) {
                if (vector[i] == vector[j]) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Solución 2: usando HashSet.
     * Se almacena cada valor visto. Si un valor ya existe, hay un duplicado.
     */
    public static boolean tieneDuplicadoHashSet(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser nulo.");
        }

        Set<Integer> vistos = new HashSet<>();

        for (int valor : vector) {
            if (!vistos.add(valor)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] vector = { 4, 2, 7, 2, 9, 1, 5 };

        boolean conDobleFor = tieneDuplicadoDobleFor(vector);
        boolean conHashSet = tieneDuplicadoHashSet(vector);

        System.out.println("Solución con dos ciclos anidados: " + conDobleFor);
        System.out.println("Solución con HashSet: " + conHashSet);

        // Comparación de complejidad:
        // - Dos ciclos anidados: O(n^2) temporal y O(1) espacial.
        // - HashSet: O(n) temporal en promedio y O(n) espacial.
        // El HashSet suele ser más eficiente para vectores grandes, mientras que el
        // doble for
        // es más simple pero cuesta más tiempo cuando el tamaño aumenta.
    }
}
