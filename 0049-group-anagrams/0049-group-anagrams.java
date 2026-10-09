
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        int n = strs.length;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            List<String> sqs = new ArrayList<>();
            sqs.add(strs[i]);
            visited[i] = true;

            for (int j = i + 1; j < n; j++) {
                if (!visited[j] && isAnagram(strs[i], strs[j])) {
                    sqs.add(strs[j]);
                    visited[j] = true;
                }
            }

            ans.add(sqs);
        }

        return ans;
    }

    private boolean isAnagram(String s, String t) {
        int sl = s.length();
        int tl = t.length();

        if (sl != tl) return false;

        int[] hash = new int[26];

        for (int i = 0; i < sl; i++) {
            hash[s.charAt(i) - 'a']++;
            hash[t.charAt(i) - 'a']--;
        }

        for (int count : hash) {
            if (count != 0) return false;
        }

        return true;
    }
}
