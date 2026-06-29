class MedianFinder {
    PriorityQueue<Integer> mx; 
    PriorityQueue<Integer> mn;

    public MedianFinder() {
        mx = new PriorityQueue<Integer>(Collections.reverseOrder());
        mn = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        if(mx.size() == 0 || mx.peek() >=num )
            mx.offer(num);
        else mn.offer(num);

        if(mx.size() > mn.size() + 1)
            mn.offer(mx.poll());
        else if(mn.size() > mx.size())
            mx.offer(mn.poll());
    }
    
    public double findMedian() {
        if(mn.size()  == mx.size()) return (mn.peek()+mx.peek())/2.0;
        else return mx.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */