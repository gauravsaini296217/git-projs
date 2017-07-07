package aadhaartokens.views;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import aadhaartokens.model.TokenDetail;

public class AadhaarTokenReport extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<TokenDetail> tokenDetails = (List<TokenDetail>) model.get("tokenDetails");
        
        // create a new Excel sheet
        XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Token Details");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
         
        // create header row
        XSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue("State");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("District");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Pec");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("Enrolment Type");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("Name");
        header.getCell(4).setCellStyle(style);
         
        header.createCell(5).setCellValue("ContactNo");
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue("Email Id");
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue("Aadhaar No");
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue("Token No");
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue("Date");
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue("Token Time");
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue("Status");
        header.getCell(11).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (TokenDetail tokenDetail : tokenDetails) {
            Row aRow = sheet.createRow(rowCount++);
            
            aRow.createCell(0).setCellValue(tokenDetail.getState());
            aRow.createCell(1).setCellValue(tokenDetail.getRegion());
            aRow.createCell(2).setCellValue(tokenDetail.getBranch());
            aRow.createCell(3).setCellValue(tokenDetail.getEnrolmenttype());
            aRow.createCell(4).setCellValue(tokenDetail.getName());
            aRow.createCell(5).setCellValue(tokenDetail.getContactno());
            aRow.createCell(6).setCellValue(tokenDetail.getEmailid());
            aRow.createCell(7).setCellValue(tokenDetail.getAadhaarno());
            aRow.createCell(8).setCellValue(tokenDetail.getFulltokenno());
            aRow.createCell(9).setCellValue(tokenDetail.getTokengenerationdate());
            aRow.createCell(10).setCellValue(tokenDetail.getTokentime());
            aRow.createCell(11).setCellValue(tokenDetail.getFlag());
            
        }
		
	}

	
	
}
