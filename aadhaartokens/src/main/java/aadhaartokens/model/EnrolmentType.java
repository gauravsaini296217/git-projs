package aadhaartokens.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="enrolmenttypemaster")
public class EnrolmentType implements Comparable<EnrolmentType>{
	
	@Override
	public int compareTo(EnrolmentType o) {
		return this.getEnrolmenttype().compareTo(o.getEnrolmenttype());
	}

	@Id
	@Column(name="sno", columnDefinition="numeric(18,0)")
	private int sno; 
	
	@Column(name="enrolmenttype", columnDefinition="nvarchar(50)")
	private String enrolmenttype;
	
	@Column(name="amount", columnDefinition="smallint")
	private int amount;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getEnrolmenttype() {
		return enrolmenttype;
	}

	public void setEnrolmenttype(String enrolmenttype) {
		this.enrolmenttype = enrolmenttype;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
