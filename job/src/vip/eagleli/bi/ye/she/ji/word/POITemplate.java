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
			XWPFDocument doc = new XWPFDocument(new FileInputStream(new File("D:\\lh\\POIģ��.docx")));
			List<XWPFTable> tables = doc.getTables();
			List<XWPFPictureData> pictures = doc.getAllPictures();
			List<XWPFChart> charts = doc.getCharts();

			System.out.println(tables.size());
			System.out.println(pictures.size());
			System.out.println(charts.size());

			XWPFTable table = tables.get(0);
			table.getRow(0).getCell(0).setText("���滻");

			XWPFChart chart = charts.get(0);
			CTChart ctChart = chart.getCTChart();
			CTPlotArea plotArea = ctChart.getPlotArea();

			CTBarChart barChart = plotArea.getBarChartArray(0);
			List<CTBarSer> serList = barChart.getSerList(); // �@ȡ����D��λ
			System.out.println(serList.size());
			int position = 1;

			// �����ʂ�
			List<String> titleArr = new ArrayList<String>();// ����
			titleArr.add("title");
			titleArr.add("�׻����Y�a�Qֵ����22222(%)");
			titleArr.add("�~���(%)");
			titleArr.add("�~���(%)");

			List<String> fldNameArr = new ArrayList<String>();// �ֶ���
			fldNameArr.add("item1");
			fldNameArr.add("item2");
			fldNameArr.add("item3");
			fldNameArr.add("item4");

			// ��������
			List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

			// ��һ�Д���
			Map<String, String> base1 = new HashMap<String, String>();
			base1.put("item1", "�����M��");
			base1.put("item2", "500");
			base1.put("item3", "500");
			base1.put("item4", "250");

			// �ڶ��Д���
			Map<String, String> base2 = new HashMap<String, String>();
			base2.put("item1", "�����M��");
			base2.put("item2", "300");
			base2.put("item3", "500");
			base2.put("item4", "250");

			// �����Д���
			Map<String, String> base3 = new HashMap<String, String>();
			base3.put("item1", "ס���M��");
			base3.put("item2", "300");
			base3.put("item3", "350");
			base3.put("item4", "250");

			dataList.add(base1);
			dataList.add(base2);
			dataList.add(base3);

			// ������������
			for (int i = 0; i < serList.size(); i++) {
				// CTSerTx tx=null;
				CTAxDataSource cat = null;
				CTNumDataSource val = null;
				CTBarSer ser = barChart.getSerArray(i);
				// tx= ser.getTx();
				// Category Axis Data
				cat = ser.getCat();
				// ��ȡͼ���ֵ
				val = ser.getVal();
				// strData.set
				CTStrData strData = cat.getStrRef().getStrCache();
				CTNumData numData = val.getNumRef().getNumCache();
				strData.setPtArray((CTStrVal[]) null); // unset old axis text
				numData.setPtArray((CTNumVal[]) null); // unset old values

				// set model
				long idx = 0;
				for (int j = 0; j < dataList.size(); j++) {
					// �Д�@ȡ��ֵ�Ƿ񠑿�
					String value = "0";
					if (new BigDecimal(dataList.get(j).get(fldNameArr.get(i + position))) != null) {
						value = new BigDecimal(dataList.get(j).get(fldNameArr.get(i + position))).toString();
					}
					if (!"0".equals(value)) {
						CTNumVal numVal = numData.addNewPt();// ����ֵ
						numVal.setIdx(idx);
						numVal.setV(value);
					}
					CTStrVal sVal = strData.addNewPt();// �������Q
					sVal.setIdx(idx);
					sVal.setV(dataList.get(j).get(fldNameArr.get(0)));
					idx++;
				}
				numData.getPtCount().setVal(idx);
				strData.getPtCount().setVal(idx);

				// ��ֵ��������������
				String axisDataRange = new CellRangeAddress(1, dataList.size(), 0, 0).formatAsString("Sheet1", true);
				cat.getStrRef().setF(axisDataRange);

				// ��������
				String numDataRange = new CellRangeAddress(1, dataList.size(), i + position, i + position)
						.formatAsString("Sheet1", true);
				val.getNumRef().setF(numDataRange);
			}

			File file = new File("D:\\lh\\poi�滻.doc");
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
