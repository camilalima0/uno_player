package com.meuapp;
import java.util.List;

public class Computer extends Player{

    public Computer(String name, List<Card> hand) {
        super(name, hand);
    }

    public Color chooseColor() {
        // Randomly choose a color
        Color[] colors = Color.values();
        int randomIndex = (int) (Math.random() * colors.length);
        return colors[randomIndex];
    }
}
