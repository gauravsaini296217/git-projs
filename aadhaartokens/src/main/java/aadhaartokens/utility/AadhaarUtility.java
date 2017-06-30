package aadhaartokens.utility;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aadhaartokens.model.BranchDateToken;
import aadhaartokens.model.CheckOTP;
import aadhaartokens.model.IssuedTokenDetails;
import aadhaartokens.model.OTP;
import aadhaartokens.model.RowDates;
import aadhaartokens.model.TokenRequest;

public class AadhaarUtility {
	
	private Connection connection;
	
	public AadhaarUtility()
	{
		try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	connection=DriverManager.getConnection("jdbc:sqlserver://192.168.84.90;user=sa;password=Karvy@123;database=uidnew");
		}catch(Exception e)
		{
			
			System.out.println("Error:"+e);
		}
		
	}
	
	private final String USER_AGENT = "Mozilla/5.0";
    
	public OTP sendOTP(String mobile,String time){
		
    OTP otp=new OTP();
    	
    try{	
    
    	
    	ResultSet rs=null;
		CallableStatement cstmt = connection.prepareCall("{call generateotp(?,?)}",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
		cstmt.setString("ContactNo", mobile);
        cstmt.setString("Time", time);
        boolean results = cstmt.execute();
        int rowsAffected = 0;
 
        System.out.println("aaa");
        
        // Protects against lack of SET NOCOUNT in stored prodedure
        while (results || rowsAffected != -1) {
            if (results) {
                rs = cstmt.getResultSet();
                break;
            } else {
                rowsAffected = cstmt.getUpdateCount();
            }
            results = cstmt.getMoreResults();
        }
        
        while (rs.next()) {
        
            otp.setStatus("Sent");
            otp.setTime(rs.getString("OtpTime"));
            otp.setDate(rs.getString("OtpDate"));
            
            System.out.println(rs.getString("Otp")+"  "+rs.getString("OtpDate")+"  "+rs.getString("OtpTime"));
            	
        	String msg="Your OTP "+rs.getString("Otp")+" is valid till "+rs.getString("OtpTime")+" hrs "+rs.getString("OtpDate")+".This OTP belongs to Karvy's Aadhaar Token Online Service.";
        	
        	
        	
        	URI uri = new URI("http","203.129.203.243","/blank/sms/user/urlsms.php","username=kdmspromo&pass=123456&senderid=KDMSLT&dest_mobileno="+mobile+"&message="+msg+"&response=Y",null);

        	
        	URL obj =new URL(uri.toASCIIString());
        	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        	con.setRequestMethod("GET");

        	con.setRequestProperty("User-Agent", USER_AGENT);

        	int responseCode = con.getResponseCode();

        	System.out.println("Response Code : " + responseCode);

        	BufferedReader in = new BufferedReader(
        	        new InputStreamReader(con.getInputStream()));
        	String inputLine;
        	StringBuffer response = new StringBuffer();

        	while ((inputLine = in.readLine()) != null) {
        		response.append(inputLine);
        	}
        	in.close();
	
        	
         }
    	
        rs.close();
    	
	return otp;
	
    }catch(Exception e)
    {
    	e.printStackTrace();
    	return otp;
    }
}
	
	
	public CheckOTP checkOTP(String mobile,String time,String otp){
		
	    CheckOTP cotp=new CheckOTP();
	    
	    	
	    try{	
	    	
	    	
	    	ResultSet rs=null;
			CallableStatement cstmt = connection.prepareCall("{call checkotp(?,?,?)}",
	                ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY);
			cstmt.setString("ContactNo", mobile);
	        cstmt.setString("Time", time);
	        cstmt.setString("Otp", otp);
	        boolean results = cstmt.execute();
	        int rowsAffected = 0;
	 
	        while (results || rowsAffected != -1) {
	            if (results) {
	                rs = cstmt.getResultSet();
	                break;
	            } else {
	                rowsAffected = cstmt.getUpdateCount();
	            }
	            results = cstmt.getMoreResults();
	        }
	        
	        while (rs.next()) {
	        
	        	cotp.setStatus(rs.getString("Status"));
	        	
	        }
	        
	        rs.close();
	        
			return cotp;
		
	    }catch(Exception e)
	    {
	    	return cotp;
	    }
	}
	
	
	public List<RowDates> getNextFifteenDays(String pecid)
	{
		List<RowDates> masters=new ArrayList<RowDates>();
		try{
    		
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    List<Date> nextFifteenDates=new ArrayList<Date>();
		    
		    Date date=new Date();
		    
		    for(int i=0;i<=14;i++)
		    {	
		    System.out.println("NextDate:"+dateFormat.format(getNextDate(date)));	
		    nextFifteenDates.add(getNextDate(date));
		    date=getNextDate(date);
		    }
			
			BranchDateToken[] branchDateTokens=new BranchDateToken[15];
			
		    int dt=0;
		    
		    
		    for(Date date2:nextFifteenDates)
		    {
		    	
		    		branchDateTokens[dt]=new BranchDateToken();
		    		System.out.println("EXEC GetToken1 '"+pecid+"','"+dateFormat.format(date2)+"'");
			        
		    		ResultSet rs=null;
		    		CallableStatement cstmt = connection.prepareCall("{call GetToken1(?,?)}",
		                    ResultSet.TYPE_SCROLL_INSENSITIVE,
		                    ResultSet.CONCUR_READ_ONLY);
		    		cstmt.setString("PECCode", pecid);
		            cstmt.setString("Date", dateFormat.format(date2));
		            boolean results = cstmt.execute();
		            int rowsAffected = 0;
		     
		            // Protects against lack of SET NOCOUNT in stored prodedure
		            while (results || rowsAffected != -1) {
		                if (results) {
		                    rs = cstmt.getResultSet();
		                    break;
		                } else {
		                    rowsAffected = cstmt.getUpdateCount();
		                }
		                results = cstmt.getMoreResults();
		            }
		            
		            while (rs.next()) {
		            	
		            	branchDateTokens[dt].setId(dt);
		            	branchDateTokens[dt].setPeccode(rs.getString(1));
			        	branchDateTokens[dt].setDate(dateFormat.format(date2));
			        	branchDateTokens[dt].setAvailabletokenno(rs.getInt(3));
			        	branchDateTokens[dt].setMaxtokens(rs.getInt(4));
			        	branchDateTokens[dt].setTimeslot(rs.getString(5));
		            }
		    		
		    	   
			         dt++;
		    	
		    }
		    
		    
		    BranchDateToken[] bdt1=new BranchDateToken[3];
		    
		    bdt1[0]=branchDateTokens[0];
		    bdt1[1]=branchDateTokens[1];
		    bdt1[2]=branchDateTokens[2];
		    
		    RowDates rowDates=new RowDates();
		    rowDates.setBranchDateTokens(bdt1);
		    masters.add(rowDates);
		    
		    BranchDateToken[] bdt2=new BranchDateToken[3];
		    
		    bdt2[0]=branchDateTokens[3];
		    bdt2[1]=branchDateTokens[4];
		    bdt2[2]=branchDateTokens[5];
		 
		    rowDates=new RowDates();
		    rowDates.setBranchDateTokens(bdt2);
		    masters.add(rowDates);
		    
		    
            BranchDateToken[] bdt3=new BranchDateToken[3];
		    
		    bdt3[0]=branchDateTokens[6];
		    bdt3[1]=branchDateTokens[7];
		    bdt3[2]=branchDateTokens[8];
		 
		    rowDates=new RowDates();
		    rowDates.setBranchDateTokens(bdt3);
		    masters.add(rowDates);
		    
            BranchDateToken[] bdt4=new BranchDateToken[3];
		    
		    bdt4[0]=branchDateTokens[9];
		    bdt4[1]=branchDateTokens[10];
		    bdt4[2]=branchDateTokens[11];
		 
		    rowDates=new RowDates();
		    rowDates.setBranchDateTokens(bdt4);
		    masters.add(rowDates);
		    
            BranchDateToken[] bdt5=new BranchDateToken[3];
		    
		    bdt5[0]=branchDateTokens[12];
		    bdt5[1]=branchDateTokens[13];
		    bdt5[2]=branchDateTokens[14];
		 
		    rowDates=new RowDates();
		    rowDates.setBranchDateTokens(bdt5);
		    masters.add(rowDates);
		    
		}catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
		return masters;
	}
	
	public BranchDateToken getDateToken(int id,String pecid,String date)
	{
		BranchDateToken branchDateToken=new BranchDateToken();
		
		try{
			
			
			ResultSet rs=null;
    		CallableStatement cstmt = connection.prepareCall("{call GetToken1(?,?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
    		cstmt.setString("PECCode", pecid);
            cstmt.setString("Date", date);
            boolean results = cstmt.execute();
            int rowsAffected = 0;
     
            // Protects against lack of SET NOCOUNT in stored prodedure
            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }
            
            while (rs.next()) {
            	
            	branchDateToken.setId(id);
            	branchDateToken.setPeccode(rs.getString(1));
	        	branchDateToken.setDate(date);
	        	branchDateToken.setAvailabletokenno(rs.getInt(3));
	        	branchDateToken.setMaxtokens(rs.getInt(4));
	        	branchDateToken.setTimeslot(rs.getString(5));
            }
            
            rs.close();
           
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return branchDateToken;
	}
	
	
	public Date getNextDate(Date date)
    {
    try{    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    int dd=Integer.parseInt(dateFormat.format(date).substring(8, 10));
    int mm=Integer.parseInt(dateFormat.format(date).substring(5, 7));
    int yy=Integer.parseInt(dateFormat.format(date).substring(0, 4));
    
    if(dd==28 || dd==29 )
    {
    
    
    if(mm==2)
    {
    
        dd=1;
        mm=3;
        
    }
    else{
    	
    	dd=dd+1;
    }
    
    }
    else if(dd==30)
    {
    	
    if(mm==4 || mm==6 || mm==9 || mm==11)
    {
    dd=1;
    mm=mm+1;
    }
    else{
    	
    	dd=dd+1;
    }
    
    }
    else if(dd==31)
    {
    	dd=1;
    	
    	if(mm==12)
    	{
    		
    		mm=1;
    		yy=yy+1;
    	}
    	else{
    	mm=mm+1;
    	}
    }
    else
    {
    dd=dd+1;
    }
    
    
    
    Date nextDay = dateFormat.parse(String.valueOf(yy)+"-"+String.valueOf(mm)+"-"+String.valueOf(dd));

    return nextDay;
    }catch(Exception e)
    {
    	e.printStackTrace();
         return null;
    }
    
    }

	public IssuedTokenDetails issueToken(TokenRequest tokenRequest)
	{
		IssuedTokenDetails issuedTokenDetails=new IssuedTokenDetails();
		
		try{
			
			ResultSet rs=null;
    		CallableStatement cstmt = connection.prepareCall("{call InsTokenDetailsNew(?,?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
    		cstmt.setString("State", String.valueOf(tokenRequest.getState()));
    		cstmt.setString("region", String.valueOf(tokenRequest.getDistrict()));
    		cstmt.setString("branch", String.valueOf(tokenRequest.getRpeccode()));
    		cstmt.setString("Type", String.valueOf(tokenRequest.getEnroltype()));
    		cstmt.setString("Name", String.valueOf(tokenRequest.getName()));
    		cstmt.setString("contactno", String.valueOf(tokenRequest.getMobile()));
    		cstmt.setString("email", String.valueOf(tokenRequest.getEmail()));
    		cstmt.setString("aadhaar", String.valueOf(tokenRequest.getAadhaar()));
    		cstmt.setString("Date", String.valueOf(tokenRequest.getRdate()));
    		cstmt.setInt("Otp", Integer.parseInt(tokenRequest.getOtp()));
    		cstmt.setString("OtpTime", String.valueOf(tokenRequest.getTime()));
    		
    		System.out.println("call InsTokenDetailsNew("+String.valueOf(tokenRequest.getState())+","+String.valueOf(tokenRequest.getDistrict())+","+String.valueOf(tokenRequest.getRpeccode())+","+String.valueOf(tokenRequest.getEnroltype())+","+String.valueOf(tokenRequest.getName())+","+String.valueOf(tokenRequest.getMobile())+","+String.valueOf(tokenRequest.getEmail())+","+String.valueOf(tokenRequest.getAadhaar())+","+String.valueOf(tokenRequest.getRdate())+","+String.valueOf(tokenRequest.getOtp())+","+String.valueOf(tokenRequest.getTime())+")");
    		
            boolean results = cstmt.execute();
            int rowsAffected = 0;
     
            // Protects against lack of SET NOCOUNT in stored prodedure
            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }
            
            while (rs.next()) {
            	
            	System.out.println("Token Status:"+rs.getString("Status"));
            	
            	issuedTokenDetails.setTokenno(rs.getString("TokenID"));
            	issuedTokenDetails.setName(tokenRequest.getName());
            	issuedTokenDetails.setMobile(tokenRequest.getMobile());
            	issuedTokenDetails.setEmail(tokenRequest.getEmail());
            	if(tokenRequest.getAadhaar()==null)
            	{
            		issuedTokenDetails.setAadhaar("");
            	}
            	else{
            	issuedTokenDetails.setAadhaar(tokenRequest.getAadhaar());
            	}
            	if(tokenRequest.getEnroltype().equalsIgnoreCase("1"))
            	{
            		issuedTokenDetails.setEnrolmenttype("New");	
            		
            	}
            	else if(tokenRequest.getEnroltype().equalsIgnoreCase("2"))
            	{
            		issuedTokenDetails.setEnrolmenttype("Demographic");	
            		
            	}
            	else if(tokenRequest.getEnroltype().equalsIgnoreCase("3"))
            	{
            		issuedTokenDetails.setEnrolmenttype("Biometric");	
            		
            	}
            	
            	issuedTokenDetails.setPeccenter(Capitalize(rs.getString("PecName")));
            	issuedTokenDetails.setStatus(rs.getString("Status"));
            	issuedTokenDetails.setDate(tokenRequest.getRdate());
            	issuedTokenDetails.setTime(rs.getString("Time"));
            	
            }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return issuedTokenDetails;
	}
	
	public String Capitalize(String pecname)
	{
		String capname="";
		String[] pecnames=pecname.split(" ");
		
		for(int i=0;i<pecnames.length;i++)
		{
			if(i==pecnames.length-1)
			{
			capname=capname+pecnames[i].substring(0, 1).toUpperCase() + pecnames[i].substring(1).toLowerCase();
			}
			else{
			capname=capname+pecnames[i].substring(0, 1).toUpperCase() + pecnames[i].substring(1).toLowerCase()+" ";
			}
		}
		
		return capname;
	}

	@Override
	protected void finalize() throws Throwable {
		
		connection.close();
	}
	
	
	
}
