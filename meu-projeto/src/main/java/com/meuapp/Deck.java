package com.meuapp;
import java.util.*;

public class Deck {
    //create a deck of cards
    private List<Card> cards = new ArrayList<>();

    //generates deck and shuffles it
    public Deck() {
        generateDeck();
        Collections.shuffle(cards);
    }


    // creating all the cards
    private void generateDeck() {
        for(Color color : Color.values()){
            cards.add(new CommonCard(color + "0", color, Simbol._0));

            for(int i = 1; i <= 9; i++){
                cards.add(new CommonCard(color + "" + i + "a", color, Simbol.valueOf("_" + i)));
                cards.add(new CommonCard(color + "" + i + "b", color, Simbol.valueOf("_" + i)));
            }

            cards.add(new CommonCard(color + "A", color, Simbol.SKIP));
            cards.add(new CommonCard(color + "B", color, Simbol.SKIP));
            cards.add(new CommonCard(color + "A", color, Simbol.REVERSE));
            cards.add(new CommonCard(color + "B", color, Simbol.REVERSE));
            cards.add(new CommonCard(color + "A", color, Simbol.DRAW_TWO));
            cards.add(new CommonCard(color + "B", color, Simbol.DRAW_TWO));
        }

        for(int i = 0; i < 4; i++){
            cards.add(new WildCard("Wild" + i, Simbol.WILD));
            cards.add(new WildCard("WildDrawFour" + i, Simbol.DRAW_FOUR));
        }
        System.out.println ("cartas no baralho: " + cards.size());
    }

    //returns and remove the first card of the deck
    public Card drawCard() {
        return cards.remove(0);
    }

    //draws a number of cards from the deck
    public List<Card> drawCards (int count) {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            hand.add(drawCard());
        }
        return hand;
    }

    //returns the number of cards in the deck
    public int size() {
        return cards.size();
    }
    

}
