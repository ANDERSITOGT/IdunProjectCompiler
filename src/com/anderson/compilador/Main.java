package com.anderson.compilador;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Escalar la interfaz para pantallas de alta resolución en Fedora
        System.setProperty("sun.java2d.uiScale", "2.0");

        // Usar el diseño nativo del sistema operativo (Modo Oscuro)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaCalculadora ventana = new VentanaCalculadora();
                ventana.setVisible(true);
            }
        });
    }
}