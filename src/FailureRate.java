import java.util.Map.*;
import java.util.*;

public class FailureRate {

    public static int[] solution(int N, int[] stages) {
        int[] answer = {};
        answer = new int[N];
        int[] challenge = new int[N+1];
        int[] fail = new int[N+1];

        for(int s : stages) {
            for(int i = 1; i <= s && i <= N; i++) challenge[i]++;
            if(s <= N) fail[s]++;
        }
        HashMap<Integer, Double> rate = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            if(challenge[i] == 0) rate.put(i, 0.0);
            else rate.put(i, (double) fail[i]/challenge[i]);
        }
        List<Entry<Integer, Double>> rateList = new ArrayList<>(rate.entrySet());
        rateList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        int j = 0;
        for(Entry<Integer, Double> e : rateList) {
            int k = (int)e.getKey();
            if(k != 0) answer[j++] = k;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{2,1,2,6,2,4,3,3})));
        System.out.println(Arrays.toString(solution(4, new int[]{1,1,1,1,1})));
    }
}
