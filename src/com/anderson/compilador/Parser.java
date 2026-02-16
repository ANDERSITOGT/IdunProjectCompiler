package com.anderson.compilador;

import java.util.List;

public class Parser {

    public String validarSintaxis(List<Token> tokens) {

        if (tokens.isEmpty()) {
            return "❌ ERROR SINTÁCTICO: La ecuación está vacía.";
        }

        // CORRECCIÓN: Ahora esto se reporta estrictamente como Error Léxico
        for (Token t : tokens) {
            if (t.tipo == TipoToken.DESCONOCIDO) {
                return "❌ ERROR LÉXICO: Se encontró un símbolo inválido ('" + t.valor + "').";
            }
        }

        for (int i = 0; i < tokens.size() - 1; i++) {
            Token actual = tokens.get(i);
            Token siguiente = tokens.get(i + 1);

            if (actual.tipo == TipoToken.OPERADOR && siguiente.tipo == TipoToken.OPERADOR) {
                return "❌ ERROR SINTÁCTICO: Operadores consecutivos no permitidos ('" + actual.valor + "' seguido de '" + siguiente.valor + "').";
            }
        }

        boolean tieneIgual = false;
        for (Token t : tokens) {
            if (t.valor.equals("=")) {
                tieneIgual = true;
                break;
            }
        }
        if (!tieneIgual) {
            return "❌ ERROR SINTÁCTICO: La ecuación está incompleta, falta el signo '='.";
        }

        return "OK";
    }
}