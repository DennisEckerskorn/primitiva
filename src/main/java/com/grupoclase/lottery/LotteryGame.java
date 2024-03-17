package com.grupoclase.lottery;

import com.grupoclase.lib.ConsoleMenu;
import com.grupoclase.lib.LibIO;

import java.util.Arrays;

/*
Class which manages the menus and the user input/output. It also contains the game modes of each game...
 */

public class LotteryGame {
    private final ConsoleMenu mainMenu;
    private final ConsoleMenu subMenuTicket;
    private final ConsoleMenu subMenuGameMode;
    private UserTicket userTicket;
    private final LotteryDrum bigDrum;
    private final LotteryDrum littleDrum;

    /**
     * Constructs a new instance of the LotteryGame class.
     * Initializes the main components of the lottery game, including the drums, user ticket, and menus.
     */
    public LotteryGame() {
        bigDrum = new LotteryDrum(1, 49);
        littleDrum = new LotteryDrum(0, 9);
        userTicket = new UserTicket();
        System.out.println(bigDrum); //TODO: TESTING PURPOSE, REMOVE WHEN FINISHED
        System.out.println(littleDrum); //TODO: TESTING PURPOSE, REMOVE WHEN FINISHED

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

    /**
     * Main Menu, this menu offers to start the game or to exit the game.
     * This menu is the first menu which is shown...
     */
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

    /**
     * Ticket Menu, this menu offers to obtain the user ticket manual or automatically.
     * It also offers to return to the main menu.
     */
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
        subMenuGameMode();
    }

    /**
     * Game Mode Menu, this menu offers the different game modes.
     * It also offers an option to return to the Ticket Menu to generate different numbers without leaving the game.
     */
    private void subMenuGameMode() {
        int option = subMenuGameMode.mostrarMenuInt();

        switch (option) {
            case 1:
                //Play the uniqueGame game mode
                uniqueGame();
                break;
            case 2:
                //Play the playTillWinWithRefound game mode
                playTillWinWithRefound();
                break;
            case 3:
                //Play the playTillWinWithoutRefound game mode
                playTillWinWithoutRefound();
                break;
            case 4:
                //Play the gameOf10000Draws game mode
                gameOf10000Draws();
                break;
            case 5:
                //Play the playToWinSpecialPrize game mode
                playToWinSpecialPrize();
                break;
            case 6:
                //Return to ticket menu
                ticketMenu();
                break;
            default:
                System.out.println("The introduced number is not in range [ 1 - 5]");
                break;
        }
    }


    // *** DATA INPUT + GAME MODE METHODS ***

    /**
     * Method checks if the array is null or size equals 0; if so, it initializes the array.
     * Otherwise, it resets the array to 0 values.
     * This allows the user to input new numbers, especially if playing multiple times.
     * The method prompts the user for 6 numbers, and adds a 7th number randomly.
     * If the numbers are added correctly, it prints them to confirm; otherwise, it prints "ERROR".
     *
     * @param quantityNumbers the number of numbers to be input by the user
     * @return ticketManual with the user-provided numbers, including the 7th randomly added number
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

    /**
     * Checks if a number is repeated within a given array up to a certain position.
     *
     * @param numbers  the array of numbers to be checked for repetition
     * @param position the position up to which to check for repetition
     * @param number   the number to be checked for repetition
     * @return true if the number is repeated within the array up to the specified position, false otherwise
     */
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
     * Performs a single round of the lottery game, where the winning numbers are drawn from two drums and compared with the user's ticket
     * to determine if any prize has been won.
     * It draws the winning numbers from the first drum and the second drum, as well as retrieves the numbers from the user's ticket.
     * Then, it compares the numbers on the user's ticket with the winning numbers for each prize category.
     * If the user has won in any category, a congratulations message is displayed.
     * Otherwise, a message indicating that no prize has been won is displayed.
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
        int numberAttempts = 0;

        while (!prize) {
            numberAttempts++;
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
                if (numberAttempts % 100000 == 0)
                    System.out.println("Unfortunately, you have not won any prizes in " + numberAttempts + " attempts.");
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
        int numberAttempts = 0;

        while (!prize) {
            numberAttempts++;

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
                if (numberAttempts % 100000 == 0)
                    System.out.println("Unfortunately, you have not won any prizes in " + numberAttempts + " attempts.");
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
        int numberAttempts = 0;

        while (!prize) {
            numberAttempts++;
            int[] ticket = new int[8];
            int[] firstDrumNumbers = bigDrum.extraerCombinacionGanadora(6);
            int[] reinbursmentDrumArray = littleDrum.extraerCombinacionGanadora(1);
            int reinbursmentDrum;
            ticket = userTicket.getNumbers();
            int reinbursmentTicket = userTicket.getReimbursement();

            reinbursmentDrum = reinbursmentDrumArray[0];

            if (numberAttempts % 1000000 == 0) {
                System.out.println("number of attempts: " + numberAttempts);
                System.out.println("Winning numbers of the first Drum:" + Arrays.toString(firstDrumNumbers));
                System.out.println("Winning numbers of the second Drum: " + Arrays.toString(reinbursmentDrumArray));
                System.out.println("Your numbers: " + userTicket);
            }
            if (PrizeCategory.SPECIAL.isWinner(firstDrumNumbers, ticket, reinbursmentTicket,reinbursmentDrum)) {
                System.out.println("¡Congratulations!, you have won the special prize ");
                System.out.println("Winning numbers of the first Drum:" + Arrays.toString(firstDrumNumbers));
                System.out.println("Winning numbers of the second Drum: " + Arrays.toString(reinbursmentDrumArray));
                System.out.println("Your numbers: " + userTicket);
                prize = true;
            }
            bigDrum.resetBombo();
            littleDrum.resetBombo();
        }

        System.out.println("Number of draws until the special prize is won: " + numberAttempts);
    }
}
