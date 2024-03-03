package com.grupoclase.lib;

import java.util.Scanner;

public class ConsoleMenu {
    //Atributos de la clase:
    private static Scanner scanner = new Scanner(System.in);
    private static final int CAPACIDAD_INICIAL = 5;
    private final String texto;
    private ConsoleMenu[] opciones;
    private int numOpciones;

    /**
     * Constructor ConsoleMenu que recibe un texto como título del menú
     * @param texto String
     */
    public ConsoleMenu(String texto) {
        this.texto = texto;
        this.opciones = null;
        numOpciones = 0;
    }

    /**
     * Método que crea un array de mayor tamaño y copia todos los valores de un array al otro.
     * Finalmente se apunta el puntero de copia al puntero del array inicial, de este modo el array expandido es opciones.
     */
    private void ampliarCapacidad() {
        ConsoleMenu[] copia = new ConsoleMenu[opciones.length * 2];
        for(int i = 0; i < opciones.length; i++) {
            copia[i] = opciones[i];
        }
        opciones = copia;
    }

    /**
     * Método que permite añadir una opción de texto al array de Strings.
     * Cada Texto añadido es una opción nueva del menú.
     * @param texto String
     * @return un objeto de tipo ConsoleMenu.
     */
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


    /**
     * Método que valida el número introducido del usuario y a la vez muestra las opciones del menú que se han añadido.
     * @return la opción introducida del usuario.
     */
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

    /**
     *  Método que valida el carácter introducido del usuario y a la vez muestra las opciones del menú que se han añadido.
     * @return la opción introducida del usuario.
     */
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

    /**
     * Método toString para mostrar el menú completo
     * Se recorre el array y se añade cada opcion al StringBuilder.
     * @return menu completo de opciones, con números o si modifica con el método numToChar(i+1) con carácteres.
     */
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
