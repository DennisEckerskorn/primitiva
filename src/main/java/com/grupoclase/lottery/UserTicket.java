package com.grupoclase.lottery;

import java.util.Arrays;
import java.util.Random;

/**
 * The UserTicket class represents a ticket in the Primitiva game, storing a combination of numbers.
 */
public class UserTicket {
    private int[] numbers;
    private int reimbursement;
    private int complementary;

    /**
     * Constructor for the UserTicket class.
     * Initializes the array of numbers for the ticket.
     */
    public UserTicket() {
        this.numbers = new int[6]; // 6 numbers
    }

    /**
     * Generates a random combination of numbers for the ticket.
     * The generated numbers are within the range of 1 to 49.
     */
    public void generateNumbers() {
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            numbers[i] = random.nextInt(49) + 1;
        }
        generateComplementary();
    }

    /**
     * Generates the complementary number for the ticket.
     * The complementary number is selected from the remaining numbers after generating the first 6 numbers.
     */
    private void generateComplementary() {
        Random random = new Random();
        boolean found = false;
        while (!found) {
            int tempComplementary = random.nextInt(49) + 1;
            found = true;
            for (int number : numbers) {
                if (tempComplementary == number) {
                    found = false;
                    break;
                }
            }
            if (found) {
                complementary = tempComplementary;
            }
        }
    }

    /**
     * Gets the numbers of the ticket.
     *
     * @return An array containing the combination of numbers of the ticket.
     */
    public int[] getNumbers() {
        return numbers;
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

    /**
     * Sets the reimbursement number for the ticket.
     *
     * @param reimbursement The reimbursement number to set.
     */
    public void setReimbursement(int reimbursement) {
        this.reimbursement = reimbursement;
    }

    /**
     * Gets the reimbursement number for the ticket.
     *
     * @return The reimbursement number.
     */
    public int getReimbursement() {
        return reimbursement;
    }

    /**
     * Gets the complementary number for the ticket.
     *
     * @return The complementary number.
     */
    public int getComplementary() {
        return complementary;
    }

    /**
     * Resets the numbers of the ticket to zero.
     */
    public void resetTicket() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 0;
        }
        reimbursement = 0;
    }

    @Override
    public String toString() {
        return "\nUserTicket{" +
                "numbers=" + Arrays.toString(numbers) +
                ", reimbursement=" + reimbursement +
                ", complementary=" + complementary +
                '}';
    }
}
