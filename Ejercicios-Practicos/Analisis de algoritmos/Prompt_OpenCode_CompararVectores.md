# Prompt para OpenCode - Comparar dos vectores

Necesitás implementar en Java un algoritmo que determine si dos vectores son iguales.

Antes de escribir el código, explicá la estrategia más adecuada para este problema: recorrer ambos arreglos en paralelo y comparar elemento por elemento. Si en algún punto los valores difieren, el algoritmo debe terminar inmediatamente y devolver que los vectores no son iguales. Si se recorren todas las posiciones sin encontrar diferencias, entonces los vectores son iguales.

Justificá por qué esta estrategia es la correcta: porque comparar los elementos en orden es una forma directa de verificar la igualdad estructural de dos arreglos. Además, la condición de salida temprana es importante porque permite detectar una diferencia sin perder tiempo revisando el resto del contenido cuando ya sabemos que no son iguales.

Indicá el análisis de caso best, worst y average:
- Mejor caso: los dos vectores difieren en la primera comparación. El algoritmo termina de inmediato. Complejidad temporal: O(1).
- Peor caso: los vectores tienen el mismo tamaño y todos los elementos coinciden hasta la última posición. Se recorren todos los elementos. Complejidad temporal: O(n).
- Caso promedio: en general, el algoritmo recorre una fracción de los elementos antes de decidir, pero en notación asintótica sigue siendo O(n), ya que en el peor caso la cantidad de elementos revisados puede llegar a n.

Además, solicitá que el algoritmo valide que ambos vectores tengan el mismo tamaño antes de compararlos y que el código esté bien comentado.

Ejemplo de prompt final:

"Implementá en Java un algoritmo que determine si dos vectores son iguales. La estrategia correcta es comparar ambas estructuras elemento por elemento en paralelo, y el algoritmo debe finalizar tan pronto como detecte una diferencia. Explicá por qué esta estrategia es adecuada y por qué la salida temprana es útil. Incluí el análisis de mejor caso, peor caso y caso promedio, indicando que el mejor caso es O(1), el peor caso es O(n) y el caso promedio también queda en O(n). Además, pedí validar que ambos arreglos tengan el mismo tamaño antes de comparar. El código debe estar comentado y debe incluir un ejemplo de uso en main."
