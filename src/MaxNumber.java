import java.util.*;

public class MaxNumber {
    class MyString implements Comparable<MyString> {

        @Override
        public int compareTo(MyString o) {
            return 0;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<String> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        String s = "";
        int[] arr = {1,10,9,2000,300};
        Integer[] arr1 = new Integer[arr.length];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        String[] strings = new String[5];
        int[] arr2 = new int[5];
        String[] ss = strings[0].substring(1,2).split(",");
    }
}
