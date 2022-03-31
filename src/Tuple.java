import java.util.*;

public class Tuple {

    public static int[] solution(String s) {
        int[] answer = {};
        ArrayList[] list = new ArrayList[501];
        ArrayList<Integer> term = new ArrayList<>();
        int i=0, l = 0;
        for(i = 1; i < s.length()-1; i++) {
            char c = s.charAt(i);
            if(c == '{') {
                while(s.charAt(i) != '}') {
                    String number = "";
                    i++;
                    while(s.charAt(i) != ',' && s.charAt(i) != '}') {
                        number += s.charAt(i++);
                    }
                    term.add(Integer.parseInt(number));
                }
                list[term.size()] = term;
                l = Math.max(l, term.size());
                term = new ArrayList<>();
            }
        }
        answer = new int[l];
        int idx = 0;
        answer[idx++] = (int)list[1].get(0);
        for(int j = 2; j <= l; j++) {
            list[j].removeAll(list[j-1]);
            answer[idx++] = (int)list[j].get(0);
            list[j].addAll(list[j-1]);
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static void main(String[] args) {
        solution("{{1,2,3},{2,1},{1,2,4,3},{2}}");
    }
}
