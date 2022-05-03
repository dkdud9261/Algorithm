import java.util.*;

public class Blind2021 {

    public static String solution1(String new_id) {
        String answer = "";
        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");
        for(; new_id.contains(".."); new_id = new_id.replaceAll("[.][.]", "."));
        if(new_id.startsWith(".")) new_id = new_id.substring(1);
        if(new_id.endsWith(".")) new_id = new_id.substring(0, new_id.length()-1);
        if(new_id.equals("")) new_id = "a";
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            if(new_id.endsWith(".")) new_id = new_id.substring(0, new_id.length()-1);
        }
        if(new_id.length() <= 2) {
            char last = new_id.charAt(new_id.length()-1);
            for(; new_id.length() != 3; new_id += last);
        }
        answer = new_id;

        return answer;
    }

    public static void combination(String str, String sub, int start, int n, int r, HashMap<String, Integer> map) {
        if(r == 0) {
            map.put(sub, map.getOrDefault(sub, 0) + 1);
            return;
        }
        for(int i = start; i < n; i++) combination(str, sub+str.charAt(i), i+1, n, r-1, map);
    }

    public static String[] solution2(String[] orders, int[] course) {
        String[] answer = {};
        HashMap<String, Integer> map = new HashMap<>();
        for(String order : orders) {
            for(int i = course.length-1; i >= 0; i--) {
                int c = course[i];
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                order = new String(arr);
                combination(order, "", 0, order.length(), c, map);
            }
        }
        int[] cnt = new int[11];
        for(String key : map.keySet()) {
            int val = map.get(key);
            if(val >= 2) cnt[key.length()] = Math.max(cnt[key.length()], val);
        }
        ArrayList<String> result = new ArrayList<>();
        for(int c : course) {
            for(String key : map.keySet()) {
                if(key.length() == c) {
                    if(map.get(key) == cnt[c]) result.add(key);
                }
            }
        };
        answer = result.toArray(new String[0]);
        Arrays.sort(answer);
        System.out.println(Arrays.toString(answer));

        return answer;
    }

    private static int lowerBound(List<Integer> data, int target) {
        if(data.size() == 0) return -1;
        int begin = 0;
        int end = data.size();
        while(begin < end) {
            int mid = (begin + end) / 2;
            if(data.get(mid) >= target) {
                end = mid;
            }
            else {
                begin = mid + 1;
            }
        }
        if(end == data.size()) return -1;
        return end;
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = {};
        answer = new int[query.length];
        ArrayList[][][][] db = new ArrayList[3][][][];
        for(int i = 0; i < 3; i++) {
            db[i] = new ArrayList[2][][];
            for(int j = 0; j < 2; j++) {
                db[i][j] = new ArrayList[2][];
                for(int k = 0; k < 2; k++) {
                    db[i][j][k] = new ArrayList[2];
                    for(int l = 0; l < 2; l++) db[i][j][k][l] = new ArrayList<Integer>();
                }
            }
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("cpp", 0);
        map.put("java", 1);
        map.put("python", 2);
        map.put("backend", 0);
        map.put("frontend", 1);
        map.put("junior", 0);
        map.put("senior", 1);
        map.put("chicken", 0);
        map.put("pizza", 1);

        for(String myInfo : info) {
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            int i0=0,i1=0,i2=0,i3=0;
            for(int i = 0; i < myInfo.length(); i++) {
                if(myInfo.charAt(i) == ' ') {
                    switch (cnt) {
                        case 0 -> i0 = map.get(sb.toString());
                        case 1 -> i1 = map.get(sb.toString());
                        case 2 -> i2 = map.get(sb.toString());
                        case 3 -> i3 = map.get(sb.toString());
                    }
                    cnt++;
                    sb.setLength(0);
                }
                else sb.append(myInfo.charAt(i));
            }
            db[i0][i1][i2][i3].add(Integer.parseInt(sb.toString()));
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 2; j++) {
                for(int k = 0; k < 2; k++) {
                    for(int l = 0; l < 2; l++) Collections.sort(db[i][j][k][l]);
                }
            }
        }
        for(int q = 0; q < query.length; q++) {
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            ArrayList<Integer> i0=new ArrayList<>(),i1=new ArrayList<>(),i2=new ArrayList<>(),i3=new ArrayList<>();
            for(int i = 0; i < query[q].length(); i++) {
                if(query[q].charAt(i) == ' ') {
                    if(sb.toString().equals("and")) {
                        sb.setLength(0);
                        continue;
                    }
                    if(sb.toString().equals("-")) {
                        switch (cnt) {
                            case 0 -> {i0.add(0);i0.add(1); i0.add(2);}
                            case 1 -> {i1.add(0); i1.add(1);}
                            case 2 -> {i2.add(0); i2.add(1);}
                            case 3 -> {i3.add(0); i3.add(1);}
                        }
                    }
                    else {
                        int num = map.get(sb.toString());
                        switch (cnt) {
                            case 0 -> i0.add(num);
                            case 1 -> i1.add(num);
                            case 2 -> i2.add(num);
                            case 3 -> i3.add(num);
                        }
                    }
                    cnt++;
                    sb.setLength(0);
                }
                else sb.append(query[q].charAt(i));
            }
            int passScore = Integer.parseInt(sb.toString());
            for(int i : i0) {
                for(int j : i1) {
                    for(int k : i2) {
                        for(int l : i3) {
                            System.out.println(db[i][j][k][l] + " " + passScore);
                            int m = lowerBound(db[i][j][k][l], passScore);
//                            System.out.println("==> " + m);
                            if(m >= 0) answer[q] += (db[i][j][k][l].size()-m);
                        }
                    }
                }
            }
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static void main(String[] args) {
        solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"}, new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
    }
}
