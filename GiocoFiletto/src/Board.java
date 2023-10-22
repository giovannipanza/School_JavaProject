class Board {
    private char[][] grid;

    public Board() {
        grid = new char[3][3];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  -+-+-");
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
    }

    public boolean checkWin(Move lastMove, char player) {
        int row = lastMove.getRow();
        int col = lastMove.getCol();

        return (grid[row][0] == player && grid[row][1] == player && grid[row][2] == player) ||
                (grid[0][col] == player && grid[1][col] == player && grid[2][col] == player) ||
                (row == col && grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) ||
                (row + col == 2 && grid[0][2] == player && grid[1][1] == player && grid[2][0] == player);
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