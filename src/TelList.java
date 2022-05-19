import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class TelList {

    public static boolean solution1(String[] phone_book) {
        HashMap<Character, LinkedList<String>> map = new HashMap<>();
        for(String p : phone_book) {
            char i = p.charAt(0);
            if(map.get(i) == null) {
                LinkedList<String> list = new LinkedList<>();
                list.add(p);
                map.put(i, list);
            }
            else {
                int j;
                for(j = 0; j < map.get(i).size(); j++) {
                    String pre = map.get(i).get(j);
                    if(p.length() < pre.length()) break;
                    int k;
                    for(k = 1; k < pre.length(); k++) {
                        if(pre.charAt(k) != p.charAt(k)) break;
                    }
                    if(k == pre.length()) return false;
                }
                map.get(i).add(j, p);
            }
        }
        for(char key : map.keySet()) System.out.println(key + " " + map.get(key));
        return true;
    }

    public static boolean solution2(String[] phone_book) {
        boolean[] single = new boolean[10];
        HashMap<Character, ArrayList[]> map = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            ArrayList[] lists = new ArrayList[10];
            for(int j = 0; j < 10; j++) {
                lists[j] = new ArrayList<String>();
            }
            map.put((char)(i+'0'), lists);
        }
        for(String p : phone_book) {
            char c = p.charAt(0);
            if(p.length() == 1) single[c-'0'] = true;
            else {
                if(single[c-'0']) return false;
                if(map.get(c)[p.charAt(1)-'0'].isEmpty()) {
                    map.get(c)[p.charAt(1)-'0'].add(p);
                    continue;
                }
                for(String pre : (ArrayList<String>)map.get(c)[p.charAt(1)-'0']) {
                    if(p.startsWith(pre) || pre.startsWith(p)) return false;
                }
                map.get(c)[p.charAt(1)-'0'].add(p);
            }
        }
        return true;
    }

//    public static boolean solution(String[] phone_book) {
//        char[][][][][][][][][][][][][][][][][][][][] book = new char[10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10][10];
//
//    }

    public static void main(String[] args) {
//        System.out.println(solution(new String[]{"12", "123", "1235", "567", "88"}));
//        System.out.println(solution(new String[]{"12", "1345678903", "1935", "567", "88", "5123456","010"}));
    }
}
