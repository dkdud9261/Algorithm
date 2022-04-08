import java.util.Stack;

public class NumberOf124 {

    public static String solution(int n) {
        String answer = "";
        Stack<Integer> stack = new Stack<>();
        boolean zero = false;
        while(n > 0) {
            int r = n%3;
            if(!zero) {
                if(r == 0) {
                    stack.push(4);
                    zero = true;
                }
                else stack.push(r);
            }
            else {
                switch (r) {
                    case 0: stack.push(2);break;
                    case 1: stack.push(4);break;
                    default:{
                        zero = false;
                        stack.push(r-1);
                    }
                }
            }
            n /= 3;
        }
        while(!stack.isEmpty()) answer += stack.pop();
        if(answer.startsWith("4")) answer = answer.substring(1);
        return answer;
    }

    public static void main(String[] args) {
        solution(1);
        solution(2);
        solution(3);
        solution(4);
        solution(5);
        solution(6);
        solution(7);
        solution(8);
        solution(9);
        solution(10);
        solution(11);
        solution(12);
        solution(13);
        solution(14);
        solution(15);
        solution(16);
        solution(17);
        solution(18);
        solution(19);
        solution(20);
        solution(21);
        solution(22);
    }
}
