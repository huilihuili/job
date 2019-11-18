package vip.eagleli.bi.ye.she.ji.apriori.main.java.com.liyuncong.algorithm.algorithm_apriori.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 事务集
 * 
 * @author yuncong
 *
 */
public class Transactions {
	private List<List<String>> transactions = new ArrayList<>();

	public static List<List<String>> getRandomTransactions(int rows, int maxCol) {
		List<List<String>> res = new ArrayList<>(rows);
		for (int i = 0; i < rows; i++) {
			List<String> list = new ArrayList<>();
			Set<Integer> set = new HashSet<>();
			for (int j = 0; j <= (int) (Math.random() * maxCol); j++) {
				int cur = (int) (Math.random() * maxCol) + 1;
				while (set.contains(cur)) {
					cur = (int) (Math.random() * maxCol) + 1;
				}
				set.add(cur);
				list.add(String.valueOf(cur));
			}
			res.add(list);
		}
		return res;
	}

	public static <E> void printTranstion(List<List<E>> transactions) {
		for (List<E> itemList : transactions) {
			for (E item : itemList) {
				System.out.print(item + "	");
			}
			System.out.println();
		}
	}

	public Transactions(List<List<String>> transactions) {
		for (List<String> items : transactions) {
			String[] itemArray =  items.toArray(new String[0]);
			// new QuickSort<String>().sort(itemArray);
			Arrays.sort(itemArray);
			this.transactions.add(Arrays.asList(itemArray));
		}
	}

	/**
	 * 假设：在文件中，一行存储一个事物
	 * 
	 * @param fileName
	 *            存有所有事务的文件的文件名
	 */
	public Transactions(String fileName) {
		// 默认保存事务的文件使用UTF-8字符集
		this(fileName, Charset.forName("UTF-8"));
	}

	public Transactions(String fileName, Charset charset) {
		try (InputStream inputStream = new FileInputStream(fileName);
				Reader reader = new InputStreamReader(inputStream, charset)) {
			StringBuffer transactaionsBuffer = new StringBuffer();
			char[] cbuf = new char[4096];
			int size = 0;
			while ((size = reader.read(cbuf)) != -1) {
				for (int i = 0; i < size; i++) {
					transactaionsBuffer.append(cbuf[i]);
				}
			}
			String transactaionsStr = transactaionsBuffer.toString();
			// 用换行符分割字符串，得到事务数组
			String[] transactaionsArr = transactaionsStr.split(System.lineSeparator());
			for (String itemsStr : transactaionsArr) {
				// 用英文或者中文逗号分隔字符串，得到项的数组
				//String[] itemsArr = itemsStr.split("[,， ]");
				String[] itemsArr = itemsStr.split(" ");
				// new QuickSort<String>().sort(itemsArr);
				Arrays.sort(itemsArr);
				this.transactions.add(Arrays.asList(itemsArr));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<List<String>> getTransactions() {
		return transactions;
	}

	public int getTransactionsSize() {
		return this.transactions.size();
	}

}
