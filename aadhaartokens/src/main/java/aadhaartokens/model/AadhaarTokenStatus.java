package aadhaartokens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aadhaartokenstatusmaster")
public class AadhaarTokenStatus {

	@Id
	@Column(name="id", columnDefinition="varchar(4)")
	private String id;
	
	@Column(name="type", columnDefinition="varchar(15)")
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
