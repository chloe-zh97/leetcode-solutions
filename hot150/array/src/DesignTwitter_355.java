import java.util.*;

public class DesignTwitter_355 {
    public static void main(String[] args) {
        Twitter tw = new Twitter();
        tw.postTweet(1,5);
        tw.getNewsFeed(1);
        tw.follow(1,2);
        tw.postTweet(2,6);
        System.out.println(tw.getNewsFeed(1));
    }

    static class Twitter {
        private int timestamp = 0;
        private Map<Integer, User> userMap;

        class User {
            int id;
            Set<Integer> follows;
            Twit head = null;

            public User(int _id) {
                id = _id;
                follows = new HashSet<>();
                follows.add(id);
            }

            public void post(int tid) {
                Twit twit = new Twit(tid);
                twit.next = head;
                head = twit;
            }

            public void follow(int id) {
                follows.add(id);
            }

            public void unfollow(int id) {
                follows.remove(id);
            }
        }

        class Twit {
            int id;
            int time;
            Twit next;

            public Twit(int _id) {
                id = _id;
                time = timestamp++;
                next = null;
            }
        }

        public Twitter() {
            userMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            if(!userMap.containsKey(userId)) {
                User user = new User(userId);
                userMap.put(userId, user);
            }
            User user = userMap.get(userId);
            user.post(tweetId);
        }

        public List<Integer> getNewsFeed(int userId) {
            if(!userMap.containsKey(userId)) return new ArrayList<>();
            User user = userMap.get(userId);
            PriorityQueue<Twit> pq = new PriorityQueue<>((a,b) -> b.time - a.time);
            for(int u: user.follows) {
                User uu = userMap.get(u);
                Twit p = uu.head;
                while(p != null) {
                    pq.offer(p);
                    p = p.next;
                }
            }

            List<Integer> ans = new LinkedList<>();
            int cnt = 0;
            while(!pq.isEmpty() && cnt < 10) {
                ans.add(pq.poll().id);
                cnt++;
            }
            return ans;
        }

        public void follow(int followerId, int followeeId) {
            if(!userMap.containsKey(followerId)) {
                User a = new User(followerId);
                userMap.put(followerId, a);
            }

            if(!userMap.containsKey(followeeId)) {
                User a = new User(followeeId);
                userMap.put(followeeId, a);
            }
            userMap.get(followerId).follows.add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if(!userMap.containsKey(followerId)) return;

            User a = userMap.get(followerId);
            a.unfollow(followeeId);
        }
    }

}
