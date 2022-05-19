import java.util.HashMap;

public class NotFinishAthletes {

    public static String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String p : participant) map.put(p, map.getOrDefault(p, 0) + 1);
        for(String c : completion) {
            int n = map.get(c);
            if(n == 1) map.remove(c);
            else map.put(c, map.get(c) - 1);
        }
        return map.keySet().iterator().next();
    }

    public static void main(String[] args) {
        String s = solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"});
        System.out.println(s);
    }
}
