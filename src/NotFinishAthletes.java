import java.util.HashMap;

public class NotFinishAthletes {

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
//        HashMap<String, Integer>
        for(String p : participant) map.put(p, 0);
        for(String c : completion) map.put(c, map.get(c)+1);
        for(String key : map.keySet()) {
            if(map.get(key) == 0) {
                answer = key;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {

    }
}
