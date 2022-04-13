import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JustThatSong {

    static class Music {
        int minute;
        String title;
        public Music(int m, String t) {
            this.minute = m;
            this.title = t;
        }
    }

    public static String rearrange(String m) {
        ArrayList<String> mList = new ArrayList<>();
        for(int i = 0; i < m.length(); i++) {
            char c = m.charAt(i);
            if(c == '#') {
                String sharp = mList.remove(mList.size()-1);
                mList.add(sharp.toLowerCase());
            }
            else mList.add(c+"");
        }
        m = "";
        for(String s : mList) m += s;

        return m;
    }

    // ** 들은길이 > 재생길이 : 걸러
    // ** 들은길이 =< 재생길이 : 재생된걸 계산해서 m을 포함하면 되잖아 !!!!!
    public static String solution(String m, String[] musicinfos) {
        String answer = "";
        m = rearrange(m);
        LinkedList<Music> candidate = new LinkedList<>();
        for(String info : musicinfos) {
            String[] infos = info.split(",");
            infos[3] = rearrange(infos[3]);
            int play_len = (int)Duration.between(LocalTime.parse(infos[0]),LocalTime.parse(infos[1])).getSeconds()/60;
            int real_len = infos[3].length();
            if(m.length() > play_len) continue;
            else {
                String play = "";
                for(int i = 0; i < play_len; i++) play += infos[3].charAt(i%real_len);
                if(play.contains(m)) {
                    if(!candidate.isEmpty()) {
                        if(candidate.get(0).minute < play_len) {
                            candidate.clear();
                            candidate.add(new Music(play_len, infos[2]));
                        }
                    }
                    else candidate.add(new Music(play_len, infos[2]));
                }
            }
        }
        if(candidate.isEmpty()) return "(None)";
        answer = candidate.get(0).title;

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
        System.out.println(solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
    }
}
