class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double low = Double.MAX_VALUE, high = Double.MIN_VALUE;

        // Compute total area and bounds
        for (int[] s : squares) {
            double y = s[1];
            double l = s[2];
            totalArea += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double half = totalArea / 2.0;

        // Binary search
        for (int iter = 0; iter < 100; iter++) {
            double mid = (low + high) / 2.0;
            double below = 0;

            for (int[] s : squares) {
                double y = s[1];
                double l = s[2];

                if (mid <= y) {
                    // fully above → contributes nothing below
                } else if (mid >= y + l) {
                    // fully below
                    below += l * l;
                } else {
                    // partially cut
                    below += (mid - y) * l;
                }
            }

            if (below < half) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
