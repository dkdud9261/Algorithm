import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SharedTaxiFare {

    static class Pair implements Comparable<Pair> {
        int v, w;
        public Pair(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int[][] weight = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(weight[i], Integer.MAX_VALUE);
            weight[i][i] = 0;
        }
        for(int[] fare : fares) weight[fare[0]][fare[1]] = weight[fare[1]][fare[0]] = fare[2];
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(weight[i][k] == Integer.MAX_VALUE || weight[k][j] == Integer.MAX_VALUE) continue;
                    if(weight[i][j] > weight[i][k] + weight[k][j]) weight[i][j] = weight[i][k] + weight[k][j];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) min = Math.min(min, weight[s][i] + weight[i][a] + weight[i][b]);
        answer = min;

        return answer;
    }

    public static int solution1(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        ArrayList[] G = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) G[i] = new ArrayList<Pair>();
        int[][] weight = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(weight[i], Integer.MAX_VALUE);
            weight[i][i] = 0;
        }
        for(int[] fare : fares) {
            G[fare[0]].add(new Pair(fare[1], fare[2]));
            G[fare[1]].add(new Pair(fare[0], fare[2]));
            weight[fare[0]][fare[1]] = weight[fare[0]][fare[1]] = fare[2];
        }
        for(int i = 1; i <= n; i++) {   // i에서 다른 모든 지점까지의 최소 거리를 weight에 저장
            boolean[] visited = new boolean[n + 1];
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            pq.add(new Pair(i, 0));
            while (!pq.isEmpty()) {
                Pair v = pq.remove();
                if (visited[v.v]) continue;
                visited[v.v] = true;
                for (Pair nv : (ArrayList<Pair>) G[v.v]) {
                    if (weight[i][nv.v] > weight[i][v.v] + nv.w) weight[i][nv.v] = weight[i][v.v] + nv.w;
                    if(!visited[nv.v]) pq.add(new Pair(nv.v, weight[i][nv.v]));
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int k = 1; k <= n; k++) {
            min = Math.min(min, weight[s][k] + weight[k][a] + weight[k][b]);
        }
        return answer;
    }

    public static void main(String[] args) {
        solution(6,4,6,2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
    }
}
