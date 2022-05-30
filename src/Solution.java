import java.util.*;

class Solution
{
    public static int solution(String s)
    {
        int[] x = new int[26];
        for(int i = 0; i < s.length(); i++) x[(int)s.charAt(i)-'a']++;
        for(int i = 0; i < 26; i++) {
            if(x[i]%2 != 0) return 0;
        }
        String[] xx = new String[26];
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for(int i = 'a'; i <= 'z'; i++) {
            sb.append((char)i).append((char)i);
            xx[j++] = sb.toString();
            sb.setLength(0);
        }
        while(true) {
            String temp = s;
            for(int i = 0; i < 26; i++) {
                if(x[i-'a'] == 0) continue;
                s = s.replaceAll(xx[i], "");
            }
            if(s.equals("")) return 1;
            if(temp.equals(s)) return 0;
        }
    }

    public static void main(String[] args) {
        solution("baabaa");
    }
}