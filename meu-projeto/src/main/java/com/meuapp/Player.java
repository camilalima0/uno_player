package com.meuapp;
import java.util.List;

public abstract class Player {
    private String name;
    private List<Card> hand;

    public Player(String name, List<Card> hand){
        this.name = name;
        this.hand = hand;
    }

    public String getName(){
        return name;
    }

    public List<Card> getHand(){
        return hand;
    }

    public void setHand(List<Card> hand){
        this.hand = hand;
    }
}
