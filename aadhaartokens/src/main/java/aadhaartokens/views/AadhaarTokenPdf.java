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
import com.itextpdf.text.pdf.PdfWriter;

import aadhaartokens.model.IssuedTokenDetails;


public class AadhaarTokenPdf extends AbstractITextPdfView{

	
	private final String USER_AGENT = "Mozilla/5.0";
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DateFormat dateFormat=new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
		
		IssuedTokenDetails issuedTokenDetails = (IssuedTokenDetails) model.get("issuedTokenDetails");
		
		
		
		Font tokenFont = new Font(Font.FontFamily.COURIER, 22,Font.NORMAL);
		Font otherFont = new Font(Font.FontFamily.COURIER, 13,Font.NORMAL);
		Font header = new Font(Font.FontFamily.COURIER, 15,Font.BOLD, BaseColor.BLUE);
		Font gendate = new Font(Font.FontFamily.COURIER, 8,Font.BOLD);
		Font content = new Font(Font.FontFamily.COURIER, 7,Font.BOLD, BaseColor.RED);
		Font copyright = new Font(Font.FontFamily.COURIER, 8,Font.BOLD, BaseColor.BLUE);
		
		document.open();
		
		PdfContentByte canvas1 = writer.getDirectContentUnder();
		Resource resource = new ClassPathResource("img/backgrounds/background.jpg");
		InputStream resourceInputStream = resource.getInputStream();
		BufferedImage bufferedImage = ImageIO.read(resourceInputStream);
        Image img = Image.getInstance(writer, bufferedImage, 1);
		img.scaleAbsolute(350,450);
        img.setAbsolutePosition(0, 0);
        canvas1.addImage(img);
		
		resource = new ClassPathResource("images/aadhaarlogo.png");
		resourceInputStream = resource.getInputStream();
		bufferedImage = ImageIO.read(resourceInputStream);
        img = Image.getInstance(writer, bufferedImage, 1);
        img.setAbsolutePosition(10f, 390f);
        document.add(img);
        
        resource = new ClassPathResource("images/kdmslogo.png");
		resourceInputStream = resource.getInputStream();
		bufferedImage = ImageIO.read(resourceInputStream);
        img = Image.getInstance(writer, bufferedImage, 1);
        img.setAbsolutePosition(250f, 395f);
        
        document.add(img);
        PdfContentByte canvas=writer.getDirectContent();
        ColumnText.showTextAligned(canvas,Element.ALIGN_CENTER, new Phrase("Enrolment Token Details", header ),175, 370,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("PEC:", otherFont ),20, 325,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getPeccenter(), tokenFont ),120, 325,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Enrol Type:", otherFont ),20, 295,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getEnrolmenttype(), tokenFont ),120, 295,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Token No:", otherFont ),20, 265,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getTokenno(), tokenFont ),120, 265,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Date:", otherFont ),20, 235,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getDate(), tokenFont ),120, 235,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Time:", otherFont ),20, 205,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getTime(), tokenFont ),120, 205,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Name:", otherFont ),20, 175,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getName(), tokenFont ),120, 175,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Mobile:", otherFont ),20, 145,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getMobile(), tokenFont ),120, 145,0);
        if(issuedTokenDetails.getAadhaar().equalsIgnoreCase(""))
        {
        	
        }else{
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Aadhaar:", otherFont ),20, 115,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase(issuedTokenDetails.getAadhaar(), tokenFont ),120, 115,0);
        }
        
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Token Generation Date : "+dateFormat.format(new Date()), gendate ),140, 50,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("Please bring filled Enrolment form along with Org POI , POA & DOB Proofs.", content ),10, 40,0);
        ColumnText.showTextAligned(canvas,Element.ALIGN_LEFT, new Phrase("One Xerox copy of all the Original documents is required.", content ),10, 25,0);
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
	
		
		String msg="Dear "+issuedTokenDetails.getName()+", Your appointment is fixed for Aadhaar "+nu+" at "+issuedTokenDetails.getPeccenter()+" on "+issuedTokenDetails.getDate()+" between "+issuedTokenDetails.getTime()+". Your token no is "+issuedTokenDetails.getTokenno()+". Please report 15 mins before and carry your Original POI, POA and DOB proofs with one xerox copy of each." + " Once your Aadhaar number is generated, you can as well avail Aadhaar PVC card across the counter at our office.";
		
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
		    props.put("mail.smtp.ssl.trust", "srv-mail-ch1.karvy.com");    
		    
		    Session session = Session.getInstance(props,new javax.mail.Authenticator() {
						protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
							return new javax.mail.PasswordAuthentication("karvyaadhaartoken@karvy.com","Karvy789&");
						}
					});
		    
		    StringBuilder email=new StringBuilder();
		    email.append("<html><head><style>th,td{padding: 5px;font-size:12px;} table, th, td {    border: 1px solid black;    border-collapse: collapse;} th,td{ text-align:center; } caption{ text-align:left;font-size:15px;font-style: italic; }</style></head><body>");
		                 
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
		    	
		    		
		    		String msg1="Your appointment is fixed for Aadhaar "+nu+" at "+issuedTokenDetails.getPeccenter()+" on "+issuedTokenDetails.getDate()+" between "+issuedTokenDetails.getTime()+". Your token no is "+issuedTokenDetails.getTokenno()+". Please report 15 mins before and carry your Original POI, POA and DOB proofs with one xerox copy of each." + " Once your Aadhaar number is generated, you can as well avail Aadhaar PVC card across the counter at our office.";
		        
		    		email.append("<p> "+msg1+"</p>");
		            email.append("<br>");
		            email.append("<p>Note:- Please do not reply, It is system generated mail.</p>");
		        email.append("<br><br><br><br>");
		        email.append("<p>Regards,</p>");
		        email.append("<p>Karvy Online Token Service<br>");
		        email.append("Karvy Data Management Services Ltd<br>");
		         
		        MimeMessage msg = new MimeMessage(session);
		        msg.setSubject("Aadhaar "+nu+" Token");
		        msg.setFrom(new InternetAddress("karvyaadhaartoken@karvy.com"));
		        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(issuedTokenDetails.getEmail()));
		        
		        Multipart multipart = new MimeMultipart();
		        BodyPart htmlBodyPart = new MimeBodyPart(); 
		        htmlBodyPart.setContent(email.toString() , "text/html"); 
		        multipart.addBodyPart(htmlBodyPart);  

		        msg.setContent(multipart);
		         
		        Transport transport = session.getTransport("smtp");
		        transport.connect("srv-mail-ch1.karvy.com", 25,"karvyaadhaartoken@karvy.com","Karvy789&");
		        transport.sendMessage(msg, msg.getAllRecipients());
		        transport.close();
		        
		        System.out.println("Mail Sent");
		        
		}catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	}
	
}
