package kmobrevamp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="KMR_Complaint")
public class Complaint {

	@Id
	@Pattern(regexp="^|[A-Za-z0-9 -]+$")
	@NotEmpty(message="Serialno is mandatory")
	@Column(name="sno", length=20)
	private String sno;
	
	@Column(name="workorderno", length=20,nullable=true)
	private String workorderno;
	
	@Past(message="inDate can't be future date")
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(name="indate", nullable=true)
	private Date indate;
	
	@Past(message="regnDate can't be future date")
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(name="regndate", nullable=true)
	private Date regndate;
	
	@Past(message="Date can't be future date")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(name="date", nullable=true)
	private Date date;
	
	
	@Column(name="tokenno",length=60, nullable=true)
	private Long tokenno;
	
	@Column(name="claimno",length=60, nullable=true)
	private Long claimno;

	@Pattern(regexp="^|[A-Za-z0-9 -]+$")
	@NotEmpty(message="Model is mandatory")
	@Column(name="model",length=20, nullable=false)
	private String model;
	
	@Pattern(regexp="^|[A-Za-z0-9 -]+$")
	@NotEmpty(message="Slno is mandatory")
	@Column(name="slno", nullable=false)
	private String slno;
	
	@Pattern(regexp="^|[0-9]+$")
	@Size(min=12,max=15,message="Size must be between 12 and 15")
	@NotEmpty(message="IMEI/RSN No is mandatory")
	@Column(name="imeiorrsnno", nullable=false, length=15)
	private String imeiorrsnno;
	
	@Pattern(regexp="^|[A-Za-z0-9 -]+$")
	@Column(name="batteryno",length=25, nullable=true)
	private String batteryno;
	
	@Past(message="Date of Purchase cannot be future date")
	@NotNull(message="Date of purchase is mandatory")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(name="dateofpurchase", nullable=false)
	private Date dateofpurchase;
	
	@Column(name="status", columnDefinition="varchar(10) default 'AMC'")
	private String status;
	
	@Pattern(regexp="^|[A-Za-z ]+$")
	@NotEmpty(message="Customer Name is mandatory")
	@Column(name="cname",length=60,nullable=false)
	private String cname;
	
	@Pattern(regexp="^|[A-Za-z0-9/\\- &#()(.*),]+$",flags={Pattern.Flag.DOTALL})
	@NotEmpty(message="Customer Address is mandatory")
	@Column(name="caddress",length=200,nullable=false)
	private String caddress;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	@NotEmpty(message="Customer Mobile is mandatory")
	@Column(name="cmobile",length=10,nullable=false)
	private String cmobile;
	
	@Pattern(regexp="(^$|[0-9]{11})")
	@Column(name="clandline",length=11,nullable=true)
	private String clandline;
	
	@NotEmpty(message="Nature of complaint is mandatory")
	@Column(name="natureofcomplaint",length=250,nullable=false)
	private String natureofcomplaint;
	
	@Column(name="handsetdamaged",nullable=true)
	private Boolean handsetdamaged;
	
	@Column(name="lcdbroken",nullable=true)
	private Boolean lcdbroken;
	
	@Column(name="adaptorburnt",nullable=true)
	private Boolean adaptorburnt;
	
	@Column(name="scratchesonbody",nullable=true)
	private Boolean scratchesonbody;
	
	@Column(name="keypadfaded",nullable=true)
	private Boolean keypadfaded;
	
	@Column(name="accfunction",nullable=true)
	private Boolean accfunction;
	
	@Column(name="liquidlogged",nullable=true)
	private Boolean liquidlogged;
	
	@Column(name="batterydamaged",nullable=true)
	private Boolean batterydamaged;
	
	@Column(name="handset",nullable=true)
	private Boolean handset;
	
	@Column(name="battery",nullable=true)
	private Boolean battery;
	
	@Column(name="charger",nullable=true)
	private Boolean charger;
	
	@Column(name="handsfree",nullable=true)
	private Boolean handsfree;
	
	@Column(name="soundmate",nullable=true)
	private Boolean soundmate;
	
	@Column(name="memorycard",nullable=true)
	private Boolean memorycard;
	
	@Column(name="datacable",nullable=true)
	private Boolean datacable;

	@Column(name="fault",length=250,nullable=true)
	private String fault;
	
	@Column(name="labour",nullable=true)
	private float labour;
	
