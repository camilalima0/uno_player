package com.meuapp;

public class CommonCard extends Card{
    private String color;
    private String simbol;
    
    public CommonCard(String name, Color color, Simbol simbol) {
        super(name);
        this.color = color.toString();
        this.simbol = simbol.toString();
    }

    public String getColor() {
        return color;
    }

    public String getSimbol() {
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
