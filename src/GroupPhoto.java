import java.util.Arrays;

public class GroupPhoto {

    static char[] friends = {'A','C','F','J','M','N','R','T'};
    static boolean[] visited;
    static String[] needs;
    static int cnt;

    public static void permutation(Character[] output, int i) {
        if(i == 8) {
            for(String n : needs) {
                int i1 = Arrays.asList(output).indexOf(n.charAt(0));
                int i2 = Arrays.asList(output).indexOf(n.charAt(2));
                int d = n.charAt(4)-'0';
                switch (n.charAt(3)) {
                    case '=':
                        if(Math.abs(i1-i2) != (d+1)) return;
                        break;
                    case '>':
                        if(Math.abs(i1-i2) <= (d+1)) return;
                        break;
                    case '<':
                        if(Math.abs(i1-i2) >= (d+1)) return;
                        break;
                }
            }
            cnt++;
            return;
        }
        for(int j = 0; j < 8; j++) {
            if(!visited[j]) {
                visited[j] = true;
                output[i] = friends[j];
                permutation(output, i+1);
                visited[j] = false;
            }
        }
    }

    public static int solution(int n, String[] data) {
        int answer = 0;
        visited = new boolean[8];
        needs = data.clone();
        cnt = 0;
        permutation(new Character[8], 0);
        answer = cnt;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, new String[]{"N~F=0", "R~T>2"}));
        System.out.println(solution(2, new String[]{"M~C<2", "C~M>1"}));
    }
}