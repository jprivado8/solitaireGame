package com.svi.training.game;

import com.svi.training.vo.*;
import com.svi.training.enums.Suit;
import java.util.*;

/**
 * Handles the logic of the Solitaire game.
 */
public class Game {
    private Deck deck;
    private List<Tableau> tableau;
    private List<Foundation> foundations;
    private Stack<Card> waste;
    private Stack<Card> talon;
    private static final int DRAW_COUNT = 3;

    public Game() {
        deck = new Deck();
        tableau = new ArrayList<>();
        foundations = new ArrayList<>();
        waste = new Stack<>();
        talon = new Stack<>();

        // Initialize foundations
        for (Suit suit : Suit.values()) {
            foundations.add(new Foundation(suit));
        }

        // Deal cards to tableau
        for (int i = 1; i <= 7; i++) {
            tableau.add(new Tableau(deck.deal(i)));
        }

        // Populate talon
        while (!deck.isEmpty()) {
            talon.push(deck.draw());
        }
        
		/*
		 * System.out.println("Deck initialized and shuffled.");
		 * System.out.println("Initial Tableau: " + tableau);
		 * System.out.println("Initial Talon size: " + talon.size());
		 */
    }

    private boolean isStalemate() {
        return waste.isEmpty() && talon.isEmpty() && !checkTableauMoves() && !checkFoundationMoves();
    }

    // Update the main game loop in `play()`:
    public void play() {
        System.out.println("Game setup:");
        displayGameState();

        while (!isGameOver()) {
            if (isStalemate()) {
                System.out.println("No moves left. Stalemate detected!");
                break;
            }

            if (!waste.isEmpty() && processWasteForMoves()) {
                continue;
            }

            if (checkTableauMoves()) {
                continue;
            }

            if (checkFoundationMoves()) {
                continue;
            }

            if (talon.isEmpty() && !waste.isEmpty()) {
                refillTalonFromWaste();
            } else {
                drawFromTalon();
            }

            displayGameState();
        }

        System.out.println("Game over!");
    }


    private boolean processWasteForMoves() {
        List<Card> tempWaste = new ArrayList<>(waste);
        waste.clear();
        boolean moved = false;

        for (Card card : tempWaste) {
            if (processCardForMoves(card)) {
                moved = true;
            } else {
                waste.push(card);
            }
        }
        return moved;
    }

    
    private boolean processCardForMoves(Card card) {
        for (Foundation foundation : foundations) {
            if (foundation.canAddCard(card)) {
                foundation.addCard(waste.pop());
                return true;
            }
        }

        for (Tableau tableauColumn : tableau) {
            if (tableauColumn.canAddCard(card)) {
                tableauColumn.addCard(waste.pop());
                return true;
            }
        }

        return false;
    }

    private void drawFromTalon() {
        List<Card> drawnCards = new ArrayList<>();

        if (!talon.isEmpty()) {
            for (int i = 0; i < DRAW_COUNT && !talon.isEmpty(); i++) {
                Card drawnCard = talon.pop();
                if (drawnCard != null) {
                    drawnCard.flip();
                    waste.push(drawnCard);
                    drawnCards.add(drawnCard);
                }
            }

            System.out.println("Drew " + drawnCards.size() + " card(s) from talon to waste.");
        }
    }


    private void refillTalonFromWaste() {
        int recycleCount = 0;
        Stack<Card> tempStack = new Stack<>();
        while (!waste.isEmpty()) {
            Card recycledCard = waste.pop();
            if (recycledCard != null) {
                recycledCard.flip();
                tempStack.push(recycledCard);
                recycleCount++;
            }
        }
        while (!tempStack.isEmpty()) {
            talon.push(tempStack.pop());
        }
        System.out.println("Recycled " + recycleCount + " cards back into talon.");
    }

    

    private boolean checkFoundationMoves() {
        for (Tableau column : tableau) {
            if (column.isEmpty()) continue;

            Card topCard = column.peekTopCard();
            for (Foundation foundation : foundations) {
                if (foundation.canAddCard(topCard)) {
                    foundation.addCard(column.removeTopCard());
                    column.flipTopCard();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkTableauMoves() {
        for (Tableau fromColumn : tableau) {
            if (fromColumn.isEmpty()) continue;

            List<Card> faceUpCards = fromColumn.getFaceUpSequence();
            for (Tableau toColumn : tableau) {
                if (fromColumn != toColumn && toColumn.canAddSequence(faceUpCards)) {
                    toColumn.addSequence(fromColumn.removeSequence(faceUpCards));
                    System.out.println("Moved " + faceUpCards + " from Column " + tableau.indexOf(fromColumn) + 
                                       " to Column " + tableau.indexOf(toColumn));
                    fromColumn.flipTopCard();
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isGameOver() {
        for (Foundation foundation : foundations) {
            if (!foundation.isComplete()) {
                return false;
            }
        }
        return talon.isEmpty() && waste.isEmpty();
    }

    public void displayGameState() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Foundations:");
        for (int i = 0; i < foundations.size(); i++) {
            System.out.println("Foundation " + (i + 1) + ": " + foundations.get(i));
        }

        System.out.println("\nTableau:");
        for (int i = 0; i < tableau.size(); i++) {
            System.out.println("Column " + (i + 1) + ": " + tableau.get(i));
        }

        System.out.println("\nWaste: " + (waste.isEmpty() ? "Empty" : waste));
        System.out.println("Talon: " + talon.size() + " cards remaining");
    }
}
