class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int match[]: matches){
            int winner = match[0];
            int losser = match[1];

            map.putIfAbsent(winner,0);
            map.put(losser,map.getOrDefault(losser,0)+1);
        }

        List<Integer> noloss = new ArrayList<>();
        List<Integer> oneloss = new ArrayList<>();
        for(Map.Entry<Integer,Integer> mp : map.entrySet()){
            int player = mp.getKey();
            int count = mp.getValue();

            if(count == 0)
                noloss.add(player);
            else if(count == 1)
                oneloss.add(player);
        }

        Collections.sort(noloss);
        Collections.sort(oneloss);

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(noloss);
        ans.add(oneloss);

        return ans;
    }
}