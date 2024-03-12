package com.grupoclase.lottery;

import com.grupoclase.dynamicarray.GenericDynamicArray;

public enum PrizeCategory {
    ESPECIAL("Especial", 6, 1),
    PRIMERA("1º", 6, 0),
    SEGUNDA("2º", 5, 1),
    TERCERA("3º", 5, 0),
    CUARTA("4º", 4, 0),
    QUINTA("5º", 5, 0);

    private final String categoryName;
    private final int matchingNumbers;
    private final int matchingSpecialNumbers;

    PrizeCategory(String categoryName, int matchingNumbers, int matchingSpecialNumbers) {
        this.categoryName = categoryName;
        this.matchingNumbers = matchingNumbers;
        this.matchingSpecialNumbers = matchingSpecialNumbers;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    /**
     * Verifica si el usuario es un ganador en una categoría de premios específica basada en los números sorteados,
     * los números seleccionados por el usuario y los números especiales.
     *
     * @param drawnNumbers Los números sorteados en la lotería.
     * @param userNumbers Los números seleccionados por el usuario en el boleto de lotería.
     * @param specialNumbers Los números especiales (por ejemplo, reintegro) en la lotería.
     * @return {@code true} si el usuario es un ganador en la categoría de premios, {@code false} en caso contrario.
     */
    public boolean isWinner(int[] drawnNumbers, int[] userNumbers, int[] specialNumbers) {
        int matchingNumbers = 0;
        int matchingSpecialNumbers = 0;

        for (int i = 0; i < userNumbers.length; i++) {
            int userNumber = userNumbers[i];


            for (int j = 0; j < drawnNumbers.length; j++) {
                Integer drawnNumber = drawnNumbers[j];
                if (drawnNumber != null && userNumber == drawnNumber.intValue()) {
                    matchingNumbers++;
                    break;
                }
            }
        }

        for (int i = 0; i < specialNumbers.length; i++) {
            int specialNumber = specialNumbers[i];

            for (int j = 0; j < userNumbers.length; j++) {
                int userNumber = userNumbers[j];
                if (userNumber == specialNumber) {
                    matchingSpecialNumbers++;
                    break;
                }
            }
        }

        return matchingNumbers >= this.matchingNumbers && matchingSpecialNumbers >= this.matchingSpecialNumbers;
    }
}
