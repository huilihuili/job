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
			XWPFDocument doc = new XWPFDocument(); // ����һ���ĵ�
			XWPFParagraph paragraph1 = doc.createParagraph(); // ����һ������
			XWPFRun run = paragraph1.createRun();
			paragraph1.createRun().setText("����һ�������е�����"); // ��������
			XWPFParagraph paragraph2 = doc.createParagraph();
			paragraph2.createRun().setText("������һ�������е�����");
			try (OutputStream out = new FileOutputStream("D:\\lh\\����.docx")) {
				doc.write(out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createPictureTest() {
		try {
			XWPFDocument doc = new XWPFDocument(); // ����һ���ĵ�

			File file = new File("good-idea.png"); // ͼƬ�ļ�
			FileInputStream fis = new FileInputStream(file);

			XWPFParagraph paragraph = doc.createParagraph(); // ����һ������

			paragraph.createRun().addPicture(fis, XWPFDocument.PICTURE_TYPE_JPEG, "good-idea", Units.toEMU(299),
					Units.toEMU(316)); // ���һ��ͼƬ
			try (OutputStream out = new FileOutputStream("D:\\lh\\ͼƬ.docx")) {
				doc.write(out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createTableTest() {
		try {
			XWPFDocument doc = new XWPFDocument(); // ����һ���ĵ�
			XWPFTable table = doc.createTable(2, 3); // ����һ��2��3�еı��

			XWPFTableRow row1 = table.getRow(0); // ��õ�һ��
			row1.getCell(0).setText("����1"); // Ϊ��Ԫ��ֵ
			row1.getCell(1).setText("����2");
			row1.getCell(2).setText("����3");

			XWPFTableRow row2 = table.getRow(1);// ��õڶ���
			row2.getCell(0).setText("����1");
			row2.getCell(1).setText("����2");
			row2.getCell(2).setText("����3");

			// save the result
			try (OutputStream out = new FileOutputStream("D:\\lh\\���.docx")) {
				doc.write(out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createBarTest() {
		String chartTitle = "�����·��������ͽ�ˮ��"; // ͼ�����
		String[] axisTitles = { "�·�", "����" }; // ���������
		String[] series = { "������", "��ˮ��" };
		String[] categories = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��" }; // ���
		Double[] values1 = { 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3 }; // ����������
		Double[] values2 = { 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3 }; // ��ˮ������

		try {
			XWPFDocument doc = new XWPFDocument(); // ����һ���ĵ�
			XWPFChart chart = doc.createChart(); // ����һ��ͼ��

			// ����������ϵ
			XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
			bottomAxis.setTitle(axisTitles[0]);
			XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
			leftAxis.setTitle(axisTitles[1]);
			leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

			// ����������
			final int numOfPoints = categories.length;
			final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, 0, 0));
			final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, 1, 1));
			final String valuesDataRange2 = chart.formatRange(new CellRangeAddress(1, numOfPoints, 2, 2));
			final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange, 0);
			final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(values1,
					valuesDataRange, 1);
			final XDDFNumericalDataSource<? extends Number> valuesData2 = XDDFDataSourcesFactory.fromArray(values2,
					valuesDataRange2, 2);

			// ������״ͼ
			XDDFBarChartData bar = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
			XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData);
			series1.setTitle(series[0], chart.setSheetTitle(series[0], 1));
			
			XDDFBarChartData.Series series2 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData2);
			series2.setTitle(series[1], chart.setSheetTitle(series[1], 2));

			bar.setVaryColors(true);
			bar.setBarDirection(BarDirection.COL);
			chart.plot(bar); // ��ͼ

			// ����ͼ��
			XDDFChartLegend legend = chart.getOrAddLegend();
			legend.setPosition(LegendPosition.LEFT);
			legend.setOverlay(false);

			// ���ñ���
			chart.setTitleText(chartTitle);
			chart.setTitleOverlay(false);

			// save the result
			try (OutputStream out = new FileOutputStream("D:\\lh\\ͼ��.docx")) {
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
