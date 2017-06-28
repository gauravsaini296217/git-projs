package aadhaartokens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pecmaster")
public class Pec implements Comparable<Pec>{
	
	@Override
	public int compareTo(Pec o) {
		return this.getPecname().compareTo(o.getPecname());
	}

	@Id
	@Column(name="pecid")
	private int pecid;
	
	@Column(name="pecname", columnDefinition="nvarchar(100)")
	private String pecname;
	
	@Column(name="pecadd1", columnDefinition="nvarchar(200)")
	private String pecadd1;
	
	@Column(name="peccity", columnDefinition="nvarchar(100)")
	private String peccity;
	
	@Column(name="pecdistrict", columnDefinition="nvarchar(100)")
	private String pecdistrict;
	
	@Column(name="pecstate", columnDefinition="nvarchar(100)")
	private String pecstate;
	
	@Column(name="pecpincode",columnDefinition="nvarchar(100)")
	private String pecpincode;
	
	@Column(name="districtid")
	private int districtid;

	@Column(name="peccode", columnDefinition="nvarchar(5)")
	private String peccode;
	
	public int getPecid() {
		return pecid;
	}

	public void setPecid(int pecid) {
		this.pecid = pecid;
	}

	public String getPecname() {
		return pecname;
	}

	public void setPecname(String pecname) {
		this.pecname = pecname;
	}

	public String getPecadd1() {
		return pecadd1;
	}

	public void setPecadd1(String pecadd1) {
		this.pecadd1 = pecadd1;
	}

	public String getPeccity() {
		return peccity;
	}

	public void setPeccity(String peccity) {
		this.peccity = peccity;
	}

	public String getPecdistrict() {
		return pecdistrict;
	}

	public void setPecdistrict(String pecdistrict) {
		this.pecdistrict = pecdistrict;
	}

	public String getPecstate() {
		return pecstate;
	}

	public void setPecstate(String pecstate) {
		this.pecstate = pecstate;
	}

	public String getPecpincode() {
		return pecpincode;
	}

	public void setPecpincode(String pecpincode) {
		this.pecpincode = pecpincode;
	}

	public int getDistrictid() {
		return districtid;
	}

	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}

	public String getPeccode() {
		return peccode;
	}

	public void setPeccode(String peccode) {
		this.peccode = peccode;
	}
	
}
