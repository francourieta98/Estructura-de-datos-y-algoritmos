# Prompt para OpenCode - Conteo regresivo recursivo

Necesitás implementar en Java una función recursiva que imprima un conteo desde n hasta 0.

Antes de escribir el código, explicá qué significa que la función no necesariamente devuelva un valor. En este caso, la función cumple una tarea de salida: mostrar números por pantalla. No necesita devolver un resultado numérico; su objetivo es ejecutar la impresión en cada llamada recursiva.

Indicá cuál es el caso base. El caso base es cuando n llega a 0. En ese momento, la función debe imprimir 0 y detenerse, porque ya se alcanzó el final del conteo.

Explicá cómo se acerca al caso base. En cada llamada, se debe reducir el valor de n en 1. Es decir, la función se llama con n - 1, y así se va aproximando a 0.

Además, pedí que expliques qué pasaría si, en lugar de reducir con n - 1, se usara n + 1. Eso haría que el valor de n aumentara en cada llamada y nunca se acercaría al caso base, por lo que la recursión no terminaría y eventualmente produciría un error de desbordamiento de pila.

Por último, pedí que el código esté bien comentado y que explique cómo probar la ejecución. Deben usarse valores de prueba, por ejemplo:
- conteo(5) debería imprimir 5, 4, 3, 2, 1, 0
- conteo(3) debería imprimir 3, 2, 1, 0
- conteo(0) debería imprimir 0

El programa debe incluir una función recursiva llamada conteoRegresivo y un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java una función recursiva que imprima un conteo desde n hasta 0. Explicá que esta función no necesariamente debe devolver un valor, sino que su propósito es mostrar información por pantalla. El caso base es n == 0, donde se imprime 0 y se termina la recursión. En cada llamada se debe reducir el valor con n - 1 para acercarse al caso base. Además, explicá qué pasaría si se usara n + 1: la recursión no terminaría, porque jamás se acercaría a 0. El código debe estar bien comentado y debe incluir ejemplos de prueba como conteo(5), conteo(3) y conteo(0)."
