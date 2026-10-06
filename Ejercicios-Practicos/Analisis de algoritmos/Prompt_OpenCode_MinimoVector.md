# Prompt para OpenCode - Encontrar el mínimo de un vector

Necesitás implementar en Java un algoritmo que encuentre el valor mínimo de un vector de números enteros.

Antes de escribir el código, explicá la estrategia más adecuada para resolver este problema. La estrategia recomendada es recorrer el vector una sola vez, manteniendo una variable llamada mínimo que se inicializa con el primer elemento del arreglo y luego compara cada elemento restante con ese valor. Si el elemento actual es menor que el mínimo guardado, actualizamos el valor de la variable.

Justificá por qué esta estrategia es correcta: porque después de cada iteración, la variable mínimo almacena el menor valor encontrado entre todos los elementos ya procesados. Cuando termina el recorrido, ese valor corresponde al mínimo de todo el vector.

Indicá la complejidad temporal y espacial esperada: la solución requiere recorrer cada elemento del arreglo exactamente una vez, por lo que la complejidad temporal es O(n). Como solo se usa una variable auxiliar para guardar el mínimo actual, la complejidad espacial es O(1).

Además, pedí que el código esté bien comentado y que incluya una explicación de por qué no es necesario ordenar el vector para encontrar el valor mínimo. Explicá que ordenar el arreglo implica una tarea adicional innecesaria, ya que la solución por recorrido directo resuelve el problema en menos pasos y con menor costo computacional.

Luego, solicitá la implementación en Java con una clase que incluya un método estático llamado encontrarMinimo y un ejemplo de uso en el método main. El código debe manejar correctamente casos como un vector vacío o nulo, lanzando una excepción clara si la entrada no es válida.

Ejemplo de prompt final:

"Implementá en Java un algoritmo que encuentre el menor valor de un vector de enteros. Antes de escribir el código, explicá la estrategia más adecuada para resolverlo: recorrer el arreglo una sola vez y mantener el menor valor encontrado hasta el momento. Justificá por qué esta estrategia es correcta porque en cada paso la variable de mínimo representa el menor elemento de la sección ya recorrida; al finalizar el recorrido, esa variable es el mínimo del vector completo. Indicá que la complejidad temporal es O(n) y la complejidad espacial es O(1). Además, explicá por qué NO es necesario ordenar el vector, ya que ordenar implica trabajo extra y una complejidad mayor, mientras que una única pasada es suficiente. El código debe estar comentado y debe incluir una explicación clara de ese punto. Implementá una clase Java con un método estático encontrarMinimo y un ejemplo de uso en main. Verificá que el arreglo no sea nulo ni vacío y lanzá una excepción con un mensaje claro si no es válido."
