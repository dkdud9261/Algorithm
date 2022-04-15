// 다음에 해볼 시도 : 초로 바꿔서 싹다 돌려보기

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Comparator;
import java.util.LinkedList;

public class ChuseokTraffic {

    static class Processing {
        LocalDateTime start;
        LocalDateTime end;

        Processing(String S, String T) {
            this.end = LocalDateTime.parse(S, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            this.start = end.minusNanos(Math.round(Double.parseDouble(T.substring(0, T.length() - 1))*1000000000)).plusNanos(1000000);
            System.out.println(start);
            System.out.println(end);
        }
    }

    public static int solution(String[] lines) {
        int answer = 0;
        LinkedList<Processing> list = new LinkedList<>();
        LinkedList<LocalDateTime> timeList = new LinkedList<>();
        for(String line : lines) {
            String[] ll = line.split(" ");
            Processing p = new Processing(ll[0] + " " + ll[1], ll[2]);
            list.add(p);
            timeList.add(p.start);
            timeList.add(p.end);
        }
        timeList.sort(new Comparator<LocalDateTime>() {
            @Override
            public int compare(LocalDateTime o1, LocalDateTime o2) {
                return o1.compareTo(o2);
            }
        });

        int cnt1 = 0;
        LocalDateTime date = LocalDateTime.parse("2016-09-15 00:00:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        while(timeList.getFirst().isEqual(date) || timeList.getFirst().isBefore(date)) {
            timeList.removeFirst();
            cnt1++;
            if(timeList.isEmpty()) break;
        }
        if(cnt1 > 0) timeList.addFirst(date);
        if(timeList.size() == 1) return 1;
        System.out.println(timeList);


//        for(LocalDateTime start = timeList.getFirst(); start.isBefore(timeList.getLast()) || start.isEqual(timeList.getLast()); start = start.plusNanos(1000000)) {
        for(LocalDateTime start : timeList){
            LocalDateTime end = start.plusSeconds(1).minusNanos(1000000);
            int cnt = 0;
            for(Processing p : list) {
                if((p.start.isBefore(start) && p.end.isAfter(start))) cnt++;
                else if(p.start.isBefore(end) && p.end.isAfter(end)) cnt++;
                else if(p.start.isEqual(start) || p.start.isEqual(end) || p.end.isEqual(start) || p.end.isEqual(end)) cnt++;
            }
            System.out.println(start + " " + end + " => " + cnt);
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);

        return answer;
    }

    public static void main(String[] args) {
//        solution(new String[]{"2016-09-15 23:59:59.999 3.000s", "2016-09-15 23:59:59.999 0.001s", "2016-09-15 23:59:59.999 0.001s", "2016-09-15 23:59:59.000 0.001s", "2016-09-15 00:00:00.000 0.001s", "2016-09-15 00:00:00.000 0.001s", "2016-09-15 00:00:00.000 0.001s"});
        solution(new String[]{"2016-09-15 00:00:00.000 0.001s", "2016-09-15 00:00:00.000 0.001s", "2016-09-15 00:00:00.000 0.001s", "2016-09-15 23:59:59.999 3.000s", "2016-09-15 23:59:59.999 0.001s", "2016-09-15 23:59:59.999 0.001s", "2016-09-15 23:59:59.000 0.001s"});
    }
}
