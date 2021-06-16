package net.octacomm.sample.view;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import org.springframework.web.servlet.view.document.AbstractExcelView;

import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.FileInventoryOfChart;
import net.octacomm.sample.domain.FileUsingChart;
import net.octacomm.sample.utils.DateUtil;

public class FileUsingChartExcelView extends AbstractExcelView {

	public boolean isPhc = true;

	private static final int COLUNM_HEIGHT = 480;

	private static short TEXT_SISE = (short) (16 * 14);
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		String userAgent = req.getHeader("User-Agent");
		String fileName = "PDAM_FILE_CHART_" + DateUtil.getCurrentDatetime() + ".xls";
		if (userAgent.indexOf("MSIE") > -1) {
			fileName = URLEncoder.encode(fileName, "utf-8");
		} else {
			fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		}
		res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		
		HSSFSheet sheet = createFirstSheet(workbook);
		HSSFPrintSetup print = sheet.getPrintSetup();
		print.setPaperSize((short) 9);
		print.setLandscape(true);
		sheet.setFitToPage(true);
		sheet.setMargin((short) 2, 0.1D);
		sheet.setMargin((short) 3, 0.1D);
		sheet.setMargin((short) 0, 0.2D);
		sheet.setMargin((short) 1, 0.2D);
		String pileName = (String) model.get("pileName");
		if (pileName.startsWith("PHC")) {
			this.isPhc = true;
			print.setScale((short) 30);
		} else {
			this.isPhc = false;
			print.setScale((short) 27);
		}
		Construction construction = (Construction) model.get("construction");
		setExcelTitleLayoutSetting(sheet, workbook, construction, pileName);
		setExcelConstructionTitleLayoutSetting(sheet, workbook);
		
		FileUsingChart chart1 = (FileUsingChart) model.get("chart1");
		FileUsingChart chart2 = (FileUsingChart) model.get("chart2");
		FileUsingChart chart3 = (FileUsingChart) model.get("chart3");
		FileUsingChart chart4 = (FileUsingChart) model.get("chart4");
		setExcelConstructionValueLayoutSetting(sheet, workbook, chart1, chart2, chart3, chart4);
		
		FileInventoryOfChart surplusChart = (FileInventoryOfChart) model.get("surplusChart");
		setExcelSurplusLayoutSetting(sheet, workbook, surplusChart);
		
		setExcelMiddleLayoutSetting(sheet, workbook);
		
		List<FileInventoryOfChart> fileInventoryChartList = (List<FileInventoryOfChart>) model.get("fileInventoryChartList");
		FileInventoryOfChart sumChart = (FileInventoryOfChart) model.get("sumChart");
		setExcelSumLayoutSetting(sheet, workbook, sumChart);
		
