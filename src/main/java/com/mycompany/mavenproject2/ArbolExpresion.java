/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

import java.util.Stack;

/**
 *
 * @author ianto
 */
public class ArbolExpresion implements Metodos {
    
    private Nodo raiz; // Declara una variable miembro raiz de tipo Nodo, que representa la raíz del árbol.

    public ArbolExpresion() { // Constructor de la clase ArbolExpresion, inicializa raiz como null.
        this.raiz = null;
    }
    
    public void construirArbol(String expresion) {
        String expresionPosfija = convertirAPosfija(expresion);
        Stack<Nodo> pila = new Stack<>();

        for (char caracter : expresionPosfija.toCharArray()) {
            if (Character.isLetterOrDigit(caracter)) { // Verifica si el caracter es una letra o un dígito.
                Nodo nodo = new Nodo(String.valueOf(caracter));
                pila.push(nodo);
            } else {
                Nodo nodoOperador = new Nodo(String.valueOf(caracter));
                Nodo hijoDerecho = pila.pop();
                Nodo hijoIzquierdo = pila.pop();

                nodoOperador.setHijoIzquierdo(hijoIzquierdo);
                nodoOperador.setHijoDerecho(hijoDerecho);

                pila.push(nodoOperador);
            }
        }

        this.raiz = pila.pop();
    }
    
    @Override
    public void imprimirInorden() {
        imprimirInorden(raiz);
    }

    private void imprimirInorden(Nodo nodo) {
        if (nodo != null) {
            imprimirInorden(nodo.getHijoIzquierdo());
            System.out.print(nodo.getValor() + " ");
            imprimirInorden(nodo.getHijoDerecho());
        }
    }
    @Override
    public void imprimirPreorden() {
        imprimirPreorden(raiz);
    }
    
    private void imprimirPreorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getValor() + " ");
            imprimirPreorden(nodo.getHijoIzquierdo());
            imprimirPreorden(nodo.getHijoDerecho());
        }
    }
    @Override
    public void imprimirPosorden() {
        imprimirPosorden(raiz);
    }

    private void imprimirPosorden(Nodo nodo) {
        if (nodo != null) {
            imprimirPosorden(nodo.getHijoIzquierdo());
            imprimirPosorden(nodo.getHijoDerecho());
            System.out.print(nodo.getValor() + " ");
        }
    }
    
    
    private String convertirAPosfija(String expresion) {
        //StringBuilder sirve para comparar cadenas dinamicamente y  el posfija sirve para guardar una cadena inversa polaca
        StringBuilder posfija = new StringBuilder();
        Stack<Character> operadores = new Stack<>(); // Stack es una pila que se llama operadores de tipo caracter para almacenar los operadore

        for (int i = 0; i < expresion.length(); i++) { // for que va desde 0 hasta expresio.length(( que cuenta los caracteres osea hsta el fin de la cadena.
            char caracter = expresion.charAt(i); // Obtiene el caracter actual de la cadena expresion la cual tiene los valores ingresados
            
            
            
            if (Character.isDigit(caracter)) { 
                posfija.append(caracter); // Agrega el dígito directamente o variable a la expresión posfija que es la cadena polaca.

            } else if (caracter == '(') { // Si es un paréntesis izquierdo, lo apila en la pila de operadores hasta encontrar su correspondiente dreecho.
                operadores.push(caracter);
            } else if (caracter == ')') { // Si es un paréntesis derecho, desapila operadores hasta encontrar el izquierdo correspondiente.

                while (!operadores.isEmpty() && operadores.peek() != '(') { //mientras la pila de operadores sea diferente de limpio y operadores.peek osea el primer  parantesis de la pila sea diferente de izquierdo
                    posfija.append(operadores.pop());
                }
                operadores.pop(); // Elimina el paréntesis izquierdo de la pila.
            } else { // Si es un operador, desapila operadores de mayor o igual precedencia y los agrega a la expresión posfija.
                while (!operadores.isEmpty() && jerarquia(operadores.peek()) >= jerarquia(caracter)) {
                    posfija.append(operadores.pop());
                }
                operadores.push(caracter); // Agrega el operador actual a la pila.
            }
        }

        while (!operadores.isEmpty()) { // Desapila los operadores restantes y los agrega a la expresión posfija.
            posfija.append(operadores.pop());
        }

        return posfija.toString(); // Devuelve la expresión posfija como una cadena.
    }

    private int jerarquia(char operador) { // Determina la precedencia de un operador.
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
             
                return 2;
            case '^':
            //case '√': No funciona como simbolo de raiz
                return 3;
            default:
                return 0;
        }
    }
    
}
