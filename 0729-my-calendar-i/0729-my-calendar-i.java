class MyCalendar {
    class Node{
        Node left, right;
        boolean booked;
        boolean lazy;
    }

    Node root;
    int MIN = 0;
    int MAX = (int)1e9;
    public MyCalendar() {  
        root = new Node();
    }
    
    public boolean book(int startTime, int endTime) {
        // query returns true only when the event is not overlapping at all
        if(query(root,MIN , MAX , startTime , endTime-1)) return false;
        update(root,MIN,MAX,startTime,endTime-1);
        return true;
    }

    private boolean query(Node node , int l , int r , int ql , int qr){
        if(node == null) return false;
        if(qr < l || r < ql ) return false; // no overlapping 
        if(node.booked) return true;
        if(l == r) return false;

        int mid = (l + r) / 2;

        boolean leftQ = query(node.left , l , mid , ql , qr);
        boolean rightQ = query(node.right , mid+1 , r , ql , qr);

        return leftQ || rightQ;
    }

    private void update(Node node , int l , int r , int ql , int qr){
        if(qr < l || r < ql) return;
        if (ql <= l && r <= qr) {
            node.booked = true;
            node.lazy = true;
            return;
        }

        int mid = (l+r) / 2;
        if(node.left == null) node.left = new Node();
        if(node.right == null) node.right = new Node();

        if(node.lazy){
            node.left.booked = true;
            node.left.lazy = true;

            node.right.booked = true;
            node.right.lazy = true;

            node.lazy = false;
        }

        update(node.left , l , mid , ql , qr);
        update(node.right , mid+1 , r , ql , qr);

        node.booked = node.left.booked && node.right.booked;

    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(startTime,endTime);
 */

 // could be either partial overlap or complete overlap 