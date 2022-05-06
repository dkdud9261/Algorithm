import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Blind2022 {

    public static int[] solution1(String[] id_list, String[] report, int k) {
        int[] answer = {};
        answer = new int[id_list.length];
        HashMap<String, Integer> id_index = new HashMap<>();
        for(int i = 0; i < id_list.length; i++) id_index.put(id_list[i], i);
        HashSet<String> newReport = new HashSet<>(Arrays.asList(report));
        HashMap<String, Integer> reported = new HashMap<>();
        for(String r : newReport) {
            String a_reported = r.split(" ")[1];
            reported.put(a_reported, reported.getOrDefault(a_reported,0)+1);
        }
        ArrayList<String> stop = new ArrayList<>();
        for(String key : reported.keySet()) {
            if(reported.get(key) >= k) stop.add(key);
        }
        for(String r : newReport) {
            String[] ids = r.split(" ");
            if(stop.contains(ids[1])) {
                answer[id_index.get(ids[0])]++;
            }
        }
        return answer;
    }

    public static boolean is_prime(long number) {
        if(number < 2) return false;
        if(number == 2) return true;
        for(long i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0) return false;
        }
        return true;
    }

    public static int solution2(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(n%k);
            n /= k;
        }
        String s = sb.reverse().toString();
        sb.setLength(0);
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '0') {
                if(sb.length() >= 1) {
                    if(is_prime(Long.parseLong(sb.toString()))) answer++;
                    sb.setLength(0);
                }
            }
            else sb.append(s.charAt(i));
        }
        if(sb.length() >= 1 && is_prime(Long.parseLong(sb.toString()))) answer++;
        System.out.println(answer);
        return answer;
    }

    static class Parking {
        String carNum;
        ArrayList<LocalTime> in, out;
        int time;
        int price;
        public Parking(String carNum) {
            this.carNum = carNum;
            this.in = new ArrayList<>();
            this.out = new ArrayList<>();
            this.time = 0;
            this.price = 0;
        }
        public void addIn(String in) {
            this.in.add(LocalTime.parse(in));
        }
        public void addOut(String out) {
            this.out.add(LocalTime.parse(out));
        }
        public int getTime() {
            if(this.in.size() != this.out.size()) out.add(LocalTime.of(23, 59));
            for(int i = 0; i < this.in.size(); i++) {
                int duration = (int)Duration.between(this.in.get(i), this.out.get(i)).getSeconds()/60;
                this.time += duration;
            }
            return this.time;
        }
    }

    public static int[] solution3(int[] fees, String[] records) {
        int[] answer = {};
        HashMap<String, Parking> map = new HashMap<>();
        for(String r : records) {
            String[] rs = r.split(" ");
            if(rs[2].equals("IN")) {
                if(map.get(rs[1]) == null) {
                    Parking parking = new Parking(rs[1]);
                    parking.addIn(rs[0]);
                    map.put(rs[1], parking);
                }
                else map.get(rs[1]).addIn(rs[0]);
            }
            else map.get(rs[1]).addOut(rs[0]);
        }
        Object[] keys = map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        answer = new int[keys.length];
        for(int i = 0; i < keys.length; i++) {
            String key = (String) keys[i];
            int time = map.get(key).getTime();
            int price = fees[1];
            if(time > fees[0]) {
                double extraTime = time - fees[0];
                price += (Math.ceil(extraTime / fees[2]) * fees[3]);
            }
            answer[i] = price;
        }
        return answer;
    }

    static class Play {
        int[] lion;
        int gap;
        public Play(int[] lion, int[] apeach) {
            if(lion == null) {
                this.lion = null;
                this.gap = -2;
            }
            else {
                this.lion = new int[11];
                this.lion = lion.clone();
                this.gap = scoreGap(apeach);
            }
        }
        public int scoreGap(int[] apeach) {
            int gap = 0;
            for(int i = 0; i <= 10; i++) {
                if(apeach[i] == 0 && lion[i] == 0) continue;
                if(lion[i] > apeach[i]) gap += (10-i);
                else gap -= (10-i);
            }
            if(gap < 0) return -1;
            return gap;
        }
    }

    public static Play permutation(int[] arr, int[] sub, int n, int r, int sum, int shoot, int[] apeach) {
        if(shoot == sum) return new Play(sub, apeach);
        if(n == r) {
            if(shoot < sum) {
                sub[10] = sum - shoot;
                return new Play(sub, apeach);
            }
            return new Play(null, apeach);
        }
        sub[r] = arr[r];
        Play play1 = permutation(arr, sub, n, r + 1, sum, shoot + arr[r], apeach);
        sub[10] = 0;
        sub[r] = 0;
        Play play2 = permutation(arr, sub, n, r + 1, sum, shoot, apeach);
        sub[10] = 0;
        if(play1.gap > play2.gap) return play1;
        return play2;
    }

    public static int[] solution(int n, int[] info) {
        int[] lion = new int[11];
        for(int i = 0; i < 10; i++) lion[i] = info[i] + 1;
        Play play = permutation(lion, new int[11], 10, 0, n, 0, info);
        if(play.gap == -1) return new int[]{-1};
        return play.lion;
    }

    public static void main(String[] args) {
        solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0});
        solution(1, new int[]{1,0,0,0,0,0,0,0,0,0,0});
        solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3});
    }
}
