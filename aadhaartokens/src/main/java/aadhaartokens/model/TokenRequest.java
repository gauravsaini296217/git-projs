package aadhaartokens.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class TokenRequest {

	@NotEmpty(message="State is Mandatory")
	private String state;
	
	@NotEmpty(message="District is Mandatory")
	private String district;
	
	@NotEmpty(message="Pec is Mandatory")
	private String pec;
	
	@NotEmpty(message="Enrolment Type is Mandatory")
	private String enroltype;
	
	@Pattern(regexp="^|[A-Za-z ]+$")
	@NotEmpty(message="Name is Mandatory")
	private String name;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	@NotEmpty(message="Mobile No. is Mandatory")
	private String mobile;
	
	@NotEmpty(message="Time is Mandatory")
	private String time;
	
	private String status;
	
	@Pattern(regexp="(^$|[0-9]{4})")
	private String otp;
	
	@Email
	private String email;
	
	private String entrytype;
	
	private String rid;
	
	private String rdate;
	
	private String ravtoken;
	
	private String rmxtoken;

	private String rtimeslot;
	
	private String rpeccode;
	
	@Pattern(regexp="(^$|[0-9]{12})", message="Aadhaar is 12 digit no.")
	private String aadhaar;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public String getEnroltype() {
		return enroltype;
	}

	public void setEnroltype(String enroltype) {
		this.enroltype = enroltype;
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

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEntrytype() {
		return entrytype;
	}

	public void setEntrytype(String entrytype) {
		this.entrytype = entrytype;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getRavtoken() {
		return ravtoken;
	}

	public void setRavtoken(String ravtoken) {
		this.ravtoken = ravtoken;
	}

	public String getRmxtoken() {
		return rmxtoken;
	}

	public void setRmxtoken(String rmxtoken) {
		this.rmxtoken = rmxtoken;
	}

	public String getRtimeslot() {
		return rtimeslot;
	}

	public void setRtimeslot(String rtimeslot) {
		this.rtimeslot = rtimeslot;
	}

	public String getRpeccode() {
		return rpeccode;
	}

	public void setRpeccode(String rpeccode) {
		this.rpeccode = rpeccode;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	@Override
	public String toString() {
		return "TokenRequest [state=" + state + ", district=" + district + ", pec=" + pec + ", enroltype=" + enroltype
				+ ", name=" + name + ", mobile=" + mobile + ", time=" + time + ", status=" + status + ", otp=" + otp
				+ ", email=" + email + ", entrytype=" + entrytype + ", rid=" + rid + ", rdate=" + rdate + ", ravtoken="
				+ ravtoken + ", rmxtoken=" + rmxtoken + ", rtimeslot=" + rtimeslot + ", rpeccode=" + rpeccode
				+ ", aadhaar=" + aadhaar + "]";
	}
	
		
}
