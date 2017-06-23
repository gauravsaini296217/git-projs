package aadhaartokens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statemaster")
public class State {

	@Id
	@Column(name="stateid")
	private int StateID;
	
	@Column(name="statename", columnDefinition="nvarchar(50)")
	private String StateName;
	
	@Column(name="agencyid", columnDefinition="int")
	private int AgencyID;
	
}
