package aadhaartokens.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="enrolmenttypemaster")
public class EnrolmentType {

	@Id
	@Column(name="sno")
	private int sno; 
	
	@Column(name="enrolmenttype", columnDefinition="nvarchar(50)")
	private String enrolmenttype;
	
	@Column(name="amount")
	private int amount;
	
	public int getSno() {
		return sno;
	}
	
	public void setSno(int sno){
		this.sno=sno;
	}
	
	public String getEnrolmentType() {
		return enrolmenttype;
	}
	
	public void setEnrolmentType(String enrolmenttype){
		this.enrolmenttype=enrolmenttype;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount){
		this.amount=amount;
	}
}
