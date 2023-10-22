public class TicTacToeGame {
    private final int BOARD_SIZE = 3;
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToeGame() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = 'X';
        gameOver = false;

        // Initialize the board with empty cells
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = '-';
            }
        }
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == '-';
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean checkForWin() {
        // Check rows for a win
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] != '-' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                gameOver = true;
                return true;
            }
        }

        // Check columns for a win
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] != '-' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                gameOver = true;
                return true;
            }
        }

        // Check diagonals for a win
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            gameOver = true;
            return true;
        }

        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            gameOver = true;
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        gameOver = true;
        return true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getBoardAsString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                sb.append(board[row][col]);
            }
            sb.append("ENDL");
        }
        return sb.toString();
    }
}