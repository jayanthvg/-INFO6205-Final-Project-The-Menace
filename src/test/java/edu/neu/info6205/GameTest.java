package edu.neu.info6205;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void watch() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.threeRow(1,1,false));
    }

    @Test
    void train() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.twoRow(1,1,"O"));
    }
}