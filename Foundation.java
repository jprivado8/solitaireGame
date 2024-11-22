package com.svi.training.vo;


import com.svi.training.enums.Rank;
import com.svi.training.enums.Suit;


import java.util.Stack;

public class Foundation {
    private Suit suit;
    private Stack<Card> stack;

    public Foundation(Suit suit) {
        this.suit = suit;
        this.stack = new Stack<>();
    }

    public boolean canAddCard(Card card) {
        if (card.getSuit() != suit) {
            return false; // Must match the suit of the foundation
        }
        if (stack.isEmpty()) {
            return card.getRank() == Rank.ACE; // Foundation starts with an Ace
        }
        Card topCard = stack.peek();
        return card.getRank().ordinal() == topCard.getRank().ordinal() + 1; // Must be in ascending order
    }

    public void addCard(Card card) {
        if (canAddCard(card)) {
            stack.push(card);
            System.out.println("Added " + card + " to Foundation " + suit);
        } else {
            System.out.println("Cannot add " + card + " to Foundation " + suit);
        }
    }


    public boolean isComplete() {
        return stack.size() == 13; // Complete if all 13 cards of the same suit are present
    }
    
    @Override
    public String toString() {
        if (stack.isEmpty()) {
            return "Empty";
        }
        return stack.toString(); // Leverages the toString() of the Stack, which uses Card's toString()
    }

}

