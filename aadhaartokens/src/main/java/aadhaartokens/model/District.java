package aadhaartokens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="districtmaster")
public class District implements Comparable<District>{
	@Override
	public int compareTo(District o) {
		
		return this.getDistrictname().compareTo(o.getDistrictname());
	}

	@Id
	@Column(name="districtid")
	private int districtid;
	
	@Column(name="districtname", columnDefinition="nvarchar(50)")
	private String districtname;
	
	@Column(name="stateid")
	private int stateid;

	public int getDistrictid() {
		return districtid;
	}

	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public int getStateid() {
		return stateid;
	}

	public void setStateid(int stateid) {
		this.stateid = stateid;
	}
	
	
	
	
}
