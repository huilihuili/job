package vip.eagleli.leetcode.weekly.contest_184;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class EntityParser_5382 {
	/**
	 
	 双引号：字符实体为 &quot; ，对应的字符是 " 。
	单引号：字符实体为 &apos; ，对应的字符是 ' 。
	与符号：字符实体为 &amp; ，对应对的字符是 & 。
	大于号：字符实体为 &gt; ，对应的字符是 > 。
	小于号：字符实体为 &lt; ，对应的字符是 < 。
	斜线号：字符实体为 &frasl; ，对应的字符是 / 。
	
	 * @param text
	 * @return
	 */
	public String entityParser(String text) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("&quot;", "\"");
		map.put("&apos;", "'");
		map.put("&amp;", "&");
		map.put("&gt;", ">");
		map.put("&lt;", "<");
		map.put("&frasl;", "/");
		String string = text;
		for (Entry<String, String> entry : map.entrySet()) {
			string = string.replaceAll(entry.getKey(), entry.getValue());
		}
		return string;
	}
}
