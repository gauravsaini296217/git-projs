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
import java.util.Date;
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

import aadhaartokens.model.IssuedTokenDetails;


public class AadhaarTokenPdf extends AbstractITextPdfView{

	
	private final String USER_AGENT = "Mozilla/5.0";
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DateFormat dateFormat=new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
		
		IssuedTokenDetails issuedTokenDetails = (IssuedTokenDetails) model.get("issuedTokenDetails");
		
		System.out.println(issuedTokenDetails.toString());
		
		Font tokenFont = new Font(Font.FontFamily.COURIER, 10,Font.NORMAL);
		Font otherFont = new Font(Font.FontFamily.COURIER, 10,Font.BOLD);
		Font header = new Font(Font.FontFamily.COURIER, 15,Font.BOLD, BaseColor.BLACK);
		Font gendate = new Font(Font.FontFamily.COURIER, 8,Font.BOLD);
		Font content = new Font(Font.FontFamily.COURIER, 7,Font.BOLD, BaseColor.RED);
		Font content1 = new Font(Font.FontFamily.COURIER, 7,Font.BOLD, BaseColor.BLACK);
		Font copyright = new Font(Font.FontFamily.COURIER, 8,Font.BOLD, BaseColor.BLUE);
		
		document.open();
		
		/*PdfContentByte canvas1 = writer.getDirectContentUnder();
		Resource resource = new ClassPathResource("img/backgrounds/background.jpg");
		InputStream resourceInputStream = resource.getInputStream();
		BufferedImage bufferedImage = ImageIO.read(resourceInputStream);
        Image img = Image.getInstance(writer, bufferedImage, 1);
		img.scaleAbsolute(350,450);
        img.setAbsolutePosition(0, 0);
        canvas1.addImage(img);*/
		
		Resource resource = new ClassPathResource("images/aadhaarlogo.png");
		InputStream resourceInputStream = resource.getInputStream();
		BufferedImage bufferedImage = ImageIO.read(resourceInputStream);
        Image img = Image.getInstance(writer, bufferedImage, 1);
        img.setAbsolutePosition(10f, 390f);
        document.add(img);
        
        resource = new ClassPathResource("images/kdmslogo.png");
		resourceInputStream = resource.getInputStream();
		bufferedImage = ImageIO.read(resourceInputStream);
        img = Image.getInstance(writer, bufferedImage, 1);
        img.setAbsolutePosition(400f, 395f);
        
        document.add(img);
        
        PdfPTable table = new PdfPTable(new float[] { 2, 6 });
        table.setTotalWidth(450f);
        
