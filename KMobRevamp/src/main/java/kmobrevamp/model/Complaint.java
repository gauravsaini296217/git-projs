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
	
	@Column(name="handsetdamaged",length=10,nullable=true)
	private String handsetdamaged;
	
	@Column(name="lcdbroken",length=10,nullable=true)
	private String lcdbroken;
	
	@Column(name="adaptorburnt",length=10,nullable=true)
	private String adaptorburnt;
	
	@Column(name="scratchesonbody",length=10,nullable=true)
	private String scratchesonbody;
	
	@Column(name="keypadfaded",length=10,nullable=true)
	private String keypadfaded;
	
	@Column(name="accfunction",length=10,nullable=true)
	private String accfunction;
	
	@Column(name="liquidlogged",length=10,nullable=true)
	private String liquidlogged;
	
	@Column(name="batterydamaged",length=10,nullable=true)
	private String batterydamaged;
	
	@Column(name="handset",length=10,nullable=true)
	private String handset;
	
	@Column(name="battery",length=10,nullable=true)
	private String battery;
	
	@Column(name="charger",length=10,nullable=true)
	private String charger;
	
	@Column(name="handsfree",length=10,nullable=true)
	private String handsfree;
	
	@Column(name="soundmate",length=10,nullable=true)
	private String soundmate;
	
	@Column(name="memorycard",length=10,nullable=true)
	private String memorycard;
	
	@Column(name="datacable",length=10,nullable=true)
	private String datacable;

	@Column(name="fault",length=250,nullable=true)
	private String fault;
	
	@Column(name="labour",nullable=true)
	private float labour;
	
	@Column(name="part",nullable=true)
	private float part;
	
	@Column(name="approved",length=10,nullable=true)
	private String approved;
	
	@Column(name="standbymodel",length=25,nullable=true)
	private String standbymodel;
	
	@Column(name="serialno",length=25,nullable=true)
	private String serialno;
	
	@Pattern(regexp="^|[A-Za-z0-9 -]+$")
	@Column(name="reforimei",length=25,nullable=true)
	private String reforimei;
	
	@DateTimeFormat(pattern="dd-MM-YYYY")
	@Column(name="standbydate",nullable=true)
	private Date standbydate;

	@Column(name="receivedequipmentorrejectestimate", length=25,nullable=true)
	private String receivedequipmentorrejectestimate;
	
	@Column(name="sig_customer1", length=25,nullable=true)
	private String sig_customer1;
	
	@Column(name="sig_asc_personnel", length=25,nullable=true)
	private String sig_asc_personnel;

	@Column(name="sig_customer2", length=25,nullable=true)
	private String sig_customer2;
	
	
	
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

	public String getHandsetdamaged() {
		return handsetdamaged;
	}

	public void setHandsetdamaged(String handsetdamaged) {
		this.handsetdamaged = handsetdamaged;
	}

	public String getLcdbroken() {
		return lcdbroken;
	}

	public void setLcdbroken(String lcdbroken) {
		this.lcdbroken = lcdbroken;
	}

	public String getAdaptorburnt() {
		return adaptorburnt;
	}

	public void setAdaptorburnt(String adaptorburnt) {
		this.adaptorburnt = adaptorburnt;
	}

	public String getScratchesonbody() {
		return scratchesonbody;
	}

	public void setScratchesonbody(String scratchesonbody) {
		this.scratchesonbody = scratchesonbody;
	}

	public String getKeypadfaded() {
		return keypadfaded;
	}

	public void setKeypadfaded(String keypadfaded) {
		this.keypadfaded = keypadfaded;
	}

	public String getAccfunction() {
		return accfunction;
	}

	public void setAccfunction(String accfunction) {
		this.accfunction = accfunction;
	}

	public String getLiquidlogged() {
		return liquidlogged;
	}

	public void setLiquidlogged(String liquidlogged) {
		this.liquidlogged = liquidlogged;
	}

	public String getBatterydamaged() {
		return batterydamaged;
	}

	public void setBatterydamaged(String batterydamaged) {
		this.batterydamaged = batterydamaged;
	}

	public String getHandset() {
		return handset;
	}

	public void setHandset(String handset) {
		this.handset = handset;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getCharger() {
		return charger;
	}

	public void setCharger(String charger) {
		this.charger = charger;
	}

	public String getHandsfree() {
		return handsfree;
	}

	public void setHandsfree(String handsfree) {
		this.handsfree = handsfree;
	}

	public String getSoundmate() {
		return soundmate;
	}

	public void setSoundmate(String soundmate) {
		this.soundmate = soundmate;
	}

	public String getMemorycard() {
		return memorycard;
	}

	public void setMemorycard(String memorycard) {
		this.memorycard = memorycard;
	}

	public String getDatacable() {
		return datacable;
	}

	public void setDatacable(String datacable) {
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

	public float getPart() {
		return part;
	}

	public void setPart(float part) {
		this.part = part;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
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

	public String getSig_customer1() {
		return sig_customer1;
	}

	public void setSig_customer1(String sig_customer1) {
		this.sig_customer1 = sig_customer1;
	}

	public String getSig_asc_personnel() {
		return sig_asc_personnel;
	}

	public void setSig_asc_personnel(String sig_asc_personnel) {
		this.sig_asc_personnel = sig_asc_personnel;
	}

	public String getSig_customer2() {
		return sig_customer2;
	}

	public void setSig_customer2(String sig_customer2) {
		this.sig_customer2 = sig_customer2;
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
				+ sig_customer1 + ", sig_asc_personnel=" + sig_asc_personnel + ", sig_customer2=" + sig_customer2 + "]";
	}

	
	
}
