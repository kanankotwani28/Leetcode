class Solution {
    class Pair{
        int []pair;
        int n;
        Pair(int[] pair , int n){
            this.pair = pair;
            this.n = n;
        }
    }

    private int distance(int x , int y){
        return ( x*x + y*y);
    }
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> b.n-a.n);
        // max - heap
        for(int []point : points){
            int x = point[0];
            int y = point[1];

            int dis = distance(x,y);
            pq.add(new Pair( point , dis));

            if(pq.size() > k){
                pq.poll();
            }
        }

        int ans[][] = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll().pair;
        }

        return ans;
    }
}

// need a function that calculates the distance between the origin
// in the pq store the coordinates along with the distance 
// make sure the pq is of size k 