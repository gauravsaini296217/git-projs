package kmobrevamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kmr_part")
public class Part {

	@Id
	@Column(name="part", length=40, nullable=false)
	private String part;
	
	@Column(name="spec", length=40, nullable=true)
	private String spec;
	
	@Column(name="rate", nullable=false)
	private float rate;
	
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return "Part [part=" + part + ", spec=" + spec + ", rate=" + rate + "]";
	}
	
	
	
}
