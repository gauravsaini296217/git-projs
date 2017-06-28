package aadhaartokens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aadhaartokenentrytype")
public class EntryType {
	
	
	@Id 
	@Column(name="typeid")
	private int typeid;
	
	@Column(name="information", columnDefinition="nvarchar(40)")
	private String information;

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	
	
}
