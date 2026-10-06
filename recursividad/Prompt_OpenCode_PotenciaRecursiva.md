# Prompt para OpenCode - Potencia recursiva sin usar Math.pow

Necesitás implementar en Java una función recursiva que calcule una potencia sin usar `Math.pow`.

Antes de escribir el código, explicá por qué una potencia puede verse como multiplicaciones sucesivas. Por ejemplo, a^3 puede entenderse como a * a * a, es decir, el número base multiplicado por sí mismo tantas veces como indique el exponente. La recursión puede representar esta idea como una multiplicación repetida del mismo valor.

Justificá por qué el caso base es cuando el exponente es 0. En matemáticas, cualquier número distinto de 0 elevado a 0 es 1. Por lo tanto, si el exponente vale 0, la función debe devolver 1 y terminar la recursión.

Describí cómo se reduce el exponente. La relación recursiva es:

potencia(base, exponente) = base * potencia(base, exponente - 1)

Cada llamada reduce el exponente en 1, hasta llegar al caso base.

Indicá qué debe devolver la función en cada caso:
- Si exponente == 0, devolver 1.
- Si exponente > 0, devolver base * potencia(base, exponente - 1).
- Si se quiere manejar valores negativos, se puede decidir si la función lanza una excepción o devuelve un valor específico; en general, se recomienda validar que el exponente sea no negativo.

Además, pedí que el código esté bien comentado y que explique por qué la recursión termina correctamente.

Por último, indicá qué casos de prueba usar para validar la función. Ejemplos recomendados:
- potencia(2, 0) debe devolver 1
- potencia(5, 1) debe devolver 5
- potencia(3, 2) debe devolver 9
- potencia(4, 3) debe devolver 64
- potencia(2, 5) debe devolver 32

El programa debe incluir una función recursiva llamada potencia y un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java una función recursiva que calcule una potencia sin usar `Math.pow`. Explicá que una potencia puede verse como multiplicaciones sucesivas, por ejemplo 3^4 = 3 * 3 * 3 * 3, y que esto se puede modelar recursivamente como base * potencia(base, exponente - 1). El caso base es cuando el exponente es 0, porque cualquier número distinto de 0 elevado a 0 es 1. Indicá que la función debe devolver 1 en ese caso y reducir el exponente en cada llamada hasta llegar a 0. Validá la función con pruebas como potencia(2, 0)=1, potencia(5, 1)=5, potencia(3, 2)=9, potencia(4, 3)=64 y potencia(2, 5)=32. El código debe estar bien comentado y debe incluir un ejemplo de uso en main."
