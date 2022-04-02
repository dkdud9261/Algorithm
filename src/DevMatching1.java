import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DevMatching1 {
    static boolean[] visited;
    static int[][] myAnswer;
    static int idx=0;

    public static void permutation(int[] arr, int[] sub, int n, int r, int[][] dist) {
        if(r == n) {
            boolean flag = false;
//            for(int i = 0; i < n; i++) {
//                for(int j = i+1; j < n; j++) {
//                    int xi = dist[sub[0]][sub[i]];
//                    int xj = dist[sub[0]][sub[j]];
//                    if(xj-xi != dist[sub[i]][sub[j]]) {
//                        flag = true;
//                        break;
//                    }
//                }
//                if(flag) break;
//            }
            if(!flag) {
                for(int i = 0; i < n; i++) myAnswer[idx][i] = sub[i];
                idx++;
            }
            return;
        }

        for(int i = 0; i < n; i++) {
            boolean flag = false;
            if(r > 0) {
                int xi = dist[sub[0]][sub[r-1]];
                int xj = dist[sub[0]][sub[i]];
                System.out.println(Arrays.toString(sub));
                System.out.println(r + " : " + sub[r-1] + " " + sub[i] + " " + dist[sub[r-1]][sub[i]]);

                if(xj-xi != dist[sub[r-1]][sub[i]]) flag = true;
            }
            if(r == 0 || (r > 0 && !flag)) {
                if(!visited[i]) {
                    visited[i] = true;
                    sub[r] = arr[i];
                    permutation(arr, sub, n, r + 1, dist);
                    visited[i] = false;
                }
            }
        }
    }

    public static int[][] solution(int[][] dist) {
        int[][] answer = {};
        int n = dist.length;
        int[] arr = new int[n];
        myAnswer = new int[100][n];
        visited = new boolean[n];
        for(int i = 0; i < n; i++) arr[i] = i;

        permutation(arr, new int[n], n, 0, dist);
        answer = new int[idx][n];
        for(int i = 0; i < idx; i++) {
            for(int j = 0; j < n; j++) answer[i][j] = myAnswer[i][j];
        }
        return answer;
    }

    public static int solution1(String[] grid) {
        int answer = -1;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length(); j++) {
                if(grid[i].charAt(j) == '?') {
                    int[] abc = {0,0,0}; // a,b,c
                    int temp = 1;
                    for(int k = 0; k < 4; k++) {
                        int sr = i + dr[k];
                        int sc = j + dc[k];
                        if(0 <= sr && sr < grid.length && 0 <= sc && sc < grid[0].length()) {
//                            switch (grid[sr].charAt(sc)) {
//                                case 'a':
//                                    abc[0]++;
//                                    break;
//                                case 'b':
//                                    abc[1]++;
//                                    break;
//                                case 'c':
//                                    abc[2]++;
//                                    break;
//                                default: break;
//                            }
                            if(grid[sr].charAt(sc) == '?') temp++;
                        }
                    }
                    System.out.println(temp);
//                    for(int k = 0; k < 3; k++) if(abc[k] >= 1) temp++;
                    answer = Math.max(temp, answer);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new String[]{"??b", "abc", "cc?"}));
//        System.out.println(solution(new String[]{"abcabcab","????????"}));
//        solution(new int[][]{{0,5,2,4,1},{5,0,3,9,6},{2,3,0,6,3},{4,9,6,0,3},{1,6,3,3,0}});
        int [][] ans = solution(new int[][]{{0,5,2,4,1},{5,0,3,9,6},{2,3,0,6,3},{4,9,6,0,3},{1,6,3,3,0}});
        for(int i = 0; i < ans.length; i++) System.out.println(Arrays.toString(ans[i]));
    }
}
