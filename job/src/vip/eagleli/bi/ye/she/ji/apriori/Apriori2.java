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
	 * ����Ƶ�������
	 */
	@Test
	public void testApriori() {
		// ���ز������ݼ�
		List<List<Integer>> dataSet = loadDataSet();
		System.out.print("dataSet: " + dataSet);

		// Apriori �㷨����Ƶ����Լ����ǵ�֧�ֶ�
		// L1, supportData1 = apriori(dataSet, minSupport=0.7)
		// print ('L(0.7): ', L1)
		// print ('supportData(0.7): ', supportData1)
		//
		// print ('->->->->->->->->->->->->->->->->->->->->->->->->->->->->')
		//
		// # Apriori �㷨����Ƶ����Լ����ǵ�֧�ֶ�
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

		// C1����dataSetȥ������Ȼ��ת�����е�Ԫ��Ϊfrozenset
		Integer[] C1 = createC1(dataSet);

		// ��ÿһ�н��� set ת����Ȼ���ŵ�������
		List<Set<Integer>> D = new ArrayList<>();
		for (Integer[] integers : dataSet) {
			D.add(new HashSet<>(Arrays.asList(integers)));
		}

		// �����ѡ���ݼ�C1�����ݼ�D�е�֧�ֶȣ�������֧�ֶȴ���minSupport ������
		// L1, supportData = scanD(D, C1, minSupport)
		// # L ����һ�� list, Lһ�� 2 �� list
		// L = [L1];k = 2
		// # �ж�L��k-2������ݳ����Ƿ�>0��Ƶ�����һ���һ��ִ��ʱ L Ϊ [[frozenset([1]), frozenset([3]),
		// frozenset([2]), frozenset([5])]]��L[k-2]=L[0]=[frozenset([1]), frozenset([3]),
		// frozenset([2]), frozenset([5])]������� k += 1
		// while (len(L[k-2]) > 0):
		// Ck = aprioriGen(L[k-2], k) # ����: �� {0},{1},{2} Ϊ������ k = 2 ����� {0,1}, {0,2},
		// {1,2}. �� {0,1},{0,2},{1,2} Ϊ������ k = 3 ����� {0,1,2}
		//
		// # ���غ�ѡ���ݼ�CK�����ݼ�D�е�֧�ֶȴ�����С֧�ֶȵ�����
		// Lk, supK = scanD(D, Ck, minSupport)
		// # �������к�ѡ���֧�ֶȣ�����ֵ�û�о�׷��Ԫ�أ�����о͸���Ԫ��
		// supportData.update(supK)
		// if len(Lk) == 0:
		// break
		// # Lk ��ʾ����Ƶ������ļ��ϣ�L Ԫ�������ӣ�����:
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
	 * �����ѡ���ݼ�CK�����ݼ�D�е�֧�ֶȣ����ش�����С֧�ֶȵ�����
	 */
	static void scanD(List<Set<Integer>> D, Integer[] Ck, float minSupport) {
		// // ssCnt ��ʱ������к�ѡ���Ƶ��
		// ssCnt = {}
		// for tid in D:
		// # print('1:',tid)
		// for can in map(frozenset,Ck): #ÿ����ѡ�can
		// # print('2:',can.issubset(tid),can,tid)
		// if can.issubset(tid):
		// if not can in ssCnt:
		// ssCnt[can] = 1
		// else:
		// ssCnt[can] +=1
		//
		// numItems = float(len(D)) # �������Ŀ
		// # ������С֧�ֶȵ�Ƶ���
		// retList = []
		// # ������С֧�ֶȵ�Ƶ�����Ƶ��
		// supportData = {}
		//
		// for key in ssCnt:
		// support = ssCnt[key]/numItems #�����ܵļ�¼��������Ϊ��֧�ֶ�
		// if support >= minSupport:
		// retList.insert(0,key) #������С֧�ֶȵ���������¼������
		// supportData[key] = support
		// return retList, supportData
	}

}
