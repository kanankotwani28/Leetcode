
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,int need1, int need2) {

        long separate = (long) need1 * cost1 + (long) need2 * cost2;
        long onlyBoth = (long) Math.max(need1, need2) * costBoth;
        long common = Math.min(need1, need2);
        long mixed = common * costBoth;

        if (need1 > need2) {
            mixed += (long) (need1 - need2) * cost1;
        } else {
            mixed += (long) (need2 - need1) * cost2;
        }

        return Math.min(separate, Math.min(onlyBoth, mixed));
    }
}