	@Column(name="part",nullable=true)
	private String part;
	
	@Column(name="partname",nullable=true)
	private String partname;
	
	@Column(name="partprice",nullable=true)
	private float partprice;
	
	@Column(name="approved",nullable=true)
	private Boolean approved;
	
	@Column(name="standbymodel",length=25,nullable=true)
	private String standbymodel;
	
	@Column(name="serialno",length=25,nullable=true)
	private String serialno;
	
	@Pattern(regexp="^|[A-Za-z0-9 -]+$")
	@Column(name="reforimei",length=25,nullable=true)
	private String reforimei;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(name="standbydate",nullable=true)
	private Date standbydate;

	@Column(name="receivedequipmentorrejectestimate",length=25,nullable=true)
	private String receivedequipmentorrejectestimate;
	
	@Column(name="sig_customer1", length=25,nullable=true)
	private Boolean sig_customer1;
	
	@Column(name="sig_asc_personnel", length=25,nullable=true)
	private Boolean sig_asc_personnel;

	@Column(name="sig_customer2", length=25,nullable=true)
	private Boolean sig_customer2;
	
	@Column(name="registeruser", length=60,nullable=true)
	private String registeruser;
	
	@Column(name="updateuser", length=60,nullable=true)
	private String updateuser;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	@Column(name="createddate",nullable=true)
	private Date createddate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	@Column(name="updatedate",nullable=true)
	private Date updatedate;
	

	public String getSno() {
		return sno;
	}



	public void setSno(String sno) {
		this.sno = sno;
	}



	public String getWorkorderno() {
		return workorderno;
	}



	public void setWorkorderno(String workorderno) {
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



	public String getNatureofcomplaint() {
		return natureofcomplaint;
	}



	public void setNatureofcomplaint(String natureofcomplaint) {
		this.natureofcomplaint = natureofcomplaint;
	}



	public Boolean getHandsetdamaged() {
		return handsetdamaged;
	}



	public void setHandsetdamaged(Boolean handsetdamaged) {
		this.handsetdamaged = handsetdamaged;
	}



	public Boolean getLcdbroken() {
		return lcdbroken;
	}



	public void setLcdbroken(Boolean lcdbroken) {
		this.lcdbroken = lcdbroken;
	}



	public Boolean getAdaptorburnt() {
		return adaptorburnt;
	}



	public void setAdaptorburnt(Boolean adaptorburnt) {
		this.adaptorburnt = adaptorburnt;
	}



	public Boolean getScratchesonbody() {
		return scratchesonbody;
	}



	public void setScratchesonbody(Boolean scratchesonbody) {
		this.scratchesonbody = scratchesonbody;
	}



	public Boolean getKeypadfaded() {
		return keypadfaded;
	}



	public void setKeypadfaded(Boolean keypadfaded) {
		this.keypadfaded = keypadfaded;
	}



	public Boolean getAccfunction() {
		return accfunction;
	}



	public void setAccfunction(Boolean accfunction) {
		this.accfunction = accfunction;
	}



	public Boolean getLiquidlogged() {
		return liquidlogged;
	}



	public void setLiquidlogged(Boolean liquidlogged) {
		this.liquidlogged = liquidlogged;
	}



	public Boolean getBatterydamaged() {
		return batterydamaged;
	}



	public void setBatterydamaged(Boolean batterydamaged) {
		this.batterydamaged = batterydamaged;
	}



	public Boolean getHandset() {
		return handset;
	}



	public void setHandset(Boolean handset) {
		this.handset = handset;
	}



	public Boolean getBattery() {
		return battery;
	}



	public void setBattery(Boolean battery) {
		this.battery = battery;
	}



	public Boolean getCharger() {
		return charger;
	}



	public void setCharger(Boolean charger) {
		this.charger = charger;
	}



	public Boolean getHandsfree() {
		return handsfree;
	}



	public void setHandsfree(Boolean handsfree) {
		this.handsfree = handsfree;
	}



	public Boolean getSoundmate() {
		return soundmate;
	}



	public void setSoundmate(Boolean soundmate) {
		this.soundmate = soundmate;
	}



	public Boolean getMemorycard() {
		return memorycard;
	}



	public void setMemorycard(Boolean memorycard) {
		this.memorycard = memorycard;
	}



	public Boolean getDatacable() {
		return datacable;
	}



	public void setDatacable(Boolean datacable) {
		this.datacable = datacable;
	}



	public String getFault() {
		return fault;
	}



	public void setFault(String fault) {
		this.fault = fault;
	}



	public float getLabour() {
		return labour;
	}



	public void setLabour(float labour) {
		this.labour = labour;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getPartname() {
		return partname;
	}

	public void setPartname(String partname) {
		this.partname = partname;
	}



	public float getPartprice() {
		return partprice;
	}



	public void setPartprice(float partprice) {
		this.partprice = partprice;
	}



	public Boolean getApproved() {
		return approved;
	}



	public void setApproved(Boolean approved) {
		this.approved = approved;
	}



	public String getStandbymodel() {
		return standbymodel;
	}



	public void setStandbymodel(String standbymodel) {
		this.standbymodel = standbymodel;
	}



	public String getSerialno() {
		return serialno;
	}



	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}



	public String getReforimei() {
		return reforimei;
	}



	public void setReforimei(String reforimei) {
		this.reforimei = reforimei;
	}



	public Date getStandbydate() {
		return standbydate;
	}



	public void setStandbydate(Date standbydate) {
		this.standbydate = standbydate;
	}

	public String getReceivedequipmentorrejectestimate() {
		return receivedequipmentorrejectestimate;
	}

	public void setReceivedequipmentorrejectestimate(String receivedequipmentorrejectestimate) {
		this.receivedequipmentorrejectestimate = receivedequipmentorrejectestimate;
	}



	public Boolean getSig_customer1() {
		return sig_customer1;
	}



	public void setSig_customer1(Boolean sig_customer1) {
		this.sig_customer1 = sig_customer1;
	}



	public Boolean getSig_asc_personnel() {
		return sig_asc_personnel;
	}



	public void setSig_asc_personnel(Boolean sig_asc_personnel) {
		this.sig_asc_personnel = sig_asc_personnel;
	}



	public Boolean getSig_customer2() {
		return sig_customer2;
	}



	public void setSig_customer2(Boolean sig_customer2) {
		this.sig_customer2 = sig_customer2;
	}



	public String getRegisteruser() {
		return registeruser;
	}



	public void setRegisteruser(String registeruser) {
		this.registeruser = registeruser;
	}



	public String getUpdateuser() {
		return updateuser;
	}



	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}