        // the cell object
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("Aadhaar Online Appointment", header));
        cell.setColspan(2);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setMinimumHeight(20f);
        table.addCell(cell);
        
        PdfContentByte canvas=writer.getDirectContent();
        /*ColumnText.showTextAligned(canvas,Element.ALIGN_CENTER, new Phrase("Enrolment Token Details", header ),175, 370,0);*/
        
        cell = new PdfPCell(new Phrase("Appointment No:", otherFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(issuedTokenDetails.getTokenno(), tokenFont));
        table.addCell(cell);
        
        /*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Appointment No:", otherFont ),20, 235,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getTokenno(), tokenFont ),120, 235,0);
        */
        
        cell = new PdfPCell(new Phrase("Name:", otherFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(issuedTokenDetails.getName().toUpperCase(), tokenFont));
        table.addCell(cell);
        
        /*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Name:", otherFont ),20, 145,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getName(), tokenFont ),120, 145,0);
        */
        
        cell = new PdfPCell(new Phrase("Mobile:", otherFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(issuedTokenDetails.getMobile(), tokenFont));
        table.addCell(cell);
        
        /*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Mobile:", otherFont ),20, 115,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getMobile(), tokenFont ),120, 115,0);
        */
        
        if(issuedTokenDetails.getAadhaar().equalsIgnoreCase(""))
        {
        	
        }else{
        
        	cell = new PdfPCell(new Phrase("Aadhaar:", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(issuedTokenDetails.getAadhaar(), tokenFont));
            table.addCell(cell);
            
        	
        /*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Aadhaar:", otherFont ),20, 85,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getAadhaar(), tokenFont ),120, 85,0);
        */
        }
        
        
        cell = new PdfPCell(new Phrase("Date:", otherFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(issuedTokenDetails.getDate().substring(8, 10)+"/"+issuedTokenDetails.getDate().substring(5, 7)+"/"+issuedTokenDetails.getDate().substring(0, 4), tokenFont));
        table.addCell(cell);
        
        
        /*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Date:", otherFont ),20, 205,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getDate().substring(8, 10)+"/"+issuedTokenDetails.getDate().substring(5, 7)+"/"+issuedTokenDetails.getDate().substring(0, 4), tokenFont ),120, 205,0);
        */
        
        cell = new PdfPCell(new Phrase("Time:", otherFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(issuedTokenDetails.getTime(), tokenFont));
        table.addCell(cell);
        
        /*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Time:", otherFont ),20, 175,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getTime(), tokenFont ),120, 175,0);
        */
        
        
        String addlines[]=issuedTokenDetails.getPeccenter().split(",");
        
        
        if(addlines.length==1)
        {
        
        	cell = new PdfPCell(new Phrase("Address of PEC:", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(issuedTokenDetails.getPeccenter(), tokenFont));
            table.addCell(cell);
        	/*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC:"+issuedTokenDetails.getPeccenter(), otherFont ),20, 325,0);	*/
        	
        }
        else if(addlines.length==2)
        {
        	cell = new PdfPCell(new Phrase("Address of PEC:", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[0], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[1], tokenFont));
            table.addCell(cell);
        	/*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC:"+addlines[0], otherFont ),20, 325,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[1], otherFont ),38, 310,0);	
        	*/
        }
        else if(addlines.length==3)
        {
        	cell = new PdfPCell(new Phrase("Address of PEC:", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[0], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[1]+" , "+addlines[2], tokenFont));
            table.addCell(cell);
        	/*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC:"+addlines[0]+" , "+addlines[1], otherFont ),20, 325,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[2], otherFont ),38, 310,0);	
        	*/
        }
        else if(addlines.length==4)
        {
        	cell = new PdfPCell(new Phrase("Address of PEC:", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[0]+" , "+addlines[1], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[2]+" , "+addlines[3], tokenFont));
            table.addCell(cell);
        	/*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC:"+addlines[0]+" , "+addlines[1], otherFont ),20, 325,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[2]+" , "+addlines[3], otherFont ),38, 310,0);	
        	*/
        }
        else if(addlines.length==5)
        {
        	cell = new PdfPCell(new Phrase("Address of PEC:", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[0]+" , "+addlines[1], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[2]+" , "+addlines[3], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[4], tokenFont));
            table.addCell(cell);
        	/*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC:"+addlines[0]+" , "+addlines[1], otherFont ),20, 325,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[2]+" , "+addlines[3], otherFont ),38, 310,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[4], otherFont ),38, 295,0);	
        	*/
        }
        else if(addlines.length==6)
        {
        	cell = new PdfPCell(new Phrase("Address of PEC:", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[0]+" , "+addlines[1], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[2]+" , "+addlines[3], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[4]+" , "+addlines[5], tokenFont));
            table.addCell(cell);
        	/*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC:"+addlines[0]+" , "+addlines[1], otherFont ),20, 325,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[2]+" , "+addlines[3], otherFont ),38, 310,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[4]+" , "+addlines[5], otherFont ),38, 295,0);	
        	*/
        }
        else if(addlines.length==7)
        {
        	cell = new PdfPCell(new Phrase("Address of PEC:", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[0]+" , "+addlines[1], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[2]+" , "+addlines[3], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[4]+" , "+addlines[5], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[6], tokenFont));
            table.addCell(cell);
        	/*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC:"+addlines[0]+" , "+addlines[1], otherFont ),20, 325,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[2]+" , "+addlines[3], otherFont ),38, 310,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[4]+" , "+addlines[5], otherFont ),38, 295,0);
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[6], otherFont ),38, 280,0);
        	*/
        }
        else if(addlines.length==8)
        {
        	cell = new PdfPCell(new Phrase("Address of PEC:", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[0]+" , "+addlines[1], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[2]+" , "+addlines[3], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[4]+" , "+addlines[5], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[6]+" , "+addlines[7], tokenFont));
            table.addCell(cell);
        	/*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC:"+addlines[0]+" , "+addlines[1], otherFont ),20, 325,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[2]+" , "+addlines[3], otherFont ),38, 310,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[4]+" , "+addlines[5], otherFont ),38, 295,0);
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[6]+" , "+addlines[7], otherFont ),38, 280,0);
        	*/
        }
        else if(addlines.length==9)
        {
        	cell = new PdfPCell(new Phrase("Address of PEC:", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[0]+" , "+addlines[1], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[2]+" , "+addlines[3], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[4]+" , "+addlines[5], tokenFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("", otherFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(addlines[6]+" , "+addlines[7]+" , "+addlines[8], tokenFont));
            table.addCell(cell);
        	/*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC:"+addlines[0]+" , "+addlines[1], otherFont ),20, 325,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[2]+" , "+addlines[3], otherFont ),38, 310,0);	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[4]+" , "+addlines[5], otherFont ),38, 295,0);
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(addlines[6]+" , "+addlines[7]+" , "+addlines[8], otherFont ),38, 280,0);
        	*/
        }
       // ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getPeccenter(), tokenFont ),120, 325,0);
        
        cell = new PdfPCell(new Phrase("Enrolment Type:", otherFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(issuedTokenDetails.getEnrolmenttype().toUpperCase(), tokenFont));
        table.addCell(cell);
        /*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Enrol Type:", otherFont ),20, 265,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getEnrolmenttype(), tokenFont ),120, 265,0);
        */
        
        
        table.writeSelectedRows(0, -1, 20f, 370f, canvas);
        /*document.add(table);*/
        
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Important Points:-", gendate ),10, 140,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Appointment under this token is valid only for one enrolment. Please obtain separate appointment for each resident", content1),10, 130,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("enrolment. This token is non-transferable and valid only for the person whose name is mentioned. The appointment is", content1),10, 120,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("valid only till the date / time mentioned on the token. Residents are requested to be present at enrolment centre 15", content1),10, 110,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("minutes before the scheduled time.Residents should carry photocopies of documents supporting proofs for identity,", content1),10, 100,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("address and date of birth and should produce the originals for verification at the time of enrolment.For any clarifi-", content1),10, 90,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("cations / queries on enrolment, please contact 040-66282840 between 9:30 AM and 6:00 PM on week days", content1),10, 80,0);
        
        
        if(issuedTokenDetails.getStatus().equalsIgnoreCase("Token Already Generated"))
        {
        	
        	ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Previous Appointment Cancelled", gendate ),350, 50,0);
        	
        }
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Appointment Generation Date : "+dateFormat.format(new Date()), gendate ),260, 40,0);
        /*ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Please bring filled Enrolment form along with Org POI , POA & DOB Proofs.", content ),10, 40,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("One Xerox copy of all the Original documents is required.", content ),10, 25,0);
        */
     //   ColumnText.showTextAligned(canvas,Element.ALIGN_CENTER, new Phrase("KDMSL � 2016-2017", copyright ),175, 10,0);
        
        
        
        document.close();
		
        sendToken(issuedTokenDetails);
        if(!issuedTokenDetails.getEmail().equalsIgnoreCase(""))
        {
        	mailService(issuedTokenDetails);	
        }
        
        
	}
	
	
	private void sendToken(IssuedTokenDetails issuedTokenDetails) throws Exception {

		
		String nu="";
		
		if(issuedTokenDetails.getEnrolmenttype().equalsIgnoreCase("New"))
		{
			
			nu="Enrolment";
		}
		else{
			
			nu="Updation";
		}
	
		String msg="Dear "+issuedTokenDetails.getName()+", Your appointment for Aadhaar "+nu+" is confirmed on "+issuedTokenDetails.getDate().substring(8,10)+"/"+issuedTokenDetails.getDate().substring(5,7)+"/"+issuedTokenDetails.getDate().substring(0,4)+" at "+issuedTokenDetails.getPeccenter()+" between "+issuedTokenDetails.getTime()+". Your appointment no is "+issuedTokenDetails.getTokenno()+". Please report 15 mins before and carry your Original Proofs towards Identity(POI), Address(POA) and Date of Birth(DOB) with one xerox copy of each." + " Once your Aadhaar number is generated, you can avail Aadhaar PVC card across the counter at our office.";
	//	String msg="Dear "+issuedTokenDetails.getName()+", Your appointment is fixed for Aadhaar "+nu+" at "+issuedTokenDetails.getPeccenter()+" on "+issuedTokenDetails.getDate()+" between "+issuedTokenDetails.getTime()+". Your token no is "+issuedTokenDetails.getTokenno()+". Please report 15 mins before and carry your Original POI, POA and DOB proofs with one xerox copy of each." + " Once your Aadhaar number is generated, you can as well avail Aadhaar PVC card across the counter at our office.";
		
		if(issuedTokenDetails.getStatus().equalsIgnoreCase("Token Already Generated"))
        {
        	
          msg=msg+"Your Previous Appointment has been cancelled.";
        	
        }
		
//		System.out.println(msg);
		
	//	URI uri = new URI("http://203.129.203.243/blank/sms/user/urlsms.php?username=kdmspromo&pass=123456&senderid=KDMSLT&dest_mobileno="+issuedTokenDetails.getMobile()+"&message="+msg+"&response=Y");
		
		URI uri = new URI("http","203.129.203.243","/blank/sms/user/urlsms.php","username=kdmspromo&pass=123456&senderid=KDMSLT&dest_mobileno="+issuedTokenDetails.getMobile()+"&message="+msg+"&response=Y",null);

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

	
	public void mailService(IssuedTokenDetails issuedTokenDetails)
	{
		try{
			
			Properties props = new Properties();
		    props.put("mail.smtp.port", "25");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.ssl.trust", "srv-mail-ch5.karvy.com");    
		    
		    Session session = Session.getInstance(props,new javax.mail.Authenticator() {
						protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
							return new javax.mail.PasswordAuthentication("karvyaadhaartoken@karvy.com","Yctguh%88");
						}
					});
		    
		    StringBuilder email=new StringBuilder();
		    email.append("<html><head><style>th,td{padding: 5px;font-size:12px;} table, th, td {    border: 1px solid black;    border-collapse: collapse;} th,td{ text-align:center; } caption{ text-align:left;font-size:15px;font-style: italic; } p{font-size:12px;font-family: Calibri, Candara, Segoe, 'Segoe UI', Optima, Arial, sans-serif;}</style></head><body>");
		                 
		            email.append("<p>Dear "+issuedTokenDetails.getName()+", </p>");
		            email.append("<br>");
		            String nu="";
		    		
		    		if(issuedTokenDetails.getEnrolmenttype().equalsIgnoreCase("New"))
		    		{
		    			
		    			nu="Enrolment";
		    		}
		    		else{
		    			
		    			nu="Updation";
		    		}
		    	
		    		
		    		String msg1="Your appointment for Aadhaar "+nu+" is confirmed on "+issuedTokenDetails.getDate().substring(8,10)+"/"+issuedTokenDetails.getDate().substring(5,7)+"/"+issuedTokenDetails.getDate().substring(0,4)+" at "+issuedTokenDetails.getPeccenter()+" between "+issuedTokenDetails.getTime()+". Your appointment no is "+issuedTokenDetails.getTokenno()+". Please report 15 mins before and carry your Original Proofs towards Identity(POI), Address(POA) and Date of Birth(DOB) with one xerox copy of each." + " Once your Aadhaar number is generated, you can avail Aadhaar PVC card across the counter at our office.";
		        
		    		email.append("<p> "+msg1+"</p>");
		            email.append("<br>");
		            if(issuedTokenDetails.getStatus().equalsIgnoreCase("Token Already Generated"))
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
		        email.append("<p>Karvy Aadhaar Online Appointment Service<br>");
		        email.append("Karvy Data Management Services Ltd<br>");
		         
		        MimeMessage msg = new MimeMessage(session);
		        msg.setSubject("Aadhaar "+nu+" Appointment");
		        msg.setFrom(new InternetAddress("karvyaadhaartoken@karvy.com"));
		        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(issuedTokenDetails.getEmail()));
		        
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
