package com.meuapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player user;
    private Player computer;
    private List<Card> discardPile;

    //starts a new game
    public Game(String name) {
        deck = new Deck();
        user = new User(name, deck.drawCards(7));
        computer = new Computer("Computer", deck.drawCards(7));
        discardPile = new ArrayList<>();
        discardPile.add(deck.drawCard()); //first card of the game
    }

    //monitors the game statements
    public void showState() {
        System.out.println("User hand:" + user.getHand());
        System.out.println("Computer hand:" + computer.getHand());
        System.out.println("Top discard: " + discardPile.get(discardPile.size() - 1));
        System.out.println("Remaining in deck: " + deck.size());
    }

    public void startGame(String name) {

        boolean userTurn = true;
        while (!user.getHand().isEmpty() && !computer.getHand().isEmpty()) {
            showState();
            if (userTurn) {
                System.out.println(name + "'s turn");
                userTurn();
            } else {
                System.out.println("Computer's turn");
                computerTurn();            
            }
            userTurn = !userTurn;
        }

    if (user.getHand().isEmpty()) {
            System.out.println("User wins!");
        } else {
            System.out.println("Computer wins!");
        }
    }

    public void userTurn() {
        int i = 1; 
        //shows options to the user
        for (Card card : user.getHand()) {
            System.out.println(i + " - " + card);
            i++;
        }

        int validChoice = 1;
        while (validChoice == 1) {
            System.out.println("Choose a card to play: ");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            //checks if the choice is valid
            if (choice > 0 && choice <= user.getHand().size()) {
                validChoice = 0;
                Card chosenCard = user.getHand().get(choice);
                System.out.println("You played: " + chosenCard);
            } else {
                System.out.println("Invalid choice. Try again.");
            }
            sc.close();
        }
    }

    public void computerTurn() {
        // Implement computer turn logic here
        // For example, you can randomly choose a card from the computer's hand
        int randomIndex = (int) (Math.random() * computer.getHand().size());
        Card chosenCard = computer.getHand().get(randomIndex);
        System.out.println("Computer played: " + chosenCard);
    }
}
