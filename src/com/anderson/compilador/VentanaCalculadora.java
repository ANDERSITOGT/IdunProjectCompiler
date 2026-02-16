package com.anderson.compilador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaCalculadora extends JFrame {

    private JTextField pantallaEcuacion;
    private JTextArea consolaReportes;

    public VentanaCalculadora() {
        setTitle("Calculadora de Ecuaciones - Sistemas Compiladores");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        pantallaEcuacion = new JTextField();
        pantallaEcuacion.setFont(new Font("Arial", Font.BOLD, 40));
        pantallaEcuacion.setHorizontalAlignment(JTextField.RIGHT);
        pantallaEcuacion.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(pantallaEcuacion, BorderLayout.NORTH);

        JPanel panelTeclado = new JPanel();
        panelTeclado.setLayout(new GridLayout(5, 4, 15, 15));
        panelTeclado.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] botones = {
                "7", "8", "9", "X",
                "4", "5", "6", "^2",
                "1", "2", "3", "+",
                "0", ".", "=", "-",
                "C", " ", " ", "Resolver"
        };

        for (String textoBoton : botones) {
            JButton boton = new JButton(textoBoton);
            boton.setFont(new Font("Arial", Font.BOLD, 28));
            boton.setMargin(new Insets(10, 10, 10, 10));

            if (textoBoton.equals(" ")) {
                boton.setEnabled(false);
                boton.setContentAreaFilled(false);
                boton.setBorderPainted(false);
            } else {
                boton.addActionListener(new ManejadorBotones());
            }

            if (textoBoton.equals("Resolver")) {
                boton.setBackground(new Color(163, 228, 114));
                boton.setForeground(Color.BLACK);
                boton.setFont(new Font("Arial", Font.BOLD, 30));
            }
            if (textoBoton.equals("C")) {
                boton.setBackground(new Color(255, 102, 102));
                boton.setForeground(Color.WHITE);
            }

            panelTeclado.add(boton);
        }
        add(panelTeclado, BorderLayout.CENTER);

        consolaReportes = new JTextArea();
        consolaReportes.setEditable(false);
        consolaReportes.setFont(new Font("Monospaced", Font.PLAIN, 18));
        consolaReportes.setText("--- Consola de Reportes lista ---\nEsperando ecuación...");
        consolaReportes.setMargin(new Insets(10,10,10,10));

        JScrollPane scrollConsola = new JScrollPane(consolaReportes);
        scrollConsola.setBorder(BorderFactory.createTitledBorder(null, "Análisis del Compilador",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 16)));
        scrollConsola.setPreferredSize(new Dimension(0, 350));

        add(scrollConsola, BorderLayout.SOUTH);
    }

    private class ManejadorBotones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();

            if (comando.equals("C")) {
                pantallaEcuacion.setText("");
                consolaReportes.setText("--- Consola de Reportes lista ---\nEsperando ecuación...");
            } else if (comando.equals("Resolver")) {
                String ecuacionEscrita = pantallaEcuacion.getText();

                if (ecuacionEscrita.isEmpty()) {
                    consolaReportes.setText("⚠️ ERROR: La pantalla está vacía. Ingresa una ecuación.");
                    return;
                }

                StringBuilder textoReporte = new StringBuilder();
                textoReporte.append("⏳ Iniciando Análisis del Compilador...\n\n");

                // --- FASE 1: LÉXICO ---
                Lexer miEscaner = new Lexer();
                List<Token> resultadoTokens = miEscaner.escanear(ecuacionEscrita);

                textoReporte.append("✅ LÉXICO: Tokens identificados correctamente.\n");
                textoReporte.append("--------------------------------------------------\n");
                // ¡AQUÍ RESTAURAMOS LA LISTA DE TOKENS!
                for (Token pieza : resultadoTokens) {
                    textoReporte.append(pieza.toString()).append("\n");
                }
                textoReporte.append("--------------------------------------------------\n\n");

                // --- FASE 2: SINTÁCTICO ---
                Parser miParser = new Parser();
                String resultadoSintaxis = miParser.validarSintaxis(resultadoTokens);

                if (!resultadoSintaxis.equals("OK")) {
                    textoReporte.append(resultadoSintaxis).append("\n");
                    if (resultadoSintaxis.contains("LÉXICO")) {
                        textoReporte.append("⚠️ Se detuvo la compilación debido a un error léxico (Fase 1).");
                    } else {
                        textoReporte.append("⚠️ Se detuvo la compilación debido a un error sintáctico (Fase 2).");
                    }
                    consolaReportes.setText(textoReporte.toString());
                    return;
                }

                textoReporte.append("✅ SINTÁCTICO: Gramática correcta. Orden validado.\n\n");

                // --- FASE 3: SEMÁNTICO Y MATEMÁTICO ---
                Semantico miSemantico = new Semantico();
                String resultadoFinal = miSemantico.analizarYResolver(resultadoTokens);

                textoReporte.append(resultadoFinal).append("\n");

                // Imprimimos todo en la pantalla de la consola
                consolaReportes.setText(textoReporte.toString());

                // Como la consola ahora tiene mucho texto, la obligamos a ir hasta arriba
                consolaReportes.setCaretPosition(0);

            } else {
                pantallaEcuacion.setText(pantallaEcuacion.getText() + comando);
            }
        }
    }
}