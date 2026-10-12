class Solution {
    public int maxElement(int[]piles)
    {
        int n = piles.length, maxi = Integer.MIN_VALUE;
        for(int i = 0 ; i< n ;i++)
        {
            maxi = Math.max(maxi,piles[i]);
        }
        return maxi;
    }
    public int calculateHours(int []v, int n)
    {
        int totalH = 0;
        for (int i = 0; i <v.length; i++) {
            totalH += Math.ceil((double)(v[i]) / (double)(n));
        }
        return totalH;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int low = 1 , high = maxElement(piles);

        while(low<=high)
        {
            int mid =(low+high)/2;
            int totalHours = calculateHours(piles,mid);

            if(totalHours <= h)
                high = mid-1;
            else
                low = mid+1;
        }
        return low;  
    }
}