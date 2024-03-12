package com.grupoclase.lottery;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The UserTicket class represents a ticket in the Primitiva game, storing a combination of numbers.
 */
public class UserTicket {
    private int[] numbers;
    private int reimbursement;
    private int complementary;

    /**
     * Constructor for the UserTicket class when prompting the user for 6 main numbers and a complementary number.
     * The reimbursement number is generated randomly.
     */
    public UserTicket(int[] inputNumbers) {
        if (inputNumbers.length == 6) {
            this.numbers = inputNumbers;
            this.complementary = inputComplementary();
            this.reimbursement = new Random().nextInt(10); // Generate random reimbursement from 0 to 9
        } else {
            throw new IllegalArgumentException("Invalid number of input numbers. The ticket requires exactly 6 numbers.");
        }
    }

    /**
     * Constructor for the UserTicket class when generating 6 main numbers, a random reimbursement, and a random complementary number.
     */
    public UserTicket() {
        this.numbers = generateRandomNumbers();
        this.reimbursement = new Random().nextInt(10); // Generate random reimbursement from 0 to 9
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
    private int[] generateRandomNumbers() {
        Random random = new Random();
        int[] randomNumbers = new int[6];
        for (int i = 0; i < 6; i++) {
            randomNumbers[i] = random.nextInt(49) + 1;
        }
        return randomNumbers;
    }

    /**
     * Generates a random complementary number.
     *
     * @return The randomly generated complementary number.
     */
    private int generateComplementary() {
        Random random = new Random();
        return random.nextInt(49) + 1;
    }

    // Getters and setters for reimbursement and complementary numbers

    @Override
    public String toString() {
        return "UserTicket{" +
                "numbers=" + Arrays.toString(numbers) +
                ", reimbursement=" + reimbursement +
                ", complementary=" + complementary +
                '}';
    }


}
