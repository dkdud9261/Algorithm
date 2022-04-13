import java.util.LinkedList;
import java.util.Queue;

public class Cache {

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize == 0) return cities.length * 5;
        Queue<String> cache = new LinkedList<>();
        for(String city : cities) {
            city = city.toLowerCase();
            // 포함 안하면 front를 제거하고 캐시에 넣어주고 5
            if(!cache.contains(city)) {
                answer += 5;
                if(cache.size() == cacheSize) cache.remove();
                cache.add(city);
            }
            // 포함하면 1, 꺼내서 맨 뒤로 넣어주기
            else {
                answer += 1;
                cache.remove(city);
                cache.add(city);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"});
    }
}
