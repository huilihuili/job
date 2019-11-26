package vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.apriori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

import vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.entity.ConfidentAssociationRule;
import vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.entity.FrequentItemset;
import vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.entity.Transactions;
import vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.util.AprioriUtil;
import vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.util.FrequentItemsetUtil;

/**
 * Apriori算法
 * 
 * @author yuncong
 *
 */
public class ConcurrentApriori {

	private Transactions transactions;
	private float minsup;
	private float minconf;

	public ConcurrentApriori(Transactions transactions, float minsup, float minconf) {
		this.transactions = transactions;
		this.minsup = minsup;
		this.minconf = minconf;
	}

	/**
	 * 生成所有频繁项目集
	 * 
	 * @param transactions
	 * @param minsup
	 * @return
	 */
	public List<FrequentItemset> generateFrequentItemsets() {
		// 已经得到的所有频繁项目集
		List<FrequentItemset> frequentItemsets = new ArrayList<FrequentItemset>();
		// first pass
		List<FrequentItemset> oneFrequentItemsets = this.firstPass(transactions, minsup);
		// k-1频繁项目集，通过它获得k频繁项目集;初始时，是单频繁项目集
		List<FrequentItemset> foreFrequentItemsets = new ArrayList<FrequentItemset>(oneFrequentItemsets);
		do {
			frequentItemsets.addAll(foreFrequentItemsets);
			// 得到新的k-1频繁项目集
			foreFrequentItemsets = this.kpass(foreFrequentItemsets);
		} while (foreFrequentItemsets != null && foreFrequentItemsets.size() > 0);
		return frequentItemsets;
	}

