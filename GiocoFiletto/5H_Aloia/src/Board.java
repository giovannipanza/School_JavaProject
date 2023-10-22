import java.io.PrintWriter;

class Board {
    private char[][] grid;
    private char currPlayer = 'X'; // il primo turno inizia dal playerX

    boolean won = false, draw = false;



    public Board() {
        grid = new char[3][3];
        initializeBoard();
    }

    public char getCurrPlayer() {
        return currPlayer;
    }

    public void setCurrPlayer(char currPlayer) {
        this.currPlayer = currPlayer;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public boolean isDraw() {
        return draw;
    }

    public boolean isWon() {
        return won;
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void printBoard(PrintWriter out) {
        out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                out.print(grid[i][j]);
                if (j < 2) {
                    out.print("|");
                }
            }
            out.println();
            if (i < 2) {
                out.println("  -+-+-");
            }
        }
    }

    public boolean isValidMove(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        return row >= 0 && row < 3 && col >= 0 && col < 3 && grid[row][col] == ' ';
    }

    public void makeMove(Move move, char player) {

        int row = move.getRow();
        int col = move.getCol();
        grid[row][col] = (player == 'X') ? 'X' : 'O';
        this.currPlayer = (player == 'X') ? 'O' : 'X';
    }



    public boolean checkWin(Move lastMove, char player) {
        int row = lastMove.getRow();
        int col = lastMove.getCol();

        if ((grid[row][0] == player && grid[row][1] == player && grid[row][2] == player) ||
                (grid[0][col] == player && grid[1][col] == player && grid[2][col] == player) ||
                (row == col && grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) ||
                (row + col == 2 && grid[0][2] == player && grid[1][1] == player && grid[2][0] == player)) {
            this.won = true;
            return true;
        }
        else return false;

    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}