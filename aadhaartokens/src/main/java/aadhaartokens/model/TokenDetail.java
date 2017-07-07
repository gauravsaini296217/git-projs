package aadhaartokens.model;

public class TokenDetail {

	private int tokenid,otp;
	
	private String state,region,branch,enrolmenttype,name,contactno,emailid,aadhaarno,tokenno;
	
	private String tokentime,tokengenerationdate,creationdate,flag,fulltokenno,otptime;

	public int getTokenid() {
		return tokenid;
	}

	public void setTokenid(int tokenid) {
		this.tokenid = tokenid;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getEnrolmenttype() {
		return enrolmenttype;
	}

	public void setEnrolmenttype(String enrolmenttype) {
		this.enrolmenttype = enrolmenttype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getAadhaarno() {
		return aadhaarno;
	}

	public void setAadhaarno(String aadhaarno) {
		this.aadhaarno = aadhaarno;
	}

	public String getTokenno() {
		return tokenno;
	}

	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
	}

	public String getTokentime() {
		return tokentime;
	}

	public void setTokentime(String tokentime) {
		this.tokentime = tokentime;
	}

	public String getTokengenerationdate() {
		return tokengenerationdate;
	}

	public void setTokengenerationdate(String tokengenerationdate) {
		this.tokengenerationdate = tokengenerationdate;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFulltokenno() {
		return fulltokenno;
	}

	public void setFulltokenno(String fulltokenno) {
		this.fulltokenno = fulltokenno;
	}

	public String getOtptime() {
		return otptime;
	}

	public void setOtptime(String otptime) {
		this.otptime = otptime;
	}

	@Override
	public String toString() {
		return "TokenDetail [tokenid=" + tokenid + ", otp=" + otp + ", state=" + state + ", region=" + region
				+ ", branch=" + branch + ", enrolmenttype=" + enrolmenttype + ", name=" + name + ", contactno="
				+ contactno + ", emailid=" + emailid + ", aadhaarno=" + aadhaarno + ", tokenno=" + tokenno
				+ ", tokentime=" + tokentime + ", tokengenerationdate=" + tokengenerationdate + ", creationdate="
				+ creationdate + ", flag=" + flag + ", fulltokenno=" + fulltokenno + ", otptime=" + otptime + "]";
	}
	
    
	
	
}
