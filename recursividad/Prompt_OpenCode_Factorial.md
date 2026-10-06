# Prompt para OpenCode - Factorial recursivo

Necesitás implementar en Java una función recursiva para calcular el factorial de un número entero no negativo.

Antes de escribir el código, explicá por qué el factorial puede resolverse de forma recursiva. El factorial de un número n se define como el producto de todos los enteros desde 1 hasta n, y también puede expresarse de forma recursiva como:

n! = n * (n - 1)!

Esto muestra que el problema se puede resolver dividiéndolo en un caso más pequeño del mismo tipo. La solución recursiva es natural porque cada llamada reduce el problema en 1.

Indicá cuál es el caso base: el caso base es cuando n es 0 o 1. En ambos casos, el factorial vale 1, porque 0! = 1 y 1! = 1.

Explicá qué ocurre cuando n vale 0 o 1: la función debe devolver 1 inmediatamente sin seguir llamándose a sí misma.

Describí cómo se reduce el problema: si n > 1, se llama a la función con n - 1 y se multiplica por n. Por ejemplo, 5! = 5 * 4!; 4! = 4 * 3!; y así sucesivamente hasta llegar a 1.

Además, pedí que el código esté bien comentado y explicá por qué la recursión termina correctamente evitando un caso infinito.

También indicá qué valores de prueba deberían usarse para verificar el algoritmo. Incluí al menos estos casos:
- factorial(0) debe devolver 1
- factorial(1) debe devolver 1
- factorial(5) debe devolver 120
- factorial(7) debe devolver 5040
- factorial(10) debe devolver 3628800

El programa debe incluir una función recursiva llamada factorial y un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java una función recursiva para calcular el factorial de un número entero no negativo. Explicá que el factorial puede resolverse recursivamente porque n! = n * (n - 1)!, por lo que cada llamada reduce el problema en 1. El caso base es cuando n == 0 o n == 1, y en ambos casos la función devuelve 1. Explicá por qué al llegar a 0 o 1 la recursión termina y no entra en un ciclo infinito. Además, indicá qué valores de prueba deberían usarse: factorial(0) = 1, factorial(1) = 1, factorial(5) = 120, factorial(7) = 5040, factorial(10) = 3628800. El código debe estar bien comentado y debe incluir un ejemplo de uso en main."
