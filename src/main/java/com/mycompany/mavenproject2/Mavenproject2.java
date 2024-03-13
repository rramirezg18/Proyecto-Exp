/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;

/**
 *
 * @author ianto
 */
import java.util.*;

//Prueba prueba


public class Mavenproject2 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner entrada = new Scanner(System.in);
        int opcion;
        String expresion = "";
        ArbolExpresion arbol = new ArbolExpresion();
        int salir = 0;
        while(salir !=1) {
        	Menu();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    //aca solicitara la expresion
                    
                    //ArbolExpresion arbol = new ArbolExpresion(); // Crea una instancia de la clase ArbolExpresion.
                    entrada.nextLine();
                    System.out.println("Ingrese una expresion");
                    expresion =  entrada.nextLine();//"5*6-4+8*3^(4-2)*√3"; // Define una expresión aritmética.
        
        // Verifica si la expresión contiene variables
                    if (contieneVariables(expresion)) {
                        System.out.println("La expresion contiene variables. Por favor, ingrese valores para las variables:");
                        for (char variable : obtenerVariables(expresion)) {
                            System.out.print("Ingrese el valor para la variable " + variable + ": ");
                            int valor = entrada.nextInt();
                            expresion = expresion.replace(String.valueOf(variable), String.valueOf(valor));
                        }
                        System.out.println("La expresion con valores constantes es: " + expresion);
                    }
                    
                    
                      break;
                case 2:
                    //aca solo generara el arbol grafico
                    

                    
                      break;
                case 3:
                    arbol.construirArbol(expresion); // Construye el árbol de expresión a partir de la expresión dada.
                    System.out.println("La expresion con valores constantes es: " + expresion);
                    System.out.println("\n");
                    System.out.println("Inorden:"); // Imprime el mensaje "Inorden:".
                    arbol.imprimirInorden(); // Imprime el recorrido inorden del árbol.
                    System.out.println();

                    System.out.println("Preorden:"); // Imprime el mensaje "Preorden:".
                    arbol.imprimirPreorden(); // Imprime el recorrido preorden del árbol.
                    System.out.println();

                    System.out.println("Posorden:"); // Imprime el mensaje "Posorden:".
                    arbol.imprimirPosorden(); // Imprime el recorrido posorden del árbol.
                    System.out.println();
                    //aca muestra los recorridos
                      break;
                case 4:
                    salir = 1;
            }       
            }
    }
    
    public static void Menu() {
    	System.out.println("\n\t MENU\n");
        System.out.println("1.\t Ingresar expresión matemática");
        System.out.println("2.\t Generar árbol de expresión");
        System.out.println("3.\t Recorridos ");
        System.out.println("4.\t Salir");
        System.out.println("\nSeleccione una opción: ");
    }
    
    
    private static boolean contieneVariables(String expresion) {
        for (char c : expresion.toCharArray()) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    // Método para obtener todas las variables de la expresión
    private static char[] obtenerVariables(String expresion) {
        String variables = expresion.replaceAll("[^a-z]", "");
        return variables.toCharArray();
    }
    
    
}
