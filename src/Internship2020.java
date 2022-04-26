import java.util.*;

public class Internship2020 {

    static LinkedList<String> list = new LinkedList<>();
    static LinkedList<String> backup = new LinkedList<>();
//    static Long max = 0L;
    static boolean[] visited = new boolean[3];

    public static void calculate(String oper) {
        System.out.println(oper);
        int i = 0;
        while(list.contains(oper)){
            System.out.println(list);
            if(list.get(i).equals(oper)) {
                long a = Long.parseLong(list.get(i-1));
                long b = Long.parseLong(list.get(i+1));
                list.remove(i-1);
                list.remove(i-1);
                list.remove(i-1);
                switch (oper) {
                    case "+" -> list.add(i - 1, (a + b) + "");
                    case "-" -> list.add(i - 1, (a - b) + "");
                    case "*" -> list.add(i - 1, (a * b) + "");
                }
            }
            else i++;
        }
        System.out.println();
    }

//    public static void permutation(String str, String sub, int n, int r) {
//        if(r == n) {
//            System.out.println(sub);
//            for(int i = 0; i < 3; i++) {
//                calculate(sub.charAt(i)+"");
//            }
////            if(list.size()>1) calculate(sub.charAt(2)+"");
//            max = Math.max(max, Math.abs(Long.parseLong(list.getFirst())));
//            list.clear();
//            list.addAll(backup);
//            return;
//        }
//        for(int i = 0; i < n; i++) {
//            if(!visited[i]) {
//                visited[i] = true;
//                permutation(str, sub + str.charAt(i), n, r+1);
//                visited[i] = false;
//            }
//        }
//    }
//
//    public static long solution1(String expression) {
//        long answer = 0;
//        String operation = "-*+";
//        int start = 0;
//        for(int i = 0; i < expression.length(); i++) {
//            if(operation.contains(expression.charAt(i)+"")) {
//                list.add(expression.substring(start,i));
//                backup.add(expression.substring(start,i));
//                list.add(expression.charAt(i)+"");
//                backup.add(expression.charAt(i)+"");
//                start = i+1;
//            }
//        }
//        list.add(expression.substring(start));
//        backup.add(expression.substring(start));
//        permutation(operation, "", 3, 0);
//        answer = max;
//        System.out.println(answer);
//        return answer;
//    }

    public static String solution1(int[] numbers, String hand) {
        String answer = "";
        int lr = 3, lc = 0, rr = 3, rc = 2;
        for(int num : numbers) {
            int r = num % 3 == 0 ? num / 3 - 1 : num / 3;
            int c = num % 3 == 0 ? 2 : num % 3 - 1;
            if(num == 0) {
                r = 3;
                c = 1;
            }
            System.out.println(r + " " + c);
            if(c == 0) {
                lr = r;
                lc = c;
                answer += "L";
            }
            else if(c == 2) {
                rr = r;
                rc = c;
                answer += "R";
            }
            else {
                int llen = Math.abs(lr - r) + (c-lc);
                int rlen = Math.abs(rr - r) + (rc-c);
                System.out.println(llen + ", " + rlen);
                if(llen > rlen) {   // 오른손
                    rr = r;
                    rc = c;
                    answer += "R";
                }
                else if(llen < rlen) {
                    lr = r;
                    lc = c;
                    answer += "L";
                }
                else {
                    if(hand.equals("right")) {
                        rr = r;
                        rc = c;
                        answer += "R";
                    }
                    else {
                        lr = r;
                        lc = c;
                        answer += "L";
                    }
                }
            }
            System.out.println(lr + " " + lc);
            System.out.println(rr + " " + rc + "\n");
        }
        System.out.println(answer);
        return answer;
    }

