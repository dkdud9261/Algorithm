public class NumberOf124 {

    public static String solution(int n) {
        String answer = "";
        System.out.print(n + " : ");
        String[] number = {"4","1","2","4"};
//        if(n <= 3) return number[n%3];
//        else if(n == 4) return "11";
//        n--;
        while(n > 0) {
            answer += number[n%3];
            n /= 3;
        }
//        answer += ((n+2)%3);
        StringBuilder sb = new StringBuilder(answer);
        answer = sb.reverse().toString();
        System.out.println(answer);
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
