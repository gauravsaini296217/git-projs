package aadhaartokens.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="aadhaartokenuser")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userid")
	private int id ;
	
	@Column(name="email",columnDefinition="varchar(60)")
	@Email(message="*Please provide a valid email")
	@NotEmpty(message="*Please provide an email")
	private String email;
	
	@Column(name="password",columnDefinition="varchar(100)")
	@Length(min=5,max=60,message="*Your Password must have at least 5 characters & at most 60 characters")
	@NotEmpty(message="*Please provide your password")
	private String password;
	
	@Column(name="name",columnDefinition="varchar(60)")
	@NotEmpty(message="*Please provide your name")
	private String name;
	
	@Column(name="active")
	private boolean active;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="aadhaartokenuserrole", joinColumns= @JoinColumn(name="userid"), inverseJoinColumns=@JoinColumn(name="roleid") )
	private Set<Role> roles;

	private String peccode;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPeccode() {
		return peccode;
	}

	public void setPeccode(String peccode) {
		this.peccode = peccode;
	}

		

}
