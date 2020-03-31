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
public class Game extends Thread {

    protected Board board;
    protected List<Player> players = new ArrayList<>();

    public Game() {
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
        p.setBoard(board);
    }

    @Override
    public void run() {
        for (Player p : players) {
            if (board.tokens.size() >= 1) {
                new Thread(p).start();
            } else {
                break;
            }
        }

    }
}
