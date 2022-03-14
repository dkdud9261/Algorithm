// HashSet은 중복 없애줌
// 최대한 그냥 Array로 해볼 것

import java.util.*;

public class GetReportsResult {

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        int l_id = id_list.length;

        answer = new int[l_id];

        if(report.length < k) return answer;

        // 각 id에 인덱스 매겨주기
        HashMap<String, Integer> users = new HashMap<>();
        for(int i = 0; i < l_id; i++) users.put(id_list[i], i);

        // 각 user가 몇 번 신고 당했는지 저장할 배열
        int[] reported_list = new int[l_id];

        // 중복 신고 내역 제거
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(report));
        report = hashSet.toArray(new String[0]);

        int l_report = report.length;

        String[] reporters = new String[l_report];
        String[] reported = new String[l_report];
        for(int i = 0; i < l_report; i++) {
            String[] ss = report[i].split(" ");
            reporters[i] = ss[0];
            reported[i] = ss[1];
        }

        // 신고 내역 탐색 -> 이용자별 신고 받은 횟수 세기
        int[] isBanned = new int[l_id];
        for(int i = 0; i < l_report; i++) {
            int rindex = users.get(reported[i]);
            if(isBanned[rindex] > 0) continue;
            if(reported_list[rindex] + 1 >= k) {
                isBanned[rindex] = 1; // 신고 받은 횟수가 k 이상이면 ban_list에 저장
                continue;
            }
            reported_list[rindex]++;
        }

        // report랑 isBanned 비교하면서
        for(int i = 0; i < l_id; i++) {
            if(isBanned[i] > 0) { // id_list[i]가 정지당함
                for(int j = 0; j < l_report; j++) { // report[j]의 신고대상이 id_list[i]랑 같으면 신고자한테 메일 발송
                    if(reported[j].equals(id_list[i])) {
                        answer[users.get(reporters[j])]++;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = {"con", "ryan"};
        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k = 3;
        int[] result = solution(id_list, report, k);
        for(int r : result) System.out.println(r);
    }
}
