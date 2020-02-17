package backtracking;

import java.util.Arrays;

public class SolveSudokus {

    public static void main(String[] args) {
        int[][] numbers = {
            {6, 0, 0, 0, 0, 0, 2, 9, 5},
            {7, 0, 0, 4, 9, 0, 6, 0, 0},
            {2, 8, 0, 0, 5, 0, 0, 0, 0},
            {0, 0, 0, 9, 2, 7, 0, 3, 0},
            {0, 9, 2, 8, 0, 5, 7, 1, 0},
            {0, 4, 0, 1, 6, 3, 0, 0, 0},
            {0, 0, 0, 0, 3, 0, 0, 5, 9},
            {0, 0, 3, 0, 7, 8, 0, 0, 2},
            {4, 2, 8, 0, 0, 0, 0, 0, 7}
        };
        Sudoku sudoku = new Sudoku(numbers);
        sudoku.solve();
        sudoku.print();
    }
}

class Sudoku {

    private int[][] sudoku;
    private int[][] solution;
    private int row;
    private int column;

    public Sudoku(int[][] numbers) {
        this.row = 0;
        this.column = 0;
        this.sudoku = numbers;
        this.solution = new int[9][9];
        for (int i=0;i<9;i++){
            this.solution[i] = Arrays.copyOf(numbers[i], numbers[i].length);
        }
    }

    public void solve() {

        while (row < 9) {
            if (sudoku[row][column] == 0) {
                do {
                    solution[row][column]++;

                    while (solution[row][column] > 9) {
                        solution[row][column] = 0;
                        goBack();
                        solution[row][column]++;
                    }
                } while (!isRight());
            }
            //next cell
            column++;
            if (column > 8) {
                row++;
                column = 0;
            }
        }

    }

    public void goBack() {
        do {
            column--;
            if (column < 0) {
                row--;
                column = 8;
            }
        } while (sudoku[row][column] != 0);
    }

    public boolean isRight() {
        //row
        for (int i = 0; i < 9; i++) {
            if (solution[i][column] == solution[row][column] && i != row) {
                return false;
            }
        }
        //column
        for (int i = 0; i < 9; i++) {
            if (solution[row][i] == solution[row][column] && i != column) {
                return false;
            }
        }
        //square
        int s_row = row / 3;
        int s_column = column / 3;
        for (int i = s_row * 3; i < s_row * 3 + 3; i++) {
            for (int j = s_column * 3; j < s_column * 3 + 3; j++) {
                if (solution[i][j] == solution[row][column] && (i != row && j != column)) {
                    return false;
                }
            }
        }
        for (int k = s_row * 3; k < column; k++) {
            if (solution[row][k] == solution[row][column]) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (solution[i][j]!=0){
                    System.out.print(solution[i][j] + " ");
                } else {
                    System.out.print("· ");
                }
                if ((j+1)%3==0) System.out.print(" ");
            }
            if ((i+1)%3==0) System.out.println();
            System.out.println();
        }
    }
}
