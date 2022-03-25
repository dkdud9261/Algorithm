import java.util.Arrays;
import java.util.HashMap;

public class OpenChatting {

    public static class Tuple {

        private int queryNum;
        private String uid;

        public Tuple(int a, String b) {
            this.queryNum = a;
            this.uid = b;
        }

        public int getQueryNum() {
            return queryNum;
        }

        public String getUid() {
            return uid;
        }
    }

    public static String[] solution(String[] record) {
        String[] answer = {};
        Tuple[] records = new Tuple[record.length];
        HashMap<String, String> nickname = new HashMap<>();
        int i = 0;

        for(String r : record) {
            String[] rs = r.split(" ");
            switch (rs[0]) {
                case "Enter" -> {
                    records[i++] = new Tuple(1, rs[1]);
                    nickname.put(rs[1], rs[2]);
                }
                case "Leave" -> {
                    records[i++] = new Tuple(2, rs[1]);
                }
                case "Change" -> nickname.put(rs[1], rs[2]);
            }
        }
        answer = new String[i];
        int j = 0;
        for(int k = 0; k < i; k++) {
            String a = nickname.get(records[k].getUid()) + "님이 ";
            if(records[k].getQueryNum() == 1) a += "들어왔습니다.";
            else a += "나갔습니다.";
            answer[j++] = a;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"})));
    }
}
