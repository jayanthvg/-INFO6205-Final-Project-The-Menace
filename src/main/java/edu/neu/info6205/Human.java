package edu.neu.info6205;

public class Human {

    //Next human move to counter the menace machine
    public int humanMove(String state, double p) {
        // TODO Auto-generated method stub

        Operations op = new Operations();
        String[][] checkRowBoard = op.stringToArray(state);
        OptimalStrategy checkRowBot = new OptimalStrategy(checkRowBoard);
        return humanStrategy(checkRowBoard, checkRowBot.findBestMove(), p);
    }

    public int humanStrategy(String[][] state, int bestMove, double prob) {
        // Returns a random integer from 0 to 8:
        int randomMove = (int)(Math.random() * 9);
        while(true) {
            randomMove = randomMove%9;
            if(state[randomMove/3][randomMove%3].equals("N")) return finalMove(bestMove, randomMove, 100*prob);
            randomMove++;
        }
    }

    public static int finalMove(int x, int y, double p)
    {
        // Generate a number from 1 to 100
        int r = (int)(Math.random() * 100 + 1);
        if (r <= p)
            return x;
        return y;
    }
}
