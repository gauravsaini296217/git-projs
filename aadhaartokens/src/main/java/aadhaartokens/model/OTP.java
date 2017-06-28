package aadhaartokens.model;


public class OTP {

	String status;
	String time;
	String date;

	
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

	public OTP(String status) {
		super();
		this.status = status;
	}

	public OTP() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
