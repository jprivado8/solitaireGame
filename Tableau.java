package com.svi.training.vo;

import java.util.*;
import com.svi.training.enums.Rank;

public class Tableau {
    private Stack<Card> column;

    public Tableau(List<Card> initialCards) {
        column = new Stack<>();
        column.addAll(initialCards);
        flipTopCard();
    }
    public List<Card> getFaceUpSequence() {
        List<Card> sequence = new ArrayList<>();
        for (int i = column.size() - 1; i >= 0; i--) {
            if (!column.get(i).isFaceUp()) break;
            sequence.add(0, column.get(i));
        }
        return sequence;
    }

    public boolean canAddSequence(List<Card> cards) {
        if (cards.isEmpty()) return false;
        Card bottomCard = cards.get(0);

        if (column.isEmpty()) {
            return bottomCard.getRank() == Rank.KING;
        }

        Card topCard = column.peek();
        return bottomCard.getColor() != topCard.getColor() &&
               bottomCard.getRank().ordinal() == topCard.getRank().ordinal() - 1;
    }

    public void addSequence(List<Card> cards) {
        column.addAll(cards);
    }

    public List<Card> removeSequence(List<Card> cards) {
        if (column.containsAll(cards)) {
            column.removeAll(cards);
        }
        return cards;
    }

    
    public boolean canAddCard(Card card) {
        if (column.isEmpty()) {
            return card.getRank() == Rank.KING; // Only Kings can go to empty columns
        }
        Card topCard = column.peek();
        return card.getColor() != topCard.getColor() && card.getRank().ordinal() == topCard.getRank().ordinal() - 1;
    }

    public void addCard(Card card) {
        column.push(card);
    }

    public Card removeTopCard() {
        return column.isEmpty() ? null : column.pop();
    }

    public Card peekTopCard() {
        return column.isEmpty() ? null : column.peek();
    }

    public boolean isEmpty() {
        return column.isEmpty();
    }

    // Flip the top card if it is face down
    public void flipTopCard() {
        if (!column.isEmpty() && column.peek() != null && !column.peek().isFaceUp()) {
            column.peek().flip();
        }
    }

    
    @Override
    public String toString() {
        return column.toString(); // Displays the stack of cards in the tableau
    }
}
