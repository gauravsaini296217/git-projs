package kmobrevamp.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class SearchComplaint {

	@Pattern(regexp="^|[A-Za-z0-9 -]+$")
	@NotEmpty(message="Serialno is mandatory")
	private String sno;

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}
	
	
	
}
