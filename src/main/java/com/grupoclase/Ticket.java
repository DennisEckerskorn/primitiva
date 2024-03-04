package com.grupoclase;

import com.grupoclase.resources.JuegoPrimitiva;

import java.util.Arrays;

import java.util.Random;

    /**
     * La clase Ticket representa un boleto de La Primitiva que contiene una combinación de números.
     */
    public class Ticket {
        private int[] numbers;

        /**
         * Constructor de la clase Ticket.
         * Inicializa el arreglo de números del boleto.
         */
        public Ticket() {
            this.numbers = new int[6];
        }

        /**
         * Método para generar una combinación aleatoria de números para el boleto.
         * Los números están en el rango del 1 al 49.
         */
        public void generateNumbers() {
            Random random = new Random();
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = random.nextInt(49) + 1;
            }
        }

        /**
         * Método para obtener los números del boleto.
         *
         * @return Un arreglo que contiene la combinación de números del boleto.
         */
        public int[] getNumbers() {
            return numbers;
        }
    }
}