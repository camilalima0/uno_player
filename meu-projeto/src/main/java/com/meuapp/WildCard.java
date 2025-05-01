package com.meuapp;

public class WildCard extends Card{
    private Simbol simbol;

    public WildCard(String name, Simbol simbol) {
        super(name);
        this.simbol = simbol;
    }

    @Override
    public Simbol getSimbol() {
        return simbol;
    }

    @Override
    public String toString() {
        return "WildCard{" +
                "simbol=" + simbol +
                '}';
    }
}
