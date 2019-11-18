package vip.eagleli.bi.ye.she.ji.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import sun.misc.BASE64Encoder;

public class FreeMakerTemplate {

	@Test
	public void test() {
		try {
			// 一些配置
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
			cfg.setDirectoryForTemplateLoading(new File("D:\\lh"));
			cfg.setDefaultEncoding("utf-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

			// 获得模板
			Template template = cfg.getTemplate("template.xml", "utf-8");

			// 构造数据
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("name", "lh");

			List<Map<String, String>> items = new ArrayList<Map<String, String>>();
			Map<String, String> item1 = new HashMap<String, String>();
			item1.put("date1", "date1-1");
			item1.put("date2", "date1-2");
			item1.put("date3", "date1-3");
			Map<String, String> item2 = new HashMap<String, String>();
			item2.put("date1", "date1-4");
			item2.put("date2", "date1-5");
			item2.put("date3", "date1-6");
			items.add(item1);
			items.add(item2);
			data.put("items", items);
			data.put("image", getImageBase("good-idea.png")); // 图片的base64码

			// 合并模板和数据模型
			Writer out = new OutputStreamWriter(new FileOutputStream("D:\\lh\\输出.doc"), "utf-8");
			template.process(data, out);

			// 关闭
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得图片的base64码
	public static String getImageBase(String src) throws Exception {
		if (src == null || src == "") {
			return "";
		}
		File file = new File(src);
		if (!file.exists()) {
			return "";
		}
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(file);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
}
