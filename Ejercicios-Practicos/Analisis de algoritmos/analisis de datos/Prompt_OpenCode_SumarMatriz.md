# Prompt para OpenCode - Sumar todos los elementos de una matriz

Necesitás implementar en Java un algoritmo que calcule la suma de todos los elementos de una matriz.

Antes de escribir el código, explicá la estrategia más adecuada para resolverlo: recorrer la matriz fila por fila y, dentro de cada fila, recorrer columna por columna. En cada posición, se suma el valor actual a un acumulador. Este enfoque asegura que todos los elementos sean visitados exactamente una vez.

Justificá por qué esta estrategia es correcta: porque cada elemento de la matriz pertenece a una fila y una columna específicas, y al recorrer todas las posiciones se obtiene la suma total de la estructura completa. Además, es una forma directa y segura de evitar perder datos o contar elementos dos veces.

Indicá el análisis de complejidad:
- Si la matriz tiene m filas y n columnas, la cantidad total de elementos es m x n.
- La complejidad temporal es O(m x n), porque se recorren todas las posiciones una vez.
- La complejidad espacial es O(1), salvo por el acumulador y variables de control, que no dependen del tamaño de la matriz.

Además, pedí que el programa contabilice la cantidad de operaciones realizadas. Es decir, debe contar cuántas veces se procesa una celda y cuántas sumas se ejecutan durante el recorrido.

El código debe estar comentado y debe incluir un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java un algoritmo que calcule la suma de todos los elementos de una matriz. Explicá que la estrategia correcta es recorrer fila por fila y columna por columna, sumando cada valor a un acumulador. Justificá por qué esta solución es correcta porque revisa cada posición exactamente una vez. Indicá que la complejidad temporal es O(m x n) y la complejidad espacial es O(1). Además, pedí que el programa cuente la cantidad de operaciones realizadas durante el recorrido. El código debe estar bien comentado y debe incluir un ejemplo de uso en main."
