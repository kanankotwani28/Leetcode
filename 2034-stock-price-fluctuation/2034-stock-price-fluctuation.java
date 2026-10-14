
import java.util.*;

class StockPrice {
    private final Map<Integer, Integer> prices;
    private final TreeMap<Integer, Integer> priceCounts;
    private int latestTimestamp;

    public StockPrice() {
        prices = new HashMap<>();
        priceCounts = new TreeMap<>();
        latestTimestamp = 0;
    }

    public void update(int timestamp, int price) {
        // If this timestamp already exists, remove its old price.
        if (prices.containsKey(timestamp)) {
            int oldPrice = prices.get(timestamp);
            removePrice(oldPrice);
        }

        // Store the corrected price.
        prices.put(timestamp, price);

        // Add the new price to the frequency map.
        priceCounts.put(
            price,
            priceCounts.getOrDefault(price, 0) + 1
        );

        // Maintain the latest timestamp.
        latestTimestamp = Math.max(latestTimestamp, timestamp);
    }

    public int current() {
        return prices.get(latestTimestamp);
    }

    public int maximum() {
        return priceCounts.lastKey();
    }

    public int minimum() {
        return priceCounts.firstKey();
    }

    private void removePrice(int price) {
        int count = priceCounts.get(price);

        if (count == 1) {
            priceCounts.remove(price);
        } else {
            priceCounts.put(price, count - 1);
        }
    }
}


/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */