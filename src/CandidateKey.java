import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class CandidateKey {
    /*static boolean[] visited;
    static int result;
    static ArrayList<String> keys;

    public static void combination(int[] arr, int start, int n, int r, String[][] relation) {
        if(r == 0) {
            ArrayList<String> store = new ArrayList();
            boolean unique = true;    // 유일함
            String col = "";
            for(int i = 0; i < arr.length; i++) {
                if(visited[i]) col += arr[i];
            }
            System.out.println(col);
            for(int j = 0; j < relation.length; j++) {  // 튜플 비교하면서 유일성 체크
                String tuple = "";
                int c = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (visited[i]) tuple += relation[j][i];
//                    if (++c >= n) break;
                }
                if(store.contains(tuple)) {
                    unique = false; // 하나라도 중복이면 유일성 x
                    break;
                }
                store.add(tuple);
            }
            if(unique) {   // 최소성 체크
                System.out.println("유일함");
                boolean min = true;
                if(n > 1) {
                    for (String key : keys) {
                        int contain = 0;
                        for (int i = 0; i < key.length(); i++) {
                            if (col.contains(key.charAt(i) + "")) contain++;
                        }
                        System.out.println(key + " " + contain);
                        if(contain == key.length()) {
                            min = false;
                            break;
                        }
                    }
                }
                if(min) {
                    System.out.println("최소임");
                    result++;
                    keys.add(col);
                }
            }
            System.out.println(keys);
            return;
        }
        for(int i = start; i < arr.length; i++) {
            visited[i] = true;
            combination(arr, i+1, n, r-1, relation);
            visited[i] = false;
        }
    }

    public static int solution(String[][] relation) {
        int answer = 0;
        int C = relation[0].length;
        keys = new ArrayList<>();
        int[] arr = new int[C];
        IntStream.range(0,C).forEach(i -> arr[i] = i);
        for(int i = 1; i <= C; i++) {
            visited = new boolean[C];
            combination(arr, 0, i, i, relation);
        }
        answer = result;
        return answer;
    }*/

    static ArrayList<ArrayList<Integer>> candidate = new ArrayList<>();

    public static boolean isUnique(ArrayList<Integer> columns, String[][] relation) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(String[] row : relation) {
            for(int col : columns) sb.append(row[col]).append(" ");
            String data = sb.toString();
            if(list.contains(data)) return false;
            list.add(data);
            sb.setLength(0);
        }
        return true;
    }

    public static void combination(int[] arr, int start, int r, boolean[] visited, String[][] relation) {
        if(r == 0) {
            ArrayList<Integer> columns = new ArrayList<>();
            for(int i = 0; i < arr.length; i++) {
                if(visited[i]) columns.add(arr[i]);
            }
            if(isUnique(columns, relation)) {
                boolean isMin = true;
                for(ArrayList<Integer> col : candidate) {
                    if(col.size() < columns.size()) {
                        if(columns.containsAll(col)) {
                            isMin = false;
                            break;
                        }
                    }
                }
                if(isMin) candidate.add(columns);
            }
        }
        for(int i = start; i < arr.length; i++) {
            visited[i] = true;
            combination(arr, i+1, r-1, visited, relation);
            visited[i] = false;
        }
    }

    public static int solution(String[][] relation) {
        int answer = 0;
        int n = relation[0].length;
        int[] columns = new int[n];
        for(int i = 0; i < n; i++) columns[i] = i;
        for(int i = 1; i <= n; i++) combination(columns, 0, i, new boolean[n], relation);
        for(ArrayList<Integer> a : candidate) System.out.println(a);
        answer = candidate.size();
        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new String[][]{{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}}));
        String[][] args1 = {{"a", "1", "4"},
                {"2", "1", "5"},
                {"a", "2", "4"}};
        String[][] args2 = {{"100", "r"}, {"200", "c"}, {"300", "d"}};
//        System.out.println(solution(args1));
        solution(new String[][]{{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}});

    }
}
