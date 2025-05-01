package com.meuapp;
import org.jpl7.*;
import java.util.Scanner;

public class UnoLogic {

    public UnoLogic() {
        Query q1 = new Query("consult('rules.pl')");
        System.out.println("Arquivo Carregado: " + (q1.hasSolution() ? "Sim" : "Não"));
    }

    public boolean canPlayCC(Color c1, Simbol s1, Color c2, Simbol s2) {
        return new Query("canPlayCC(" + c1.toString().toLowerCase() + ", " + s1.toString().toLowerCase() +
                         ", " + c2.toString().toLowerCase() + ", " + s2.toString().toLowerCase() + ")").hasSolution();
    }

    public boolean canPlayWW() {
        return new Query("canPlayWW").hasSolution();
    }

    public boolean canPlayWC(Color chosenColor, Color color2) {
        return new Query("canPlayWC(" + chosenColor.toString().toLowerCase() + ", " + color2.toString().toLowerCase() + ")").hasSolution();
    }

    public boolean canPlayCW(String simbol1) {
        return new Query("canPlayCW(" + simbol1.toLowerCase() + ")").hasSolution();
    }

    public boolean canPlay(Player user, Computer computer, Card card1, Card card2) {
        if (card1 instanceof CommonCard && card2 instanceof CommonCard) {
            return canPlayCC(card1.getColor(), card1.getSimbol(), card2.getColor(), card2.getSimbol());
        } else if (card1 instanceof WildCard && card2 instanceof WildCard) {
            return canPlayWW();
        } else if (card1 instanceof WildCard && card2 instanceof CommonCard) {
            Color chosenColor;
            if (user instanceof Computer) {
                chosenColor = ((Computer) user).chooseColor();
                System.out.println("Computer chose: " + chosenColor);
            } else {
                Scanner sc = new Scanner(System.in);
                while (true) {
                    System.out.println("RED, BLUE, GREEN, YELLOW");
                    System.out.print("Choose a color: ");
                    String input = sc.nextLine().toUpperCase();
                    try {
                        chosenColor = Color.valueOf(input);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid color. Try again.");
                    }
                }
                sc.close();
            }
            return canPlayWC(chosenColor, card2.getColor());
        } else if (card1 instanceof CommonCard && card2 instanceof WildCard) {
            return canPlayCW(card1.getSimbol().toString());
        }
        return false;
    }

    public boolean drawTwo(Simbol simbol1) {
        return simbol1 == Simbol.DRAW_TWO;
    }

    public boolean skip(Simbol simbol1) {
        return simbol1 == Simbol.SKIP;
    }

    public boolean drawFour(Simbol simbol1) {
        return simbol1 == Simbol.DRAW_FOUR;
    }

    public boolean reverse(Simbol simbol1) {
        return simbol1 == Simbol.REVERSE;
    }
}

 