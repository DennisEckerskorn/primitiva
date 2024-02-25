package com.grupoclase.lib;

import java.util.Scanner;

public class ConsoleMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static final int CAPACIDAD_INICIAL = 5;
    private final String texto;
    private ConsoleMenu[] opciones;
    private int numOpciones;

    public ConsoleMenu(String texto) {
        this.texto = texto;
        this.opciones = null;
        numOpciones = 0;
    }

    private void ampliarCapacidad() {
        ConsoleMenu[] copia = new ConsoleMenu[opciones.length * 2];
        for(int i = 0; i < opciones.length; i++) {
            copia[i] = opciones[i];
        }
        opciones = copia;
    }

    public ConsoleMenu addOpcion(String texto) {
        if(opciones == null) {
            opciones = new ConsoleMenu[CAPACIDAD_INICIAL];
        }
        if(numOpciones == opciones.length) { //El array está lleno
            ampliarCapacidad();
        }
        ConsoleMenu resultado = new ConsoleMenu(texto);
        opciones[numOpciones++] = resultado;
        return resultado;
    }

    public int mostrarMenuInt() {
        boolean valido;
        int opcion;
        do{
            System.out.println(this);
            opcion = Integer.parseInt(scanner.nextLine());
            valido = opcion >= 1 && opcion <= numOpciones;
        }while(!valido);
        return opcion;
    }

    public char mostrarMenuChar() {
        boolean valido;
        char letra;
        do{
            System.out.println(this);
            letra = scanner.next().charAt(0);
            valido = letra >= 'a' && letra <= 'z'; //Falta ajustar
        }while(!valido);
        return letra;
    }

    /**
     * Método que recibe el número de opciones del menú y convierte el número a un carácter.
     * El bucle empieza en 97 porque la a minúscula en ASCII es 97.
     * Se suma el número de veces al al contador letra mínima para obtener la cantidad de carácteres a convertir.
     * @param numVeces Integer
     * @return char correspondiente código ascii.
     */
    private char numToChar(int numVeces) {
        int contadorLetraMin = 97;
        int letraMax = contadorLetraMin + numVeces;
        char letra = ' ';
        for(int i = contadorLetraMin; i < letraMax; i++) {
            letra = (char) i;
        }
        return letra;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*** ").append(texto).append(" ***\n");
        for(int i = 0; i < numOpciones; i++) {
            sb.append(i + 1).append(". ").append(opciones[i].texto).append("\n"); //Ajustar con stringformat
        }
        sb.append("------------------\n");
        sb.append("Elija una opción: \n");
        return sb.toString();
    }
}
