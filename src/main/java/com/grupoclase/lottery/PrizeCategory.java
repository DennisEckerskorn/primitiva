package com.grupoclase.lottery;

import com.grupoclase.dynamicarray.GenericDynamicArray;

public enum PrizeCategory {
    ESPECIAL("Especial", 6),
    PRIMERA("1º", 6),
    SEGUNDA("2º", 5),
    TERCERA("3º", 5),
    CUARTA("4º", 4),
    QUINTA("5º", 5);

    private final String categoryName;
    private final int matchingNumbers;

    PrizeCategory(String categoryName, int matchingNumbers) {
        this.categoryName = categoryName;
        this.matchingNumbers = matchingNumbers;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    /**
     * Método que verifica si el usuario ha ganado en una categoría específica.
     * Compara los números del usuario con los números sorteados y los números especiales (reembolso).
     *
     * @param drawnNumbers    Números sorteados
     * @param userNumbers     Números del usuario
     * @param specialNumbers  Números especiales (reembolso)
     * @return true si el usuario ha ganado, false de lo contrario.
     */
    public boolean isWinner(GenericDynamicArray<Integer> drawnNumbers, GenericDynamicArray<Integer> userNumbers, GenericDynamicArray<Integer> specialNumbers) {
        int matchingNumbers = 0;

        for (int i = 0; i < userNumbers.size(); i++) {
            int userNumber = userNumbers.get(i);

            boolean numberExists = false;
            for (int j = 0; j < drawnNumbers.size(); j++) {
                Integer drawnNumber = drawnNumbers.get(j);
                if (drawnNumber != null && userNumber == drawnNumber.intValue()) {
                    numberExists = true;
                    break;
                }
            }
            if (numberExists) {
                matchingNumbers++;
            }
        }
        return matchingNumbers >= this.matchingNumbers;
    }
}
