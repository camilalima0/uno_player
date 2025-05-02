package com.meuapp;

public class CommonCard extends Card{
    private Color color;
    private Symbol symbol;
    
    public CommonCard(String name, Color color, Symbol symbol) {
        super(name);
        this.color = color;
        this.symbol = symbol;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "CommonCard{" +
                "name='" + getName() + '\'' +
                ", color='" + color + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
