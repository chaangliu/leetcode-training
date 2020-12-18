package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DesignTwitter {
    /**
     * 题意：设计一个twitter，实现关注、获取最近10条feed流。
     * 解法：主要就是getNewsFeed的实现，我用了merge K sorted list那种思路，需要注意的是要clone，否则remove掉的tweet就没法再query到了。
     */
    class Twitter {

        /**
         * Initialize your data structure here.
         */
        public Twitter() {

        }

        class TweetObject {
            int tweetId;
            int timeStamp;

            TweetObject(int id, int time) {
                tweetId = id;
                timeStamp = time;
            }
        }

        int timeStamp = 0;
        Map<Integer, List<TweetObject>> tweetMap = new HashMap<>(); // user => tweets
        Map<Integer, HashSet<Integer>> followMap = new HashMap<>(); // user => people he followed

        /**
         * Compose a new tweet.
         */
        public void postTweet(int userId, int tweetId) {
            tweetMap.putIfAbsent(userId, new ArrayList<>());
            tweetMap.get(userId).add(new TweetObject(tweetId, timeStamp++));
        }

        /**
         * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
         */
        public List<Integer> getNewsFeed(int userId) {
            PriorityQueue<List<TweetObject>> q = new PriorityQueue<>((a, b) -> b.get(b.size() - 1).timeStamp - a.get(a.size() - 1).timeStamp);
            for (int f : followMap.getOrDefault(userId, new HashSet<>())) {
                if (tweetMap.containsKey(f) && f != userId) // 有个case是自己关注自己，要去掉
                    q.offer(tweetMap.get(f));
            }
            if (tweetMap.containsKey(userId) && tweetMap.get(userId).size() > 0) q.offer(tweetMap.get(userId));
            List<Integer> res = new ArrayList<>();
            PriorityQueue<List<TweetObject>> qCopy = new PriorityQueue<>(q);
            while (!qCopy.isEmpty() && res.size() < 10) {
                List<TweetObject> list = new ArrayList<>(qCopy.poll()); // 用复制一个副本
                if (list.size() > 0) res.add(list.get(list.size() - 1).tweetId);
                list.remove(list.size() - 1);
                if (list.size() > 0) qCopy.offer(list);
            }
            return res;
        }

        /**
         * Follower follows a followee. If the operation is invalid, it should be a no-op.
         */
        public void follow(int followerId, int followeeId) {
            followMap.putIfAbsent(followerId, new HashSet<>());
            followMap.get(followerId).add(followeeId);
        }

        /**
         * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
         */
        public void unfollow(int followerId, int followeeId) {
            if (followMap.containsKey(followerId)) followMap.get(followerId).remove(followeeId);
        }
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
}
