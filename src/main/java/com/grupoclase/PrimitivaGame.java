package com.grupoclase;

import java.util.Arrays;
import java.util.Scanner;

import static com.grupoclase.Prize.checkPrize;

/**
 * La clase PrimitivaGame representa el juego de La Primitiva.
 * Permite al usuario generar combinaciones de números y comprobar premios.
 */
import java.util.Scanner;

/**
 * The PrimitivaGame class represents the main program that simulates the Primitiva game.
 * It manages user input/output and controls the flow of the game.
 */
public class PrimitivaGame {
    private Scanner scanner;
    private Ticket ticket;

    /**
     * Constructor for the PrimitivaGame class.
     * Initializes the scanner for user input and creates a new Ticket object.
     */
    public PrimitivaGame() {
        this.scanner = new Scanner(System.in);
        this.ticket = new Ticket();
    }

    /**
     * Starts the Primitiva game.
     * Manages the main menu and user interactions.
     */
    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("PRIMITIVA GAME");
            System.out.println("--------------");
            System.out.println("1. Generate new ticket");
            System.out.println("2. Play game");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    generateTicket();
                    break;
                case 2:
                    playGame();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
        scanner.close();
    }

    /**
     * Generates a new ticket with user input or random numbers.
     */
    private void generateTicket() {
        System.out.println("Generate new ticket:");
        System.out.println("1. Manual input");
        System.out.println("2. Random numbers");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (option) {
            case 1:
                manualInput();
                break;
            case 2:
                ticket.generateNumbers();
                System.out.println("New ticket generated: " + Arrays.toString(ticket.getNumbers()));
                break;
            default:
                System.out.println("Invalid option. Ticket generation canceled.");
        }
    }

    /**
     * Allows the user to input the numbers manually for the ticket.
     */
    private void manualInput() {
        System.out.println("Enter 6 numbers (1-49) and 1 additional number (0-9):");
        int[] inputNumbers = new int[7];
        for (int i = 0; i < 7; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            inputNumbers[i] = scanner.nextInt();
        }
        ticket.setNumbers(inputNumbers);
        System.out.println("New ticket generated: " + Arrays.toString(ticket.getNumbers()));
    }

    /**
     * Simulates the Primitiva game.
     */
    private void playGame() {
        System.out.println("Playing game...");
        // Implementation of the game logic goes here
    }

    public static void main(String[] args) {
        PrimitivaGame game = new PrimitivaGame();
        game.start();
    }
}
