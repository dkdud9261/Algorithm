import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class SortFileName {

    static class Fname implements Comparable<Fname> {
        String head;
        String smallHead;
        int number;
        String number_str;
        String tail;
        public Fname(String name) {
            int i;
            for(i = 0; i < name.length(); i++) {
                if(isNumber(name.charAt(i))) break;
            }
            this.head = name.substring(0, i);
            this.smallHead = head.toLowerCase();
            int j;
            for(j = i; j < name.length(); j++) {
                if(!isNumber(name.charAt(j))) break;
            }
            this.number_str = name.substring(i,j);
            this.number = Integer.parseInt(number_str);
            this.tail = name.substring(j);
        }

        boolean isNumber(char c) {
            return c >= 48 && c <= 57;
        }

        @Override
        public int compareTo(Fname o) {
            if(o.smallHead.equals(this.smallHead)) return this.number - o.number;
            return this.smallHead.compareTo(o.smallHead);
        }

        public String getFname() {return this.head+this.number_str+this.tail;}
    }

    public static String[] solution(String[] files) {
        String[] answer = {};
        LinkedList<Fname> fileList = new LinkedList<>();
        for(String f : files) fileList.add(new Fname(f));
        Collections.sort(fileList);

        answer = new String[fileList.size()];
        int i = 0;
        for(Fname f : fileList) answer[i++] = f.getFname();
        System.out.println(Arrays.toString(answer));

        return answer;
    }

    public static void main(String[] args) {
        solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
        solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});

    }
}
