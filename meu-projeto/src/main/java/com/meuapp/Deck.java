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
            cards.add(new CommonCard(Color + "0", color, Simbol.S0));

            for(int i = 1; i <= 9; i++){
                cards.add(new CommonCard(Color + i + "a", color, Simbol.valueOf("S" + i)));
                cards.add(new CommonCard(Color + i + "b", color, Simbol.valueOf("S" + i)));
            }

            cards.add(new CommonCard(Color + "A", color, Simbol.SKIP));
            cards.add(new CommonCard(Color + "B", color, Simbol.SKIP));
            cards.add(new CommonCard(Color + "A", color, Simbol.REVERSE));
            cards.add(new CommonCard(Color + "B", color, Simbol.REVERSE));
            cards.add(new CommonCard(Color + "A", color, Simbol.DRAW_TWO));
            cards.add(new CommonCard(Color + "B", color, Simbol.DRAW_TWO));
        }

        for(int i = 0; i < 4; i++){
            cards.add(new WildCard("Wild" + i, Simbol.WILD));
            cards.add(new WildCard("WildDrawFour" + i, Simbol.DRAW_FOUR));
        }
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
            cards.remove(drawCard());
        }
        return hand;
    }

    //returns the number of cards in the deck
    public int size() {
        return cards.size();
    }
    

}
