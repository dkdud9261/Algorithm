public class NumericString {

    public static int solution(String s) {
        int answer = 0;
        String[] strings = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for(int i = 0; i < 10; i++) s = s.replaceAll(strings[i], nums[i]);
        answer = Integer.parseInt(s);
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("one4seveneight"));
    }
}
