# Prompt para OpenCode - Invertir un vector

Necesitás implementar en Java dos versiones distintas para invertir un vector.

La primera versión debe utilizar un vector auxiliar. La idea es crear un segundo arreglo del mismo tamaño, copiar los elementos del vector original en orden inverso, y luego reemplazar el contenido original con esa copia. Esta estrategia es sencilla de entender y de implementar.

La segunda versión debe invertir el mismo vector sin utilizar memoria adicional significativa. Para eso, se utilizan dos índices: uno al inicio y otro al final del arreglo. Mientras ambos índices no se crucen, se intercambian los elementos correspondientes. Esta solución hace el intercambio in-place, sin necesidad de crear un arreglo extra.

Antes de escribir el código, explicá por qué estas dos estrategias son adecuadas para el problema. Luego, solicitá una comparación entre ambas implementaciones indicando ventajas, desventajas y complejidad temporal y espacial.

La comparación debe incluir lo siguiente:
- Versión con vector auxiliar: complejidad temporal O(n) y complejidad espacial O(n), porque se crea un arreglo adicional del mismo tamaño.
- Versión in-place: complejidad temporal O(n) y complejidad espacial O(1), porque solo se usan variables auxiliares de índices y un valor temporal para el intercambio.

Además, pedí que el código esté bien comentado y que incluya un ejemplo de uso en el método main.

Ejemplo de prompt final:

"Implementá en Java dos versiones distintas para invertir un vector. La primera debe usar un vector auxiliar, copiando los elementos en orden inverso; la segunda debe invertir el mismo arreglo sin memoria adicional significativa usando dos índices que se aproximan desde los extremos. Explicá por qué cada solución es válida y compará ambas indicando ventajas, desventajas, complejidad temporal y complejidad espacial. La versión con vector auxiliar tiene O(n) de tiempo y O(n) de espacio, mientras que la versión in-place también es O(n) en tiempo pero O(1) en espacio. El código debe estar comentado y debe incluir un ejemplo de uso en main."
