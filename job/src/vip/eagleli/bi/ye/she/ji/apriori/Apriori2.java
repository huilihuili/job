package vip.eagleli.bi.ye.she.ji.apriori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class Apriori2 {
	public static void main(String[] args) {

	}

	/**
	 * 测试频繁项集生产
	 */
	@Test
	public void testApriori() {
		// 加载测试数据集
		List<List<Integer>> dataSet = loadDataSet();
		System.out.print("dataSet: " + dataSet);

		// Apriori 算法生成频繁项集以及它们的支持度
		// L1, supportData1 = apriori(dataSet, minSupport=0.7)
		// print ('L(0.7): ', L1)
		// print ('supportData(0.7): ', supportData1)
		//
		// print ('->->->->->->->->->->->->->->->->->->->->->->->->->->->->')
		//
		// # Apriori 算法生成频繁项集以及它们的支持度
		// L2, supportData2 = apriori(dataSet, minSupport=0.5)
		// print ('L(0.5): ', L2)
		// print ('supportData(0.5): ', supportData2)
	}

	static List<List<Integer>> loadDataSet() {
		Integer[][] dataSet = { { 1, 3, 4 }, { 2, 3, 5 }, { 1, 2, 3, 5 }, { 2, 5 } };
		List<List<Integer>> res = new ArrayList<>();
		for (Integer[] integers : dataSet) {
			res.add(Arrays.asList(integers));
		}
		return res;
	}

	static void apriori(Integer[][] dataSet, float minSupport) {

		// C1即对dataSet去重排序，然后转换所有的元素为frozenset
		Integer[] C1 = createC1(dataSet);

		// 对每一行进行 set 转换，然后存放到集合中
		List<Set<Integer>> D = new ArrayList<>();
		for (Integer[] integers : dataSet) {
			D.add(new HashSet<>(Arrays.asList(integers)));
		}

		// 计算候选数据集C1在数据集D中的支持度，并返回支持度大于minSupport 的数据
		// L1, supportData = scanD(D, C1, minSupport)
		// # L 加了一层 list, L一共 2 层 list
		// L = [L1];k = 2
		// # 判断L第k-2项的数据长度是否>0即频繁项集第一项。第一次执行时 L 为 [[frozenset([1]), frozenset([3]),
		// frozenset([2]), frozenset([5])]]。L[k-2]=L[0]=[frozenset([1]), frozenset([3]),
		// frozenset([2]), frozenset([5])]，最后面 k += 1
		// while (len(L[k-2]) > 0):
		// Ck = aprioriGen(L[k-2], k) # 例如: 以 {0},{1},{2} 为输入且 k = 2 则输出 {0,1}, {0,2},
		// {1,2}. 以 {0,1},{0,2},{1,2} 为输入且 k = 3 则输出 {0,1,2}
		//
		// # 返回候选数据集CK在数据集D中的支持度大于最小支持度的数据
		// Lk, supK = scanD(D, Ck, minSupport)
		// # 保存所有候选项集的支持度，如果字典没有就追加元素，如果有就更新元素
		// supportData.update(supK)
		// if len(Lk) == 0:
		// break
		// # Lk 表示满足频繁子项的集合，L 元素在增加，例如:
		// # l=[[set(1), set(2), set(3)]]
		// # l=[[set(1), set(2), set(3)], [set(1, 2), set(2, 3)]]
		// L.append(Lk)
		// k += 1
		// return L, supportData
	}

	static Integer[] createC1(Integer[][] dataSet) {
		HashSet<Integer> c1 = new HashSet<>();
		for (Integer[] transaction : dataSet) {
			for (Integer item : transaction) {
				c1.add(item);
			}
		}
		Integer[] res = c1.toArray(new Integer[c1.size()]);
		Arrays.sort(res);
		return res;
	}

	/**
	 * 计算候选数据集CK在数据集D中的支持度，返回大于最小支持度的数据
	 */
	static void scanD(List<Set<Integer>> D, Integer[] Ck, float minSupport) {
		// // ssCnt 临时存放所有候选项集和频率
		// ssCnt = {}
		// for tid in D:
		// # print('1:',tid)
		// for can in map(frozenset,Ck): #每个候选项集can
		// # print('2:',can.issubset(tid),can,tid)
		// if can.issubset(tid):
		// if not can in ssCnt:
		// ssCnt[can] = 1
		// else:
		// ssCnt[can] +=1
		//
		// numItems = float(len(D)) # 所有项集数目
		// # 满足最小支持度的频繁项集
		// retList = []
		// # 满足最小支持度的频繁项集和频率
		// supportData = {}
		//
		// for key in ssCnt:
		// support = ssCnt[key]/numItems #除以总的记录条数，即为其支持度
		// if support >= minSupport:
		// retList.insert(0,key) #超过最小支持度的项集，将其记录下来。
		// supportData[key] = support
		// return retList, supportData
	}

}
