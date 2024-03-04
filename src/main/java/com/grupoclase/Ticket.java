package com.grupoclase;

import java.util.Arrays;
import java.util.Random;

/**
 * The Ticket class represents a ticket in the Primitiva game, storing a combination of numbers.
 */
public class Ticket {
    private int[] numbers;

    /**
     * Constructor for the Ticket class.
     * Initializes the array of numbers for the ticket.
     */
    public Ticket() {
        this.numbers = new int[7]; // 6 numbers + 1 additional number
    }

    /**
     * Generates a random combination of numbers for the ticket.
     * The generated numbers are within the range of 1 to 49.
     * The additional number is generated within the range of 0 to 9.
     */
    public void generateNumbers() {
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            numbers[i] = random.nextInt(49) + 1;
        }
        numbers[6] = random.nextInt(10); // Additional number for the special category
    }

    /**
     * Gets the numbers of the ticket.
     * @return An array containing the combination of numbers of the ticket.
     */
    public int[] getNumbers() {
        return numbers;
    }

    /**
     * Sets the numbers of the ticket based on the input array.
     * @param inputNumbers An array containing the numbers to set for the ticket.
     */
    public void setNumbers(int[] inputNumbers) {
        if (inputNumbers.length == 7) {
            numbers = inputNumbers;
        } else {
            System.out.println("Error: Invalid number of elements. The ticket requires 7 numbers.");
        }
    }
}
