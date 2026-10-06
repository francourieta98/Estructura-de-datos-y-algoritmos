# Prompt para OpenCode - Buscar un elemento en un vector desordenado

Necesitás implementar en Java un algoritmo que busque un elemento dentro de un vector desordenado.

Antes de escribir el código, explicá la estrategia que utilizarás. La estrategia más adecuada es la búsqueda secuencial, también conocida como búsqueda lineal. Consiste en recorrer el vector desde la primera posición hasta la última, comparando cada elemento con el valor buscado. Si se encuentra una coincidencia, se reporta la posición y la cantidad de posiciones recorridas. Si se llega al final del arreglo sin encontrarlo, se informa que el elemento no existe.

Justificá por qué esta estrategia es la adecuada: porque el vector está desordenado, lo que implica que no se puede aplicar una búsqueda por división o una búsqueda binaria. La búsqueda lineal es la alternativa correcta porque funciona con cualquier disposición de los elementos y no exige que el arreglo esté ordenado.

Solicitá que el análisis incluya el mejor caso, el peor caso y el caso promedio:
- Mejor caso: el elemento buscado está en la primera posición. Se recorre 1 posición y la búsqueda termina inmediatamente. Complejidad temporal: O(1).
- Peor caso: el elemento no aparece en el vector o se encuentra en la última posición. Se recorren n posiciones. Complejidad temporal: O(n).
- Caso promedio: en un arreglo sin orden, la búsqueda suele requerir recorrer aproximadamente la mitad de los elementos. Complejidad temporal: O(n/2) que se simplifica a O(n).

Además, pedí que el programa informe cuántas posiciones fueron recorridas hasta encontrar el elemento o determinar que no existe. Es decir, debe llevar un contador que aumente en cada comparación y, al finalizar, mostrar ese valor junto con el resultado de la búsqueda.

El código debe estar comentado y debe incluir una explicación clara de por qué no es posible aplicar una búsqueda binaria en un vector desordenado.

Ejemplo de prompt final:

"Implementá en Java un algoritmo para buscar un elemento dentro de un vector desordenado. La estrategia adecuada es la búsqueda secuencial: recorrer el arreglo desde el principio hasta el final y comparar cada elemento con el valor buscado. Justificá por qué esta estrategia es correcta para un arreglo desordenado, ya que no se puede usar búsqueda binaria sin ordenar los datos. Incluí el análisis de mejor caso, peor caso y caso promedio, indicando que el mejor caso es O(1), el peor caso es O(n) y el caso promedio también es O(n) en términos asintóticos. El programa debe informar cuántas posiciones fueron recorridas hasta encontrar el elemento o hasta determinar que no existe. Además, el código debe estar bien comentado y explicar claramente por qué una búsqueda binaria no es aplicable en este caso. Implementá una clase Java con un método estático buscarElemento y un ejemplo de uso en main."
