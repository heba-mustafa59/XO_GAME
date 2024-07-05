package practice;
import java.util.Scanner;

public class Practice {
    private static final int SIZE = 3;
    private static final char EMPTY = '-';
    private static final char P1= 'X';
    private static final char P2= 'O';

    public static void main(String[] args) {
        char[][] board = new char[SIZE][SIZE];
        initBoard(board);
        playGame(board);
    }

    private static void initBoard(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void playGame(char[][] board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("HEY DUDE! ,Welcome to heba's XO");
        System.out.print("Enter player 1's name: ");
        String p1 = sc.nextLine();
        System.out.print("Enter player 2's name: ");
        String p2 = sc.nextLine();

        boolean isP1Turn = true;
        boolean gameEnd = false;

        while (!gameEnd) {
            draw(board);
            String currPlayer = isP1Turn ? p1 : p2;
            System.out.println(currPlayer + "'s turn (" + (isP1Turn ? P1 : P2) + "):");

            int r, c;
            while (true) {
                r = getInt(sc, "Enter the row number (0 to 2): ");
                c = getInt(sc, "Enter the column number (0 to 2): ");
                if (isValid(board, r, c)) {
                    break;
                }
                System.out.println("Invalid POSITION, Try again.");
            }

            board[r][c] = isP1Turn ? P1 : P2;
            if (won(board, isP1Turn ? P1 : P2)) {
                System.out.println(currPlayer + "  WON!");
                gameEnd = true;
            } else if (full(board)) {
                System.out.println("TIE GUYES !");
                gameEnd = true;
            } else {
                isP1Turn = !isP1Turn;
            }
        }
        draw(board);
        sc.close();
    }

    private static int getInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Invalid input. " + prompt);
        }
        return sc.nextInt();
    }

    private static boolean isValid(char[][] board, int r, int c) {
        return r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c] == EMPTY;
    }

    private static void draw(char[][] board) {
        System.out.println("Current board:");
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    private static boolean won(char[][] board, char sym) {
        for (int i = 0; i < SIZE; i++) {
            if (checkRow(board, i, sym) || checkCol(board, i, sym)) {
                return true;
            }
        }
        return checkDiags(board, sym);
    }

    private static boolean checkRow(char[][] board, int r, char sym) {
        for (int c = 0; c < SIZE; c++) {
            if (board[r][c] != sym) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkCol(char[][] board, int c, char sym) {
        for (int r = 0; r < SIZE; r++) {
            if (board[r][c] != sym) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiags(char[][] board, char sym) {
        boolean diag1 = true, diag2 = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] != sym) {
                diag1 = false;
            }
            if (board[i][SIZE - 1 - i] != sym) {
                diag2 = false;
            }
        }
        return diag1 || diag2;
    }

    private static boolean full(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
