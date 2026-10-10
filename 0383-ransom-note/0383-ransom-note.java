class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int hash[] = new int[26];
        if (ransomNote.length() > magazine.length()) return false;
        for (int i = 0; i < magazine.length(); i++) {
            hash[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            hash[ransomNote.charAt(i) - 'a']--;
            if (hash[ransomNote.charAt(i) - 'a'] < 0) return false;
        }

        return true;
    }
}

// ransom note must be substing of magazine
//count the number of freq od all character in

