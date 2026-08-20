class Solution {
    class Pair{
        int freq;
        int el;
        Pair(int freq , int el){
            this.freq = freq;
            this.el = el;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.freq - b.freq);
        for(Map.Entry<Integer,Integer> m : map.entrySet()){
            pq.add(new Pair(m.getValue(), m.getKey()));
            if(pq.size() > k){
                pq.poll();
            }
        }

        int ans [] = new int[k];
        for(int i = 0 ; i< k ; i++){
            ans[i] = pq.poll().el;
        }

        return ans;
    }
}

//build the frq according to the given nums array 
//