# Prompt para OpenCode - Buscar un elemento en un vector ordenado

Necesitás implementar en Java un algoritmo de búsqueda binaria para encontrar un elemento dentro de un vector ordenado.

Antes de escribir el código, explicá por qué esta estrategia es más eficiente que una búsqueda lineal cuando los datos están ordenados. La búsqueda binaria funciona dividiendo el rango de búsqueda a la mitad en cada iteración. Si el valor buscado es menor que el valor central, se descarta la mitad derecha; si es mayor, se descarta la mitad izquierda. Esto elimina la mitad del espacio de búsqueda en cada paso, reduciendo drásticamente la cantidad de comparaciones necesarias.

Justificá por qué esta estrategia es la adecuada: porque el arreglo está ordenado, lo que permite descartar grandes regiones sin revisarlas. En cambio, la búsqueda lineal tendría que recorrer muchos elementos uno por uno, por lo que la búsqueda binaria resulta más eficiente en conjuntos grandes.

Indicá la complejidad esperada:
- Mejor caso: O(1), si el elemento buscado está exactamente en el centro en la primera comparación.
- Peor caso: O(log n), porque el rango de búsqueda se reduce a la mitad en cada iteración.
- Caso promedio: O(log n), ya que el algoritmo elimina aproximadamente la mitad del espacio de búsqueda en cada paso.

Además, solicitá que el programa muestre paso a paso cómo evolucionan las variables de búsqueda. Debe visualizar el valor de los índices izquierdo, derecho y medio, y mostrar qué mitad del vector queda descartada en cada iteración.

El código debe estar bien comentado, explicar claramente por qué la búsqueda binaria requiere que el vector esté ordenado y mostrar un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java un algoritmo de búsqueda binaria para encontrar un elemento en un vector ordenado. Antes de escribir el código, explicá por qué esta estrategia es más eficiente que una búsqueda lineal cuando los datos están ordenados, ya que descarta la mitad del vector en cada paso y reduce la cantidad de comparaciones. Indicá la complejidad esperada: mejor caso O(1), peor caso O(log n) y caso promedio O(log n). Además, pedí que el programa muestre paso a paso la evolución de las variables izq, der y medio, indicando qué mitad del arreglo se descarta en cada iteración. El código debe estar comentado, explicar por qué la búsqueda binaria necesita un arreglo ordenado y proporcionar un ejemplo de uso en main."
