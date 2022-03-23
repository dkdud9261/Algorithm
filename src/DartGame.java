public class DartGame {

    public static int solution(String dartResult) {
        int answer = 0;
        int[] scores = new int[3];
        int[] start = new int[3];
        int[] end = new int[4];
        int j = 0;
        for(int i = 0; i < dartResult.length(); i++) {
            if(Character.isDigit(dartResult.charAt(i))) {
                if(dartResult.charAt(i) == '1' && dartResult.charAt(i+1)=='0') {
                    scores[j] = 10;
                    start[j] = i+2;
                    end[j++] = i;
                    i++;
                }
                else{
                    scores[j] = dartResult.charAt(i) - '0';
                    start[j] = i+1;
                    end[j++] = i;
                }
            }
        }
        end[3] = dartResult.length();

        String[] games = new String[3];
        for(int i = 0; i < 3; i++) {
            games[i] = dartResult.substring(start[i], end[i+1]);
            int score = scores[i];
            switch (games[i].charAt(0)) {
                case 'S': break;
                case 'D': score *= score; break;
                case 'T': score *= (score*score); break;
            }
            if(games[i].length() > 1) {
                if(games[i].charAt(1) == '*') {
                    if(i > 0) scores[i-1] *= 2;
                    score *= 2;
                }
                else score *= -1;
            }
            scores[i] = score;
        }

        for(int i : scores) answer += i;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("1S*2T*3S"));
    }
}
