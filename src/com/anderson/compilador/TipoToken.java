package com.anderson.compilador;

/*
 * Las "etiquetas" que le pondremos a cada pedacito de la ecuación.
 */
public enum TipoToken {
    NUMERO,      // Ej: 5, -3.14, 0
    VARIABLE,    // Ej: X
    OPERADOR,    // Ej: +, -, =, ^
    DESCONOCIDO  // Ej: @, ?, etc.
}