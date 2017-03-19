/**
 * @name :
 * @e-mail:
 */
public class SinglyLinkedNode<T> {
    private SinglyLinkedNode<T> next;
    private Object data;

    /**
     * create a new node
     *
     * @param data
     */
    public SinglyLinkedNode(T data) {
        this.data = data;
        this.next = null;
    }


    /**
     * @return T the data in the node
     */
    @SuppressWarnings("unchecked")
    public T getData() {
        return (T) data;
    }


    /**
     * sets the node as the "next" node in the list, returned by getNext()
     *
     * @param nextNode
     */
    public void setNext(SinglyLinkedNode<T> nextNode) {
        this.next = nextNode;
    }


    /**
     * @return the next node in the list
     */
    public SinglyLinkedNode<T> getNext() {
        return next;
    }


    /**
     * @return the string of the data inside it.
     */
    @Override
    public String toString() {
        return data.toString();
    }


}
