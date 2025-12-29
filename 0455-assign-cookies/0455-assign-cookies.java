import java.util.Arrays;

public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); // sort greed factors
        Arrays.sort(s); // sort cookie sizes

        int child = 0;
        int cookie = 0;

        while (child < g.length && cookie < s.length) {
            if (s[cookie] >= g[child]) {
                child++; // move to next child
            }
            cookie++; // always move to next cookie
        }

        return child; // number of content children
    }
}