	public Date getCreateddate() {
		return createddate;
	}



	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}


	
	public Date getUpdatedate() {
		return updatedate;
	}


	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}



	@Override
	public String toString() {
		return "Complaint [sno=" + sno + ", workorderno=" + workorderno + ", indate=" + indate + ", regndate="
				+ regndate + ", date=" + date + ", tokenno=" + tokenno + ", claimno=" + claimno + ", model=" + model
				+ ", slno=" + slno + ", imeiorrsnno=" + imeiorrsnno + ", batteryno=" + batteryno + ", dateofpurchase="
				+ dateofpurchase + ", status=" + status + ", cname=" + cname + ", caddress=" + caddress + ", cmobile="
				+ cmobile + ", clandline=" + clandline + ", natureofcomplaint=" + natureofcomplaint
				+ ", handsetdamaged=" + handsetdamaged + ", lcdbroken=" + lcdbroken + ", adaptorburnt=" + adaptorburnt
				+ ", scratchesonbody=" + scratchesonbody + ", keypadfaded=" + keypadfaded + ", accfunction="
				+ accfunction + ", liquidlogged=" + liquidlogged + ", batterydamaged=" + batterydamaged + ", handset="
				+ handset + ", battery=" + battery + ", charger=" + charger + ", handsfree=" + handsfree
				+ ", soundmate=" + soundmate + ", memorycard=" + memorycard + ", datacable=" + datacable + ", fault="
				+ fault + ", labour=" + labour + ", part=" + part + ", approved=" + approved + ", standbymodel="
				+ standbymodel + ", serialno=" + serialno + ", reforimei=" + reforimei + ", standbydate=" + standbydate
				+ ", receivedequipmentorrejectestimate=" + receivedequipmentorrejectestimate + ", sig_customer1="
				+ sig_customer1 + ", sig_asc_personnel=" + sig_asc_personnel + ", sig_customer2=" + sig_customer2
				+ ", registeruser=" + registeruser + ", updateuser=" + updateuser + ", createddate=" + createddate
				+ ", updatedate=" + updatedate + "]";
	}

    
	
}
