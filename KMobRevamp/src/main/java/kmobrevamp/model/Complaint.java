package kmobrevamp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="KMR_Complaint")
public class Complaint {

	@Id
	@Column(name="workorderno")
	private Long workorderno;
	
	@Past(message="inDate can't be future date")
	@Column(name="indate")
	private Date indate;
	
	@Past(message="regnDate can't be future date")
	@Column(name="regndate")
	private Date regndate;
	
	@Past(message="Date can't be future date")
	@Column(name="date")
	private Date date;
	
	@Column(name="tokenno")
	private Long tokenno;
	
	@Column(name="claimno")
	private Long claimno;

	@Column(name="model")
	private String model;
	
	@Column(name="slno")
	private String slno;
	
	@Column(name="imeiorrsnno")
	private String imeiorrsnno;
	
	@Column(name="batteryno")
	private String batteryno;
	
	@Past(message="Date of Purchase can't be future date")
	@Column(name="dateofpurchase")
	private Date dateofpurchase;
	
	@Column(name="status", columnDefinition="varchar(10) default 'AMC'")
	private String status;
	
	@Pattern(regexp="^[A-Za-z ]+$")
	@NotEmpty(message="Customer Name is mandatory")
	@Column(name="cname")
	private String cname;
	
	@Pattern(regexp="^[A-Za-z0-9/\\- &#.,]+$")
	@NotEmpty(message="Customer Address is mandatory")
	@Column(name="caddress")
	private String caddress;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	@NotEmpty(message="Customer Mobile is mandatory")
	@Column(name="cmobile")
	private String cmobile;
	
	@Pattern(regexp="(^$|[0-9]{11})")
	@Column(name="clandline")
	private String clandline;
	
	public Long getWorkorderno() {
		return workorderno;
	}

	public void setWorkorderno(Long workorderno) {
		this.workorderno = workorderno;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public Date getRegndate() {
		return regndate;
	}

	public void setRegndate(Date regndate) {
		this.regndate = regndate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getTokenno() {
		return tokenno;
	}

	public void setTokenno(Long tokenno) {
		this.tokenno = tokenno;
	}

	public Long getClaimno() {
		return claimno;
	}

	public void setClaimno(Long claimno) {
		this.claimno = claimno;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSlno() {
		return slno;
	}

	public void setSlno(String slno) {
		this.slno = slno;
	}

	public String getImeiorrsnno() {
		return imeiorrsnno;
	}

	public void setImeiorrsnno(String imeiorrsnno) {
		this.imeiorrsnno = imeiorrsnno;
	}

	public String getBatteryno() {
		return batteryno;
	}

	public void setBatteryno(String batteryno) {
		this.batteryno = batteryno;
	}

	public Date getDateofpurchase() {
		return dateofpurchase;
	}

	public void setDateofpurchase(Date dateofpurchase) {
		this.dateofpurchase = dateofpurchase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public String getCmobile() {
		return cmobile;
	}

	public void setCmobile(String cmobile) {
		this.cmobile = cmobile;
	}

	public String getClandline() {
		return clandline;
	}

	public void setClandline(String clandline) {
		this.clandline = clandline;
	}
	
	
	
}
