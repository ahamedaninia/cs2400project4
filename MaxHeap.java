import java.util.ArrayList;
import java.util.Arrays;

public final class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>{
    
    private T[] heap; //array of heap entries
    private int lastIndex; //index of last entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000; //10,000
    private int swaps;

    public MaxHeap() {
        this(DEFAULT_CAPACITY); //call next constructor
    } //end default constructor

    public MaxHeap(int initialCapacity) {
        //is initial capacity too small?
        if(initialCapacity < DEFAULT_CAPACITY) {
            initialCapacity = DEFAULT_CAPACITY;
        } else {
            checkCapacity(initialCapacity);
        }

        //the cast is safe because the new array contains all null entries
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        integrityOK = true;
    } //end constructor

    //smart way to heap
    public MaxHeap(ArrayList<T> entries) {
        this(entries.size()); //call other constructor
        assert integrityOK = true;

        //copy given array to data field
        for (int i = 0; i < entries.size(); i++) {
            heap[i + 1] = entries.get(i);
        } // end for

        //create heap
        swaps = 0;
        lastIndex = entries.size();
        for(int root = lastIndex/2; root > 0; root--) {
            swaps += reheap(root);
        } //end for
    }//end constructor

    //add a new entry to the heap
    public int add(T newEntry) {
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex/2; //index of parent at newIndex
        int swap = 0;

        while(parentIndex > 0 && newEntry.compareTo(heap[parentIndex]) > 0) {
            heap[newIndex] = heap[parentIndex];
            //update indices
            newIndex = parentIndex;
            parentIndex = newIndex/2;
            swap++;
        } //end while
        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
        return swap;
        
    } //end add

    public T removeMax() {
        checkIntegrity();
        T root = null;

        if(!isEmpty()) {
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        } //end if
        return root;

    }//end removeMax

    private int reheap(int rootIndex) {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2*rootIndex;
        int swap = 0;
        while(!done && (leftChildIndex <= lastIndex)) {
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;

            if((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0) {
                largerChildIndex = rightChildIndex;
            }//end if

            if(orphan.compareTo(heap[largerChildIndex]) < 0) {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2*rootIndex;
            } else {
                done = true;
            }
            swap++;
        } //end while
        heap[rootIndex] = orphan;
        return swap;
    } //end reheap

    public T getMax() {
        checkIntegrity();
        T root = null;
        if(!isEmpty()) {
            root = heap[1];
        }
        return root;    
    } //end getMax

    public boolean isEmpty() {
        return lastIndex < 1;
    }

    public int getSize() {
        return lastIndex;
    }

    public void clear() {
        checkIntegrity();
        while (lastIndex > -1) {
            heap[lastIndex] = null;
            lastIndex--;
        } //end while
        lastIndex = 0;
    }//end clear

    private void checkIntegrity() {
        if(!integrityOK) {
            throw new SecurityException("Resizable Array Bag object is corrupt");
        }
    }

    /** 
     * @param size the initial size of the bag
     */
    private void checkCapacity(int size) {
        if (size > MAX_CAPACITY) {
            throw new IllegalStateException(("Attempt to create a bag whose" 
            + "capacity exceeds allowed maximum of " + MAX_CAPACITY));

        }
    }

    //make sure there's enough room in the array
    private void ensureCapacity() {
        if(lastIndex >= heap.length - 1) { //if array full, double size
            int newLength = 2*heap.length;
            checkCapacity(newLength);
            heap = Arrays.copyOf(heap, newLength);
        }
    }

    public int getSwaps() {
        return swaps;
    }

}