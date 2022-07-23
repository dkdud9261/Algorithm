import java.math.BigInteger;
import java.util.Arrays;

public class Test {

    public static long fact(int n) {
        long ret = 1;
        for(int i = 1; i <= n; i++) ret *= i;
        return ret;
    }

    public static int[] solution(int n, long k) {
        if(n == 1) return new int[]{1};

        int[] answer = new int[n];
        boolean[] visited = new boolean[n+1];
        int idx = 0;
        for(int i = n-1; i >= 1; i--) {
            long f = fact(i);
            long temp = Math.abs((k-1) / f) + 1;
            long cnt = 0;
            for(int j = 1; j <= n; j++) {
                if(!visited[j]) {
                    cnt++;
                    if (cnt == temp) {
                        visited[j] = true;
                        answer[idx++] = j;
                        break;
                    }
                }
            }
            k %= f;
        }
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                answer[idx++] = i;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] answer = solution(20, 123456790);
        System.out.println(Arrays.toString(answer));
    }
}
