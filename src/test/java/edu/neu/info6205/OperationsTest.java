package edu.neu.info6205;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void PickBead() {
        Operations op = new Operations();
        int[] a = {1,2,3,4,5,6,7,8,9};
        boolean flag = op.pickBead(a)<9 && op.pickBead(a)>=0;
        assertTrue(flag);
    }

    @Test
    void beadPositionNumbers() {
        Operations op = new Operations();
        String a = "BBNNBNNNN";
        String beadCount = op.beadPositionNumbers(a,8);
        assertEquals("8,8,0,0,8,0,0,0,0,", beadCount);
    }

    @Test
    void beadPositions() {
        Operations op = new Operations();
        String a = "OXNNNNNXO";
        String nextPositions = op.beadPositions(a);
        assertEquals("OXBNBBNXO", nextPositions);
    }

    @Test
    void finalArrays() {
        Set<String> set = new HashSet<String>(){{
            add("NNXNNNONN");
            add("NNNNXNNON");
            add("XNNNNONNN");
            add("NNNNONNNX");
            add("NNNNXNNNO");
            add("XNNONNNNN");
            add("NXNNNNNNO");
            add("NNNONNNXN");
            add("NXNNONNNN");
            add("NNNNNNXNO");
            add("NNNONXNNN");
            add("ONNXNNNNN");

        }};
        Operations op = new Operations();
        String a = "OXNNNNNNN";
        assertEquals(set, op.finalArrays(a));
    }

    @Test
    void similarArrays() {
        Set<String> set = new HashSet<String>(){{
            add("XNONNNNNN");
            add("NNXNNNNNO");
            add("NNNNNNONX");
            add("NNONNNNNX");
            add("NNNNNNXNO");
            add("XNNNNNONN");
            add("ONXNNNNNN");
        }};
        Operations op = new Operations();
        String a = "ONNNNNXNN";
        assertEquals(set, op.similarArrays(a));
    }

    @Test
    void arrayToString() {
        Operations op = new Operations();
        String[][] a = {{"N","X","O"},{"O","X","N"},{"N","N","N"}};
        String s = op.arrayToString(a);
        assertEquals("NXOOXNNNN", s);
    }

    @Test
    void stringToArray() {
        Operations op = new Operations();
        String[][] a = {{"N","X","O"},{"O","X","N"},{"N","N","N"}};
        String s = "NXOOXNNNN";
        assertArrayEquals(a, op.stringToArray(s));
    }

    @Test
    void rotateArray() {
        Operations op = new Operations();
        String[][] a = {{"N","X","O"},{"O","X","N"},{"N","N","N"}};
        String[][] b = {{"N","O","N"},{"N","X","X"},{"N","N","O"}};
        assertArrayEquals(b, op.rotateArray(a));
    }

    @Test
    void mirrorImage() {
        Operations op = new Operations();
        String[][] a = {{"N","X","O"},{"O","X","N"},{"N","N","N"}};
        String[][] b = {{"O","X","N"},{"N","X","O"},{"N","N","N"}};
        assertArrayEquals(b, op.mirrorImage(a));
    }

    @Test
    void gameEndCheck() {
        Operations op = new Operations();
        String[][] a = {{"X","N","O"},{"X","X","O"},{"N","N","O"}};
        String b = "Human Wins";
        assertEquals(b, op.gameEndCheck(a));
    }

    @Test
    void printArray() {
        Operations op = new Operations();
        String[][] a = {{"N","X","O"},{"O","X","N"},{"N","N","N"}};
        String[][] b = {{"O","X","N"},{"N","X","O"},{"N","N","N"}};
        assertArrayEquals(b, op.mirrorImage(a));
    }

    @Test
    void nextState() {
        Operations op = new Operations();
        String[][] a = {{"N","X","O"},{"O","X","N"},{"N","N","N"}};
        String[][] b = {{"N","O","N"},{"N","X","X"},{"N","N","O"}};
        assertArrayEquals(b, op.rotateArray(a));
    }
}