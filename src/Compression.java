import java.util.*;

public class Compression {
    static LinkedList<Character> msgList;
    static HashMap<String, Integer> dictionary;

    public static String findW() {
        String w = "";
        int i = 0;
        String temp = "";
        while(true) {
            if(i >= msgList.size()) return temp;
            temp += msgList.get(i++);
            if(dictionary.get(temp) == null) return w;
            w = temp;
        }
    }

    public static int[] solution(String msg) {
        int[] answer = {};
        int[] myAnswer = new int[1001];
        int myIdx = 0;
        msgList = new LinkedList<>();
        for(int i = 0; i < msg.length(); i++) msgList.add(msg.charAt(i));
        dictionary = new HashMap<>();
        for(int i = 1; i <= 26; i++) dictionary.put((char)(i + 64) + "", i);
        int dictionaryIndex = 27; // 할차례
        String w;
        while(true) {
            w = findW();
            myAnswer[myIdx++] = dictionary.get(w);
            for(int i = 0; i < w.length(); i++) msgList.removeFirst();
            if(!msgList.isEmpty()) {
                dictionary.put(w+msgList.getFirst(), dictionaryIndex++);
            }
            else break;
        }
        answer = Arrays.copyOf(myAnswer, myIdx);

        return answer;
    }

    public static void main(String[] args) {
        solution("ABABABABABABABAB");
    }
}
