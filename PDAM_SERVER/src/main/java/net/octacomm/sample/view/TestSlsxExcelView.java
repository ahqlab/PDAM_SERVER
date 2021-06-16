package net.octacomm.sample.view;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import net.octacomm.sample.utils.DateUtil;

public class TestSlsxExcelView extends AbstractView{

	int ROW_ACCESS_WINDOW_SIZE = 500;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
        String userAgent = request.getHeader("User-Agent");
		String fileName = "PDAM_REPORT_" + DateUtil.getCurrentDatetime() + ".xlsx";

		if (userAgent.indexOf("MSIE") > -1) {
			fileName = URLEncoder.encode(fileName, "utf-8");
		} else {  
			fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		}  

		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		//SXSSFWorkbook workbook = new SXSSFWorkbook();
		//
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, ROW_ACCESS_WINDOW_SIZE);
		SXSSFSheet sheet = createFirstSheet(sxssfWorkbook);
		
		SXSSFRow row = sheet.createRow(0); 
		SXSSFCell cell = row.createCell(0);
		cell.setCellValue("test");
    }
	
	
	private SXSSFSheet createFirstSheet(SXSSFWorkbook workbook) {
		
		SXSSFSheet sheet = workbook.createSheet();
		
		//sheet.protectSheet("we8104");
		workbook.setSheetName(0, "test");
		//sheet.setColumnWidth(0, (int)(100 * 36.57));
		return sheet;
	}
}
