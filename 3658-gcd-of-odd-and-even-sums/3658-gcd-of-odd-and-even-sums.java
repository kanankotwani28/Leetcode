class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd = 0;
        int sumEven = 0;

        for(int i = 1 ; i<=n*2 ; i++){
            if(i%2 == 0) sumEven+=i;
            if(i%2 == 1) sumOdd +=i;
        }

        return findGcd(sumOdd,sumEven);
    }

    private static int findGcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
