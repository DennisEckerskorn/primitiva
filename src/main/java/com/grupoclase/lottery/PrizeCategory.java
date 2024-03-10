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

    public boolean isWinner(GenericDynamicArray<Integer> drawnNumbers, GenericDynamicArray<Integer> userNumbers, GenericDynamicArray<Integer> specialNumbers) {
        int matchingNumbers = 0;
        int matchingSpecialNumbers = 0;

        for (int i = 0; i < userNumbers.size(); i++) {
            int userNumber = userNumbers.get(i);


            for (int j = 0; j < drawnNumbers.size(); j++) {
                Integer drawnNumber = drawnNumbers.get(j);
                if (drawnNumber != null && userNumber == drawnNumber.intValue()) {
                    matchingNumbers++;
                    break;
                }
            }
        }

        for (int i = 0; i < specialNumbers.size(); i++) {
            int specialNumber = specialNumbers.get(i);

            for (int j = 0; j < userNumbers.size(); j++) {
                int userNumber = userNumbers.get(j);
                if (userNumber == specialNumber) {
                    matchingSpecialNumbers++;
                    break;
                }
            }
        }

        return matchingNumbers >= this.matchingNumbers && matchingSpecialNumbers >= this.matchingSpecialNumbers;
    }
}