		setExcelValueSetting(sheet, workbook, fileInventoryChartList, workbook.createCellStyle());
	}

	private void setExcelValueSetting(HSSFSheet sheet, HSSFWorkbook workbook, List<FileInventoryOfChart> fileInventoryChartList, CellStyle cellStyle) {
		createExcelValue(sheet, workbook, fileInventoryChartList, cellStyle);
	}

	private void createExcelValue(HSSFSheet sheet, HSSFWorkbook workbook,
			List<FileInventoryOfChart> fileInventoryChartList , CellStyle cellStyle) {
		for (int i = 0; i < fileInventoryChartList.size(); i++) {
			sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1024);
			if (this.isPhc) {
				setExcelValue(workbook, sheet.createRow(i + 8),
						new String[] { ((FileInventoryOfChart) fileInventoryChartList.get(i)).getRegistDate(),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getTotal()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getAccum()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof51()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof61()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof71()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof81()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof91()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof101()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof111()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof121()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof131()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof141()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof151()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getTotal1()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof52()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof62()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof72()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof82()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof92()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof102()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof112()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof122()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof132()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof142()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof152()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getTotal2()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof53()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof63()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof73()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof83()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof93()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof103()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof113()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof123()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof133()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof143()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof153()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getTotal3()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof54()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof64()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof74()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof84()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof94()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof104()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof114()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof124()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof134()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof144()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof154()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getTotal4()),
								"" }, cellStyle);
			} else {
				
				setExcelValue(workbook, sheet.createRow(i + 8),
						new String[] { ((FileInventoryOfChart) fileInventoryChartList.get(i)).getRegistDate(),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getTotal()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getAccum()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof51()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof61()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof71()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof81()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof91()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof101()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof111()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof121()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof131()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof141()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof151()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof161()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof171()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof181()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getTotal1()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof52()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof62()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof72()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof82()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof92()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof102()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof112()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof122()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof132()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof142()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof152()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof162()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof172()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof182()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getTotal2()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof53()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof63()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof73()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof83()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof93()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof103()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof113()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof123()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof133()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof143()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof153()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof163()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof173()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof183()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getTotal3()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof54()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof64()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof74()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof84()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof94()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof104()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof114()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof124()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof134()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof144()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof154()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof164()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof174()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getMeterof184()),
								String.valueOf(((FileInventoryOfChart) fileInventoryChartList.get(i)).getTotal4()),
								"" }, cellStyle);
			}
		}
	}


	private void setExcelValue(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels, CellStyle cellStyle) {
		
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setWrapText(true);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setLocked(true);

		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		
		for (int i = 0; i < columnLabels.length; i++) {
			HSSFCell cell = rows.createCell(i);
			rows.setHeight((short) COLUNM_HEIGHT);
			cell.setCellValue(columnLabels[i]);
			cell.setCellStyle(cellStyle);
		}
	}

	private void setExcelSumLayoutSetting(HSSFSheet sheet, HSSFWorkbook workbook, FileInventoryOfChart sumChart) {
		createExcelSum(sheet, workbook, sumChart);
	}

	private void createExcelSum(HSSFSheet sheet, HSSFWorkbook workbook, FileInventoryOfChart sumChart) {
		if (isPhc) {
			setExcelSum(workbook, sheet.createRow(7),
					new String[] { "합계", "N.A", String.valueOf(sumChart.getTotal()),
							String.valueOf(sumChart.getMeterof51()), String.valueOf(sumChart.getMeterof61()),
							String.valueOf(sumChart.getMeterof71()), String.valueOf(sumChart.getMeterof81()),
							String.valueOf(sumChart.getMeterof91()), String.valueOf(sumChart.getMeterof101()),
							String.valueOf(sumChart.getMeterof111()), String.valueOf(sumChart.getMeterof121()),
							String.valueOf(sumChart.getMeterof131()), String.valueOf(sumChart.getMeterof141()),
							String.valueOf(sumChart.getMeterof151()), String.valueOf(sumChart.getTotal1()),
							String.valueOf(sumChart.getMeterof52()), String.valueOf(sumChart.getMeterof62()),
							String.valueOf(sumChart.getMeterof72()), String.valueOf(sumChart.getMeterof82()),
							String.valueOf(sumChart.getMeterof92()), String.valueOf(sumChart.getMeterof102()),
							String.valueOf(sumChart.getMeterof112()), String.valueOf(sumChart.getMeterof122()),
							String.valueOf(sumChart.getMeterof132()), String.valueOf(sumChart.getMeterof142()),
							String.valueOf(sumChart.getMeterof152()), String.valueOf(sumChart.getTotal2()),
							String.valueOf(sumChart.getMeterof53()), String.valueOf(sumChart.getMeterof63()),
							String.valueOf(sumChart.getMeterof73()), String.valueOf(sumChart.getMeterof83()),
							String.valueOf(sumChart.getMeterof93()), String.valueOf(sumChart.getMeterof103()),
							String.valueOf(sumChart.getMeterof113()), String.valueOf(sumChart.getMeterof123()),
							String.valueOf(sumChart.getMeterof133()), String.valueOf(sumChart.getMeterof143()),
							String.valueOf(sumChart.getMeterof153()), String.valueOf(sumChart.getTotal3()),
							String.valueOf(sumChart.getMeterof54()), String.valueOf(sumChart.getMeterof64()),
							String.valueOf(sumChart.getMeterof74()), String.valueOf(sumChart.getMeterof84()),
							String.valueOf(sumChart.getMeterof94()), String.valueOf(sumChart.getMeterof104()),
							String.valueOf(sumChart.getMeterof114()), String.valueOf(sumChart.getMeterof124()),
							String.valueOf(sumChart.getMeterof134()), String.valueOf(sumChart.getMeterof144()),
							String.valueOf(sumChart.getMeterof154()), String.valueOf(sumChart.getTotal4()), "" });
		} else {
			setExcelSum(workbook, sheet.createRow(7),
					new String[] { "합계", "N.A", String.valueOf(sumChart.getTotal()),
							String.valueOf(sumChart.getMeterof51()), String.valueOf(sumChart.getMeterof61()),
							String.valueOf(sumChart.getMeterof71()), String.valueOf(sumChart.getMeterof81()),
							String.valueOf(sumChart.getMeterof91()), String.valueOf(sumChart.getMeterof101()),
							String.valueOf(sumChart.getMeterof111()), String.valueOf(sumChart.getMeterof121()),
							String.valueOf(sumChart.getMeterof131()), String.valueOf(sumChart.getMeterof141()),
							String.valueOf(sumChart.getMeterof151()), String.valueOf(sumChart.getMeterof161()),
							String.valueOf(sumChart.getMeterof171()), String.valueOf(sumChart.getMeterof181()),
							String.valueOf(sumChart.getTotal1()), String.valueOf(sumChart.getMeterof52()),
							String.valueOf(sumChart.getMeterof62()), String.valueOf(sumChart.getMeterof72()),
							String.valueOf(sumChart.getMeterof82()), String.valueOf(sumChart.getMeterof92()),
							String.valueOf(sumChart.getMeterof102()), String.valueOf(sumChart.getMeterof112()),
							String.valueOf(sumChart.getMeterof122()), String.valueOf(sumChart.getMeterof132()),
							String.valueOf(sumChart.getMeterof142()), String.valueOf(sumChart.getMeterof152()),
							String.valueOf(sumChart.getMeterof162()), String.valueOf(sumChart.getMeterof172()),
							String.valueOf(sumChart.getMeterof182()), String.valueOf(sumChart.getTotal2()),
							String.valueOf(sumChart.getMeterof53()), String.valueOf(sumChart.getMeterof63()),
							String.valueOf(sumChart.getMeterof73()), String.valueOf(sumChart.getMeterof83()),
							String.valueOf(sumChart.getMeterof93()), String.valueOf(sumChart.getMeterof103()),
							String.valueOf(sumChart.getMeterof113()), String.valueOf(sumChart.getMeterof123()),
							String.valueOf(sumChart.getMeterof133()), String.valueOf(sumChart.getMeterof143()),
							String.valueOf(sumChart.getMeterof153()), String.valueOf(sumChart.getMeterof163()),
							String.valueOf(sumChart.getMeterof173()), String.valueOf(sumChart.getMeterof183()),
							String.valueOf(sumChart.getTotal3()), String.valueOf(sumChart.getMeterof54()),
							String.valueOf(sumChart.getMeterof64()), String.valueOf(sumChart.getMeterof74()),
							String.valueOf(sumChart.getMeterof84()), String.valueOf(sumChart.getMeterof94()),
							String.valueOf(sumChart.getMeterof104()), String.valueOf(sumChart.getMeterof114()),
							String.valueOf(sumChart.getMeterof124()), String.valueOf(sumChart.getMeterof134()),
							String.valueOf(sumChart.getMeterof144()), String.valueOf(sumChart.getMeterof154()),
							String.valueOf(sumChart.getMeterof164()), String.valueOf(sumChart.getMeterof174()),
							String.valueOf(sumChart.getMeterof184()), String.valueOf(sumChart.getTotal4()), "" });
		}

	}

	private void setExcelSum(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels) {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setLocked(true);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(HSSFColor.TAN.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		CellStyle redStyle = workbook.createCellStyle();
		redStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		redStyle.setWrapText(true);
		redStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		redStyle.setLocked(true);
		redStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		redStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		redStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		redStyle.setFillForegroundColor(HSSFColor.TAN.index);
		redStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		redStyle.setFont(font);

		for (int i = 0; i < columnLabels.length; i++) {
			HSSFCell cell = rows.createCell(i);
			rows.setHeight((short) COLUNM_HEIGHT);
			cell.setCellValue(columnLabels[i]);
			cell.setCellStyle(style);
		}
	}

	private void setExcelMiddleLayoutSetting(HSSFSheet sheet, HSSFWorkbook workbook) {
		createExcelMiddle(sheet, workbook);
		sheet.addMergedRegion(new CellRangeAddress(4, 6, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(4, 6, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(4, 6, 2, 2));

		if (isPhc) {
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 63 - 12, 63 - 12));
			sheet.addMergedRegion(new CellRangeAddress(4, 7, 63 - 12, 63 - 12));
		} else {
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 63, 63));
			sheet.addMergedRegion(new CellRangeAddress(4, 7, 63, 63));
		}

		if (isPhc) {
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 14));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 3, 14));

			// sheet.addMergedRegion(new CellRangeAddress(4, 4, 15, (15 + 11)));
			// sheet.addMergedRegion(new CellRangeAddress(5, 5, 15, (15 + 11)));
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 15, 26));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 15, 26));

			sheet.addMergedRegion(new CellRangeAddress(4, 4, 27, 38));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 27, 38));

			sheet.addMergedRegion(new CellRangeAddress(4, 4, 39, 50));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 39, 50));

			// sheet.addMergedRegion(new CellRangeAddress(4, 6 , (15 + 11 + 1 + 11 + 1 + 11)
			// + 1, (15 + 11 + 1 + 11 + 1 + 11) + 1));
		} else {
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 17));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 3, 17));

			sheet.addMergedRegion(new CellRangeAddress(4, 4, 18, (18 + 14)));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 18, (18 + 14)));

			sheet.addMergedRegion(new CellRangeAddress(4, 4, (18 + 14 + 1), (18 + 14 + 1 + 14)));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, (18 + 14 + 1), (18 + 14 + 1 + 14)));

			sheet.addMergedRegion(new CellRangeAddress(4, 4, (18 + 14 + 1 + 14 + 1), (18 + 14 + 1 + 14 + 1 + 14)));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, (18 + 14 + 1 + 14 + 1), (18 + 14 + 1 + 14 + 1 + 14)));
		}
		//
	}

	private void createExcelMiddle(HSSFSheet sheet, HSSFWorkbook workbook) {
		if (isPhc) {
			setExcelMiddleOfOne(workbook, sheet.createRow(4),
					new String[] { "날짜", "일일\n반입량\n(본)", "누계\n반입량\n(본)", "파일반입수량", "", "", "", "", "",
							"", "", "", "", "", "", "파일반입수량", "", "", "", "", "", "", "", "", "", "", "",
							"파일반입수량", "", "", "", "", "", "", "", "", "", "", "", "파일반입수량", "", "", "", "",
							"", "", "", "", "", "", "", "비고" });
			setExcelMiddleOfOne(workbook, sheet.createRow(5),
					new String[] { "", "", "", "단본", "", "", "", "", "", "", "", "", "", "", "", "하단", "", "", "",
							"", "", "", "", "", "", "", "", "중단", "", "", "", "", "", "", "", "", "", "", "", "상단",
							"", "", "", "", "", "", "", "", "", "", "", "비고" });
			setExcelMiddle(workbook, sheet.createRow(6),
					new String[] { "파일시공수량", "", "", "5M", "6M", "7M", "8M", "9M", "10M", "11M", "12M", "13M",
							"14M", "15M", "합계", "5M", "6M", "7M", "8M", "9M", "10M", "11M", "12M", "13M", "14M",
							"15M", "합계", "5M", "6M", "7M", "8M", "9M", "10M", "11M", "12M", "13M", "14M", "15M",
							"합계", "5M", "6M", "7M", "8M", "9M", "10M", "11M", "12M", "13M", "14M", "15M", "합계",
							"비고" });
		} else {
			setExcelMiddleOfOne(workbook, sheet.createRow(4),
					new String[] { "날짜", "일일\n반입량\n(본)", "누계\n반입량\n(본)", "파일반입수량", "", "", "", "", "",
							"", "", "", "", "", "", "", "", "", "파일반입수량", "", "", "", "", "", "", "", "", "", "",
							"", "", "", "", "파일반입수량", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"파일반입수량", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "비고" });
			setExcelMiddleOfOne(workbook, sheet.createRow(5),
					new String[] { "", "", "", "단본", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "하단",
							"", "", "", "", "", "", "", "", "", "", "", "", "", "", "중단", "", "", "", "", "", "", "",
							"", "", "", "", "", "", "", "상단", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"비고" });
			setExcelMiddle(workbook, sheet.createRow(6),
					new String[] { "파일시공수량", "", "", "5M", "6M", "7M", "8M", "9M", "10M", "11M", "12M", "13M",
							"14M", "15M", "16M", "17M", "18M", "합계", "5M", "6M", "7M", "8M", "9M", "10M", "11M",
							"12M", "13M", "14M", "15M", "16M", "17M", "18M", "합계", "5M", "6M", "7M", "8M", "9M",
							"10M", "11M", "12M", "13M", "14M", "15M", "16M", "17M", "18M", "합계", "5M", "6M", "7M",
							"8M", "9M", "10M", "11M", "12M", "13M", "14M", "15M", "16M", "17M", "18M", "합계", "비고" });
		}

	}

	private void setExcelMiddleOfOne(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels) {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setLocked(true);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(HSSFColor.TAN.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		CellStyle redStyle = workbook.createCellStyle();
		redStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		redStyle.setWrapText(true);
		redStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		redStyle.setLocked(true);
		redStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		redStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		redStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		redStyle.setFillForegroundColor(HSSFColor.TAN.index);
		redStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		HSSFFont font = workbook.createFont();
		font.setFontHeight(TEXT_SISE);
		redStyle.setFont(font);

		for (int i = 0; i < columnLabels.length; i++) {
			HSSFCell cell = rows.createCell(i);
			rows.setHeight((short) COLUNM_HEIGHT);
			cell.setCellValue(columnLabels[i]);
			cell.setCellStyle(style);

			if (i == 0 || i == 1 || i == 2 || i == 3 || i == 15 || i == 27 || i == 39 || i == 51) {
				rows.setHeight((short) COLUNM_HEIGHT);
				cell.setCellValue(columnLabels[i]);
				cell.setCellStyle(redStyle);
			} else {
				rows.setHeight((short) COLUNM_HEIGHT);
				cell.setCellValue(columnLabels[i]);
				cell.setCellStyle(style);
			}

		}

	}

	private void setExcelMiddle(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels) {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setLocked(true);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(HSSFColor.TAN.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < columnLabels.length; i++) {
			HSSFCell cell = rows.createCell(i);
			rows.setHeight((short) COLUNM_HEIGHT);
			cell.setCellValue(columnLabels[i]);
			cell.setCellStyle(style);
		}

	}

	private void setExcelSurplusLayoutSetting(HSSFSheet sheet, HSSFWorkbook workbook,
			FileInventoryOfChart surplusChart) {
		createExcelSurplus(sheet, workbook, surplusChart);
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
	}

	private void createExcelSurplus(HSSFSheet sheet, HSSFWorkbook workbook, FileInventoryOfChart surplusChart) {
		HSSFRow secondtRow = sheet.createRow(3);
		if (isPhc) {
			String allTotal = String.valueOf(surplusChart.getTotal1() + surplusChart.getTotal2()
					+ surplusChart.getTotal3() + surplusChart.getTotal4());
			setExcelConstructionSurplus(workbook, secondtRow,
					new String[] { "파일재고수량", "", allTotal, String.valueOf(surplusChart.getMeterof51()),
							String.valueOf(surplusChart.getMeterof61()), String.valueOf(surplusChart.getMeterof71()),
							String.valueOf(surplusChart.getMeterof81()), String.valueOf(surplusChart.getMeterof91()),
							String.valueOf(surplusChart.getMeterof101()), String.valueOf(surplusChart.getMeterof111()),
							String.valueOf(surplusChart.getMeterof121()), String.valueOf(surplusChart.getMeterof131()),
							String.valueOf(surplusChart.getMeterof141()), String.valueOf(surplusChart.getMeterof151()),
							String.valueOf(surplusChart.getTotal1()), String.valueOf(surplusChart.getMeterof52()),
							String.valueOf(surplusChart.getMeterof62()), String.valueOf(surplusChart.getMeterof72()),
							String.valueOf(surplusChart.getMeterof82()), String.valueOf(surplusChart.getMeterof92()),
							String.valueOf(surplusChart.getMeterof102()), String.valueOf(surplusChart.getMeterof112()),
							String.valueOf(surplusChart.getMeterof122()), String.valueOf(surplusChart.getMeterof132()),
							String.valueOf(surplusChart.getMeterof142()), String.valueOf(surplusChart.getMeterof152()),
							String.valueOf(surplusChart.getTotal2()), String.valueOf(surplusChart.getMeterof53()),
							String.valueOf(surplusChart.getMeterof63()), String.valueOf(surplusChart.getMeterof73()),
							String.valueOf(surplusChart.getMeterof83()), String.valueOf(surplusChart.getMeterof93()),
							String.valueOf(surplusChart.getMeterof103()), String.valueOf(surplusChart.getMeterof113()),
							String.valueOf(surplusChart.getMeterof123()), String.valueOf(surplusChart.getMeterof133()),
							String.valueOf(surplusChart.getMeterof143()), String.valueOf(surplusChart.getMeterof153()),
							String.valueOf(surplusChart.getTotal3()), String.valueOf(surplusChart.getMeterof54()),
							String.valueOf(surplusChart.getMeterof64()), String.valueOf(surplusChart.getMeterof74()),
							String.valueOf(surplusChart.getMeterof84()), String.valueOf(surplusChart.getMeterof94()),
							String.valueOf(surplusChart.getMeterof104()), String.valueOf(surplusChart.getMeterof114()),
							String.valueOf(surplusChart.getMeterof124()), String.valueOf(surplusChart.getMeterof134()),
							String.valueOf(surplusChart.getMeterof144()), String.valueOf(surplusChart.getMeterof154()),
							String.valueOf(surplusChart.getTotal4()), "" });
		} else {
			String allTotal = String.valueOf(surplusChart.getTotal1() + surplusChart.getTotal2()
					+ surplusChart.getTotal3() + surplusChart.getTotal4());

			setExcelConstructionSurplus(workbook, secondtRow,
					new String[] { "파일재고수량", "", allTotal, String.valueOf(surplusChart.getMeterof51()),
							String.valueOf(surplusChart.getMeterof61()), String.valueOf(surplusChart.getMeterof71()),
							String.valueOf(surplusChart.getMeterof81()), String.valueOf(surplusChart.getMeterof91()),
							String.valueOf(surplusChart.getMeterof101()), String.valueOf(surplusChart.getMeterof111()),
							String.valueOf(surplusChart.getMeterof121()), String.valueOf(surplusChart.getMeterof131()),
							String.valueOf(surplusChart.getMeterof141()), String.valueOf(surplusChart.getMeterof151()),
							String.valueOf(surplusChart.getMeterof161()), String.valueOf(surplusChart.getMeterof171()),
							String.valueOf(surplusChart.getMeterof181()), String.valueOf(surplusChart.getTotal1()),
							String.valueOf(surplusChart.getMeterof52()), String.valueOf(surplusChart.getMeterof62()),
							String.valueOf(surplusChart.getMeterof72()), String.valueOf(surplusChart.getMeterof82()),
							String.valueOf(surplusChart.getMeterof92()), String.valueOf(surplusChart.getMeterof102()),
							String.valueOf(surplusChart.getMeterof112()), String.valueOf(surplusChart.getMeterof122()),
							String.valueOf(surplusChart.getMeterof132()), String.valueOf(surplusChart.getMeterof142()),
							String.valueOf(surplusChart.getMeterof152()), String.valueOf(surplusChart.getMeterof162()),
							String.valueOf(surplusChart.getMeterof172()), String.valueOf(surplusChart.getMeterof182()),
							String.valueOf(surplusChart.getTotal2()), String.valueOf(surplusChart.getMeterof53()),
							String.valueOf(surplusChart.getMeterof63()), String.valueOf(surplusChart.getMeterof73()),
							String.valueOf(surplusChart.getMeterof83()), String.valueOf(surplusChart.getMeterof93()),
							String.valueOf(surplusChart.getMeterof103()), String.valueOf(surplusChart.getMeterof113()),
							String.valueOf(surplusChart.getMeterof123()), String.valueOf(surplusChart.getMeterof133()),
							String.valueOf(surplusChart.getMeterof143()), String.valueOf(surplusChart.getMeterof153()),
							String.valueOf(surplusChart.getMeterof163()), String.valueOf(surplusChart.getMeterof173()),
							String.valueOf(surplusChart.getMeterof183()), String.valueOf(surplusChart.getTotal3()),
							String.valueOf(surplusChart.getMeterof54()), String.valueOf(surplusChart.getMeterof64()),
							String.valueOf(surplusChart.getMeterof74()), String.valueOf(surplusChart.getMeterof84()),
							String.valueOf(surplusChart.getMeterof94()), String.valueOf(surplusChart.getMeterof104()),
							String.valueOf(surplusChart.getMeterof114()), String.valueOf(surplusChart.getMeterof124()),
							String.valueOf(surplusChart.getMeterof134()), String.valueOf(surplusChart.getMeterof144()),
							String.valueOf(surplusChart.getMeterof154()), String.valueOf(surplusChart.getMeterof164()),
							String.valueOf(surplusChart.getMeterof174()), String.valueOf(surplusChart.getMeterof184()),
							String.valueOf(surplusChart.getTotal4()), "" });
		}

	}

	private void setExcelConstructionSurplus(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels) {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setLocked(true);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(HSSFColor.YELLOW.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		style.setFont(font);

		CellStyle textBicStyle = workbook.createCellStyle();
		textBicStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		textBicStyle.setWrapText(true);
		textBicStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		textBicStyle.setLocked(true);
		textBicStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		textBicStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		textBicStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		textBicStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
		textBicStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		HSSFFont bicStylefont = workbook.createFont();
		bicStylefont.setColor(HSSFColor.RED.index);
		bicStylefont.setFontHeight(TEXT_SISE);// �궗�씠利�
		textBicStyle.setFont(bicStylefont);

		for (int i = 0; i < columnLabels.length; i++) {
			HSSFCell cell = rows.createCell(i);
			rows.setHeight((short) COLUNM_HEIGHT);
			cell.setCellValue(columnLabels[i]);
			cell.setCellStyle(style);

			if (i == 0) {
				rows.setHeight((short) COLUNM_HEIGHT);
				cell.setCellValue(columnLabels[i]);
				cell.setCellStyle(textBicStyle);
			} else {
				rows.setHeight((short) COLUNM_HEIGHT);
				cell.setCellValue(columnLabels[i]);
				cell.setCellStyle(style);
			}

		}

	}

	private void setExcelTitleLayoutSetting(HSSFSheet sheet, HSSFWorkbook workbook, Construction construction,
			String pileName) {
		createExcelTitle(sheet, workbook, construction, pileName);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

		if (isPhc) {
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 14));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 15, 26));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 27, 51));
		} else {
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 14 + 3));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 15 + 3, 26 + 3));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 27 + 3, (51 + 12)));
		}

	}

	private void createExcelTitle(HSSFSheet sheet, HSSFWorkbook workbook, Construction construction, String pileName) {
		HSSFRow firstRow = sheet.createRow(0);
		if (isPhc) {
			setExcelTitles1(workbook, firstRow,
					new String[] { "현장명", "", "", construction.getLocation(), "", "", "", "", "", "", "", "", "", "", "",
							pileName, "", "", "", "", "", "", "", "", "", "", "", "파일 반입 및 재고", "", "", "", "",
							"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" });
		} else {
			setExcelTitles1(workbook, firstRow,
					new String[] { "현장명", "", "", construction.getLocation(), "", "", "", "", "", "", "", "", "", "", "",
							"", "", "", pileName, "", "", "", "", "", "", "", "", "", "", "", "", "",
							"파일 반입 및 재고", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"", "", "", "", "", "", "", "", "", "", "", "", "" });
		}

	}

	private void setExcelTitles1(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels) {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		HSSFFont fontOfGothicBlackBold16 = workbook.createFont();
		fontOfGothicBlackBold16.setFontHeight((short) (24 * 13)); // �궗�씠利�
		fontOfGothicBlackBold16.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD); // 蹂쇰뱶 (援듦쾶)
		style.setFont(fontOfGothicBlackBold16);

		for (int i = 0; i < columnLabels.length; i++) {

			HSSFCell cell = rows.createCell(i);
			rows.setHeight((short) COLUNM_HEIGHT);
			cell.setCellValue(columnLabels[i]);
			cell.setCellStyle(style);
		}
	}

	private void setExcelConstructionTitleLayoutSetting(HSSFSheet sheet, HSSFWorkbook workbook) {
		createConstructionExcelTitle(sheet, workbook);
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 1));
	}

	private void createConstructionExcelTitle(HSSFSheet sheet, HSSFWorkbook workbook) {
		HSSFRow secondtRow = sheet.createRow(1);
		sheet.setColumnWidth(2, (sheet.getColumnWidth(1)) + (short) 1024 * 2);

		if (isPhc) {
			setExcelConstructionTitles(workbook, secondtRow,
					new String[] { "파일시공수량", "", "총작업량(본)", "5M", "6M", "7M", "8M", "9M", "10M", "11M", "12M",
							"13M", "14M", "15M", "합계", "5M", "6M", "7M", "8M", "9M", "10M", "11M", "12M", "13M",
							"14M", "15M", "합계", "5M", "6M", "7M", "8M", "9M", "10M", "11M", "12M", "13M", "14M",
							"15M", "합계", "5M", "6M", "7M", "8M", "9M", "10M", "11M", "12M", "13M", "14M", "15M",
							"합계", "비고" });
		} else {
			setExcelConstructionTitles(workbook, secondtRow,
					new String[] { "파일시공수량", "", "총작업량(본)", "5M", "6M", "7M", "8M", "9M", "10M", "11M", "12M",
							"13M", "14M", "15M", "16M", "17M", "18M", "합계", "5M", "6M", "7M", "8M", "9M", "10M",
							"11M", "12M", "13M", "14M", "15M", "16M", "17M", "18M", "합계", "5M", "6M", "7M", "8M",
							"9M", "10M", "11M", "12M", "13M", "14M", "15M", "16M", "17M", "18M", "합계", "5M", "6M",
							"7M", "8M", "9M", "10M", "11M", "12M", "13M", "14M", "15M", "16M", "17M", "18M", "합계",
							"비고" });
		}

	}

	private void setExcelConstructionTitles(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setLocked(true);
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		CellStyle redStyle = workbook.createCellStyle();
		redStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		redStyle.setWrapText(true);
		redStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		redStyle.setLocked(true);
		redStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		redStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		redStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		redStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		redStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setFontHeight(TEXT_SISE);// �궗�씠利�
		redStyle.setFont(font);

		CellStyle textBicStyle = workbook.createCellStyle();
		textBicStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		textBicStyle.setWrapText(true);
		textBicStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		textBicStyle.setLocked(true);
		textBicStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		textBicStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		textBicStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		textBicStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		textBicStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		HSSFFont bicStyleFont = workbook.createFont();
		bicStyleFont.setFontHeight(TEXT_SISE);// �궗�씠利�
		textBicStyle.setFont(bicStyleFont);

		for (int i = 0; i < columnLabels.length; i++) {
			HSSFCell cell = rows.createCell(i);
			if (i == 0 || i == 1 || i == 2) {

				rows.setHeight((short) COLUNM_HEIGHT);
				cell.setCellValue(columnLabels[i]);
				cell.setCellStyle(textBicStyle);
			} else {
				// HSSFCell cell = rows.createCell(i);
				rows.setHeight((short) COLUNM_HEIGHT);
				cell.setCellValue(columnLabels[i]);
				cell.setCellStyle(style);
			}
		}
	}

	private void setExcelConstructionValues(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setLocked(true);
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		CellStyle redStyle = workbook.createCellStyle();
		redStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		redStyle.setWrapText(true);
		redStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		redStyle.setLocked(true);
		redStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		redStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		redStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		redStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		redStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setFontHeight(TEXT_SISE);// �궗�씠利�
		redStyle.setFont(font);

		CellStyle textBicStyle = workbook.createCellStyle();
		textBicStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		textBicStyle.setWrapText(true);
		textBicStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		textBicStyle.setLocked(true);
		textBicStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		textBicStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		textBicStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		textBicStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		textBicStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		HSSFFont bicStyleFont = workbook.createFont();
		bicStyleFont.setFontHeight(TEXT_SISE);// �궗�씠利�
		textBicStyle.setFont(bicStyleFont);

		for (int i = 0; i < columnLabels.length; i++) {
			HSSFCell cell = rows.createCell(i);
			rows.setHeight((short) COLUNM_HEIGHT);
			cell.setCellValue(columnLabels[i]);
			cell.setCellStyle(style);
		}
	}

	private void setExcelConstructionValueLayoutSetting(HSSFSheet sheet, HSSFWorkbook workbook, FileUsingChart chart1,
			FileUsingChart chart2, FileUsingChart chart3, FileUsingChart chart4) {
		createConstructionValueExcelTitle(sheet, workbook, chart1, chart2, chart3, chart4);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));

	}

	private void createConstructionValueExcelTitle(HSSFSheet sheet, HSSFWorkbook workbook, FileUsingChart chart1,
			FileUsingChart chart2, FileUsingChart chart3, FileUsingChart chart4) {
		HSSFRow secondtRow = sheet.createRow(2);
		if (isPhc) {
			String total = String
					.valueOf(chart1.getTotal() + chart2.getTotal() + chart3.getTotal() + chart4.getTotal());
			setExcelConstructionValues(workbook, secondtRow,
					new String[] { "", "", total, String.valueOf(chart1.getValue5()),
							String.valueOf(chart1.getValue6()), String.valueOf(chart1.getValue7()),
							String.valueOf(chart1.getValue8()), String.valueOf(chart1.getValue9()),
							String.valueOf(chart1.getValue10()), String.valueOf(chart1.getValue11()),
							String.valueOf(chart1.getValue12()), String.valueOf(chart1.getValue13()),
							String.valueOf(chart1.getValue14()), String.valueOf(chart1.getValue15()),
							String.valueOf(chart1.getTotal()), String.valueOf(chart2.getValue5()),
							String.valueOf(chart2.getValue6()), String.valueOf(chart2.getValue7()),
							String.valueOf(chart2.getValue8()), String.valueOf(chart2.getValue9()),
							String.valueOf(chart2.getValue10()), String.valueOf(chart2.getValue11()),
							String.valueOf(chart2.getValue12()), String.valueOf(chart2.getValue13()),
							String.valueOf(chart2.getValue14()), String.valueOf(chart2.getValue15()),
							String.valueOf(chart2.getTotal()), String.valueOf(chart3.getValue5()),
							String.valueOf(chart3.getValue6()), String.valueOf(chart3.getValue7()),
							String.valueOf(chart3.getValue8()), String.valueOf(chart3.getValue9()),
							String.valueOf(chart3.getValue10()), String.valueOf(chart3.getValue11()),
							String.valueOf(chart3.getValue12()), String.valueOf(chart3.getValue13()),
							String.valueOf(chart3.getValue14()), String.valueOf(chart3.getValue15()),
							String.valueOf(chart3.getTotal()), String.valueOf(chart4.getValue5()),
							String.valueOf(chart4.getValue6()), String.valueOf(chart4.getValue7()),
							String.valueOf(chart4.getValue8()), String.valueOf(chart4.getValue9()),
							String.valueOf(chart4.getValue10()), String.valueOf(chart4.getValue11()),
							String.valueOf(chart4.getValue12()), String.valueOf(chart4.getValue13()),
							String.valueOf(chart4.getValue14()), String.valueOf(chart4.getValue15()),
							String.valueOf(chart4.getTotal()), "비고" });
		} else {
			String total = String
					.valueOf(chart1.getTotal() + chart2.getTotal() + chart3.getTotal() + chart4.getTotal());
			setExcelConstructionValues(workbook, secondtRow,
					new String[] { "", "", total, String.valueOf(chart1.getValue5()),
							String.valueOf(chart1.getValue6()), String.valueOf(chart1.getValue7()),
							String.valueOf(chart1.getValue8()), String.valueOf(chart1.getValue9()),
							String.valueOf(chart1.getValue10()), String.valueOf(chart1.getValue11()),
							String.valueOf(chart1.getValue12()), String.valueOf(chart1.getValue13()),
							String.valueOf(chart1.getValue14()), String.valueOf(chart1.getValue15()),
							String.valueOf(chart1.getValue16()), String.valueOf(chart1.getValue17()),
							String.valueOf(chart1.getValue18()), String.valueOf(chart1.getTotal()),
							String.valueOf(chart2.getValue5()), String.valueOf(chart2.getValue6()),
							String.valueOf(chart2.getValue7()), String.valueOf(chart2.getValue8()),
							String.valueOf(chart2.getValue9()), String.valueOf(chart2.getValue10()),
							String.valueOf(chart2.getValue11()), String.valueOf(chart2.getValue12()),
							String.valueOf(chart2.getValue13()), String.valueOf(chart2.getValue14()),
							String.valueOf(chart2.getValue15()), String.valueOf(chart2.getValue16()),
							String.valueOf(chart2.getValue17()), String.valueOf(chart2.getValue18()),
							String.valueOf(chart2.getTotal()), String.valueOf(chart3.getValue5()),
							String.valueOf(chart3.getValue6()), String.valueOf(chart3.getValue7()),
							String.valueOf(chart3.getValue8()), String.valueOf(chart3.getValue9()),
							String.valueOf(chart3.getValue10()), String.valueOf(chart3.getValue11()),
							String.valueOf(chart3.getValue12()), String.valueOf(chart3.getValue13()),
							String.valueOf(chart3.getValue14()), String.valueOf(chart3.getValue15()),
							String.valueOf(chart3.getValue16()), String.valueOf(chart3.getValue17()),
							String.valueOf(chart3.getValue18()), String.valueOf(chart3.getTotal()),
							String.valueOf(chart4.getValue5()), String.valueOf(chart4.getValue6()),
							String.valueOf(chart4.getValue7()), String.valueOf(chart4.getValue8()),
							String.valueOf(chart4.getValue9()), String.valueOf(chart4.getValue10()),
							String.valueOf(chart4.getValue11()), String.valueOf(chart4.getValue12()),
							String.valueOf(chart4.getValue13()), String.valueOf(chart4.getValue14()),
							String.valueOf(chart4.getValue15()), String.valueOf(chart4.getValue16()),
							String.valueOf(chart4.getValue17()), String.valueOf(chart4.getValue18()),
							String.valueOf(chart4.getTotal()), "비고" });
		}

	}

	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet();
		// sheet.protectSheet("we8104");
		workbook.setSheetName(0, "총 파일집계표");
		// sheet.setColumnWidth(0, (int)(100 * 36.57));
		return sheet;
	}
}
