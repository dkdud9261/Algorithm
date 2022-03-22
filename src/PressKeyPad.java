public class PressKeyPad {

    public static String solution(int[] numbers, String hand) {
        String answer = "";
        int curr_left = 10, curr_right = 12;
        int[] rows = new int[]{3,0,0,0,1,1,1,2,2,2,3,-1,3};
        int[] cols = new int[]{1,0,1,2,0,1,2,0,1,2,0,-1,2};

        for(int n : numbers) {
            if(n == 1 || n == 4 || n == 7) {
                answer += "L";
                curr_left = n;
            }
            else if(n==3 || n == 6 || n == 9) {
                answer += "R";
                curr_right = n;
            }
            else {
                double temp = Math.abs(rows[n]-rows[curr_left]) + Math.abs(cols[n]-cols[curr_left]) - Math.abs(rows[n]-rows[curr_right]) - Math.abs(cols[n]-cols[curr_right]);
                if(temp > 0) {
                    answer += "R";
                    curr_right = n;
                }
                else if(temp < 0) {
                    answer += "L";
                    curr_left = n;
                }
                else {
                    if(hand.equals("left")) {
                        answer += "L";
                        curr_left = n;
                    }
                    else {
                        answer += "R";
                        curr_right = n;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,3,4,5,8,2,1,4,5,9,5}, "right"));
        System.out.println(solution(new int[]{2,5,8,0,2,5,8,0}, "right"));
    }
}
