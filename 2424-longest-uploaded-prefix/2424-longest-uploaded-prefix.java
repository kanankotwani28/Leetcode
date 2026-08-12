class LUPrefix {
    HashSet <Integer> uploaded;
    int next ;
    public LUPrefix(int n) {
        uploaded = new HashSet<>();
        next = 1;
    }
    
    public void upload(int video) {
        uploaded.add(video);
        while(uploaded.contains(next))
            next++;
    }
    
    public int longest() {
        return next-1;
    }
}

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix obj = new LUPrefix(n);
 * obj.upload(video);
 * int param_2 = obj.longest();
 */