import java.util.*;

public class MoreSpicy {

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < scoville.length; i++) pq.add(scoville[i]);
        while(true) {
            if(pq.size() == 1 && pq.peek() < K) return -1;
            boolean done = true;
            for(int s : pq) {
                if(s < K) {
                    done = false;
                    break;
                }
            }
            if(done) break;

            int mix = pq.remove() + (pq.remove() * 2);
            pq.add(mix);
            answer++;
        }
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
//        solution(new int[]{1,2,3,9,10,12}, 7);
        solution(new int[]{1,1,1,1,1}, 20);
    }
}
