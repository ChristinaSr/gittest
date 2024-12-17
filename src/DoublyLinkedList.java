import java.io.PrintStream;
import java.util.NoSuchElementException;

//The implementation had been done for project 1
public class DoublyLinkedList<T> {

    private DLNode<T> head = null;
    private DLNode<T> tail = null;

    public boolean isEmpty()
    {
        return this.head == null;
    }

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

    // it adds to the list an element (Object City) to a given index by replacing the element it already has at that index.
    public void addAtIndex(T item,int index){
        DLNode<T> node = head;
        DLNode<T> newnode = new DLNode<T>(item);
        newnode.setSize();  // the class Node has now an additional method called setSize which does not allow the dll to increase its size by 1, in order to keep its current size (because we do not add an item, we put it to the same node by replacement.
        // Because the size attribute is static, every time we create an object Node in the methods of dll,
        // it increases by 1. So, if we want just to replace an item instead of adding it to a new node, by the creation of a node that holds te new item the size will increase autommatically.
        // By calling this method, the size decreases by 1. Thus, the size of the dll remains the same.
        if (isEmpty()) {
            addFirst(item);
        }
        if (index == 0) {
            newnode.setNext(this.head.getNext());   // if we want to replace the first item
            this.head = newnode;
        }
        else if (index == size()-1) {
            newnode.setPrev(this.tail.getPrev());   // if we want to replace the last item
            tail.getPrev().setNext(newnode);
            this.tail = newnode;
        }
        else {
            for (int i = 0; i < index - 1 && node.getNext().getNext() != null; i++) {
                node = node.getNext();
            }
            newnode.setNext(node.getNext().getNext());
            newnode.getNext().setPrev(newnode);
            newnode.setPrev(node);
            node.setNext(newnode);
        }
    }

    public T removeAtIndex(int index){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T item = null;
        DLNode<T> temp = head;
        for(int i=0; i<index; i++) {
            temp = temp.getNext();
        }
        DLNode<T> node = temp.getNext();
        item = temp.getData();
        node.setPrev(temp.getPrev());
        temp.getPrev().setNext(node);
        temp = null;
        return item;    //Deleted node's data
    }

    public T getFirst()
    {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.head.getData();
    }

    public T getLast()
    {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.tail.getData();
    }

    public void printQueue(PrintStream stream)
    {
        DLNode<T> node = head;

        while (node != null) {
            stream.print(node.getData());
            stream.flush();
            node = node.getNext();
        }
    }

    public int size()
    {
        return tail.getSize();
    }

    // returns -in our case- the object we want its methods accessed
    public T getItem(int index) throws IndexOutOfBoundsException{
        if (index <= this.size() && index >= 0) {
            DLNode<T> node = head;
            int count = 0;

            while (node != null) {
                if (count == index) {
                    return node.getData();  // when the counter equals to the given index it returns the object
                }
                count++;
                node = node.getNext();
            }
        }
        else {
            throw new IndexOutOfBoundsException();  // if index is out of range throw exception
        }
        return null;
    }

}
