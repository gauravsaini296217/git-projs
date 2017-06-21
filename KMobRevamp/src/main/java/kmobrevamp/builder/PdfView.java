package kmobrevamp.builder;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import kmobrevamp.model.Complaint;
import kmobrevamp.view.AbstractItextPdfView;

public class PdfView extends AbstractItextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        Complaint complaint = (Complaint) model.get("complaint");
        Font font = FontFactory.getFont("Arial", 7,Font.BOLD);
        Font font1 = FontFactory.getFont("Arial", 7,Font.BOLD);
        
        doc.add(new Paragraph("Customer's Copy",font));
        
        font = FontFactory.getFont("Arial", 8,Font.BOLD);
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100.0f);
    //    table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
        table.setSpacingBefore(3);
        
        
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPadding(8);
        
        cell.setPhrase(new Phrase("Complaint Receipt", font));
        table.addCell(cell);
        
        doc.add(table);
        
        table = new PdfPTable(2);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {2.0f, 3.0f});
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);
        font = FontFactory.getFont("Arial", 7);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Serialno", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getSno(), font1));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Model", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getModel(), font1));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Customer Name", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getCname(), font1));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Customer Address", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getCaddress(), font1));
        table.addCell(cell);
        
        doc.add(table);
        
        doc.add(new Paragraph("Received Items:",font1));
        
        table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {2.5f, 1.5f,2.5f, 1.5f});
        table.setSpacingBefore(3);
        font = FontFactory.getFont("Arial", 7);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Hand set", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getHandset()? "R" : "NR", font1));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Battery", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getBattery()? "R" : "NR", font1));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Charger", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getCharger()? "R" : "NR", font1));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Hands free", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getHandsfree()? "R" : "NR", font1));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Sound Mate", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getSoundmate()? "R" : "NR", font1));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Memory card", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getMemorycard()? "R" : "NR", font1));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Data Cable", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getDatacable()? "R" : "NR", font1));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("", font));
        table.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("", font));
        table.addCell(cell);
        
        doc.add(table);
        
        PdfPTable mainTable = new PdfPTable(2);
        mainTable.setWidthPercentage(100.0f);
        mainTable.setWidths(new float[] {2.0f,3.0f});
        mainTable.setSpacingBefore(10);
        
        // First table
        PdfPCell firstTableCell = new PdfPCell();
        /*firstTableCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        firstTableCell.setPadding(5);
        firstTableCell.setPhrase(new Phrase("Estimate Details", font1));
        mainTable.addCell(firstTableCell);*/
        
        PdfPCell secondTableCell = new PdfPCell();
        /*secondTableCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        secondTableCell.setPadding(5);
        secondTableCell.setPhrase(new Phrase("Details of Stand by model issued:", font1));
        mainTable.addCell(secondTableCell);*/
        
        firstTableCell = new PdfPCell();
        firstTableCell.setBorder(PdfPCell.NO_BORDER);
        PdfPTable firstTable = new PdfPTable(2);
        firstTable.setWidthPercentage(100.0f);
        firstTable.setWidths(new float[] {0.5f,0.5f});
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Estimate Details", font1));
        firstTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("", font1));
        firstTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Type", font1));
        firstTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Amount", font1));
        firstTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Labour", font));
        firstTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(String.valueOf(complaint.getLabour()), font1));
        firstTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Part"+(complaint.getPartname().equalsIgnoreCase("")?"":"-"+complaint.getPartname()), font1));
        firstTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(String.valueOf(complaint.getPartprice()), font1));
        firstTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Approved/Not Approved", font));
        firstTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getApproved()?"Approved":"Not Approved", font1));
        firstTable.addCell(cell);
        
        firstTableCell.addElement(firstTable);
        mainTable.addCell(firstTableCell);
        
        
        secondTableCell = new PdfPCell();
        secondTableCell.setBorder(PdfPCell.NO_BORDER);
        PdfPTable secondTable = new PdfPTable(2);
        secondTable.setWidthPercentage(100.0f);
        secondTable.setWidths(new float[] {0.4f,0.6f});
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Details of Stand by model issued:", font1));
        secondTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("", font1));
        secondTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Stand By Model", font));
        secondTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getStandbymodel(), font1));
        secondTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Serial No", font));
        secondTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getSerialno(), font1));
        secondTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Ref./IMEI", font));
        secondTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getReforimei(), font1));
        secondTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Date", font));
        secondTable.addCell(cell);
        
        cell = new PdfPCell();
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPadding(5);
        cell.setPhrase(new Phrase(complaint.getStandbydate()!=null?new SimpleDateFormat("dd-MM-yyyy").format(complaint.getStandbydate()):"", font1));
        secondTable.addCell(cell);
        
        secondTableCell.addElement(secondTable);
        mainTable.addCell(secondTableCell);
        
        doc.add(mainTable);
		
	}

	
	
}
