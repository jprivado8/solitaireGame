package com.svi.training.vo;


import com.svi.training.enums.Rank;
import com.svi.training.enums.Suit;
import java.util.*;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        // Initialize the deck with 52 cards
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.push(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Card> deal(int numberOfCards) {
        List<Card> dealtCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            if (!cards.isEmpty()) {
                dealtCards.add(cards.pop());
            }
        }
        return dealtCards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            System.out.println("Deck is empty. Cannot draw a card.");
            return null;
        }
        return cards.pop();
    }

    
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    
}
