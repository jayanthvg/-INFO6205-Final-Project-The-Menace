package edu.neu.info6205;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeadsFileTest {

    @Test
    void demo() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.twoCol(1,1,"O"));
    }
}