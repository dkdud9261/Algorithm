import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ArcheyCompetition {
    static int max = 0;
    static ArrayList<String> candidate = new ArrayList<>();

    static int scoreGap(int[] apeach, int[] lion) {
        int gap = 0;
        for(int i = 0; i <= 10; i++) {
            if(apeach[i] == 0 && lion[i] == 0) continue;
            if(apeach[i] >= lion[i]) gap -= (10-i);
            else gap += (10-i);
        }
        return gap;
    }

    static void fight(int[] apeach, int[] lion, int n, int r, int idx) {
        if(r > n) return;
        if(idx >= 10 || r == n) {
            if(r < n) lion[10] = n-r;
            int gap = scoreGap(apeach, lion);
            if (gap > max) {
                max = gap;
                candidate.clear();
                candidate.add(Arrays.toString(lion));
            } else if (gap == max) candidate.add(Arrays.toString(lion));

            if(r < n) lion[10] = 0;
            return;
        }

        // 이기는 경우
        if(r + apeach[idx] + 1 <= n) {
            lion[idx] = apeach[idx] + 1;
            fight(apeach, lion, n, r + lion[idx], idx + 1);
        }

        // 지는 경우
        lion[idx] = 0;
        fight(apeach, lion, n, r, idx+1);
    }

    public static int[] solution(int n, int[] info) {
        int[] answer = {};
        answer = new int[11];
        int[] lion = new int[11];
        fight(info, lion, n, 0, 0);

        if(candidate.size() == 1) {
            String[] strings = candidate.get(0).substring(1, candidate.get(0).length()-1).split(", ");
            for(int i = 0; i <= 10; i++) answer[i] = Integer.parseInt(strings[i]);
            return answer;
        }

        ArrayList<String> reverse = new ArrayList<>();
        for(String c : candidate) {
            reverse.add(new StringBuilder(c).reverse().toString());
        }
        if(candidate.isEmpty() || max == 0) return new int[]{-1};
        reverse.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        String answer_str = new StringBuilder(reverse.get(0)).reverse().toString();
        String[] strings = answer_str.substring(1, answer_str.length()-1).split(", ");
        for(int i = 0; i <= 10; i++) answer[i] = Integer.parseInt(strings[i]);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0})));
    }
}
