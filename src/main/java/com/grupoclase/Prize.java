package com.grupoclase;

public class Prize {

        private int amount;
        private String category;

        public Prize(int amount, String category) {
            this.amount = amount;
            this.category = category;
        }

    public Prize() {

    }

    public static Prize checkPrize(Ticket ticket, int[] winningNumbers) {
        return null;
    }

    public static void checkPrize() {
    }

    public int getAmount() {
            return amount;
        }

        public String getCategory() {
            return category;
        }
    }
