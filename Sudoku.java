import java.util.Scanner;

public class Sudoku {

        public static void main(String args[]) {
            /*Scanner input = new Scanner(System.in);
             Intended for future use

             */
            System.out.println("The following program solves 9x9 sudoku puzzles");
            System.out.println("Instructions: \n"+"Order of entering values\n" +
                    "1  | 2  |  3 | 4  | 5  | 6  |  7 |  8 |  9\n" +
                    "10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18\n" +
                    "...\n" +
                    "Values are entered from the left to the right\n" +
                    "Follow this order when entering the values of the puzzle\n" +
                    "NB: Where a value is not given in the puzzle enter zero, 0\n"
                  );
            //Method containing values
            new Sudoku((new int[][]{
                    {3, 0, 6, 5, 0, 8, 4, 0, 0},
                    {5, 2, 0, 0, 0, 0, 0, 0, 0},
                    {0, 8, 7, 0, 0, 0, 0, 3, 1},
                    {0, 0, 3, 0, 1, 0, 0, 8, 0},
                    {9, 0, 0, 8, 6, 3, 0, 0, 5},
                    {0, 5, 0, 0, 9, 0, 6, 0, 0},
                    {1, 3, 0, 0, 0, 0, 2, 5, 0},
                    {0, 0, 0, 0, 0, 0, 0, 7, 4},
                    {0, 0, 5, 2, 0, 6, 3, 0, 0},
            })).solve();// Calling solve method
        }

    private int[][] sudoku_puzzle;
        private int n = 9;

        public Sudoku(int sudoku_puzzle[][]) {
            this.sudoku_puzzle = sudoku_puzzle;
        }

        public void solve() {

            if (!backtrackSolve()) {
                System.out.println("This sudoku can't be solved.");
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(sudoku_puzzle[i][j] + " ");
                }
                System.out.println();
            }
        }

        public boolean isSuitableToPutXThere(int i, int j, int x) {

            // Is 'x' used in row.
            for (int jj = 0; jj < n; jj++) {
                if (sudoku_puzzle[i][jj] == x) {
                    return false;
                }
            }

            // Is 'x' used in column.
            for (int ii = 0; ii < n; ii++) {
                if (sudoku_puzzle[ii][j] == x) {
                    return false;
                }
            }

            // Is 'x' used in sudoku 3x3 box.
            int boxRow = i - i % 3;
            int boxColumn = j - j % 3;

            for (int ii = 0; ii < 3; ii++) {
                for (int jj = 0; jj < 3; jj++) {
                    if (sudoku_puzzle[boxRow + ii][boxColumn + jj] == x) {
                        return false;
                    }
                }
            }

            // Everything looks good.
            return true;
        }
        //Backtrack solve algorithm
        public boolean backtrackSolve() {
            int i = 0, j = 0;
            boolean isThereEmptyCell = false;

            for (int ii = 0; ii < n && !isThereEmptyCell; ii++) {
                for (int jj = 0; jj < n && !isThereEmptyCell; jj++) {
                    if (sudoku_puzzle[ii][jj] == 0) {
                        isThereEmptyCell = true;
                        i = ii;
                        j = jj;
                    }
                }
            }

            //Cell value not zero. Done
            if (!isThereEmptyCell) {
                return true;
            }

            for (int x = 1; x < 10; x++) {

                if (isSuitableToPutXThere(i, j, x)) {
                    sudoku_puzzle[i][j] = x;

                    if (backtrackSolve()) {
                        return true;
                    }

                    sudoku_puzzle[i][j] = 0; // Failure if cell value is zero
                }

            }

            return false; // Backtracking
        }
    }


