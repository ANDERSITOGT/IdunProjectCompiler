package com.anderson.compilador;

import java.util.List;

public class Semantico {

    /*
     * Esta función recibe los tokens ya validados por el Sintáctico.
     * Extrae a, b y c, y calcula las raíces.
     */
    public String analizarYResolver(List<Token> tokens) {
        double a = 0, b = 0, c = 0;

        // Variables auxiliares para ir leyendo la ecuación
        double valorActual = 1.0;
        int signoActual = 1;
        boolean tieneNumero = false;

        // 1. RECORRIDO PARA EXTRAER a, b, c
        for (int i = 0; i < tokens.size(); i++) {
            Token t = tokens.get(i);

            if (t.tipo == TipoToken.OPERADOR) {
                if (t.valor.equals("+")) signoActual = 1;
                else if (t.valor.equals("-")) signoActual = -1;
                else if (t.valor.equals("=")) break; // Dejamos de leer al llegar al "="

            } else if (t.tipo == TipoToken.NUMERO) {
                valorActual = Double.parseDouble(t.valor);
                tieneNumero = true;

                // Si llegamos al final, o si el SIGUIENTE token NO es una 'X'
                // significa que este número está solito, por lo tanto es la 'c'
                if (i + 1 >= tokens.size() || tokens.get(i + 1).tipo != TipoToken.VARIABLE) {
                    c = valorActual * signoActual;
                    tieneNumero = false;
                    valorActual = 1.0; // Reiniciamos
                }

            } else if (t.tipo == TipoToken.VARIABLE) { // Si encontramos una 'X'
                if (!tieneNumero) {
                    valorActual = 1.0; // Si el usuario escribe solo "X^2", equivale a "1X^2"
                }

                // Miramos hacia adelante: ¿Le sigue un "^" y un "2"?
                if (i + 2 < tokens.size() && tokens.get(i + 1).valor.equals("^") && tokens.get(i + 2).valor.equals("2")) {
                    a = valorActual * signoActual;
                    i += 2; // Brincamos el '^' y el '2' para no volver a leerlos
                } else {
                    // Si es solo una 'X' sin exponente
                    b = valorActual * signoActual;
                }

                tieneNumero = false;
                valorActual = 1.0; // Reiniciamos
            }
        }

        // 2. REGLAS SEMÁNTICAS (Validaciones matemáticas)
        if (a == 0) {
            return "❌ ERROR SEMÁNTICO: El coeficiente 'a' es 0. \n⚠️ No es una ecuación de segundo grado.";
        }

        double discriminante = (b * b) - (4 * a * c);

        if (discriminante < 0) {
            return "❌ ERROR SEMÁNTICO: El discriminante es negativo (" + discriminante + "). \n⚠️ La ecuación tiene raíces imaginarias (números complejos).";
        }

        // 3. CÁLCULO DE RESULTADOS FINAL
        double x1 = (-b + Math.sqrt(discriminante)) / (2 * a);
        double x2 = (-b - Math.sqrt(discriminante)) / (2 * a);

        return "✅ SEMÁNTICO: Variables extraídas -> a=" + a + ", b=" + b + ", c=" + c + "\n" +
                "--------------------------------------------------\n" +
                "🌟 RESULTADO FINAL DE LA ECUACIÓN:\n" +
                "   X1 = " + x1 + "\n" +
                "   X2 = " + x2;
    }
}