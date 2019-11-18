package vip.eagleli.bi.ye.she.ji.apriori.test.java.com.liyuncong.algorithm.algorithm_apriori.apriori;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.apriori.Apriori;
import vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.apriori.ConcurrentApriori;
import vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.entity.ConfidentAssociationRule;
import vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.entity.FrequentItemset;
import vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.entity.Transactions;

public class TestApriori {
	@SuppressWarnings("unchecked")
	@Test
	public void testFirstPass() {
		String fileName = "transactions.txt";
		Transactions transactions = new Transactions(fileName);
		// 打印出事务集
		for (List<String> itemList : transactions.getTransactions()) {
			for (String item : itemList) {
				System.out.print(item + "	");
			}
			System.out.println();
		}

		float minsup = 0.5f;
		Apriori apriori = new Apriori();
		// 利用反射访问私有方法
		Method firstPassMethod = null;
		try {
			firstPassMethod = Apriori.class.getDeclaredMethod("firstPass", Transactions.class, float.class);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		firstPassMethod.setAccessible(true);

		try {
			List<FrequentItemset> oneFrequentItemsets = (List<FrequentItemset>) firstPassMethod.invoke(apriori,
					transactions, minsup);
			for (FrequentItemset frequentItemset : oneFrequentItemsets) {
				System.out.println("................");
				// List<String> itemList = frequentItemset.getFrequentItemset();
				// for (String item : itemList) {
				// System.out.print(item + " ");
				// }
				// System.out.println(frequentItemset.getsupportCount());
				System.out.println(frequentItemset);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testGenerateFrequentItemsets() {
		String fileName = "transactions.txt";
		Transactions transactions = new Transactions(fileName);
		float minsup = 0.5f;
		Apriori apriori = new Apriori();
		List<FrequentItemset> frequentItemsets = apriori.generateFrequentItemsets(transactions, minsup);
		for (FrequentItemset frequentItemset : frequentItemsets) {
			System.out.println("....................");
			System.out.println(frequentItemset);
		}
	}

	@Test
	public void testGenerateConfidentAssociationRules() {
		String fileName = "transactions.txt";
		Transactions transactions = new Transactions(fileName);
		float minsup = 0.5f;
		Apriori apriori = new Apriori();
		List<FrequentItemset> frequentItemsets = apriori.generateFrequentItemsets(transactions, minsup);
		List<ConfidentAssociationRule> confidentAssociationRules = apriori
				.generateConfidentAssociationRules(frequentItemsets, 0.5f);
		for (ConfidentAssociationRule confidentAssociationRule : confidentAssociationRules) {
			System.out.println("--------------------");
			System.out.println(confidentAssociationRule);
		}
	}

	@Test
	public void getRandomTransactionsTest() {
		List<List<String>> transactions = Transactions.getRandomTransactions(10, 10);
		Transactions.printTranstion(transactions);
	}

	@Test
	public void timeTest1() {
		String fileName = "transactions.txt";
		Transactions transactions = new Transactions(fileName);
		Date date1 = Calendar.getInstance().getTime();
		float minsup = 0.5f;
		float minconf = 0.5f;
		Apriori apriori = new Apriori();
		List<FrequentItemset> frequentItemsets = apriori.generateFrequentItemsets(transactions, minsup);
		List<ConfidentAssociationRule> confidentAssociationRules = apriori
				.generateConfidentAssociationRules(frequentItemsets, minconf);
		Date date2 = Calendar.getInstance().getTime();
		System.out.println((date2.getTime() - date1.getTime()));
		//for (FrequentItemset frequentItemset : frequentItemsets) {
		//	System.out.println("....................");
		//	System.out.println(frequentItemset);
		//}
		//for (ConfidentAssociationRule confidentAssociationRule : confidentAssociationRules) {
		//	System.out.println("--------------------");
		//	System.out.println(confidentAssociationRule);
		//}
	}

	@Test
	public void timeTest2() {
		String fileName = "transactions.txt";
		Transactions transactions = new Transactions(fileName);
		Date date1 = Calendar.getInstance().getTime();
		float minsup = 0.5f;
		float minconf = 0.5f;
		ConcurrentApriori apriori = new ConcurrentApriori(transactions, minsup, minconf);
		List<FrequentItemset> frequentItemsets = apriori.generateFrequentItemsets();
		List<ConfidentAssociationRule> confidentAssociationRules = apriori
				.generateConfidentAssociationRules(frequentItemsets);
		Date date2 = Calendar.getInstance().getTime();
		System.out.println((date2.getTime() - date1.getTime()));
		for (FrequentItemset frequentItemset : frequentItemsets) {
			System.out.println("....................");
			System.out.println(frequentItemset);
		}
		for (ConfidentAssociationRule confidentAssociationRule : confidentAssociationRules) {
			System.out.println("--------------------");
			System.out.println(confidentAssociationRule);
		}
	}
}