    public static int[] solution(String[] gems) {
        int[] answer = {};
        answer = new int[2];
        HashMap<String, Integer> map = new HashMap<>();
        for(String g : gems) map.put(g, 0);
        int size = map.size();
        if(size == 1) return new int[]{1,1};
        int[] minMax = new int[gems.length+1];
        int min_dist = Integer.MAX_VALUE;
        map.clear();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= gems.length; i++) {
            System.out.println(Arrays.toString(minMax));
            if(min == map.getOrDefault(gems[i-1], min)) min = i;
            map.put(gems[i-1], i);
            max = Math.max(max, i);
            System.out.println(min + " " + max);
            if(map.size() == size) {
                if(minMax[min] == 0) minMax[min] = max;
                else {
                    if(max - min < minMax[min] - min) minMax[min] = max;
                }
            }
        }
        int min1 = Integer.MAX_VALUE;
        for(int key = 1; key <= gems.length; key++) {
            if(minMax[key] != 0) {
                if (minMax[key] - key < min_dist) {
                    min1 = key;
                    min_dist = minMax[key] - key;
                }
            }
        }
        answer[0] = min1;
        answer[1] = minMax[min1];
        System.out.println(Arrays.toString(answer));
        return answer;
    }

//    public static void search(int[][] board, int[][] price, char[][] direct, boolean[][] visited, int r, int c) {
//        visited[r][c] = true;
//        int[] dr = {-1,0, 1, 0};
//        int[] dc = {0, 1, 0, -1};
//        for(int i = 0; i < 4; i++) {
//            int nr = r + dr[i];
//            int nc = c + dc[i];
//            if(0 <= nr && nr < board.length && 0 <= nc && nc < board.length) {
//                if(!visited[nr][nc] && board[nr][nc] != 1) {
//
//                }
//            }
//        }
//    }

    public static int solution1(int[][] board) {
        int answer = 0;
        int n = board.length;
        int[][] price = new int[n][n];
        char[][] direct = new char[n][n];
        direct[0][0] = '.';
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if((i == 0 && j == 0) || board[i][j] == 1) continue;
                int lprice = 0, uprice = 0;
                // 왼쪽에서 왔을 경우
                if(j-1 >= 0 && board[i][j-1] != 1) {
                    if(direct[i][j-1] == '.' || direct[i][j-1] == '-') lprice = 100;
                    else if(direct[i][j-1] == '+') {
                        lprice = 100;
                        direct[i][j-1] = '-';
                    }
                    else lprice = 600;
                }
                // 위에서 왔을 경우
                if(i-1 >= 0 && board[i-1][j] != 1) {
                    if(direct[i-1][j] == '.' || direct[i-1][j] == '|') uprice = 100;
                    else if(direct[i-1][j] == '+') {
                        lprice = 100;
                        direct[i-1][j] = '|';
                    }
                    else uprice = 600;
                }
                System.out.println(i + "," + j + ": " + lprice + " " + uprice);
                if(lprice == 0) {
                    if(uprice != 0) {
                        price[i][j] = price[i-1][j] + uprice;
                        direct[i][j] = '|';
                    }
                }
                else if(uprice == 0) {
                    price[i][j] = price[i][j-1]+lprice;
                    direct[i][j] = '-';
                }
                else {
                    if(lprice < uprice) {
                        price[i][j] = price[i][j-1]+lprice;
                        direct[i][j] = '-';
                    }
                    else if(lprice > uprice) {
                        price[i][j] = price[i-1][j] + uprice;
                        direct[i][j] = '|';
                    }
                    else {
                        price[i][j] = Math.min(price[i][j-1],price[i-1][j]) + uprice;
                        direct[i][j] = '+';
                    }
                }
            }
        }
        for(int i = 0; i < n; i++) System.out.println(Arrays.toString(price[i]));
        for(int i = 0; i < n; i++) System.out.println(Arrays.toString(direct[i]));
        return answer;
    }

    public static void main(String[] args) {
//        solution(new int[][]{{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1}, {0,1,0,0,0,1},{0,0,0,0,0,0}});
        solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
    }
}
