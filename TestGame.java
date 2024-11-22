/*
 * package com.svi.training.test;
 * 
 * import com.svi.training.enums.*; import com.svi.training.game.Game; import
 * com.svi.training.vo.*;
 * 
 * import org.junit.Test; import static org.junit.Assert.*;
 * 
 * import java.util.*;
 * 
 * public class TestGame {
 * 
 * @Test public void testTableauSetup() { Deck deck = new
 * FixedDeck(generateOrderedDeck()); Game game = new Game(deck);
 * 
 * for (int i = 0; i < 7; i++) { Tableau tableau = game.getTableau().get(i);
 * assertEquals(i + 1, tableau.size()); // Column i should have i+1 cards
 * assertTrue(tableau.peekTopCard().isFaceUp()); // Top card should be face-up }
 * }
 * 
 * @Test public void testFoundationAdditions() { Deck deck = new
 * FixedDeck(generateOrderedDeck()); Game game = new Game(deck);
 * 
 * Foundation foundation = game.getFoundations().get(0); // DIAMOND Foundation
 * 
 * Card aceOfDiamonds = new Card(Suit.DIAMOND, Rank.ACE); Card twoOfDiamonds =
 * new Card(Suit.DIAMOND, Rank.TWO);
 * 
 * assertTrue(foundation.canAddCard(aceOfDiamonds));
 * foundation.addCard(aceOfDiamonds);
 * 
 * assertTrue(foundation.canAddCard(twoOfDiamonds));
 * foundation.addCard(twoOfDiamonds);
 * 
 * assertEquals(2, foundation.getSize()); }
 * 
 * @Test public void testTalonRecycling() { Deck deck = new
 * FixedDeck(generateOrderedDeck()); Game game = new Game(deck);
 * 
 * game.drawFromTalon(); // Simulate draw game.refillTalonFromWaste(); //
 * Recycle waste
 * 
 * assertEquals(3, game.getTalon().size()); }
 * 
 * // Helper methods private List<Card> generateOrderedDeck() { List<Card> cards
 * = new ArrayList<>(); for (Suit suit : Suit.values()) { for (Rank rank :
 * Rank.values()) { cards.add(new Card(suit, rank)); } } return cards; } }
 */
