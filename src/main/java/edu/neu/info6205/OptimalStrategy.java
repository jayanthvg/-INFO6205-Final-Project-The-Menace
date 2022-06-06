package edu.neu.info6205;

import java.util.*;

public class OptimalStrategy {

    /* state of the current board */
    private final String[][] _board;

    public OptimalStrategy(String[][] board) {
        _board = board;

    }

    public int findBestMove() {
        /* {rank : current move} */
        HashMap<Integer, Integer> squareRanks = new HashMap<Integer, Integer>();
        /* linear index of move (0,...,8) */
        int linearIndex = -1;
        /* rank of move at linearIndex */
        int rank;
        /* iterate through each square on the board, ranking moves using Newell and Simon's 1972 tic-tac-toe program. */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] square = {i, j};
                if (_board[square[0]][square[1]].equals("N")) {
                    linearIndex = i * 3 + j;
                    if (win(i, j)) {
                        rank = 1;
                    } else if (block(i, j)) {
                        rank = 2;
                    } else if (fork(i, j, "O")) {
                        rank = 3;
                    } else if (blockFork(i, j)) {
                        rank = 4;
                    } else if (center(i, j)) {
                        rank = 5;
                    } else if (oppositeCorner(i, j)) {
                        rank = 6;
                    } else if (emptyCorner(i, j)) {
                        rank = 7;
                    } else {
                        /* Empty side */
                        rank = 8;
                    }
                    squareRanks.put(rank, linearIndex);
                }
            }
        }
        int bestRank = Collections.min(squareRanks.keySet());
        return squareRanks.get(bestRank);
    }

    /* number of possible forks open to opponent on current _board */
    public int numOpponentForks() {
        int forkSum = 0;
        for (int i = 0; i < _board.length; i++) {
            for (int j = 0; j < _board[0].length; j++) {
                if (fork(i, j, "X") && _board[i][j].equals("N")) {
                    forkSum++;
                }
            }
        }
        return forkSum;
    }

    /* win if placing "O" in (row, col) --> 3 in a row */
    public boolean win(int row, int col) {
        return threeRow(row, col, false) || threeCol(row, col, false) || threeDiag(row, col, false);
    }

    public boolean block(int row, int col) {
        return threeRow(row, col, true) || threeCol(row, col, true) || threeDiag(row, col, true);
    }

    public boolean fork(int row, int col, String player) {
        boolean twoRowOpen = twoRow(row, col, player);
        boolean twoColOpen = twoCol(row, col, player);
        boolean twoDiagOpen = twoDiag(row, col, player);
        return twoRowOpen && twoColOpen || twoRowOpen && twoDiagOpen || twoColOpen && twoDiagOpen;
    }

    public boolean blockFork(int row, int col) {

        /* create test board to give to new AI */
        String[][] testBoard = new String[3][3];
        /* copy contents of current board into testBoard */
        for (int i = 0; i < _board.length; i++) {
            for (int j =0; j < _board[0].length; j++) {
                testBoard[i][j] = _board[i][j];
            }
        }
        /* place "O" at testBoard[row][col] */
        testBoard[row][col] = "O";
        /* create temporary AI instance using testBoard */
        OptimalStrategy tempBot = new OptimalStrategy (testBoard);

        /* number of forks available to opponent if we place "O" at _board[row][col] */
        int numOpponentForksFuture= tempBot.numOpponentForks();

        /* if opponent currently has one fork opportunity, we should block this fork */
        if (numOpponentForks() == 1 && numOpponentForksFuture == 0) {
            return true;
        }

        /* ^^switch these cases?vv */

        /* if our move blocks all forks and gives us two in a row, move */
        if (numOpponentForksFuture == 0 && twoRow(row, col, "O") || numOpponentForksFuture == 0 && twoCol(row, col, "O")
                || numOpponentForksFuture == 0 && twoDiag(row, col, "O")) {
            return true;
        }
        /* make two in a row if opponent's subsequent block doesn't create fork */
        if (twoRow(row, col, "O")) {
            int[] opponentSquare = getEmpty(row, col, "row");
            return !fork(opponentSquare[0], opponentSquare[1], "X");
        }
        if (twoCol(row, col, "O")) {
            int[] opponentSquare = getEmpty(row, col, "col");
            return !fork(opponentSquare[0], opponentSquare[1], "X");
        }
        if (twoDiag(row, col, "O")) {
            int[] opponentSquare = getEmpty(row, col, "diag");
            return !fork(opponentSquare[0], opponentSquare[1], "X");
        }
        return false;
    }

    public boolean center(int row, int col) {
        return row == 1 && col == 1;
    }

    public boolean oppositeCorner(int row, int col) {
        boolean corner1 = _board[0][0].equals("X") && row == 2 && col == 2;
        boolean corner2 = _board[0][2].equals("X") && row == 2 && col == 0;
        boolean corner3 = _board[2][0].equals("X") && row == 0 && col == 2;
        boolean corner4 = _board[2][2].equals("X") && row == 0 && col == 0;
        return corner1 || corner2 || corner3 || corner4;

    }

    public boolean emptyCorner(int row, int col) {
        return row == 0 & col == 0 || row == 0 & col == 2 || row == 2 && col == 0 || row == 2 && col == 2;
    }


    /* placing "O" at _board[row][col] wins the game (BLOCK==false) or blocks the opponent (BLOCK==true) */
    public boolean threeRow(int row, int col, boolean block) {
        int[] firstPartner = {row, pythonMod(col - 1, 3)};
        int[] secondPartner = {row, pythonMod( col + 1, 3)};
        if (block) {
            return _board[firstPartner[0]][firstPartner[1]].equals("X") && _board[secondPartner[0]][secondPartner[1]].equals("X");
        } else {
            return _board[firstPartner[0]][firstPartner[1]].equals("O") && _board[secondPartner[0]][secondPartner[1]].equals("O");
        }
    }

    /* placing "O" at _board[row][col] wins the game (BLOCK==false) or blocks the opponent (BLOCK==true) */
    public boolean threeCol(int row, int col, boolean block) {
        int[] firstPartner = {pythonMod(row - 1, 3), col};
        int[] secondPartner = {pythonMod(row + 1, 3), col};
        if (block) {
            return _board[firstPartner[0]][firstPartner[1]].equals("X") && _board[secondPartner[0]][secondPartner[1]].equals("X");
        } else {
            return _board[firstPartner[0]][firstPartner[1]].equals("O") && _board[secondPartner[0]][secondPartner[1]].equals("O");
        }
    }

    /* placing "O" at _board[row][col] wins the game (BLOCK==false) or blocks the opponent (BLOCK==true) */
    public boolean threeDiag(int row, int col, boolean block) {
        int[] posFirstPartner = {pythonMod(row - 1, 3), pythonMod(col + 1, 3)};
        int[] posSecondPartner = {pythonMod(row + 1, 3), pythonMod(col - 1, 3)};
        int[] negFirstPartner = {pythonMod(row + 1, 3), pythonMod(col + 1, 3)};
        int[] negSecondPartner = {pythonMod(row - 1, 3), pythonMod(col - 1, 3)};
        if (block) {
            if (row == 1 & col == 1) {
                return _board[posFirstPartner[0]][posFirstPartner[1]].equals("X") && _board[posSecondPartner[0]][posSecondPartner[1]].equals("X")
                        || _board[negFirstPartner[0]][negFirstPartner[1]].equals("X") && _board[negSecondPartner[0]][negSecondPartner[1]].equals("X");
            } else if (row == 0 & col == 0 || row == 2 & col == 2) {
                return _board[negFirstPartner[0]][negFirstPartner[1]].equals("X") && _board[negSecondPartner[0]][negSecondPartner[1]].equals("X");
            } else if (row == 0 & col == 2 || row == 2 & col == 0) {
                return _board[posFirstPartner[0]][posFirstPartner[1]].equals("X") && _board[posSecondPartner[0]][posSecondPartner[1]].equals("X");
            }
        } else {
            if (row == 1 & col == 1) {
                return _board[posFirstPartner[0]][posFirstPartner[1]].equals("O") && _board[posSecondPartner[0]][posSecondPartner[1]].equals("O")
                        || _board[negFirstPartner[0]][negFirstPartner[1]].equals("O") && _board[negSecondPartner[0]][negSecondPartner[1]].equals("O");
            } else if (row == 0 & col == 0 || row == 2 & col == 2) {
                return _board[negFirstPartner[0]][negFirstPartner[1]].equals("O") && _board[negSecondPartner[0]][negSecondPartner[1]].equals("O");
            } else if (row == 0 & col == 2 || row == 2 & col == 0) {
                return _board[posFirstPartner[0]][posFirstPartner[1]].equals("O") && _board[posSecondPartner[0]][posSecondPartner[1]].equals("O");
            }
        }

        return false;
    }

    /* placing "O" at _board[row][col] creates unblocked 2-in-a-row */
    public boolean twoRow(int row, int col, String player) {
        int[] firstPartner = {row, pythonMod(col - 1, 3)};
        int[] secondPartner = {row, pythonMod( col + 1, 3)};
        return _board[firstPartner[0]][firstPartner[1]].equals(player) && _board[secondPartner[0]][secondPartner[1]].equals("N")
                || _board[firstPartner[0]][firstPartner[1]].equals("N") && _board[secondPartner[0]][secondPartner[1]].equals(player);
    }

    /* placing "O" at _board[row][col] creates unblocked 2-in-a-column */
    public boolean twoCol(int row, int col, String player) {
        int[] firstPartner = {pythonMod(row - 1, 3), col};
        int[] secondPartner = {pythonMod(row + 1, 3), col};
        return _board[firstPartner[0]][firstPartner[1]].equals(player) && _board[secondPartner[0]][secondPartner[1]].equals("N")
                || _board[firstPartner[0]][firstPartner[1]].equals("N") && _board[secondPartner[0]][secondPartner[1]].equals(player);
    }

    /* placing "O" at _board[row][col] creates unblocked 2-in-a-diagonal */
    public boolean twoDiag(int row, int col, String player) {
        int[] posFirstPartner = {pythonMod(row - 1, 3), pythonMod(col + 1, 3)};
        int[] posSecondPartner = {pythonMod(row + 1, 3), pythonMod(col - 1, 3)};
        int[] negFirstPartner = {pythonMod(row + 1, 3), pythonMod(col + 1, 3)};
        int[] negSecondPartner = {pythonMod(row - 1, 3), pythonMod(col - 1, 3)};
        if (row == 1 & col == 1) {
            return _board[posFirstPartner[0]][posFirstPartner[1]].equals(player) && _board[posSecondPartner[0]][posSecondPartner[1]].equals("N")
                    || _board[posFirstPartner[0]][posFirstPartner[1]].equals("N") && _board[posSecondPartner[0]][posSecondPartner[1]].equals(player)
                    || _board[negFirstPartner[0]][negFirstPartner[1]].equals(player) && _board[negSecondPartner[0]][negSecondPartner[1]].equals("N")
                    || _board[negFirstPartner[0]][negFirstPartner[1]].equals("N") && _board[negSecondPartner[0]][negSecondPartner[1]].equals(player);
        } else if (row == 0 & col == 0 || row == 2 & col == 2) {
            return _board[negFirstPartner[0]][negFirstPartner[1]].equals(player) && _board[negSecondPartner[0]][negSecondPartner[1]].equals("N")
                    || _board[negFirstPartner[0]][negFirstPartner[1]].equals("N") && _board[negSecondPartner[0]][negSecondPartner[1]].equals(player);
        } else if (row == 0 & col == 2 || row == 2 & col == 0) {
            return _board[posFirstPartner[0]][posFirstPartner[1]].equals(player) && _board[posSecondPartner[0]][posSecondPartner[1]].equals("N")
                    || _board[posFirstPartner[0]][posFirstPartner[1]].equals("N") && _board[posSecondPartner[0]][posSecondPartner[1]].equals(player);
        }
        return false;
    }

    /* placing "O" at _board[row][col] creates two in a line along DIMENSION; find empty spot in line */
    public int[] getEmpty(int row, int col, String dimension) {

        int[] shouldBeEmpty = {-1, -1};

        if (dimension.equals("row")) {
            int[] firstPartner = {row, pythonMod(col - 1, 3)};
            int[] secondPartner = {row, pythonMod( col + 1, 3)};
            if (_board[firstPartner[0]][firstPartner[1]].equals("O")) {
                shouldBeEmpty[0] = secondPartner[0]; shouldBeEmpty[1] = secondPartner[1];
            } else {
                shouldBeEmpty[0] = firstPartner[0]; shouldBeEmpty[1] = firstPartner[1];
            }
        }
        else if (dimension.equals("col")) {
            int[] firstPartner = {pythonMod(row - 1, 3), col};
            int[] secondPartner = {pythonMod(row + 1, 3), col};
            if (_board[firstPartner[0]][firstPartner[1]].equals("O")) {
                shouldBeEmpty[0] = secondPartner[0]; shouldBeEmpty[1] = secondPartner[1];
            } else {
                shouldBeEmpty[0] = firstPartner[0]; shouldBeEmpty[1] = firstPartner[1];
            }

        } else if (dimension.equals("diag")) {
            int[] posFirstPartner = {pythonMod(row - 1, 3), pythonMod(col + 1, 3)};
            int[] posSecondPartner = {pythonMod(row + 1, 3), pythonMod(col - 1, 3)};
            int[] negFirstPartner = {pythonMod(row + 1, 3), pythonMod(col + 1, 3)};
            int[] negSecondPartner = {pythonMod(row - 1, 3), pythonMod(col - 1, 3)};
            if (row == 1 & col == 1) {
                if (_board[posFirstPartner[0]][posFirstPartner[1]].equals("O") && _board[posSecondPartner[0]][posSecondPartner[1]].equals("N")) {
                    shouldBeEmpty[0] = posSecondPartner[0]; shouldBeEmpty[1] = posSecondPartner[1];
                } else if (_board[posFirstPartner[0]][posFirstPartner[1]].equals("N") && _board[posSecondPartner[0]][posSecondPartner[1]].equals("O")) {
                    shouldBeEmpty[0] = posFirstPartner[0]; shouldBeEmpty[1] = posFirstPartner[1];
                } else if (_board[negFirstPartner[0]][negFirstPartner[1]].equals("O") && _board[negSecondPartner[0]][negSecondPartner[1]].equals("N")) {
                    shouldBeEmpty[0] = negSecondPartner[0]; shouldBeEmpty[1] = negSecondPartner[1];
                } else {
                    shouldBeEmpty[0] = negFirstPartner[0]; shouldBeEmpty[1] = negFirstPartner[1];
                }
            } else if (row == 0 & col == 0 || row == 2 & col == 2) {
                if (_board[negFirstPartner[0]][negFirstPartner[1]].equals("O") && _board[negSecondPartner[0]][negSecondPartner[1]].equals("N")) {
                    shouldBeEmpty[0] = negSecondPartner[0]; shouldBeEmpty[1] = negSecondPartner[1];
                } else {
                    shouldBeEmpty[0] = negFirstPartner[0];
                    shouldBeEmpty[1] = negFirstPartner[1];
                }

            } else if (row == 0 & col == 2 || row == 2 & col == 0) {
                if (_board[posFirstPartner[0]][posFirstPartner[1]].equals("O") && _board[posSecondPartner[0]][posSecondPartner[1]].equals("N")) {
                    shouldBeEmpty[0] = posSecondPartner[0]; shouldBeEmpty[1] = posSecondPartner[1];
                } else {
                    shouldBeEmpty[0] = posFirstPartner[0];
                    shouldBeEmpty[1] = posFirstPartner[1];
                }
            }
        }
        return shouldBeEmpty;
    }

    public int pythonMod(int dividend, int divisor) {
        return ((dividend % divisor) + divisor) % divisor;
    }

}
