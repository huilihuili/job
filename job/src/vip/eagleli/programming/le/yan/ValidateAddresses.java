package vip.eagleli.programming.le.yan;

import java.util.ArrayList;
import java.util.List;

public class ValidateAddresses {

	public static void main(String[] args) {
		System.out.println(isIpv4("23.45.22.32."));
	}

	public static List<String> validateAddresses(List<String> addresses) {
		List<String> res = new ArrayList<>();
		for (String string : addresses) {
			if (string == null || string.equals("")) {
				res.add("Neither");
				continue;
			}
			if (isIpv4(string)) {
				res.add("IPv4");
				continue;
			}

			if (isIpv6(string)) {
				res.add("IPv6");
				continue;
			}

			res.add("Neither");
		}
		return res;
	}

	private static boolean isIpv4(String address) {
		if (address.charAt(0) == '.' || address.charAt(address.length() - 1) == '.') {
			return false;
		}
		String[] values = address.split("\\.");
		if (values.length != 4) {
			return false;
		}

		for (int i = 0; i < values.length; i++) {
			if (values[i].trim().equals("")) {
				return false;
			}
			int v = Integer.valueOf(values[i].trim());
			if (0 <= v && v <= 9) {
				if (values[i].trim().length() > 1) {
					return false;
				}
			}

			if (10 <= v && v <= 99) {
				if (values[i].trim().length() > 2) {
					return false;
				}
			}

			if (99 <= v && v <= 255) {
				if (values[i].trim().length() > 3) {
					return false;
				}
			}

			if (v < 0 || v > 255) {
				return false;
			}
		}
		return true;
	}

	private static boolean isIpv6(String address) {
		if (address.charAt(0) == '.' || address.charAt(address.length() - 1) == '.') {
			return false;
		}
		String[] strings = address.split("::");
		if (strings.length > 2) {
			return false;
		}
		int length = 8;
		for (int i = 0; i < strings.length; i++) {
			String string = strings[i];
			String[] segs = string.split(":");
			length -= segs.length;
			if (length < 0) {
				return false;
			}
			for (int j = 0; j < segs.length; j++) {
				char[] cs = segs[j].toCharArray();
				if (cs.length > 4) {
					return false;
				}
				for (char c : cs) {
					if (!(('0' <= c && c <= '9') || ('a' <= c && c <= 'f'))) {
						return false;
					}
				}
			}
		}
		if (strings.length == 1 && length > 0) {
			return false;
		}
		return true;
	}
}
