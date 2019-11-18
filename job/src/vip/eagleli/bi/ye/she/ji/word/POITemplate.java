package vip.eagleli.bi.ye.she.ji.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumVal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrVal;

public class POITemplate {

	@Test
	public void tblTest() {
		try {
			XWPFDocument doc = new XWPFDocument(new FileInputStream(new File("D:\\lh\\POI模板.docx")));
			List<XWPFTable> tables = doc.getTables();
			List<XWPFPictureData> pictures = doc.getAllPictures();
			List<XWPFChart> charts = doc.getCharts();

			System.out.println(tables.size());
			System.out.println(pictures.size());
			System.out.println(charts.size());

			XWPFTable table = tables.get(0);
			table.getRow(0).getCell(0).setText("已替换");

			XWPFChart chart = charts.get(0);
			CTChart ctChart = chart.getCTChart();
			CTPlotArea plotArea = ctChart.getPlotArea();

			CTBarChart barChart = plotArea.getBarChartArray(0);
			List<CTBarSer> serList = barChart.getSerList(); // 獲取柱狀圖單位
			System.out.println(serList.size());
			int position = 1;

			// 數據準備
			List<String> titleArr = new ArrayList<String>();// 标题
			titleArr.add("title");
			titleArr.add("佔基金資產淨值比例22222(%)");
			titleArr.add("額外的(%)");
			titleArr.add("額外的(%)");

			List<String> fldNameArr = new ArrayList<String>();// 字段名
			fldNameArr.add("item1");
			fldNameArr.add("item2");
			fldNameArr.add("item3");
			fldNameArr.add("item4");

			// 數據集合
			List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

			// 第一行數據
			Map<String, String> base1 = new HashMap<String, String>();
			base1.put("item1", "材料費用");
			base1.put("item2", "500");
			base1.put("item3", "500");
			base1.put("item4", "250");

			// 第二行數據
			Map<String, String> base2 = new HashMap<String, String>();
			base2.put("item1", "出差費用");
			base2.put("item2", "300");
			base2.put("item3", "500");
			base2.put("item4", "250");

			// 第三行數據
			Map<String, String> base3 = new HashMap<String, String>();
			base3.put("item1", "住宿費用");
			base3.put("item2", "300");
			base3.put("item3", "350");
			base3.put("item4", "250");

			dataList.add(base1);
			dataList.add(base2);
			dataList.add(base3);

			// 更新数据区域
			for (int i = 0; i < serList.size(); i++) {
				// CTSerTx tx=null;
				CTAxDataSource cat = null;
				CTNumDataSource val = null;
				CTBarSer ser = barChart.getSerArray(i);
				// tx= ser.getTx();
				// Category Axis Data
				cat = ser.getCat();
				// 获取图表的值
				val = ser.getVal();
				// strData.set
				CTStrData strData = cat.getStrRef().getStrCache();
				CTNumData numData = val.getNumRef().getNumCache();
				strData.setPtArray((CTStrVal[]) null); // unset old axis text
				numData.setPtArray((CTNumVal[]) null); // unset old values

				// set model
				long idx = 0;
				for (int j = 0; j < dataList.size(); j++) {
					// 判斷獲取的值是否爲空
					String value = "0";
					if (new BigDecimal(dataList.get(j).get(fldNameArr.get(i + position))) != null) {
						value = new BigDecimal(dataList.get(j).get(fldNameArr.get(i + position))).toString();
					}
					if (!"0".equals(value)) {
						CTNumVal numVal = numData.addNewPt();// 序列值
						numVal.setIdx(idx);
						numVal.setV(value);
					}
					CTStrVal sVal = strData.addNewPt();// 序列名稱
					sVal.setIdx(idx);
					sVal.setV(dataList.get(j).get(fldNameArr.get(0)));
					idx++;
				}
				numData.getPtCount().setVal(idx);
				strData.getPtCount().setVal(idx);

				// 赋值横坐标数据区域
				String axisDataRange = new CellRangeAddress(1, dataList.size(), 0, 0).formatAsString("Sheet1", true);
				cat.getStrRef().setF(axisDataRange);

				// 数据区域
				String numDataRange = new CellRangeAddress(1, dataList.size(), i + position, i + position)
						.formatAsString("Sheet1", true);
				val.getNumRef().setF(numDataRange);
			}

			File file = new File("D:\\lh\\poi替换.doc");
			if (file.exists()) {
				file.delete();
			}
			FileOutputStream fos = new FileOutputStream(file);
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
		}
	}
}
