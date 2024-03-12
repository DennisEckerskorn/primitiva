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

    /**
     * Constructor for the UserTicket class.
     * Initializes the array of numbers for the ticket.
     */
    public UserTicket() {
        this.numbers = new int[6]; // 6 numbers
    }

  /* Generates a random combination of numbers for the ticket.
            * The generated numbers are within the range of 1 to 49.
            */
    public void generateNumbers() {
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            numbers[i] = random.nextInt(49) + 1;
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
         * Allows the user to choose numbers for the ticket.
         * This method prompts the user to enter numbers and validates the input to ensure they are within the required ranges and not repeated.
         */
      /*  public void chooseNumbers() {
            Scanner scanner = new Scanner(System.in);
            boolean repeated = true, outOfRange = true;
            int i, j;

            // Loop to choose numbers
            while (repeated || outOfRange) {
                for (i = 0; i < 6; i++) {
                    // Prompt for number
                    System.out.println("Enter number " + (i + 1) + " (from 1 to 49):");
                    // Read number
                    numbers[i] = scanner.nextInt();
                    repeated = false;
                    outOfRange = false;
                    // Check if the number is within the range
                    if (numbers[i] < 1 || numbers[i] > 49) {
                        System.out.println("The number is not valid. It should be between 1 and 49.");
                        outOfRange = true;
                    }
                    // Check if the number is repeated
                    if (i > 0) {
                        for (j = i - 1; j >= 0; j--) {
                            if (numbers[i] == numbers[j]) {
                                System.out.println("The number is not valid. It is repeated.");
                                repeated = true;
                            }
                        }
                    }
                }
            }
        }
*/
        /**
         * Resets the numbers of the ticket to zero.
         */
        public void resetTicket() {
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = 0;
            }
            reimbursement = 0;
        }

        /**
         * Validates if the ticket numbers are valid for the draw.
         *
         * @return True if the numbers are valid, otherwise false.
         */
      /*  public boolean validateTicket() {
            // Validate numbers are within the range
            for (int i = 0; i < 6; i++) {
                if (numbers[i] < 1 || numbers[i] > 49) {
                    return false;
                }
            }
            // Validate numbers are not repeated
            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    if (numbers[i] == numbers[j]) {
                        return false;
                    }
                }
            }
            return true;
        }*/
    }
