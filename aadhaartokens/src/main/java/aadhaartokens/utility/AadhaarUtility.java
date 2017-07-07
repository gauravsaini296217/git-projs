package aadhaartokens.utility;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import aadhaartokens.model.BranchDateToken;
import aadhaartokens.model.CheckOTP;
import aadhaartokens.model.EnrolmentDetails;
import aadhaartokens.model.IssuedTokenDetails;
import aadhaartokens.model.OTP;
import aadhaartokens.model.ReportRequest;
import aadhaartokens.model.RowDates;
import aadhaartokens.model.SearchRequest;
import aadhaartokens.model.TokenDetail;
import aadhaartokens.model.TokenRequest;
import aadhaartokens.service.AadhaarTokenStatusService;
import aadhaartokens.service.AadhaarTokenStatusServiceImpl;

@Component
public class AadhaarUtility {
	
	private Connection connection;
	
	
	AadhaarTokenStatusService aadhaarTokenStatusService=new AadhaarTokenStatusServiceImpl();
	
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
            	
            	issuedTokenDetails.setTokenno(rs.getString("TokenID"));
            	issuedTokenDetails.setName(tokenRequest.getName());
            	issuedTokenDetails.setMobile(tokenRequest.getMobile());
            	issuedTokenDetails.setEmail(rs.getString("Email"));
            	if(rs.getString("Aadhaar")==null || rs.getString("Aadhaar")=="")
            	{
            		issuedTokenDetails.setAadhaar("");
            	}
            	else{
            	issuedTokenDetails.setAadhaar(rs.getString("Aadhaar"));
            	}
            	if(rs.getString("EType").equalsIgnoreCase("NEW"))
            	{
            		issuedTokenDetails.setEnrolmenttype("New");	
            		
            	}
            	else if(rs.getString("EType").equalsIgnoreCase("Demographic Updation"))
            	{
            		issuedTokenDetails.setEnrolmenttype("Demographic");	
            		
            	}
            	else if(rs.getString("EType").equalsIgnoreCase("Biometric Updation"))
            	{
            		issuedTokenDetails.setEnrolmenttype("Biometric");	
            		
            	}
            	
