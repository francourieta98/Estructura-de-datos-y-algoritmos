# Prompt para OpenCode - Sumar dígitos recursivos

Necesitás implementar en Java una función recursiva que sume los dígitos de un número entero positivo.

Antes de escribir el código, explicá cómo se obtiene el último dígito usando módulo. Si n es un número como 456, entonces n % 10 devuelve 6, que es el último dígito. Esto permite separar la última cifra del número sin perderla.

Describí cómo se reduce el número usando división entera. Si se divide 456 entre 10, se obtiene 45. Esto elimina la última cifra y deja el resto del número para seguir sumando sus dígitos.

Indicá cuál es el caso base: cuando el número es menor que 10, es decir, cuando queda un solo dígito. En ese caso, la función debe devolver ese mismo valor, porque la suma de un único dígito es el propio dígito.

Explicá cómo se combinan los resultados: la suma total se calcula como:

sumarDigitos(n) = n % 10 + sumarDigitos(n / 10)

Esto combina el último dígito con la suma del resto del número.

Además, pedí que el código esté bien comentado y que explique por qué la recursión termina correctamente.

Además, indicá qué pruebas deben validarse, por ejemplo:
- sumarDigitos(7) debe devolver 7
- sumarDigitos(25) debe devolver 7
- sumarDigitos(123) debe devolver 6
- sumarDigitos(987) debe devolver 24
- sumarDigitos(4567) debe devolver 22

El programa debe incluir una función recursiva llamada sumarDigitos y un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java una función recursiva que sume los dígitos de un número entero positivo. Explicá que el último dígito puede obtenerse con n % 10 y que el número puede reducirse con n / 10. El caso base es cuando n < 10, porque hay un solo dígito y la función debe devolver ese valor. Explicá que la solución combina el último dígito con la suma del resto: sumarDigitos(n) = n % 10 + sumarDigitos(n / 10). El código debe estar bien comentado y explicar por qué la recursión termina. Validá la función con casos como sumarDigitos(7)=7, sumarDigitos(25)=7, sumarDigitos(123)=6, sumarDigitos(987)=24 y sumarDigitos(4567)=22."
