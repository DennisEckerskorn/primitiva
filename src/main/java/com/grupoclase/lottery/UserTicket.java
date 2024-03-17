package com.grupoclase.lottery;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The UserTicket class represents a ticket in the Primitiva game, storing a combination of numbers.
 */
public class UserTicket {
    private int[] numbers;
    private int reinbursment;
    private int complementary;

    /**
     * Constructor for the UserTicket class when prompting the user for 6 main numbers and a complementary number.
     * The reinbursment number is generated randomly.
     */
    public UserTicket(int[] inputNumbers) {
        if (inputNumbers.length == 6) {
            this.numbers = inputNumbers;
            this.complementary = inputComplementary();
            this.reinbursment = new Random().nextInt(10); // Generate random reinbursment from 0 to 9
        } else {
            throw new IllegalArgumentException("Invalid number of input numbers. The ticket requires exactly 6 numbers.");
        }
    }

    /**
     * Constructor for the UserTicket class when generating 6 main numbers, a random reinbursment, and a random complementary number.
     */

    public UserTicket() {
        this.numbers = generateRandomNumbers();
        this.reinbursment = new Random().nextInt(10); // Generate random reinbursment from 0 to 9
        this.complementary = generateComplementary();
    }

    /**
     * Prompts the user to enter 6 main numbers.
     *
     * @return An array containing the 6 main numbers chosen by the user.
     */
    private int[] inputNumbers() {
        Scanner scanner = new Scanner(System.in);
        int[] inputNumbers = new int[6];
        for (int i = 0; i < 6; i++) {
            System.out.println("Enter number " + (i + 1) + ": ");
            inputNumbers[i] = scanner.nextInt();
        }
        return inputNumbers;
    }

    /**
     * Prompts the user to enter the complementary number.
     *
     * @return The complementary number chosen by the user.
     */
    private int inputComplementary() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter complementary number (1-49): ");
        return scanner.nextInt();
    }
    public int[] getNumbers() {
        return numbers;
    }
    /**
     * Generates a random combination of 6 main numbers.
     *
     * @return An array containing the 6 randomly generated main numbers.
     */
    public int[] generateRandomNumbers() {
        LotteryDrum rand = new LotteryDrum(1,49);
        Random random = new Random();
        int[] randomNumbers = new int[6];
        randomNumbers = rand.extraerCombinacionGanadora(6);
        rand.resetBombo();
        return randomNumbers;
    }

    /**
     * Generates a random complementary number.
     *
     * @return The randomly generated complementary number.
     */
    private int generateComplementary() {
        boolean[] availableNumbers = new boolean[49]; // Array para almacenar los números disponibles
        for (int number : numbers) {
            availableNumbers[number - 1] = true; // Marca como no disponible los números ya elegidos
        }
        Random random = new Random();
        int complementary;
        do {
            complementary = random.nextInt(49) + 1;
        } while (availableNumbers[complementary - 1]); // Repite si el número generado ya fue elegido
        return complementary;
    }


    // Getters and setters for reinbursment and complementary numbers

    public int getReimbursement() {
        return reinbursment;
    }

    public void setReimbursement(int reinbursment) {
        this.reinbursment = reinbursment;
    }

    public int getComplementary() {
        return complementary;
    }

    public void setComplementary(int complementary) {
        this.complementary = complementary;
    }

    @Override
    public String toString() {
        return "UserTicket{" +
                "numbers=" + Arrays.toString(numbers) +
                ", reinbursment=" + reinbursment +
                ", complementary=" + complementary +
                '}';
    }

    public String getNumbersOnlyString(){
        return "Your numbers = " + Arrays.toString(numbers);
    }
    public String getReinbursmentOnlyString(){
        return "Reinbursement=" + reinbursment;
    }
    public String getComplementaryOnlyString(){
        return "Complementary=" + complementary;
    }
    public String getComplementaryAndReinbursmentString(){
        return "Reinbursment =" + reinbursment +
                ", Complementary=" + complementary ;
    }
    /**
     * Resets the numbers of the ticket to zero.
     */
    public void resetTicket() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 0;
        }
        reinbursment = 0;
    }

    /**
     * Sets the numbers of the ticket based on the input array.
     *

     * @param inputNumbers An array containing the numbers to set for the ticket.
     */
    public boolean setNumbers(int[] inputNumbers) {
        if (inputNumbers.length == 6) {
            numbers = inputNumbers;
            generateComplementary();
            return true;
        } else {
            return false;
        }
    }
}
