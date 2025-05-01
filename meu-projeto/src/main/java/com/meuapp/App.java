package com.meuapp;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the game!");
        System.out.println("Please enter your name:");
        String name = sc.nextLine();
        System.out.println("Hello " + name + "! Let's start the game.");

        Game game = new Game(name);
        game.startGame(name);
        sc.close();
    }
} 
