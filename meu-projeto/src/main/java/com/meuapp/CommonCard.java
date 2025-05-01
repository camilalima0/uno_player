package com.meuapp;

public class CommonCard extends Card{
    private Color color;
    private Simbol simbol;
    
    public CommonCard(String name, Color color, Simbol simbol) {
        super(name);
        this.color = color;
        this.simbol = simbol;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Simbol getSimbol() {
        return simbol;
    }

    @Override
    public String toString() {
        return "CommonCard{" +
                "name='" + getName() + '\'' +
                ", color='" + color + '\'' +
                ", simbol='" + simbol + '\'' +
                '}';
    }
}
