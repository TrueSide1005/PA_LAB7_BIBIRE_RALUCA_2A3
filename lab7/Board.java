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
public class Board {
    protected List<Token> tokens = new ArrayList<>();
    
    public void addToken (Token t){
        this.tokens.add(t);
    }
    
    public synchronized Token selectToken(){
        Random rand = new Random(); 
        Token t = new Token();
        int n=this.tokens.size();
        int index = rand.nextInt(n+1);
        t=tokens.get(index);
        tokens.remove(index);
        return t;
    }
    
    public Board() {
    }
    
}
