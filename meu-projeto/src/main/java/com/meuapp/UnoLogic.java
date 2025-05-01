package com.meuapp;

import java.util.Scanner;
import org.jpl7.Query;

public class UnoLogic {

    public UnoLogic() {
        Query q1 = new Query("consult('rules.pl')");
        System.out.println("Prolog file loaded: " + (q1.hasSolution() ? "Yes" : "No"));
    }

    public boolean canPlay(Card card1, Card card2, Player player) {
        String topColor = card1.getColor() != null ? card1.getColor().toString().toLowerCase() : "none";
        String topSymbol = card1.getSymbol().toString().toLowerCase();
        String playedColor = card2.getColor() != null ? card2.getColor().toString().toLowerCase() : "none";
        String playedSymbol = card2.getSymbol().toString().toLowerCase();

        String query;

        if (isCommon(card2) && isCommon(card1)) {
            query = String.format("canPlayCC(%s, %s, %s, %s)",
                    playedColor, playedSymbol, topColor, topSymbol);
        } else if (isWild(card2) && isCommon(card1)) {
            String chosenColor;

            if (player instanceof Computer) {
                Color random = Color.values()[(int) (Math.random() * 4)]; // red, green, blue, yellow
                chosenColor = random.toString().toLowerCase();
                System.out.println("Computer chose color: " + chosenColor);
            } else {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("Choose a color (red, green, blue, yellow): ");
                    String input = scanner.nextLine().trim().toLowerCase();
                    if (input.equals("red") || input.equals("green") || input.equals("blue") || input.equals("yellow")) {
                        chosenColor = input;
                        break;
                    } else {
                        System.out.println("Invalid color. Try again.");
                    }
                    scanner.close();
                }
            }

            query = String.format("canPlayWC(%s, %s)", chosenColor, topColor);
        } else if (isCommon(card2) && isWild(card1)) {
            query = String.format("canPlayCW(%s)", playedSymbol);
        } else if (isWild(card2) && isWild(card1)) {
            query = "canPlayWW";
        } else {
            return false;
        }

        return new Query(query).hasSolution();
    }

    public boolean drawTwo(Symbol symbol) {
        return new Query("draw_two(" + symbol.toString().toLowerCase() + ")").hasSolution();
    }

    public boolean drawFour(Symbol symbol) {
        return new Query("draw_four(" + symbol.toString().toLowerCase() + ")").hasSolution();
    }

    public boolean skip(Symbol symbol) {
        return new Query("skip(" + symbol.toString().toLowerCase() + ")").hasSolution();
    }

    private boolean isWild(Card card) {
        Symbol sym = card.getSymbol();
        return sym == Symbol.WILD || sym == Symbol.DRAW_FOUR;
    }

    private boolean isCommon(Card card) {
        return !isWild(card);
    }
}




 