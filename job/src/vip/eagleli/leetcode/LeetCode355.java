package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class LeetCode355 {
	public static void main(String[] args) {
		Twitter twitter = new Twitter();
		// ["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]
		// [[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]

		twitter.postTweet(1, 5);
		twitter.postTweet(1, 3);
		twitter.postTweet(1, 2);
		System.out.println(twitter.getNewsFeed(1));
	}

	static class Twitter {

		static int index;

		class Post {
			int userId;
			int tweetId;
			long timestamp;

			public Post(int userId, int tweetId, long timestamp) {
				super();
				this.userId = userId;
				this.tweetId = tweetId;
				this.timestamp = timestamp;
			}

		}

		Map<Integer, NavigableSet<Post>> recentNewsMap;
		Map<Integer, List<Integer>> followeeMap;
		Map<Integer, List<Post>> newsMap;
		static final int RECENT_NUM = 10;

		/** Initialize your data structure here. */
		public Twitter() {
			recentNewsMap = new HashMap<>();
			followeeMap = new HashMap<>();
			newsMap = new HashMap<>();
		}

		/** Compose a new tweet. */
		public void postTweet(int userId, int tweetId) {
			Post post = new Post(userId, tweetId, index++);
			if (recentNewsMap.get(userId) == null) {
				recentNewsMap.put(userId, new TreeSet<>((p1, p2) -> Long.compare(p2.timestamp, p1.timestamp)));
			}
			recentNewsMap.get(userId).add(post);

			if (newsMap.get(userId) == null) {
				newsMap.put(userId, new LinkedList<>());
			}
			newsMap.get(userId).add(post);

			List<Integer> list = followeeMap.get(userId);
			if (list == null) {
				return;
			}
			for (Integer followeeId : followeeMap.get(userId)) {
				if (followeeId == userId) {
					continue;
				}
				if (recentNewsMap.get(followeeId) == null) {
					recentNewsMap.put(userId, new TreeSet<>((p1, p2) -> Long.compare(p2.timestamp, p1.timestamp)));
				}
				recentNewsMap.get(followeeId).add(post);
			}
		}

		/** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
		public List<Integer> getNewsFeed(int userId) {
			List<Integer> ans = new ArrayList<>(RECENT_NUM);
			NavigableSet<Post> navigableSet = recentNewsMap.get(userId);
			if (navigableSet == null) {
				return ans;
			}
			Iterator<Post> iterator = navigableSet.iterator();
			for (int i = 0; i < RECENT_NUM && iterator.hasNext(); i++) {
				ans.add(iterator.next().tweetId);
			}
			return ans;
		}

		/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
		public void follow(int followerId, int followeeId) {
			if (followeeMap.get(followeeId) == null) {
				followeeMap.put(followeeId, new LinkedList<>());
			}
			followeeMap.get(followeeId).add(followerId);

			if (followerId == followeeId) {
				return;
			}
			if (recentNewsMap.get(followerId) == null) {
				recentNewsMap.put(followerId, new TreeSet<>((p1, p2) -> Long.compare(p2.timestamp, p1.timestamp)));
			}
			NavigableSet<Post> navigableSet = recentNewsMap.get(followerId);
			if (newsMap.get(followeeId) != null) {
				for (Post post : newsMap.get(followeeId)) {
					navigableSet.add(post);
				}
			}
		}

		/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
		public void unfollow(int followerId, int followeeId) {
			List<Integer> list = followeeMap.get(followeeId);
			if (list == null) {
				return;
			}
			if (list.remove(Integer.valueOf(followerId)) && followerId != followeeId) {
				NavigableSet<Post> navigableSet = recentNewsMap.get(followerId);
				if (navigableSet == null) {
					return;
				}
				Iterator<Post> iterator = navigableSet.iterator();
				while (iterator.hasNext()) {
					if (iterator.next().userId == followeeId) {
						iterator.remove();
					}
				}
			}
		}
	}

	static class Twitter2 {
		/**
		 * 推文类，是一个单链表（结点视角）
		 */
		private class Tweet {
			/**
			 * 推文 id
			 */
			private int tweetId;

			/**
			 * 发推文的时间戳
			 */
			private int timestamp;
			private Tweet next;

			public Tweet(int tweetId, int timestamp) {
				this.tweetId = tweetId;
				this.timestamp = timestamp;
			}
		}

		/**
		* 用户 id 和推文（单链表）的对应关系
		*/
		private Map<Integer, Tweet> twitter;

		/**
		 * 用户 id 和他关注的用户列表的对应关系
		 */
		private Map<Integer, Set<Integer>> followings;

		/**
		 * 全局使用的时间戳字段，用户每发布一条推文之前 + 1
		 */
		private static int timestamp = 0;

		/**
		 * 合并 k 组推文使用的数据结构（可以在方法里创建使用），声明成全局变量非必需，视个人情况使用
		 */
		private static PriorityQueue<Tweet> maxHeap;

		/**
		 * Initialize your data structure here.
		 */
		public Twitter2() {
			followings = new HashMap<>();
			twitter = new HashMap<>();
			maxHeap = new PriorityQueue<>((o1, o2) -> o2.timestamp - o1.timestamp);
		}

		/**
		 * Compose a new tweet.
		 */
		public void postTweet(int userId, int tweetId) {
			timestamp++;
			if (twitter.containsKey(userId)) {
				Tweet oldHead = twitter.get(userId);
				Tweet newHead = new Tweet(tweetId, timestamp);
				newHead.next = oldHead;
				twitter.put(userId, newHead);
			} else {
				twitter.put(userId, new Tweet(tweetId, timestamp));
			}
		}

		/**
		 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
		 */
		public List<Integer> getNewsFeed(int userId) {
			// 由于是全局使用的，使用之前需要清空
			maxHeap.clear();

			// 如果自己发了推文也要算上
			if (twitter.containsKey(userId)) {
				maxHeap.offer(twitter.get(userId));
			}

			Set<Integer> followingList = followings.get(userId);
			if (followingList != null && !followingList.isEmpty()) {
				for (Integer followingId : followingList) {
					Tweet tweet = twitter.get(followingId);
					if (tweet != null) {
						maxHeap.offer(tweet);
					}
				}
			}

			List<Integer> res = new ArrayList<>(10);
			int count = 0;
			while (!maxHeap.isEmpty() && count < 10) {
				Tweet head = maxHeap.poll();
				res.add(head.tweetId);

				// 这里最好的操作应该是 replace，但是 Java 没有提供
				if (head.next != null) {
					maxHeap.offer(head.next);
				}
				count++;
			}
			return res;
		}

		/**
		 * Follower follows a followee. If the operation is invalid, it should be a no-op.
		 *
		 * @param followerId 发起关注者 id
		 * @param followeeId 被关注者 id
		 */
		public void follow(int followerId, int followeeId) {
			// 被关注人不能是自己
			if (followeeId == followerId) {
				return;
			}

			// 获取我自己的关注列表
			Set<Integer> followingList = followings.get(followerId);
			if (followingList == null) {
				Set<Integer> init = new HashSet<>();
				init.add(followeeId);
				followings.put(followerId, init);
			} else {
				if (followingList.contains(followeeId)) {
					return;
				}
				followingList.add(followeeId);
			}
		}

		/**
		 * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
		 *
		 * @param followerId 发起取消关注的人的 id
		 * @param followeeId 被取消关注的人的 id
		 */
		public void unfollow(int followerId, int followeeId) {
			if (followeeId == followerId) {
				return;
			}

			// 获取我自己的关注列表
			Set<Integer> followingList = followings.get(followerId);

			if (followingList == null) {
				return;
			}
			// 这里删除之前无需做判断，因为查找是否存在以后，就可以删除，反正删除之前都要查找
			followingList.remove(followeeId);
		}
	}
}
