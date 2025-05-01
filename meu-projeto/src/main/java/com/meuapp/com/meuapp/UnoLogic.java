package com.meuapp;
import org.jpl7.*;
import java.util.Scanner;
import Color;


public class UnoLogic {

    public UnoLogic() {
        //loads the prolog file
        Query q1 = new Query("consult('rules.pl')");
        System.out.println("Arquivo Carregado: " + (q1.hasSolution() ? "Sim" : "Não"));
    }

    public boolean canPlay(Player user, Card card1, Card card2) {
        if (card1 instanceof CommonCard && card2 instanceof CommonCard) {
            return canPlayCC(card1.color, card2.color);
        }
        else if (card1 instanceof WildCard && card2 instanceof WildCard) {
            return canPlayWW();
        }
        else if (card1 instanceof WildCard && card2 instanceof CommonCard) {
            if (user instanceof User) {
                Color chosenColor = computer.chooseColor();
                System.out.println("Computer chose: " + chosenColor);
                return canPlayWC(chosenColor, card2.color);
            }
            else {
                Scanner sc = new Scanner(System.in);
                int canPlay = 1;
                while (canPlay == 1) {
                    System.out.println("RED, BLUE, GREEN, YELLOW");
                    System.out.println("Choose a color: ");
                    Color chosenColor = sc.nextLine();

                    for (Color color : Color.values()) {
                        if (color.toString().equals(chosenColor)) {
                            canPlay = 0;
                            return canPlayWC(chosenColor, card2.Color);
                        }
                        else {
                            System.out.println("Invalid color. Try again.");
                        }
                
                }
            }   
            
        }
    }
        else if (card1 instanceof CommonCard && card2 instanceof WildCard) {
            canPlayCW(card1.simbol);
        }
        return false; // Add a default return statement to ensure all paths return a value
    }

    //give card1 simbol
    public boolean drawTwo(CommonCard.Simbol simbol) {
        return simbol == CommonCard.Simbol.DRAW_TWO;
    }

    public boolean skip(CommonCard.Simbol simbol) {
        return simbol == CommonCard.Simbol.SKIP;
    }

    public boolean drawFour(WildCard.Simbol simbol) {
        return simbol == WildCard.Simbol.DRAW_FOUR;
    }
    public boolean reverse(CommonCard.Simbol simbol) {
        return simbol == CommonCard.Simbol.REVERSE;
    }

}
