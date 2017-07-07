package aadhaartokens.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class EnrolmentDetails {

	
	
	
	private String gender;
	
	@Past(message="Can't be future date")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dob;
	
	private String co;
	
	@NotEmpty(message="Guardian Name is mandatory")
	private String gname;
	
	@NotEmpty(message="House No is mandatory")
	private String hno;

	private String streetno;
	
	private String lmark;
	
	private String area;

	
	@Pattern(regexp="(^$|[0-9]{6})", message="Pincode is 6 digit no.")
	@NotEmpty(message="Pincode is mandatory")
	private String pin;
	
	private String vill;
	
	private String dist;
	
	private String post;
	
	private String state;
	
	private String mobile;
	
	@Email
	private String email;
	
	private String vertype;
	
	private String hof;
	
	@Pattern(regexp="^|[A-Za-z ]+$")
	private String hofname;
	
	@Pattern(regexp="(^$|[0-9]{12})", message="Aadhaar is 12 digit no.")
	private String hofaadhaar;
	
	private String poi;
	
	private String poa;
	
	private String dobp;	
	
	private String por;
	
	private String name;
	
	private String enroltype;
	
	private String aadhaarno;
	
    private String rid;
	
	private String rdate;
	
	private String ravtoken;
	
	private String rmxtoken;

	private String rtimeslot;
	
	private String rpeccode;
	
	private String peccode;

	private String sstate;
	
	private String sdistrict;
	
	private int age;
	
	private String otp;
	
	private String otptime;
	
	private String dguardian;
	
	private String tokenid;
	
	private String pecname;
	
	private String status;
	
	private String time;
	
	@Pattern(regexp="^|[A-Za-z ]+$")
	private String dgname;
	
	@Pattern(regexp="(^$|[0-9]{12})", message="Aadhaar is 12 digit no.")
	private String dgaadhaar;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getHno() {
		return hno;
	}

	public void setHno(String hno) {
		this.hno = hno;
	}

	public String getStreetno() {
		return streetno;
	}

	public void setStreetno(String streetno) {
		this.streetno = streetno;
	}

	public String getLmark() {
		return lmark;
	}

	public void setLmark(String lmark) {
		this.lmark = lmark;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getVill() {
		return vill;
	}

	public void setVill(String vill) {
		this.vill = vill;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getVertype() {
		return vertype;
	}

	public void setVertype(String vertype) {
		this.vertype = vertype;
	}

	public String getHof() {
		return hof;
	}

	public void setHof(String hof) {
		this.hof = hof;
	}

	public String getHofname() {
		return hofname;
	}

	public void setHofname(String hofname) {
		this.hofname = hofname;
	}

	public String getHofaadhaar() {
		return hofaadhaar;
	}

	public void setHofaadhaar(String hofaadhaar) {
		this.hofaadhaar = hofaadhaar;
	}

	public String getPoi() {
		return poi;
	}

	public void setPoi(String poi) {
		this.poi = poi;
	}

	public String getPoa() {
		return poa;
	}

	public void setPoa(String poa) {
		this.poa = poa;
	}

	public String getDobp() {
		return dobp;
	}

	public void setDobp(String dobp) {
		this.dobp = dobp;
	}

	public String getPor() {
		return por;
	}

	public void setPor(String por) {
		this.por = por;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnroltype() {
		return enroltype;
	}

	public void setEnroltype(String enroltype) {
		this.enroltype = enroltype;
	}

	public String getAadhaarno() {
		return aadhaarno;
	}

	public void setAadhaarno(String aadhaarno) {
		this.aadhaarno = aadhaarno;
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

	public String getPeccode() {
		return peccode;
	}

	public void setPeccode(String peccode) {
		this.peccode = peccode;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getOtptime() {
		return otptime;
	}

	public void setOtptime(String otptime) {
		this.otptime = otptime;
	}

	public String getDguardian() {
		return dguardian;
	}

	public void setDguardian(String dguardian) {
		this.dguardian = dguardian;
	}

	public String getDgname() {
		return dgname;
	}

	public void setDgname(String dgname) {
		this.dgname = dgname;
	}

	public String getDgaadhaar() {
		return dgaadhaar;
	}

	public void setDgaadhaar(String dgaadhaar) {
		this.dgaadhaar = dgaadhaar;
	}

	public String getSstate() {
		return sstate;
	}

	public void setSstate(String sstate) {
		this.sstate = sstate;
	}

	public String getSdistrict() {
		return sdistrict;
	}

	public void setSdistrict(String sdistrict) {
		this.sdistrict = sdistrict;
	}

	public String getTokenid() {
		return tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}

	public String getPecname() {
		return pecname;
	}

	public void setPecname(String pecname) {
		this.pecname = pecname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
}
