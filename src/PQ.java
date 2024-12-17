public class PQ {

    private DoublyLinkedList<City> heap;    // the heap to store data in
    private int size;   // size of the queue

    public PQ(int capacity) {
        this.heap = new DoublyLinkedList<>();
        for (int i=0; i<capacity; i++) { heap.addLast(null); }
        this.size = 0;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public void insert(City item) {
        // if the heap is empty
        if (isEmpty()) {
            heap.addFirst(item);
        }
        else {
            heap.addAtIndex(item, size);
        }
        swim(size);
        size++;
    }

    public City max() {
        if (isEmpty()) {
            return null;
        }
        else {
            return heap.getFirst();
        }
    }

    public City getMax() {
        if (this.size == 0) {
            return null;
        }
        if (this.size == 1) {
            return heap.removeFirst();
        }
        City item = heap.getFirst();
        heap.addAtIndex(heap.removeAtIndex(size()-1), 0);
        size--;
        sink(0);
        return item;
    }

    public City remove(int id) {
        if (isEmpty()) {
            return null;    // the queue is empty
        }
        for (int i=0; i<size; i++) {
            if (heap.getItem(i).getID() == id) {
                size--;
                City item = heap.getItem(i);
                swap(heap, i, size);
                heap.addAtIndex(null, size);

                if (i == size) { return item; }     // the removed item was at the end of the queue

                City check_sink = heap.getItem(i);
                sink(i);

                if (heap.getItem(i) == check_sink) { swim(i); }     // the sink did not work, so swim

                return item;
            }
        }
        return null;    // the function will return null if it does not find the id in the queue
    }

    public int size() {
        return size;
    }

    private void swim(int i) {
        if (i == 0) {
            return;
        }
        int parent = (i-1)/2;   // the parent of the new stored element

        while (i >= 0 && heap.getItem(i).compareTo(heap.getItem(parent)) > 0) {
            swap(heap, i, parent);
            i = parent;
            parent = (i-1)/2;
        }
    }

    private void sink(int i) {
        int left = 2*i+1;
        int right = 2*i+2;
        if (heap.getItem(left) == null) { return; }

        while (left < size() && heap.getItem(left) != null) {
            int max = left;
            if (right < size() && heap.getItem(left).compareTo(heap.getItem(right)) < 0) {
                max = right;
            }
            // root has larger priority than the child with the largest priority
            if (heap.getItem(i).compareTo(heap.getItem(max)) >= 0) { return; }
            swap(heap, i, max);
            i = max;
            left = 2*i+1;
            right = 2*i+2;
        }
    }

    private void swap(DoublyLinkedList<City> list, int a, int b) {
        City temp = list.getItem(a);
        list.addAtIndex(list.getItem(b), a);
        list.addAtIndex(temp, b);
    }

}
