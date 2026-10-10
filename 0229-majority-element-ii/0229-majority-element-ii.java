import java.util.*;

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Integer el1 = null, el2 = null;
        int cn1 = 0, cn2 = 0;

        for (int num : nums) {
            if (el1 != null && num == el1) {
                cn1++;
            } else if (el2 != null && num == el2) {
                cn2++;
            } else if (cn1 == 0) {
                el1 = num;
                cn1 = 1;
            } else if (cn2 == 0) {
                el2 = num;
                cn2 = 1;
            } else {
                cn1--;
                cn2--;
            }
        }

        // Verify the candidates
        cn1 = 0;
        cn2 = 0;
        for (int num : nums) {
            if (el1 != null && num == el1) cn1++;
            if (el2 != null && num == el2) cn2++;
        }

        List<Integer> result = new ArrayList<>();
        int threshold = nums.length / 3;
        if (cn1 > threshold) result.add(el1);
        if (el2 != null && !el2.equals(el1) && cn2 > threshold) result.add(el2);

        return result;
    }
}
