package com.grupoclase.lottery;

import com.grupoclase.lottery.UserTicket;
import com.grupoclase.dynamicarray.GenericDynamicArray;
import com.grupoclase.lib.ConsoleMenu;
import com.grupoclase.lib.LibIO;

import java.util.Arrays;

/*
Classe que maneja la entrada/salida y los menus de la primitiva.
 */

public class LotteryGame {
    private ConsoleMenu mainMenu;
    private ConsoleMenu subMenuTicket;
    private ConsoleMenu subMenuGameMode;
    private UserTicket userTicket;
    private LotteryDrum bigDrum;
    private LotteryDrum littleDrum;
    private int[] ticket;

    public LotteryGame() {
        bigDrum = new LotteryDrum(1, 49);
        littleDrum = new LotteryDrum(0, 9);
        userTicket = new UserTicket();
        System.out.println(bigDrum); //todo: TESTING PURPOSE
        System.out.println(littleDrum); //TODO: TESTING PURPOSE

        mainMenu = new ConsoleMenu("LOTTERY");
        mainMenu.addOpcion("Play Game");
        mainMenu.addOpcion("Exit");

        subMenuTicket = new ConsoleMenu("TICKET");
        subMenuTicket.addOpcion("Introduce your Ticket Number...");
        subMenuTicket.addOpcion("Generate Random Ticket Number...");

        subMenuGameMode = new ConsoleMenu("GAME MODE");
        subMenuGameMode.addOpcion("Unique Game...");
        subMenuGameMode.addOpcion("Play until Prize with Refund...");
        subMenuGameMode.addOpcion("Play until Prize without Refund...");
        subMenuGameMode.addOpcion("Game of 10000 Draws...");
        subMenuGameMode.addOpcion("Play until Special Prize...");


        int option;
        boolean numbersGenerated;

        do {
            option = mainMenu.mostrarMenuInt();

            switch (option) {
                case 1:
                    ticketMenu();
                    //Jugar
                    break;
                case 2:
                    //Salir
                    System.out.println("See you soon...");
                    break;
                default:
                    System.out.println("The introduced number is not in range [1 - 2]");
                    break;
            }
        } while (option != 2);
    }

    private void ticketMenu() {
        int option = subMenuTicket.mostrarMenuInt();

        switch (option) {
            case 1:
                //Obtain numbers manual:
                obtainUserNumbersManual(6);
                break;
            case 2:
                //Generate random ticket.
                obtainAutomaticTicket();
                break;
            default:
                System.out.println("The introduced number is not in range [ 1 - 2]");
                break;
        }
        //al "arrayDeEnteros" habrá que sumarle los números del reintegro, es decir crear un nuevo array con una capacidad superior al "arrayDeEnteros"
        //usando un bucle for hay que copiar arrayDeEnteros uno por uno al nuevo array con mayor capacidad y agregar los números aleatorios del reintegro al final del array nuevo, el array nuevo se llamará "arrayTicket"
        //ticket = arrayTicket;
        subMenuGameMode();
    }

    private void subMenuGameMode() {
        int option = subMenuGameMode.mostrarMenuInt();

        switch (option) {
            case 1:
                juegoUnico();
                break;
            case 2:
                jugarHastaPremio();
                break;
            case 3:
                jugarHastaPremioSinReintegro();
                break;
            case 4:
                gameOf10000Draws();
                break;
            case 5:
                //jugarHastaPremioEspecial();
                break;
            default:
                System.out.println("The introduced number is not in range [ 1 - 5]");
                break;
        }
    }


    //Solicitar datos:

    /**
     * Method which first checks if the array is null or size equal to 0; otherwise, it resets the array to 0 values.
     * This way, the user can always introduce new numbers in case they play several times.
     * The next step is asking the user for the 6 numbers. The additional 7th number is added randomly.
     * If the numbers have been added correctly, it prints them to confirm; otherwise, it prints ERROR.
     *
     * @return ticketManual with numbers, including the 7th number added randomly (refund).
     */
    public UserTicket obtainUserNumbersManual(int quantityNumbers) {
        if (userTicket == null || userTicket.getNumbers().length == 0) {
            userTicket = new UserTicket();
        } else {
            userTicket.resetTicket();
            //TODO: Remove after testing
            System.out.println("The array has been reset...");
        }

        int[] userInputNumbers = new int[quantityNumbers];
        for (int i = 0; i < quantityNumbers; i++) {
            int number = LibIO.requestInt("Introduce the number " + (i + 1) + " of your Ticket:", 0, 49);
            userInputNumbers[i] = number;
        }

        userTicket.setNumbers(userInputNumbers);
        System.out.println("You are playing with these numbers: " + userTicket);
        return userTicket;
    }

