public class PQHeap implements PriorityQueue {
    // member variables
    protected Player[] heap;
    protected int size;

    // constructors

    /**
     * creates an empty Heap instance with 1000 available spaces.
     */
    public PQHeap() {
        heap = new Player[1000];
        size = 0;
    }

    /**
     * creates a new Heap instance, copying another instance of Heap
     * @param other a different instance of heap
     */
    public PQHeap(PQHeap other) {
        heap = new Player[1000];
        size = other.size;
        for (int i = 0; i < size; i++) {
            heap[i] = other.heap[i];
        }
    }

    /**
     * takes an instance of heap, copies all it's member variables, then returns it
     * @return deep copy heap
     */
    public PQHeap createClone() {
        PQHeap clonedHeap = new PQHeap();
        Player[] clonedArr = new Player[1000];
        int size = this.size;
        for (int i = 0; i < size; i++) {
            clonedArr[i] = this.heap[i];
        }
        clonedHeap.heap = clonedArr;
        clonedHeap.size = size;
        return clonedHeap;
    }

    // heap traversal methods

    /**
     * returns parent of given index
     * @param index given index
     * @return index of parent
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * returns the left child of the given index
     * @param index given index
     * @return index of left child
     */
    private int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    /**
     * returns the right child of the given index
     * @param index given index
     * @return index of right child
     */
    private int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    /**
     * compares the index of an elements left and right child, and returns the index of which one is larger
     * @param index given index
     * @return the largest index, between the left and right index
     */
    private int getLargestChildIndex(int index) {
        if (heap[getLeftChildIndex(index)].getScore() < heap[getRightChildIndex(index)].getScore()) {
            return getRightChildIndex(index);
        }
        else if (heap[getLeftChildIndex(index)].getScore() > heap[getRightChildIndex(index)].getScore()) {
            return getLeftChildIndex(index);
        }
        else {
            return -1;
        }
    }

    // heap manipulation methods

    /**
     * swaps elements at indexes i and j in the heap
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        Player temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * ensures heap integrity, beginning upwards from the specified index
     * @param index starting index ensure heap integrity from
     */
    private void heapifyUp(int index) {
        try {
            int check = index;
            while (heap[index].getScore() > heap[getParentIndex(index)].getScore()) {
                check = getParentIndex(index);
                swap(index, getParentIndex(index));
                index = check;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("top of the list reached");
        }
    }

    /**
     * ensures heap integrity, beginning downwards from the specified index.
     * the function ends when there is no more heap violation.
     * conditions for no more heap violation include when:
     *  - the bottom of the heap is reached (index out of bounds for array)
     *  - the bottom of the heap is reached (index of child exceeds size of heap)
     *  - no heap property violation is found
     * @param index starting index to ensure heap integrity from
     */
    private void heapifyDown(int index) {
        try {
            int check = index;
            while ((heap[index].getScore() < heap[getLargestChildIndex(check)].getScore() && getLargestChildIndex(check) < size)) {
                check = getLargestChildIndex(index);
                swap(index, getLargestChildIndex(index));
                index = check;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("bottom of the list reached");
        }
    }

    // priorityQueue methods implementation (public methods

    /**
     * method derived from PriorityQueue interface,
     * adds an element to the heap, then ensures heap property integrity
     * @param p Player to be added to heap
     */
    public void add(Player p) {
        heap[size] = p;
        heapifyUp(size);
        size++;
    }

    /**
     * method derived from PriorityQueue interface,
     * returns the size of the heap
     * @return size of heap as an integer
     */
    public int getSize() {
        return size;
    }

    /**
     * method derived from PriorityQueue interface,
     * returns true if the heap is empty, returns false otherwise
     * @return return true if empty, false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * method derived from PriorityQueue interface,
     * clears a heap of all elements
     */
    public void clear() {
        this.heap = new Player[1000];
        this.size = 0;
    }

    /**
     * method derived from PriorityQueue interface,
     * returns the highest score player, and removes them from the heap
     * @return return the Player with the highest score in the heap
     */
    public Player getHighestScorePlayer() {
        if (size == 0) {
            return null;
        }
        Player highest = new Player(heap[0]);
        heap[0] = heap[this.size - 1];
        heapifyDown(0);
        size--;
        return highest;
    }

    public void printFirstTen() {
        for (int i = 0; i < 10; i++) {
            System.out.print(this.heap[i].getScore());
        }
        System.out.println();
    }
}
