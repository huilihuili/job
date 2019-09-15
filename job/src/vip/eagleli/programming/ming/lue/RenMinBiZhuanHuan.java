package vip.eagleli.programming.ming.lue;

import java.util.Scanner;

public class RenMinBiZhuanHuan {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double d = new Scanner(System.in).nextDouble();
		System.out.println(solve(d));
	}

	public static String solve(double number) {
		StringBuffer chineseNumber = new StringBuffer();
		String[] num = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };
		String[] unit = { "��", "��", "Բ", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��" };
		String tempNumber = String.valueOf(Math.round((number * 100)));
		int tempNumberLength = tempNumber.length();
		if ("0".equals(tempNumber)) {
			return "��Բ��";
		}
		if (tempNumberLength > 15) {
			try {
				throw new Exception("����ת����Χ.");
			} catch (Exception e) {
			}
		}
		boolean preReadZero = true; // ǰ����ַ��Ƿ����
		for (int i = tempNumberLength; i > 0; i--) {
			if ((tempNumberLength - i + 2) % 4 == 0) {
				// ����ڣ�Բ�����ڣ���λ�ϵ��ĸ�����Ϊ��,�����־Ϊδ���㣬��ֻ���㣬�����־Ϊ�Ѷ��㣬���Թ�����λ
				if (i - 4 >= 0 && "0000".equals(tempNumber.substring(i - 4, i))) {
					if (!preReadZero) {
						chineseNumber.insert(0, "��");
						preReadZero = true;
					}
					i -= 3; // ���滹��һ��i--
					continue;
				}
				// �����ǰλ�ڣ�Բ�����ڣ���λ�ϣ������ñ�־Ϊ�Ѷ��㣨�����ö����־��
				preReadZero = true;
			}
			int digit = Integer.parseInt(tempNumber.substring(i - 1, i));
			if (digit == 0) {
				// �����ǰλ���㲢�ұ�־Ϊδ���㣬����㣬�����ñ�־Ϊ�Ѷ���
				if (!preReadZero) {
					chineseNumber.insert(0, "��");
					preReadZero = true;
				}
				// �����ǰλ���㲢���ڣ�Բ�����ڣ���λ�ϣ��������Բ�����ڣ���
				if ((tempNumberLength - i + 2) % 4 == 0) {
					chineseNumber.insert(0, unit[tempNumberLength - i]);
				}
			}
			// �����ǰλ��Ϊ�㣬�������λ���������ñ�־Ϊδ����
			else {
				chineseNumber.insert(0, num[digit] + unit[tempNumberLength - i]);
				preReadZero = false;
			}
		}
		// ����ֽ���λ�ϵ�ֵ��Ϊ�㣬�����һ����������
		if (tempNumberLength - 2 >= 0 && "00".equals(tempNumber.substring(tempNumberLength - 2, tempNumberLength))) {
			chineseNumber.append("��");
		}
		return "�����" + chineseNumber.toString();
	}
}