    /**
     * Generates an automatic lottery ticket for the user and displays the generated numbers.
     *
     * @return The UserTicket object representing the automatically generated ticket.
     */
    public UserTicket obtainAutomaticTicket() {
        userTicket = new UserTicket();
        userTicket.generateNumbers();
        System.out.println(userTicket);
        return userTicket;
    }


    //A partir de este punto surgen problemas.
    private void juegoUnico() {
        //Se gira el bombo y se extraen los números:
        int[] firstDrumNumbers = bigDrum.extraerCombinacionGanadora(6);
        int[] secondDrumNumbers = littleDrum.extraerCombinacionGanadora(2);
        int[] ticket = userTicket.getNumbers();

        System.out.println("Números ganadores del primer bombo:" + Arrays.toString(firstDrumNumbers));
        System.out.println("Números ganadores del segundo bombo: " + Arrays.toString(secondDrumNumbers));
        System.out.println("Tus números: " + ticket);

        boolean anyPrizeWon = false;

        for (int i = 0; i < PrizeCategory.values().length; i++) {
            PrizeCategory category = PrizeCategory.values()[i];
            if (category.isWinner(firstDrumNumbers, ticket, secondDrumNumbers)) {
                System.out.println("¡Felicidades! Has ganado en la categoria " + category.getCategoryName());
                anyPrizeWon = true;
            }
        }
        if (!anyPrizeWon) {
            System.out.println("Mala Suerte, no has ganado ningún premio...");
        }
    }


    public void jugarHastaPremio() {
        boolean premio = false;
        while (premio == false) {
            int numberAttemps = 0;
            int[] firstDrumNumbers = bigDrum.extraerCombinacionGanadora(6);
            int[] secondDrumNumbers = littleDrum.extraerCombinacionGanadora(2);

            for (PrizeCategory category : PrizeCategory.values()) {
                if (category.isWinner(firstDrumNumbers, ticket, secondDrumNumbers)) {
                    System.out.println("¡Felicidades! Has ganado en la categoría " + category.getCategoryName());
                    System.out.println("Números ganadores del primer bombo:" + firstDrumNumbers);
                    System.out.println("Números ganadores del segundo bombo: " + secondDrumNumbers);
                    System.out.println("Tus números: " + ticket);
                    return;
                }
            }

            numberAttemps++;

            System.out.println("Has perdido en el sorteo número " + numberAttemps);
            //TODO añadir que cuando gane "premio" = true;
            bigDrum.resetBombo();
            littleDrum.resetBombo();
        }
    }

    public void jugarHastaPremioSinReintegro() {
        boolean premio = false;
        while (premio == false) {

            int numberAttemps = 0;
            int[] firstDrumNumbers = bigDrum.extraerCombinacionGanadora(6);
            int[] secondDrumNumbers = littleDrum.extraerCombinacionGanadora(2);


            for (PrizeCategory category : PrizeCategory.values()) {
                if (category != PrizeCategory.SPECIAL && category.isWinner(firstDrumNumbers, ticket, secondDrumNumbers)) {
                    System.out.println("¡Felicidades! Has ganado en la categoría " + category.getCategoryName());
                    System.out.println("Números ganadores del primer bombo:" + firstDrumNumbers);
                    System.out.println("Números ganadores del segundo bombo: " + secondDrumNumbers);
                    System.out.println("Tus números: " + ticket);
                    return;
                }
            }
            numberAttemps++;
            System.out.println("Has perdido en el sorteo número" + numberAttemps);
            //todo añadir que cuando gane "premio" = true;
            bigDrum.resetBombo();
            littleDrum.resetBombo();
        }
    }

    public void gameOf10000Draws() {
        int[] prizeCounter = new int[PrizeCategory.values().length];
        for (int i = 0; i < 10000; i++) {

            bigDrum.resetBombo();
            littleDrum.resetBombo();

            int[] firstDrumNumbers = bigDrum.extraerCombinacionGanadora(6);
            int[] secondDrumNumbers = littleDrum.extraerCombinacionGanadora(2);

            for (PrizeCategory category : PrizeCategory.values()) {
                if (category.isWinner(firstDrumNumbers, ticket, secondDrumNumbers)) {
                    prizeCounter[category.ordinal()]++;
                }
            }
        }

        System.out.println("Resumen de premios: ");
        for (PrizeCategory category : PrizeCategory.values()) {
            System.out.println(category.getCategoryName() + ": " + prizeCounter[category.ordinal()]);
        }
    }


}


