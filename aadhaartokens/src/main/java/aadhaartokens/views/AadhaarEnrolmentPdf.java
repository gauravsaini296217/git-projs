package aadhaartokens.views;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import aadhaartokens.model.EnrolmentDetails;

public class AadhaarEnrolmentPdf extends AbstractEnrolmentPdfView{

	private final String USER_AGENT = "Mozilla/5.0";
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DateFormat dateFormat=new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
		
		DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
		EnrolmentDetails enrolmentDetails = (EnrolmentDetails) model.get("enrolmentDetails");
		
		
		
		Font header1 = new Font(Font.FontFamily.HELVETICA, 6,Font.NORMAL);
		Font header2 = new Font(Font.FontFamily.HELVETICA, 14,Font.BOLD);
		Font header3 = new Font(Font.FontFamily.UNDEFINED, 9,Font.BOLDITALIC, BaseColor.RED);
		Font header4 = new Font(Font.FontFamily.UNDEFINED, 9,Font.BOLD, BaseColor.RED);
		Font header5 = new Font(Font.FontFamily.UNDEFINED, 9,Font.BOLD, new BaseColor(0, 0, 102));
		Font aadhaar = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
		Font header6 = new Font(Font.FontFamily.UNDEFINED, 10,Font.BOLDITALIC);
		Font numbers = new Font(Font.FontFamily.UNDEFINED, 10,Font.BOLD);
		Font text = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.NORMAL);
		Font info = new Font(Font.FontFamily.TIMES_ROMAN, 11,Font.NORMAL);
		Font values = new Font(Font.FontFamily.TIMES_ROMAN, 11,Font.BOLD);
		Font child = new Font(Font.FontFamily.TIMES_ROMAN, 6,Font.NORMAL);
		Font footer1 = new Font(Font.FontFamily.UNDEFINED, 9,Font.BOLD);
		Font footer2 = new Font(Font.FontFamily.UNDEFINED, 10,Font.NORMAL);
		Font tokenno = new Font(Font.FontFamily.HELVETICA, 14,Font.BOLD);
		Font tokenno1 = new Font(Font.FontFamily.HELVETICA, 10,Font.BOLD);
		
		document.open();
		
        Resource resource = new ClassPathResource("images/Ashok3.PNG");
        InputStream resourceInputStream = resource.getInputStream();
        BufferedImage bufferedImage = ImageIO.read(resourceInputStream);
        Image img = Image.getInstance(writer, bufferedImage, 1);
        img.setAbsolutePosition(260f, 800f);
        document.add(img);
        
        resource = new ClassPathResource("images/Aadhaar3.PNG");
        resourceInputStream = resource.getInputStream();
        bufferedImage = ImageIO.read(resourceInputStream);
        img = Image.getInstance(writer, bufferedImage, 1);
        img.setAbsolutePosition(496f, 775f);
        document.add(img);
        
        PdfContentByte canvas=writer.getDirectContent();
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(enrolmentDetails.getTokenid(), tokenno),350, 810,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Under Section 3 of THE AADHAAR (TARGETED DELIVERY OF FINANCIAL AND OTHER SUBSIDIES, BENEFITS AND SERVICES) ACT, 2016 (Aadhaar Act)", header1),70, 792,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("AADHAAR ENROLMENT / CORRECTION FORM", header2),120, 774,0);        
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Aadhaar Enrolment is free and voluntary. Correction within 96 hours of enrolment is also free. No charges are applicable for Form", header3),25, 760,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("and Aadhaar Enrolment. In case of Correction provide your EID, Name and only that field which needs Correction.", header4),25, 750,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("In case of Correction provide your EID No here: " , header5),63, 737,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(enrolmentDetails.getAadhaarno() , aadhaar),275, 737,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Please follow the instructions overleaf while filling up the form. Use capital letters only." , header6),90, 725,0);
        PdfPTable table = new PdfPTable(4);
        table.setTotalWidth(562);
        table.setWidths(new float[] {0.2f,2,0.2f,2});
        PdfPCell cell=new PdfPCell();
        
        Phrase phrase=new Phrase();
        
        
        cell = new PdfPCell(new Phrase("1",numbers));
        cell.setFixedHeight(20f);
        table.addCell(cell);
        phrase=new Phrase();
        phrase.add(new Chunk("Pre-Enrolment ID:",text));
        phrase.add(new Chunk(enrolmentDetails.getTokenid(),values));
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("2",numbers));
        cell.setFixedHeight(20f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("NPR Receipt/TIN Number:",text));
        cell.setFixedHeight(20f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("3",numbers));
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Full Name : ",text));
        phrase.add(new Chunk(enrolmentDetails.getName().toUpperCase(),values));
        
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("4",numbers));
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Gender : ",text));
        phrase.add(new Chunk(enrolmentDetails.getGender(),values));
        
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("5",numbers));
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        String dobstatus="Declared";
        
        if(enrolmentDetails.getDob()!=null && enrolmentDetails.getDobp()!=null && !enrolmentDetails.getDobp().equalsIgnoreCase(""))
        {
        	dobstatus="Verified";
        }
        
        phrase=new Phrase();
        phrase.add(new Chunk("Age : ",text));
        
        phrase.add(new Chunk(String.valueOf(enrolmentDetails.getAge()),values));
        
        phrase.add(new Chunk(" Yrs Date of Birth : ",text));
        if(enrolmentDetails.getDob()==null)
        {
        	phrase.add(new Chunk(""+" "+dobstatus,values));	
        }
        else{
        phrase.add(new Chunk(format.format(enrolmentDetails.getDob())+" "+dobstatus,values));
        }
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("6",numbers));
        cell.setFixedHeight(120f);
        cell.setRowspan(6);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Address : ",text));
        phrase.add(new Chunk(enrolmentDetails.getCo(),values));
        
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk(enrolmentDetails.getGname().toUpperCase(),values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        cell.setColspan(2);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("House No/Bldg./Apt. : ",text));
        phrase.add(new Chunk(enrolmentDetails.getHno().toUpperCase(),values));
        
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Street/Road/Lane : ",text));
        phrase.add(new Chunk(enrolmentDetails.getStreetno().toUpperCase(),values));
        
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        cell.setColspan(2);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Landmark : ",text));
        phrase.add(new Chunk(enrolmentDetails.getLmark().toUpperCase(),values));
        
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Area/locality/sector : ",text));
        phrase.add(new Chunk(enrolmentDetails.getArea().toUpperCase(),values));
        
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        cell.setColspan(2);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Village/Town/City : ",text));
        phrase.add(new Chunk(enrolmentDetails.getVill(),values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Post Office : ",text));
        phrase.add(new Chunk(enrolmentDetails.getPost(),values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        cell.setColspan(2);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("District : ",text));
        phrase.add(new Chunk(enrolmentDetails.getDist(),values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Sub-District : ",text));
        phrase.add(new Chunk(enrolmentDetails.getPost(),values));
        phrase.add(new Chunk(" State : ",text));
        phrase.add(new Chunk(enrolmentDetails.getState(),values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        cell.setColspan(2);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Email : ",text));
        phrase.add(new Chunk(enrolmentDetails.getEmail(),values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Mobile No : ",text));
        phrase.add(new Chunk(enrolmentDetails.getMobile(),values));
        phrase.add(new Chunk(" PIN CODE : ",text));
        phrase.add(new Chunk(String.valueOf(enrolmentDetails.getPin()),values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        cell.setColspan(2);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("7",numbers));
        cell.setFixedHeight(60f);
        cell.setRowspan(3);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Details of : ",text));
        phrase.add(new Chunk(enrolmentDetails.getDguardian(),values));
        phrase.add(Chunk.NEWLINE);
        phrase.add(new Chunk("For children below 5 years Father/Mother/Guardian's details are mandatory. Adults can opt to not specify this information, if they cannot/do not want to disclose",child));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(25f);
        cell.setColspan(3);
        table.addCell(cell);
        
        
        phrase=new Phrase();
        phrase.add(new Chunk("Name : ",text));
        phrase.add(new Chunk(enrolmentDetails.getDgname().toUpperCase(),values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        cell.setColspan(3);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("EID/Aadhaar No.: ",text));
        phrase.add(new Chunk(enrolmentDetails.getDgaadhaar(),values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        cell.setColspan(3);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("Verification Type : ",values));
        phrase.add(new Chunk(enrolmentDetails.getVertype(),values));
        phrase.add(Chunk.NEWLINE);
        phrase.add(new Chunk("Select only one of the above. Select Introducer or Head of Family only if you do not possess any documentary prf of identity",info));
        phrase.add(Chunk.NEWLINE);
        phrase.add(new Chunk(" and/or address. Introducer and Head of Family details are not required in case of Document based Verification.",info));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(40f);
        cell.setColspan(4);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("8",numbers));
        cell.setFixedHeight(20f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk(" For Document Based ",values));
        phrase.add(new Chunk("(Write Names of the documents produced. Refer overleaf of this form for list of valid documents)",text));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(20f);
        cell.setColspan(3);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("a. POI-" + enrolmentDetails.getPoi() ,text));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(25f);
        cell.setColspan(2);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("b. POA-" + enrolmentDetails.getPoa() ,text));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(25f);
        cell.setColspan(2);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("c. DOB-" + enrolmentDetails.getDobp() ,text));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(25f);
        cell.setColspan(2);
        table.addCell(cell);
        
        
        phrase=new Phrase();
        phrase.add(new Chunk("d. POR-" + enrolmentDetails.getPor() ,text));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(25f);
        cell.setColspan(2);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("9",numbers));
        cell.setFixedHeight(30f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk(" For Introducer Based-" ,values));
        phrase.add(new Chunk(Chunk.NEWLINE));
        phrase.add(new Chunk(" Introducer's Aadhaar No.:" ,text));
        phrase.add(new Chunk("",values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(30f);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk(" For HOF Based - "+enrolmentDetails.getHof() ,values));
        phrase.add(new Chunk(Chunk.NEWLINE));
        phrase.add(new Chunk(" HOF's Aadhaar No.:" ,text));
        phrase.add(new Chunk(enrolmentDetails.getHofaadhaar(),values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(30f);
        cell.setColspan(2);
        table.addCell(cell);
        
        phrase=new Phrase();
        phrase.add(new Chunk("I hereby confirm the identity and address of ",info));
        if(enrolmentDetails.getVertype().equalsIgnoreCase("HeadofFamily"))
        {
        phrase.add(new Chunk(enrolmentDetails.getName().toUpperCase(),values));
        }
        else{
        phrase.add(new Chunk(enrolmentDetails.getName().toUpperCase(),values));	
        }
        phrase.add(new Chunk(" as being true, correct and accurate.",info));
        phrase.add(new Chunk(Chunk.NEWLINE));
        phrase.add(new Chunk(Chunk.NEWLINE));
        phrase.add(new Chunk("Introducer/HOF's Name : " ,info));
        if(!enrolmentDetails.getHofname().equalsIgnoreCase(""))
        {
        	phrase.add(new Chunk(enrolmentDetails.getHofname().toUpperCase(),values));	
        }
        else{
        phrase.add(new Chunk("",values));
        }
        phrase.add(new Chunk(Chunk.NEWLINE));
        phrase.add(new Chunk(Chunk.NEWLINE));
        phrase.add(new Chunk("Signature of Introducer/HOF:",values));
        
        cell = new PdfPCell(phrase);
        cell.setFixedHeight(65f);
        cell.setColspan(4);
        table.addCell(cell);
        
        table.writeSelectedRows(0,26, 20,715, canvas);
        
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Disclosure under section 3(2) of THE AADHAAR (TARGETED DELIVERY OF FINANCIAL AND OTHER SUBSIDIES, BENEFITS AND", footer1),25, 230,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("SERVICES) ACT, 2016", footer1),25, 215,0);
        
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("I confirm that I have been residing in India for at least 182 days in the preceding 12 months & information (including biometrics)", footer2),25, 185,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("provided by me to the UIDAI is my own and is true, correct and accurate. I am aware that my information including biometrics", footer2),25, 173,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("will be used for generation of Aadhaar and authentication. I understand that my identity information (except core biometric)", footer2),25, 161,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("may be provided to an agency only with my consent during authentication or as per the provisions of the Aadhaar Act. I have a", footer2),25, 149,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("right to access my identity information (except core biometrics) following the procedure laid down by UIDAI.", footer2),25, 137,0);
        
        
        
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Verifier�s Stamp and Signature:", info),25, 107,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("(Verifier must put his/her Name, if stamp is not available)", child),25, 95,0);
        
        
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Applicant�s signature/Thumbprint", values),400, 95,0);
        
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("--------------------------------------------------------------------------------------------------------------------------------------------------------------------", text),25, 65,0);
        
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("To be filled by the Enrolment Agency only :", child),25, 50,0);
        
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Date & time of Enrolment: ----------------------------------------------------", child),275, 50,0);
        
        PdfPTable table1 = new PdfPTable(1);
        table1.setTotalWidth(540f);
        
        // the cell object
        PdfPCell cell1;
        
        cell1 = new PdfPCell(new Phrase("PEC : "+enrolmentDetails.getPecname(), tokenno1));
        cell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table1.addCell(cell1);
                
        cell1 = new PdfPCell(new Phrase("Appointment Date & Time : "+enrolmentDetails.getRdate().substring(8, 10)+"/"+enrolmentDetails.getRdate().substring(5, 7)+"/"+enrolmentDetails.getRdate().substring(0, 4) +"  "+enrolmentDetails.getTime(), tokenno1));
        cell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table1.addCell(cell1);
        
        table1.writeSelectedRows(0, -1, 20f, 45f, canvas);
        
        
        /*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC :"+enrolmentDetails.getPecname(), tokenno1),25, 30,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Appointment Date & Time :" + enrolmentDetails.getRdate().substring(8, 10)+"/"+enrolmentDetails.getRdate().substring(5, 7)+"/"+enrolmentDetails.getRdate().substring(0, 4) +"  "+enrolmentDetails.getTime(), tokenno),25, 10,0);
        */
        document.close();
		
        sendToken(enrolmentDetails);
        if(!enrolmentDetails.getEmail().equalsIgnoreCase(""))
        {
        	mailService(enrolmentDetails);	
        }
        
	}
	
private void sendToken(EnrolmentDetails enrolmentDetails) throws Exception {

	    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");	
	
		String nu="";
		
		if(enrolmentDetails.getEnroltype().equalsIgnoreCase("NEW"))
		{
			
			nu="Enrolment";
		}
		else{
			
			nu="Updation";
		}
	
		String msg="Dear "+enrolmentDetails.getName()+", Your appointment for Aadhaar "+nu+" is confirmed on "+enrolmentDetails.getRdate().substring(8, 10)+"/"+enrolmentDetails.getRdate().substring(5, 7)+"/"+enrolmentDetails.getRdate().substring(0, 4)+" at "+enrolmentDetails.getPecname()+" between "+enrolmentDetails.getTime()+". Your appointment no is "+enrolmentDetails.getTokenid()+". Please report 15 mins before and carry your Original Proofs towards Identity(POI), Address(POA) and Date of Birth(DOB) with one xerox copy of each." + " Once your Aadhaar number is generated, you can avail Aadhaar PVC card across the counter at our office.";
		//	String msg="Dear "+issuedTokenDetails.getName()+", Your appointment is fixed for Aadhaar "+nu+" at "+issuedTokenDetails.getPeccenter()+" on "+issuedTokenDetails.getDate()+" between "+issuedTokenDetails.getTime()+". Your token no is "+issuedTokenDetails.getTokenno()+". Please report 15 mins before and carry your Original POI, POA and DOB proofs with one xerox copy of each." + " Once your Aadhaar number is generated, you can as well avail Aadhaar PVC card across the counter at our office.";
			
			if(enrolmentDetails.getStatus().equalsIgnoreCase("Token Already Generated"))
	        {
	        	
	          msg=msg+"Your Previous Appointment has been cancelled.";
	        	
	        }
		
	//	String msg="Dear "+enrolmentDetails.getName()+", Your appointment is fixed for Aadhaar "+nu+" at "+enrolmentDetails.getPecname()+" on "+enrolmentDetails.getRdate()+" between "+enrolmentDetails.getTime()+". Your token no is "+enrolmentDetails.getTokenid()+". Please report 15 mins before and carry your Original POI, POA and DOB proofs with one xerox copy of each." + " Once your Aadhaar number is generated, you can as well avail Aadhaar PVC card across the counter at our office.";
		
//		System.out.println(msg);
		
	//	URI uri = new URI("http://203.129.203.243/blank/sms/user/urlsms.php?username=kdmspromo&pass=123456&senderid=KDMSLT&dest_mobileno="+issuedTokenDetails.getMobile()+"&message="+msg+"&response=Y");
		
		URI uri = new URI("http","203.129.203.243","/blank/sms/user/urlsms.php","username=kdmspromo&pass=123456&senderid=KDMSLT&dest_mobileno="+enrolmentDetails.getMobile()+"&message="+msg+"&response=Y",null);

//		System.out.println(uri.toASCIIString());
		
		URL obj =new URL(uri.toASCIIString());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'GET' request to URL : " + obj);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	
	public void mailService(EnrolmentDetails enrolmentDetails)
	{
		try{
		
			SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
			
			Properties props = new Properties();
		    props.put("mail.smtp.port", "25");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.ssl.trust", "srv-mail-ch5.karvy.com");    
		    
		    Session session = Session.getInstance(props,new javax.mail.Authenticator() {
						protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
							return new javax.mail.PasswordAuthentication("karvyaadhaartoken@karvy.com","Karvy789&");
						}
					});
		    
		    StringBuilder email=new StringBuilder();
		    email.append("<html><head><style>th,td{padding: 5px;font-size:12px;} table, th, td {    border: 1px solid black;    border-collapse: collapse;} th,td{ text-align:center; } caption{ text-align:left;font-size:15px;font-style: italic; }</style></head><body>");
		                 
		            email.append("<p>Dear "+enrolmentDetails.getName()+", </p>");
		            email.append("<br>");
		            String nu="";
		    		
		    		if(enrolmentDetails.getEnroltype().equalsIgnoreCase("NEW"))
		    		{
		    			
		    			nu="Enrolment";
		    		}
		    		else{
		    			
		    			nu="Updation";
		    		}
		    	
		    		
		    		
		    		
		    		/*String msg1="Your appointment is fixed for Aadhaar "+nu+" at "+enrolmentDetails.getPecname()+" on "+enrolmentDetails.getRdate()+" between "+enrolmentDetails.getTime()+". Your token no is "+enrolmentDetails.getTokenid()+". Please report 15 mins before and carry your Original POI, POA and DOB proofs with one xerox copy of each." + " Once your Aadhaar number is generated, you can as well avail Aadhaar PVC card across the counter at our office.";
		        
		    		email.append("<p> "+msg1+"</p>");
		            email.append("<br>");
		        */    
		            String msg1="Your appointment for Aadhaar "+nu+" is confirmed on "+enrolmentDetails.getRdate().substring(8, 10)+"/"+enrolmentDetails.getRdate().substring(5, 7)+"/"+enrolmentDetails.getRdate().substring(0, 4)+" at "+enrolmentDetails.getPecname()+" between "+enrolmentDetails.getTime()+". Your appointment no is "+enrolmentDetails.getTokenid()+". Please report 15 mins before and carry your Original Proofs towards Identity(POI), Address(POA) and Date of Birth(DOB) with one xerox copy of each." + " Once your Aadhaar number is generated, you can avail Aadhaar PVC card across the counter at our office.";
			        
		    		email.append("<p> "+msg1+"</p>");
		            email.append("<br>");
		            if(enrolmentDetails.getStatus().equalsIgnoreCase("Token Already Generated"))
		            {
		            	
		            	email.append("<p>Your Previous Appointment has been cancelled.</p>");
		            	
		            }
		            email.append("<br>");
		            email.append("Important Points:-");
		            email.append("<ul>");
		            email.append("<li>Appointment under this token is valid only for one enrolment. Please obtain separate appointment for each resident enrolment.</li>");
		            email.append("<li>This token is non-transferable and valid only for the person whose name is mentioned.</li>");
		            email.append("<li>The appointment is valid only till the date / time mentioned on the token. Residents are requested to be present at enrolment centre 15 minutes before the scheduled time</li>");
		            email.append("<li>Residents should carry photocopies of documents supporting proofs for identity, address and date of birth and should produce the originals for verification at the time of enrolment</li></ul>");
		            email.append("<li>For any clarifications / queries on enrolment, please contact 040-66282840 between 9:30 AM and 6:00 PM on week days</li>");
		            
		            
		            
		            
		        email.append("<p>Note:- Please do not reply, It is system generated mail.</p>");
		        email.append("<br><br><br><br>");
		        email.append("<p>Regards,</p>");
		        email.append("<p>Karvy Online Token Service<br>");
		        email.append("Karvy Data Management Services Ltd<br>");
		         
		        MimeMessage msg = new MimeMessage(session);
		        msg.setSubject("Aadhaar "+nu+" Token");
		        msg.setFrom(new InternetAddress("karvyaadhaartoken@karvy.com"));
		        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(enrolmentDetails.getEmail()));
		        
		        Multipart multipart = new MimeMultipart();
		        BodyPart htmlBodyPart = new MimeBodyPart(); 
		        htmlBodyPart.setContent(email.toString() , "text/html"); 
		        multipart.addBodyPart(htmlBodyPart);  

		        msg.setContent(multipart);
		         
		        Transport transport = session.getTransport("smtp");
		        transport.connect("srv-mail-ch5.karvy.com", 25,"karvyaadhaartoken@karvy.com","Yctguh%88");
		        transport.sendMessage(msg, msg.getAllRecipients());
		        transport.close();
		        
		        System.out.println("Mail Sent");
		        
		}catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	}
}
