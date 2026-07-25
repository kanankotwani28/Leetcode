class Solution {
    public int maxProduct(int n) {
        int hash[] = new int[10];
        while(n>0){
            int d = n % 10;
            hash[d]++;
            n /= 10;
        }
        
        int multi = 1;
        int cnt = 0;
        for(int d=hash.length-1 ; d>=0 ; d--){
            
            if(hash[d] == 1 && cnt!= 2){
                multi*= d;
                cnt ++;
            } 
            else if(hash[d] > 1){
                if(cnt == 0) return d*d;
                else { 
                    multi*= d; 
                    cnt++;
                }
            }

            if(cnt == 2) return multi;
        }
        return multi;
    }
}