package com.meuapp;

public class WildCard extends Card{
    private Symbol symbol;

    public WildCard(String name, Symbol symbol) {
        super(name);
        this.symbol = symbol;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "WildCard{" +
                "symbol=" + symbol +
                '}';
    }
}
