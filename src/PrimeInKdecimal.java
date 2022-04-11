import java.util.Arrays;
import java.util.Stack;

public class PrimeInKdecimal {

    public static boolean isPrime(long n) {
        for(long i = 2; i < Math.sqrt(n); i++) {
            if(n%i == 0) return false;
        }
        return true;
    }

    public static int solution(int n, int k) {
        int answer = -1;
        answer++;
        Stack<String> stack = new Stack<>();
        String kDecimal = "";
        while(n > 0) {
            stack.push(n%k+"");
            n /= k;
        }
        while(!stack.isEmpty()) kDecimal += stack.pop();
        String[] numbers = kDecimal.split("0");
        for(String num : numbers) {
            if(!num.equals("") && !num.equals("1")) {
                if (isPrime(Long.parseLong(num))) answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution(437674, 3));
//        System.out.println(solution(110011, 10));
        System.out.println(solution(0, 10));
//        String str = "000";
//        System.out.println(Arrays.toString(str.split("0")));
    }
}
