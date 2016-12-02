package aoc01;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Problem {
    int currentDirection = 0;
    Coord pos = new Coord(0,0);
    
    LinkedList<Coord> history = new LinkedList<Coord>();
    boolean reachedHistory = false;
    
    private static class Coord {
        int x, y;
        Coord(int x, int y) {
            this.x = x; this.y = y;
        }
        
        @Override
        public boolean equals(Object obj) {
            Coord c = (Coord) obj;
            return (c.x == this.x && c.y == this.y);
        }
    }
    
    // North, East, South, West
    final int[] xMove = {0, 1, 0, -1};
    final int[] yMove = {-1, 0, 1, 0};
    
    void applyInstruction(char rot, int magnitude) {
        if(rot == 'R')
            currentDirection = (currentDirection + 1) % 4;
        else
            currentDirection = (currentDirection + 4 - 1) % 4;
        
        for(int i = 0; i < magnitude; i++) {
            history.add(new Coord(pos.x, pos.y));
            
            pos.x += xMove[currentDirection];
            pos.y += yMove[currentDirection];
            
            if(!reachedHistory) {
                if(history.contains(pos)) {
                    reachedHistory = true;
                    System.out.println("You reached a position twice! It was " + computeDistance(pos) + " blocks away.");
                }
            }
        }
    }
    
    public Problem() {}
    
    private int computeDistance(Coord c) {
        return Math.abs(c.x) + Math.abs(c.y);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner sn = new Scanner(file).useDelimiter(", ");
        Problem state = new Problem();
        while(sn.hasNext()) {
            String next = sn.next();
            char direction = next.charAt(0);
            int magnitude = Integer.parseInt(next.substring(1));
            state.applyInstruction(direction, magnitude);
            
        }
        System.out.println("Easter Bunny HQ is " + state.computeDistance(state.pos) + " blocks away!");
        sn.close();
    }

}
