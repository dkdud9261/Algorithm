import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Blind2022 {

    public static int[] solution1(String[] id_list, String[] report, int k) {
        int[] answer = {};
        answer = new int[id_list.length];
        HashMap<String, Integer> id_index = new HashMap<>();
        for(int i = 0; i < id_list.length; i++) id_index.put(id_list[i], i);
        HashSet<String> newReport = new HashSet<>(Arrays.asList(report));
        HashMap<String, Integer> reported = new HashMap<>();
        for(String r : newReport) {
            String a_reported = r.split(" ")[1];
            reported.put(a_reported, reported.getOrDefault(a_reported,0)+1);
        }
        ArrayList<String> stop = new ArrayList<>();
        for(String key : reported.keySet()) {
            if(reported.get(key) >= k) stop.add(key);
        }
        for(String r : newReport) {
            String[] ids = r.split(" ");
            if(stop.contains(ids[1])) {
                answer[id_index.get(ids[0])]++;
            }
        }
        return answer;
    }

    public static boolean is_prime(long number) {
        if(number < 2) return false;
        if(number == 2) return true;
        for(long i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0) return false;
        }
        return true;
    }

    public static int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(n%k);
            n /= k;
        }
        String s = sb.reverse().toString();
        sb.setLength(0);
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '0') {
                if(sb.length() >= 1) {
                    if(is_prime(Long.parseLong(sb.toString()))) answer++;
                    sb.setLength(0);
                }
            }
            else sb.append(s.charAt(i));
        }
        if(sb.length() >= 1 && is_prime(Long.parseLong(sb.toString()))) answer++;
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        solution(00000, 3);
    }
}
