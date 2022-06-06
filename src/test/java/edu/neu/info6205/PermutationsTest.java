package edu.neu.info6205;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermutationsTest {

    @Test
    void allPermutations() {
        Operations op = new Operations();
        String[][] a = {{"N","X","O"},{"O","X","N"},{"N","N","N"}};
        String[][] b = {{"N","O","N"},{"N","X","X"},{"N","N","O"}};
        assertArrayEquals(b, op.rotateArray(a));
    }
}