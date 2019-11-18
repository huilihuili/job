package vip.eagleli.bi.ye.she.ji.word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.BarDirection;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;

public class POICreate {
	public static void main(String[] args) throws Exception {
		System.out.println("----");
		try (BufferedReader modelReader = new BufferedReader(new FileReader("bar-chart-data.txt"))) {

			String chartTitle = modelReader.readLine(); // first line is chart title
			System.out.println(chartTitle);

			String[] series = modelReader.readLine().split(",");
			System.out.println(Arrays.toString(series));

			// Category Axis Data
			List<String> listLanguages = new ArrayList<>(10);

			// Values
			List<Double> listCountries = new ArrayList<>(10);
			List<Double> listSpeakers = new ArrayList<>(10);

			// set model
			String ln;
			while ((ln = modelReader.readLine()) != null) {
				String[] vals = ln.split(",");
				listCountries.add(Double.valueOf(vals[0]));
				listSpeakers.add(Double.valueOf(vals[1]));
				listLanguages.add(vals[2]);
			}

			System.out.println(listCountries);
			System.out.println(listSpeakers);
			System.out.println(listLanguages);

			String[] categories = listLanguages.toArray(new String[listLanguages.size()]);
			Double[] values1 = listCountries.toArray(new Double[listCountries.size()]);
			Double[] values2 = listSpeakers.toArray(new Double[listSpeakers.size()]);

			try (XWPFDocument doc = new XWPFDocument()) {
				XWPFChart chart = doc.createChart(XDDFChart.DEFAULT_WIDTH, XDDFChart.DEFAULT_HEIGHT);
				setBarData(chart, chartTitle, series, categories, values1, values2);

				chart = doc.createChart(XDDFChart.DEFAULT_WIDTH, XDDFChart.DEFAULT_HEIGHT);
				setBarData(chart, chartTitle, series, categories, values1, values2);

				// save the result
				try (OutputStream out = new FileOutputStream("bar-chart-demo-output.docx")) {
					doc.write(out);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Done");
	}

	@Test
	public void createParagraph() {
		try {
			XWPFDocument doc = new XWPFDocument(); // 创建一个文档
			XWPFParagraph paragraph1 = doc.createParagraph(); // 创建一个段落
			XWPFRun run = paragraph1.createRun();
			paragraph1.createRun().setText("这是一个段落中的文字"); // 创建文字
			XWPFParagraph paragraph2 = doc.createParagraph();
			paragraph2.createRun().setText("这是另一个段落中的文字");
			try (OutputStream out = new FileOutputStream("D:\\lh\\段落.docx")) {
				doc.write(out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createPictureTest() {
		try {
			XWPFDocument doc = new XWPFDocument(); // 创建一个文档

			File file = new File("good-idea.png"); // 图片文件
			FileInputStream fis = new FileInputStream(file);

			XWPFParagraph paragraph = doc.createParagraph(); // 创建一个段落

			paragraph.createRun().addPicture(fis, XWPFDocument.PICTURE_TYPE_JPEG, "good-idea", Units.toEMU(299),
					Units.toEMU(316)); // 添加一张图片
			try (OutputStream out = new FileOutputStream("D:\\lh\\图片.docx")) {
				doc.write(out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createTableTest() {
		try {
			XWPFDocument doc = new XWPFDocument(); // 创建一个文档
			XWPFTable table = doc.createTable(2, 3); // 创建一个2行3列的表格

			XWPFTableRow row1 = table.getRow(0); // 获得第一行
			row1.getCell(0).setText("标题1"); // 为单元格赋值
			row1.getCell(1).setText("标题2");
			row1.getCell(2).setText("标题3");

			XWPFTableRow row2 = table.getRow(1);// 获得第二行
			row2.getCell(0).setText("内容1");
			row2.getCell(1).setText("内容2");
			row2.getCell(2).setText("内容3");

			// save the result
			try (OutputStream out = new FileOutputStream("D:\\lh\\表格.docx")) {
				doc.write(out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createBarTest() {
		String chartTitle = "各个月份蒸发量和降水量"; // 图表标题
		String[] axisTitles = { "月份", "毫升" }; // 坐标轴标题
		String[] series = { "蒸发量", "降水量" };
		String[] categories = { "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月" }; // 类别
		Double[] values1 = { 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3 }; // 蒸发量数据
		Double[] values2 = { 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3 }; // 降水量数据

		try {
			XWPFDocument doc = new XWPFDocument(); // 创建一个文档
			XWPFChart chart = doc.createChart(); // 创建一个图表

			// 创建坐标轴系
			XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
			bottomAxis.setTitle(axisTitles[0]);
			XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
			leftAxis.setTitle(axisTitles[1]);
			leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

			// 定义数据域
			final int numOfPoints = categories.length;
			final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, 0, 0));
			final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, 1, 1));
			final String valuesDataRange2 = chart.formatRange(new CellRangeAddress(1, numOfPoints, 2, 2));
			final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange, 0);
			final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(values1,
					valuesDataRange, 1);
			final XDDFNumericalDataSource<? extends Number> valuesData2 = XDDFDataSourcesFactory.fromArray(values2,
					valuesDataRange2, 2);

			// 创建柱状图
			XDDFBarChartData bar = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
			XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData);
			series1.setTitle(series[0], chart.setSheetTitle(series[0], 1));
			
			XDDFBarChartData.Series series2 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData2);
			series2.setTitle(series[1], chart.setSheetTitle(series[1], 2));

			bar.setVaryColors(true);
			bar.setBarDirection(BarDirection.COL);
			chart.plot(bar); // 画图

			// 设置图例
			XDDFChartLegend legend = chart.getOrAddLegend();
			legend.setPosition(LegendPosition.LEFT);
			legend.setOverlay(false);

			// 设置标题
			chart.setTitleText(chartTitle);
			chart.setTitleOverlay(false);

			// save the result
			try (OutputStream out = new FileOutputStream("D:\\lh\\图表.docx")) {
				doc.write(out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void setBarData(XWPFChart chart, String chartTitle, String[] series, String[] categories,
			Double[] values1, Double[] values2) {
		// Use a category axis for the bottom axis.
		XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
		bottomAxis.setTitle(series[2]);
		XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
		leftAxis.setTitle(series[0] + "," + series[1]);
		leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

		final int numOfPoints = categories.length;
		final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, 0, 0));
		final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, 1, 1));
		final String valuesDataRange2 = chart.formatRange(new CellRangeAddress(1, numOfPoints, 2, 2));
		final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange, 0);
		final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(values1,
				valuesDataRange, 1);
		values1[6] = 16.0; // if you ever want to change the underlying data
		final XDDFNumericalDataSource<? extends Number> valuesData2 = XDDFDataSourcesFactory.fromArray(values2,
				valuesDataRange2, 2);

		XDDFBarChartData bar = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
		XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData);
		series1.setTitle(series[0], chart.setSheetTitle(series[0], 1));

		XDDFBarChartData.Series series2 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData2);
		series2.setTitle(series[1], chart.setSheetTitle(series[1], 2));

		bar.setVaryColors(true);
		bar.setBarDirection(BarDirection.COL);
		chart.plot(bar);

		XDDFChartLegend legend = chart.getOrAddLegend();
		legend.setPosition(LegendPosition.LEFT);
		legend.setOverlay(false);

		chart.setTitleText(chartTitle);
		chart.setTitleOverlay(false);
	}
}
