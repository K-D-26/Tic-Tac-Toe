import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println(
                "Enter the input in the form of row and col. \nFor e.g., 1 2 - which means first row and second column.");
        System.out.println("First turn for player 'X'.\n");

        char[][] board = new char[3][3];
        board = new char[3][3];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }

        char player = 'X';
        boolean gameOver = false;
        boolean filled, won = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            printBoard(board);
            System.out.print("Player " + player + " enter: ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            System.out.println();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = player;
                won = haveWon(board, player);
                filled = allFilled(board);
                gameOver = won || filled;
                if (gameOver) {
                    break;
                } else {
                    player = (player == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move. Try again!");
            }
        }

        scanner.close();
        return;
    }

    public static void printBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (col == 0) {
                    System.out.print(" ");
                }
                if (col != 2) {
                    System.out.print(board[row][col] + " | ");
                } else {
                    System.out.print(board[row][col]);
                }
            }
            System.out.println();
            if (row != 2) {
                System.out.print("--- --- ---\n");
            }
        }
        System.out.println();
    }

    private static boolean allFilled(char[][] board) {
        // checks if all the 9 blocks are filled.
        boolean isNotFilled = false;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    isNotFilled = true;
                    break;
                }
            }
        }

        if (!isNotFilled) {
            printBoard(board);
            System.out.println("Game Over! Nobody wins.");
            return true;
        }

        return false;
    }

    private static boolean haveWon(char[][] board, char player) {
        boolean won = false;
        // check for the row
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                won = true;
            }
        }

        // check for the col
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                won = true;
            }
        }

        // check for the diagonal (top-left to bottom-right)
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            won = true;
        }

        // check for the diagonal (top-right to bottom-left)
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            won = true;
        }

        if (won == true) {
            printBoard(board);
            System.out.println("Player " + player + " wins!");
            return true;
        }

        return false;
    }
}