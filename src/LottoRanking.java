import java.util.Arrays;

public class LottoRanking {
    public static void main(String[] args) {
        int[] lottos = new int[]{31, 10, 45, 1, 6, 19};
        int[] win_nums = new int[]{31, 10, 45, 1, 6, 19};
        int[] answer = solution(lottos, win_nums);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};
        answer = new int[2];
        int match = 0;
        int zero = 0, i = 0;
        for(int num : lottos) {
            if(num == 0) zero++;
            else if(Arrays.stream(win_nums).anyMatch(j -> j == num)) match++;
        }
        answer[0] = getRanking(match + zero);
        answer[1] = getRanking(match);

        return answer;
    }

    public static int getRanking(int num) {
        switch (num) {
            case 6: return 1;
            case 5: return 2;
            case 4: return 3;
            case 3: return 4;
            case 2: return 5;
            default: return 6;
        }
    }
}
