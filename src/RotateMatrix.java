import java.util.Arrays;

public class RotateMatrix {

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        answer = new int[queries.length];
        int[][] matrix = new int[rows+1][columns+1];
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= columns; j++) matrix[i][j] = (i-1)*columns+j;
        }
        int x1=0, x2=0, y1=0, y2=0;
        int[][] new_matrix = new int[rows+1][columns+1];
        for(int q = 0; q < queries.length; q++) {
            for(int i = 1; i <= rows; i++)System.arraycopy(matrix[i], 0, new_matrix[i], 0, new_matrix[i].length);
            x1=queries[q][0]; y1=queries[q][1]; x2=queries[q][2]; y2=queries[q][3];
            int l = (x2-x1+y2-y1)*2;
            int[] rows_i = new int[l];
            int[] cols_i = new int[l];
            int r = 0, c = 0;
            rows_i[r++] = x1;
            cols_i[c++] = y1;
            for(int i = 0; i < y2-y1; i++) {
                rows_i[r++] = rows_i[r-2];
                cols_i[c++] = cols_i[c-2]+1;
            }
            for(int i = 0; i < x2-x1; i++) {
                rows_i[r++] = rows_i[r-2]+1;
                cols_i[c++] = cols_i[c-2];
            }
            for(int i = 0; i < y2-y1; i++) {
                rows_i[r++] = rows_i[r-2];
                cols_i[c++] = cols_i[c-2]-1;
            }
            for(int i = 0; i < x2-x1-1; i++) {
                rows_i[r++] = rows_i[r-2]-1;
                cols_i[c++] = cols_i[c-2];
            }
            int[] nums = new int[l];
            for(int i = 0; i < l; i++) nums[i] = new_matrix[rows_i[i]][cols_i[i]];
            for(int i = 0; i < l; i++) {
                int new_i = i+1;
                if(new_i >= l) new_i -= l;
                new_matrix[rows_i[new_i]][cols_i[new_i]] = nums[i];
            }
            int min = 10001;
            for(int i = 1; i <= rows; i++) {
                for(int j = 1; j <= columns; j++) {
                    if(matrix[i][j] != new_matrix[i][j]) min = Math.min(min, new_matrix[i][j]);
                }
            }
            answer[q] = min;
            for(int i = 0; i <= rows; i++) System.arraycopy(new_matrix[i], 0, matrix[i], 0, matrix[i].length);
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(6,6, new int[][]{{2,2,5,4},{3,3,6,6},{5,1,6,3}})));
    }
}
