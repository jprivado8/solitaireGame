package com.svi.training.enums;

public enum Suit {
    DIAMOND("D"), HEART("H"), SPADE("S"), CLUB("C");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
