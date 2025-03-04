public class PQHeap implements PriorityQueue {
    /**
     * member variables
     */
    protected Player[] heap;
    protected int size;

    /**
     * constructors
     */
    public PQHeap() {
        heap = new Player[1000];
        size = 0;
    }

    public PQHeap(PQHeap other) {
        heap = new Player[1000];
        size = other.size;
        for (int i = 0; i < size; i++) {
            heap[i] = other.heap[i];
        }
    }

    /**
     * heap traversal methods:
     * getting parent index
     * getting child index (both left and right)
     */
    public int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    public int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    public int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    public int getLargestChildIndex(int index) {
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

    /**
     * internal manipulation of heap information
     */
    private void swap(int i, int j) {
        Player temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void heapifyUp(int index) {
        try {
            while (heap[index].getScore() > heap[getParentIndex(index)].getScore()) {
                swap(index, getParentIndex(index));
                index = getParentIndex(index);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("top of the list reached");
        }
    }

    private void heapifyDown(int index) {
        try {
            while (heap[index].getScore() < heap[getLargestChildIndex(index)].getScore()) {
                swap(index, getLargestChildIndex(index));
                index = getLargestChildIndex(index);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("bottom of the list reached");
        }
    }
    /**
     * PriorityQueue method implementations
     */
    public void add(Player p) {
        heap[size] = p;
        heapifyUp(size);
        size++;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear() {
        this.heap = new Player[1000];
        this.size = 0;
    }

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

    public void printFirst10() {
        for (int i = 0; i < 10; i++) {
            System.out.println(heap[i].getScore());
        }
    }
}
