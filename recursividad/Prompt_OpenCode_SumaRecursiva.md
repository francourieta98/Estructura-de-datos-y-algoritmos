# Prompt para OpenCode - Suma recursiva de los primeros N números

Necesitás implementar en Java una función recursiva que sume los números desde n hasta 1.

Antes de escribir el código, explicá cuál es el caso más simple. El caso más simple es cuando n es 0, porque la suma de cero números es 0 y la función debe devolver 0 sin hacer más llamadas recursivas.

Justificá por qué la suma puede pensarse como n + suma(n - 1). La suma de los primeros n números naturales puede escribirse como:

suma(n) = n + suma(n - 1)

Esto significa que, para resolver el problema de tamaño n, se reduce a un problema más pequeño del mismo tipo con n - 1. El valor de n se suma al resultado del caso más pequeño, y así se construye la solución final.

Explicá qué debe pasar si n es 0: la función debe devolver 0 y terminar de inmediato, porque no hay elementos a sumar.

Además, pedí que el código esté bien comentado y que explique por qué la recursión finaliza correctamente sin entrar en un ciclo infinito.

Por último, indicá cómo validar que la función funciona correctamente. Deben usarse pruebas de ejemplo, por ejemplo:
- suma(0) debe devolver 0
- suma(1) debe devolver 1
- suma(5) debe devolver 15
- suma(10) debe devolver 55
- suma(15) debe devolver 120

El programa debe incluir una función recursiva llamada suma y un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java una función recursiva que sume los números desde n hasta 1. Explicá que el caso más simple es n == 0, porque la suma de cero elementos es 0. Justificá que la solución puede pensarse como n + suma(n - 1), reduciendo el problema en cada llamada hasta llegar al caso base. Indica que, si n es 0, la función debe devolver 0 y terminar. El código debe estar bien comentado y explicar por qué la recursión termina correctamente. Además, pedí validar la función con pruebas como suma(0)=0, suma(1)=1, suma(5)=15, suma(10)=55 y suma(15)=120. Incluí un ejemplo de uso en main."
