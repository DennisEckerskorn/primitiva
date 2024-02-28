package com.grupoclase.resources;

import com.grupoclase.lib.ConsoleMenu;

//Classe que maneja la entrada/salida y los menus de la primitiva.
public class Principal {
    private ConsoleMenu menu;
    public Principal(){
        menu = new ConsoleMenu("PRIMITIVA");
        menu.addOpcion("Ingresar número del boleto.");
        menu.addOpcion("Generar número del boleto");
        menu.addOpcion("Salir");

        int opcion;

        do{
            opcion = menu.mostrarMenuInt();
            switch(opcion) {
                case 1:
                    //Método para pedir los numeros del boleto al usuario
                    break;
                case 2:
                    //Metodo para generar una lista de numeros de un boleto para que el usuario elija el número
                    break;
                case 3:
                    System.out.println("Hasta pronto ...");
                    break;
                default:
                    System.out.println("EL valor introducido es inválido, solo valores entre 1 - 3");
            }
        }while(opcion != 3);

    }
}
