import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class ParkingFee {

    public static class Parking {
        String carNum;
        LocalTime in, out;
        int minute;
        public Parking(String carNum, LocalTime in) {
            this.carNum = carNum;
            this.in = in;
            this.out = null;
            this.minute = 0;
        }
        public int calculate(LocalTime out) {
            this.out = out;
            return (int)Duration.between(in, out).getSeconds()/60;
        }
    }

    public static int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        // frees[0]분까지는 fees[1]원 초과하면 fees[2]분당 fees[3]원 추가요금
        LinkedList<Parking> INs = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(String r : records) {
            String[] rs = r.split(" ");
            if(rs[2].equals("IN")) INs.add(new Parking(rs[1], LocalTime.parse(rs[0])));
            else {
                for(Parking p : INs) {
                    if(p.carNum.equals(rs[1])) {
                        map.put(rs[1], map.getOrDefault(rs[1], 0) + p.calculate(LocalTime.parse(rs[0])));
                        INs.remove(p);
                        break;
                    }
                }
            }
        }
        for(Parking p : INs) map.put(p.carNum, map.getOrDefault(p.carNum,0) + p.calculate(LocalTime.parse("23:59")));

        Object[] mapkey = map.keySet().toArray();
        Arrays.sort(mapkey);

        answer = new int[map.size()];
        int i = 0;
        for(Object key : mapkey) {
            if (map.get(key) <= fees[0]) answer[i++] = fees[1];
            else{
                double extraFee = (double)(map.get(key) - fees[0]) / fees[2];
                answer[i++] = fees[1] + (int)Math.ceil(extraFee) * fees[3];
            }

        }
        System.out.println(Arrays.toString(answer));

        return answer;
    }

    public static void main(String[] args) {
        int[] fees = {1, 461, 1, 10};
//        String[] records = {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
        String[] records = {"00:00 1234 IN"};
        solution(fees, records);
    }
}
