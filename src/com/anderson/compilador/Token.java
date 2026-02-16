package com.anderson.compilador;

/*
 * La "cajita" donde guardamos la pieza recortada por el Lexer.
 */
public class Token {

    public TipoToken tipo;
    public String valor;

    public Token(TipoToken tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "[Tipo: " + tipo + " | Valor: '" + valor + "']";
    }
}