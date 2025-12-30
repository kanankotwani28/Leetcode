class Solution {
    public boolean isAnagram(String s, String t) {
        int sl = s.length();
        int tl = t.length();
        
        if(sl != tl) return false;

        int []hash = new int[26];

        for(int i = 0; i<sl ; i++)
        {
            hash[s.charAt(i) - 'a']++;
            hash[t.charAt(i) - 'a']--;
        }

        for(int count : hash)
        {
            if(count != 0)
                return false;
        }

        return true;
       
    }
}