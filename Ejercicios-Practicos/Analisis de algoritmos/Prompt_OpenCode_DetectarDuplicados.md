# Prompt para OpenCode - Detectar elementos duplicados en un vector

Necesitás implementar en Java dos soluciones diferentes para detectar si un vector contiene elementos duplicados.

La primera solución debe usar dos ciclos anidados. La idea es comparar cada elemento con todos los elementos que le siguen en el vector. Si se encuentra el mismo valor en otra posición, entonces el arreglo tiene un duplicado. Esta estrategia es simple y directa, y resulta útil cuando se quiere una solución sin estructuras auxiliares.

La segunda solución debe usar una estructura HashSet. En este enfoque, se recorre el vector y se intenta insertar cada elemento en el conjunto. Si el valor ya existe, entonces se detecta un duplicado. El HashSet facilita la verificación de pertenencia en tiempo casi constante.

Antes de escribir el código, explicá por qué estas dos estrategias son apropiadas para el problema y en qué casos cada una resulta más conveniente. Luego, compará ambas implementaciones desde el punto de vista de la complejidad temporal y espacial.

La comparación debe incluir lo siguiente:
- Solución con dos ciclos anidados: complejidad temporal O(n^2) y complejidad espacial O(1), porque no usa estructuras adicionales significativas.
- Solución con HashSet: complejidad temporal promedio O(n), ya que cada inserción o comprobación tiene costo constante en promedio, y complejidad espacial O(n), porque el conjunto almacena los elementos vistos.

Además, pedí que el código esté bien comentado, que incluya una explicación clara de las diferencias entre ambas soluciones y que muestre un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java dos soluciones diferentes para detectar elementos duplicados dentro de un vector. La primera debe utilizar dos ciclos anidados, comparando cada elemento con los que siguen. La segunda debe usar un HashSet, insertando cada valor y comprobando si ya estaba presente. Explicá por qué cada estrategia es apropiada para este problema, y luego compará ambas desde el punto de vista de la complejidad temporal y espacial. La solución con dos ciclos anidados tiene complejidad O(n^2) y O(1) de espacio, mientras que la solución con HashSet tiene complejidad promedio O(n) y O(n) de espacio. El código debe estar comentado y debe incluir un ejemplo de uso en main para mostrar cómo detectar duplicados en un vector."
