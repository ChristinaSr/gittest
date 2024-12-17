//Doubly Linked List Nodes with Generics
public class DLNode<T> {
    private T item;
    private DLNode<T> next;
    private DLNode<T> prev;
    private static int size = 0;

    //Constructor
    DLNode(T item) {
        this.item = item;
        this.next = null;
        this.prev = null;
        size++;
    }
    //Getters and Setters
    T getData() {
        return item;
    }

    public void setData(T item) {
        this.item = item;
    }

    void setNext(DLNode<T> next) {
        this.next = next;
    }

    DLNode<T> getNext() {
        return next;
    }

    void setPrev(DLNode<T> prev) {
        this.prev = prev;
    }

    DLNode<T> getPrev() {
        return prev;
    }

    int getSize() { return size; }

}
