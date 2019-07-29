package binarysearch;

/**
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.
 * <p>
 * Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
 * <p>
 * Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
 * <p>
 * Return the minimum integer K such that she can eat all the bananas within H hours.
 * Example 1:
 * Input: piles = [3,6,7,11], H = 8
 * Output: 4
 * Example 2:
 * Input: piles = [30,11,23,4,20], H = 5
 * Output: 30
 * Example 3:
 * <p>
 * Input: piles = [30,11,23,4,20], H = 6
 * Output: 23
 * Note:
 * 1 <= piles.length <= 10^4
 * piles.length <= H <= 10^9
 * 1 <= piles[i] <= 10^9
 * 20190729 on the air
 */
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int H) {
        int l = 1, r = 1000000000;//max eating speed needed
        while (l < r) {
            int k = l + (r - l) / 2;
            int h = 0;
            for (int p : piles)
                h += (p + k - 1) / k;//等同于Math.ceil(p * 1.0 / k)
            if (h <= H) r = k;//h == H的时候k仍可能有压缩空间（压缩到h > H的时候停止）
            else l = k + 1;
        }
        return l;
    }
}
