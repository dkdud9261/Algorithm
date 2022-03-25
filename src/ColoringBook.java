import java.util.Arrays;

public class ColoringBook {

    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int size;

    public static void dfs(int r, int c, int m, int n, int[][] picture) {
        size++;
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (0 <= nr && nr < m && 0 <= nc && nc < n) {
                if (picture[nr][nc] > 0 && !visited[nr][nc] && picture[r][c] == picture[nr][nc]) {
                    dfs(nr, nc, m, n, picture);
                }
            }
        }

    }

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                size = 0;
                if(picture[i][j] > 0 && !visited[i][j]) dfs(i, j, m, n, picture);
                if(size > 0) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(6,4,new int[][]{{1,1,1,0},{1,2,2,0},{1,0,0,1},{0,0,0,1},{0,0,0,3},{0,0,0,3}})));
        System.out.println(Arrays.toString(solution(1,1,new int[][]{{0}})));
        System.out.println(Arrays.toString(solution(2,2,new int[][]{{0,1},{1,0}})));
    }
}
