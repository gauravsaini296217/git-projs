package aadhaartokens.model;

public class IssuedTokenDetails {

	private String tokenno="", name="", mobile="", email="", aadhaar="", enrolmenttype="", peccenter="",status="",date="", time="";

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTokenno() {
		return tokenno;
	}

	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getEnrolmenttype() {
		return enrolmenttype;
	}

	public void setEnrolmenttype(String enrolmenttype) {
		this.enrolmenttype = enrolmenttype;
	}

	public String getPeccenter() {
		return peccenter;
	}

	public void setPeccenter(String peccenter) {
		this.peccenter = peccenter;
	}
	
	
}
