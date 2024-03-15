package com.grupoclase.lottery;

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

    /**
     * Constructs a new instance of the LotteryGame class.
     * Initializes the main components of the lottery game, including the drums, user ticket, and menus.
     */
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
        subMenuTicket.addOpcion("Return to Main Menu...");

        subMenuGameMode = new ConsoleMenu("GAME MODE");
        subMenuGameMode.addOpcion("Unique Game...");
        subMenuGameMode.addOpcion("Play until Prize with Refund...");
        subMenuGameMode.addOpcion("Play until Prize without Refund...");
        subMenuGameMode.addOpcion("Game of 10000 Draws...");
        subMenuGameMode.addOpcion("Play until Special Prize...");
        subMenuGameMode.addOpcion("Return to Ticket Menu...");

        displayMainMenu();
    }

    private void displayMainMenu() {
        int option;

        do {
            option = mainMenu.mostrarMenuInt();

            switch (option) {
                case 1:
                    ticketMenu();
                    break;
                case 2:
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
                //Generate random ticket:
                obtainAutomaticTicket();
                break;
            case 3:
                //Return to main menu:
                displayMainMenu();
                break;
            default:
                System.out.println("The introduced number is not in range [ 1 - 3]");
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
                uniqueGame();
                break;
            case 2:
                playTillWinWithRefound();
                break;
            case 3:
                playTillWinWithoutRefound();
                break;
            case 4:
                gameOf10000Draws();
                break;
            case 5:
                playToWinSpecialPrize();
                break;
            case 6:
                ticketMenu();
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

        //Problema al validar la repeticion de los numeros de cada posicion del array.


        for (int i = 0; i < quantityNumbers; i++) {
            userInputNumbers[i] = LibIO.requestIntValidating("Introduce the number " + (i + 1) + " of your Ticket:", 0, 49);
            if (i > 0) {
                while (validateRepeatedNumber(userInputNumbers, i, userInputNumbers[i])) {
                    userInputNumbers[i] = LibIO.requestIntValidating("The number already exists, please introduce the number again", 0, 49);

                }
            }
        }
        if (userTicket.setNumbers(userInputNumbers)) {
            System.out.println("You are playing with these numbers: " + userTicket);
            return userTicket;
        } else {
            return null;
        }
    }

    private boolean validateRepeatedNumber(int[] numbers, int position, int number) {
        for (int i = 0; i < position; i++) {
            if (numbers[i] == number) {
                return true; // Return true if the number is repeated
            }
        }
        return false; // Return false if the number is not repeated
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


    /**
     * Performs a single round of lottery game, where the winning numbers are drawn from two drums and compared with the user's ticket
     * to determine if any prize has been won.
     * Draws the winning numbers from the first drum and the second drum, as well as the numbers from the user's ticket.
     * Compares the numbers on the user's ticket with the winning numbers for each prize category.
     * If the user has won in any category, a congratulations message is displayed.
     * Otherwise, a message indicating no prize has been won is displayed.
     */
    private void uniqueGame() {
        //Se gira el bombo y se extraen los números:
        int[] firstDrumNumbers = bigDrum.extraerCombinacionGanadora(6); //Numeros + complementario
        int[] secondDrumNumbers = littleDrum.extraerCombinacionGanadora(1); //Reintegro
        int[] ticket = userTicket.getNumbers();

        System.out.println("Winning numbers of the first Drum:" + Arrays.toString(firstDrumNumbers));
        System.out.println("Winning numbers of the second Drum: " + Arrays.toString(secondDrumNumbers));
        System.out.println("Your numbers: " + userTicket);

        boolean anyPrizeWon = false;

        for (int i = 0; i < PrizeCategory.values().length; i++) {
            PrizeCategory category = PrizeCategory.values()[i];
            if (category.isWinner(firstDrumNumbers, ticket, secondDrumNumbers)) {
                System.out.println("¡Congratulations! You have won in the category: " + category.getCategoryName());
                anyPrizeWon = true;
            }
        }
        if (!anyPrizeWon) {
            System.out.println("Unfortunately, you haven't won any prizes this time...");
        }
    }


    /**
     * Plays the lottery until winning a prize including the refund.
     * During each attempt, the winning numbers are extracted from the two drums and compared
     * with the user's ticket numbers to determine if any prize has been won including the refund.
     * Upon winning a prize, a congratulatory message is displayed along with the winning numbers
     * and the user's ticket. The method stops its execution after winning a prize.
     * If no prize is won including the refund, a message indicating that no prizes have been won is displayed.
     * The drums are reset after each attempt.
     */

    public void playTillWinWithRefound() {
        boolean prize = false;
        int numberAttemps = 0;

        while (!prize) {
            numberAttemps++;
            int[] firstDrumNumbers = bigDrum.extraerCombinacionGanadora(6);
            int[] secondDrumNumbers = littleDrum.extraerCombinacionGanadora(2);
            int[] ticket = userTicket.getNumbers();

            boolean anyPrizeWon = false;

            for (PrizeCategory category : PrizeCategory.values()) {
                if (category.isWinner(firstDrumNumbers, ticket, secondDrumNumbers)) {
                    System.out.println("¡Congratulations! You have won in the category: " + category.getCategoryName());
                    System.out.println("Winning numbers of the first Drum:" + Arrays.toString(firstDrumNumbers));
                    System.out.println("Winning numbers of the second Drum: " + Arrays.toString(secondDrumNumbers));
                    System.out.println("Your numbers: " + userTicket);
                    anyPrizeWon = true;
                    prize = true;
                }
            }

            if (!anyPrizeWon) {
                System.out.println("Unfortunately, you haven't won any prizes in the attemp number" + numberAttemps);
            }
            bigDrum.resetBombo();
            littleDrum.resetBombo();

        }
    }

    /**
     * Plays the lottery until winning a prize excluding the refund.
     * During each attempt, the winning numbers are extracted from the two drums and compared
     * with the user's ticket numbers to determine if any prize has been won excluding the refund.
     * Upon winning a prize, a congratulatory message is displayed along with the winning numbers
     * and the user's ticket. The method stops its execution after winning a prize.
     * If no prize is won excluding the refund, a message indicating that no prizes have been won is displayed.
     * The drums are reset after each attempt.
     */
    public void playTillWinWithoutRefound() {
        boolean prize = false;
        int numberAttemps = 0;

        while (!prize) {
            numberAttemps++;

            int[] firstDrumNumbers = bigDrum.extraerCombinacionGanadora(6);
            int[] secondDrumNumbers = littleDrum.extraerCombinacionGanadora(2);
            int[] ticket = userTicket.getNumbers();


            boolean anyPrizeWon = false;

            for (PrizeCategory category : PrizeCategory.values()) {
                if (category != PrizeCategory.SPECIAL && category.isWinner(firstDrumNumbers, ticket, secondDrumNumbers)) {
                    System.out.println("¡Congratulations! You have won in the category: " + category.getCategoryName());
                    System.out.println("Winning numbers of the first Drum:" + Arrays.toString(firstDrumNumbers));
                    System.out.println("Winning numbers of the second Drum: " + Arrays.toString(secondDrumNumbers));
                    System.out.println("Your numbers: " + userTicket);
                    anyPrizeWon = true;
                    prize = true;
                }
            }

            if (!anyPrizeWon) {
                System.out.println("Unfortunately, you haven't won any prizes in the attemp number" + numberAttemps);
            }

            bigDrum.resetBombo();
            littleDrum.resetBombo();
        }
    }

    /**
     * Performs 10000 lottery draws.
     * During each draw, winning numbers are extracted from the two drums and compared
     * with the numbers on the user's ticket to determine if any prizes have been won.
     * After each draw, the drums are reset for the next draw.
     * Upon completion of the 10000 draws, a summary of the prizes won in each category is displayed.
     */

    public void gameOf10000Draws() {
        int[] prizeCounter = new int[PrizeCategory.values().length];


        for (int i = 0; i < 10000; i++) {

            int[] firstDrumNumbers = bigDrum.extraerCombinacionGanadora(6);
            int[] secondDrumNumbers = littleDrum.extraerCombinacionGanadora(2);
            int[] ticket = userTicket.getNumbers();

            for (PrizeCategory category : PrizeCategory.values()) {
                if (category.isWinner(firstDrumNumbers, ticket, secondDrumNumbers)) {
                    prizeCounter[category.ordinal()]++;
                }
            }

            bigDrum.resetBombo();
            littleDrum.resetBombo();
        }

        System.out.println("Summary of awards: ");
        for (PrizeCategory category : PrizeCategory.values()) {
            System.out.println(category.getCategoryName() + ": " + prizeCounter[category.ordinal()]);
        }
    }


    /**
     * Performs draws until winning a prize in the special category.
     * After each draw, the winning numbers are compared with the user's ticket numbers
     * to determine if a win has been achieved.
     * Once the prize is obtained, a congratulatory message is displayed with the winning numbers
     * and the user's ticket, along with the number of draws made until obtaining the special prize.
     */
    private void playToWinSpecialPrize() {
        boolean prize = false;
        int numberAttemps = 0;
        long intento=0;

        while (!prize) {
            numberAttemps++;

            int[] firstDrumNumbers = bigDrum.extraerCombinacionGanadora(6);
            int[] secondDrumNumbers = littleDrum.extraerCombinacionGanadora(1);
            int[] ticket = userTicket.getNumbers();
            intento++;
            if(intento % 1000000 == 0){
                System.out.println("intento"+intento);
                System.out.println("Winning numbers of the first Drum:" + Arrays.toString(firstDrumNumbers));
                System.out.println("Winning numbers of the second Drum: " + Arrays.toString(secondDrumNumbers));
                System.out.println("Your numbers: " + userTicket);
            }
            if (PrizeCategory.SPECIAL.isWinner(firstDrumNumbers, ticket, secondDrumNumbers)) {
                System.out.println("¡Congratulations!, you have won the special prize ");
                System.out.println("Winning numbers of the first Drum:" + Arrays.toString(firstDrumNumbers));
                System.out.println("Winning numbers of the second Drum: " + Arrays.toString(secondDrumNumbers));
                System.out.println("Your numbers: " + userTicket);
                prize = true;
            }
            bigDrum.resetBombo();
            littleDrum.resetBombo();
        }

        System.out.println("Number of draws until the special prize is won:  " + numberAttemps);
    }
}
