package aadhaartokens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.internal.util.compare.ComparableComparator;

@Entity
@Table(name="statemaster")
public class State implements Comparable<State>{
	
	

	@Override
	public int compareTo(State o) {
		
		return this.statename.compareTo(o.statename);
	}

	@Id
	@Column(name="stateid")
	public int stateid;
	
	@Column(name="statename", columnDefinition="nvarchar(50)")
	public String statename;
	
	@Column(name="agencyid", columnDefinition="int")
	private int agencyid;

	public int getStateid() {
		return stateid;
	}

	public void setStateid(int stateid) {
		this.stateid = stateid;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public int getAgencyid() {
		return agencyid;
	}

	public void setAgencyid(int agencyid) {
		this.agencyid = agencyid;
	}

	@Override
	public String toString() {
		return "State [stateid=" + stateid + ", statename=" + statename + ", agencyid=" + agencyid + "]";
	}
	
	
	
}
