package com.grupoclase.resources;

import com.grupoclase.lib.ConsoleMenu;

//Classe que maneja la entrada/salida y los menus de la primitiva.
public class Principal {
    private ConsoleMenu menu;
    private ConsoleMenu menuJuego;

    public Principal() {
        //Se crea un objeto de la clase ConsoleMenu y se usa para añadir opciónes al menú, mediante un método.
        menu = new ConsoleMenu("PRIMITIVA");
        menu.addOpcion("Jugar ...");
        menu.addOpcion("Salir.");

        //Menú para el tipo de juego.
        menuJuego = new ConsoleMenu("TIPOS DE JUEGO");
        menuJuego.addOpcion("Juego único ...");
        menuJuego.addOpcion("Jugar hasta obtener premio (reintegro) ...");
        menuJuego.addOpcion("Jugar hasta obtener premio (sin reintegro) ...");
        menuJuego.addOpcion("Ciclo de 10000 sorteos");
        menuJuego.addOpcion("Jugar hasta obtener premio categoría especial");
        menuJuego.addOpcion("Volver al menú principal");
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
                //Juego único
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
}