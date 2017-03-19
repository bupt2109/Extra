/**
 * @name :
 * @e-mail:
 */
public class Queue<T> {

    private int MAX;
    private Object[] elements;
    private int size;

    /**
     * Constructor that creates the internal array of size “size”, as well as any other variables needed in the queue.
     *
     * @param size queue size
     */
    //Constructor that creates the internal array of size “size”, as well as any other variables needed in the queue.
    public Queue(int size) {
        MAX = size;
        elements = new Object[MAX];
        this.size = 0;
    }

    //enqueue data

    /**
     * enqueue data to queue
     *
     * @param data
     */
    public void enqueue(T data) {
        if (isFull())
            return;
        elements[size++] = data;
    }

    //dequeue first item in the queue

    /**
     * @return first item in the queue
     */
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty())
            return null;
        T res = (T) elements[0];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return res;
    }

    //return size of the queue

    /**
     * @return size of the queue
     */
    public int getSize() {
        return size;
    }

    //returns true if queue is empty

    /**
     * @return true if queue is empty
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    //return true if queue is full.

    /**
     * @return true if queue is full.
     */
    public boolean isFull() {
        if (size == MAX) {
            return true;
        } else {
            return false;
        }
    }


}
