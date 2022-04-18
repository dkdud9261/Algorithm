import java.util.Arrays;

public class LockAndKey {

    public static int[][] rotate(int[][] key) {
        int n = key.length;
        int[][] answer = new int[n][n];

        // 1. 대각선 반전
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                answer[i][j] = key[j][i];
                answer[j][i] = key[i][j];
            }
        }
        // 2. 좌우반전
        int l;
        if(n%2 == 0) l = n/2-1;
        else l = n/2;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= n/2-1; j++) {
                int temp = answer[i][j];
                answer[i][j] = answer[i][n-j-1];
                answer[i][n-j-1] = temp;
            }
        }

        return answer;
    }

    public static boolean check(int[][] lock) {
        int n = lock.length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(lock[i][j] != 1) return false;
            }
        }
        return true;
    }

    // 문제 1 : key 밖으로 나간 값 유지가 안댐 => border를 키워야함
    // 문제 2 : 안됐으면 원상복귀 시켜야함
    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        int m = key.length;
        int n = lock.length;
        int[][] temp1 = new int[m][m];
        int[][] temp2 = new int[n][n];
        for(int i = 0; i < 4; i++) {    // 회전
            key = rotate(key);
            for(int a = 0; a < m; a++) System.arraycopy(key[a], 0, temp1[a], 0, m);
            for(int j = -1*(m-1); j < n; j++) {
                for(int k = -1*(m-1); k < n; k++) {     // 이동
                    for(int a = 0; a < n; a++) System.arraycopy(lock[a], 0, temp2[a], 0, n);
                    for(int a = j; a < j+m; a++) {
                        for(int b = k; b < k+m; b++) {
                            if(0 <= a && a < n && 0 <= b && b < n) lock[a][b] += key[a-j][b-k];
                        }
                    }
                    if(check(lock)) return true;
                    for(int a = 0; a < n; a++) System.arraycopy(temp2[a], 0, lock[a], 0, n);
                }
            }
            for(int a = 0; a < m; a++) System.arraycopy(temp1[a], 0, key[a], 0, m);
        }
        return answer;
    }

    public static void main(String[] args) {

//        boolean a = solution(new int[][]{{0,0,0},{1,0,0},{0,1,1}}, new int[][]{{1,1,1},{1,1,0},{1,0,1}});
//        System.out.println(a);

        int[][] arr = {{1,2},{3,4}};
        for(int i = 0; i < 4; i++) {
            arr = rotate(arr);
            for(int j = 0; j < 2; j++) System.out.println(Arrays.toString(arr[j]));
            System.out.println();
        }
    }
}
