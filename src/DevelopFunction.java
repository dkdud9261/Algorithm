import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DevelopFunction {

    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int l = progresses.length;
        answer = new int[l];
        int[] days = new int[l];
        for(int i = 0; i < l; i++) {
            double d = (double) (100 - progresses[i]) / speeds[i];
            days[i] = (int)Math.ceil(d);
        }
        int j, k = 0;
        for(int i = 0; i < l; i = j) {
            for(j = i+1; j < l && days[i] >= days[j]; j++);
            answer[k++] = (j - i);
        }
        answer = Arrays.copyOf(answer, k);
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static void main(String[] args) {
        solution(new int[]{93,30,5}, new int[]{1,30,5});
        solution(new int[]{95,90,99,99,80,99}, new int[]{1,1,1,1,1,1});
        solution(new int[]{95, 95}, new int[]{1,1});
    }
}
