import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class Friends4Blocks {
    static boolean[][] visited;
    static HashSet<Block> remove;

    static class Block {
        int i, j;
        public Block(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object a) {
            Block obj = (Block) a;
            return (obj.i == this.i && obj.j == this.j);
        }

        @Override
        public int hashCode() {
            return (i+""+ j).hashCode();
        }
    }

    public static char[][] rearrange(int m, int n, char[][] board) {
        int i, j;

        for(Block b : remove) {
            i = b.i;
            j = b.j;
            board[i][j] = '-';
        }
        for(int c = 0; c < n; c++) {
            int level = -1;
            for (int r = m-1; r >= 0; r--) {
                if (board[r][c] == '-') {
                    if(level == -1) level = r;
                }
                if (level != -1 && board[r][c] != '-') {
                    board[level][c] = board[r][c];
                    board[r][c] = '-';
                    level--;
                }
            }
        }

        return board;
    }

    public static void search(int m, int n, char[][] board, int i, int j) {
        int[] dr = {0, 1, 1};
        int[] dc = {1, 1, 0};

        int cnt = 0;
        visited[i][j] = true;
        for(int k = 0; k < 3; k++) {
            int nr = i + dr[k];
            int nc = j + dc[k];
            if(board[i][j] == board[nr][nc]) {
                cnt++;
                if (!visited[nr][nc] && nr < m - 1 && nc < n - 1) {
                    search(m, n, board, nr, nc);
                }
            }
        }
        if(cnt == 3) {
            remove.add(new Block(i, j));
            for(int k = 0; k < 3; k++) {
                remove.add(new Block(i+dr[k], j+dc[k]));
            }
        }
    }

    public static int solution(int m, int n, String[] board) {
        int answer = 0;
        visited = new boolean[m][n];
        remove = new HashSet<>();
        char[][] myBoard = new char[m][n];
        for(int r = 0; r < m; r++) {
            String[] split = board[r].split("");
            for(int c = 0; c < n; c++) myBoard[r][c] = split[c].charAt(0);
        }

        int temp;
        while(true) {
            temp = answer;
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (!visited[i][j] && myBoard[i][j] != '-') search(m, n, myBoard, i, j);
                }
            }
            rearrange(m, n, myBoard);
            answer += remove.size();
            if(temp == answer) break;
            remove.clear();
            visited = new boolean[m][n];
        }
        System.out.println(answer);

        return answer;
    }

    public static void main(String[] args) {
        solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"});
    }
}
