# Prompt para OpenCode - Palíndromo recursivo

Necesitás implementar en Java una función recursiva que determine si una palabra es palíndromo.

Antes de escribir el código, explicá por qué hay que comparar el primer y último carácter. Un palíndromo se define como una palabra que se lee igual de izquierda a derecha que de derecha a izquierda. Por lo tanto, la primera y la última letra deben coincidir para que la palabra pueda seguir siendo un palíndromo.

Explicá qué pasa si son distintos: si el primer y el último carácter son diferentes, la palabra no es un palíndromo y la función debe devolver false de inmediato.

Describí cómo se reduce el problema eliminando extremos. Si los extremos coinciden, se debe llamar recursivamente a la función sobre la subcadena que se obtiene eliminando el primer y último carácter. Por ejemplo: "reconocer" -> "econoce" -> "conoc" -> ...

Indicá cuál es el caso base: una cadena vacía o de un solo carácter es un palíndromo. Por lo tanto, si la longitud es 0 o 1, la función debe devolver true.

Además, pedí que el código esté bien comentado y que explique por qué la recursión termina correctamente.

Por último, indicá qué pruebas deberían hacerse para validar la función, por ejemplo:
- esPalindromo("") debe devolver true
- esPalindromo("a") debe devolver true
- esPalindromo("oso") debe devolver true
- esPalindromo("radar") debe devolver true
- esPalindromo("hola") debe devolver false
- esPalindromo("reconocer") debe devolver true

El programa debe incluir una función recursiva llamada esPalindromo y un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java una función recursiva que determine si una palabra es palíndromo. Explicá que para que sea palíndromo, el primer y último carácter deben ser iguales; si no lo son, la función debe devolver false. Si coinciden, se reduce el problema eliminando ambos extremos y se llama recursivamente a la subcadena restante. El caso base es cuando la cadena tiene longitud 0 o 1, porque en esos casos siempre es un palíndromo. El código debe estar bien comentado y debe incluir pruebas como esPalindromo("") = true, esPalindromo("a") = true, esPalindromo("oso") = true, esPalindromo("radar") = true, esPalindromo("hola") = false y esPalindromo("reconocer") = true."
