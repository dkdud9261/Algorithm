public class LargestSquare {

    public boolean canSquare(int[][] board, int x, int y, int N) {
        if(y+N >= board[0].length) return false;
        for(int i = x; i < x+N; i++) {
            for(int j = y; j < y+N; j++) {
                if(board[i][j] != 1) return false;
            }
        }
        return true;
    }

    public int solution(int [][]board)
    {
        int R = board.length;
        int C = board[0].length;
        boolean[][] visisted = new boolean[R][C];
        int max = 0;

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(board[i][j] == 1 && !visisted[i][j]) {
                    int N = 0;
                    for(int k = i; k < R; k++) {
                        if(board[k][j] == 1) N++;
                        else break;
                    }
                    // 1x1 ~ NxN 중에 최대 정사각형
                    for(int k = N; k >= 1; k--) {
                        if(canSquare(board, i, j, k)) {
                            max = Math.max(max, N*N);
                            for(int x = i; x < i+N; x++) {
                                for(int y = j; y < j+N; y++) visisted[x][y] = true;
                            }
                            break;
                        }
                    }
                }
            }
        }

        return max;
    }
}
