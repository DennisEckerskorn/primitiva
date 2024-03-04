package com.grupoclase;

import com.grupoclase.resources.JuegoPrimitiva;

import java.util.Arrays;

public class Ticket {
    private int[] numeros;

    /**
     * Constructor que crea un boleto con una combinación de números especificada por el jugador.
     *
     * @param numeros que son los primeros 6 Un array de enteros que representa la combinación de números del boleto.
     */
    public Ticket(int[] numeros) {
        this.numeros = Arrays.copyOf(numeros, numeros.length);
    }

    /**
     * Constructor que crea un boleto vacío, listo para generar una combinación de números aleatorios.
     */
    public Ticket() {// vacio para aleatorio
        this.numeros = new int[6];
    }


    /**
     * Obtiene la combinación de números del boleto.
     *
     * @return Un array de enteros que representa la combinación de números del boleto.
     */
    public int[] getNumeros() {
        return Arrays.copyOf(numeros, numeros.length);
    }

    /**
     * Establece la combinación de números del boleto.
     *
     * @param numeros Un array de enteros que representa la nueva combinación de números del boleto.
     */
    public void setNumeros(int[] numeros) {
        this.numeros = Arrays.copyOf(numeros, numeros.length);
    }

    /**
     * Genera una combinación aleatoria de números para el boleto.
     * Los números están en el rango del 1 al 49.
     */
    public int[] generarNumerosAleatorios(int min, int max, int tamanyio) {
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (int) (Math.random() * 49) + 1;
        }
        return numeros;
    }

    public void generateNumbers() {
    }

    public long[] getNumbers() {
        return new long[0];
    }
}
