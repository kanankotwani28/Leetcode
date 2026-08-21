class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        for (int pile : piles)
            pq.add(pile);
        int i = 0;
        while( i < k ){
            int num = pq.poll();
            pq.add(num - (num/2));
            i++;
        }
        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }
}

// given: piles array having number of stones in the ith pile
// choose any pile and remove the floor of the asked 
// in the max heap we can store the piles value and pop out the values accordingly
// pop the max value extraxt it perfrom floor then add into pq do this operations k times 
// sum up the array and return 
