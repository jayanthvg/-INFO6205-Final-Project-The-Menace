package edu.neu.info6205;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RewardsTest {

    @Test
    void rewards() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        int[] b = {0,0};
        assertArrayEquals(b, os.getEmpty(0,1,"row"));
    }
}