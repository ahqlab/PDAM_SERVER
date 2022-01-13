package net.octacomm.sample.view;

import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.octacomm.sample.domain.Penetration;
import net.octacomm.sample.domain.Piece;
import net.octacomm.sample.domain.Report;
import net.octacomm.sample.utils.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ConnectStatsExcelView extends AbstractExcelView
{
  private static final int COLUNM_HEIGHT = 480;
  private static int tableLabelStartIndex = 3;
  private static int tableLabelEndIndex = 4;

  private static int tableValueStartIndex = 5;

  protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res)
    throws Exception
  {
    String userAgent = req.getHeader("User-Agent");
    String fileName = "PDAM_REPORT_" + DateUtil.getCurrentDatetime() + ".xls";

    if (userAgent.indexOf("MSIE") > -1)
      fileName = URLEncoder.encode(fileName, "utf-8");
    else {
      fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
    }

    res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
    res.setHeader("Content-Transfer-Encoding", "binary");

    HSSFSheet sheet = createFirstSheet(workbook);

    HSSFPrintSetup print = sheet.getPrintSetup();

    print.setPaperSize((short)9);

    print.setLandscape(true);

    print.setScale((short)52);
    sheet.setFitToPage(true);

    sheet.setMargin((short)2, 0.1D);
    sheet.setMargin((short)3, 0.1D);
    sheet.setMargin((short)0, 0.2D);
    sheet.setMargin((short)1, 0.2D);

    sheet.setColumnWidth(1, 3000);
    
    setExcelTitleLayoutSetting(sheet, workbook);
    setColunmLabelLayoutSettiog(sheet, workbook);

    List reportList = (List)model.get("domainList");
    System.err.println("reportList :  " + reportList.size());

    createColunm(sheet, workbook, reportList);
    createSumColunm(sheet, workbook, reportList);
  }

  private void createSumColunm(HSSFSheet sheet, HSSFWorkbook workbook, List<Report> reportList)
  {
    double sumTotalConnectWidth = 0.0D;
    double sumConnectLength = 0.0D;
    double sumDrillingDepth = 0.0D;
    double sumIntrusionDepth = 0.0D;
    double sumBlance = 0.0D;
    double sumGongSac = 0.0D;
    for (Report report : reportList) {
      sumTotalConnectWidth += Double.parseDouble(report.getTotalConnectWidth().isEmpty() ? "0" : report.getTotalConnectWidth());
      sumConnectLength += Double.parseDouble(report.getConnectLength().isEmpty() ? "0" : report.getConnectLength());
      sumDrillingDepth += Double.parseDouble(report.getDrillingDepth().isEmpty() ? "0" : report.getDrillingDepth());
      sumIntrusionDepth += Double.parseDouble(report.getIntrusionDepth().isEmpty() ? "0" : report.getIntrusionDepth());
      sumBlance += report.getBalance();
      sumGongSac += report.getGongSac();
    }

    HSSFRow row = sheet.createRow(tableValueStartIndex + reportList.size());
    setSumValues(workbook, row, 
      new String[] { 
      "합계", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      String.valueOf(String.format("%.1f", new Object[] { Double.valueOf(sumTotalConnectWidth) })), 
      String.valueOf(String.format("%.1f", new Object[] { Double.valueOf(sumConnectLength) })), 
      String.valueOf(String.format("%.1f", new Object[] { Double.valueOf(sumDrillingDepth) })), 
      String.valueOf(String.format("%.1f", new Object[] { Double.valueOf(sumIntrusionDepth) })), 
      String.valueOf(String.format("%.1f", new Object[] { Double.valueOf(sumBlance) })), 
      String.valueOf(String.format("%.1f", new Object[] { Double.valueOf(sumGongSac) })), 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      "", 
      //헤머효율삭제"" 
      });
  }

  private void setSumValues(HSSFWorkbook workbook, HSSFRow row, String[] strings)
  {
    CellStyle style = workbook.createCellStyle();
    style.setAlignment((short)2);
    style.setVerticalAlignment((short)1);
    style.setBorderRight((short)1);
    style.setBorderLeft((short)1);
    style.setBorderBottom((short)1);

    for (int i = 0; i < strings.length; i++) {
      HSSFCell cell = row.createCell(i);
      row.setHeight((short)480);
      cell.setCellValue(strings[i]);
      cell.setCellStyle(style);
    }
  }

  private void createColunm(HSSFSheet sheet, HSSFWorkbook workbook, List<Report> reportList) {
    for (int i = 0; i < reportList.size(); i++) {
      HSSFRow row1 = sheet.createRow(i + tableValueStartIndex);
      
      if(i == 3 || i == 4 ) {
    	  sheet.autoSizeColumn(i);
          sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 512 );
      }
      
      String[] strings = { String.valueOf(i + 1), 
        ((Report)reportList.get(i)).getCurrentDateTime(), 
        ((Report)reportList.get(i)).getPileType(), 
        ((Report)reportList.get(i)).getMethod(), 
        ((Report)reportList.get(i)).getLocation(), 
        ((Report)reportList.get(i)).getPileNo(), 
        ((Report)reportList.get(i)).getPileStandard(), 
        "", 
        "", 
        "", 
        "", 
        "", 
        ((Report)reportList.get(i)).getTotalConnectWidth(), 
        ((Report)reportList.get(i)).getConnectLength(), 
        ((Report)reportList.get(i)).getDrillingDepth(), 
        ((Report)reportList.get(i)).getIntrusionDepth(), 
        String.valueOf(((Report)reportList.get(i)).getBalance()), 
        String.valueOf(((Report)reportList.get(i)).getGongSac()), 
        ((Report)reportList.get(i)).getHammaT(), 
        ((Report)reportList.get(i)).getFallMeter(), 
        ((Report)reportList.get(i)).getManagedStandard(), 
        "", 
        "", 
        "", 
        "", 
        "", 
        ((Report)reportList.get(i)).getAvgPenetrationValue(), 
        ((Report)reportList.get(i)).getTotalPenetrationValue(), 
        ((Report)reportList.get(i)).getBigo(), 
        //,((Report)reportList.get(i)).getHammaEfficiency() 
        };

      strings[7] = ((Piece)((Report)reportList.get(i)).getPiece().get(0)).getValue();
      strings[8] = ((Piece)((Report)reportList.get(i)).getPiece().get(1)).getValue();
      strings[9] = (((Report)reportList.get(i)).getPiece().size() >= 4 ? ((Piece)((Report)reportList.get(i)).getPiece().get(2)).getValue() : "");
      strings[10] = (((Report)reportList.get(i)).getPiece().size() >= 5 ? ((Piece)((Report)reportList.get(i)).getPiece().get(3)).getValue() : "");
      strings[11] = ((Piece)((Report)reportList.get(i)).getPiece().get(((Report)reportList.get(i)).getPiece().size() - 1)).getValue();

      for (int j = 0; j <= 4; j++) {
        try {
          strings[(j + 21)] = (((Penetration)((Report)reportList.get(i)).getPenetrations().get(j)).getValue() != "" ? ((Penetration)((Report)reportList.get(i)).getPenetrations().get(j)).getValue() : "");
        } catch (IndexOutOfBoundsException e) {
          strings[(j + 21)] = "";
        }
      }
      setColumn(workbook, row1, strings);
    }
  }

  private HSSFSheet createFirstSheet(HSSFWorkbook workbook)
  {
    HSSFSheet sheet = workbook.createSheet();

    workbook.setSheetName(0, "접속통계");

    return sheet;
  }

  private void setExcelTitleLayoutSetting(HSSFSheet sheet, HSSFWorkbook workbook) {
    createExcelTitle(sheet, workbook);
    //sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 19));
    //헤머효율삭제로 한칸 땡김
    sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 18));
  }

  private void createExcelTitle(HSSFSheet sheet, HSSFWorkbook workbook)
  {
    HSSFRow firstRow = sheet.createRow(0);
    //setExcelTitles1(workbook, firstRow, new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" });
    //헤머효율삭제
    setExcelTitles1(workbook, firstRow, new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
    HSSFRow secondtRow = sheet.createRow(1);
    //setExcelTitles2(workbook, secondtRow, new String[] { "", "", "", "", "", "", "", "", "파일 항타 관입량 자동측정 시스템 (PDAM 시스템)\n Pile Driving Automatic Measurement system", "", "", "", "", "", "", "", "", "", "" });
    //헤머효율삭제
    setExcelTitles2(workbook, secondtRow, new String[] { "", "", "", "", "", "", "",  "파일 항타 관입량 자동측정 시스템 (PDAM 시스템)\n Pile Driving Automatic Measurement system", "", "", "", "", "", "", "", "", "", "", });
  }

  private void setExcelTitles1(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels) {
    CellStyle style = workbook.createCellStyle();

    style.setAlignment((short)2);
    style.setWrapText(true);
    style.setVerticalAlignment((short)1);
    style.setLocked(true);

    HSSFFont fontOfGothicBlackBold16 = workbook.createFont();
    fontOfGothicBlackBold16.setFontHeight((short)480);
    fontOfGothicBlackBold16.setBoldweight((short)700);
    style.setFont(fontOfGothicBlackBold16);

    for (int i = 0; i < columnLabels.length; i++) {
      HSSFCell cell = rows.createCell(i);
      rows.setHeight((short)0);
      cell.setCellValue(columnLabels[i]);
      cell.setCellStyle(style);
    }
  }

  private void setExcelTitles2(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels) {
    CellStyle style = workbook.createCellStyle();

    style.setAlignment((short)2);
    style.setWrapText(true);
    style.setVerticalAlignment((short)1);
    style.setLocked(true);

    HSSFFont fontOfGothicBlackBold16 = workbook.createFont();
    fontOfGothicBlackBold16.setFontHeight((short)480);
    fontOfGothicBlackBold16.setBoldweight((short)700);
    style.setFont(fontOfGothicBlackBold16);

    for (int i = 0; i < columnLabels.length; i++) {
      HSSFCell cell = rows.createCell(i);
      rows.setHeight((short)1500);
      cell.setCellValue(columnLabels[i]);
      cell.setCellStyle(style);
    }
  }

  private void createColumnLabels(HSSFSheet sheet, HSSFWorkbook workbook)
  {
    HSSFRow row1 = sheet.createRow(tableLabelStartIndex);

    //setColumnLabels(workbook, row1, new String[] { "번호", "시공일", "파일종류", "시공공법", "시공위치", "파일번호", "파일규격 \n( D )", "파일구분", "", "", "", "", "", "이음개소\n( EA )", "천공깊이\n( M )", "관잎깊이\n( M )", "파일잔량\n( M )", "공삭공\n( M )", "헤머무게\n( Ton )", "낙하높이\n( M )", "관리기준\n( mm )", "관입량 자동 측정( mm )", "", "", "", "", "", "", "비고", "해머효율\n( % )" });
    setColumnLabels(workbook, row1, new String[] { "번호", "시공일", "파일종류", "시공공법", "시공위치", "파일번호", "파일규격 \n( D )", "파일구분", "", "", "", "", "", "이음개소\n( EA )", "천공깊이\n( M )", "관잎깊이\n( M )", "파일잔량\n( M )", "공삭공\n( M )", "헤머무게\n( Ton )", "낙하높이\n( M )", "관리기준\n( mm )", "관입량 자동 측정( mm )", "", "", "", "", "", "", "비고" });

    HSSFRow row2 = sheet.createRow(tableLabelEndIndex);

    //setColumnLabels(workbook, row2, new String[] { "번호", "시공일", "파일종류", "시공공법", "시공위치", "파일번호", "파일규격 D", "단본", "하단", "중단", "중단", "상단", "합계", "이음개소", "천공깊이", "관잎깊이", "파일잔량", "공삭공", "헤머무게", "낙하높이", "관리기준", "1회", "2회", "3회", "4회", "5회", "평균관입", "최종관입", "비고", "해머효율" });
    setColumnLabels(workbook, row2, new String[] { "번호", "시공일", "파일종류", "시공공법", "시공위치", "파일번호", "파일규격 D", "단본", "하단", "중단", "중단", "상단", "합계", "이음개소", "천공깊이", "관잎깊이", "파일잔량", "공삭공", "헤머무게", "낙하높이", "관리기준", "1회", "2회", "3회", "4회", "5회", "평균관입", "최종관입", "비고" });
  }

  private void setColumn(HSSFWorkbook workbook, HSSFRow row1, String[] strings)
  {
    CellStyle style = workbook.createCellStyle();
    style.setAlignment((short)2);
    style.setVerticalAlignment((short)1);
    style.setBorderRight((short)1);
    style.setBorderLeft((short)1);
    style.setBorderBottom((short)1);
    style.setLocked(true);

    CellStyle redStyle = workbook.createCellStyle();
    redStyle.setAlignment((short)2);
    redStyle.setVerticalAlignment((short)1);
    redStyle.setBorderRight((short)1);
    redStyle.setBorderLeft((short)1);
    redStyle.setBorderBottom((short)1);
    redStyle.setFillForegroundColor((short)10);
    redStyle.setFillPattern((short)1);
    redStyle.setLocked(true);

    HSSFFont font = workbook.createFont();
    font.setColor((short)9);
    redStyle.setFont(font);

    for (int i = 0; i < strings.length; i++) {
      HSSFCell cell = null;
      if ((i > 18) && (i < 26)) {
        cell = row1.createCell(i);
        row1.setHeight((short)480);
        cell.setCellValue(strings[i]);
        cell.setCellStyle(style);
      } else {
        cell = row1.createCell(i);
        row1.setHeight((short)480);
        cell.setCellValue(strings[i]);
        cell.setCellStyle(style);
      }

      if ((i == 26) && 
        (Float.parseFloat(strings[20]) < Float.parseFloat(strings[26])))
        cell.setCellStyle(redStyle);
    }
  }

  private void setColunmLabelLayoutSettiog(HSSFSheet sheet, HSSFWorkbook workbook)
  {
    createColumnLabels(sheet, workbook);
    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      0, 
      0));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      1, 
      1));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      2, 
      2));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      3, 
      3));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      4, 
      4));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      5, 
      5));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      6, 
      6));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex - 1, 
      7, 
      12));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      13, 
      13));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      14, 
      14));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      15, 
      15));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      16, 
      16));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      17, 
      17));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      17, 
      17));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      18, 
      18));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      19, 
      19));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      20, 
      20));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex - 1, 
      21, 
      27));

    sheet.addMergedRegion(new CellRangeAddress(
      tableLabelStartIndex, 
      tableLabelEndIndex, 
      28, 
      28));
    //헤머효율삭제
//    sheet.addMergedRegion(new CellRangeAddress(
//      tableLabelStartIndex, 
//      tableLabelEndIndex, 
//      29, 
//      29));
  }

  private void setColumnLabels(HSSFWorkbook workbook, HSSFRow rows, String[] columnLabels)
  {
    CellStyle style = workbook.createCellStyle();
    style.setWrapText(true);
    style.setAlignment((short)2);
    style.setVerticalAlignment((short)1);
    style.setLocked(true);

    HSSFFont font = workbook.createFont();

    style.setFont(font);
    style.setBorderRight((short)1);
    style.setBorderLeft((short)1);
    style.setBorderTop((short)1);
    style.setBorderBottom((short)1);
    font.setBoldweight((short)700);

    for (int i = 0; i < columnLabels.length; i++) {
      HSSFCell cell = null;
      if ((i > 18) && (i < 26)) {
        cell = rows.createCell(i);
        rows.setHeight((short)480);
        cell.setCellValue(columnLabels[i]);
        cell.setCellStyle(style);
      } else {
        cell = rows.createCell(i);
        rows.setHeight((short)480);
        cell.setCellValue(columnLabels[i]);
        cell.setCellStyle(style);
      }
    }
  }
}