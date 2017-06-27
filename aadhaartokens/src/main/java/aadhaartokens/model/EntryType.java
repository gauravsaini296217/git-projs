package aadhaartokens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AadhaarTokenEntryType")
public class EntryType {
	
	
	@Id 
	@Column(name="typeid")
	private int typeid;
	
	@Column(name="information", columnDefinition="nvarchar(40)")
	private String information;
	
	public int getTypeID(){
		return typeid;
	}
	
	public void setTypeID(int typeid){
		this.typeid=typeid;
	}
	
	public String getInformation(){
		return information;
	}
	
	public void setInformation(String information){
		this.information=information;
	}
}
