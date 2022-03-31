import java.util.Arrays;
import java.util.LinkedList;

public class MaximizeExpression {
    static boolean[] visited = {false,false,false};
    static Character[] operations = {'+', '-', '*'};
    static LinkedList<String> exList;
    static long max = 0;

    public static void permutation(char[] sub, int n, int r) {
        if(r == n) {
            LinkedList<String> temp = (LinkedList) exList.clone();
            LinkedList<String> result = new LinkedList<>();
            for(char cal : sub) {
                result.add(temp.get(0));
                for (int i = 1; i < temp.size(); i++) {
                    String term = temp.get(i);
                    if (term.equals(cal + "")) {
                        long num1 = Long.parseLong(result.getLast());
                        long num2 = Long.parseLong(temp.get(++i));
                        long res = switch (cal) {
                            case '+' -> num1 + num2;
                            case '-' -> num1 - num2;
                            case '*' -> num1 * num2;
                            default -> 0;
                        };
                        result.removeLast();
                        result.add(res + "");
                    }
                    else result.add(term);
                }
                temp = (LinkedList<String>) result.clone();
                result.clear();
            }
            max = Math.max(max, Math.abs(Long.parseLong(temp.get(0))));
            return;
        }
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                sub[r] = operations[i];
                permutation(sub, n, r+1);
                visited[i] = false;
            }
        }
    }

    public static long solution(String expression) {
        long answer = 0;
        exList = new LinkedList<>();
        int start = 0;
        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if(Arrays.asList(operations).contains(c)) {
                exList.add(expression.substring(start, i));
                exList.add(c+"");
                start = i+1;
            }
        }
        exList.add(expression.substring(start));
        permutation(new char[3], 3, 0);
        answer = max;
        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution("100-200*300-500+20"));
        System.out.println(solution("177-661*999*99-133+221+334+555-166-144-551-166*166-166*166-133*88*55-11*4+55*888*454*12+11-66+444*99"));
    }
}
