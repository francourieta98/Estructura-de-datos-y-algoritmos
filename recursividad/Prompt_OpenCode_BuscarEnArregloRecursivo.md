# Prompt para OpenCode - Búsqueda recursiva en un arreglo

Necesitás implementar en Java una función recursiva que busque un número dentro de un arreglo, sin usar ciclos.

Antes de escribir el código, explicá desde qué posición empieza la búsqueda. La búsqueda debe comenzar en la posición 0 del arreglo, porque es el primer elemento a revisar.

Describí cómo avanzar en el arreglo sin usar `for` ni `while`. La idea es pasar como parámetro la posición actual del arreglo y, en cada llamada recursiva, avanzar una posición. Es decir, la búsqueda se mueve hacia la derecha mediante `indice + 1`.

Indicá cuál es el caso base cuando encuentra el valor: si el elemento en la posición actual es igual al valor buscado, la función debe devolver la posición actual, porque ese es el punto en el que se encontró el elemento.

Además, explicá cuál es el caso base cuando llega al final: si el índice supera el último elemento del arreglo, significa que el valor no existe y la función debe devolver -1.

También pedí que describan qué debe devolver la función en cada caso:
- si encuentra el valor, devuelve su índice;
- si no lo encuentra y llega al final, devuelve -1;
- si el arreglo es nulo o vacío, puede devolver -1 o lanzar una excepción, según se prefiera, pero debe manejarse explícitamente.

Además, pedí que el código esté bien comentado y que explique por qué la recursión termina correctamente.

Por último, indicá qué valores de prueba usar para validar la función, por ejemplo:
- buscar([3, 7, 1, 9], 1) debe devolver 2
- buscar([3, 7, 1, 9], 9) debe devolver 3
- buscar([3, 7, 1, 9], 5) debe devolver -1
- buscar([], 4) debe devolver -1

El programa debe incluir una función recursiva llamada buscar y un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java una función recursiva que busque un número dentro de un arreglo sin usar ciclos. Explicá que la búsqueda comienza en la posición 0 y avanza hacia la derecha usando un índice que se pasa como parámetro en cada llamada recursiva, por ejemplo buscar(arreglo, indice, valor). El caso base es cuando el elemento actual coincide con el valor buscado, en cuyo caso la función devuelve el índice actual. Si el índice llega al final del arreglo, la función debe devolver -1. El código debe estar bien comentado y explicar por qué la recursión termina correctamente. Validá la función con pruebas como buscar([3, 7, 1, 9], 1) = 2, buscar([3, 7, 1, 9], 9) = 3, buscar([3, 7, 1, 9], 5) = -1 y buscar([], 4) = -1."
