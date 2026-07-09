class Solution {

    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];

        // Fill board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        HashSet<Integer> cols = new HashSet<>();
        HashSet<Integer> diag1 = new HashSet<>(); // row - col
        HashSet<Integer> diag2 = new HashSet<>(); // row + col

        backtrack(0, board, cols, diag1, diag2);

        return ans;
    }

    private void backtrack(int row,
                           char[][] board,
                           HashSet<Integer> cols,
                           HashSet<Integer> diag1,
                           HashSet<Integer> diag2) {

        // Base Case
        if (row == board.length) {
            ans.add(construct(board));
            return;
        }

        // Try every column
        for (int col = 0; col < board.length; col++) {

            // Check if queen can be placed
            if (cols.contains(col) ||
                diag1.contains(row - col) ||
                diag2.contains(row + col)) {
                continue;
            }

            // Place Queen
            board[row][col] = 'Q';
            cols.add(col);
            diag1.add(row - col);
            diag2.add(row + col);

            // Recurse for next row
            backtrack(row + 1, board, cols, diag1, diag2);

            // Backtrack
            board[row][col] = '.';
            cols.remove(col);
            diag1.remove(row - col);
            diag2.remove(row + col);
        }
    }

    private List<String> construct(char[][] board) {

        List<String> list = new ArrayList<>();

        for (char[] row : board) {
            list.add(new String(row));
        }

        return list;
    }
}