//数组和链表算法一样，不同数据结构不同实现。
//找并集算法如下：
//两两相并。
//由于排好序，只要两个数组从数组头走到尾，对应的值小的存入新数组，移动下标，大的不移动。若相等，存入新数组，两个数组都移动下标。
//时间复杂度先合并A、B到新数组AB，需要遍历A、B：O(N+N)=O(2N)，然后合并AB和C，需要遍历AB和C：O(2N+N)=O(3N)，所以是O(2N)+O(3N)=O(5N)=O(N)
public class Union_LinkedList {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        LinkedList virtulHead = new LinkedList(0);
        ListNode p = l1;
        ListNode q = l2;
        while(p!=null && q!=null){
            if (p.val<q.val) {
                virtulHead.add(p.val);
                p = p.next;
            } else if(p.val>q.val){
                virtulHead.add(q.val);
                q = q.next;
            }else{
                virtulHead.add(q.val);
                q = q.next;
                p = p.next;
            }
        }
        while (p!=null) {
            virtulHead.add(p.val);
            p = p.next;
        }
        while (q!=null) {
            virtulHead.add(q.val);
            q = q.next;
        }
        return virtulHead.head.next;
    }

    public static void main(String[] args) {
        //链表数据
        LinkedList A = new LinkedList(1);
        A.add(2);
        A.add(5);
        A.add(6);
        A.add(7);
        A.add(9);
        LinkedList.printList(A.head);

        LinkedList B = new LinkedList(3);
        B.add(5);
        B.add(6);
        B.add(7);
        B.add(8);
        B.add(10);
        LinkedList.printList(B.head);

        LinkedList C = new LinkedList(1);
        C.add(3);
        C.add(4);
        C.add(5);
        C.add(6);
        C.add(8);
        LinkedList.printList(C.head);


        ListNode AB = mergeTwoLists(A.head,B.head);
        //LinkedList.printList(mergeTwoLists(A.head,B.head));
        ListNode ABC = mergeTwoLists(AB,C.head);
        LinkedList.printList(ABC);
        //LinkedList.printList(A.head);
        //LinkedList.printList(B.head);
        //LinkedList.printList(C.head);

    }

}
