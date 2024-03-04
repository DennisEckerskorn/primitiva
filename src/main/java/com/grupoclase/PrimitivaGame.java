package com.grupoclase;

import java.util.Arrays;
import java.util.Scanner;

import static com.grupoclase.Prize.checkPrize;

/**
 * La clase PrimitivaGame representa el juego de La Primitiva.
 * Permite al usuario generar combinaciones de números y comprobar premios.
 */
public class PrimitivaGame {
    private Scanner scanner;
    private Ticket ticket;

    /**
     * Constructor de la clase PrimitivaGame.
     * Inicializa el scanner y crea un nuevo Ticket.
     */
    public PrimitivaGame() {
        this.scanner = new Scanner(System.in);
        this.ticket = new Ticket();
    }

    /**
     * Método principal para iniciar el juego.
     * Muestra el menú y realiza las operaciones correspondientes según la opción seleccionada.
     */
    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("JUEGO DE LA PRIMITIVA");
            System.out.println("---------------------");
            System.out.println("1. Generar nueva combinación de números");
            System.out.println("2. Comprobar premios");
            System.out.println("0. Salir");
            System.out.println("---------------------");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (option) {
                case 1:
                    generateTicket(); // Generar una nueva combinación de números para el boleto
                    break;
                case 2:
                   checkPrize(); // Comprobar los premios del boleto
                    break;
                case 0:
                    exit = true; // Salir del juego
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
        scanner.close(); // Cerrar el scanner al salir del juego
    }


    /**
     * Comprueba si el boleto del jugador ha ganado algún premio.
     */
    private void checkPrizes() {
        System.out.println("Ingrese los números ganadores del sorteo:");
        int[] winningNumbers = new int[6];
        for (int i = 0; i < winningNumbers.length; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            winningNumbers[i] = scanner.nextInt();
        }
        Prize prize = checkPrize(ticket, winningNumbers);
        System.out.println("Resultado de la comprobación de premios:");
        if (prize != null) {
            System.out.println("¡Felicidades! Ha ganado un premio de " + prize.getAmount() + "€ en la categoría " + prize.getCategory());
        } else {
            System.out.println("Lo siento, no ha ganado ningún premio en esta ocasión.");
        }
    }

    /**
     * Método para jugar una única vez.
     * Este método permite al usuario jugar una sola vez.
     */
    public void playSingleGame() {
        System.out.println("¡Bienvenido al juego de La Primitiva! ¡Buena suerte!");
        generateTicket();
        checkPrizes();
    }

    /**
     * Método para jugar hasta ganar.
     * Este método permite al usuario jugar repetidamente hasta que gane un premio.
     */
    public void playUntilWin() {
        boolean win = false;
        while (!win) {
            generateTicket();
            win = checkWin();
        }
        System.out.println("¡Felicidades! ¡Ha ganado un premio!");
    }

    /**
     * Método para jugar hasta ganar sin premio especial.
     * Este método permite al usuario jugar repetidamente hasta que gane un premio sin un premio especial.
     */
    public void playUntilWinWithoutSpecial() {
        boolean win = false;
        while (!win) {
            generateTicket();
            win = checkWinWithoutSpecial();
        }
        System.out.println("¡Felicidades! ¡Ha ganado un premio sin premio especial!");
    }

    /**
     * Método para jugar múltiples veces.
     * Este método permite al usuario jugar una cantidad específica de veces.
     */
    public void playMultipleGames() {
        int numGames = 0;
        for (int i = 0; i < numGames; i++) {
            System.out.println("Juego " + (i + 1) + ":");
            playSingleGame();
        }
    }

    /**
     * Método para jugar hasta ganar un premio especial.
     * Este método permite al usuario jugar repetidamente hasta que gane un premio especial.
     */
    public void playUntilSpecialWin() {
        boolean win = false;
        while (!win) {
            generateTicket();
            win = checkSpecialWin();
        }
        System.out.println("¡Felicidades! ¡Ha ganado un premio especial!");
    }

    /**
     * Método privado para verificar si el boleto del jugador ha ganado un premio.
     * @return true si el boleto ha ganado un premio, false de lo contrario.
     */
    private boolean checkWin() {
        // Lógica para verificar si el boleto ha ganado un premio
        return false; // Cambiar esto según la implementación real
    }

    /**
     * Método privado para verificar si el boleto del jugador ha ganado un premio sin premio especial.
     * @return true si el boleto ha ganado un premio sin premio especial, false de lo contrario.
     */
    private boolean checkWinWithoutSpecial() {
        // Lógica para verificar si el boleto ha ganado un premio sin premio especial
        return false; // Cambiar esto según la implementación real
    }

    /**
     * Método privado para verificar si el boleto del jugador ha ganado un premio especial.
     * @return true si el boleto ha ganado un premio especial, false de lo contrario.
     */
    private boolean checkSpecialWin() {
        // Lógica para verificar si el boleto ha ganado un premio especial
        return false; // Cambiar esto según la implementación real
    }



    private void generateTicket() {
        ticket.generateNumbers();
        System.out.println("¡Nueva combinación generada con éxito!");
        System.out.println("Números del boleto: " + Arrays.toString(ticket.getNumbers()));
    }


}