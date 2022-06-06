package edu.neu.info6205;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptimalStrategyTest {

    @Test
    void findBestMove() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(8, os.findBestMove());
    }

    @Test
    void numOpponentForks() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(0, os.numOpponentForks());
    }

    @Test
    void win() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.win(1,1));
    }

    @Test
    void block() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.block(1,1));
    }

    @Test
    void fork() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.fork(1,1,"O"));
    }

    @Test
    void blockFork() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.blockFork(1,1));
    }

    @Test
    void center() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(true, os.center(1,1));
    }

    @Test
    void oppositeCorner() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.oppositeCorner(1,1));
    }

    @Test
    void emptyCorner() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.emptyCorner(1,1));
    }

    @Test
    void threeRow() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.threeRow(1,1,false));
    }

    @Test
    void threeCol() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.threeCol(1,1,false));
    }

    @Test
    void threeDiag() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.threeDiag(1,1,false));
    }

    @Test
    void twoRow() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.twoRow(1,1,"O"));
    }

    @Test
    void twoCol() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.twoCol(1,1,"O"));
    }

    @Test
    void twoDiag() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.twoDiag(1,1,"O"));
    }

    @Test
    void getEmpty() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        int[] b = {0,0};
        assertArrayEquals(b, os.getEmpty(0,1,"row"));
    }

    @Test
    void pythonMod() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(0, os.pythonMod(2,1));
    }
}