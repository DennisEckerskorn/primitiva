package com.grupoclase.resources;

import com.grupoclase.lib.ConsoleMenu;

import java.util.Scanner;

/*
Classe que maneja la entrada/salida y los menus de la primitiva.
 */

public class JuegoPrimitiva {
    private ConsoleMenu menu;
    private ConsoleMenu menuJuego;
    private ConsoleMenu menuJugadaUnica;

    public JuegoPrimitiva() {
        //Se crea un objeto de la clase ConsoleMenu y se usa para añadir opciónes al menú, mediante un método.
        menu = new ConsoleMenu("PRIMITIVA");
        menu.addOpcion("Jugar ...");
        menu.addOpcion("Salir.");

        //Menú para el tipo o modalidad de juego.
        menuJuego = new ConsoleMenu("TIPOS DE JUEGO");
        menuJuego.addOpcion("Juego único ...");
        menuJuego.addOpcion("Jugar hasta obtener premio (reintegro) ...");
        menuJuego.addOpcion("Jugar hasta obtener premio (sin reintegro) ...");
        menuJuego.addOpcion("Ciclo de 10000 sorteos");
        menuJuego.addOpcion("Jugar hasta obtener premio categoría especial");
        menuJuego.addOpcion("Volver al menú principal");

        //Menú para la jugada unica, automatico o manual.
        menuJugadaUnica = new ConsoleMenu("JUGADA ÚNICA");
        menuJugadaUnica.addOpcion("Elegir números manualmente ...");
        menuJugadaUnica.addOpcion("Elegir números al azar ...");

        int opcion;

        do {
            opcion = menu.mostrarMenuInt();
            switch (opcion) {
                case 1:
                    //Método para entrar al menu del tipo de juego:
                    menuTipoJuego();
                    break;
                case 2:
                    //Salimos del programa.
                    System.out.println("Hasta pronto ...");
                    break;
                default:
                    System.out.println("EL valor introducido es inválido, solo valores entre 1 - 3");
            }
        } while (opcion != 2);
    }

    /**
     * Método para el menu de tipo de juego, se muestra el menú con sus opciones.
     * Mediante el switch se elige la opción y el método correspondiente.
     */
    public void menuTipoJuego() {
        int opcion;

        opcion = menuJuego.mostrarMenuInt();
        switch (opcion) {
            case 1:
                menuJugadaUnica();
                break;
            case 2:
                //Jugar hasta obtener premio (reintegro)
                break;
            case 3:
                //Jugar hasta obtener premio (sin reintegro)
                break;
            case 4:
                //Ciclo de 10000 sorteos
                break;
            case 5:
                //Jugar hasta obtener premio categoría especial
                break;
            default:
                System.out.println("EL valor introducido es inválido, solo valores entre 1 - 5");
        }
    }

    public void menuJugadaUnica() {
        int opcion;

        opcion = menuJugadaUnica.mostrarMenuInt();
        switch (opcion) {
            case 1:
                //Juego único

                /*
                   Pasos a seguir, opcion Manual:
                    1 - Mostrar los números disponibles de un ticket:
                        Bombo1:
                        1 2 3 4 5 6 7 8 9 10
                        11 12 13 14 15 16 17
                        18 19 20 21 22 23 24
                        25 26 27 28 29 30 31
                        32 33 34 35 36 37 38
                        39 40 41 42 43 44 45
                        46 47 48 49
                    2 - Elegir 6 números de este ticket manualmente

                    Para los dos casos:
                    1 - Generar una combinacionGanadora y comparar si los números elegidos del usuario (auto o manual)
                        coinciden con los números de la combinación Ganadora.
                 */


                break;
            case 2:
                /*
                Pasos a seguir, opcion Automatica:
                    1 - Generar 6 números aleatorios entre 1 y 49.
                    2 - Mostrar los números aleatorios generados al usuario.

                    Para los dos casos:
                    1 - Generar una combinacionGanadora y comparar si los números elegidos del usuario (auto o manual)
                        coinciden con los números de la combinación Ganadora.

                        PREGUNTA? Como funciona el tema del reintegro aqui?
                 */
                break;
            default:
                System.out.println("El valor introducido es inválida, solo valores entre 1 - 2");

        }
    }

    private static void elegir6nums(){
        int[] combinacionElegida = new int[6];
        //TODO si hace falta, modificar el metodo para que los números elegidos no se puedan repetir
        int num = leerInt(1,49);
        num = combinacionElegida[0];
        num = leerInt(1,49);
        num = combinacionElegida[1];
        num = leerInt(1,49);
        num = combinacionElegida[2];
        num = leerInt(1,49);
        num = combinacionElegida[3];
        num = leerInt(1,49);
        num = combinacionElegida[4];
        num = leerInt(1,49);
        num = combinacionElegida[5];

    }
    private static int leerInt(int min, int max){
        Scanner lector = new Scanner(System.in);
        int respuesta = 0;
        boolean valido = false;
        do {
            if (lector.hasNextInt()) {
                respuesta = lector.nextInt();
                if (respuesta >= min && respuesta <= max) {
                    valido = true;
                } else {
                    System.out.println("Por favor, introduce un número dentro del rango (" + min + " - " + max + ").");
                }
            } else {
                System.out.println("Por favor, introduce un número válido.");
                lector.next(); // Limpiar el buffer de entrada
            }
        } while (!valido);
        lector.nextLine();
        return respuesta;
    }
}


