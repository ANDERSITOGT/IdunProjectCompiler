Compilador de Ecuaciones de Segundo Grado
Este es un proyecto practico desarrollado para el curso de Sistemas Compiladores de la carrera de Ingenieria en Sistemas. Consiste en una aplicacion con interfaz grafica construida nativamente en Java que actua como un compilador para analizar, validar y resolver ecuaciones cuadraticas.

Que hace el proyecto
La herramienta proporciona una calculadora visual donde el usuario puede ingresar una ecuacion de segundo grado (por ejemplo: 2X^2 - 3X + 2 = 0). Al presionar el boton de resolver, el programa no solo calcula el resultado matematico, sino que procesa el texto ingresado mostrando un reporte en consola que detalla el reconocimiento de los elementos, la validacion de la gramatica y la resolucion matematica. Si el usuario comete un error al ingresar los datos, el sistema detiene la ejecucion y reporta en que fase especifica fallo.

Como funciona
El programa esta estructurado respetando las tres fases fundamentales del diseño de compiladores:

Analisis Lexico: Se encarga de escanear la cadena de texto ingresada y agrupar los caracteres en "Tokens" validos. Soporta numeros enteros y decimales reales, identificando si cada pieza es un Numero, una Variable o un Operador. Tambien es capaz de detectar simbolos no validos en el abecedario del programa.

Analisis Sintactico: Recibe la lista de tokens generada por la fase lexica y la somete a validaciones de reglas gramaticales. Verifica el orden logico de los elementos para asegurar que la ecuacion este bien formada. Detecta errores comunes como operadores matematicos consecutivos o la ausencia del signo de igualdad.

Analisis Semantico y Matematico: Una vez validada la estructura, esta fase extrae los valores de los coeficientes a, b y c. Antes de operar, aplica reglas logicas estrictas, validando que el coeficiente "a" no sea igual a cero y que el discriminante no sea negativo para evitar raices imaginarias. Finalmente, calcula y muestra los resultados de X1 y X2.

Requisitos y Despliegue
Para poder ejecutar este proyecto en un entorno local, se recomienda lo siguiente:

Java Development Kit (JDK) 25. El proyecto fue construido y testeado utilizando OpenJDK 25 sobre Fedora Linux, aunque la interfaz grafica construida en Java Swing es compatible con cualquier sistema operativo.

Entorno de desarrollo: IntelliJ IDEA (recomendado) o cualquier otro IDE compatible con Java.

Instrucciones de ejecucion:
Clona este repositorio en tu equipo local.

Abre la carpeta del proyecto en IntelliJ IDEA.

Dirigete a la configuracion del proyecto (Project Structure) y asegurate de que el SDK este configurado en Java 25.

En el panel izquierdo, navega hasta src/com/anderson/compilador y abre el archivo Main.java.

Ejecuta el archivo Main. La interfaz grafica se desplegara automaticamente y estara lista para utilizarse.
