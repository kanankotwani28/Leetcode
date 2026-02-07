class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();

        if(p.length() > s.length()) return ans;

        int []need = new int[26];
        for(char ch: p.toCharArray()){
            need[ch - 'a']++;
        }

        int []window = new int[26];
        int l = 0 , r = 0 ;

        while(r < s.length()){
            window[s.charAt(r) - 'a']++;
            
            while(window[s.charAt(r) - 'a'] > need[s.charAt(r) - 'a']){
                window[s.charAt(l) - 'a']--;
                l++;
            }

            if(r-l+1 == p.length()){
                ans.add(l);
            }
            r++;
        }
    return ans;
    }
}