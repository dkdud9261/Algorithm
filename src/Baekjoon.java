//import java.io.*;
//import java.util.Collections;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//
//public class Baekjoon {
//
//    public class Main {
//
//        public static void main1(String[] args) throws IOException {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//            int N = Integer.parseInt(br.readLine());
//            PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
//            PriorityQueue<Integer> pq2 = new PriorityQueue<>();
//            pq1.add(Integer.parseInt(br.readLine()));
//            bw.write(String.valueOf(pq1.peek()));
//            bw.newLine();
//            int mid = pq1.peek();
//            for(int i = 2; i <= N; i++) {
//                int number = Integer.parseInt(br.readLine());
//                if(number > mid) {
//                    if(pq1.size() == pq2.size()) pq1.add(pq2.remove());
//                    pq2.add(number);
//                }
//                else if(number == mid) {
//                    if(pq1.size() > pq2.size()) pq2.add(number);
//                    else pq1.add(number);
//                }
//                else {
//                    if(pq1.size() > pq2.size()) pq2.add(pq1.remove());
//                    pq1.add(number);
//                }
//                mid = Math.min(pq1.peek(), pq2.peek());
//                bw.write(String.valueOf(mid));
//                bw.newLine();
//            }
//            bw.flush();
//        }
//
//        static class Pair implements Comparable<Pair> {
//            int ranking1, ranking2;
//            int id;
//            public Pair(int ranking1, int ranking2, int id) {
//                this.ranking1 = ranking1;
//                this.ranking2 = ranking2;
//                this.id = id;
//            }
//
//            @Override
//            public int compareTo(Pair o) {
//                return Integer.compare(this.ranking1, o.ranking1);
//            }
//        }
//        // 1946 신입사원
//        public static void main(String[] args) throws IOException {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//            int T = Integer.parseInt(br.readLine());
//            for(int i = 0; i < T; i++) {
//                int N = Integer.parseInt(br.readLine());
//                PriorityQueue<Pair> pq1 = new PriorityQueue<>();
//                PriorityQueue<Pair> pq2 = new PriorityQueue<>();
//                for(int j = 0; j < N; j++) {
//                    StringTokenizer st = new StringTokenizer(br.readLine());
//                    int s1 = Integer.parseInt(st.nextToken());
//                    int s2 = Integer.parseInt(st.nextToken());
//                    pq1.add(new Pair(s1, s2, j));
//                    pq2.add(new Pair(s2, s1, j));
//                }
//                boolean[] fail = new boolean[N]; // true면 탈락 false면 통과
//                int cutline1 = pq1.remove().ranking2;
//                int cutline2 = pq2.remove().ranking2;
//                for(int j = 1; j < N; j++) {
//                    Pair applicant1 = pq1.remove();
//                    if(applicant1.ranking2 > cutline1) fail[applicant1.id] = true;
//                    else cutline1 = applicant1.ranking2;
//                    Pair applicant2 = pq2.remove();
//                    if(applicant2.ranking2 > cutline2) fail[applicant2.id] = true;
//                    else cutline2 = applicant2.ranking2;
//                }
//                int pass = 0;
//                for(int j = 0; j < N; j++) {
//                    if(!fail[j]) pass++;
//                }
//                bw.write(String.valueOf(pass));
//                bw.newLine();
//            }
//            bw.flush();
//        }
//    }
//
//}
