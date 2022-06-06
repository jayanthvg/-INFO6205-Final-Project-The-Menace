package edu.neu.info6205;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVreadWriteTest {

    @Test
    void readCSV() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.threeDiag(1,1,false));
    }

    @Test
    void writeCSV() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.twoRow(1,1,"O"));
    }

    @Test
    void readCSVline() {
        String[][] a = {{"N","N","N"},{"N","X","N"},{"N","N","N"}};
        OptimalStrategy os = new OptimalStrategy(a);
        assertEquals(false, os.twoCol(1,1,"O"));
    }
}