import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class EditChart {

    public static String solution1(int n, int k, String[] cmd) {
        String answer = "";
        LinkedList<Integer> chart = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) chart.add(i);
        for(String c : cmd) {
            switch (c.charAt(0)) {
                case 'C': {
                    int remove = chart.get(k);
                    stack.push(remove);
                    chart.remove(k);
                    if (k >= chart.size()) k--;
                    break;
                }
                case 'U': {
                    k -= Integer.parseInt(c.substring(2));
                    break;
                }
                case 'D' : {
                    k += Integer.parseInt(c.substring(2));
                    break;
                }
                case 'Z' : {
                    int selected = chart.get(k);
                    int re = stack.pop();
                    int i;
                    for(i = re-1; !chart.contains(i) && i >= 0; i--);
                    if(i < 0) {
                        chart.add(0, re);
                        k++;
                    }
                    else {
                        chart.add(chart.indexOf(i)+1, re);
                        if(selected > chart.get(k)) k++;
                    }
                    break;
                }
            }
            System.out.println(chart + " ==> " + k + " " + stack);
        }
        boolean[] check = new boolean[n];
        for(int i : chart) check[i] = true;
        for(boolean b : check) {
            if(b) answer += "O";
            else answer += "X";
        }
        System.out.println(answer);
        return answer;
    }

    public static String solution(int n, int k, String[] cmd) {
        String answer = "";
        boolean[] chart = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) chart[i] = true;
        for(String c : cmd) {
            int cnt;
            switch (c.charAt(0)) {
                case 'D': {
                    int i = c.indexOf(" ");
                    cnt = Integer.parseInt(c.substring(i+1));
                    for(; cnt > 0; k++) {
                        if(chart[k]) cnt--;
                    }
                    break;
                }
                case 'U': {
                    int i = c.indexOf(" ");
                    cnt = Integer.parseInt(c.substring(i+1));
                    for(; cnt > 0; k--) {
                        if(chart[k]) cnt--;
                    }
                    break;
                }
                case 'C': {
                    chart[k] = false;
                    stack.push(k);
                    if(k == n-1) {
                        while(!chart[k]) k--;
                    }
                    else {
                        while(!chart[k]) k++;
                    }
                    break;
                }
                case 'Z': {
                    chart[stack.pop()] = true;
                    break;
                }
            }
            System.out.println(Arrays.toString(chart) + " ==> " + k + " " + stack);
        }

        for(boolean b : chart) {
            if(b) answer += "O";
            else answer += "X";
        }
        System.out.println(answer);

        return answer;
    }

    public static void main(String[] args) {
        solution1(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"});
    }
}
