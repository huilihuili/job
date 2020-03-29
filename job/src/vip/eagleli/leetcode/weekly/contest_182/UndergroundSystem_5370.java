package vip.eagleli.leetcode.weekly.contest_182;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndergroundSystem_5370 {
	class UndergroundSystem {

		Map<Integer, String> inMap;
		Map<Integer, String> outMap;
		Map<String, List<Integer>> timeMap;

		public UndergroundSystem() {
			inMap = new HashMap<>();
			outMap = new HashMap<>();
			timeMap = new HashMap<>();
		}

		public void checkIn(int id, String stationName, int t) {
			inMap.put(id, stationName + "_" + t);
		}

		public void checkOut(int id, String stationName, int t) {
			String[] preStation = inMap.get(id).split("_");
			String key = preStation[0] + "_" + stationName;
			if (!timeMap.containsKey(key)) {
				timeMap.put(key, new ArrayList<>());
			}
			timeMap.get(key).add(t - Integer.valueOf(preStation[1]));
		}

		public double getAverageTime(String startStation, String endStation) {
			double sum = 0;
			List<Integer> times = timeMap.get(startStation + "_" + endStation);
			if (times == null) {
				return -1;
			}
			for (Integer integer : times) {
				sum += integer;
			}
			return sum / times.size();
		}
	}
}
