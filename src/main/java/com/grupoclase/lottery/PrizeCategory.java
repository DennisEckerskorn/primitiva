package com.grupoclase.lottery;

/**
 * Represents a prize category in a lottery, with information such as category name,
 * the required matching numbers, and matching special numbers.
 */
public enum PrizeCategory {
    SPECIAL("Special", 6, 1),
    FIRST("1º", 6, 0),
    SECOND("2º", 5, 1),
    THIRD("3º", 5, 0),
    FOURTH("4º", 4, 0),
    FIFTH("5º", 5, 0);

    private final String categoryName;
    private final int matchingNumbers;
    private final int matchingSpecialNumbers;

    /**
     * Constructor for PrizeCategory.
     *
     * @param categoryName           The name of the prize category.
     * @param matchingNumbers        The required matching regular numbers for winning.
     * @param matchingSpecialNumbers The required matching special numbers (bonus numbers) for winning.
     */
    PrizeCategory(String categoryName, int matchingNumbers, int matchingSpecialNumbers) {
        this.categoryName = categoryName;
        this.matchingNumbers = matchingNumbers;
        this.matchingSpecialNumbers = matchingSpecialNumbers;
    }

    /**
     * Gets the name of the prize category.
     *
     * @return The category name.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Gets the required matching regular numbers for winning.
     *
     * @return The number of matching regular numbers required.
     */
    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    /**
     * Checks if the user is a winner in a specific prize category based on the drawn numbers,
     * the numbers selected by the user, and the special numbers.
     *
     * @param drawnNumbers   The numbers drawn in the lottery.
     * @param userNumbers    The numbers selected by the user on the lottery ticket.
     * @param specialNumbers The special numbers (e.g., bonus numbers) in the lottery.
     * @return {@code true} if the user is a winner in the prize category, {@code false} otherwise.
     */
    public boolean isWinner(int[] drawnNumbers, int[] userNumbers, int[] specialNumbers) {
        if (userNumbers == null) {
            return false;
        }
        int matchingNumbers = 0;
        int matchingSpecialNumbers = 0;

        for (int i = userNumbers.length-1; i >= 0 ; i--) {
            int userNumber = userNumbers[i];
            int drawnNumber = drawnNumbers[i];

            if (userNumber == drawnNumber) {
                matchingNumbers++;
            } else {
                break;
            }
        }

        if (specialNumbers != null) {
            for (int specialNumber : specialNumbers) {
                for (int userNumber : userNumbers) {
                    if (userNumber == specialNumber) {
                        matchingSpecialNumbers++;
                    }
                }
            }
        }

        return matchingNumbers >= this.matchingNumbers && matchingSpecialNumbers >= this.matchingSpecialNumbers;
    }
    public boolean isWinnerAll(int[] drawnNumbers, int[] userNumbers, int ticketReinbursment, int drumReinbursment) {
        if (userNumbers == null) {
            return false;
        }

        int matchingNumbers = 0;
        if(ticketReinbursment == drumReinbursment){
            matchingNumbers++;
            for (int i = userNumbers.length-1; i >= 0 ; i--) {
                int userNumber = userNumbers[i];
                int drawnNumber = drawnNumbers[i];

                if (userNumber == drawnNumber) {
                    matchingNumbers++;
                }else{
                    break;
                }
            }
            if (matchingNumbers == 7){
                return true;
            }
        }

        return false;
    }

    public boolean isWinnerAny(int[] drawnNumbers, int[] userNumbers, int ticketReinbursment, int drumReinbursment) {
        if (userNumbers == null) {
            return false;
        }

        int matchingNumbers = 0;
        if(ticketReinbursment == drumReinbursment){
            matchingNumbers++;
        }
        for (int i = userNumbers.length-1; i >= 0 ; i--) {
            int userNumber = userNumbers[i];
            int drawnNumber = drawnNumbers[i];

            if (userNumber == drawnNumber) {
                matchingNumbers++;
            }else{
                break;
            }
        }
        return true;
    }

    /**
     * Overrides the default toString method to provide a meaningful string representation of PrizeCategory.
     *
     * @return A string representation of the PrizeCategory object.
     */
    @Override
    public String toString() {
        return "PrizeCategory{" +
                "categoryName='" + categoryName + '\'' +
                ", matchingNumbers=" + matchingNumbers +
                ", matchingSpecialNumbers=" + matchingSpecialNumbers +
                '}';
    }
}
