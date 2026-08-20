class Solution {

    List<Integer> heap;
    int size;

    private void intialize(){
        heap = new ArrayList<>();
        size = 0;
    }

    private void heapifyUp(int index ){
        int parent = (index -1 ) / 2;
        if(parent < size && heap.get(index) > heap.get(parent)){
            int temp = heap.get(parent);
            heap.set(parent,heap.get(index));
            heap.set(index,temp);
            heapifyUp(parent);
        }
    }

    private int extractMax(){
        int max = heap.get(0);
        heap.set(0,heap.get(size-1));
        heap.remove(size-1);
        size--;
        if(size > 0) heapifyDown(0);
        return max;
    }

    private void heapifyDown(int index ){
        int largest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if(leftChild < size && heap.get(leftChild) > heap.get(index))
            largest = leftChild;
        if(rightChild < size  && heap.get(rightChild) > heap.get(largest))
            largest = rightChild;

        if(largest!=index){
            int temp = heap.get(largest);
            heap.set(largest, heap.get(index));
            heap.set(index, temp);
            heapifyDown(largest);
        }
    }

    public int lastStoneWeight(int[] stones) {
        int n = stones.length;
        intialize();
        for(int i = 0 ; i< n ; i++){
            heap.add(size,stones[i]);
            size++;
            heapifyUp(size-1);
        }

        while(size > 1){
            int x = extractMax();
            int y = extractMax();

            if( x!= y){
                int rem  = x-y;
                heap.add(rem);
                size++;

                heapifyUp(size -1);
            }
        }
        
        return size == 0 ? 0 : heap.get(0);

    }
}