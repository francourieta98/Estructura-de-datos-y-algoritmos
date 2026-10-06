# Prompt para OpenCode - Contar ocurrencias en un vector

Necesitás implementar en Java un algoritmo que cuente cuántas veces aparece un valor determinado dentro de un vector.

Antes de escribir el código, explicá por qué el algoritmo necesita recorrer completamente el vector. La razón es que para saber cuántas veces aparece un valor, hay que revisar cada posición del arreglo y comparar cada elemento con el valor buscado. No se puede saber con certeza la cantidad total de ocurrencias sin inspeccionar todos los elementos, porque un valor puede aparecer varias veces en cualquier parte del vector.

Justificá esta estrategia como la adecuada para el problema: recorrer el arreglo desde el principio hasta el final y acumular un contador cada vez que el valor coincida con el buscado. Esta técnica es directa, fácil de verificar y correcta para cualquier distribución de datos, sin necesidad de que el vector esté ordenado.

Solicitá el análisis de complejidad:
- El algoritmo recorre cada elemento exactamente una vez, por lo que la complejidad temporal es O(n).
- Solo se usa una variable para contar las ocurrencias, por lo que la complejidad espacial es O(1).

Además, pedí que el código esté bien comentado y que incluya un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java un algoritmo que cuente cuántas veces aparece un valor determinado dentro de un vector. Explicá por qué es necesario recorrer completamente el arreglo, ya que cada posición debe ser revisada para determinar la cantidad total de coincidencias. Justificá que la estrategia es recorrer el vector y llevar un contador que se incremente cada vez que el valor coincida con el buscado. Indicá la complejidad temporal O(n) y la espacial O(1). El código debe estar comentado y debe incluir un ejemplo de uso en main."
