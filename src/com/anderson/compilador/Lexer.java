package com.anderson.compilador;

import java.util.ArrayList;
import java.util.List;

/*
 * FASE 1: El Analizador Léxico (Escáner).
 * Lee el texto y lo separa en Tokens, soportando números con decimales.
 */
public class Lexer {

    public List<Token> escanear(String ecuacion) {
        List<Token> listaDeTokens = new ArrayList<>();
        ecuacion = ecuacion.replace(" ", ""); // Limpiamos espacios

        int i = 0;
        while (i < ecuacion.length()) {
            char caracterActual = ecuacion.charAt(i);

            // CASO 1: OPERADOR
            if (caracterActual == '+' || caracterActual == '-' || caracterActual == '=' || caracterActual == '^') {
                listaDeTokens.add(new Token(TipoToken.OPERADOR, String.valueOf(caracterActual)));
                i++;
            }
            // CASO 2: VARIABLE
            else if (Character.isLetter(caracterActual)) {
                listaDeTokens.add(new Token(TipoToken.VARIABLE, String.valueOf(caracterActual)));
                i++;
            }
            // CASO 3: NÚMERO REAL (Acepta un punto decimal)
            else if (Character.isDigit(caracterActual)) {
                String numeroCompleto = "";
                boolean yaTienePunto = false;

                while (i < ecuacion.length()) {
                    char c = ecuacion.charAt(i);

                    if (Character.isDigit(c)) {
                        numeroCompleto += c;
                        i++;
                    } else if (c == '.' && !yaTienePunto) {
                        numeroCompleto += c;
                        yaTienePunto = true;
                        i++;
                    } else {
                        break;
                    }
                }
                listaDeTokens.add(new Token(TipoToken.NUMERO, numeroCompleto));
            }
            // CASO 4: DESCONOCIDO
            else {
                listaDeTokens.add(new Token(TipoToken.DESCONOCIDO, String.valueOf(caracterActual)));
                i++;
            }
        }

        return listaDeTokens;
    }
}