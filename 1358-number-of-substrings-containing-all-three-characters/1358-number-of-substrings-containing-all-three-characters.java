class Solution {
    public int numberOfSubstrings(String s) {
        
        int ans=0;
        int count[] = new int[3];
        Arrays.fill(count,-1);

        for(int i = 0;i<s.length();i++)
        {
            count[s.charAt(i)-'a']=i;
            if(count[0]!=-1 && count[1]!=-1 && count[2]!=-1)
            {
                ans += 1 + Math.min(Math.min(count[0], count[1]), count[2]);
            }
        }
        return ans;
    }
}