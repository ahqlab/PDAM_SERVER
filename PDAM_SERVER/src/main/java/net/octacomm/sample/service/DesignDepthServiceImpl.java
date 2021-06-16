package net.octacomm.sample.service;

import net.octacomm.sample.dao.mapper.DesignDepthMapper;
import net.octacomm.sample.dao.mapper.DeviceMapper;
import net.octacomm.sample.dao.mapper.UserMapper;
import net.octacomm.sample.domain.Construction;
import net.octacomm.sample.domain.DesignDepth;
import net.octacomm.sample.domain.Device;
import net.octacomm.sample.domain.User;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DesignDepthServiceImpl implements DesignDepthService {

	@Autowired
	private DesignDepthMapper mapper;

	@Override
	public List<DesignDepth> uploadExcelFile(MultipartFile file) {
		// TODO Auto-generated method stub
		List<DesignDepth> list = new ArrayList<DesignDepth>();

		OPCPackage opcPackage;
		int rowindex = 0;
		try {
			opcPackage = OPCPackage.open(file.getInputStream());
			XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);

			XSSFSheet sheet = workbook.getSheetAt(0);
			System.err.println("sheet.getLastRowNum(): " + sheet.getPhysicalNumberOfRows());
			int rows = sheet.getPhysicalNumberOfRows();
			for (rowindex = 1; rowindex < rows; rowindex++) {
				XSSFRow row = sheet.getRow(rowindex);
				DesignDepth depth = new DesignDepth();
				for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
					XSSFCell cell = row.getCell(i);
					
					
					String value = null;
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_FORMULA:
						value = cell.getCellFormula();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC: // 데이터 타입이 숫자.
						Integer ivalue = (int) cell.getNumericCellValue(); // 리턴타입이 double 임. integer로 형변환 해주고
						value = ivalue.toString(); // integer 로 형변환된 값을 String 으로 받아서 value값에 넣어준다.
						break;
					case XSSFCell.CELL_TYPE_STRING:
						value = "" + cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					case XSSFCell.CELL_TYPE_ERROR:
						value = "" + cell.getErrorCellValue();
						break;
					default:
					}
					
					if(i == 1) {
						depth.setPileType(value);
					}else if(i == 2) {
						depth.setMethod(value);
					}else if(i == 3) {
						depth.setLocation(value);
					}else if(i == 4) {
						depth.setPileNo(value);
					}else if(i == 5) {
						depth.setPileStandard(value);
					}else if(i == 6) {
						depth.setDesignDepth(value);
					}	
					System.err.println("cell value : " + value);
				}
				list.add(depth);
			}
		} catch (InvalidFormatException | IOException e1) {
			e1.printStackTrace();
		}
		return list;
	}
}