            	issuedTokenDetails.setStatus(rs.getString("Status"));
            	issuedTokenDetails.setDate(rs.getString("Date"));
            	issuedTokenDetails.setTime(rs.getString("Time"));
            	issuedTokenDetails.setPeccenter(Capitalize(rs.getString("PecName")));
            	
            	
            }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return issuedTokenDetails;
	}
	
	public List<TokenDetail> getReportResult(ReportRequest reportResult)
	{
		
		System.out.println(reportResult);
		
		List<TokenDetail> tokenDetails=new ArrayList<>();
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			
			String sql="select * from tokendetails ";
			if(!reportResult.getRegionspecific().equalsIgnoreCase("ALL") && !sql.contains("where"))
			{
				sql=sql+"where ";
			}
			
			if(reportResult.getRegionspecific().equalsIgnoreCase("State"))
			{
				sql=sql+"state='"+reportResult.getState()+"' ";
			}
			else if(reportResult.getRegionspecific().equalsIgnoreCase("District"))
			{
				sql=sql+"state='"+reportResult.getState()+"' ";
				sql=sql+" and ";
				sql=sql+"region='"+reportResult.getDistrict()+"' ";
				
			}
			else if(reportResult.getRegionspecific().equalsIgnoreCase("Pec"))
			{
				sql=sql+"state='"+reportResult.getState()+"' ";
				sql=sql+" and ";
				sql=sql+"region='"+reportResult.getDistrict()+"' ";
				sql=sql+" and ";
				sql=sql+"branch='"+reportResult.getPec()+"' ";
				
			}
			if(!reportResult.getEnrolmentspecific().equalsIgnoreCase("ALL") && !sql.contains("where"))
			{
				sql=sql+"where ";
			}
			
			if(reportResult.getEnrolmentspecific().equalsIgnoreCase("EnrolmentType"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch"))
				{
					sql=sql+" and ";
				}
				sql=sql+"enrolmenttype='"+reportResult.getEnrolmenttype()+"' ";
				
			}
			if(!reportResult.getStatusspecific().equalsIgnoreCase("ALL") && !sql.contains("where"))
			{
				sql=sql+"where ";
			}
			
			if(reportResult.getStatusspecific().equalsIgnoreCase("Status"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch") || sql.contains("enrolmenttype"))
				{
					sql=sql+" and ";
				}
				sql=sql+"flag='"+reportResult.getStatus()+"' ";
				
			}
			if(!reportResult.getDatespecific().equalsIgnoreCase("ALL") && !sql.contains("where"))
			{
				sql=sql+"where ";
			}
			if(reportResult.getDatespecific().equalsIgnoreCase("Specific Date"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch") || sql.contains("enrolmenttype") || sql.contains("flag"))
				{
					sql=sql+" and ";
				}
				sql=sql+"tokengenerationdate='"+format.format(reportResult.getFdate())+"' ";
				
			}
			if(reportResult.getDatespecific().equalsIgnoreCase("Date Range"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch") || sql.contains("enrolmenttype") || sql.contains("flag"))
				{
					sql=sql+" and ";
				}
				sql=sql+"tokengenerationdate>='"+format.format(reportResult.getFdate())+"' and tokengenerationdate<='"+format.format(reportResult.getTdate());
				
			}
			
			System.out.println("sql:"+sql);
			
			TokenDetail tokendetail=new TokenDetail();
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				tokendetail=new TokenDetail();
				tokendetail.setTokenid(rs.getInt("TokenID"));
				PreparedStatement ps1=connection.prepareStatement("select PECState,PECDistrict,PECCity from pecmaster where stateid='"+rs.getString("State")+"' and districtid='"+rs.getString("Region")+"' and peccode='"+rs.getString("Branch")+"'");
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next())
				{
					tokendetail.setState(rs1.getString(1));
					tokendetail.setRegion(rs1.getString(2));
					tokendetail.setBranch(rs1.getString(3));
					
				}
				
			    tokendetail.setEnrolmenttype(rs.getString("EnrolmentType"));
				tokendetail.setName(rs.getString("Name"));
				tokendetail.setContactno(rs.getString("ContactNo"));
				tokendetail.setEmailid(rs.getString("EmailID"));
				tokendetail.setAadhaarno(rs.getString("AadhaarNo"));
				tokendetail.setTokenno(rs.getString("TokenNO"));
				tokendetail.setTokentime(rs.getString("TokenTime"));
				tokendetail.setTokengenerationdate(rs.getString("TokenGenerationDate"));
				tokendetail.setCreationdate(rs.getString("CreationDate"));
				ps1=connection.prepareStatement("select type from aadhaartokenstatusmaster where id='"+rs.getString("Flag")+"'");
				rs1=ps1.executeQuery();
				while(rs1.next())
				{
					tokendetail.setFlag(rs1.getString(1));
					
				}
				tokendetail.setFulltokenno(rs.getString("FullTokenNo"));
				tokendetail.setOtp(rs.getInt("Otp"));
				tokendetail.setOtptime(rs.getString("OtpTime"));
				System.out.println(tokendetail.toString());
				
				tokenDetails.add(tokendetail);
				
			}
			
			System.out.println("Size:"+tokenDetails.size());
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		return tokenDetails;
		
	}
	
	
	public List<TokenDetail> getStateReportResult(ReportRequest reportResult, String peccode)
	{
		
		System.out.println(reportResult);
		
		List<TokenDetail> tokenDetails=new ArrayList<>();
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		
		try{
			
			String sql="select * from tokendetails ";
			if(!sql.contains("where"))
			{
				sql=sql+"where ";
			}
			
			if(reportResult.getRegionspecific().equalsIgnoreCase("ALL"))
			{
				sql=sql+"state='"+peccode+"' ";
			}
			else if(reportResult.getRegionspecific().equalsIgnoreCase("District"))
			{
				sql=sql+"state='"+peccode+"' ";
				sql=sql+" and ";
				sql=sql+"region='"+reportResult.getDistrict()+"' ";
				
			}
			else if(reportResult.getRegionspecific().equalsIgnoreCase("Pec"))
			{
				sql=sql+"state='"+peccode+"' ";
				sql=sql+" and ";
				sql=sql+"region='"+reportResult.getDistrict()+"' ";
				sql=sql+" and ";
				sql=sql+"branch='"+reportResult.getPec()+"' ";
				
			}
			if(!reportResult.getEnrolmentspecific().equalsIgnoreCase("ALL") && !sql.contains("where"))
			{
				sql=sql+"where ";
			}
			
			if(reportResult.getEnrolmentspecific().equalsIgnoreCase("EnrolmentType"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch"))
				{
					sql=sql+" and ";
				}
				sql=sql+"enrolmenttype='"+reportResult.getEnrolmenttype()+"' ";
				
			}
			if(!reportResult.getStatusspecific().equalsIgnoreCase("ALL") && !sql.contains("where"))
			{
				sql=sql+"where ";
			}
			
			if(reportResult.getStatusspecific().equalsIgnoreCase("Status"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch") || sql.contains("enrolmenttype"))
				{
					sql=sql+" and ";
				}
				sql=sql+"flag='"+reportResult.getStatus()+"' ";
				
			}
			if(!reportResult.getDatespecific().equalsIgnoreCase("ALL") && !sql.contains("where"))
			{
				sql=sql+"where ";
			}
			if(reportResult.getDatespecific().equalsIgnoreCase("Specific Date"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch") || sql.contains("enrolmenttype") || sql.contains("flag"))
				{
					sql=sql+" and ";
				}
				sql=sql+"tokengenerationdate='"+format.format(reportResult.getFdate())+"' ";
				
			}
			if(reportResult.getDatespecific().equalsIgnoreCase("Date Range"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch") || sql.contains("enrolmenttype") || sql.contains("flag"))
				{
					sql=sql+" and ";
				}
				sql=sql+"tokengenerationdate>='"+format.format(reportResult.getFdate())+"' and tokengenerationdate<='"+format.format(reportResult.getTdate());
				
			}
			
			System.out.println("sql:"+sql);
			
			TokenDetail tokendetail=new TokenDetail();
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				tokendetail=new TokenDetail();
				tokendetail.setTokenid(rs.getInt("TokenID"));
				PreparedStatement ps1=connection.prepareStatement("select PECState,PECDistrict,PECCity from pecmaster where stateid='"+rs.getString("State")+"' and districtid='"+rs.getString("Region")+"' and peccode='"+rs.getString("Branch")+"'");
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next())
				{
					tokendetail.setState(rs1.getString(1));
					tokendetail.setRegion(rs1.getString(2));
					tokendetail.setBranch(rs1.getString(3));
					
				}
				
			    tokendetail.setEnrolmenttype(rs.getString("EnrolmentType"));
				tokendetail.setName(rs.getString("Name"));
				tokendetail.setContactno(rs.getString("ContactNo"));
				tokendetail.setEmailid(rs.getString("EmailID"));
				tokendetail.setAadhaarno(rs.getString("AadhaarNo"));
				tokendetail.setTokenno(rs.getString("TokenNO"));
				tokendetail.setTokentime(rs.getString("TokenTime"));
				tokendetail.setTokengenerationdate(rs.getString("TokenGenerationDate"));
				tokendetail.setCreationdate(rs.getString("CreationDate"));
				ps1=connection.prepareStatement("select type from aadhaartokenstatusmaster where id='"+rs.getString("Flag")+"'");
				rs1=ps1.executeQuery();
				while(rs1.next())
				{
					tokendetail.setFlag(rs1.getString(1));
					
				}
				tokendetail.setFulltokenno(rs.getString("FullTokenNo"));
				tokendetail.setOtp(rs.getInt("Otp"));
				tokendetail.setOtptime(rs.getString("OtpTime"));
				System.out.println(tokendetail.toString());
				
				tokenDetails.add(tokendetail);
				
			}
			
			System.out.println("Size:"+tokenDetails.size());
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		return tokenDetails;
		
	}
	
	
	public List<TokenDetail> getBranchReportResult(ReportRequest reportResult, String peccode)
	{
		
		System.out.println(reportResult);
		
		List<TokenDetail> tokenDetails=new ArrayList<>();
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		
		try{
			
			String sql="select * from tokendetails where branch='"+peccode+"' ";
			
			if(!reportResult.getEnrolmentspecific().equalsIgnoreCase("ALL") && !sql.contains("where"))
			{
				sql=sql+"where ";
			}
			
			if(reportResult.getEnrolmentspecific().equalsIgnoreCase("EnrolmentType"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch"))
				{
					sql=sql+" and ";
				}
				sql=sql+"enrolmenttype='"+reportResult.getEnrolmenttype()+"' ";
				
			}
			if(!reportResult.getStatusspecific().equalsIgnoreCase("ALL") && !sql.contains("where"))
			{
				sql=sql+"where ";
			}
			
			if(reportResult.getStatusspecific().equalsIgnoreCase("Status"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch") || sql.contains("enrolmenttype"))
				{
					sql=sql+" and ";
				}
				sql=sql+"flag='"+reportResult.getStatus()+"' ";
				
			}
			if(!reportResult.getDatespecific().equalsIgnoreCase("ALL") && !sql.contains("where"))
			{
				sql=sql+"where ";
			}
			if(reportResult.getDatespecific().equalsIgnoreCase("Specific Date"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch") || sql.contains("enrolmenttype") || sql.contains("flag"))
				{
					sql=sql+" and ";
				}
				sql=sql+"tokengenerationdate='"+format.format(reportResult.getFdate())+"' ";
				
			}
			if(reportResult.getDatespecific().equalsIgnoreCase("Date Range"))
			{
				if(sql.contains("state") || sql.contains("region") || sql.contains("branch") || sql.contains("enrolmenttype") || sql.contains("flag"))
				{
					sql=sql+" and ";
				}
				sql=sql+"tokengenerationdate>='"+format.format(reportResult.getFdate())+"' and tokengenerationdate<='"+format.format(reportResult.getTdate());
				
			}
			
			System.out.println("sql:"+sql);
			
			TokenDetail tokendetail=new TokenDetail();
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				tokendetail=new TokenDetail();
				tokendetail.setTokenid(rs.getInt("TokenID"));
				PreparedStatement ps1=connection.prepareStatement("select PECState,PECDistrict,PECCity from pecmaster where stateid='"+rs.getString("State")+"' and districtid='"+rs.getString("Region")+"' and peccode='"+rs.getString("Branch")+"'");
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next())
				{
					tokendetail.setState(rs1.getString(1));
					tokendetail.setRegion(rs1.getString(2));
					tokendetail.setBranch(rs1.getString(3));
					
				}
				
			    tokendetail.setEnrolmenttype(rs.getString("EnrolmentType"));
				tokendetail.setName(rs.getString("Name"));
				tokendetail.setContactno(rs.getString("ContactNo"));
				tokendetail.setEmailid(rs.getString("EmailID"));
				tokendetail.setAadhaarno(rs.getString("AadhaarNo"));
				tokendetail.setTokenno(rs.getString("TokenNO"));
				tokendetail.setTokentime(rs.getString("TokenTime"));
				tokendetail.setTokengenerationdate(rs.getString("TokenGenerationDate"));
				tokendetail.setCreationdate(rs.getString("CreationDate"));
				ps1=connection.prepareStatement("select type from aadhaartokenstatusmaster where id='"+rs.getString("Flag")+"'");
				rs1=ps1.executeQuery();
				while(rs1.next())
				{
					tokendetail.setFlag(rs1.getString(1));
					
				}
				tokendetail.setFulltokenno(rs.getString("FullTokenNo"));
				tokendetail.setOtp(rs.getInt("Otp"));
				tokendetail.setOtptime(rs.getString("OtpTime"));
				System.out.println(tokendetail.toString());
				
				tokenDetails.add(tokendetail);
				
			}
			
			System.out.println("Size:"+tokenDetails.size());
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		return tokenDetails;
		
	}
	
	
	public TokenDetail getSearchResult(SearchRequest searchRequest)
	{
		
		System.out.println(searchRequest);
		
		TokenDetail tokendetail=null;	
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		
		try{
			
			String sql="";
			
			if(searchRequest.getSearchtype().equalsIgnoreCase("Token"))
			{
				sql="select * from tokendetails where FullTokenNo='"+searchRequest.getTokenno()+"' and flag='A'";
			}
			else if(searchRequest.getSearchtype().equalsIgnoreCase("NameMobile"))
			{
				sql="select * from tokendetails where name='"+searchRequest.getName()+"' and contactno='"+searchRequest.getMobile()+"' and flag='A'";
			}
			
			System.out.println("sql:"+sql);
			
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				tokendetail=new TokenDetail();
				tokendetail.setTokenid(rs.getInt("TokenID"));
				PreparedStatement ps1=connection.prepareStatement("select PECState,PECDistrict,PECCity from pecmaster where stateid='"+rs.getString("State")+"' and districtid='"+rs.getString("Region")+"' and peccode='"+rs.getString("Branch")+"'");
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next())
				{
					tokendetail.setState(rs1.getString(1));
					tokendetail.setRegion(rs1.getString(2));
					tokendetail.setBranch(rs1.getString(3));
					
				}
				
			    tokendetail.setEnrolmenttype(rs.getString("EnrolmentType"));
				tokendetail.setName(rs.getString("Name"));
				tokendetail.setContactno(rs.getString("ContactNo"));
				tokendetail.setEmailid(rs.getString("EmailID"));
				tokendetail.setAadhaarno(rs.getString("AadhaarNo"));
				tokendetail.setTokenno(rs.getString("TokenNO"));
				tokendetail.setTokentime(rs.getString("TokenTime"));
				tokendetail.setTokengenerationdate(rs.getString("TokenGenerationDate"));
				tokendetail.setCreationdate(rs.getString("CreationDate"));
				ps1=connection.prepareStatement("select type from aadhaartokenstatusmaster where id='"+rs.getString("Flag")+"'");
				rs1=ps1.executeQuery();
				while(rs1.next())
				{
					tokendetail.setFlag(rs1.getString(1));
					
				}
				tokendetail.setFulltokenno(rs.getString("FullTokenNo"));
				tokendetail.setOtp(rs.getInt("Otp"));
				tokendetail.setOtptime(rs.getString("OtpTime"));
				System.out.println(tokendetail.toString());
				
				
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		return tokendetail;
		
	}
	
	
	public List<TokenDetail> getSearchResults(SearchRequest searchRequest)
	{
		
		System.out.println(searchRequest);
		
		List<TokenDetail> tokendetails=new ArrayList<TokenDetail>();
		
		TokenDetail tokendetail=null;	
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		
		try{
			
			String sql="";
			
			if(searchRequest.getSearchtype().equalsIgnoreCase("Token"))
			{
				sql="select * from tokendetails where FullTokenNo='"+searchRequest.getTokenno()+"'";
			}
			else if(searchRequest.getSearchtype().equalsIgnoreCase("NameMobile"))
			{
				sql="select * from tokendetails where name='"+searchRequest.getName()+"' and contactno='"+searchRequest.getMobile()+"'";
			}
			
			System.out.println("sql:"+sql);
			
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				tokendetail=new TokenDetail();
				tokendetail.setTokenid(rs.getInt("TokenID"));
				PreparedStatement ps1=connection.prepareStatement("select PECState,PECDistrict,PECCity from pecmaster where stateid='"+rs.getString("State")+"' and districtid='"+rs.getString("Region")+"' and peccode='"+rs.getString("Branch")+"'");
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next())
				{
					tokendetail.setState(rs1.getString(1));
					tokendetail.setRegion(rs1.getString(2));
					tokendetail.setBranch(rs1.getString(3));
					
				}
				
			    tokendetail.setEnrolmenttype(rs.getString("EnrolmentType"));
				tokendetail.setName(rs.getString("Name"));
				tokendetail.setContactno(rs.getString("ContactNo"));
				tokendetail.setEmailid(rs.getString("EmailID"));
				tokendetail.setAadhaarno(rs.getString("AadhaarNo"));
				tokendetail.setTokenno(rs.getString("TokenNO"));
				tokendetail.setTokentime(rs.getString("TokenTime"));
				tokendetail.setTokengenerationdate(rs.getString("TokenGenerationDate"));
				tokendetail.setCreationdate(rs.getString("CreationDate"));
				ps1=connection.prepareStatement("select type from aadhaartokenstatusmaster where id='"+rs.getString("Flag")+"'");
				rs1=ps1.executeQuery();
				while(rs1.next())
				{
					tokendetail.setFlag(rs1.getString(1));
					
				}
				tokendetail.setFulltokenno(rs.getString("FullTokenNo"));
				tokendetail.setOtp(rs.getInt("Otp"));
				tokendetail.setOtptime(rs.getString("OtpTime"));
				System.out.println(tokendetail.toString());
				
				tokendetails.add(tokendetail);
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		return tokendetails;
		
	}
	
	
	
	public String updateResult(SearchRequest searchRequest,String email)
	{
		
		System.out.println(searchRequest);
		
		String result="";
		
		try{
			
			String sql="insert into aadhaartokenupdatedetails values('"+searchRequest.getTokenid()+"','"+searchRequest.getTname()+"','"+searchRequest.getTmobile()+"','"+searchRequest.getTtoken()+"','"+searchRequest.getEnrolmentid()+"','"+email+"');";
			System.out.println("sql:"+sql);
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.execute();
			
			sql="update tokendetails set flag='D' where tokenid='"+searchRequest.getTokenid()+"' and fulltokenno='"+searchRequest.getTtoken()+"' and flag='A';";
			System.out.println("sql:"+sql);
			
			ps=connection.prepareStatement(sql);
			ps.execute();
			
			result="Updated";
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			result="Error";
			
		}
		
		return result;
		
	}
	
	public boolean appointmentExists(String name,String mobile)
	{
		
		boolean result=false;
		
		try{
			
			String sql="select * from tokendetails where flag='A' and name='"+name+"' and contactno='"+mobile+"';";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				return true;
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
			
		}
		
		return result;
		
	}
	
	
	public EnrolmentDetails getEDateToken(EnrolmentDetails enrolmentDetails)
	{
		
		try{
			
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			
			System.out.println(enrolmentDetails);
			
			System.out.println(enrolmentDetails.getRdate());
			
			ResultSet rs=null;
    		CallableStatement cstmt = connection.prepareCall("{call InsFullEnrolmentFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
    		cstmt.setString("State", String.valueOf(enrolmentDetails.getSstate()));
    		cstmt.setString("region", String.valueOf(enrolmentDetails.getSdistrict()));
    		cstmt.setString("branch", String.valueOf(enrolmentDetails.getPeccode()));
    		cstmt.setString("Type", String.valueOf(enrolmentDetails.getEnroltype()));
    		cstmt.setString("Date", String.valueOf(enrolmentDetails.getRdate()));
    		cstmt.setInt("Otp", Integer.parseInt(enrolmentDetails.getOtp()));
    		cstmt.setString("OtpTime", String.valueOf(enrolmentDetails.getOtptime()));
    		cstmt.setString("FullName", String.valueOf(enrolmentDetails.getName()));
    		cstmt.setString("Gender", String.valueOf(enrolmentDetails.getGender()));
    		cstmt.setInt("Age", enrolmentDetails.getAge());
    		if(enrolmentDetails.getDob()==null)
    		{
    		cstmt.setString("DateofBirth", String.valueOf("1900-01-01"));
    		}
    		else{
    		cstmt.setString("DateofBirth", String.valueOf(format.format(enrolmentDetails.getDob())));
    		}
    		cstmt.setString("RelationType", String.valueOf(enrolmentDetails.getCo()));
    		cstmt.setString("RelationName", String.valueOf(enrolmentDetails.getGname()));
    		cstmt.setString("HouseNoBldgApt", String.valueOf(enrolmentDetails.getHno()));
    		cstmt.setString("StreetRoadLane", String.valueOf(enrolmentDetails.getStreetno()));
    		cstmt.setString("Landmark", String.valueOf(enrolmentDetails.getLmark()));
    		cstmt.setString("Arealocalitysector", String.valueOf(enrolmentDetails.getArea()));
    		cstmt.setString("VillageTownCity", String.valueOf(enrolmentDetails.getVill()));
    		cstmt.setString("PostOffice", String.valueOf(enrolmentDetails.getPost()));
    		cstmt.setString("ResState", String.valueOf(enrolmentDetails.getState()));
    		cstmt.setString("SubDistrict", String.valueOf(enrolmentDetails.getPost()));
    		cstmt.setString("District", String.valueOf(enrolmentDetails.getDist()));
    		cstmt.setInt("PINCODE", Integer.parseInt(enrolmentDetails.getPin()));
    		cstmt.setString("MobileNo", String.valueOf(enrolmentDetails.getMobile()));
    		cstmt.setString("EMail", String.valueOf(enrolmentDetails.getEmail()));
    		cstmt.setString("Guardian", String.valueOf(enrolmentDetails.getDguardian()));
    		cstmt.setString("BFiveyrGuardName", String.valueOf(enrolmentDetails.getDgname()));
    		cstmt.setString("GuardianAadhaarNo", String.valueOf(enrolmentDetails.getDgaadhaar()));
    		if(enrolmentDetails.getAadhaarno().length()==12)
    		{
    			cstmt.setString("AadhaarNo", String.valueOf(enrolmentDetails.getAadhaarno()));
        		cstmt.setString("EID", String.valueOf(""));
    		}
    		else if(enrolmentDetails.getAadhaarno().length()>12){
    			cstmt.setString("AadhaarNo", String.valueOf(""));
        		cstmt.setString("EID", String.valueOf(enrolmentDetails.getAadhaarno()));
    		}
    		else{
    			cstmt.setString("AadhaarNo", String.valueOf(""));
        		cstmt.setString("EID", String.valueOf(""));
    		}
    		cstmt.setString("POI", String.valueOf(enrolmentDetails.getPoi()));
    		cstmt.setString("POA", String.valueOf(enrolmentDetails.getPoa()));
    		cstmt.setString("DOB", String.valueOf(enrolmentDetails.getDobp()));
    		cstmt.setString("POR", String.valueOf(enrolmentDetails.getPor()));
    		cstmt.setString("IntroducerBasedAadaarNo", String.valueOf(""));
    		cstmt.setString("HOFMember", String.valueOf(enrolmentDetails.getHof()));
    		cstmt.setString("HOFName", String.valueOf(enrolmentDetails.getHofname()));
    		if(enrolmentDetails.getHofaadhaar().length()==12)
    		{
    			cstmt.setString("HOFBasedAadhaarNo", String.valueOf(enrolmentDetails.getHofaadhaar()));
        		cstmt.setString("HOFBasedEID", String.valueOf(""));
    		}
    		else if(enrolmentDetails.getHofaadhaar().length()>12){
    			cstmt.setString("HOFBasedAadhaarNo", String.valueOf(""));
        		cstmt.setString("HOFBasedEID", String.valueOf(enrolmentDetails.getHofaadhaar()));
    		}
    		else{
    			cstmt.setString("HOFBasedAadhaarNo", String.valueOf(""));
        		cstmt.setString("HOFBasedEID", String.valueOf(""));
    		}
    		
    		
    		
    		
    		
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
            	
            	enrolmentDetails.setTokenid(rs.getString("TokenID"));
            	
            	if(enrolmentDetails.getEnroltype().equalsIgnoreCase("1"))
            	{
            		enrolmentDetails.setEnroltype("New");	
            		
            	}
            	else if(enrolmentDetails.getEnroltype().equalsIgnoreCase("2"))
            	{
            		enrolmentDetails.setEnroltype("Demographic");	
            		
            	}
            	else if(enrolmentDetails.getEnroltype().equalsIgnoreCase("3"))
            	{
            		enrolmentDetails.setEnroltype("Biometric");	
            		
            	}
            	
            	enrolmentDetails.setPecname(Capitalize(rs.getString("PecName")));
            	enrolmentDetails.setStatus(rs.getString("Status"));
            	enrolmentDetails.setTime(rs.getString("Time"));
            	
            	
            }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return enrolmentDetails;
	}
	
	
	public String Capitalize(String pecname)
	{
		String capname="";
		String[] pecnames=pecname.split(" ");
		System.out.println("pecnames length:"+pecnames.length);
		
		for(int i=0;i<pecnames.length;i++)
		{
			if(i==pecnames.length-1)
			{
			capname=capname+pecnames[i].substring(0, 1).toUpperCase() + pecnames[i].substring(1).toLowerCase();
			}
			else{
			if(!pecnames[i].toString().equalsIgnoreCase(""))	
			{
			capname=capname+pecnames[i].substring(0, 1).toUpperCase() + pecnames[i].substring(1).toLowerCase()+" ";
			}
			}
		}
		
		return capname;
	}

	@Override
	protected void finalize() throws Throwable {
		
		connection.close();
	}
	
	
	
}
