import java.util.LinkedList;
import java.util.Stack;

public class NdecimalGame {

    public static String nDecimal(int n, int k) {
        if(n == 0) return "0";
        Stack<String> stack = new Stack<>();
        while(n > 0) {
            if(n%k > 9) stack.push((char)(n%k+55)+"");
            else stack.push((n%k)+"");
            n /= k;
        }
        String str = "";
        while(!stack.isEmpty()) str += stack.pop() + "";
        return str;
    }

    public static  String solution(int n, int t, int m, int p) {
        String answer = "";
        LinkedList<Character> list = new LinkedList<>();
        for(int i = 0; i < t*m; i++) {
            String str = nDecimal(i, n);
            for(int j = 0; j < str.length(); j++) list.add(str.charAt(j));
        }
        for(int i = p-1; t > 0; i += m, t--) {
            answer += list.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        solution(2,4,2,1);
        solution(16,16,2,1);
        solution(16,16,2,2);
    }
}
