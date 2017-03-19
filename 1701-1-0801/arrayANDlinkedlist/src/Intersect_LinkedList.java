//数组和链表算法一样，不同数据结构不同实现。
//找交集算法如下：
//用三个数据下标从头走到尾，若三个下标对应的值都相等，说明是重合部分，一旦有不相等的，就离开了重合部分，即找到重合部分
//不相等时，找最大的，让其他的数据下标超过最大的。循环找到重复点。
//以这个为例：一开始，下标都是0，对应的值为1,3,1.不相等，最大的是3。A开始找比3大的，2不行，找到5.C找到3.
//下标对应值为5,3,3，不相等，最大为5,。B移动下标找到5，C也找到5.
//下标对应值为5,5,5，相等。进入重合部分。
//6,6,6,相等，重合
//7,7,8，不相等，结束。
//重合部分为5,6
//时间复杂度最多三个数据遍历完，所以是O(N+N+N)=O(3N)=O(N)
public class Intersect_LinkedList {
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

        //以下为找三个链表交集的核心算法部分
        LinkedList virtulHead = new LinkedList(0);
        boolean enter = false;
        for(ListNode i=A.head,j=B.head,k=C.head;i!=null && j!=null && k!=null;){//遍历三个数据
            if(i.val == j.val && j.val == k.val){//重合处
                enter = true;
                virtulHead.add(i.val);
                i = i.next;
                j = j.next;
                k = k.next;
                continue;
            }
            if(enter){
                break;//若重合结束，退出
            }
            //当前三个节点中最大值
            int max = ((i.val > j.val ? i.val : j.val) > k.val) ? (i.val > j.val ? i.val : j.val) : k.val;
            //三个链表节点若小于目标值，则移动链表节点，直到找到大于等于max的节点
            while(i.val<max){
                i = i.next;
            }
            while(j.val<max){
                j = j.next;
            }
            while(k.val<max){
                k = k.next;
            }
        }
        LinkedList.printList(virtulHead.head.next);
    }
}
