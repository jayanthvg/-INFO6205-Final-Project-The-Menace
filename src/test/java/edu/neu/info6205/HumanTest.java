package edu.neu.info6205;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    void finalMove() {
        int move = Human.finalMove(1,2,0.5);
        boolean flag = (move == 1) || (move == 2);
        assertTrue(flag);

    }

    @Test
    void humanMove() {
        Human h = new Human();
        int move = h.humanMove("ONONXNXXN",1);
        assertEquals(1,move);
    }

    @Test
    void humanStrategy() {
        Human h = new Human();
        String[][] a = {{"O","N","O"},{"N","X","N"},{"X","X","N"}};
        int move = h.humanStrategy(a,1, 1);
        assertEquals(1,move);
    }

}