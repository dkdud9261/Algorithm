public class MyLibrary {

    static boolean[] visited;

    static class Tuple<A,B> {
        private A a;
        private B b;

        Tuple(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A getA() {
            return a;
        }

        public B getB() {
            return b;
        }

        public void setA(A a) {
            this.a = a;
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    // 조합
    public static void combination(String str, String sub, int start, int n, int r) {
        if(r == n) {
            System.out.println(sub);
            return;
        }
        for(int i = start; i < n; i++) combination(str, sub+str.charAt(i), i+1, n, r+1);
    }

    // 순열
    // visited 필요
    public static void permutation(String str, String sub, int n, int r) {
        if(r == n) {
            System.out.println(sub);
            return;
        }
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                permutation(str, sub + str.charAt(i), n, r+1);
                visited[i] = false;
            }
        }
    }

    // 소수 판별기
    public static boolean is_prime(int number) {
        if(number < 2) return false;
        if(number == 2) return true;
        for(int i = 2; i < number; i++) {
            if(number % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        visited = new boolean[4];
        combination("1234", "", 0, 4, 2);
        System.out.println();
        visited = new boolean[4];
        permutation("1234", "", 4, 0);

        Tuple<String, Integer> t = new Tuple<>("abc", 3);
        System.out.println(t.getA());
    }

}
