public class FineSquare {

    static int GCD(int a, int b) {  // a가 큰거
        if(a % b == 0) return b;
        return GCD(b, a % b);
    }

    public static long solution(int w, int h) {
        long answer = 1;
        if(w > h) {
            int temp = w;
            w = h;
            h = temp;
        }
        int gcd = GCD(h, w);
        int smallW = w / gcd;
        int smallH = h / gcd;
        long remove = 0;
        remove = (long)(smallW-1) * 2 + smallH-(smallW-1);
        answer = (long) w * h - (remove * gcd);
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(8,12));
        System.out.println(solution(4,3));
        System.out.println(solution(5,5));
        System.out.println(solution(1,10));
    }
}
