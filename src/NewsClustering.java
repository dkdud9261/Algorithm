import java.util.ArrayList;
import java.util.HashMap;

public class NewsClustering {

    public static int solution(String str1, String str2) {
        int answer = 0;
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        for(int i = 0; i < str1.length()-1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i+1);
            if(Character.isAlphabetic(c1) && Character.isAlphabetic(c2)) {
                String key = c1 + "" + c2;
                map1.put(key, map1.getOrDefault(key, 0)+1);
            }
        }
        for(int i = 0; i < str2.length()-1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i+1);
            if(Character.isAlphabetic(c1) && Character.isAlphabetic(c2)) {
                String key = c1 + "" + c2;
                map2.put(key, map2.getOrDefault(key, 0)+1);
            }
        }
        if(map1.isEmpty() && map2.isEmpty()) return 65536;

        int interNum = 0, unionNum = 0;
        ArrayList<String> interList = new ArrayList<>(map1.keySet());
        interList.retainAll(map2.keySet());
        for(String s : interList) {
            interNum += Math.min(map1.get(s), map2.get(s));
            unionNum += Math.max(map1.get(s), map2.get(s));
        }

        ArrayList<String> unionList = new ArrayList<>(map1.keySet());
        unionList.addAll(map2.keySet());
        unionList.removeAll(interList);
        for(String s : unionList) {
            if(map1.containsKey(s)) unionNum += map1.get(s);
            if(map2.containsKey(s)) unionNum += map2.get(s);
        }

        answer = (int)((double) interNum / unionNum * 65536);
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("FRANCE", "french"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }
}
