# Prompt para OpenCode - Encontrar el mayor elemento de una matriz

Necesitás implementar en Java un algoritmo que encuentre el mayor elemento de una matriz.

Antes de escribir el código, explicá por qué es necesario recorrer todos los elementos. La razón es que el valor máximo puede estar en cualquier posición de la matriz, no necesariamente en la primera fila, la última columna o una posición predecible. Para estar seguro de que el resultado es correcto, hay que revisar cada elemento y comparar cada valor con el máximo encontrado hasta el momento.

Justificá por qué esta estrategia es la adecuada: se inicializa una variable con el primer elemento y, mientras se recorre la matriz, si se encuentra un valor mayor, se actualiza el máximo. Al final del recorrido, esa variable contiene el mayor elemento de toda la estructura.

Solicitá el análisis de complejidad:
- Si la matriz tiene m filas y n columnas, la cantidad total de elementos es m x n.
- La complejidad temporal es O(m x n), porque se recorren todos los elementos exactamente una vez.
- La complejidad espacial es O(1), porque solo se usa una variable extra para guardar el valor máximo.

Además, pedí que el código esté bien comentado y que incluya un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java un algoritmo que encuentre el mayor elemento de una matriz. Explicá que es necesario recorrer todos los elementos porque el valor máximo puede estar en cualquier posición y no puede inferirse sin revisar la matriz completa. La estrategia recomendada es recorrer fila por fila y columna por columna, manteniendo el mayor valor encontrado hasta el momento. Indicá que la complejidad temporal es O(m x n) y la complejidad espacial es O(1). El código debe estar comentado y debe incluir un ejemplo de uso en main."
