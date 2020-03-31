/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author School
 */
public class Player implements Runnable{
    private String name;
    private List<Token> playerToken = new ArrayList<>();
    private Board board;

    public Player(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addToken(Token t){
        this.playerToken.add(t);
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    
    @Override
    public void run() {
        Token t = new Token();
        t = board.selectToken();
        this.addToken(t);
        System.out.println("Player-ul " + this.toString() + " a selectat: " + t.toString());
    }

    @Override
    public String toString() {
        return name;
    }    
}