	/**
	 * 获得单项频繁项目集集合
	 * 
	 * @param transactions
	 *            事务集
	 * @param minsup
	 *            最小支持度
	 * @return
	 */
	private List<FrequentItemset> firstPass(Transactions transactions, float minsup) {
		// 遍历事务集，获得所有元素
		List<String> itemList = new ArrayList<String>();
		List<List<String>> transactionList = transactions.getTransactions();
		for (List<String> transaction : transactionList) {
			for (String item : transaction) {
				if (!itemList.contains(item)) {
					itemList.add(item);
				}
			}
		}

		ForkJoinPool pool = new ForkJoinPool();
		OneFrequentItemsetTask task = new OneFrequentItemsetTask(0, itemList.size() - 1, itemList);
		Future<List<FrequentItemset>> result = pool.submit(task);

		try {
			return result.get(); // 获取结果
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private class OneFrequentItemsetTask extends RecursiveTask<List<FrequentItemset>> {
		private static final long serialVersionUID = 1L;

		private static final int THREAD_HOLD = 3; // 阈值

		private int start;
		private int end;
		private List<String> itemList;

		public OneFrequentItemsetTask(int start, int end, List<String> itemList) {
			this.start = start;
			this.end = end;
			this.itemList = itemList;
		}

		@Override
		protected List<FrequentItemset> compute() {
			boolean canCompute = (end - start) <= THREAD_HOLD; // 是否小于阈值
			int transactionsSize = transactions.getTransactionsSize();
			List<FrequentItemset> oneFrequentItemsets = new ArrayList<FrequentItemset>();

			if (canCompute) {
				for (int i = start; i <= end; i++) {
					List<String> itemListCandidate = Arrays.asList(itemList.get(i));
					int supportCount = getSupportCount(transactions, itemListCandidate);
					if ((float) supportCount / transactionsSize >= minsup) {
						FrequentItemset frequentItemset = new FrequentItemset(itemListCandidate, supportCount);
						oneFrequentItemsets.add(frequentItemset);
					}
				}
			} else {
				int middle = (start + end) / 2;
				OneFrequentItemsetTask left = new OneFrequentItemsetTask(start, middle, itemList);
				OneFrequentItemsetTask right = new OneFrequentItemsetTask(middle + 1, end, itemList);

				// 执行子任务
				left.fork();
				right.fork();

				// 获取子任务结果
				List<FrequentItemset> lResult = left.join();
				List<FrequentItemset> rResult = right.join();
				oneFrequentItemsets.addAll(lResult);
				oneFrequentItemsets.addAll(rResult);
			}
			return oneFrequentItemsets;
		}
	}

	/**
	 * 获得一个候选频繁项目集在事务集中的支持度计数
	 * 
	 * @param transactions
	 *            事务集
	 * @param itemListCandidate
	 *            候选频繁项目集
	 * @return
	 */
	private int getSupportCount(Transactions transactions, List<String> itemListCandidate) {
		int supportCount = 0;
		List<List<String>> transactionList = transactions.getTransactions();
		for (List<String> transaction : transactionList) {
			if (transaction.containsAll(itemListCandidate)) {
				supportCount++;
			}
		}
		return supportCount;
	}

	/**
	 * 获得k频繁项目集
	 * 
	 * @param transactions
	 * @param foreFrequentItemsets
	 * @param minsup
	 * @return
	 */
	private List<FrequentItemset> kpass(List<FrequentItemset> foreFrequentItemsets) {
		List<List<String>> c1 = this.merge(foreFrequentItemsets);
		if (c1.size() == 0) {
			return null;
		}
		List<List<String>> c2 = this.tailor(foreFrequentItemsets, c1);
		if (c2.size() == 0) {
			return null;
		}

		ForkJoinPool pool = new ForkJoinPool();
		KFrequentItemsetTask task = new KFrequentItemsetTask(0, c2.size() - 1, c2);
		Future<List<FrequentItemset>> result = pool.submit(task);
		try {
			return result.get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private class KFrequentItemsetTask extends RecursiveTask<List<FrequentItemset>> {
		private static final long serialVersionUID = 1L;

		private static final int THREAD_HOLD = 3;

		private int start;
		private int end;
		private List<List<String>> c2;

		public KFrequentItemsetTask(int start, int end, List<List<String>> c2) {
			super();
			this.start = start;
			this.end = end;
			this.c2 = c2;
		}

		@Override
		protected List<FrequentItemset> compute() {
			boolean canCompute = (end - start) <= THREAD_HOLD;

			List<FrequentItemset> kfrequentItemsets = new ArrayList<FrequentItemset>();
			if (canCompute) {
				int transactionsSize = transactions.getTransactionsSize();
				for (int i = start; i <= end; i++) {
					List<String> itemList = c2.get(i);
					int supportCount = getSupportCount(transactions, itemList);
					if ((float) supportCount / transactionsSize >= minsup) {
						FrequentItemset frequentItemset = new FrequentItemset(itemList, supportCount);
						kfrequentItemsets.add(frequentItemset);
					}
				}
			} else {
				int middle = (start + end) / 2;
				KFrequentItemsetTask left = new KFrequentItemsetTask(start, middle, c2);
				KFrequentItemsetTask right = new KFrequentItemsetTask(middle + 1, end, c2);
				// 执行子任务
				left.fork();
				right.fork();
				// 获取子任务结果
				List<FrequentItemset> lResult = left.join();
				List<FrequentItemset> rResult = right.join();
				kfrequentItemsets.addAll(lResult);
				kfrequentItemsets.addAll(rResult);
			}
			return kfrequentItemsets;
		}

	}

	/**
	 * 合并k-1频繁项目集，得到k候选频繁项目集； 方法是寻找所有只有最后一个元素不同的k-1 频繁项目集对，然后合并它们，这样做，之
	 * 所以可行，是因为，如果一个项目集是k频繁 项目集，那么它的所有k-1个元素的子集也 必然是频繁项目集，自然存在只有最后一个元素
	 * 不同的两个k-1项子集，所以不会漏掉任何一个 候选k频繁项目集
	 * 
	 * @param foreFrequentItemsets
	 * @return
	 */
	private List<List<String>> merge(List<FrequentItemset> foreFrequentItemsets) {
		List<List<String>> c1 = new ArrayList<List<String>>();
		int foreFrequentItemsetsSize = foreFrequentItemsets.size();
		// 遍历每一个k-1频繁项目集，让它们与排在它们后面的频繁项目集连线,
		// 从而让所有k-1频繁项目集两两配对
		for (int i = 0; i < foreFrequentItemsetsSize; i++) {
			FrequentItemset frequentItemset = foreFrequentItemsets.get(i);
			for (int j = i + 1; j < foreFrequentItemsetsSize; j++) {
				FrequentItemset another = foreFrequentItemsets.get(j);
				if (frequentItemset.isPairTo(another)) {
					List<String> frequentItemsetAfterMerge = FrequentItemsetUtil
							.mergePairFrequentItemset(frequentItemset, another);
					if (!c1.contains(frequentItemsetAfterMerge)) {
						c1.add(frequentItemsetAfterMerge);
					}
				}
			}
		}
		return c1;
	}

	/**
	 * 如果一个k项目集的任何k-1项目子集不是频繁项目集， 那么这个k项目集也不是频繁项目集
	 * 
	 * @param transactions
	 * @param c1
	 * @return
	 */
	private List<List<String>> tailor(List<FrequentItemset> foreFrequentItemsets, List<List<String>> c1) {
		List<List<String>> c2 = new ArrayList<List<String>>();
		for (List<String> itemList : c1) {
			if (AprioriUtil.isRealCandidateFrequentItemset(foreFrequentItemsets, itemList)) {
				c2.add(itemList);
			}
		}
		return c2;
	}

	/**
	 * 从频繁项目集上生成所有置信度大于等于最小置信度的可信关联规则
	 * 
	 * @param frequentItemsets
	 * @param minconf
	 * @return
	 */
	public List<ConfidentAssociationRule> generateConfidentAssociationRules(List<FrequentItemset> frequentItemsets) {
		ForkJoinPool pool = new ForkJoinPool();
		ConfidentAssociationRuleTask task = new ConfidentAssociationRuleTask(0, frequentItemsets.size() - 1,
				frequentItemsets);
		Future<List<ConfidentAssociationRule>> result = pool.submit(task);
		try {
			return result.get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// List<ConfidentAssociationRule> confidentAssociationRules = new
		// ArrayList<ConfidentAssociationRule>();
		// // 对每一个元素个数大于等于2的频繁项目集生成可信关联规则
		// for (FrequentItemset frequentItemset : frequentItemsets) {
		// List<String> frequentItemList = frequentItemset.getFrequentItemset();
		// int frequentItemListCount = frequentItemset.getsupportCount();
		// int frequentItemListSize = frequentItemList.size();
		// if (frequentItemListSize >= 2) {
		// confidentAssociationRules.addAll(this.generateConfidentAssociationRules(frequentItemsets,
		// frequentItemList, minconf, frequentItemListCount));
		// }
		// }
		// return confidentAssociationRules;
	}

	private class ConfidentAssociationRuleTask extends RecursiveTask<List<ConfidentAssociationRule>> {
		private static final long serialVersionUID = 1L;

		private static final int THREAD_HOLD = 3;

		private int start;
		private int end;
		List<FrequentItemset> frequentItemsets;

		public ConfidentAssociationRuleTask(int start, int end, List<FrequentItemset> frequentItemsets) {
			super();
			this.start = start;
			this.end = end;
			this.frequentItemsets = frequentItemsets;
		}

		@Override
		protected List<ConfidentAssociationRule> compute() {
			boolean canCompute = (end - start) <= THREAD_HOLD;

			List<ConfidentAssociationRule> confidentAssociationRules = new ArrayList<ConfidentAssociationRule>();
			if (canCompute) {
				for (int i = start; i <= end; i++) {
					FrequentItemset frequentItemset = frequentItemsets.get(i);
					List<String> frequentItemList = frequentItemset.getFrequentItemset();
					int frequentItemListCount = frequentItemset.getsupportCount();
					int frequentItemListSize = frequentItemList.size();
					if (frequentItemListSize >= 2) {
						confidentAssociationRules.addAll(generateConfidentAssociationRules(frequentItemsets,
								frequentItemList, minconf, frequentItemListCount));
					}
				}
			} else {
				int middle = (start + end) / 2;
				ConfidentAssociationRuleTask left = new ConfidentAssociationRuleTask(start, middle, frequentItemsets);
				ConfidentAssociationRuleTask right = new ConfidentAssociationRuleTask(middle + 1, end,
						frequentItemsets);
				// 执行子任务
				left.fork();
				right.fork();
				// 获取子任务结果
				List<ConfidentAssociationRule> lResult = left.join();
				List<ConfidentAssociationRule> rResult = right.join();
				confidentAssociationRules.addAll(lResult);
				confidentAssociationRules.addAll(rResult);
			}
			return confidentAssociationRules;
		}

	}

	/**
	 * 生成某一个频繁项目集的所有可信关联规则; frequentItemList元素个数大于等于2； 后件元素个数保证比最多frequentItemList
	 * 元素个数少1
	 * 
	 * @param frequentItemsets
	 * @param frequentItemList
	 * @param minconf
	 * @param frequentItemListCount
	 * @return
	 */
	private List<ConfidentAssociationRule> generateConfidentAssociationRules(List<FrequentItemset> frequentItemsets,
			List<String> frequentItemList, float minconf, int frequentItemListCount) {
		List<ConfidentAssociationRule> confidentAssociationRules = new ArrayList<ConfidentAssociationRule>();
		int frequentItemListSize = frequentItemList.size();
		// 生成后件只有一个元素的可信关联规则
		List<List<String>> oneConsequents = this.getOneConsequents(frequentItemList);
		// 包含k-1个元素的可信关联规则的后件的集合
		List<List<String>> foreConsequents = new ArrayList<List<String>>();
		for (List<String> consequent : oneConsequents) {
			float confidence = this.getConfidence(frequentItemsets, frequentItemList, frequentItemListCount,
					consequent);
			if (confidence >= minconf) {
				foreConsequents.add(consequent);
				List<String> condition = AprioriUtil.getCondition(frequentItemList, consequent);
				ConfidentAssociationRule confidentAssociationRule = new ConfidentAssociationRule(condition, consequent,
						confidence);
				confidentAssociationRules.add(confidentAssociationRule);
			}
		}

		// i表示后件的元素个数，必须比frequentItemListSize小;
		// 保存i-1个元素的后件的foreConsequents也必须有元素，
		// 因为i个元素的后件必须由它们合并而来
		for (int i = 2; i < frequentItemListSize && foreConsequents.size() > 0; i++) {
			// k个元素的后件，是由k-1个元素的后件合并而来的
			List<List<String>> c1 = this.mergeConsequents(foreConsequents);
			List<List<String>> c2 = this.tailorConsequents(foreConsequents, c1);
			foreConsequents.clear();
			for (List<String> consequent : c2) {
				float confidence = this.getConfidence(frequentItemsets, frequentItemList, frequentItemListCount,
						consequent);
				if (confidence >= minconf) {
					foreConsequents.add(consequent);
					List<String> condition = AprioriUtil.getCondition(frequentItemList, consequent);
					ConfidentAssociationRule confidentAssociationRule = new ConfidentAssociationRule(condition,
							consequent, confidence);
					confidentAssociationRules.add(confidentAssociationRule);
				}
			}
		}

		return confidentAssociationRules;
	}

	/**
	 * 获得只包含一个元素的后件的集合
	 * 
	 * @param frequentItemList
	 * @return
	 */
	private List<List<String>> getOneConsequents(List<String> frequentItemList) {
		List<List<String>> oneConsequentList = new ArrayList<List<String>>();
		for (String item : frequentItemList) {
			List<String> oneConsequent = new ArrayList<>();
			oneConsequent.add(item);
			oneConsequentList.add(oneConsequent);
		}
		return oneConsequentList;
	}

	/**
	 * 获得一个后件的对应的关联规则的支持度
	 * 
	 * @param frequentItemsets
	 * @param frequentItemList
	 * @param frequentItemListCount
	 * @param consequent
	 * @return
	 */
	private float getConfidence(List<FrequentItemset> frequentItemsets, List<String> frequentItemList,
			int frequentItemListCount, List<String> consequent) {
		List<String> condition = AprioriUtil.getCondition(frequentItemList, consequent);
		int conditionCount = 0;
		for (FrequentItemset frequentItemset : frequentItemsets) {
			if (frequentItemset.getFrequentItemset().equals(condition)) {
				conditionCount = frequentItemset.getsupportCount();
			}
		}
		float confidence = (float) frequentItemListCount / conditionCount;
		return confidence;
	}

	/**
	 * 组合含k-1个元素的后件为k个元素的后件
	 * 
	 * @param foreConsequents
	 * @return
	 */
	private List<List<String>> mergeConsequents(List<List<String>> foreConsequents) {
		List<List<String>> c1 = new ArrayList<List<String>>();
		int foreConsequentsSize = foreConsequents.size();
		// 遍历每一个k-1后件项目集，让它们与排在它们后面的后件项目集连线,
		// 从而让所有k-1后件项目集两两配对
		for (int i = 0; i < foreConsequentsSize; i++) {
			List<String> consequent = foreConsequents.get(i);
			for (int j = i + 1; j < foreConsequentsSize; j++) {
				List<String> another = foreConsequents.get(j);
				if (AprioriUtil.isPair(consequent, another)) {
					List<String> consequentAfterMerge = AprioriUtil.mergePairList(consequent, another);
					if (!c1.contains(consequentAfterMerge)) {
						c1.add(consequentAfterMerge);
					}
				}
			}
		}
		return c1;
	}

	/**
	 * k项候选后件的任何k-1项子集都必须在 k-1项可信关联规则后件集合里面
	 * 
	 * @param foreConsequents
	 * @param c1
	 * @return
	 */
	private List<List<String>> tailorConsequents(List<List<String>> foreConsequents, List<List<String>> c1) {
		List<List<String>> c2 = new ArrayList<List<String>>();
		for (List<String> itemList : c1) {
			if (AprioriUtil.isRealCandidateConsequent(foreConsequents, itemList)) {
				c2.add(itemList);
			}
		}
		return c2;
	}

}
