public int[] countMentions(int numberOfUsers, List<List<String>> events) {

    // Convert to array + sort
    events.sort((a, b) -> {
        int t1 = Integer.parseInt(a.get(1));
        int t2 = Integer.parseInt(b.get(1));
        if (t1 != t2) return t1 - t2;

        // same timestamp: OFFLINE must come before MESSAGE
        if (a.get(0).equals("OFFLINE") && b.get(0).equals("MESSAGE")) return -1;
        if (a.get(0).equals("MESSAGE") && b.get(0).equals("OFFLINE")) return 1;
        return 0;
    });

    boolean[] online = new boolean[numberOfUsers];
    int[] offlineUntil = new int[numberOfUsers];
    int[] mentions = new int[numberOfUsers];

    Arrays.fill(online, true);

    for (List<String> e : events) {
        String type = e.get(0);
        int time = Integer.parseInt(e.get(1));
        String data = e.get(2);

        // auto-reactivate users
        for (int u = 0; u < numberOfUsers; u++) {
            if (!online[u] && offlineUntil[u] <= time) {
                online[u] = true;
            }
        }

        if (type.equals("OFFLINE")) {
            int id = Integer.parseInt(data);
            online[id] = false;
            offlineUntil[id] = time + 60;
        } 
        else { // MESSAGE
            String[] tokens = data.split(" ");
            for (String token : tokens) {
                if (token.equals("ALL")) {
                    for (int i = 0; i < numberOfUsers; i++) mentions[i]++;
                } 
                else if (token.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++)
                        if (online[i]) mentions[i]++;
                } 
                else if (token.startsWith("id")) {
                    int id = Integer.parseInt(token.substring(2));
                    mentions[id]++;
                }
            }
        }
    }

    return mentions;
}
