import java.util.LinkedList;
import java.util.Locale;

class SkillCheck2 {
    class RootForPoint {
        private boolean U, D, R, L;
        RootForPoint(boolean b) {
            U = b;
            D = b;
            R = b;
            L = b;
        }

        public void setU(boolean u) {
            U = u;
        }

        public void setD(boolean d) {
            D = d;
        }

        public void setL(boolean l) {
            L = l;
        }

        public void setR(boolean r) {
            R = r;
        }

        public boolean isD() {
            return D;
        }

        public boolean isL() {
            return L;
        }

        public boolean isR() {
            return R;
        }

        public boolean isU() {
            return U;
        }
    }

    public int solution1(String dirs) {
        int answer = 0;
        RootForPoint[][] visited = new RootForPoint[11][11];
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) visited[i][j] = new RootForPoint(false);
        }
        int i = 5, j = 5;
        for(int k = 0; k < dirs.length(); k++) {
            switch (dirs.charAt(k)) {
                case 'U':
                    if(i <= 9) {
                        if(!visited[i][j].isU() && !visited[i+1][j].isD()) {
                            answer++;
                            visited[i][j].setU(true);
                            visited[i+1][j].setD(true);
                        }
                        i++;
                    }
                    break;
                case 'D':
                    if(i >= 1) {
                        if(!visited[i][j].isD() && !visited[i-1][j].isU()) {
                            answer++;
                            visited[i][j].setD(true);
                            visited[i-1][j].setU(true);
                        }
                        i--;
                    }
                    break;
                case 'R':
                    if(j <= 9) {
                        if(!visited[i][j].isR() && !visited[i][j+1].isL()) {
                            answer++;
                            visited[i][j].setR(true);
                            visited[i][j+1].setL(true);
                        }
                        j++;
                    }
                    break;
                case 'L':
                    if(j >= 1) {
                        if(!visited[i][j].isL() && !visited[i][j-1].isR()) {
                            answer++;
                            visited[i][j].setL(true);
                            visited[i][j-1].setR(true);
                        }
                        j--;
                    }
                    break;
            }
        }
        return answer;
    }

    public int[][] solution1(int[][] arr1, int[][] arr2) {
        int[][] answer = {};
        int r1 = arr1.length;
        int c1 = arr2[0].length;
        int c2 = arr1[0].length;
        answer = new int[r1][c1];
        for(int i = 0; i < r1; i++) {
            for(int j = 0; j < c1; j++) {
                for(int k = 0; k < c2; k++) answer[i][j] += arr1[i][k] * arr2[k][j];
            }
        }

        return answer;
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize == 0) return cities.length*5;
        LinkedList<String> cache = new LinkedList<>();
        for(String city : cities) {
            System.out.println(cache + " " + city);
            city = city.toLowerCase();
            if(cache.contains(city)) {
                cache.remove(city);
                cache.add(city);
                answer++;
            }
            else {
                if(cache.size() >= cacheSize) cache.removeFirst();
                cache.add(city);
                answer += 5;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
//        String[] str = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
//       String[] str = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        String[] str = {"Jeju", "pan", "df", "NewYork", "LA", "hg", "ll"};
       System.out.println(solution(5, str));
    }
}