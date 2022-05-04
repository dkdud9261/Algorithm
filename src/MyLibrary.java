// 한 정점에서 다른 모든 정점까지의 shortest path : 다익스트라(ElogV), 벨만 포드(VE)
// 모든 정점 사이의 최소 weight : 플로이드 => 이차원 배열만 있으면 됨, 3차 for문 (k,i,j) n^3
// 그래프에서 weight의 합이 최소인 트리(모든 정점 포함) : prim, kruskal

import java.util.*;

public class MyLibrary {

    static boolean[] visited;

    // 조합 - 문자열
    public static void combination(String str, String sub, int start, int n, int r) {
        if(r == n) {
            System.out.println(sub);
            return;
        }
        for(int i = start; i < n; i++) combination(str, sub+str.charAt(i), i+1, n, r+1);
    }

    // 조합 - 배열
    public static void combination1(int[] arr, int start, int r) {
        if(r == 0) {
            for(int i = 0; i < arr.length; i++) {
                if(visited[i]) System.out.print(arr[i] + " ");
            }
        }
        for(int i = start; i < arr.length; i++) {
            visited[i] = true;
            combination1(arr, i+1, r-1);
            visited[i] = false;
        }
    }

    // 순열
    // visited 필요
    public static void permutation(String str, String sub, int n, int r) {
        if(r == n) {
            System.out.println(sub);
            return;
        }
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                permutation(str, sub + str.charAt(i), n, r+1);
                visited[i] = false;
            }
        }
    }

    // 소수 판별기
    public static boolean is_prime(int number) {
        if(number < 2) return false;
        if(number == 2) return true;
        for(int i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0) return false;
        }
        return true;
    }

    // 최대공약수
    // a가 큰거
    static int GCD(int a, int b) {
        if(a % b == 0) return b;
        return GCD(b, a % b);
    }

    // 최소공배수
    static int LCM(int a, int b) {
        return a * b / GCD(a,b);
    }

    // target보다 크거나 같은 첫번째 인덱스 반환, 없으면 data.size() 반환
    static int lowerBound(List<Integer> data, int target) {
        int begin = 0;
        int end = data.size();
        while(begin < end) {
            int mid = (begin + end) / 2;
            if(data.get(mid) >= target) {
                end = mid;
            }
            else {
                begin = mid + 1;
            }
        }
        return end;
    }

    // 다익스트라
    // 초기화 : weight[i][i] = 0, 각 edge의 weight, 나머지는 INF
    static void dijkstra(int[][] weight, int src, ArrayList[] G) {
        PriorityQueue<SharedTaxiFare.Pair> pq = new PriorityQueue<>();
        pq.add(new SharedTaxiFare.Pair(src, 0));
        while (!pq.isEmpty()) {
            SharedTaxiFare.Pair v = pq.remove();
            if (visited[v.v]) continue;
            visited[v.v] = true;
            for (SharedTaxiFare.Pair nv : (ArrayList<SharedTaxiFare.Pair>) G[v.v]) {
                if (weight[src][nv.v] > weight[src][v.v] + nv.w) weight[src][nv.v] = weight[src][v.v] + nv.w;
                if(!visited[nv.v]) pq.add(new SharedTaxiFare.Pair(nv.v, weight[src][nv.v]));
            }
        }
    }

    // 플로이드
    // 초기화 : weight[i][i] = 0, 각 edge의 weight, 나머지는 INF
    static void floyd(int n, int[][] weight) {
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(weight[i][k] == Integer.MAX_VALUE || weight[k][j] == Integer.MAX_VALUE) continue;
                    if(weight[i][j] > weight[i][k] + weight[k][j]) weight[i][j] = weight[i][k] + weight[k][j];
                }
            }
        }
    }

}
