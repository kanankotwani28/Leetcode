class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();
        int left = 0;
        int right = n - 1;

        // remove harmless leading 'L'
        while (left < n && directions.charAt(left) == 'L') 
            left++;

        // remove harmless trailing 'R'
        while (right >= 0 && directions.charAt(right) == 'R') 
            right--;

        if (left > right) return 0; // no collision region

        int collisions = 0;
        for (int i = left; i <= right; i++) {
            if (directions.charAt(i) != 'S') {
                collisions++; 
            }
        }
        return collisions;
    }
}
