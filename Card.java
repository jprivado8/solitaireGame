package com.svi.training.vo;

import com.svi.training.enums.Rank;
import com.svi.training.enums.Suit;

public class Card {
    private Suit suit;
    private Rank rank;
    private boolean faceUp;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = false; // Initially, the card is face down
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public String getColor() {
        if (suit == Suit.HEART || suit == Suit.DIAMOND) {
            return "RED";
        } else {
            return "BLACK";
        }
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flip() {
        faceUp = !faceUp;
    }

    public boolean isAlternateColor(Card other) {
        if (this.suit.equals(Suit.HEART) || this.suit.equals(Suit.DIAMOND)) {
            return !(other.suit.equals(Suit.HEART) || other.suit.equals(Suit.DIAMOND));
        } else {
            return !(other.suit.equals(Suit.SPADE) || other.suit.equals(Suit.CLUB));
        }
    }

    public boolean isOneRankHigherThan(Card other) {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int thisRankIndex = java.util.Arrays.asList(ranks).indexOf(this.rank.getSymbol());
        int otherRankIndex = java.util.Arrays.asList(ranks).indexOf(other.rank.getSymbol());
        return thisRankIndex == otherRankIndex + 1;
    }
    
    @Override
    public String toString() {
        if (!faceUp) {
            return "[x]";
        }
        return rank.getSymbol() + "-" + suit.getSymbol();
    }
    
}
