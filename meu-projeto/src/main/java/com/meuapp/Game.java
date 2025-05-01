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

    // Starts a new game
    public Game(String name) {
        deck = new Deck();
        user = new User(name, deck.drawCards(7));
        computer = new Computer("Computer", deck.drawCards(7));
        discardPile = new ArrayList<>();
        discardPile.add(deck.drawCard()); // first card of the game
        logic = new UnoLogic();
    }

    // Monitors the game statements
    public void showState() {
        System.out.println("User hand: " + user.getHand() + "\n");
        System.out.println("Computer hand: " + computer.getHand() + "\n");
        System.out.println("Top discard: " + discardPile.get(discardPile.size() - 1) + "\n");
        System.out.println("Remaining in deck: " + deck.size() + "\n");
    }

    public void startGame(String name) {
        boolean userTurn = true;

        while (!user.getHand().isEmpty() && !computer.getHand().isEmpty()) {
            showState();
            if (userTurn) {
                System.out.println(name + "'s turn");
                boolean skip = userTurn();
                if (skip) {
                    userTurn = !userTurn; // mantém para pular a vez do adversário
                }
            } else {
                System.out.println("Computer's turn");
                boolean skip = computerTurn();
                if (skip) {
                    userTurn = !userTurn; // mantém para pular a vez do adversário
                }
            }
            userTurn = !userTurn;
        }

        if (user.getHand().isEmpty()) {
            System.out.println("User wins!");
        } else {
            System.out.println("Computer wins!");
        }
    }

    public boolean userTurn() {
        List<Card> hand = user.getHand();
        Card card1 = discardPile.get(discardPile.size() - 1);

        boolean hasPlayable = false;
        for (Card card2 : hand) {
            if (logic.canPlay(user, computer, card1, card2)) {
                hasPlayable = true;
                break;
            }
        }

        if (!hasPlayable) {
            Card drawnCard = deck.drawCard();
            System.out.println("No playable card. You draw: " + drawnCard);
            hand.add(drawnCard);
            return false;
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Your hand:");
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i + 1) + " - " + hand.get(i));
            }

            System.out.print("Choose a card to play: ");
            int choice = sc.nextInt();

            if (choice > 0 && choice <= hand.size()) {
                Card card2 = hand.get(choice - 1);
                if (logic.canPlay(user, computer, card1, card2)) {
                    System.out.println("You played: " + card2);
                    hand.remove(card2);
                    discardPile.add(card2);
                    return applyCardEffect(card2, computer); // aplica efeito no adversário
                } else {
                    System.out.println("You cannot play that card. Try again.");
                }
            } else {
                System.out.println("Invalid choice. Try again.");
            }
            sc.close();
        } 
    }

    public boolean computerTurn() {
        List<Card> hand = computer.getHand();
        Card card1 = discardPile.get(discardPile.size() - 1);

        for (Card card2 : hand) {
            if (logic.canPlay(user, computer, card1, card2)) {
                System.out.println("Computer played: " + card2);
                hand.remove(card2);
                discardPile.add(card2);
                return applyCardEffect(card2, user); // aplica efeito no adversário
            }
        }

        Card drawnCard = deck.drawCard();
        System.out.println("Computer draws a card.");
        hand.add(drawnCard);

        if (logic.canPlay(user, computer, card1, drawnCard)) {
            System.out.println("Computer played drawn card: " + drawnCard);
            hand.remove(drawnCard);
            discardPile.add(drawnCard);
            return applyCardEffect(drawnCard, user);
        } else {
            System.out.println("Computer could not play any card.");
            return false;
        }
    }

    //DRAW_TWO, DRAW_FOUR, SKIP
    private boolean applyCardEffect(Card card, Player opponent) {
        Simbol simbol = card.getSimbol();

        if (logic.drawTwo(simbol)) {
            System.out.println(opponent.getName() + " draws 2 cards and skips turn!");
            opponent.getHand().addAll(deck.drawCards(2));
            return true; // pular próxima jogada
        } else if (logic.drawFour(simbol)) {
            System.out.println(opponent.getName() + " draws 4 cards and skips turn!");
            opponent.getHand().addAll(deck.drawCards(4));
            return true;
        } else if (logic.skip(simbol)) {
            System.out.println(opponent.getName() + " loses their turn!");
            return true;
        }

        return false; 
    }
}


