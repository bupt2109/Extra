/**
 * @name :
 * @e-mail:
 */
public class SinglyLinkedList<T> {

    private SinglyLinkedNode<T> firstNode;
    private SinglyLinkedNode<T> lastNode;
    private int size;

    /**
     * Constructor
     */
    public SinglyLinkedList() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }


    /**
     * @return the first node in the list (null if empty)
     */
    public SinglyLinkedNode<T> getHead() {
        return firstNode;
    }


    /**
     * insert "data" at the end.
     *
     * @param data
     */
    public void regularInsert(T data) {
        if (size <= 0) {
            addFirstNode(data);
        } else {
            SinglyLinkedNode<T> node = new SinglyLinkedNode<>(data);
            lastNode.setNext(node);
            lastNode = node;
            size++;
        }
    }


    /**
     * insert "data" at a random point in the LinkedList. It should
     * be equally likely that a card will end up at any of the possible
     * locations, including the  front and the end. This is going to be used
     * when inserting a card into the discard pile, as well as initially into the deck.
     *
     * @param data
     */
    public void randomInsert(T data) {
        if (size <= 0) {
            addFirstNode(data);
        } else {
            int rand = (int) (Math.random() * (size + 1));
            //System.out.println(rand + " " + size + " "+this);
            if (rand == 0) {
                SinglyLinkedNode<T> node = firstNode;
                firstNode = new SinglyLinkedNode<>(data);
                firstNode.setNext(node);
                size++;
            } else if (rand == size) {
                regularInsert(data);
            } else {
                SinglyLinkedNode<T> nodeCur = firstNode;
                for (int i = 1; i < rand; i++) {
                    nodeCur = nodeCur.getNext();
                }
                SinglyLinkedNode<T> nodeNext = nodeCur.getNext();
                SinglyLinkedNode<T> nodeInsert = new SinglyLinkedNode<T>(data);
                nodeCur.setNext(nodeInsert);
                nodeInsert.setNext(nodeNext);
                size++;
            }

        }
    }


    /**
     * delete the “data” node from the list.
     *
     * @param data delete the “data”
     * @return delete the “data”
     */
    public T remove(T data) {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("Empty list!");
            // return null;
        }
        SinglyLinkedNode<T> node = firstNode;
        if (data.equals(firstNode.getData())) {//node to be delete is the first node
            firstNode = firstNode.getNext();
            node.setNext(null);
            if (firstNode == null) {
                lastNode = null;
            }
            size--;
            return node.getData();
        } else {
            while ((node.getNext() != null) && !data.equals(node.getNext().getData())) {
                node = node.getNext();
            }
            if (node.getNext() == null && !data.equals(node.getData())) {
                throw new IndexOutOfBoundsException("Can't not find data in list!");
                //return null;
            } else {
                SinglyLinkedNode<T> tmp = node.getNext();
                T res = tmp.getData();
                node.setNext(tmp.getNext());
                tmp.setNext(null);
                if (tmp == lastNode) {
                    lastNode = node;
                }
                size--;
                return res;
            }
        }
    }


    /**
     * delete the item at "index", return that item.
     *
     * @param index delete the “data”
     * @return delete the “data”
     */
    public T removeIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("A illegal index!");
        }
        SinglyLinkedNode<T> node = firstNode;
        if (index == 0) {
            firstNode = firstNode.getNext();
            node.setNext(null);
            if (firstNode == null)
                lastNode = null;
            size--;
            return node.getData();
        } else {
            for (int i = 1; i < index; i++) {
                node = node.getNext();
            }
            SinglyLinkedNode<T> tmp = node.getNext();//node to be delete
            T res = tmp.getData();
            node.setNext(tmp.getNext());
            tmp.setNext(null);
            if (tmp == lastNode) {
                lastNode = node;
            }
            size--;
            return res;
        }
    }


    /**
     * @return size of linked list
     */
    public int size() {
        return size;
    }

    /**
     * @return all node's data
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append(name);
        if (size <= 0) {
            sb.append("Empty");
            return sb.toString();
        }
        SinglyLinkedNode<T> node = firstNode;
        while (node != null) {
            sb.append(node.getData() + ",");
            node = node.getNext();
        }
        return sb.toString();
    }

    /**
     * add a data to the first place in list
     *
     * @param data data to be added
     */
    private void addFirstNode(T data) {
        firstNode = new SinglyLinkedNode<>(data);
        firstNode.setNext(lastNode);
        lastNode = firstNode;
        size++;
    }
}
