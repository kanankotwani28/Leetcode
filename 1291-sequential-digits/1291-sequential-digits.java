class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        String s = "123456789";

        int lowD = String.valueOf(low).length();
        int highD = String.valueOf(high).length();

        for(int len = lowD ; len<=highD ; len++){
            for(int i = 0 ; i<= 9-len ; i++){
                String sub = s.substring(i,i+len);
                int num = Integer.parseInt(sub);

                if(num >=low && num<=high) ans.add(num);
            }
        }
        return ans;
    }
}