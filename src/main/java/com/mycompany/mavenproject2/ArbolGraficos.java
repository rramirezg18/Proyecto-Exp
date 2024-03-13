package com.mycompany.mavenproject2;

import java.awt.*;
import javax.swing.*;

public class ArbolGraficos extends JPanel {
    private static final int ANCHO_NODO = 40;
    private static final int ALTO_NODO = 40;
    private static final int DISTANCIA_HORIZONTAL = 1600;
    private static final int DISTANCIA_VERTICAL = 80;

    public Nodo raiz;

    public ArbolGraficos(Nodo raiz) {
        this.raiz = raiz;
    }

    public static void mostrarArbolExpresion(ArbolExpresion arbol) {
        JFrame frame = new JFrame("Árbol de Expresión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);

        // Crea un panel para mostrar el árbol
        ArbolGraficos panel = new ArbolGraficos(arbol.raiz); // Aquí estoy asumiendo que raiz es pública
        frame.add(panel);

        frame.setVisible(true);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            dibujarNodo(g, getWidth() / 2, 140, raiz, getWidth() / 3);
        }
    }

    private void dibujarNodo(Graphics g, int x, int y, Nodo nodo, int espacio) {
        g.setColor(Color.BLACK);
        g.drawOval(x, y, ANCHO_NODO, ALTO_NODO);
        g.drawString(nodo.getValor(), x + 12, y + 3);// numeros circulo

        if (nodo.getHijoIzquierdo() != null) {
            int nuevoEspacio = espacio / 3;
            g.drawLine(x + ANCHO_NODO / 3, y + ALTO_NODO, x - espacio / 2 + ANCHO_NODO / 2, y + DISTANCIA_VERTICAL);
            dibujarNodo(g, x - espacio / 2, y + DISTANCIA_VERTICAL, nodo.getHijoIzquierdo(), nuevoEspacio);
        }

        if (nodo.getHijoDerecho() != null) {
            int nuevoEspacio = espacio / 2;
            g.drawLine(x + ANCHO_NODO / 2, y + ALTO_NODO, x + espacio / 2 + ANCHO_NODO / 2, y + DISTANCIA_VERTICAL);
            dibujarNodo(g, x + espacio / 2, y + DISTANCIA_VERTICAL, nodo.getHijoDerecho(), nuevoEspacio);
        }
    }
}

