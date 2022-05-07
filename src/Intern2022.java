import java.util.*;

public class Intern2022 {

    public static String solution1(String[] survey, int[] choices) {
        String answer = "";
        HashMap<Character, Integer> map = new HashMap<>();
        int l = survey.length;
        for(int i = 0; i < l; i++) {
            char type0 = survey[i].charAt(0);
            char type1 = survey[i].charAt(1);
            int result = choices[i] - 4;
            if(result < 0) {
                map.put(type0, map.getOrDefault(type0, 0) + Math.abs(result));
                map.put(type1, map.getOrDefault(type1, 0) - Math.abs(result));
            }
            else {
                map.put(type0, map.getOrDefault(type0, 0) - Math.abs(result));
                map.put(type1, map.getOrDefault(type1, 0) + Math.abs(result));
            }
        }
        char[][] indicators = {{'R','T'}, {'C','F'}, {'J','M'}, {'A','N'}};
        for(int i = 0; i < 4; i++) {
            if(map.get(indicators[i][0]) == null || map.get(indicators[i][0]) >= 0) answer += indicators[i][0];
            else answer += indicators[i][1];
        }
        return answer;
    }

    public static int solution2(int[] queue1, int[] queue2) {
        Queue<Long> q1 = new LinkedList<>();
        Queue<Long> q2 = new LinkedList<>();
        long sum1 = 0, sum2 = 0;
        long max1 = 0, max2 = 0;
        int l = queue1.length;
        for(int i = 0; i < l; i++) {
            q1.add((long) queue1[i]);
            q2.add((long) queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
            max1 = Math.max(max1, queue1[i]);
            max2 = Math.max(max2, queue2[i]);
        }
        if((sum1 + sum2) % 2 != 0) return -1;
        int cnt = 0;
        long val;
        while(sum1 != sum2) {
            cnt++;
            if(sum1 > sum2) {   // q1이 주기
                val = q1.remove();
                q2.add(val);
                sum1 -= val;
                sum2 += val;
            }
            else {  // q2가 주기
                val = q2.remove();
                q1.add(val);
                sum2 -= val;
                sum1 += val;
            }
            if(q1.isEmpty() || q2.isEmpty() || cnt > l) {
                cnt = -1;
                break;
            }
        }

        return cnt;
    }

    static class Problem implements Comparable<Problem> {
        int qnum;
        int al_req, co_req, al_rwd, co_rwd, cost;
        boolean for_what; // true이면 푸는 순서, false이면 꺼내는 순서
        boolean isAlpLess; // true이면 알능 필요, false이면 코능 필요

        public Problem(int qnum, int[] p, boolean for_what, boolean isAlpLess) {
            this.qnum = qnum;
            this.al_req = p[0];
            this.co_req = p[1];
            this.al_rwd = p[2];
            this.co_rwd = p[3];
            this.cost = p[4];
            this.for_what = for_what;
            this.isAlpLess = isAlpLess;
        }

        @Override
        public int compareTo(Problem o) {
            if(for_what) {
                if(isAlpLess) {
                    if (this.al_rwd == o.al_rwd) {
                        if(this.al_req == o.al_req) return Integer.compare(this.cost, o.cost);
                        return Integer.compare(this.al_req, o.al_req);
                    }
                    return Integer.compare(o.al_rwd, this.al_rwd);
                }
                else {
                    if(this.co_rwd == o.co_rwd) {
                        if(this.co_req == o.co_req) return Integer.compare(this.cost, o.cost);
                        return Integer.compare(this.co_req, o.co_req);
                    }
                    return Integer.compare(o.co_rwd, this.co_rwd);
                }
            }
            if(isAlpLess) { // 알능이 작으면
                if(this.al_req == this.co_req) return Integer.compare(o.al_req, this.al_req);
                if(this.al_req == o.al_req) return Integer.compare(this.co_req, o.co_req);
                return Integer.compare(this.al_req, o.al_req);
            }
            else {
                if(this.al_req == this.co_req) return Integer.compare(o.co_req, this.co_req);
                if(this.co_req == o.co_req) return Integer.compare(this.al_req, o.al_req);
                return Integer.compare(this.co_req, o.co_req);
            }
        }
    }

    /*public static int solution3(int alp, int cop, int[][] problems) {
        int answer = 0;
        PriorityQueue<Problem> problem = new PriorityQueue<>();
        PriorityQueue<Problem> solveWhenNeedAl = new PriorityQueue<>();
        PriorityQueue<Problem> solveWhenNeedCo = new PriorityQueue<>();
        boolean isAlpLess = (alp <= cop);
        for(int i = 0; i < problems.length; i++) {
            problem.add(new Problem(i, problems[i], false, isAlpLess));
            solveWhenNeedAl.add(new Problem(i, problems[i], true, true));
            solveWhenNeedCo.add(new Problem(i, problems[i], true, false));
        }
        LinkedList<Problem> can_solve = new LinkedList<>();
        while(!problem.isEmpty()) {
            Problem try_solve;
            while (!problem.isEmpty()) {
                try_solve = problem.peek();
                if(alp < try_solve.al_req || cop < try_solve.co_req) break;
                can_solve.add(try_solve);
                problem.remove();
            }
            if(try_solve)
        }

        return answer;
    }*/

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

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        answer = new int[2];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MAX_VALUE;
        ArrayList[] G = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) G[i] = new ArrayList<Pair>();
        for(int[] path : paths) {
            G[path[0]].add(new Pair(path[1], path[2]));
            G[path[1]].add(new Pair(path[0], path[2]));
        }
        int[] type = new int[n+1];  // 1-gate 2-summit 3-relax
        for(int g : gates) type[g] = 1;
        for(int s : summits) type[s] = 2;

        int[] intensity = new int[n+1]; // 출입구에서 각 지점까지의 intensity
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int g : gates) pq.add(new Pair(g, 0));
        while(!pq.isEmpty()) {
            Pair v = pq.remove();
            if(intensity[v.v] < v.w) continue;
            intensity[v.v] = v.w;
            if(type[v.v] == 2) {
                answer[1] = Math.min(answer[1], intensity[v.v]);
                answer[0] = Math.min(answer[0], v.v);
            }
            for(Pair nv : (ArrayList<Pair>) G[v.v]) {
                int max = Math.max(intensity[nv.v], v.w);
                if(intensity[nv.v] > max) {
                    intensity[nv.v] = max;
                    pq.add(new Pair(nv.v, intensity[nv.v]));
                }
            }
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new int[]{3,2,7,2}, new int[]{4,6,5,1}));
//        System.out.println(solution(new int[]{1,2,1,2}, new int[]{1,10,1,2}));
//        System.out.println(solution(new int[]{1,1}, new int[]{1,5}));

//        solution(0, 0, new int[][]{{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}});
//        solution(6, new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}}, new int[]{1,3}, new int[]{5});
        solution(7, new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}}, new int[]{1}, new int[]{2,3,4});
    }
}
