
public class LinkedList {

    ListNode head;//链表头
    ListNode tail;//链表尾

    //创建链表，赋表头
    public LinkedList(int val) {
        head = new ListNode(val);
        tail = head;
        tail.next = null;
    }

    //添加一个节点，更改表尾
    public void add(int val){
        ListNode node = new ListNode(val);
        tail.next = node;
        tail = node;
    }

    //打印从传入节点到链表尾的所有节点
    public static void printList(ListNode node){
        StringBuilder sb = new StringBuilder();
        if(node==null){
            sb.append("null");
        }
        else {
            ListNode tmp = node;
            while (tmp != null){
                sb.append(tmp.val+"->");
                tmp = tmp.next;
            }
            sb.append("null");
        }
        System.out.println(sb.toString());
    }
}
