import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class FindWayGame {

    static ArrayList<Integer> preorder;
    static ArrayList<Integer> postorder;

    static class Pos implements Comparable<Pos> {
        int v;
        int x, y;
        public Pos(int v, int x, int y) {
            this.v = v;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.y < o.y) return 1;
            if(this.y == o.y) return Integer.compare(this.x, o.x);
            return -1;
        }
    }

    public static void preorder(Pos[] arr, int idx) {
        if(arr[idx] != null) {
            preorder.add(arr[idx].v);
            preorder(arr, idx*2);
            preorder(arr, idx*2+1);
        }
    }

    public static void postorder(Pos[] arr, int idx) {
        if(arr[idx] != null) {
            postorder(arr, idx*2);
            postorder.add(arr[idx].v);
            postorder(arr, idx*2+1);
        }
    }

    public static int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        HashSet<Integer> ys = new HashSet<>();
        for(int i = 0; i < nodeinfo.length; i++) {
            pq.add(new Pos(i+1, nodeinfo[i][0], nodeinfo[i][1]));
            ys.add(nodeinfo[i][1]);
        }
        int tree_level = ys.size();
        Pos[] tree = new Pos[(int)Math.pow(2, tree_level)];
        tree[1] = pq.remove();
        int idx = 2;
        while(!pq.isEmpty()) {
            Pos node = pq.remove();
            while(tree[idx] != null) {
                if(node.x < tree[idx/2].x) idx *= 2;
                else idx = idx * 2 + 1;
            }
            System.out.println(idx);
            tree[idx] = node;
        }
        for(int i = 1; i < tree.length; i++) {
            if(tree[i] != null) System.out.println(i + " " + tree[i].v);
        }
        preorder = new ArrayList<>();
        preorder(tree, 1);
        postorder = new ArrayList<>();
        postorder(tree, 1);
//        System.out.println(preorder);
//        System.out.println(postorder);
        return answer;
    }

    public static void main(String[] args) {
        solution(new int[][]{{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
    }
}
