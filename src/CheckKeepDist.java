import java.util.Arrays;

public class CheckKeepDist {

    public static int[] solution(String[][] places) {
        int[] answer = {};
        answer = new int[5];
        int[] dr1 = {-1, 0, 1, 0};
        int[] dc1 = {0, 1, 0, -1}; // 거리가 1
        int[] dr21 = {-2, 0, 2, 0};
        int[] dc21 = {0, 2, 0, -2}; // 한 칸 띄우고 앉기
        int[] dr22 = {-1, -1, 1, 1};
        int[] dc22 = {-1, 1, 1, -1}; // 대각선에 앉기

        for(int i = 0; i < 5; i++) { // i번째 방
            System.out.println(i + " : ");
            int flag = 1;
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    if(places[i][j].charAt(k) == 'P') {
                        for(int p = 0; p < 4; p++) {
                            int nr = j + dr1[p];
                            int nc = k + dc1[p];
                            if(0 <= nr && nr < 5 && 0 <= nc && nc < 5) {
                                if(places[i][nr].charAt(nc) == 'P') {
                                    flag = 0;
                                    break;
                                }
                            }
                        }
                        if(flag == 0) break;
                        for(int p = 0; p < 4; p++) {
                            int nr = j + dr21[p];
                            int nc = k + dc21[p];
                            if(0 <= nr && nr < 5 && 0 <= nc && nc < 5) {
                                if(places[i][nr].charAt(nc) == 'P') {
                                    int pr = j + dr1[p];
                                    int pc = k + dc1[p];
                                    if(places[i][pr].charAt(pc) != 'X') {
                                        flag = 0;
                                        break;
                                    }
                                }
                            }
                        }
                        if(flag == 0) break;
                        for(int p = 0; p < 4; p++) {
                            int nr = j + dr22[p];
                            int nc = k + dc22[p];
                            if (0 <= nr && nr < 5 && 0 <= nc && nc < 5) {
                                if(places[i][nr].charAt(nc) == 'P') {
                                    if (places[i][j].charAt(nc) != 'X' || places[i][nr].charAt(k) != 'X') {
                                        flag = 0;
                                        break;
                                    }
                                }
                            }
                        }
                        if(flag == 0) break;
                    }
                }
                if(flag == 0) break;
            }
            answer[i] = flag;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, 
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, 
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}})));
    }
}
