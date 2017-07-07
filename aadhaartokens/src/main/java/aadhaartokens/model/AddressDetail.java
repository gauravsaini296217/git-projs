package aadhaartokens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="aadhaarpincodemaster")
public class AddressDetail{

	@Id
	@Column(name="aadpincodemasterid")
	private int aadpincodemasterid;
	
	@Column(name="pincode")
	private int pincode;
	
	@Column(name="statename", columnDefinition="nvarchar(100)")
	private String statename;
	
	@Column(name="statecode")
	private int statecode;
	
	@Column(name="districtname", columnDefinition="nvarchar(100)")
	private String districtname;
	
	@Column(name="districtcode")
	private int districtcode;
	
	@Column(name="subdistrictname", columnDefinition="nvarchar(100)")
	private String subdistrictname;
	
	@Column(name="subdistrictcode")
	private int subdistrictcode;
	
	@Column(name="vtcname", columnDefinition="nvarchar(200)")
	private String vtcname;
	
	@Column(name="vtccode", columnDefinition="nvarchar(40)")
	private String vtccode;
	
	public int getAadPincodeMasterID(){
		return aadpincodemasterid;
	}
	
	public void setAadPincodeMasterID(int aadpincodemasterid){
		this.aadpincodemasterid=aadpincodemasterid;
	}
	
	public int getPinCode(){
		return pincode;
	}
	
	public void setPinCode(int pincode){
		this.pincode=pincode;
	}
	
	public String getStateName(){
		return statename;
	}
	
	public void setStateName(String statename){
		this.statename=statename;
	}
	
	public int getStateCode(){
		return statecode;
	}
	
	public void setStateCode(int statecode){
		this.statecode=statecode;
	}
	
	public String getDistrictName(){
		return districtname;
	}
	
	public void setDistrictName(String districtname){
		this.districtname=districtname;
	}
	
	public int getDistrictCode(){
		return districtcode;
	}
	
	public void setDistrictCode(int districtcode){
		this.districtcode=districtcode;
	}
	
	public String getSubDistrictName(){
		return subdistrictname;
	}
	
	public void setSubDistrictName(String subdistrictname){
		this.subdistrictname=subdistrictname;
	}
	
	public int getSubDistrictCode(){
		return subdistrictcode;
	}
	
	public void setSubDistrictCode(int subdistrictcode){
		this.subdistrictcode=subdistrictcode;
	}
	
	public String getVtcName(){
		return vtcname;
	}
	
	public void setVtcName(String vtcname){
		this.vtcname=vtcname;
	}
	
	public String getVtcCode(){
		return vtccode;
	}
	
	public void setVtcCode(String vtccode){
		this.vtccode=vtccode;
	}
}
