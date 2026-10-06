# Prompt para OpenCode - Contar dígitos recursivos

Necesitás implementar en Java una función recursiva que cuente los dígitos de un número entero positivo.

Antes de escribir el código, explicá cómo se puede reducir el número usando división entera. Si n tiene más de un dígito, se puede obtener un problema más pequeño aplicando división entera por 10, por ejemplo: 1234 -> 123 -> 12 -> 1. De esta forma, cada llamada recursiva reduce el problema en un orden de magnitud y se acerca al caso base.

Indicá cuál es el caso base: cuando el número es menor que 10, es decir, cuando queda un solo dígito. En ese caso, la función debe devolver 1.

Justificá por qué un número menor que 10 tiene un solo dígito: porque cualquier número de 0 a 9 tiene exactamente una cifra.

Explicá qué debe devolver cada llamada: la función debe devolver 1 + contarDigitos(n / 10) para números mayores o iguales a 10. Esto cuenta el dígito actual y luego suma los que quedan en el resto del número.

Además, pedí que el código esté bien comentado y que explique por qué la recursión termina correctamente.

También indicá qué valores de prueba deberían usarse para verificar la función, por ejemplo:
- contarDigitos(7) debe devolver 1
- contarDigitos(25) debe devolver 2
- contarDigitos(100) debe devolver 3
- contarDigitos(12345) debe devolver 5
- contarDigitos(987654321) debe devolver 9

El programa debe incluir una función recursiva llamada contarDigitos y un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java una función recursiva que cuente los dígitos de un número entero positivo. Explicá que el problema puede reducirse usando división entera por 10, por ejemplo 1234 -> 123 -> 12 -> 1, hasta llegar a un solo dígito. El caso base es cuando n < 10, porque entonces el número tiene un solo dígito y la función debe devolver 1. Explicá que para valores mayores, la función debe devolver 1 + contarDigitos(n / 10). El código debe estar bien comentado y explicar por qué la recursión termina correctamente. Incluí pruebas como contarDigitos(7)=1, contarDigitos(25)=2, contarDigitos(100)=3, contarDigitos(12345)=5 y contarDigitos(987654321)=9."
