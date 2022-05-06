import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        pq1.add(Integer.parseInt(br.readLine()));
        bw.write(String.valueOf(pq1.peek()));
        bw.newLine();
        int mid = pq1.peek();
        for(int i = 2; i <= N; i++) {
            int number = Integer.parseInt(br.readLine());
            if(number > mid) {
                if(pq1.size() == pq2.size()) pq1.add(pq2.remove());
                pq2.add(number);
            }
            else if(number == mid) {
                if(pq1.size() > pq2.size()) pq2.add(number);
                else pq1.add(number);
            }
            else {
                if(pq1.size() > pq2.size()) pq2.add(pq1.remove());
                pq1.add(number);
            }
            mid = Math.min(pq1.peek(), pq2.peek());
            bw.write(String.valueOf(mid));
            bw.newLine();
        }
        bw.flush();
    }
}
