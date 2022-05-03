import java.util.*;

public class Internship2021 {

    static class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int search(String[][] places, int num) {
        int[] dr = {-1, -1, 1, 1};
        int[] dc = {-1, 1, 1, -1};
        int[] dr1 = {-2, 0, 2, 0};
        int[] dc1 = {0, 2, 0, -2};
        int[] dr2 = {-1, 0, 1, 0};
        int[] dc2 = {0, 1, 0, -1};
        Queue<Pos> queue = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (places[num][i].charAt(j) == 'P') {
                    queue.add(new Pos(i, j));
                }
            }
        }
        while (!queue.isEmpty()) {
            Pos curr = queue.remove();
            int r = curr.r;
            int c = curr.c;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nr1 = r + dr1[i];
                int nc1 = c + dc1[i];
                int nr2 = r + dr2[i];
                int nc2 = c + dc2[i];
                if (0 <= nr && nr < 5 && 0 <= nc && nc < 5) {
                    if (places[num][nr].charAt(nc) == 'P' && (places[num][nr].charAt(c) != 'X' || places[num][r].charAt(nc) != 'X'))
                        return 0;
                }
                if (0 <= nr1 && nr1 < 5 && 0 <= nc1 && nc1 < 5) {
                    if (places[num][nr1].charAt(nc1) == 'P' && places[num][r + (dr1[i] / 2)].charAt(c + (dc1[i] / 2)) != 'X')
                        return 0;
                }
                if(0 <= nr2 && nr2 < 5 && 0 <= nc2 && nc2 < 5) {
                    if(places[num][nr2].charAt(nc2) == 'P') return 0;
                }
            }
        }
        return 1;
    }

    public static int[] solution2(String[][] places) {
        int[] answer = {};
        answer = new int[5];
        for(int num = 0; num < 5; num++) {
            answer[num] = search(places, num);
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static int solution1(String s) {
        int answer = 0;
        s = s.replaceAll("zero", "0");
        s = s.replaceAll("one", "1");
        s = s.replaceAll("two", "2");
        s = s.replaceAll("three", "3");
        s = s.replaceAll("four", "4");
        s = s.replaceAll("five", "5");
        s = s.replaceAll("six", "6");
        s = s.replaceAll("seven", "7");
        s = s.replaceAll("eight", "8");
        s = s.replaceAll("nine", "9");
        answer = Integer.parseInt(s);
        return answer;
    }

    public static String solution3(int n, int k, String[] cmd) {
        String answer = "";
        LinkedList<Character> chart = new LinkedList<>();
        Stack<Character> remove = new Stack<>();
        boolean[] exist = new boolean[n];
        for(int i = 0; i < n; i++) {
            chart.add((char)(i+65));
            exist[i] = true;
        }
        for(String c : cmd) {
            // D X, U X
            if(c.length() > 1) {
                int x = Integer.parseInt(c.substring(2));
                if(c.charAt(0) == 'D') k += x;
                else k -= x;
            }
            // C, Z
            else {
                if(c.charAt(0) == 'C') {
                    char rc = chart.get(k);
                    remove.add(rc);
                    exist[rc-65] = false;
                    chart.remove(k);
                    if(k == chart.size()) k--;
                }
                else {
                    char temp = chart.get(k);
                    char re = remove.pop();
                    if(re == 'A') {
                        exist[0] = true;
                        chart.addFirst('A');
                    }
                    else {
                        exist[re - 65] = true;
                        int i;
                        for (i = re - 66; i > 0; i--) {
                            if(exist[i]) break;
                        }
                        if(exist[i]) {
                            int ri = chart.indexOf((char) (i + 65));
                            chart.add(ri + 1, re);
                        }
                        else chart.addFirst(re);
                    }
                    k = chart.indexOf(temp);
                }
            }
            System.out.println(chart + " k : " + chart.get(k));
        }
        for(boolean b : exist) {
            if(b) answer += "O";
            else answer += "X";
        }
        return answer;
    }

    static class Vertex {
        int nroom, sum;
        public Vertex(int nroom, int sum) {
            this.nroom = nroom;
            this.sum = sum;
        }
    }

    public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        int[][] map = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) Arrays.fill(map[i], Integer.MAX_VALUE);
        for(int i = 0; i < roads.length; i++) {
            map[roads[i][0]][roads[i][1]] = Math.min(map[roads[i][0]][roads[i][1]],roads[i][2]);
            map[roads[i][1]][roads[i][0]] = -1;
        }
        int result = Integer.MAX_VALUE;

        boolean[] visited = new boolean[n+1];
        HashMap<Integer, boolean[]> trapStates = new HashMap<>();
        boolean[] trapState = new boolean[11];
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(new Vertex(start, 0));
        while(!queue.isEmpty()) {
            Vertex v = queue.remove();
            System.out.println(v.nroom + " " + Arrays.toString(trapState));
            if(v.nroom == end) {
                result = Math.min(result, v.sum);
                break;
            }
            if(trapStates.get(v.nroom) != null) {
                if(Arrays.equals(trapState, trapStates.get(v.nroom))) {
                    visited[v.nroom] = true;
                    continue;
                }
            }
            if(Arrays.binarySearch(traps, v.nroom) >= 0) {
                for(int i = 1; i <= n; i++) {
                    if(map[v.nroom][i] != Integer.MAX_VALUE) {
                        int temp = map[v.nroom][i];
                        map[v.nroom][i] = map[i][v.nroom];
                        map[i][v.nroom] = temp;
                    }
                }
                trapState[v.nroom] = !trapState[v.nroom];
            }
            trapStates.put(v.nroom, Arrays.copyOf(trapState, 11));
            for(int i = 1; i <= n; i++) {
                if(i != v.nroom && !visited[i]) {
                    if(map[v.nroom][i] > 0 && map[v.nroom][i] < Integer.MAX_VALUE) {
                        queue.add(new Vertex(i, v.sum+map[v.nroom][i]));
                    }
                }
            }
        }
        answer = result;
        return answer;
    }

    public static void main(String[] args) {
        solution(4,1,4,new int[][]{{1,2,1},{3,2,1}, {2,4,1}}, new int[]{2,3});
    }
}
