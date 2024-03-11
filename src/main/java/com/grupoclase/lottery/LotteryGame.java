package com.grupoclase.lottery;

import com.grupoclase.dynamicarray.GenericDynamicArray;
import com.grupoclase.lib.ConsoleMenu;

/*
Classe que maneja la entrada/salida y los menus de la primitiva.
 */

public class LotteryGame {
    private ConsoleMenu mainMenu;
    private ConsoleMenu subMenu;
    private ConsoleMenu numberMenu;
    private UserTicket userTicket;
    private LotteryDrum firstDrum;
    private LotteryDrum secondDrum;

    public LotteryGame() {
        //firstDrum = new LotteryDrum("Big Drum", 1, 49);
        //secondDrum = new LotteryDrum("Small Drum", 0, 9);
        System.out.println(firstDrum); //todo: TESTING PURPOSE
        System.out.println(secondDrum); //TODO: TESTING PURPOSE

        mainMenu = new ConsoleMenu("PRIMITIVA");
        mainMenu.addOpcion("Jugar");
        mainMenu.addOpcion("Salir");

        subMenu = new ConsoleMenu("TIPO DE JUEGO");
        subMenu.addOpcion("Introducir / Generar números");
        subMenu.addOpcion("Jugada Única...");

        numberMenu = new ConsoleMenu("OBTENER NÚMEROS");
        numberMenu.addOpcion("Introducir números manualmente...");
        numberMenu.addOpcion("Generar números aleatorios...");


        int option;
        boolean numbersGenerated;

        do {
            option = mainMenu.mostrarMenuInt();

            switch (option) {
                case 1:
                    //Jugar
                    submenu(userTicket);
                    break;
                case 2:
                    //Salir
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("El número introducido está fuera de rango [1 - 2]");
                    break;
            }
        } while (option != 2);
    }

    private void submenu(UserTicket userTicket) {
        int option = subMenu.mostrarMenuInt();

        switch (option) {
            case 1:
                //Obtener numeros para jugar.
                numberMenu(userTicket);
                break;
            case 2:
                //playSingleGame(userTicket);
                break;
            default:
                System.out.println("El número introducido está fuera de rango [ 1 - ?]");
                break;
        }
    }

    private void numberMenu(UserTicket userTicket) {
        int option = numberMenu.mostrarMenuInt();

        UserTicket ticket = null;

        switch (option) {
            case 1:
                //userTicket = obtainUserNumbersManual(6);
                break;
            case 2:
                //userTicket = obtainAutomaticTicket();
                break;
            default:
                System.out.println("El número introducido está fuera de rango [ 1 - ?]");
                break;
        }
        submenu(userTicket);
    }


    //Solicitar datos:

    /**
     * Method which first checks if array is null or size equal to 0, otherwise it resets the array to 0 values.
     * This way the user can always introduce new numbers in case he plays several times.
     * Next step is asking the user for the 6 numbers, addNumbersToArray is used to convert the string into int and add them to an array.
     * if numbers have been added correctly it prints them to confirm, otherwise it prints ERROR.
     *
     * @return ticketManual with numbers, also the 7 number is added randomly (refund).
     */
    /*
    public UserTicket obtainUserNumbersManual(int quantityNumbers) {
        if (userTicket == null || userTicket.getUserData().size() == 0) {
            userTicket = new UserTicket("DennisManual");
        } else {
            userTicket.resetTicket();
            //TODO: Remove after testing
            System.out.println("El array ha sido vaciado...");
        }

        GenericDynamicArray<Integer> numbers = new GenericDynamicArray<>(6);
        for(int i = 0; i < quantityNumbers; i++) {
            int number = LibIO.requestInt("Introduce el número " + (i +1) + " de tu boleto:", 0, 49);
            numbers.add(number);
        }

        boolean numbersAdded = userTicket.addNumbersToArray(numbers);

        if (numbersAdded) {
            System.out.println("Juegas con estos números: " + userTicket);
            return userTicket;
        } else {
            System.out.println("Error, los números no son válidos");
        }
        return userTicket;
    }
    */

    /**
     * Method to create a new object with random ticket numbers
     * Prints the numbers to verify.
     *
     * @return array with random numbers (ticketManual).
     */
    public UserTicket obtainAutomaticTicket() {
        userTicket = new UserTicket("DennisAuto");
        userTicket.generateRandomTicket();
        System.out.println(userTicket);
        return userTicket;
    }

    private void playSingleGame(UserTicket userTicket) {
        //Se gira el bombo y se extraen los números:
        GenericDynamicArray<Integer> firstDrumNumbers = firstDrum.rotateAndDraw(6);
        GenericDynamicArray<Integer> secondDrumNumbers = secondDrum.rotateAndDraw(2);
        //Se obtienen los números del usuario:
        GenericDynamicArray<Integer> userNumbers = userTicket.getUserData();

        System.out.println("Números ganadores del primer bombo:" + firstDrumNumbers);
        System.out.println("Números ganadores del segundo bombo: " + secondDrumNumbers);
        System.out.println("Tus números: " + userNumbers);

        boolean anyPrizeWon = false;

        for (int i = 0; i < PrizeCategory.values().length; i++) {
            PrizeCategory category = PrizeCategory.values()[i];
            if (category.isWinner(firstDrumNumbers, userNumbers, secondDrumNumbers)) {
                System.out.println("¡Felicidades! Has ganado en la categoria " + category.getCategoryName());
                anyPrizeWon = true;
            }
        }
        if(!anyPrizeWon) {
            System.out.println("Mala Suerte, no has ganado ningún premio...");
        }
    }

    /*

    public void playUntilPrizeWithRefund(UserTicket userTicket) {
        while (true) {

            int numberAttemps = 0;

            GenericDynamicArray<Integer> firstDrumNumbers = firstDrum.rotateAndDraw(6);
            GenericDynamicArray<Integer> secondDrumNumbers = secondDrum.rotateAndDraw(2);
            GenericDynamicArray<Integer> userNumbers = userTicket.getUserData();

            for (PrizeCategory category : PrizeCategory.values()) {
                if (category.isWinner(firstDrumNumbers, userNumbers, secondDrumNumbers)) {
                    System.out.println("¡Felicidades! Has ganado en la categoría " + category.getCategoryName());
                    System.out.println("Números ganadores del primer bombo:" + firstDrumNumbers);
                    System.out.println("Números ganadores del segundo bombo: " + secondDrumNumbers);
                    System.out.println("Tus números: " + userNumbers);
                    return;
                }
            }

            numberAttemps++;

            System.out.println("Has perdido en el sorteo número " + numberAttemps);
        }
    }
      */


}


