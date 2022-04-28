import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BuildRaceTrack {

    public static class Pos {
        int i, j, dist;
        int dir;
        Pos(int i, int j, int dist, int dir) {
            this.i = i;
            this.j = j;
            this.dir = dir;
            this.dist = dist;
        }
    }

    public static int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        int[] dr = {-1,0,1,0};
        int[] dc = {0,1,0,-1};

        int[][] price = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(price[i], Integer.MAX_VALUE);
        price[0][0] = 0;
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(0,0,0, -1));
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Pos pos = queue.remove();
            int i = pos.i;
            int j = pos.j;
//            System.out.println(i + " " + j);
            if(i == n-1 && j == n-1) {
                min = Math.min(min, pos.dist);
                continue;
            }
            for(int k = 0; k < 4; k++) {
                int ni = i + dr[k];
                int nj = j + dc[k];
                if(0 <= ni && ni < n && 0 <= nj && nj < n) {
                    if(board[ni][nj] != 1 && price[ni][nj] > pos.dist) {
                        int ndist = pos.dist;
                        if(pos.dir == -1 || pos.dir == k) ndist += 100;
                        else ndist += 600;
                        price[ni][nj] = pos.dist+100;
                        queue.add(new Pos(ni, nj, ndist, k));
                    }
                }
            }
        }
        answer = min;
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        solution(new int[][]{{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}});
        solution(new int[][]{{0,0,0},{0,0,0},{0,0,0}});
        solution(new int[][]{{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}});
        solution(new int[][]{{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}});

    }
}
