/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;

import java.util.Random;

/**
 *
 * @author School
 */
public class Lab7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game g = new Game();
        Board b = new Board();
        for(int i=1; i<=10; i++)
        {
            Random rand = new Random();
            int val=rand.nextInt(20);
            Token t = new Token(val);
            b.addToken(t);
        }
        g.setBoard(b);
        Player p1=new Player("P1");
        Player p2=new Player("P2");
        g.addPlayer(p1);
        g.addPlayer(p2);
        g.start();
    }    
}
