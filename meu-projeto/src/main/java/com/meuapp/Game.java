package com.meuapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player user;
    private Computer computer;
    private List<Card> discardPile;
    private UnoLogic logic;

    public Game(String name) {
        deck = new Deck();
        user = new User(name, deck.drawCards(7));
        computer = new Computer("Computer", deck.drawCards(7));
        discardPile = new ArrayList<>();
        discardPile.add(deck.drawCard()); // first card of the game
        logic = new UnoLogic();
    }

    public void showState() {
        System.out.println("User hand: " + user.getHand());
        System.out.println("Computer hand: " + computer.getHand());
        System.out.println("Top discard: " + discardPile.get(discardPile.size() - 1));
        System.out.println("Remaining in deck: " + deck.size() + "\n");
    }

    public void startGame(String name) {
        boolean userTurn = true;

        while (!user.getHand().isEmpty() && !computer.getHand().isEmpty()) {
            showState();
            boolean skip = false;

            if (userTurn) {
                System.out.println(name + "'s turn");
                skip = userTurn();
            } else {
                System.out.println("Computer's turn");
                skip = computerTurn();
            }

            // Só troca a vez se não houve skip
            if (!skip) {
                userTurn = !userTurn;
            }
        }

        if (user.getHand().isEmpty()) {
            System.out.println("User wins!");
        } else {
            System.out.println("Computer wins!");
        }
    }

    public boolean userTurn() {
        List<Card> hand = user.getHand();
        Card topCard = discardPile.get(discardPile.size() - 1);

        boolean hasPlayable = false;
        for (Card card : hand) {
            if (logic.canPlay(topCard, card, user)) {
                hasPlayable = true;
                break;
            }
        }

        if (!hasPlayable) {
            Card drawn = deck.drawCard();
            System.out.println("No playable card. You drew: " + drawn);
            hand.add(drawn);
            return false;
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i + 1) + " - " + hand.get(i));
            }
            System.out.print("Choose a card to play: ");
            int choice = sc.nextInt();

            if (choice > 0 && choice <= hand.size()) {
                Card selected = hand.get(choice - 1);
                if (logic.canPlay(topCard, selected, user)) {
                    System.out.println("You played: " + selected);
                    hand.remove(selected);
                    discardPile.add(selected);
                    return applyCardEffect(selected, computer);
                } else {
                    System.out.println("Invalid card. Try again.");
                }
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public boolean computerTurn() {
        List<Card> hand = computer.getHand();
        Card topCard = discardPile.get(discardPile.size() - 1);

        for (Card card : hand) {
            if (logic.canPlay(topCard, card, computer)) {
                System.out.println("Computer played: " + card);
                hand.remove(card);
                discardPile.add(card);
                return applyCardEffect(card, user);
            }
        }

        Card drawn = deck.drawCard();
        System.out.println("Computer draws a card: " + drawn);
        hand.add(drawn);

        if (logic.canPlay(topCard, drawn, computer)) {
            System.out.println("Computer played drawn card: " + drawn);
            hand.remove(drawn);
            discardPile.add(drawn);
            return applyCardEffect(drawn, user);
        }

        System.out.println("Computer cannot play.");
        return false;
    }

    private boolean applyCardEffect(Card card, Player opponent) {
        Symbol sym = card.getSymbol();

        if (logic.drawTwo(sym)) {
            System.out.println(opponent.getName() + " draws 2 cards and loses turn.");
            opponent.getHand().addAll(deck.drawCards(2));
            return true;
        }

        if (logic.drawFour(sym)) {
            System.out.println(opponent.getName() + " draws 4 cards and loses turn.");
            opponent.getHand().addAll(deck.drawCards(4));
            return true;
        }

        if (logic.skip(sym)) {
            System.out.println(opponent.getName() + " loses turn.");
            return true;
        }

        return false;
    }
}




