//import java.util.*;
//
//public class MaximumNumber {
//
//    public static void main(String[] args) {
//        int[] arr = {5,3,2,1,4};
//        Integer[] arr1 = new Integer[arr.length];
//        for(int i = 0; i < arr.length; i++) arr1[i] = arr[i];
//        Arrays.sort(arr, (o1, o2) -> {
//            String s1 = Integer.toString(o1);
//            String s2 = Integer.toString(o2);
//            if (s1.length() == s2.length()) return Integer.compare(o1, o2);
//            if (s1.length() < s2.length()) {
//                StringBuilder sb = new StringBuilder(s1);
//                while (sb.length() == s2.length()) sb.append(s1.charAt(0));
//                return Integer.compare(Integer.parseInt(sb.toString()), o2);
//            }
//            StringBuilder sb = new StringBuilder(s2);
//            while (sb.length() == s1.length()) sb.append(s2.charAt(0));
//            return Integer.compare(o1, Integer.parseInt(sb.toString()));
//        });
//    }
//}
