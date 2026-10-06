# Prompt para OpenCode - Invertir una palabra recursivamente

Necesitás implementar en Java una función recursiva que invierta un String.

Antes de escribir el código, explicá cuál es el caso base. Si la cadena es vacía o tiene un solo carácter, ya está invertida, y la función debe devolver la misma cadena sin hacer más llamadas recursivas. Por ejemplo, "" devuelve "" y "a" devuelve "a".

Justificá cómo se separa el primer carácter del resto. La idea es tomar el primer carácter y el resto de la cadena. Por ejemplo, para "hola", se puede separar como primer = 'h' y resto = "ola". Luego se resuelve recursivamente el problema para el resto.

Explicá cómo se reduce la cadena en cada llamada: se debe llamar a la misma función con una subcadena que excluye el primer carácter, por ejemplo: invertir("hola") = invertir("ola") + "h".

Indicá cómo se reconstruye el resultado al volver de la recursión: cuando la llamada recursiva retorna la cadena invertida del resto, se concatenan el primer carácter al final, logrando la inversión completa.

Además, pedí que el código esté bien comentado y que explique por qué la recursión termina correctamente.

Por último, citá palabras de prueba recomendadas para validar la función, por ejemplo:
- invertir("") debe devolver ""
- invertir("a") debe devolver "a"
- invertir("hola") debe devolver "aloh"
- invertir("reconocer") debe devolver "reconocer"
- invertir("java") debe devolver "avaj"

El programa debe incluir una función recursiva llamada invertirPalabra y un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java una función recursiva que invierta un String. Explicá que el caso base es una cadena vacía o de un solo carácter, en cuyo caso la función devuelve la misma cadena. La solución debe separar el primer carácter y el resto de la cadena, reducir la llamada a la subcadena sin el primer carácter y luego reconstruir el resultado agregando el primer carácter al final. Así, invertir("hola") se resuelve como invertir("ola") + "h". El código debe estar bien comentado y explicar por qué la recursión termina correctamente. Validá la función con pruebas como invertir("") = "", invertir("a") = "a", invertir("hola") = "aloh", invertir("reconocer") = "reconocer" e invertir("java") = "avaj"."
