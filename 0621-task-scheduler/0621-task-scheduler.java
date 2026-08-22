class Solution {
    class Pair{
        int ch;
        int freq;
        Pair(int ch , int freq){
            this.ch = ch ;
            this.freq = freq;
        }
    }

    class Cooldown{
        int ch;
        int freq;
        int schtime;
        Cooldown(int ch , int freq , int schtime){
            this.ch = ch;
            this.freq = freq;
            this.schtime = schtime;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        int []freq = new int[26];
        for(int i = 0 ; i < tasks.length ; i++){
            freq[tasks[i] - 'A']++;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> b.freq - a.freq);
        for(int i = 0; i< 26 ; i++){
            if(freq[i] > 0) pq.add(new Pair(i,freq[i]));
        }

        Queue<Cooldown> q = new LinkedList<>();

        int time = 0;
        
        while(!pq.isEmpty() || !q.isEmpty()){

            while(!q.isEmpty() &&
                    q.peek().schtime <= time){
                        Cooldown c = q.poll();
pq.add(new Pair(c.ch, c.freq));
            }

            if(!pq.isEmpty()){
                Pair p = pq.poll();
                time++;
                p.freq--;
                if (p.freq > 0) {
                    q.add(new Cooldown(
                        p.ch,
                        p.freq,
                        time + n
                    ));
                }
            }

            else{
                time++;
            }
        }

    return time;
    }
}

// lets make a count of each of the character along with the frweq
// the one with the highets freq will be arrangeed first atleast at an intervela of n 
// the updated inyterval is then added into the heap 
// return the length of the ans arry

// freq i supdated 
// not the last one 
