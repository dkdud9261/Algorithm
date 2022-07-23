import java.util.*;
import java.io.*;

public class Main {

    // 치킨 배달(15686)
    /*static class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Dist implements Comparable<Dist> {
        Pos house, chicken;
        int dist;

        public Dist(Pos house, Pos chicken) {
            this.house = house;
            this.chicken = chicken;
            this.dist = Math.abs(house.r-chicken.r) + Math.abs(house.c-chicken.c);
        }

        @Override
        public int compareTo(Dist o) {
            if(this.dist == o.dist) return Integer.compare(this.chicken.r, o.chicken.r);
            return Integer.compare(this.dist, o.dist);
        }
    }

    // 집(A개) 리스트, 치킨집(B개) 리스트
    // 각 집마다 각 치킨집까지의 거리를 가까운순으로 PriorityQueue<>[]
    // 치킨집을 M개씩 선택해서 치킨거리 구하고 최솟값 구하기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Pos> house = new ArrayList<>();
        ArrayList<Pos> chicken = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 1) house.add(new Pos(i, j));
                else if(n == 2) chicken.add(new Pos(i,j));
            }
        }
        PriorityQueue[] pq = new PriorityQueue[house.size()];
        int[][] chickenDist = new int[house.size()][chicken.size()];
        for(int i = 0; i < house.size(); i++) {
            pq[i] = new PriorityQueue<Dist>();
            for(int j = 0; j < chicken.size(); j++) {
                Dist dist = new Dist(house.get(i), chicken.get(j));
                pq[i].add(dist);
                chickenDist[i][j] = dist.dist;
            }
        }
        int a = 'a';

    }*/

    // 설탕 배달
    /*public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int five = N/5;     // 3
        int rest = N%5;     // 3
        if(rest == 0) System.out.println(five);
        else {
            while(five >= 0) {
                if(rest % 3 == 0) {
                    int three = rest / 3;
                    System.out.println((five + three));
                    break;
                }
                five--;
                rest += 5;
            }
            if(five < 0)System.out.println(-1);
        }
    }*/

    // ATM
    /*public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer[] arr = new Integer[N];
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        int[] sum = new int[N];
        sum[0] = arr[0];
        if(N > 1) {
            for(int i = 1; i < N; i++) sum[i] = sum[i-1] + arr[i];
        }
        int answer = 0;
        for(int s : sum) answer += s;
        System.out.println(answer);
    }*/

}