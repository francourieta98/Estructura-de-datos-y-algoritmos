# Prompt para OpenCode - Multiplicación recursiva sin usar *

Necesitás implementar en Java una función recursiva para multiplicar dos números enteros sin usar el operador `*`.

Antes de escribir el código, explicá cómo puede representarse una multiplicación como sumas repetidas. Por ejemplo, 5 x 3 puede pensarse como 3 + 3 + 3 + 3 + 3, o bien como 5 + 5 + 5. La idea es que la multiplicación se puede resolver sumando repetidamente un número tantas veces como indique el otro factor.

Indicá cuál de los dos parámetros se reduce en cada llamada recursiva. Por ejemplo, si se toma el primer factor como el que se reduce, entonces la función puede resolverse como:

multiplicar(a, b) = a + multiplicar(a, b - 1)

Esto reduce el segundo parámetro en cada paso, y cuando b llega a 0 termina el proceso.

Explicá por qué el caso base debería ser cuando uno de los factores vale 0. Si cualquiera de los dos factores es 0, el producto debe ser 0, y además la recursión debe terminar para evitar un ciclo infinito.

Indicá qué debe devolver la función en ese caso: debe devolver 0 inmediatamente.

Además, pedí que el código esté bien comentado y que explique por qué la recursión termina correctamente.

Por último, indicá qué pruebas deberían hacerse para validar la función. Deben incluir ejemplos como:
- multiplicar(0, 7) debe devolver 0
- multiplicar(7, 0) debe devolver 0
- multiplicar(4, 3) debe devolver 12
- multiplicar(5, 2) debe devolver 10
- multiplicar(6, 6) debe devolver 36

El programa debe incluir una función recursiva llamada multiplicar y un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java una función recursiva que multiplique dos números enteros sin usar el operador `*`. Explicá que la multiplicación puede verse como sumas repetidas, por ejemplo 5 x 3 = 3 + 3 + 3 + 3 + 3. La recursión debe reducir uno de los factores en cada llamada, por ejemplo multiplicar(a, b) = a + multiplicar(a, b - 1). El caso base es cuando alguno de los factores vale 0, porque el producto debe ser 0 y la función debe terminar. El código debe estar bien comentado, explicar por qué la recursión finaliza correctamente y validar la función con casos como 0 x 7 = 0, 7 x 0 = 0, 4 x 3 = 12, 5 x 2 = 10 y 6 x 6 = 36. Incluí un ejemplo de uso en main."
