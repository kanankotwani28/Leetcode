class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if(pattern.length() != words.length) return false;
        HashMap<Character,String> map = new HashMap<>();
        for(int i = 0 ; i< pattern.length() ; i++){
            char character = pattern.charAt(i);
            String word = words[i];

            if(map.containsKey(character)){
                if(!map.get(character).equals(word))
                    return false;
            }

           else{
                if(map.containsValue(word))
                    return false;
           }
           map.put(character,word);
        }

        return true;
    }
}