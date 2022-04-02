import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MultiLevelSales {
    static class Node {
        String name;
        Node parent;
        int price;
        Node(String name) {
            this.name = name;
            parent = null;
        }
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        answer = new int[enroll.length];
        HashMap<String, Integer> map = new HashMap<>();
        Node root = new Node("center");
        Node[] tree = new Node[enroll.length];
        for(int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], i);
            tree[i] = new Node(enroll[i]);
        }
        for(int i = 0; i < referral.length; i++) {
            if(referral[i].equals("-")) tree[i].parent = root;
            else {
                Node parent = tree[map.get(referral[i])];
                tree[i].parent = parent;
            }
        }
        for(int i = 0; i < seller.length; i++) {
            Node s = tree[map.get(seller[i])];
            s.price += amount[i]*100;
            Double d = amount[i] * 100 * 0.1;
            int div = d.intValue();
            for(Node node = s; s != root; s = s.parent) {
                s.price -= div;
                s.parent.price += div;
                d = div * 0.1;
                div = d.intValue();
            }
        }
        for(int i = 0; i < enroll.length; i++) answer[i] = tree[i].price;
        return answer;
    }

    public static void main(String[] args) {
        solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}, new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}, new String[]{"young", "john", "tod", "emily", "mary"}, new int[]{12, 4, 2, 5, 10});
    }
}
