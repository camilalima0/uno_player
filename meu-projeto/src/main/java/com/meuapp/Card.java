package com.meuapp;
public abstract class Card {
    private String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                '}';
    }
}
