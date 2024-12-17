public class HeapSort {

    private static int total;

    private static void swap(DoublyLinkedList<City> city_list, int a, int b) {
        City temp = city_list.getItem(a);
        city_list.addAtIndex(city_list.getItem(b), a);  // the dll has also a method called addAtIndex which adds a given object to a given position replacing the item it already has.
        city_list.addAtIndex(temp, b);
    }

    private static void heapify(DoublyLinkedList<City> city_list, int i) {
        int left = i*2+1;
        int right = i*2+2;
        int smallest = i;
        // If left child is smaller than root
        if (left <= total && city_list.getItem(left).compareTo(city_list.getItem(smallest)) < 0)
            smallest = left;
        // If right child is smaller than the smallest
        if (right <= total && city_list.getItem(right).compareTo(city_list.getItem(smallest)) < 0)
            smallest = right;
        // If smallest is not root swap and continue heapifying
        if (smallest != i) {
            swap(city_list, i, smallest);
            heapify(city_list, smallest);
        }
    }

    static void sort(DoublyLinkedList<City> city_list) {
        total = city_list.size()-1;
        // Build heap
        for (int i=total/2; i>=0; i--)
            heapify(city_list, i);
        // Extract an element from heap
        for (int i=total; i>0; i--) {
            swap(city_list, 0, i);
            total--;
            heapify(city_list, 0);
        }
    }
}
