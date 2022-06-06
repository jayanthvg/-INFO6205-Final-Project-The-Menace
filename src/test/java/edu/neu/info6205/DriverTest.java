package edu.neu.info6205;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    @Test
    void main() {
        Operations op = new Operations();
        String[][] a = {{"N","X","O"},{"O","X","N"},{"N","N","N"}};
        String s = op.arrayToString(a);
        assertEquals("NXOOXNNNN", s);
    }
}