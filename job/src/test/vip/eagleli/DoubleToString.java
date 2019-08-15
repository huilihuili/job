package test.vip.eagleli;

import java.math.BigDecimal;

public class DoubleToString {
	public static void main(String[] args) {
		double d = 1.3825180899E10;
		System.out.println(Double.toString(d));
		System.out.println(new BigDecimal(d).toPlainString());
	}
}
