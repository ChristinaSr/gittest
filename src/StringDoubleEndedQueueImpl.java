import java.io.PrintStream;
import java.util.NoSuchElementException;

//The implementation is done by the use of generics, so I have altered some of the interface's return types and parameters.
public class StringDoubleEndedQueueImpl<T> implements StringDoubleEndedQueue<T>{

    private DLNode<T> head = null;
    private DLNode<T> tail = null;

    /**
     * @return true if the queue is empty
     */
    public boolean isEmpty()
    {
        return this.head == null;
    }

    /**
     * insert a String item at the front of the queue
     */
    public void addFirst(T item)
    {
        DLNode<T> node = new DLNode<T>(item);

        if (this.isEmpty()) {
            this.head = node;
            this.tail = node;
        }
        //list not empty
        else{
            node.setNext(this.head);
            this.head.setPrev(node);
            this.head = node;
        }
    }

    /**
     * remove and return the item at the front of the queue
     * @return String from the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T removeFirst() throws NoSuchElementException
    {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        // list not empty
        T item = this.head.getData();

        if (this.head == this.tail) {     //if list has one item only
            this.head = null;
            this.tail = null;
        }
        else{
            this.head = this.head.getNext();
        }

        return item;
    }

    /**
     * insert a String item at the end of the queue
     */
    public void addLast(T item)
    {
        DLNode<T> node = new DLNode<T>(item);

        if (this.isEmpty()) {
            this.head = node;
            this.tail = node;
        }
        //list not empty
        else{
            node.setPrev(tail);
            tail.setNext(node);
            tail = node;
        }
    }

    /**
     * remove and return the item at the end of the queue
     * @return String from the end of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T removeLast() throws NoSuchElementException
    {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        T item = tail.getData();

        if (this.head == this.tail) {    //if list has one item only
            this.head = null;
            this.tail = null;
        }
        else {
            tail = tail.getPrev();
            tail.setNext(null);
        }

        return item;
    }

    /**
     * return without removing the item at the front of the queue
     * @return String from the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T getFirst()
    {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.head.getData();
    }

    /**
     * return without removing the item at the end of the queue
     * @return String from the end of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T getLast()
    {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.tail.getData();
    }

    /**
     * print the String items of the queue, starting from the front,
     * to the print stream given as argument. For example, to
     * print the elements to the
     * standard output, pass System.out as parameter. E.g.,
     * printQueue(System.out);
     */
    public void printQueue(PrintStream stream)
    {
        DLNode<T> node = head;

        while (node != null) {
            stream.print(node.getData());
            stream.flush();
            node = node.getNext();
        }
    }

    /**
     * return the size of the queue, 0 if empty
     * @return number of elements in the queue
     */
    public int size()
    {
        return tail.getSize();
    }

}
