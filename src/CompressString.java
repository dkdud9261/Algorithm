import java.util.Arrays;

public class CompressString {

    public static int solution(String s) {
        int answer = 0;
        answer = s.length();
        String[] sub_str = new String[s.length()];
        int[] sub_cnt = new int[s.length()];

        for (int l = 1; l <= s.length()/2; l++) {
            int i = 0, j = 0,  k = 0;
            for(i = 0; i+l <= s.length(); i = k) {
                int cnt = 1;
                String sub = s.substring(i, i + l);
                for(k = i+l; k+l <= s.length(); k += l) {
                    if(s.substring(k, k+l).equals(sub)) cnt++;
                    else break;
                }
                sub_str[j] = sub;
                sub_cnt[j++] = cnt;
            }
            int len = l * j;
            for(int m = 0; m < j; m++) {
                if(sub_cnt[m] > 1) len += Integer.toString(sub_cnt[m]).length();
            }
            if(i < s.length()) len += (s.length()-i);
            answer = Math.min(answer, len);

            Arrays.fill(sub_str, "0");
            Arrays.fill(sub_cnt, 0);
        }

        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution("aabbaccc") + "\n");
//        System.out.println(solution("ababcdcdababcdcd") + "\n");
        System.out.println(solution("abcabcdede") + "\n");
//        System.out.println(solution("abcabcabcabcdededededede") + "\n");
//        System.out.println(solution("xababcdcdababcdcd") + "\n");
//        System.out.println(solution("ababa") + "\n");
//        System.out.println(solution("") + "\n");
        System.out.println(solution("aaaaaaaaaabbbbbbbbbb") + "\n");
    }
}
