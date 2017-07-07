package aadhaartokens.model;

import java.util.Date;

import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class ReportRequest {

	private String regionspecific;
	
	private String enrolmentspecific;
	
	private String statusspecific;
	
	private String datespecific;
	
	private String state;
	
	private String district;
	
	private String pec;
	
	private String enrolmenttype;
	
	private String status;
	
	@Past(message="Can't be future date")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fdate;
	
	@Past(message="Can't be future date")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date tdate;

	public String getRegionspecific() {
		return regionspecific;
	}

	public void setRegionspecific(String regionspecific) {
		this.regionspecific = regionspecific;
	}

	public String getEnrolmentspecific() {
		return enrolmentspecific;
	}

	public void setEnrolmentspecific(String enrolmentspecific) {
		this.enrolmentspecific = enrolmentspecific;
	}

	public String getStatusspecific() {
		return statusspecific;
	}

	public void setStatusspecific(String statusspecific) {
		this.statusspecific = statusspecific;
	}

	public String getDatespecific() {
		return datespecific;
	}

	public void setDatespecific(String datespecific) {
		this.datespecific = datespecific;
	}

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

	public String getEnrolmenttype() {
		return enrolmenttype;
	}

	public void setEnrolmenttype(String enrolmenttype) {
		this.enrolmenttype = enrolmenttype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	public Date getTdate() {
		return tdate;
	}

	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}

	@Override
	public String toString() {
		return "ReportRequest [regionspecific=" + regionspecific + ", enrolmentspecific=" + enrolmentspecific
				+ ", statusspecific=" + statusspecific + ", datespecific=" + datespecific + ", state=" + state
				+ ", district=" + district + ", pec=" + pec + ", enrolmenttype=" + enrolmenttype + ", status=" + status
				+ ", fdate=" + fdate + ", tdate=" + tdate + "]";
	}
	
	
	
}
