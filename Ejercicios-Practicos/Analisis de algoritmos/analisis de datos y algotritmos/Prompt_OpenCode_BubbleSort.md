# Prompt para OpenCode - Ordenamiento burbuja

Necesitás implementar en Java el algoritmo Bubble Sort para ordenar un vector de números enteros.

Antes de escribir el código, explicá por qué este algoritmo es adecuado únicamente para fines didácticos o para conjuntos pequeños de datos. El Bubble Sort compara elementos adyacentes y los intercambia cuando están desordenados, repitiendo este proceso varias veces hasta que todos queden ordenados. Aunque es fácil de entender y de implementar, no es eficiente para arreglos grandes porque revisa repetidamente el mismo conjunto de elementos.

Justificá por qué esta estrategia es válida y por qué no es la mejor opción para grandes volúmenes de datos: el algoritmo requiere pasar por el arreglo muchas veces, y cada pasada puede hacer comparaciones redundantes. Por eso, es útil como ejemplo pedagógico, pero no es recomendable para datasets grandes ni para problemas donde se prioriza el rendimiento.

Además, pedí que el programa contabilice comparaciones e intercambios realizados durante la ejecución. Esto permitirá analizar cuántas veces se comparan elementos y cuántas veces se intercambian para lograr el ordenamiento final.

Solicitá también la complejidad esperada del algoritmo:
- Mejor caso: O(n), si el arreglo ya está ordenado y se agrega una optimización para detectar si hubo intercambio.
- Peor caso: O(n^2), porque en cada pasada se comparan muchos elementos y se repite el proceso varias veces.
- Caso promedio: O(n^2), ya que la estructura del algoritmo requiere revisiones repetidas del arreglo.

El código debe estar bien comentado y debe incluir un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java el algoritmo Bubble Sort para ordenar un vector de enteros. Explicá que este algoritmo resulta útil principalmente para fines didácticos o para conjuntos pequeños, porque compara y reacomoda elementos adyacentes repetidamente, lo que lo hace ineficiente para grandes volúmenes de datos. Pedí que el programa cuente las comparaciones e intercambios realizados durante la ejecución. Indicá la complejidad esperada: mejor caso O(n) con optimización, peor caso y promedio O(n^2). El código debe estar comentado y debe incluir un ejemplo de uso en main."
