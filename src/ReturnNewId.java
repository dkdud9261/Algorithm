public class ReturnNewId {

    public static String solution(String new_id) {
        String answer = "";
        answer = new_id.toLowerCase().replaceAll("[^a-z0-9-_.]", "");
        String temp = "";
        for(int i = 0; i < answer.length()-1; i++) {
            if(!(answer.charAt(i)=='.' && answer.charAt(i+1)=='.')) temp += answer.charAt(i);
        }
        temp += answer.charAt(answer.length()-1);
        answer = temp;
        if(answer.equals(".")) answer = "";
        else {
            if (answer.length() >= 2 && answer.charAt(0) == '.') {
                while (answer.charAt(0) == '.') answer = answer.substring(1);
            }
            if (answer.charAt(answer.length() - 1) == '.') {
                while (answer.charAt(answer.length() - 1) == '.') answer = answer.substring(0, answer.length() - 1);
            }
        }
        if(answer.equals("")) answer = "a";
        if(answer.length() >= 16) {
            answer = answer.substring(0, 15);
            if(answer.charAt(answer.length()-1)=='.') answer = answer.substring(0, answer.length()-1);
        }
        if(answer.length() <= 2) {
            char last_c = answer.charAt(answer.length()-1);
            while(answer.length() < 3) answer += last_c;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
        System.out.println(solution("z-+.^."));
        System.out.println(solution("=.="));
        System.out.println(solution("123_.def"));
        System.out.println(solution("abcdefghijklmn.p"));
        System.out.println(solution("aaa.......aaaa........aaaaa"));

    }
}
