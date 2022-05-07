import java.util.Arrays;

public class CompressString {

    /*public static int solution(String s) {
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
    }*/

    public static int solution(String s) {
        int answer = 0;
        int len = s.length();
        int min = len;
        for(int l = len/2; l >= 1; l--) {
            StringBuilder sb1 = new StringBuilder();
            int i;
            for(i = 0; i+l <= len;) { // 단위 시작 인덱스
                StringBuilder sb = new StringBuilder();
                for(int j = i; j < i+l; j++) sb.append(s.charAt(j));
                String sub = sb.toString(); // 단위
                int cnt = 1;
                int j;
                for(j = i+l; j+l <= len; j+=l) {
                    if(s.substring(j, j+l).equals(sub)) cnt++;
                    else break;
                }
                if(cnt > 1) sb1.append(cnt);
                sb1.append(sub);
                i = j;
            }
            if(i < len) sb1.append(s.substring(i));
            min = Math.min(min, sb1.length());
        }
        answer = min;
        return answer;
    }

    public static void main(String[] args) {
        solution("aabbaccc");
        solution("ababcdcdababcdcd");
        solution("abcabcdede");
        solution("abcabcabcabcdededededede");
        solution("xababcdcdababcdcd");    // 문제
    }
}
