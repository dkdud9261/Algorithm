import java.util.Stack;

public class ClawCraneGame {

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> basket = new Stack<>();
        for(int m : moves) {
            m--;
            int i;
            for(i = 0; i < board.length && board[i][m]==0; i++);
            if(i < board.length) {
                if(basket.isEmpty() || basket.peek() != board[i][m]) basket.push(board[i][m]);
                else {
                    basket.pop();
                    answer += 2;
                }
                board[i][m] = 0;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}}, new int[]{1, 5, 3, 5, 1, 2, 1, 4}));
    }
}
