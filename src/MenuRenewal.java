import java.util.*;

public class MenuRenewal {
    static boolean[] visited;
    static HashMap<String, Integer> set_menu;
    static int max;

    public static void combination(String str, String sub, int start, int n, int r) {
        if(r == 0) {
            set_menu.merge(sub, 1, (v1, v2) -> v1 + v2);
            max = Math.max(max, set_menu.get(sub));
            return;
        }
        for(int i = start; i < n; i++) {
            visited[i] = true;
            combination(str, sub+str.charAt(i), i+1, n, r-1);
            visited[i] = false;
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        set_menu = new HashMap<>();
        visited = new boolean[10];
        LinkedList<String> courses = new LinkedList<>();
        for(int c : course) {
            set_menu = new HashMap<>();
            max = 0;
            for(String order : orders) {
                char[] strarr = order.toCharArray();
                Arrays.sort(strarr);
                order = new String(strarr);
                Arrays.fill(visited, false);
                combination(order, "",0, order.length(), c);
            }
            if(max >= 2) {
                for (String key : set_menu.keySet()) {
                    if (set_menu.get(key) == max) courses.add(key);
                }
            }
        }
        Collections.sort(courses);
        answer = courses.toArray(new String[0]);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2,3,4})));
    }
}
